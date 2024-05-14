package com.example.impl_projeto_clisitef.main_activity;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import br.com.softwareexpress.sitef.android.CliSiTef;
import br.com.softwareexpress.sitef.android.CliSiTefI;
import br.com.softwareexpress.sitef.android.ICliSiTefListener;

import io.flutter.Log;
import io.flutter.plugin.common.EventChannel;

public class CliSiTefListener implements ICliSiTefListener {

    private EventChannel.EventSink dataSink = null;
    private EventChannel.EventSink eventSink = null;

    @Override
    public void onData(int currentStage, int command, int fieldId, int minLength, int maxLength, byte[] input) {
        String data = "";
        ClisitefData clisitefData = null;
        CliSiTef cliSiTef = CliSiTef.getInstance();
        switch (command) {
            case CliSiTef.CMD_RESULT_DATA:
                clisitefData = new ClisitefData(DataEvents.DATA, currentStage, cliSiTef.getBuffer(), true, fieldId);
                break;
            case CliSiTef.CMD_SHOW_MSG_CASHIER:
            case CliSiTef.CMD_CLEAR_MSG_CASHIER:
                clisitefData = new ClisitefData(DataEvents.MESSAGE_CASHIER, currentStage, cliSiTef.getBuffer());
                break;
            case CliSiTef.CMD_SHOW_MSG_CUSTOMER:
            case CliSiTef.CMD_CLEAR_MSG_CUSTOMER:
                clisitefData = new ClisitefData(DataEvents.MESSAGE_CUSTOMER, currentStage, cliSiTef.getBuffer());
                break;
            case CliSiTef.CMD_SHOW_MSG_CASHIER_CUSTOMER:
            case CliSiTef.CMD_CLEAR_MSG_CASHIER_CUSTOMER:
                clisitefData = new ClisitefData(DataEvents.MESSAGE_CASHIER_CUSTOMER, currentStage, cliSiTef.getBuffer());
                break;
            case CliSiTef.CMD_SHOW_MENU_TITLE:
            case CliSiTef.CMD_CLEAR_MENU_TITLE:
                clisitefData = new ClisitefData(DataEvents.MENU_TITLE, currentStage, cliSiTef.getBuffer());
                break;
            case CliSiTef.CMD_SHOW_HEADER:
            case CliSiTef.CMD_CLEAR_HEADER:
                clisitefData = new ClisitefData(DataEvents.HEADER_SHOW, currentStage, cliSiTef.getBuffer());
                break;
            case CliSiTef.CMD_CONFIRM_GO_BACK:
                clisitefData = new ClisitefData(DataEvents.CONFIRM_GO_BACK, currentStage, cliSiTef.getBuffer(), false);
                break;
            case CliSiTef.CMD_CONFIRMATION:
                clisitefData = new ClisitefData(DataEvents.CONFIRMATION, currentStage, cliSiTef.getBuffer(), false);
                break;
            case CliSiTef.CMD_GET_MENU_OPTION:
                clisitefData = new ClisitefData(DataEvents.MENU_OPTIONS, currentStage, cliSiTef.getBuffer(), false);
                break;
            case CliSiTef.CMD_PRESS_ANY_KEY:
                clisitefData = new ClisitefData(DataEvents.PRESS_ANY_KEY, currentStage, cliSiTef.getBuffer(), false);
                break;
            case CliSiTef.CMD_ABORT_REQUEST:
                clisitefData = new ClisitefData(DataEvents.ABORT_REQUEST, currentStage, cliSiTef.getBuffer());
                break;
            case CliSiTef.CMD_GET_FIELD_INTERNAL:
                clisitefData = new ClisitefData(DataEvents.GET_FIELD_INTERNAL, currentStage, cliSiTef.getBuffer(), false, maxLength, minLength);
                break;
            case CliSiTef.CMD_GET_FIELD:
                clisitefData = new ClisitefData(DataEvents.GET_FIELD, currentStage, cliSiTef.getBuffer(), false, maxLength, minLength);
                break;
            case CliSiTef.CMD_GET_FIELD_CHEQUE:
                clisitefData = new ClisitefData(DataEvents.GET_FIELD_CHEQUE, currentStage, cliSiTef.getBuffer(), false, maxLength, minLength);
                break;
            case CliSiTef.CMD_GET_FIELD_TRACK:
                clisitefData = new ClisitefData(DataEvents.GET_FIELD_TRACK, currentStage, cliSiTef.getBuffer(), false, maxLength, minLength);
                break;
            case CliSiTef.CMD_GET_FIELD_PASSWORD:
                clisitefData = new ClisitefData(DataEvents.GET_FIELD_PASSWORD, currentStage, cliSiTef.getBuffer(), false, maxLength, minLength);
                break;
            case CliSiTef.CMD_GET_FIELD_CURRENCY:
                clisitefData = new ClisitefData(DataEvents.GET_FIELD_CURRENCY, currentStage, cliSiTef.getBuffer(), false, maxLength, minLength);
                break;
            case CliSiTef.CMD_GET_FIELD_BARCODE:
                clisitefData = new ClisitefData(DataEvents.GET_FIELD_BARCODE, currentStage, cliSiTef.getBuffer(), false, maxLength, minLength);
                break;
            case CliSiTef.CMD_GET_PINPAD_CONFIRMATION:
                clisitefData = new ClisitefData(DataEvents.GET_PINPAD_CONFIRMATION, currentStage, cliSiTef.getBuffer(), false, maxLength, minLength);
                break;
            case CliSiTef.CMD_GET_MASKED_FIELD:
                clisitefData = new ClisitefData(DataEvents.GET_MASKED_FIELD, currentStage, cliSiTef.getBuffer(), false, maxLength, minLength);
                break;
            case CliSiTef.CMD_SHOW_QRCODE_FIELD:
                clisitefData = new ClisitefData(DataEvents.SHOW_QRCODE_FIELD, currentStage, cliSiTef.getBuffer());
                break;
            case CliSiTef.CMD_REMOVE_QRCODE_FIELD:
                clisitefData = new ClisitefData(DataEvents.REMOVE_QRCODE_FIELD, currentStage, cliSiTef.getBuffer());
                break;
            case CliSiTef.CMD_MESSAGE_QRCODE:
                clisitefData = new ClisitefData(DataEvents.MESSAGE_QRCODE, currentStage, cliSiTef.getBuffer());
                break;
            default:
                Log.i("CliSiTefListener", "onData Default case for command " + command);
                break;
        }

        if (clisitefData != null) {
            if (dataSink != null) {
                dataSink.success(clisitefData.toDataSink());
            }
            if (clisitefData.isShouldContinue()) {
                cliSiTef.continueTransaction(data);
            }
        } else {
            cliSiTef.continueTransaction(data);
        }
    }

