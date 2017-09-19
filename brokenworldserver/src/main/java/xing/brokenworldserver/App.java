package xing.brokenworldserver;

import java.util.List;
import java.util.Timer;
import java.util.logging.Logger;

import org.hibernate.query.Query;

import xing.brokenworldserver.dailyresolution.StartDailyResolution;
import xing.brokenworldserver.model.Attack;
import xing.brokenworldserver.model.Kingdom;
import xing.brokenworldserver.model.User;
import xing.brokenworldserver.utils.HibernateUtils;
import xing.brokenworldserver.utils.Log;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		// for (int i = 0; i < 2; i++) {
		// prepare1();
		// }
		// HibernateUtils.getFactory().close();
		// test1();
		// retreive1();
		// startDaily(5);

	}

	private static void test1() {
		List<Attack> attacks;
		// attack = HibernateUtils.getSession().get(Attack.class, 6);
		attacks = HibernateUtils.getSession().createCriteria(Attack.class).list();
		// System.out.println("===========" + attack.getDestination().getId());
		for (Attack attack : attacks) {
			System.out.println("===========" + attack.getDestination().getId());
		}
		HibernateUtils.getSession().close();
	}

	private static void startDaily(int delayInSeconds) {
		long time = delayInSeconds * 1000;
		Timer timer = new Timer();
		timer.schedule(new StartDailyResolution(), 1000, time);
	}

	private static void retreive1() {
		try {
			// HibernateUtils.getSession().beginTransaction();
			Query<User> query = HibernateUtils.getSession().createQuery("from User order by id ASC");
			List<User> users = query.list();

			for (User user : users) {
				System.out.println(user.toString());
			}

		} finally {
			HibernateUtils.getSession().close();
			HibernateUtils.getFactory().close();
		}
	}

	private static void prepare1() {
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

			HibernateUtils.getSession().persist(user);
			HibernateUtils.getSession().persist(user2);
			HibernateUtils.getSession().persist(kingdom);
			HibernateUtils.getSession().persist(kingdom2);
			HibernateUtils.getSession().persist(kingdom3);
			HibernateUtils.getSession().persist(attack);
			HibernateUtils.getSession().getTransaction().commit();

		} finally {
			HibernateUtils.getSession().close();
			// HibernateUtils.getFactory().close();
		}
	}
}
