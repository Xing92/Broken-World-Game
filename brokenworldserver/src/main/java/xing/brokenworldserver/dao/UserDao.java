package xing.brokenworldserver.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import xing.brokenworld_requests.response.LoginUserResponse;
import xing.brokenworldserver.model.User;
import xing.brokenworldserver.utils.HibernateUtils;

public class UserDao {

	public LoginUserResponse tryLogin(String login, String password) {

		List<User> users;
		LoginUserResponse loginUserResponse = new LoginUserResponse();
		
		try {
			CriteriaBuilder builder = HibernateUtils.getSession().getCriteriaBuilder();
			CriteriaQuery<User> query = builder.createQuery(User.class);
			Root<User> root = query.from(User.class);

			query.select(root).where(builder.and(builder.equal(root.get("login"), login),
					builder.equal(root.get("password"), password)));
			users = HibernateUtils.getSession().createQuery(query).getResultList();
		} finally {
			HibernateUtils.getSession().close();
		}
		
		if(users.size()==1){
			loginUserResponse.setUsername(users.get(0).getName());
			loginUserResponse.setOk(true);	
		}
		else{
			loginUserResponse.setOk(false);	
		}

			

		return loginUserResponse;
	}


}
