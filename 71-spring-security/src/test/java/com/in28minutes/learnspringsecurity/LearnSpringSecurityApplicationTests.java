package com.in28minutes.learnspringsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class LearnSpringSecurityApplicationTests {

	@Test
	void contextLoads() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String encoded = bCryptPasswordEncoder.encode("dummy");
		System.out.println("encoded " + encoded);
	}

}
