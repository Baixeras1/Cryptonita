package com.cryptonita.app.data.providers.impl;

import com.cryptonita.app.data.daos.IBannedUserDao;
import com.cryptonita.app.data.daos.ICoinDAO;
import com.cryptonita.app.data.daos.IFavoritesDao;
import com.cryptonita.app.data.daos.IUserDao;
import com.cryptonita.app.data.entities.BannedUsersModel;
import com.cryptonita.app.data.entities.CoinModel;
import com.cryptonita.app.data.entities.FavouritesModel;
import com.cryptonita.app.data.entities.UserModel;
import com.cryptonita.app.data.providers.IUserProvider;
import com.cryptonita.app.data.providers.mappers.IMapper;
import com.cryptonita.app.dto.request.UserRegisterDTO;
import com.cryptonita.app.dto.response.BannedUserResponseDTO;
import com.cryptonita.app.dto.response.FavoritesResponseDto;
import com.cryptonita.app.dto.response.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class UserProviderImpl implements IUserProvider {

    private final IUserDao userDao;
    private final IBannedUserDao bannedUserDao;
    private final IFavoritesDao favoritesDao;
    private final ICoinDAO coinDAO;

    private final IMapper<UserModel, UserRegisterDTO> registerDTOIMapper;
    private final IMapper<UserModel, UserResponseDTO> responseDTOIMapper;
    private final IMapper<BannedUsersModel, BannedUserResponseDTO> banResponseDTOIMapper;
    private final IMapper<FavouritesModel, FavoritesResponseDto> favoritesResponseDtoIMapper;

    private final PasswordEncoder encoder;

    @Override
    public UserResponseDTO register(UserRegisterDTO dto) {
        if (userDao.findByMail(dto.mail).isPresent())
            throw new RuntimeException("user by that mail exists!"); // TODO

        if (userDao.findByUsername(dto.username).isPresent())
            throw new RuntimeException("user by that username exists!"); // TODO

        UserModel user = registerDTOIMapper.mapToEntity(dto);
        user.setPassword(encoder.encode(user.getPassword()));

        user = userDao.save(user);

        return responseDTOIMapper.mapToDto(user);
    }

    @Override
    public boolean matchesPassword(String mail, String password) {
        return innerMatchPassword(userDao.findByMail(mail), password);
    }

    @Override
    public boolean matchesPasswordByUsername(String username, String password) {
        return innerMatchPassword(userDao.findByUsername(username), password);
    }

    /**
     * Inner method to check password
     */
    private boolean innerMatchPassword(Optional<UserModel> user, String rawPassword) {
        return user.isPresent() && encoder.matches(rawPassword, user.get().getPassword());
    }

    @Override
    public boolean exists(String mail) {
        return userDao.findByMail(mail).isPresent();
    }

    @Override
    public boolean existsByUsername(String username) {
        return userDao.findByUsername(username).isPresent();
    }

    @Override
    public BannedUserResponseDTO banUser(String mail) {
        return innerBanUser(userDao.findByMail(mail).orElse(null));
    }

    @Override
    public BannedUserResponseDTO unBanUser(String mail) {
        return innerUnbanUser(bannedUserDao.findByUserMail(mail).orElse(null));
    }

    @Override
    public BannedUserResponseDTO banUserByUsername(String username) {
        return innerBanUser(userDao.findByUsername(username).orElse(null));
    }

    @Override
    public BannedUserResponseDTO unbanUserByUsername(String username) {
        return innerUnbanUser(bannedUserDao.findByUser_Username(username).orElse(null));
    }

    /**
     * Inner ban user method
     */
    private BannedUserResponseDTO innerBanUser(UserModel user) {
        if (user == null)
            throw new RuntimeException("No user with that mail!"); // TODO

        BannedUsersModel bannedUser = BannedUsersModel.builder()
                .user(user)
                .bannedAt(LocalDate.now())
                .expiresAt(LocalDate.now().plusMonths(1))
                .build();

        bannedUser = bannedUserDao.save(bannedUser);

        return banResponseDTOIMapper.mapToDto(bannedUser);
    }

    /**
     * Inner unban user method
     */
    private BannedUserResponseDTO innerUnbanUser(BannedUsersModel bannedUser) {
        if (bannedUser == null)
            throw new RuntimeException("No user with that mail!"); // TODO

        bannedUserDao.delete(bannedUser);

        return banResponseDTOIMapper.mapToDto(bannedUser);
    }

    @Override
    public List<BannedUserResponseDTO> getBannedUsers() {
        return bannedUserDao.findAll().stream()
                .map(banResponseDTOIMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isBanned(String mail) {
        return bannedUserDao.findByUserMail(mail).isPresent();
    }

    @Override
    public boolean isBannedByUsername(String username) {
        return bannedUserDao.findByUser_Username(username).isPresent();
    }

    @Override
    public BannedUserResponseDTO get(String mail) {
        return bannedUserDao.findByUserMail(mail)
                .map(banResponseDTOIMapper::mapToDto)
                .orElse(null); // TODO
    }

    @Override
    public BannedUserResponseDTO getByUsername(String username) {
        return bannedUserDao.findByUser_Username(username)
                .map(banResponseDTOIMapper::mapToDto)
                .orElse(null); // TODO
    }

    @Override
    public FavoritesResponseDto addFavourite(String name, String coinStr) {
        UserModel user = userDao.findByUsername(name).orElse(null);
        if (user == null)
            throw new RuntimeException("That user does not exists!"); //TODO

        CoinModel coin = coinDAO.findByName(coinStr).orElse(null);
        if (coin == null)
            throw new RuntimeException("That coin does not exists!"); //TODO

        if (favoritesDao.findByUser_UsernameAndCoinName(name, coinStr).isPresent())
            throw new RuntimeException("Coin already exists!"); //TODO

        FavouritesModel favourite = FavouritesModel.builder()
                .user(user)
                .coin(coin)
                .build();

       favourite = favoritesDao.save(favourite);

       return favoritesResponseDtoIMapper.mapToDto(favourite);
    }

    @Override
    public FavoritesResponseDto removeFavorite(String name, String coinStr) {
        UserModel user = userDao.findByUsername(name).orElse(null);
        if (user == null)
            throw new RuntimeException("That user does not exists!"); //TODO

        CoinModel coin = coinDAO.findByName(coinStr).orElse(null);
        if (coin == null)
            throw new RuntimeException("That coin does not exists!"); //TODO

        FavouritesModel favourite = favoritesDao.findByUser_UsernameAndCoinName(name, coinStr).orElse(null);
        if (favourite == null)
            throw new RuntimeException("Coin already exists!"); //TODO

        favoritesDao.delete(favourite);

        return favoritesResponseDtoIMapper.mapToDto(favourite);
    }



}
