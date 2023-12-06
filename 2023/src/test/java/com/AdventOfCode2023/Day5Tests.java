package com.AdventOfCode2023;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigInteger;

@SpringBootTest
class Day5Tests {
	@Test
	public void Test1() throws IOException {
		var day5 = new Day5();
		Assert.assertEquals(BigInteger.valueOf(35), day5.ExecutePart1("/src/test/Inputs/Day5Example.txt"));
	}

	@Test
	public void Part1() throws IOException {
		var day5 = new Day5();
		Assert.assertEquals(BigInteger.valueOf(535088217), day5.ExecutePart1("/src/test/Inputs/Day5Input.txt"));
	}

	@Test
	public void Test2() throws IOException {
		var day5 = new Day5();
		Assert.assertEquals(BigInteger.valueOf(46), day5.ExecutePart2("/src/test/Inputs/Day5Example.txt"));
	}

	@Test
	public void Part2() throws IOException {
		var day5 = new Day5();
		Assert.assertEquals(51399228, day5.ExecutePart2("/src/test/Inputs/Day5Input.txt"));
	}
}
