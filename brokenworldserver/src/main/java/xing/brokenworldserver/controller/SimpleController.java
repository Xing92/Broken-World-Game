package xing.brokenworldserver.controller;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xing.brokenworldserver.dao.SimpleDao;
import xing.brokenworldserver.model.Kingdom;
import xing.brokenworldserver.model.User;
import xing.brokenworldserver.utils.HibernateUtils;

@RestController
@SpringBootApplication
public class SimpleController {

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public User getUsers() {
		List<User> users;
		try {
			users = HibernateUtils.getSession().createCriteria(User.class).list();
		} finally {
			HibernateUtils.getSession().close();
		}

		return users.get(0);
	}

	@RequestMapping(value = "/kingdom", method = RequestMethod.GET)
	public Kingdom getKingdom() {
		List<Kingdom> kingdoms;
		try {
			kingdoms = HibernateUtils.getSession().createCriteria(Kingdom.class).list();
		} finally {
			HibernateUtils.getSession().close();
		}

		return kingdoms.get(0);
	}

	@RequestMapping(value = "/userdetails", method = RequestMethod.GET)
	public User getUserDetails(@RequestParam(name = "login", required = true, defaultValue = "novalue") String login,
			@RequestParam(name = "password", required = true, defaultValue = "novalue") String password) {

		SimpleDao sd = new SimpleDao();
		User user = sd.getUser(login, password);

		return user;
	}

	@RequestMapping(value = "/usercreate", method = RequestMethod.POST)
	public boolean postCreateUser(@RequestBody User requestUser) {

		SimpleDao sd = new SimpleDao();
		boolean isUserCreated = sd.createUser(requestUser.getName(), requestUser.getLogin(), requestUser.getPassword());

		return isUserCreated;
	}

}
