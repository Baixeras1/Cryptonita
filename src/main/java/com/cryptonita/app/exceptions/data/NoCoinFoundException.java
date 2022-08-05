package com.cryptonita.app.exceptions.data;

import com.cryptonita.app.exceptions.LogicError;

public class NoCoinFoundException extends LogicError {

    public NoCoinFoundException() {
    }

    public NoCoinFoundException(String message) {
        super(message);
    }

    public NoCoinFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoCoinFoundException(Throwable cause) {
        super(cause);
    }

    public NoCoinFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
