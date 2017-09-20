package xing.clientWebservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@SpringBootApplication
public class ClientSpringBoot {
	public static void main(String[] args) {
		SpringApplication.run(ClientSpringBoot.class, args);
	}

	@RequestMapping(value = "/greetings", method = RequestMethod.GET)
	public String greetings(@RequestParam(value = "name", required = false, defaultValue = "Stranger") String name,
			Model model) {
		model.addAttribute("name", name);

		return "greetings";
	}

	// @RequestMapping(value = "/userdetails", method = RequestMethod.GET)
	// public String getUserdetails(
	// @RequestParam(value = "username", required = false, defaultValue =
	// "Stranger") String username,
	// String password, Model model) {
	// model.addAttribute("username", username);
	// model.addAttribute("password", password);
	// System.out.println("GET");
	// System.out.println(username);
	// System.out.println(password);
	//
	// return "userdetails";
	// }

	@RequestMapping(value = "/userdetails", method = RequestMethod.POST)
	public String postUserdetails(String username, String password, Model model) {
		model.addAttribute("username", username);
		model.addAttribute("password", password);
		System.out.println("POST");
		System.out.println(username);
		System.out.println(password);

		User user = getUserDetails(username, password);
		System.out.println("XING: " + user.getKingdoms().get(0).getName());
		model.addAttribute("kingdom", user.getKingdoms().get(0).getName());
		model.addAttribute("kingdoms", user.getKingdoms());
		
		return "userdetails";
	}

	public static User getUserDetails(String username, String password) {

		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> variables = new HashMap<String, String>();
		variables.put("login", username);
		variables.put("password", password);
		System.out.println("var: " + variables.get("login"));
//		User user = restTemplate.getForObject("HTTP://localhost:8080/userdetails", User.class, username, password);
		User user = restTemplate.getForObject("HTTP://localhost:8080/userdetails", User.class, variables);
//		User user = restTemplate.getForEntity("HTTP://localhost:8080/userdetails", User.class).getBody();
		System.out.println("User: " + user.getName());
		
		return user;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getMainpage(Model model) {

		return "bootstrap/mainpage";
	}
	
	@RequestMapping(value = "/testtable", method = RequestMethod.GET)
	public String getTesttable(Model model) {
		
		List<String> tests = new ArrayList<>();
		tests.add("A1");
		tests.add("B2");
		
		model.addAttribute("tests", tests);

		return "testtable";
	}
	
}
