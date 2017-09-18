package xing.brokenworldserver;

import java.util.List;

import org.hibernate.query.Query;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import xing.brokenworldserver.model.Attack;
import xing.brokenworldserver.model.Kingdom;
import xing.brokenworldserver.model.Move;
import xing.brokenworldserver.model.User;
import xing.brokenworldserver.utils.HibernateUtils;

public class DatabaseTest {

	@BeforeTest
	public void prepareDatabase() {
		try {
			HibernateUtils.getSession().beginTransaction();

			User user = new User();
			user.setName("Pawel");
			user.setLogin("asd");
			user.setPassword("asd123");
			Kingdom kingdom = new Kingdom();
			kingdom.setName("Kingdom Pawla");
			kingdom.setRace("Orcs");
			kingdom.setPeople(1000);
			kingdom.setLand(2000);
			user.addKingdom(kingdom);

			User user2 = new User();
			user2.setName("Karol");
			user2.setLogin("asd");
			user2.setPassword("asd123");
			Kingdom kingdom2 = new Kingdom();
			kingdom2.setName("Kingdom Karola");
			kingdom2.setRace("Elves");
			kingdom2.setPeople(1000);
			kingdom2.setLand(2000);
			user2.addKingdom(kingdom2);

			Kingdom kingdom3 = new Kingdom();
			kingdom3.setName("Kingdom Pawla 2");
			kingdom3.setRace("Humans");
			kingdom3.setPeople(1000);
			kingdom3.setLand(2000);
			user.addKingdom(kingdom3);

			Attack attack = new Attack();
			attack.setSource(kingdom);
			attack.setDestination(kingdom2);
			attack.setPeople(1100);

			Move move = new Move();
			move.setChangeArmy(500);
			move.setChangeMiners(750);
			kingdom.setMove(move);

			HibernateUtils.getSession().persist(user);
			HibernateUtils.getSession().persist(user2);
			HibernateUtils.getSession().persist(kingdom);
			HibernateUtils.getSession().persist(kingdom2);
			HibernateUtils.getSession().persist(kingdom3);
			HibernateUtils.getSession().persist(attack);
			HibernateUtils.getSession().persist(move);
			HibernateUtils.getSession().getTransaction().commit();

		} finally {
			HibernateUtils.getSession().close();
		}
	}

	@Test
	public void fisrtTest() {
		try {
			Query<User> query = HibernateUtils.getSession().createQuery("from User order by id ASC");
			List<User> users = query.list();
			Assert.assertEquals(users.get(0).getName(), "Pawel", "Failed to find User with name \"Pawel\"");
		} finally {
			HibernateUtils.getSession().close();
		}

	}
}
