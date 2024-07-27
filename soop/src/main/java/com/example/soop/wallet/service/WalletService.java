package com.example.soop.wallet.service;

import com.example.soop.global.exception.wallet.WalletException;
import com.example.soop.wallet.dto.WalletResponse;
import com.example.soop.wallet.dto.request.WalletCreateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.example.soop.global.exception.ExceptionCode.FAIL_CREATE_WALLET;
import static com.example.soop.global.exception.ExceptionCode.FAIL_LOAD_BALANCE;

@Service
@Slf4j
public class WalletService {

    private static final String WALLET_PATH = "C://Users//오주은//Desktop//personal study//도전학기제//";

    public WalletResponse createWallet(WalletCreateRequest req)  {
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
            log.error(e.getMessage());
            throw new WalletException(FAIL_CREATE_WALLET);
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

    public BigDecimal getBalance(String wallet) {
        String sepoliaUrl = "https://sepolia.infura.io/v3/6e30d3806a854239bd8bda2c95f17ab8"; // Infura 또는 다른 서비스의 URL 사용
        Web3j web3j = Web3j.build(new HttpService(sepoliaUrl));

        // 잔액 조회
        EthGetBalance ethGetBalance;
        try {
            ethGetBalance = web3j.ethGetBalance(wallet, DefaultBlockParameterName.LATEST).send();
        } catch (IOException e) {
            throw new WalletException(FAIL_LOAD_BALANCE);
        }

        BigInteger wei = ethGetBalance.getBalance();

        // 잔액을 Ether로 변환
        return Convert.fromWei(wei.toString(), Convert.Unit.ETHER);
    }
}

