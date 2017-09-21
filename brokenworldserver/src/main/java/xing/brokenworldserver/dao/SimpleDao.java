package xing.brokenworldserver.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import xing.brokenworldserver.model.User;
import xing.brokenworldserver.utils.HibernateUtils;
import xing.brokenworldserver.utils.Log;

public class SimpleDao {

	public User getUser(String login, String password) {
		User user;
		try {
			CriteriaBuilder builder = HibernateUtils.getSession().getCriteriaBuilder();
			CriteriaQuery<User> query = builder.createQuery(User.class);
			Root<User> root = query.from(User.class);

			query.select(root).where(builder.and(builder.equal(root.get("login"), login),
					builder.equal(root.get("password"), password)));

			System.out.println("login: " + login);
			System.out.println("password: " + password);
			
			user = HibernateUtils.getSession().createQuery(query).getSingleResult();
		} finally {
			HibernateUtils.getSession().close();
		}

		return user;
	}

	public User getSimpleUser(String name) {

		return null;
	}

	public boolean createUser(String name, String login, String password) {

		try {
			CriteriaBuilder builder = HibernateUtils.getSession().getCriteriaBuilder();
			CriteriaQuery<User> query = builder.createQuery(User.class);
			Root<User> root = query.from(User.class);
			query.select(root)
					.where(builder.or(builder.equal(root.get("login"), login), builder.equal(root.get("name"), name)));

			User tempUser = HibernateUtils.getSession().createQuery(query).getSingleResult();
			if (tempUser != null) {
				Log.info("User with given name or login already exists.");
				return false;
			}
		} catch (Exception e) {
			Log.info("Exception while searching a user. ", e);
		}
		try {
			HibernateUtils.getSession().beginTransaction();

			User user = new User();
			user.setName(name);
			user.setLogin(login);
			user.setPassword(password);

			HibernateUtils.getSession().persist(user);
			HibernateUtils.getSession().getTransaction().commit();
		} catch (Exception e) {
			Log.info("Could not create user with name: {}", name, e);
			return false;
		} finally {
			HibernateUtils.getSession().close();
		}

		Log.info("User {} successfuly created.", name);
		return true;
	}
}
