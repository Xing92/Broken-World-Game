package xing.brokenworldserver.controller;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xing.brokenworldserver.model.Kingdom;
import xing.brokenworldserver.model.User;
import xing.brokenworldserver.utils.HibernateUtils;

@RestController
@SpringBootApplication
public class SimpleController {

	@RequestMapping("/user")
	public User getUsers() {
		List<User> users;
		try {
			users = HibernateUtils.getSession().createCriteria(User.class).list();//createQuery("from Move order by id ASC").list();
		} finally {
			HibernateUtils.getSession().close();
		}

		return users.get(0);
	}
	
	@RequestMapping("/kingdom")
	public Kingdom getKingdom() {
		List<Kingdom> kingdoms;
		try {
			kingdoms = HibernateUtils.getSession().createCriteria(Kingdom.class).list();//createQuery("from Move order by id ASC").list();
		} finally {
			HibernateUtils.getSession().close();
		}

		return kingdoms.get(0);
	}

//	public static void main(String[] args) {
//		SpringApplication.run(SimpleController.class, args);
//	}

}
