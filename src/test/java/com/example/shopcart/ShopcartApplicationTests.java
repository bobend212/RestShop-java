package com.example.shopcart;

import static org.junit.jupiter.api.Assertions.assertAll;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ShopcartApplicationTests {

	Calculator underTest = new Calculator();

	@Test
	void itShouldAddTwoNumbers() {
		// given
		int numberOne = 2;
		int numberTwo = 2;

		// when
		int result = underTest.add(numberOne, numberTwo);

		// then
		assertAll(
				() -> Assertions.assertThat(result).isEqualTo(4)
		);
	}

	class Calculator {
		int add(int a, int b) {
			return a + b;
		}
	}

}
