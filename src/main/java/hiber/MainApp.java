package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Car1", 100500)));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Car2", 100501)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Car3", 100502)));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Car4", 100502)));

        List<User> users = userService.listUsers();
        printEntity(users);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter model: ");
        String model = scanner.nextLine();
        System.out.println("Enter series: ");
        int series = Integer.parseInt(scanner.nextLine());

        List<User> carUsers = userService.getUsersByCar(model, series);
        printEntity(carUsers);
        
        context.close();
    }

    static void printEntity(List<User> users) {
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car_id = " + user.getCar().getId());
            System.out.println("Car_model = " + user.getCar().getModel());
            System.out.println("Car_series = " + user.getCar().getSeries());
            System.out.println();
        }
    }
}
