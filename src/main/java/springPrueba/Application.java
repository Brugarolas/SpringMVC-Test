package springPrueba;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
    	@SuppressWarnings("unused")
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
        
        System.out.println("¡Ready! Connect to https://localhost/.");
    }
}