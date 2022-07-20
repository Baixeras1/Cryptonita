package com.cryptonita.app.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class Coins implements Serializable {

    private CoinData[] data;

    private Date timestamp;

    public CoinData[] getData() {
        return data;
    }

    public void setData(CoinData[] data) {
        this.data = data;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Coins() {
    }

    public Coins(CoinData[] data, Date timestamp) {
        this.data = data;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Coins{" +
                "data=" + Arrays.toString(data) +
                ", timestamp=" + timestamp +
                '}';
    }
}
