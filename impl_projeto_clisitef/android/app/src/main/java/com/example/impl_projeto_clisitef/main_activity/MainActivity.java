package com.example.impl_projeto_clisitef.main_activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;

import br.com.softwareexpress.sitef.android.CliSiTef;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;


public class MainActivity extends FlutterActivity{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public void configureFlutterEngine(FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        // Assegure-se que o ClisitefPlugin está registrado
        flutterEngine.getPlugins().add(new ClisitefPlugin());
    }
}

