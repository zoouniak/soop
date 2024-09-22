package com.example.soop.wallet.service;

import com.example.soop.global.exception.UserException;
import com.example.soop.global.exception.wallet.WalletException;
import com.example.soop.user.domain.User;
import com.example.soop.user.domain.repository.UserRepository;
import com.example.soop.wallet.dto.request.WalletCreateRequest;
import com.example.soop.wallet.dto.response.BalanceResponse;
import com.example.soop.wallet.dto.response.WalletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;

import static com.example.soop.global.exception.ExceptionCode.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletService {

    @Value("web3.wallet-path")
    private String WALLET_PATH;
    private final Web3j web3j;
    private final UserRepository userRepository;


    public WalletResponse createWallet(WalletCreateRequest req) {
        Path walletDir = Paths.get(WALLET_PATH);
        if (!Files.exists(walletDir)) {
            try {
                Files.createDirectory(walletDir);
            } catch (IOException e) {
                log.error(e.getMessage());
                throw new WalletException(FAIL_CREATE_WALLET);
            }
        }

        String walletName;

        try {
            walletName = WalletUtils.generateLightNewWalletFile(req.password(), walletDir.toFile());
        } catch (Exception e) {
            throw  new WalletException(FAIL_LOAD_BALANCE);
        }

        Credentials credentials;
        try {
            credentials = WalletUtils.loadCredentials(req.password(), WALLET_PATH + walletName);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new WalletException(FAIL_CREATE_WALLET);
        }
        String privateKey = credentials.getEcKeyPair().getPrivateKey().toString(16);
        String address = credentials.getAddress();

        return new WalletResponse(address, privateKey);
    }

    public WalletResponse createWalletFromPrivateKey(String privateKey, String password) throws Exception {
        ECKeyPair ecKeyPair = ECKeyPair.create(new BigInteger(privateKey, 16));
        String walletName = WalletUtils.generateWalletFile(password, ecKeyPair, Paths.get(WALLET_PATH).toFile(), false);
        Credentials credentials = WalletUtils.loadCredentials(password, WALLET_PATH + walletName);
        String address = credentials.getAddress();

        return new WalletResponse(address, privateKey);
    }

    public BalanceResponse getBalance(Long userId) {
        User user = getUser(userId);
        String walletAddress = user.getWallet();
        BigInteger weiBalance;
        try {
            weiBalance = web3j.ethGetBalance(walletAddress, DefaultBlockParameterName.LATEST)
                    .sendAsync()
                    .get()
                    .getBalance();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        // Convert Wei to Ether
        return new BalanceResponse(Convert.fromWei(weiBalance.toString(), Convert.Unit.ETHER));
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(()->new UserException(NO_SUCH_USER));
    }
}

