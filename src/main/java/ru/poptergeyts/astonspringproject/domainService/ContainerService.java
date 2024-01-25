package ru.poptergeyts.astonspringproject.domainService;

import org.springframework.stereotype.Repository;
import ru.poptergeyts.astonspringproject.dto.User;

import java.util.LinkedList;
import java.util.List;

@Repository
public class  ContainerService {
    private final List<User> users = new LinkedList<>();

    /*
    Метод для получения пользователя из контейнера по логину
     */
    public User getUser(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    /*
    Метод для пдобавления пользователя в контейнер
     */
    public void addUser(User user) {
        users.add(user);
    }

    /*
    Метод для получения логины всех пользователей из контейнера
     */
    public String getAll() {
        StringBuilder result = new StringBuilder();
        int num = 1;
        for (User user : users) {
            result.append("User #" + num + ": " + user.getLogin() +"\n");
            num++;
        }
        return result.isEmpty() ? "There are no registered users." : result.toString();
    }
}
