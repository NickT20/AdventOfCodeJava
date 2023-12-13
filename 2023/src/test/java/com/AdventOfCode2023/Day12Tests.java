package com.AdventOfCode2023;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigInteger;

@SpringBootTest
class Day12Tests {
	@Test
	public void Test1() throws IOException {
		var day12 = new Day12();
		Assert.assertEquals(21, day12.ExecutePart1("/src/test/Inputs/Day12Example.txt"));
	}

	@Test
	public void Part1() throws IOException {
		var day12 = new Day12();
		Assert.assertEquals(7506, day12.ExecutePart1("/src/test/Inputs/Day12Input.txt"));
	}

	@Test
	public void Test2() throws IOException {
		var day12 = new Day12();
		Assert.assertEquals(525152, day12.ExecutePart2("/src/test/Inputs/Day12Example.txt"));
	}

	@Test
	public void Part2() throws IOException {
		var day12 = new Day12();
		Assert.assertEquals(Long.parseLong("548241300348335"), day12.ExecutePart2("/src/test/Inputs/Day12Input.txt"));
	}
}
