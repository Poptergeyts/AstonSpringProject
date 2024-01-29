package ru.poptergeyts.astonspringproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    // Логин
    private String login;
    // Текущий пароль
    private String password;
}
