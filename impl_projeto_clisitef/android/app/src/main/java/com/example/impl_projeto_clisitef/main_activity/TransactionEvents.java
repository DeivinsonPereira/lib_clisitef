package com.example.impl_projeto_clisitef.main_activity;

public enum TransactionEvents {

    TRANSACTION_CONFIRM("TRANSACTION_CONFIRM"),
    TRANSACTION_FAILED("TRANSACTION_FAILED"),
    TRANSACTION_OK("TRANSACTION_OK"),
    TRANSACTION_ERROR("TRANSACTION_ERROR");

    private final String named;

    // Constructor for the enum
    TransactionEvents(String named) {
        this.named = named;
    }

    // Getter for the named property
    public String getNamed() {
        return this.named;
    }
}
