package ru.poptergeyts.astonspringproject.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("ru.poptergeyts.astonspringproject")
public class SpringConfig { }
