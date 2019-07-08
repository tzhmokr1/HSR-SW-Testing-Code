package ch.hsr.testing.unittest.address.refactoringexample.api;


import ch.hsr.testing.unittest.address.api.AddressHistoryChain;

public class Company {
    private final AddressHistoryChain addressHistoryChain;

    public Company(AddressHistoryChain addressHistoryChain) {
        this.addressHistoryChain = addressHistoryChain;
    }

    public AddressHistoryChain getShippingAddressHistoryChain() {
        return addressHistoryChain;
    }
}
