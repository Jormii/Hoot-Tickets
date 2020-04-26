package dad.hoottickets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;


import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;

@EnableHazelcastHttpSession
@SpringBootApplication
public class HootTicketsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HootTicketsApplication.class, args);
	}

}
