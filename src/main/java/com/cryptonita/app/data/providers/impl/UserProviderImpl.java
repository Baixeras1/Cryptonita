package com.cryptonita.app.data.providers.impl;

import com.cryptonita.app.data.daos.IBannedUserDao;
import com.cryptonita.app.data.daos.IUserDao;
import com.cryptonita.app.data.entities.BannedUsersModel;
import com.cryptonita.app.data.entities.UserModel;
import com.cryptonita.app.data.providers.IUserProvider;
import com.cryptonita.app.data.providers.mappers.IMapper;
import com.cryptonita.app.dto.request.UserRegisterDTO;
import com.cryptonita.app.dto.response.BannedUserResponseDTO;
import com.cryptonita.app.dto.response.FavoritesResponseDto;
import com.cryptonita.app.dto.response.UserResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserProviderImpl implements IUserProvider {

    private final IUserDao userDao;
    private final IBannedUserDao bannedUserDao;

    private final IMapper<UserModel, UserRegisterDTO> registerDTOIMapper;
    private final IMapper<UserModel, UserResponseDTO> responseDTOIMapper;
    private final IMapper<BannedUsersModel, BannedUserResponseDTO> banResponseDTOIMapper;

    private final PasswordEncoder encoder;

    @Override
    public UserResponseDTO register(UserRegisterDTO dto) {
        if (userDao.findByMail(dto.mail).isPresent())
            throw new RuntimeException("user by that mail exists!"); // TODO

        if (userDao.findByUsername(dto.username).isPresent())
            throw new RuntimeException("user by that username exists!"); // TODO

        UserModel user = registerDTOIMapper.mapToEntity(dto);
        user = userDao.save(user);

        return responseDTOIMapper.mapToDto(user);
    }

    @Override
    public boolean matchesPassword(String mail, String password) {
        UserModel user = userDao.findByMail(mail).orElse(null);
        if (user == null)
            throw new RuntimeException("No user with that mail!"); // TODO

        return encoder.matches(password, user.getPassword());
    }

    @Override
    public boolean matchesPasswordByUsername(String username, String password) {
        UserModel user = userDao.findByUsername(username).orElse(null);
        if (user == null)
            throw new RuntimeException("No user with that mail!"); // TODO

        return encoder.matches(password, user.getPassword());
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
        UserModel user = userDao.findByMail(mail).orElse(null);
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

    @Override
    public BannedUserResponseDTO unBanUser(String mail) {
        BannedUsersModel bannedUser = bannedUserDao.findByUserMail(mail).orElse(null);
        if (bannedUser == null)
            throw new RuntimeException("No user with that mail!"); // TODO

        bannedUserDao.delete(bannedUser);

        return banResponseDTOIMapper.mapToDto(bannedUser);
    }

    @Override
    public BannedUserResponseDTO banUserByUsername(String username) {
        BannedUsersModel bannedUser = bannedUserDao.findByUser_Username(username).orElse(null);
        if (bannedUser == null)
            throw new RuntimeException("No user with that mail!"); // TODO

        bannedUserDao.delete(bannedUser);

        return banResponseDTOIMapper.mapToDto(bannedUser);
    }

    @Override
    public BannedUserResponseDTO unbanUserByUsername(String username) {
        BannedUsersModel bannedUser = bannedUserDao.findByUser_Username(username).orElse(null);
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
    public FavoritesResponseDto addFavourite(String name, String coin) {
        return null;
    }

    @Override
    public FavoritesResponseDto removeFavorite(String name, String coin) {
        return null;
    }
}
