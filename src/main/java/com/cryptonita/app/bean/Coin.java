package com.cryptonita.app.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Coin implements Serializable {

    private CoinData data;

    private Date timestamp;

    public CoinData getData() {
        return data;
    }

    public void setData(CoinData data) {
        this.data = data;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Coin() {
    }

    public Coin(CoinData data, Date timestamp) {
        this.data = data;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Coin{" +
                "data=" + data +
                ", timestamp=" + timestamp +
                '}';
    }
}
