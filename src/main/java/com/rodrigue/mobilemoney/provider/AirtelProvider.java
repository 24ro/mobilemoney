package com.rodrigue.mobilemoney.provider;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class AirtelProvider implements MobileMoneyProvider {

    @Override
    public String initiateTransfer(
            String senderPhone,
            String receiverPhone,
            BigDecimal amount,
            String currency,
            String idempotencyKey
    ) {
        return "AIRTEL-" + idempotencyKey;
    }

    @Override
    public BigDecimal checkBalance(String phoneNumber) {
        return new BigDecimal("5000.00");
    }

    @Override
    public String getTransactionStatus(String providerTransactionId) {
        return "SUCCESS";
    }
}