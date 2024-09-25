package com.example.soop.reservation.service;

import com.example.soop.contract.ReservationContract;
import com.example.soop.global.exception.*;
import com.example.soop.product.domain.Product;
import com.example.soop.product.domain.ProductRepository;
import com.example.soop.reservation.domain.Reservation;
import com.example.soop.reservation.domain.repository.ReservationRepository;
import com.example.soop.reservation.dto.req.ReservationCancelRequest;
import com.example.soop.reservation.dto.req.ReserveRequest;
import com.example.soop.reservation.dto.res.ReservationResponse;
import com.example.soop.reservation.dto.res.ReserveResponse;
import com.example.soop.timeslot.domain.TimeSlot;
import com.example.soop.timeslot.domain.repository.TimeSlotRepository;
import com.example.soop.user.domain.User;
import com.example.soop.user.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReserveService {
    @Value("${web3.contract-address}")
    private String contractAddress;
    private final Web3j web3j;
    private final ContractGasProvider contractGasProvider;
    private final ReservationRepository reservationRepository;
    private final TimeSlotRepository timeSlotRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ReserveService(Web3j web3j, ContractGasProvider contractGasProvider, ReservationRepository reservationRepository, TimeSlotRepository timeSlotRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.web3j = web3j;
        this.contractGasProvider = contractGasProvider;
        this.reservationRepository = reservationRepository;
        this.timeSlotRepository = timeSlotRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<ReservationResponse> getReservations(final Long userId) {
        User user = getUser(userId);
        return reservationRepository.findAllByUser(user)
                .stream()
                .map(reservation -> new ReservationResponse(reservation.getId(),
                        reservation.getProduct().getName(),
                        reservation.getCreatedAt(),
                        reservation.getStatus()))
                .collect(Collectors.toList());
    }

    public ReserveResponse getReservation(final Long memberId, final Long reservationId) {
        Reservation reservation = getReservation(reservationId);
        isValidReserver(memberId, reservation);

        return ReserveResponse.of(reservation);
    }

    // 사용자의 프라이빗 키로 reserve 함수를 호출
    @Transactional
    public ReserveResponse reserve(final Long userId, final ReserveRequest reserveRequest) {
        try {
            User user = getUser(userId);
            Product product = getProduct(reserveRequest);
            TimeSlot timeSlot = getTimeSlot(reserveRequest);

            ReservationContract userContract = getUserContract(user.getPrivateKey());
            userContract.reserve(convertReservationDate(), new BigInteger(product.getPrice())).send();
            Long nextId = Long.valueOf(userContract.nextResId().send().toString());

            Reservation save = reservationRepository.save(new Reservation(nextId - 1, timeSlot, user, product, LocalDateTime.now()));
            timeSlot.setUnAvailable();
            return ReserveResponse.of(save);
        } catch (Exception e) {
            System.out.println(e);
            throw new ReservationException(ExceptionCode.FAIL_RESERVE);
        }
    }

    // 예약 취소
    @Transactional
    public void cancel(final Long userId, final ReservationCancelRequest cancelRequest) {
        // 검증
        validateReservationByUser(userId, cancelRequest.reservationId());

        User user = getUser(userId);
        Reservation reservation = getReservation(cancelRequest.reservationId());

        ReservationContract userContract = getUserContract(user.getPrivateKey());
        try {
            userContract.cancelReservation(BigInteger.valueOf(cancelRequest.reservationId())).send();
        } catch (Exception e) {
            System.out.println(e);
            throw new ReservationException(ExceptionCode.FAIL_CANCEL);
        } finally {
            reservation.updateToCanceled();
        }
    }

    private void validateReservationByUser(final Long userId, final Long reservationId) {
        if (!reservationRepository.existsByUserIdAndId(userId, reservationId)) {
            throw new ReservationException(ExceptionCode.INVALID_RESERVER);
        }
    }

    private ReservationContract getUserContract(final String privateKey) {
        Credentials userCredentials = Credentials.create(privateKey);
        return ReservationContract.load(contractAddress, web3j, userCredentials, contractGasProvider);
    }

    private static BigInteger convertReservationDate() {
        LocalDateTime now = LocalDateTime.now();

        // LocalDateTime을 epoch 초로 변환 (UTC 기준)
        long epochSeconds = now.toEpochSecond(ZoneOffset.UTC);

        // epoch 초를 BigInteger로 변환
        return BigInteger.valueOf(epochSeconds);
    }


    // 예약 완료
    public void complete(final Long userId, final Long reserveId) {
        try {
            User user = getUser(userId);
            ReservationContract userContract = getUserContract(user.getPrivateKey());
            userContract.finalizeTransaction(BigInteger.valueOf(reserveId)).send();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private TimeSlot getTimeSlot(final ReserveRequest reserveRequest) {
        return timeSlotRepository.findById(reserveRequest.timeSlotId()).orElseThrow(() -> new TimeSlotException(ExceptionCode.NO_SUCH_TIMESLOT));
    }

    private Product getProduct(final ReserveRequest reserveRequest) {
        return productRepository.findById(reserveRequest.productId()).orElseThrow(() -> new ProductException(ExceptionCode.NO_SUCH_PRODUCT));
    }

    private User getUser(final Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserException(ExceptionCode.NO_SUCH_USER));
    }

    private void isValidReserver(final Long memberId, final Reservation reservation) {
        if (reservation.getUser().getId() != memberId) {
            throw new ReservationException(ExceptionCode.INVALID_RESERVER);
        }
    }

    private Reservation getReservation(final Long reservationId) {
        return reservationRepository.findById(reservationId).orElseThrow(() -> new ReservationException(ExceptionCode.NO_SUCH_RESERVATION));
    }
}
