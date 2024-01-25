package ru.poptergeyts.astonspringproject.applicationService;

import com.google.common.hash.Hashing;
import org.springframework.stereotype.Service;
import ru.poptergeyts.astonspringproject.domainService.ContainerService;
import ru.poptergeyts.astonspringproject.dto.User;

import java.nio.charset.StandardCharsets;

@Service
public class UserService {
    private final ContainerService containerService;

    public UserService(ContainerService containerService) {
        this.containerService = containerService;
    }

    /*
    Метод, регистрирующий пользователя, при этом пароль шифруется через sha256
     */
    public String signUp(String login, String password) {
        if (containerService.getUser(login) != null) {
            return "ERROR: User with this login has already been signed up.";
        }

        String passwordHash = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();

        containerService.addUser(new User(login, passwordHash));
        return "SUCCESS: User has been signed up.";
    }

    /*
    Метод для входа пользователя
     */
    public String signIn(String login, String password) {
        User user = containerService.getUser(login);
        if (user == null) {
            return "ERROR: User with this login is not registered.";
        }

        String passwordHash = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();

        if (user.getPassword().equals(passwordHash)) {
            return "SUCCESS: User has been signed in.";
        } else {
            return "ERROR: Invalid password.";
        }
    }

    /*
    Метод для смены пароля пользователя
     */
    public String changePassword(String login, String oldPassword, String newPassword) {
        User user = containerService.getUser(login);
        if (user == null) {
            return "ERROR: User with this login is not registered.";
        }

        String oldPasswordHash = Hashing.sha256()
                .hashString(oldPassword, StandardCharsets.UTF_8)
                .toString();

        if (user.getPassword().equals(oldPasswordHash)) {
            if (oldPassword.equals(newPassword)) {
                return "ERROR: Old password and new password are the same.";
            } else {
                user.setPassword(Hashing.sha256().hashString(newPassword, StandardCharsets.UTF_8).toString());
                return "SUCCESS: Password has been changed";
            }
        }
        return "ERROR: Invalid old password.";
    }

    /*
    Метод для вывода списка пользователей
     */
    public String getAll() {
        return containerService.getAll();
    }
}
