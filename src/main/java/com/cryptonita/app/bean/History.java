package com.cryptonita.app.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
@JsonIgnoreProperties(ignoreUnknown = true)
public class History implements Serializable {

    private HistoryData data;

    public History() {

    }

    public HistoryData getData() {
        return data;
    }

    @Override
    public String toString() {
        return "History{" +
                "data=" + data +
                '}';
    }
}
