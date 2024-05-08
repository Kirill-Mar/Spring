package hiber;

import hiber.HibernateUtil.HibernateUtil;
import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Kirill", "Marchenko", "user1@mail.ru");
      Car car1 = new Car("Lexus", 1234);
      user1.setCar(car1);
      userService.add(user1);

      User user2 = new User("Anastasia", "Gritsenko", "user2@mail.ru");
      Car car2 = new Car("Honda", 5678);
      user2.setCar(car2);
      userService.add(user2);

      User user3 = new User("Alexander", "Hajdukov", "user3@mail.ru");
      Car car3 = new Car("BMW", 91011);
      user3.setCar(car3);
      userService.add(user3);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         if (user.getCar() != null) {
            System.out.println("Car Model = " + user.getCar().getModel());
            System.out.println("Car Series = " + user.getCar().getSeries());
         }
         System.out.println();
      }

      context.close();
      HibernateUtil.shutdown();
   }
}
