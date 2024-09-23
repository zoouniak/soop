package com.example.soop.reservation.service;

import com.example.soop.contract.ReservationContract;
import com.example.soop.global.exception.*;
import com.example.soop.product.domain.Product;
import com.example.soop.product.domain.ProductRepository;
import com.example.soop.reservation.domain.Reservation;
import com.example.soop.reservation.domain.repository.ReservationRepository;
import com.example.soop.reservation.dto.req.ReserveRequest;
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

    // 사용자의 프라이빗 키로 reserve 함수를 호출
    @Transactional
    public ReserveResponse reserve(Long userId, ReserveRequest reserveRequest) {
        try {
            User user = getUser(userId);
            Product product = getProduct(reserveRequest);
            TimeSlot timeSlot = getTimeSlot(reserveRequest);

            ReservationContract userContract = getUserContract(user.getPrivateKey());
            userContract.reserve(convertReservationDate(), new BigInteger(product.getPrice())).send();
            Long nextId = Long.valueOf(userContract.nextResId().send().toString());

            reservationRepository.save(new Reservation(nextId - 1, timeSlot, user, product, LocalDateTime.now()));
            timeSlot.setUnAvailable();
            return new ReserveResponse(1L, product.getName(), timeSlot.getDate(), timeSlot.getStartedAt(), user.getNickname(), user.getPhone());
        } catch (Exception e) {
            System.out.println(e);
            throw new ReservationException(ExceptionCode.FAIL_RESERVE);
        }
    }

    private ReservationContract getUserContract(String privateKey) {
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

    // 예약 취소
    public void cancel(Long userId, Long reserveId) {
        try {
            User user = getUser(userId);
            ReservationContract userContract = getUserContract(user.getPrivateKey());
            userContract.cancelReservation(BigInteger.valueOf(reserveId)).send();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 예약 완료
    public void complete(Long userId, Long reserveId) {
        try {
            User user = getUser(userId);
            ReservationContract userContract = getUserContract(user.getPrivateKey());
            userContract.finalizeTransaction(BigInteger.valueOf(reserveId)).send();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private TimeSlot getTimeSlot(ReserveRequest reserveRequest) {
        return timeSlotRepository.findById(reserveRequest.timeSlotId()).orElseThrow(() -> new TimeSlotException(ExceptionCode.NO_SUCH_TIMESLOT));
    }

    private Product getProduct(ReserveRequest reserveRequest) {
        return productRepository.findById(reserveRequest.productId()).orElseThrow(() -> new ProductException(ExceptionCode.NO_SUCH_PRODUCT));
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserException(ExceptionCode.NO_SUCH_USER));
    }
}
