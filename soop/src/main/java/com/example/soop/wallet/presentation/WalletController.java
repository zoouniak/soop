package com.example.soop.wallet.presentation;

import com.example.soop.wallet.dto.request.WalletCreateRequest;
import com.example.soop.wallet.dto.response.BalanceResponse;
import com.example.soop.wallet.dto.response.WalletResponse;
import com.example.soop.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wallet")
public class WalletController {

    private final WalletService walletService;

    /*
     * 지갑 생성
     */
    @PostMapping("/create")
    public ResponseEntity<WalletResponse> createWallet(@RequestBody WalletCreateRequest req) {
        WalletResponse wallet = walletService.createWallet(req);
        return ResponseEntity.ok(wallet);

    }

    @GetMapping("/balance")
    public ResponseEntity<BalanceResponse> getBalance(@RequestParam String wallet) {


        return ResponseEntity.ok(walletService.getBalance(wallet));
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
