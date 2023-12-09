package com.AdventOfCode2023;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigInteger;

@SpringBootTest
class Day9Tests {
	@Test
	public void Test1() throws IOException {
		var day9 = new Day9();
		Assert.assertEquals(114, day9.ExecutePart1("/src/test/Inputs/Day9Example.txt"));
	}

	@Test
	public void Part1() throws IOException {
		var day9 = new Day9();
		Assert.assertEquals(1904165718, day9.ExecutePart1("/src/test/Inputs/Day9Input.txt"));
	}

	@Test
	public void Test2() throws IOException {
		var day9 = new Day9();
		Assert.assertEquals(2, day9.ExecutePart2("/src/test/Inputs/Day9Example.txt"));
	}

	@Test
	public void Part2() throws IOException {
		var day9 = new Day9();
		Assert.assertEquals(964, day9.ExecutePart2("/src/test/Inputs/Day9Input.txt"));
	}
}
