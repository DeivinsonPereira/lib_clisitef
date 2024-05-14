package com.example.impl_projeto_clisitef.main_activity;

import br.com.softwareexpress.sitef.android.CliSiTef;
import io.flutter.Log;
import io.flutter.plugin.common.MethodChannel;

public class SiTefClient {
    private MethodChannel.Result resultHandler;

    CliSiTef cliSiTef = CliSiTef.getInstance();

    // Set the result handler
    public void setResultHandler(MethodChannel.Result result) {
        this.resultHandler = result;
    }

    // Handle success
    public void success(Object result) {
        if (resultHandler != null) {
            resultHandler.success(result);
        } else {
            Log.v("br.com.claudneysessa.clisitef.SiTefClient::success", result == null ? "null" : result.toString());
        }
    }

    // Handle errors
    public void error(String errorCode, String errorMessage, Object errorDetails) {
        if (resultHandler != null) {
            resultHandler.error(errorCode, errorMessage, errorDetails);
        } else {
            Log.v("br.com.claudneysessa.clisitef.SiTefClient::error::" + errorCode, errorMessage == null ? "null" : errorMessage);
        }
    }

    // Another error method to handle cases without errorDetails
    public void error(String errorCode, String errorMessage) {
        this.error(errorCode, errorMessage, null);
    }

    // Handle not implemented actions
    public void notImplemented() {
        if (resultHandler != null) {
            resultHandler.notImplemented();
        } else {
            Log.v("br.com.claudneysessa.clisitef.SiTefClient::notImplemented", "");
        }
    }
}