    @Override
    public void onTransactionResult(int currentStage, int resultCode) {
        CliSiTef cliSiTef = CliSiTef.getInstance();

        if (currentStage == 1) {
            if (resultCode == 0) {
                try {
                    cliSiTef.finishTransaction(1);
                    if (eventSink != null) {
                        eventSink.success(TransactionEvents.TRANSACTION_CONFIRM.getNamed());
                    }
                } catch (Exception e) {
                    if (eventSink != null) {
                        eventSink.error(TransactionEvents.TRANSACTION_FAILED.getNamed(), e.toString(), e);
                    }
                }
            }
        } else {
            if (resultCode == 0) {
                if (eventSink != null) {
                    eventSink.success(TransactionEvents.TRANSACTION_OK.getNamed());
                }
            }
        }
        if (eventSink != null) {
            eventSink.error(TransactionEvents.TRANSACTION_ERROR.getNamed(), null, null);
        }
    }

    public void setEventSink(EventChannel.EventSink sink) {
        this.eventSink = sink;
    }

    public void setDataSink(EventChannel.EventSink sink) {
        this.dataSink = sink;
    }

    public Handler onMessage(Looper looper) {
        return new Handler(looper, new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                switch (message.what) {
                    case CliSiTefI.EVT_INICIA_ATIVACAO_BT:
                        if (eventSink != null) eventSink.success(PinPadEvents.START_BLUETOOTH.getEvent());
                        break;
                    case CliSiTefI.EVT_FIM_ATIVACAO_BT:
                        if (eventSink != null) eventSink.success(PinPadEvents.END_BLUETOOTH.getEvent());
                        break;
                    case CliSiTefI.EVT_INICIA_AGUARDA_CONEXAO_PP:
                        if (eventSink != null) eventSink.success(PinPadEvents.WAITING_PINPAD_CONNECTION.getEvent());
                        break;
                    case CliSiTefI.EVT_FIM_AGUARDA_CONEXAO_PP:
                        if (eventSink != null) eventSink.success(PinPadEvents.PINPAD_OK.getEvent());
                        break;
                    case CliSiTefI.EVT_PP_BT_CONFIGURANDO:
                        if (eventSink != null) eventSink.success(PinPadEvents.WAITING_PINPAD_BLUETOOTH.getEvent());
                        break;
                    case CliSiTefI.EVT_PP_BT_CONFIGURADO:
                        if (eventSink != null) eventSink.success(PinPadEvents.PINPAD_BLUETOOTH_CONNECTED.getEvent());
                        break;
                    case CliSiTefI.EVT_PP_BT_DESCONECTADO:
                        if (eventSink != null) eventSink.success(PinPadEvents.PINPAD_BLUETOOTH_DISCONNECTED.getEvent());
                        break;
                    default:
                        if (eventSink != null) eventSink.error(PinPadEvents.GENERIC_ERROR.getEvent(), Integer.toString(message.what), message);
                        break;
                }
                return true;
            }
        });
    }
}
