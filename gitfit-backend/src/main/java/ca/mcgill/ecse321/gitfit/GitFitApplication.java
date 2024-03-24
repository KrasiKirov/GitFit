package ca.mcgill.ecse321.gitfit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.gitfit.dao.SportCenterRepository;
import ca.mcgill.ecse321.gitfit.model.SportCenter;

import java.sql.Time;
import java.util.Iterator;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SpringBootApplication
public class GitFitApplication {

	@Autowired
	private SportCenterRepository sportCenterRepository;

	private SportCenter sportCenter;

	public static void main(String[] args) {
		SpringApplication.run(GitFitApplication.class, args);
	}

	@RequestMapping("/")
	public String greeting() {
		Iterator<SportCenter> sportCenterIterator = sportCenterRepository.findAll().iterator();
		if (sportCenterIterator.hasNext()) {
			sportCenter = sportCenterIterator.next(); // Returns only sport center
		} else {// If no sport center exists, make both owner and sport center
			sportCenter = new SportCenter("GitFit", 30, Time.valueOf("09:00:00"),
					Time.valueOf("22:00:00"), "admin", "admin@gitfit.com", "uwu", "Joe", "Biden");
			sportCenter = sportCenterRepository.save(sportCenter);
		}
		return "Hello world!";
	}
}