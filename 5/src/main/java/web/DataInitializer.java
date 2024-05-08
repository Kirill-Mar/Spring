package web;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import web.config.AppConfig;
import web.model.User;
import web.service.UserService;

public class DataInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);

        User user1 = new User("Имя1", "Фамилия1", "email1@mail.com", "пароль1");
        userService.createOrUpdateUser(user1);

        User user2 = new User("Имя2", "Фамилия2", "email2@mail.com", "пароль2");
        userService.createOrUpdateUser(user2);

        context.close();
    }
}

