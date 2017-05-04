package test;

import static org.junit.Assert.*;

import org.junit.Test;
import controller.SignInUp;

public class LoginProcessingTest {

	@Test
	public void testHash() {
		assertTrue(SignInUp.hash("HelloThere") != 0);
		assertTrue(SignInUp.hash("HelloThere") == (SignInUp.hash("HelloThere")));
		assertTrue(SignInUp.hash("HelloThere") != (SignInUp.hash("ThereHello")));
	}

}
