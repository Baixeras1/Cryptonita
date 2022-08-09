package com.cryptonita.app.data.providers;

import com.cryptonita.app.dto.data.response.WallerResponseDto;

import java.util.List;

public interface IAccountProvider {

    /**
     * Convenient method to get a specific account of a user
     *
     * @param user the username of the user involved in the transaction
     * @param coin the coin name to get the account from
     * @return the dto with all the information of the account
     */
    WallerResponseDto get(String user,String coin);

    /**
     *
     * Convenient method of creating a user-specific account
     *
     * @param user the username of the user involved in the transaction
     * @param coin the coin name to get the account from
     * @return the dto with all the information of the account
     */
    WallerResponseDto create(String user,String coin);

    /**
     *
     * Convenient method for entering currencies to a specific user account
     *
     * @param user the username of the user involved in the transaction
     * @param coin the coin name to get the account from
     * @param ammount the amount of the user's coins
     * @return the dto with all the information of the account
     */
    WallerResponseDto deposit(String user, String coin, double amount);

    /**
     *
     * Convenient method for withdraw money from a certain user and a certain coin
     *
     * @param user the username of the user involved in the transaction
     * @param coin the coin name to get the account from
     * @param ammount the amount of the user's coins
     * @return the dto with all the information of the account
     */
    WallerResponseDto withDraw(String user, String coin, double amount);

    /**
     * Convenient method for clear
     *
     * @param user the username of the user involved in the transaction
     * @param coin the coin name to get the account from
     * @return the dto with all the information of the account
     */
    WallerResponseDto clear(String user, String coin);

    /**
     *
     * Convenient method to get all account of a user
     *
     * @param user the username of the user involved in the transaction
     * @return the dto with all the information of the account
     */
    List<WallerResponseDto> getAllFromUser(String user);

}
