package com.cryptonita.app.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoryData implements Serializable {

    private double priceUsd;
    private Date time;

    public HistoryData() {

    }

    public double getPriceUsd() {
        return priceUsd;
    }

    public Date getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "HistoryData{" +
                "priceUsd=" + priceUsd +
                ", time=" + time +
                '}';
    }
}
