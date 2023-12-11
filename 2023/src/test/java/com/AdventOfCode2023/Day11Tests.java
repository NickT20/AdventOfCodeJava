package com.AdventOfCode2023;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigInteger;

@SpringBootTest
class Day11Tests {
	@Test
	public void Test1() throws IOException {
		var day11 = new Day11();
		Assert.assertEquals(374, day11.ExecutePart1("/src/test/Inputs/Day11Example.txt"));
	}

	@Test
	public void Part1() throws IOException {
		var day11 = new Day11();
		Assert.assertEquals(9918828, day11.ExecutePart1("/src/test/Inputs/Day11Input.txt"));
	}

	@Test
	public void Test2() throws IOException {
		var day11 = new Day11();
		Assert.assertEquals((BigInteger.valueOf(374)), day11.ExecutePart2("/src/test/Inputs/Day11Example.txt", 1));
	}

	@Test
	public void Test3() throws IOException {
		var day11 = new Day11();
		Assert.assertEquals((BigInteger.valueOf(1030)), day11.ExecutePart2("/src/test/Inputs/Day11Example.txt", 9));
	}

	@Test
	public void Test4() throws IOException {
		var day11 = new Day11();
		Assert.assertEquals((BigInteger.valueOf(8410)), day11.ExecutePart2("/src/test/Inputs/Day11Example.txt", 99));
	}

	@Test
	public void Part2() throws IOException {
		var day11 = new Day11();
		Assert.assertEquals(BigInteger.valueOf(Long.parseLong("692506533832")), day11.ExecutePart2("/src/test/Inputs/Day11Input.txt", 999999));
	}
}
