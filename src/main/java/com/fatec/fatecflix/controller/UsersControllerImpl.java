package com.fatec.fatecflix.controller;

import com.fatec.fatecflix.controller.docs.UserController;
import com.fatec.fatecflix.dto.LoginDTO;
import com.fatec.fatecflix.dto.ProfileInformationDTO;
import com.fatec.fatecflix.dto.TokenDTO;
import com.fatec.fatecflix.entities.User;
import com.fatec.fatecflix.service.UsersService;
import com.fatec.fatecflix.utils.TokenUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/users")
public class UsersControllerImpl implements UserController {

    @Autowired
    private UsersService service;

    @Override
    @PostMapping("/signUp")
    public ResponseEntity<User> signUp(@RequestBody @Valid ProfileInformationDTO profileInformationDTO) {
        User responseBody = service.signUp(profileInformationDTO);

        return ResponseEntity.ok(responseBody);
    }

    @Override
    @PostMapping("/signIn")
    public ResponseEntity<TokenDTO> signIn(@RequestBody @Valid LoginDTO loginDTO) {
        return ResponseEntity.ok(service.signIn(loginDTO));
    }

    @Override
    @GetMapping("/me")
    public ResponseEntity<User> me(HttpServletRequest request) {
        String token = TokenUtils.wrapperToken(request);
        return ResponseEntity.ok(service.profileInformation(token));
    }
}
