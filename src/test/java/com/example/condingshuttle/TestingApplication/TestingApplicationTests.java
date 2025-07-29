package com.example.condingshuttle.TestingApplication;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Assertions;

import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

//@SpringBootTest
@Slf4j
class TestingApplicationTests {

//	@Test
	@BeforeEach
	void setUp(){
		log.info("Setting up the configuration..");
	}

	@AfterEach
	void voidTearDown(){
		log.info("Clearing the configuration");
	}

	@BeforeAll
	static void setUpOnce(){
		log.info("Seting up once");
	}

	@Test
	void testNumberOne() {
		log.info("First test run...");
	}

	@Test
	void testNumberTwo() {
		int a = 20;
		int b = 30;

		int res = a + b;

//		Assertions.assertEquals(50, res);

//		assertThat(res)
//				.isEqualTo(50)
//				.isCloseTo(45, Offset.offset(10));

		assertThat("Apple")
				.startsWith("App")
				.hasSize(20);

	}

	int addTumNumbers(int a, int b){
		return a + b;
	}

	double divideTwoNumbers(int a, int b){
		try {
			return a / b;
		}catch(ArithmeticException e){
			log.info("Arithmetic Exception Occurred : " + e.getLocalizedMessage());
			throw new ArithmeticException(e.getLocalizedMessage());
		}
	}

	@Test
	void testDivideTwoNumbers_whenDenominatorIsZero_thenArithmeticException(){
		int a = 5;
		int b = 0;

		divideTwoNumbers(a, b);

		assertThatThrownBy( () -> divideTwoNumbers(a, b))
				.isInstanceOf(ArrayStoreException.class)
				.hasMessage("Tried to divide by zero.");
	}

}
