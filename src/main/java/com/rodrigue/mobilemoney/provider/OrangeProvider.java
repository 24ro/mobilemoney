package com.rodrigue.mobilemoney.provider;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class OrangeProvider implements MobileMoneyProvider {

    @Override
    public String initiateTransfer(
            String senderPhone,
            String receiverPhone,
            BigDecimal amount,
            String currency,
            String idempotencyKey
    ) {
        return "ORANGE-" + idempotencyKey;
    }

    @Override
    public BigDecimal checkBalance(String phoneNumber) {
        return new BigDecimal("7500.00");
    }

    @Override
    public String getTransactionStatus(String providerTransactionId) {
        return "SUCCESS";
    }
}