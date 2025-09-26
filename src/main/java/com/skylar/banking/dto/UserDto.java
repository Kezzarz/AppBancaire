package com.skylar.banking.dto;

import com.skylar.banking.models.User;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder

public class UserDto {

    private Integer id;

    @NotNull(message = "Le prénom ne doit pas être nul.")
    @NotEmpty(message = "Le prénom ne doit pas être nul.")
    @NotBlank(message = "Le prénom ne doit pas être nul.")
    private String firstname;

    @NotNull(message = "Le nom ne doit pas être vide.")
    @NotEmpty(message = "Le nom ne doit pas être vide.")
    @NotBlank(message = "Le nom ne doit pas être vide.")
    private String lastname;

    @NotNull(message = "L'email ne doit pas être vide.")
    @NotEmpty(message = "L'email ne doit pas être vide.")
    @NotBlank(message = "L'email ne doit pas être vide.")
    @Email(message = "L'email n'est pas conforme")
    private String email;

    @NotNull(message = "Le mot de passe ne doit pas être vide.")
    @NotEmpty(message = "Le mot de passe ne doit pas être vide.")
    @NotBlank(message = "Le mot de passe ne doit pas être vide.")
    @Size(min = 8, max = 16, message = "Le mot de passe doit etre entre 8 et 16 caracteres")
    private String password;

    @Past
    private LocalDateTime birthdate;

    public static UserDto fromEntity(User user) {
     // null check
     return UserDto.builder()
             .id(user.getId())
             .firstname(user.getFirstname())
             .lastname(user.getLastname())
             .email(user.getEmail())
             .password(user.getPassword())
             .build();
    }

    public static User toEntity(UserDto user) {
        // null check
        return User.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

}
