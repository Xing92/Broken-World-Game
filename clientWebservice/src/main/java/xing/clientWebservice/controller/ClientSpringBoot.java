package xing.clientWebservice.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;
import org.springframework.web.util.UriComponentsBuilder;

import xing.brokenworld_requests.request.CreateUserRequest;
import xing.brokenworld_requests.request.LoginUserRequest;
import xing.brokenworld_requests.response.GenericResponse;
import xing.brokenworld_requests.response.LoginUserResponse;
import xing.clientWebservice.model.User;

@Controller
@SpringBootApplication
public class ClientSpringBoot {
	public static void main(String[] args) {
		SpringApplication.run(ClientSpringBoot.class, args);
	}

	@RequestMapping(value = "/greetings", method = RequestMethod.GET)
	public String greetings(@RequestParam(value = "name", required = false, defaultValue = "Stranger") String name,
			Model model) {
		model.addAttribute("loggedUsername", name);

		return "greetings";
	}

	@RequestMapping(value = "/userdetails", method = RequestMethod.GET)
	public String getUserDetails(String login, String password, Model model) {
		model.addAttribute("login", login);
		model.addAttribute("password", password);

		User user = getUserDetails(login, password);
		if (!user.getKingdoms().isEmpty()) {
			model.addAttribute("kingdoms", user.getKingdoms());
		}

		return "userdetails";
	}

	public static User getUserDetails(String login, String password) {

		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> variables = new HashMap<String, String>();
		variables.put("login", login);
		variables.put("password", password);

		URI uri = UriComponentsBuilder.fromHttpUrl("HTTP://localhost:8080/userdetails").queryParam("login", login)
				.queryParam("password", password).build().encode().toUri();

		User user = restTemplate.getForObject(uri, User.class);

		return user;
	}
	
	public static User getUserDetails(String username) {

		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> variables = new HashMap<String, String>();
		variables.put("username", username);
		URI uri = UriComponentsBuilder.fromHttpUrl("HTTP://localhost:8080/userdetails").queryParam("username", username).build().encode().toUri();

		User user = restTemplate.getForObject(uri, User.class);

		return user;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getMainpage(Model model) {

		return "greetings";
	}

	@RequestMapping(value = "/testtable", method = RequestMethod.GET)
	public String getTesttable(Model model) {

		List<String> tests = new ArrayList<>();
		tests.add("A1");
		tests.add("B2");

		model.addAttribute("tests", tests);

		return "testtable";
	}

	@RequestMapping(value = "/usercreate", method = RequestMethod.POST)
	public String postUserCreate(String login, String password, String name, Model model) {
		RestTemplate restTemplate = new RestTemplate();

		CreateUserRequest createUserRequest = new CreateUserRequest();
		createUserRequest.setLogin(login);
		createUserRequest.setPassword(password);
		createUserRequest.setUsername(name);

		GenericResponse response = restTemplate.postForObject("HTTP://localhost:8080/usercreate", createUserRequest,
				GenericResponse.class);
		System.out.println("Response: " + response.isOk());
		return "greetings";
	}
	
	@RequestMapping(value = "/userlogin", method = RequestMethod.GET)
	public String getUserlogin(String login, String password, Model model) {
		RestTemplate restTemplate = new RestTemplate();

		LoginUserRequest loginUserRequest = new LoginUserRequest(); 
		
		loginUserRequest.setLogin(login);
		loginUserRequest.setPassword(password);
		
		LoginUserResponse loginUserResponse = restTemplate.postForObject("HTTP://localhost:8080/login", loginUserRequest,
				LoginUserResponse.class);
		
		System.out.println("Response: " + loginUserResponse.isOk());
		if(loginUserResponse.isOk()){
			model.addAttribute("loggedUsername", loginUserResponse.getUsername());
		}
		else{
			model.addAttribute("loggedUsername", "FAILED TO LOGIN!");
		}
		
		return "greetings";
	}
	
	@RequestMapping(value = "/user/{username}/userdetails", method = RequestMethod.GET)
	public String getUserDetails(@PathVariable("username") String username, Model model){

		User user = getUserDetails(username);
		if (!user.getKingdoms().isEmpty()) {
			model.addAttribute("kingdoms", user.getKingdoms());
		}

		return "userdetails";
	}
	

}
