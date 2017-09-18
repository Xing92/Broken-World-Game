package xing.brokenworldserver;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleModelTest {

	@Test
	public void firstTest() {

		Assert.assertTrue(true, "Should be true");

	}

	@Test
	public void secondTest() {

		Assert.assertFalse(false, "Should be false");

	}
	
}
