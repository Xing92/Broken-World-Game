package xing.brokenworldserver;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import xing.brokenworldserver.utils.HibernateUtils;

public class UtilsTest {

	@Test
	public void getFactoryTest() {
		SessionFactory factory1 = HibernateUtils.getFactory();
		SessionFactory factory2 = HibernateUtils.getFactory();

		Assert.assertNotNull(factory1, "HibernateUtils.getFactory method returned a null object");
		Assert.assertSame(factory1, factory2,
				"HibernateUtils.getFactory method did not returned the same SessionFactory.");
	}

	@Test
	public void getSessionTest() {

		Session session1 = HibernateUtils.getSession();
		Session session2 = HibernateUtils.getSession();

		Assert.assertNotNull(session1, "HibernateUtils.getSession method returned a null object");
		Assert.assertSame(session1, session2, "HibernateUtils.getSession method did not returned the same Session.");
	}

}
