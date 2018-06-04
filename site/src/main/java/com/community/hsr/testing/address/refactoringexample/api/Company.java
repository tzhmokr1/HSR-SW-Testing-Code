package com.community.hsr.testing.address.refactoringexample.api;

import com.community.hsr.testing.address.api.AddressHistoryChain;

public class Company {
    private final AddressHistoryChain addressHistoryChain;

    public Company(AddressHistoryChain addressHistoryChain) {
        this.addressHistoryChain = addressHistoryChain;
    }

    public AddressHistoryChain getShippingAddressHistoryChain() {
        return addressHistoryChain;
    }
}
