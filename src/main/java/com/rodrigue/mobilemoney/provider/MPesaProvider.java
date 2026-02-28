package com.rodrigue.mobilemoney.provider;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class MPesaProvider implements MobileMoneyProvider {

    @Override
    public String initiateTransfer(
            String senderPhone,
            String receiverPhone,
            BigDecimal amount,
            String currency,
            String idempotencyKey
    ) {
        // Day 4: real Daraja STK Push call goes here
        // For now we return a simulated transaction ID
        return "MPESA-" + idempotencyKey;
    }

    @Override
    public BigDecimal checkBalance(String phoneNumber) {
        // Day 4: real Daraja balance check goes here
        return new BigDecimal("10000.00");
    }

    @Override
    public String getTransactionStatus(String providerTransactionId) {
        // Day 4: real Daraja status check goes here
        return "SUCCESS";
    }
}