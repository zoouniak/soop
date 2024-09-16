package com.example.soop.wallet.dto.response;

import java.math.BigDecimal;

public record BalanceResponse(
        BigDecimal balance
) {
}
