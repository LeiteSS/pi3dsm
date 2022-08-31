package com.fatec.fatecflix.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileInformationDTO {

    @NotNull
    @ApiModelProperty(value = "Username")
    private String username;

    @Size(min = 8, message = "A senha deve ter no minimo 8 caracteres.")
    @NotNull
    @ApiModelProperty(value = "Senha")
    private String password;

    @Email(message = "Insira um email valido")
    @Pattern(regexp=".+@.+\\..+", message="Insira um email valido")
    @NotNull
    @ApiModelProperty(value = "Email")
    private String email;

    @NotNull
    @ApiModelProperty(value = "Nome")
    private String name;

    @NotNull
    @ApiModelProperty(value = "Sobrenome")
    private String lastname;

    @CPF
    @NotNull
    @ApiModelProperty(value = "CPF")
    private String cpf;

    @ApiModelProperty(value = "Papeis ou funções: USERS ou MANAGERS")
    private List<String> roles;
}
