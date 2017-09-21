package xing.clientWebservice;

import org.springframework.web.client.RestTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import xing.clientWebservice.model.User;

public class MyClient extends WebServiceGatewaySupport{

	public static void getResponse(){

		RestTemplate restTemplate = new RestTemplate();
		User user = restTemplate.getForEntity("HTTP://localhost:8080/user", User.class).getBody();
		System.out.println("User: " + user.getKingdoms().get(0).getUser().getKingdoms().get(0).getName());
	}
	
	public static void main(String[] args){
		getResponse();
	}
	
}
