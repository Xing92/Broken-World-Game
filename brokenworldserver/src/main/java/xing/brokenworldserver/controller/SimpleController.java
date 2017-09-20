package xing.brokenworldserver.controller;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
			users = HibernateUtils.getSession().createCriteria(User.class).list();// createQuery("from
																					// Move
																					// order
																					// by
																					// id
																					// ASC").list();
		} finally {
			HibernateUtils.getSession().close();
		}

		return users.get(0);
	}

	@RequestMapping("/kingdom")
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
		User user;
		try {
			CriteriaBuilder builder = HibernateUtils.getSession().getCriteriaBuilder();
			CriteriaQuery<User> query = builder.createQuery(User.class);
			Root<User> root = query.from(User.class);
			// query.select(root).where(builder.equal(root.get("login"),
			// username));

			System.out.println("login: " + login);
			System.out.println("password: " + password);
			query.select(root).where(builder.and(builder.equal(root.get("login"), "xing"),
					builder.equal(root.get("password"), "asd123")));

			user = HibernateUtils.getSession().createQuery(query).getSingleResult();
		} finally {
			HibernateUtils.getSession().close();
		}

		return user;
	}
}
