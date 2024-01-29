package ru.poptergeyts.astonspringproject.domainService;

import org.springframework.stereotype.Repository;
import ru.poptergeyts.astonspringproject.dto.UserDto;

import java.util.LinkedList;
import java.util.List;

@Repository
public class  ContainerService {
    private final List<UserDto> usersDto = new LinkedList<>();

    /*
    Метод для проверки находится ли пользователь в контейнере
     */
    public boolean hasUser(UserDto userDto) {
        for (UserDto currentUserDto : usersDto) {
            if (currentUserDto.getLogin().equals(userDto.getLogin())) {
                return true;
            }
        }
        return false;
    }

    public String getUserPassword(UserDto userDto) {
        for (UserDto currentUserDto : usersDto) {
            if (currentUserDto.getLogin().equals(userDto.getLogin())) {
                return currentUserDto.getPassword();
            }
        }
        return null;
    }

    public void changePassword(UserDto userDto, String newPassword) {
        for (UserDto currentUserDto : usersDto) {
            if (currentUserDto.getLogin().equals(userDto.getLogin())) {
                currentUserDto.setPassword(newPassword);
            }
        }
    }

    /*
    Метод для добавления пользователя в контейнер
     */
    public void addUser(UserDto userDto) {
        usersDto.add(userDto);
    }

    /*
    Метод для получения логины всех пользователей из контейнера
     */
    public List<UserDto> getAll() {
        return usersDto;
    }
}
