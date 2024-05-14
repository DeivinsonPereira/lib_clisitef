package com.example.impl_projeto_clisitef.main_activity;

import android.annotation.SuppressLint;

import br.com.softwareexpress.sitef.android.CliSiTef;
import io.flutter.Log;

public class PinPadMethods extends SiTefClient {

    CliSiTef cliSiTef = CliSiTef.getInstance();


    // Método para definir uma mensagem de exibição no pinpad
    public void setDisplayMessage(String message) {
        success(cliSiTef.pinpad.setDisplayMessage(message));
    }

    // Método para ler uma entrada de Sim ou Não do pinpad
    public void readYesOrNo(String message) {
        success(cliSiTef.pinpad.readYesNo(message));
    }

    // Método para verificar se o pinpad está presente
    @SuppressLint("LongLogTag")
    public void isPresent() {
        try {
            success(cliSiTef.pinpad.isPresent());
        } catch (Exception e) {
            success(false);
        } catch (Error e) {
            Log.e("PinPadMethods::isPresent", e.toString());
            success(false);
        }
    }


}