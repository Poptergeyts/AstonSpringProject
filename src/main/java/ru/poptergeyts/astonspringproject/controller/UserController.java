package ru.poptergeyts.astonspringproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.poptergeyts.astonspringproject.applicationService.UserService;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*
    Метод, отлавливающий get запрос по url: /signUp c параметром login и password
     */
    @GetMapping("/signUp")
    public ResponseEntity<String> signUp(
            @RequestParam("login") String login,
            @RequestParam("password") String password
    ) {
        return ResponseEntity.ok(userService.signUp(login, password));
    }

    /*
    Метод, отлавливающий get запрос по url: /signIn c параметром login и password
     */
    @GetMapping("/signIn")
    public ResponseEntity<String> signIn(
            @RequestParam("login") String login,
            @RequestParam("password") String password
    ) {
        return ResponseEntity.ok(userService.signIn(login, password));
    }

    /*
    Метод, отлавливающий get запрос по url: /changePassword c параметром login, oldPassword и newPassword
     */
    @GetMapping("/changePassword")
    public ResponseEntity<String> changePassword(
            @RequestParam("login") String login,
            @RequestParam("oldPassword") String oldPassword,
            @RequestParam("newPassword") String newPassword
    ) {
        return ResponseEntity.ok(userService.changePassword(login, oldPassword, newPassword));
    }

    /*
    Метод, отлавливающий get запрос по url: /changePassword без параметров
     */
    @GetMapping("/all")
    public ResponseEntity<String> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }
}
