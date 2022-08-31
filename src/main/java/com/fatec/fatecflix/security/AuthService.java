package com.fatec.fatecflix.security;

import com.fatec.fatecflix.entities.User;
import com.fatec.fatecflix.repository.UsersRepository;
import com.fatec.fatecflix.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UsersService service;

    @Autowired
    private UsersRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmail(username);

        if (user == null) {
            user = repository.findByUsername(username);
        }

        if (!validUser(user)) {
            throw new UsernameNotFoundException("Usuario sem permissão");
        }

        return user;
    }

    private boolean validUser(User user) {
        boolean validUser = false;

        if (user != null && user.getRoles() != null) {
            validUser = true;
        }

        return validUser;
    }
}
