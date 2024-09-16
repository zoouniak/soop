package com.example.soop.wallet.service;

import com.example.soop.global.exception.wallet.WalletException;
import com.example.soop.wallet.dto.request.WalletCreateRequest;
import com.example.soop.wallet.dto.response.BalanceResponse;
import com.example.soop.wallet.dto.response.WalletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;

import static com.example.soop.global.exception.ExceptionCode.FAIL_CREATE_WALLET;
import static com.example.soop.global.exception.ExceptionCode.FAIL_LOAD_BALANCE;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletService {

    private static final String WALLET_PATH = "C://Users//오주은//Desktop//personal study//도전학기제//";
    private final Web3j web3j;

    public WalletService() {
        // Connect to Ganache
        web3j = Web3j.build(new HttpService("http://127.0.0.1:8545")); // Replace with your Ganache RPC URL
    }

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

    public BalanceResponse getBalance(String walletAddress) {
        BigInteger weiBalance = null;
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
}

