package test;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.SignInUp;

public class passwordHashTest {

	@Test
	public void test() {
		assertTrue(SignInUp.hash("a") != SignInUp.hash("b"));
		assertTrue(SignInUp.hash("a") != SignInUp.hash("A"));
		assertTrue(SignInUp.hash("hello") != SignInUp.hash("HELLO"));
		assertTrue(SignInUp.hash("hello") != SignInUp.hash("olleh"));
		assertTrue(SignInUp.hash("a") == SignInUp.hash("a"));
	}

}
