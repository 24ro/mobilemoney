package com.rodrigue.mobilemoney.provider;

import java.math.BigDecimal;

public interface MobileMoneyProvider {

    String initiateTransfer(
            String senderPhone,
            String receiverPhone,
            BigDecimal amount,
            String currency,
            String idempotencyKey
    );

    BigDecimal checkBalance(String phoneNumber);

    String getTransactionStatus(String providerTransactionId);
}
