package com.example.impl_projeto_clisitef.main_activity;

    public enum DataEvents {
        DATA("DATA"),
        MESSAGE_CASHIER("MESSAGE_CASHIER"),
        // MESSAGE_CLEAR_CASHIER("MESSAGE_CLEAR_CASHIER"), // Comentado como no original
        MESSAGE_CUSTOMER("MESSAGE_CUSTOMER"),
        // MESSAGE_CLEAR_CUSTOMER("MESSAGE_CLEAR_CUSTOMER"), // Comentado como no original
        MESSAGE_CASHIER_CUSTOMER("MESSAGE_CASHIER_CUSTOMER"),
        // MESSAGE_CLEAR_CASHIER_CUSTOMER("MESSAGE_CLEAR_CASHIER_CUSTOMER"), // Comentado como no original
        MENU_TITLE("MENU_TITLE"),
        // MENU_TITLE_CLEAR("MENU_TITLE_CLEAR"), // Comentado como no original
        HEADER_SHOW("HEADER_SHOW"),
        // HEADER_CLEAR("HEADER_CLEAR"), // Comentado como no original
        CONFIRM_GO_BACK("CONFIRM_GO_BACK"),
        CONFIRMATION("CONFIRMATION"),
        MENU_OPTIONS("MENU_OPTIONS"),
        PRESS_ANY_KEY("PRESS_ANY_KEY"),
        ABORT_REQUEST("ABORT_REQUEST"),
        GET_FIELD_INTERNAL("GET_FIELD_INTERNAL"),
        GET_FIELD("GET_FIELD"),
        GET_FIELD_CHEQUE("GET_FIELD_CHEQUE"),
        GET_FIELD_TRACK("GET_FIELD_TRACK"),
        GET_FIELD_PASSWORD("GET_FIELD_PASSWORD"),
        GET_FIELD_BARCODE("GET_FIELD_BARCODE"),
        GET_FIELD_CURRENCY("GET_FIELD_CURRENCY"),
        GET_PINPAD_CONFIRMATION("GET_PINPAD_CONFIRMATION"),
        GET_MASKED_FIELD("GET_MASKED_FIELD"),
        SHOW_QRCODE_FIELD("SHOW_QRCODE_FIELD"),
        REMOVE_QRCODE_FIELD("REMOVE_QRCODE_FIELD"),
        MESSAGE_QRCODE("MESSAGE_QRCODE");

        private final String named;

        // Constructor for the enum
        DataEvents(String named) {
            this.named = named;
        }

        // Getter for the named property
        public String getNamed() {
            return this.named;
        }
    }

