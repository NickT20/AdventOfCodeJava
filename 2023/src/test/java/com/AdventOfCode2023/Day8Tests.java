package com.AdventOfCode2023;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigInteger;

@SpringBootTest
class Day8Tests {
	@Test
	public void Test1() throws IOException {
		var day8 = new Day8();
		Assert.assertEquals(2, day8.ExecutePart1("/src/test/Inputs/Day8Example.txt"));
	}

	@Test
	public void Test2() throws IOException {
		var day8 = new Day8();
		Assert.assertEquals(6, day8.ExecutePart1("/src/test/Inputs/Day8Example2.txt"));
	}

	@Test
	public void Part1() throws IOException {
		var day8 = new Day8();
		Assert.assertEquals(22357, day8.ExecutePart1("/src/test/Inputs/Day8Input.txt"));
	}

	@Test
	public void Test3() throws IOException {
		var day8 = new Day8();
		Assert.assertEquals(BigInteger.valueOf(6), day8.ExecutePart2("/src/test/Inputs/Day8ExamplePart2.txt"));
	}

	@Test
	public void Part2() throws IOException {
		var day8 = new Day8();
		// 129655083671578319400 Too High
		Assert.assertEquals(BigInteger.valueOf(Long.parseLong("10371555451871")), day8.ExecutePart2("/src/test/Inputs/Day8Input.txt"));
	}
}
