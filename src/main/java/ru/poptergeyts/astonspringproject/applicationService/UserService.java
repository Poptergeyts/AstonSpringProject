package ru.poptergeyts.astonspringproject.applicationService;

import com.google.common.hash.Hashing;
import org.springframework.stereotype.Service;
import ru.poptergeyts.astonspringproject.domainService.ContainerService;
import ru.poptergeyts.astonspringproject.dto.ChangePasswordDto;
import ru.poptergeyts.astonspringproject.dto.UserDto;
import ru.poptergeyts.astonspringproject.exception.InvalidNewPasswordException;
import ru.poptergeyts.astonspringproject.exception.InvalidPasswordException;
import ru.poptergeyts.astonspringproject.exception.LoginAlreadyExistsException;
import ru.poptergeyts.astonspringproject.exception.LoginDoesNotExist;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class UserService {
    private final ContainerService containerService;

    public UserService(ContainerService containerService) {
        this.containerService = containerService;
    }

    /*
    Метод, регистрирующий пользователя, при этом пароль шифруется через sha256
     */
    public String signUp(UserDto userDto) throws LoginAlreadyExistsException{
        if (containerService.hasUser(userDto)) {
            throw new LoginAlreadyExistsException();
        }

        userDto.setPassword(Hashing.sha256()
                .hashString(userDto.getPassword(), StandardCharsets.UTF_8)
                .toString());

        containerService.addUser(userDto);
        return "SUCCESS: User has been signed up.";
    }

    /*
    Метод для входа пользователя
     */
    public String signIn(UserDto userDto) throws LoginDoesNotExist, InvalidPasswordException{
        if (!containerService.hasUser(userDto)) {
            throw new LoginDoesNotExist();
        }

        String passwordHash = Hashing.sha256()
                .hashString(userDto.getPassword(), StandardCharsets.UTF_8)
                .toString();

        if (passwordHash.equals(containerService.getUserPassword(userDto))) {
            return "SUCCESS: User has been signed in.";
        } else {
            throw new InvalidPasswordException();
        }
    }

    /*
    Метод для смены пароля пользователя
     */
    public String changePassword(ChangePasswordDto changePasswordDto)
            throws LoginDoesNotExist,
            InvalidPasswordException,
            InvalidNewPasswordException {
        UserDto userDto = changePasswordDto.getUserDto();
        if (!containerService.hasUser(userDto)) {
            throw new LoginDoesNotExist();
        }

        String oldPasswordHash = Hashing.sha256()
                .hashString(changePasswordDto.getOldPassword(), StandardCharsets.UTF_8)
                .toString();

        if (containerService.getUserPassword(userDto).equals(oldPasswordHash)) {
            if (changePasswordDto.getOldPassword().equals(changePasswordDto.getNewPassword())) {
                throw new InvalidNewPasswordException();
            } else {
                containerService.changePassword(userDto,
                        Hashing.sha256().hashString(changePasswordDto.getNewPassword(),
                                StandardCharsets.UTF_8).toString());
                return "SUCCESS: Password has been changed";
            }
        }
        throw new InvalidPasswordException();
    }

    /*
    Метод для вывода списка пользователей
     */
    public List<UserDto> getAll() {
        return containerService.getAll();
    }
}
