package com.fatec.fatecflix.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fatec.fatecflix.dto.LoginDTO;
import com.fatec.fatecflix.dto.ProfileInformationDTO;
import com.fatec.fatecflix.dto.TokenDTO;
import com.fatec.fatecflix.entities.User;
import com.fatec.fatecflix.repository.UsersRepository;
import com.fatec.fatecflix.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ObjectMapper objectMapper;

    public User signUp(ProfileInformationDTO profileInformationDTO) {
        String pwd = profileInformationDTO.getPassword();
        profileInformationDTO.setPassword(new BCryptPasswordEncoder().encode(pwd));

        User user = objectMapper.convertValue(profileInformationDTO, User.class);

        return usersRepository.save(user);
    }

    public TokenDTO signIn(LoginDTO loginDTO) {

        UsernamePasswordAuthenticationToken loginData = loginDTO.convert();
        Authentication auth = authenticationManager.authenticate(loginData);
        String token = tokenService.getToken(auth);

        return new TokenDTO(token, "Bearer");
    }

    public User profileInformation(String token) {
        Long userId = tokenService.getUserId(token);

        return usersRepository.getReferenceById(userId);
    }
}
