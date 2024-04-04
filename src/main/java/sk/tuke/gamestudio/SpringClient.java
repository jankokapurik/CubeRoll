package sk.tuke.gamestudio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sk.tuke.gamestudio.game.CubeRoll.ConsoleUI.ConsoleUI;
import sk.tuke.gamestudio.service.*;

@SpringBootApplication
@Configuration
public class SpringClient {

    public static void main(String[] args) {
//        ConfigurableApplicationContext context = new SpringApplicationBuilder(SpringClient.class)
//                .headless(false) // Set headless mode to false or true as needed
//                .run(args);
//        SpringApplication.run(SpringClient.class, args);


        new SpringApplicationBuilder(SpringClient.class).web(WebApplicationType.NONE).run(args);

    }

    @Bean
    public CommandLineRunner runner(ConsoleUI ui) {
        return args -> ui.play();
    }

//    @Bean
//    public ScoreService scoreService() {
//        return new ScoreServiceJPA();
//    }

    @Bean
    public RatingService ratingService() {
        return new RatingServiceJPA();
    }

//    @Bean
//    public CommentService commentService() {
//        return new CommentServiceJPA();
//    }

    @Bean
    public ConsoleUI consoleUI() {
        return new ConsoleUI();
    }
}