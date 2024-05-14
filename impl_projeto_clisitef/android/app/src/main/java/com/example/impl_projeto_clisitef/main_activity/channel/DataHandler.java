package com.example.impl_projeto_clisitef.main_activity.channel;

import com.example.impl_projeto_clisitef.main_activity.CliSiTefListener;

import io.flutter.plugin.common.EventChannel;

public class DataHandler implements EventChannel.StreamHandler{
    private static EventChannel.EventSink eventSink;
    private static CliSiTefListener listener;

    public static DataHandler setListener(CliSiTefListener l) {
        listener = l;
        return new DataHandler();
    }

    @Override
    public void onListen(Object arguments, EventChannel.EventSink events) {
        eventSink = events;
        if (listener != null) {
            listener.setDataSink(eventSink);
        }
    }

    @Override
    public void onCancel(Object arguments) {
        eventSink = null;
        if (listener != null) {
            listener.setDataSink(null);
        }
    }
}
