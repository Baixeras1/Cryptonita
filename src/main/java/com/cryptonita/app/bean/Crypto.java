package com.cryptonita.app.bean;

public class Crypto {

    private String status;

    private DataCrypto data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataCrypto getData() {
        return data;
    }

    public void setData(DataCrypto data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Crypto{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}