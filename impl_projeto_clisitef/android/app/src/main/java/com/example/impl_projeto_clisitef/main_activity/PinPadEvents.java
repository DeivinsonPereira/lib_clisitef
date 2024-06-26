package com.example.impl_projeto_clisitef.main_activity;

public enum PinPadEvents {
    GENERIC_ERROR("GENERIC_ERROR"),
    START_BLUETOOTH("EVT_INICIA_ATIVACAO_BT"),
    END_BLUETOOTH("EVT_FIM_ATIVACAO_BT"),
    WAITING_PINPAD_CONNECTION("EVT_INICIA_AGUARDA_CONEXAO_PP"),
    PINPAD_OK("EVT_FIM_AGUARDA_CONEXAO_PP"),
    WAITING_PINPAD_BLUETOOTH("EVT_PP_BT_CONFIGURANDO"),
    PINPAD_BLUETOOTH_CONNECTED("EVT_PP_BT_CONFIGURADO"),
    PINPAD_BLUETOOTH_DISCONNECTED("EVT_PP_BT_DESCONECTADO");

    private String event;

    PinPadEvents(String event){
        this.event = event;
    }

    public String getEvent() {
        return this.event;
    }

}
