package ru.poptergeyts.astonspringproject.domainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.poptergeyts.astonspringproject.dto.UserDto;
import ru.poptergeyts.astonspringproject.entity.User;
import ru.poptergeyts.astonspringproject.exception.LoginAlreadyExistsException;
import ru.poptergeyts.astonspringproject.exception.LoginDoesNotExist;
import ru.poptergeyts.astonspringproject.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserContainerService {
    private final UserRepository userRepository;

    @Autowired
    public UserContainerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
    Метод для проверки находится ли пользователь в контейнере
     */
    public boolean hasUser(UserDto userDto) {
        return userRepository.getByLogin(userDto.getLogin()) != null;
    }

    public String getUserPassword(UserDto userDto) throws LoginDoesNotExist{
        User user = userRepository.getByLogin(userDto.getLogin());

        if (user == null) {
            throw new LoginDoesNotExist();
        }

        return user.getPassword();
    }

    public void setUserPassword(UserDto userDto, String newPassword) throws LoginDoesNotExist{
        if (userRepository.getByLogin(userDto.getLogin()) == null) {
            throw new LoginDoesNotExist();
        }

        userRepository.updatePassword(userDto.getLogin(),newPassword);
    }

    /*
    Метод для добавления пользователя в контейнер
     */
    public void addUser(UserDto userDto) throws LoginAlreadyExistsException{
        if (userRepository.getByLogin(userDto.getLogin()) != null) {
            throw new LoginAlreadyExistsException();
        }

        User user = new User();
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        userRepository.addUser(user);
    }

    /*
    Метод для получения всех пользователей из контейнера
     */
    public List<UserDto> getAll() {
        return mapListOfUsers(userRepository.getAll());
    }

    private List<UserDto> mapListOfUsers(List<User> users) {
        List<UserDto> usersDto = new ArrayList<>(users.size());
        for (User user : users) {
            usersDto.add(new UserDto(user.getLogin(), user.getPassword()));
        }
        return usersDto;
    }
}
