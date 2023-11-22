package com.example.adventofcode;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Path;

@SpringBootTest
class Day2Tests {
	@Test
	public void Test1() throws IOException {
		var day2 = new Day2();
		Assert.assertEquals(18, day2.ExecutePart1("C:\\Users\\nicho\\Downloads\\adventofcode\\adventofcode\\src\\test\\Inputs\\Day2Example.txt"));
	}

	@Test
	public void Part1() throws IOException {
		var day2 = new Day2();
		Assert.assertEquals(32121, day2.ExecutePart1("C:\\Users\\nicho\\Downloads\\adventofcode\\adventofcode\\src\\test\\Inputs\\Day2Input.txt"));
	}

	@Test
	public void Test2() throws IOException {
		var day2 = new Day2();
		Assert.assertEquals(9, day2.ExecutePart2("C:\\Users\\nicho\\Downloads\\adventofcode\\adventofcode\\src\\test\\Inputs\\Day2Example2.txt"));
	}

	@Test
	public void Part2() throws IOException {
		var day2 = new Day2();
		Assert.assertEquals(197, day2.ExecutePart2("C:\\Users\\nicho\\Downloads\\adventofcode\\adventofcode\\src\\test\\Inputs\\Day2Input.txt"));
	}
}
