package com.AdventOfCode2023;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigInteger;

@SpringBootTest
class Day6Tests {
	@Test
	public void Test1() throws IOException {
		var day6 = new Day6();
		Assert.assertEquals(288, day6.ExecutePart1("/src/test/Inputs/Day6Example.txt"));
	}

	@Test
	public void Part1() throws IOException {
		var day6 = new Day6();
		Assert.assertEquals(1660968, day6.ExecutePart1("/src/test/Inputs/Day6Input.txt"));
	}

	@Test
	public void Test2() throws IOException {
		var day6 = new Day6();
		Assert.assertEquals(71503, day6.ExecutePart2("/src/test/Inputs/Day6Example2.txt"));
	}

	@Test
	public void Part2() throws IOException {
		var day6 = new Day6();
		Assert.assertEquals(1660968, day6.ExecutePart2("/src/test/Inputs/Day6Input2.txt"));
	}
}
