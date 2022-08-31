package com.fatec.fatecflix.controller.docs;

import com.fatec.fatecflix.dto.ErrorDTO;
import com.fatec.fatecflix.dto.LoginDTO;
import com.fatec.fatecflix.dto.ProfileInformationDTO;
import com.fatec.fatecflix.dto.TokenDTO;
import com.fatec.fatecflix.entities.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Api(value = "/api/v1/users",  description = "Operações relacionadas aos Usuarios")
public interface UserController {

    @ApiOperation(value = "Cadastrar um usuario", nickname = "signUp", notes = "", response = User.class, responseContainer = "object", tags = { "Users", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuario Cadastrado no sistema", response = User.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object")})
    @PostMapping("/signUp")
    public ResponseEntity<User> signUp(@RequestBody @Valid ProfileInformationDTO profileInformationDTO);

    @ApiOperation(value = "Logar no sistema", nickname = "signIn", notes = "", response = TokenDTO.class, responseContainer = "object", tags = { "Users", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuario Logado com sucesso!", response = TokenDTO.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 404, message = "Usuário não encontrada") })
    @PostMapping("/signIn")
    public ResponseEntity<TokenDTO> signIn(@RequestBody @Valid LoginDTO loginDTO);

    @ApiOperation(value = "Dados do usuario logado", nickname = "me", notes = "", response = User.class, responseContainer = "object", authorizations = {
            @Authorization(value = "Authorization") }, tags = { "Users", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Dados do usuario encontrado com sucesso!", response = User.class, responseContainer = "object"),
            @ApiResponse(code = 400, message = "Dados informados para a requisição estão inconsistentes", response = ErrorDTO.class, responseContainer = "object"),
            @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Usuário não encontrados") })
    @GetMapping("/me")
    public ResponseEntity<User> me(HttpServletRequest request);
}
