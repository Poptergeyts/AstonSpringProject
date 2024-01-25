package ru.poptergeyts.astonspringproject.domainService;

import org.junit.jupiter.api.Test;
import ru.poptergeyts.astonspringproject.dto.User;

import static org.junit.jupiter.api.Assertions.*;

class ContainerServiceTest {

    @Test
    void testGetUserMethod() {
        ContainerService containerService = new ContainerService();
        User user = new User("login", "password");
        containerService.addUser(user);
        assertEquals(user, containerService.getUser("login"));
    }

    @Test
    void testAddUserMethod() {
        ContainerService containerService = new ContainerService();
        containerService.addUser(new User("test_name", "test_password"));
        assertNull(containerService.getUser("login"));
    }

    @Test
    void testGetAllMethod() {
        ContainerService containerService = new ContainerService();
        containerService.addUser(new User("user1", "test"));
        containerService.addUser(new User("user2", "test"));
        containerService.addUser(new User("user3", "test"));
        assertEquals("""
                User #1: user1
                User #2: user2
                User #3: user3
                """, containerService.getAll());
    }
}