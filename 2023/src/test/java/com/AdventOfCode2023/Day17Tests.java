package com.AdventOfCode2023;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class Day17Tests {
	@Test
	public void Test1() throws IOException {
		var day17 = new Day17();
		Assert.assertEquals(46, day17.ExecutePart1("/src/test/Inputs/Day17Example.txt"));
	}

	@Test
	public void Part1() throws IOException {
		var day17 = new Day17();
		Assert.assertEquals(6855, day17.ExecutePart1("/src/test/Inputs/Day16Input.txt"));
	}
	@Test
	public void Test12() throws IOException {
		long result = DayTest.calculateShortestPathFromSource("/src/test/Inputs/Day17Example.txt");
		Assert.assertEquals(102, result);
	}

	// @Test
	// public void Test2() throws IOException {
	// 	var day17 = new Day17();
	// 	Assert.assertEquals(51, day17.ExecutePart2("/src/test/Inputs/Day16Example.txt"));
	// }

	// @Test
	// public void Part2() throws IOException {
	// 	var day17 = new Day17();
	// 	Assert.assertEquals(7513, day17.ExecutePart2("/src/test/Inputs/Day16Input.txt"));
	// }
}
