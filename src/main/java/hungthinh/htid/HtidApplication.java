package hungthinh.htid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@SpringBootApplication
public class HtidApplication {

	public static void main(String[] args) {
		SpringApplication.run(HtidApplication.class, args);
	}



}
