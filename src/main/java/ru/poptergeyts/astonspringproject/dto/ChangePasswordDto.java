package ru.poptergeyts.astonspringproject.dto;

import lombok.Data;

@Data
public class ChangePasswordDto {
    private String login;
    private String oldPassword;
    private String newPassword;

    public UserDto getUserDto() {
        return new UserDto(login, oldPassword);
    }
}
