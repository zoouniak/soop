package com.example.soop.wallet.presentation;

import com.example.soop.wallet.service.WalletService;
import com.example.soop.wallet.dto.WalletResponse;
import com.example.soop.wallet.dto.request.WalletCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    /*
     * 지갑 생성
     */
    @PostMapping("/create")
    public ResponseEntity<WalletResponse> createWallet(@RequestBody WalletCreateRequest req) {
            WalletResponse wallet = walletService.createWallet(req);
            return ResponseEntity.ok(wallet);

    }
    @GetMapping("/balance")
    public ResponseEntity getBalance(@RequestParam String wallet){
        BigDecimal balance = walletService.getBalance(wallet);
        return ResponseEntity.ok(balance);
    }

    @PostMapping("/create-from-private")
    public WalletResponse createWalletFromPrivateKey(@RequestParam String privateKey, @RequestParam String password) {
        try {
            return walletService.createWalletFromPrivateKey(privateKey, password);
        } catch (Exception e) {
            throw new RuntimeException("Wallet creation from private key failed", e);
        }
    }
}
