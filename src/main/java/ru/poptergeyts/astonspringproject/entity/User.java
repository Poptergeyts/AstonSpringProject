package ru.poptergeyts.astonspringproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class User {
    @Id
    @Getter
    @Setter
    private String login;

    @Getter
    @Setter
    private String password;
}
