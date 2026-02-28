package com.rodrigue.mobilemoney.router;

import com.rodrigue.mobilemoney.provider.AirtelProvider;
import com.rodrigue.mobilemoney.provider.MobileMoneyProvider;
import com.rodrigue.mobilemoney.provider.MPesaProvider;
import com.rodrigue.mobilemoney.provider.OrangeProvider;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ProviderRouter {

    private final Map<String, MobileMoneyProvider> prefixMap;

    public ProviderRouter(
            MPesaProvider mPesaProvider,
            AirtelProvider airtelProvider,
            OrangeProvider orangeProvider
    ) {
        prefixMap = Map.of(
                "+2547", mPesaProvider,    // Kenya M-Pesa
                "+2541", mPesaProvider,    // Kenya M-Pesa (secondary prefix)
                "+25670", airtelProvider,  // Uganda Airtel (070x)
                "+25674", airtelProvider,  // Uganda Airtel (074x)
                "+25675", airtelProvider,  // Uganda Airtel (075x)
                "+25620", airtelProvider,  // Uganda Airtel (020x)
                "+2557", airtelProvider,   // Tanzania Airtel
                "+2250", orangeProvider,   // Ivory Coast Orange
                "+2212", orangeProvider    // Senegal Orange
        );
    }

    public MobileMoneyProvider route(String phoneNumber) {
        return prefixMap.entrySet()
                .stream()
                .filter(entry -> phoneNumber.startsWith(entry.getKey()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No provider found for phone number: " + phoneNumber
                ));
    }

    public String detectNetwork(String phoneNumber) {
        MobileMoneyProvider provider = route(phoneNumber);
        if (provider instanceof MPesaProvider) return "MPESA";
        if (provider instanceof AirtelProvider) return "AIRTEL";
        if (provider instanceof OrangeProvider) return "ORANGE";
        return "UNKNOWN";
    }
}