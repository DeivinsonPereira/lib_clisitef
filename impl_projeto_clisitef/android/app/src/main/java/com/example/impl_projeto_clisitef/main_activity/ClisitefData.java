package com.example.impl_projeto_clisitef.main_activity;

import java.util.HashMap;
import java.util.Map;

public class ClisitefData {

    private DataEvents event;
    private int currentStage;
    private String buffer;
    private boolean shouldContinue;
    private int fieldId;
    private int maxLength;
    private int minLength;

    public ClisitefData(DataEvents event, int currentStage, String buffer, boolean shouldContinue, int fieldId, int maxLength, int minLength) {
        this.event = event;
        this.currentStage = currentStage;
        this.buffer = buffer;
        this.shouldContinue = shouldContinue;
        this.fieldId = fieldId;
        this.maxLength = maxLength;
        this.minLength = minLength;
    }

    public ClisitefData(DataEvents event, int currentStage, String buffer, boolean shouldContinue, int maxLength, int minLength) {
        this(event, currentStage, buffer, shouldContinue, 0, maxLength, minLength);

    }

    public ClisitefData(DataEvents event, int currentStage, String buffer, boolean shouldContinue, int fieldId) {
        this(event, currentStage, buffer, shouldContinue, fieldId, 0, 0);
    }

    public ClisitefData(DataEvents event, int currentStage, String buffer) {
        this(event, currentStage, buffer, true, 0, 0, 0);
    }
    public ClisitefData(DataEvents event, int currentStage, String buffer, boolean shouldContinue) {
        this(event, currentStage, buffer, shouldContinue, 0, 0, 0);
    }

    public Map<String, Object> toDataSink() {
        Map<String, Object> dataSink = new HashMap<>();
        dataSink.put("event", this.event.getNamed());
        dataSink.put("currentStage", this.currentStage);
        dataSink.put("buffer", this.buffer);
        dataSink.put("shouldContinue", this.shouldContinue);
        dataSink.put("fieldId", this.fieldId);
        dataSink.put("maxLength", this.maxLength);
        dataSink.put("minLength", this.minLength);
        return dataSink;
    }

    public DataEvents getEvent() {
        return event;
    }

    public void setEvent(DataEvents event) {
        this.event = event;
    }

    public int getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(int currentStage) {
        this.currentStage = currentStage;
    }

    public String getBuffer() {
        return buffer;
    }

    public void setBuffer(String buffer) {
        this.buffer = buffer;
    }

    public boolean isShouldContinue() {
        return shouldContinue;
    }

    public void setShouldContinue(boolean shouldContinue) {
        this.shouldContinue = shouldContinue;
    }

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

}
