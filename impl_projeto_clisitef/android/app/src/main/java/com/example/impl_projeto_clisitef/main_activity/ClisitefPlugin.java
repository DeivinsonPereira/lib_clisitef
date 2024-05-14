package com.example.impl_projeto_clisitef.main_activity;

import android.app.Activity;
import android.os.Looper;

import androidx.annotation.NonNull;

import com.example.impl_projeto_clisitef.main_activity.channel.DataHandler;
import com.example.impl_projeto_clisitef.main_activity.channel.EventHandler;

import br.com.softwareexpress.sitef.android.CliSiTef;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel.Result;


public class ClisitefPlugin implements FlutterPlugin, MethodChannel.MethodCallHandler, ActivityAware {
    private MethodChannel methodChannel;
    private Activity activity;
    private EventChannel eventChannel;
    private EventChannel dataChannel;
    private CliSiTef cliSiTef;
    private TefMethods tefMethods;
    private PinPadMethods pinPadMethods;
    private CliSiTefListener cliSiTefListener;
    private static final String CHANNEL = "com.example.impl_projeto_clisitef";

    @Override
    public void onAttachedToEngine(@NonNull FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        methodChannel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), CHANNEL);

        cliSiTef = new CliSiTef(flutterPluginBinding.getApplicationContext());
        cliSiTefListener = new CliSiTefListener();

        eventChannel = new EventChannel(flutterPluginBinding.getBinaryMessenger(), CHANNEL + "/events");
        eventChannel.setStreamHandler(EventHandler.setListener(cliSiTefListener));

        dataChannel = new EventChannel(flutterPluginBinding.getBinaryMessenger(), CHANNEL + "/events/data");
        dataChannel.setStreamHandler(DataHandler.setListener(cliSiTefListener));

        cliSiTef.setMessageHandler(cliSiTefListener.onMessage(Looper.getMainLooper()));

        tefMethods = new TefMethods();
        pinPadMethods = new PinPadMethods();

        methodChannel.setMethodCallHandler(this);
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        tefMethods.setResultHandler(result);
        pinPadMethods.setResultHandler(result);

        switch (call.method) {
            case "setPinpadDisplayMessage":
                pinPadMethods.setDisplayMessage(call.argument("message"));
                break;
            case "pinpadReadYesNo":
                pinPadMethods.readYesOrNo(call.argument("message"));
                break;
            case "pinpadIsPresent":
                pinPadMethods.isPresent();
                break;
            case "configure":
                tefMethods.configure(
                        call.argument("enderecoSitef"),
                        call.argument("codigoLoja"),
                        call.argument("numeroTerminal"),
                        "[TipoPinPad=" + call.argument("tipoPinPad") + "];[ParmsClient=1=" + call.argument("cnpjLoja") + ";2=" + call.argument("cnpjAutomacao") + ";" + call.argument("parametrosAdicionais") + "]"
                );
                break;
            case "getQttPendingTransactions":
                tefMethods.getQttPendingTransactions(call.argument("dataFiscal"), call.argument("cupomFiscal"));
                break;
            case "startTransaction":
                tefMethods.startTransaction(
                        cliSiTefListener,
                        call.argument("modalidade"),
                        call.argument("valor"),
                        call.argument("cupomFiscal"),
                        call.argument("dataFiscal"),
                        call.argument("horario"),
                        call.argument("operador"),
                        call.argument("restricoes")
                );
                break;
            case "finishLastTransaction":
                tefMethods.finishLastTransaction(call.argument("confirma"));
                break;
            case "finishTransaction":
                tefMethods.finishTransaction(
                        call.argument("confirma"),
                        call.argument("cupomFiscal"),
                        call.argument("dataFiscal"),
                        call.argument("horaFiscal")
                );
                break;
            case "abortTransaction":
                tefMethods.abortTransaction(call.argument("continua"));
                break;
            case "continueTransaction":
                tefMethods.continueTransaction(call.argument("data"));
                break;
            default:
                result.notImplemented();
                break;
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPlugin.FlutterPluginBinding binding) {
        methodChannel.setMethodCallHandler(null);
        eventChannel.setStreamHandler(null);
        dataChannel.setStreamHandler(null);
    }

    @Override
    public void onAttachedToActivity(ActivityPluginBinding activityPluginBinding) {
        this.activity = activityPluginBinding.getActivity();
        if (cliSiTef != null) {
            cliSiTef.setActivity(activity);
        }
    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {
        if (cliSiTef != null) {
            cliSiTef.setActivity(null);
        }
    }

    @Override
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding activityPluginBinding) {
        this.activity = activityPluginBinding.getActivity();
        if (cliSiTef != null) {
            cliSiTef.setActivity(activity);
        }
    }

    @Override
    public void onDetachedFromActivity() {
        if (cliSiTef != null) {
            cliSiTef.setActivity(null);
        }
    }
}
