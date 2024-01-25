package ru.poptergeyts.astonspringproject.applicationService;

import org.junit.jupiter.api.Test;
import ru.poptergeyts.astonspringproject.domainService.ContainerService;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Test
    void testSignUpMethod() {
        ContainerService containerService = new ContainerService();
        UserService userService = new UserService(containerService);
        assertEquals("SUCCESS: User has been signed up.",
                userService.signUp("login", "password"));
        assertEquals("ERROR: User with this login has already been signed up.",
                userService.signUp("login", "password"));
    }

    @Test
    void testSignInMethod() {
        ContainerService containerService = new ContainerService();
        UserService userService = new UserService(containerService);
        userService.signUp("login", "password");
        assertEquals("SUCCESS: User has been signed in.",
                userService.signIn("login", "password"));
        assertEquals("ERROR: User with this login is not registered.",
                userService.signIn("wrongLogin", "password"));
        assertEquals("ERROR: Invalid password.",
                userService.signIn("login", "wrongPassword"));
    }

    @Test
    void changePassword() {
        ContainerService containerService = new ContainerService();
        UserService userService = new UserService(containerService);
        userService.signUp("login", "oldPassword");
        assertEquals("SUCCESS: Password has been changed",
                userService.changePassword("login", "oldPassword", "newPassword"));
        assertEquals("ERROR: Old password and new password are the same.",
                userService.changePassword("login", "newPassword", "newPassword"));
        assertEquals("ERROR: Invalid old password.",
                userService.changePassword("login", "oldPassword", "newPassword"));
        assertEquals("ERROR: User with this login is not registered.",
                userService.changePassword("wrongLogin", "newPassword", "newPassword"));
    }

    @Test
    void getAll() {
        ContainerService containerService = new ContainerService();
        UserService userService = new UserService(containerService);
        userService.signUp("user1", "test");
        userService.signUp("user2", "test");
        userService.signUp("user3", "test");
        assertEquals("""
                User #1: user1
                User #2: user2
                User #3: user3
                """, userService.getAll());
    }
}