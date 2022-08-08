package com.cryptonita.app.exceptions.data;

import com.cryptonita.app.exceptions.LogicError;

public class StakingNotFoundException extends LogicError {

    public StakingNotFoundException() {
    }

    public StakingNotFoundException(String message) {
        super(message);
    }

    public StakingNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StakingNotFoundException(Throwable cause) {
        super(cause);
    }

    public StakingNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
