package com.AdventOfCode2023;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class Day4Tests {
	@Test
	public void Test1() throws IOException {
		var day4 = new Day4();
		Assert.assertEquals(13, day4.ExecutePart1("/src/test/Inputs/Day4Example.txt"));
	}

	@Test
	public void Part1() throws IOException {
		var day4 = new Day4();
		Assert.assertEquals(20829, day4.ExecutePart1("/src/test/Inputs/Day4Input.txt"));
	}

	@Test
	public void Test2() throws IOException {
		var day4 = new Day4();
		Assert.assertEquals(30, day4.ExecutePart2("/src/test/Inputs/Day4Example.txt"));
	}

	@Test
	public void Part2() throws IOException {
		var day4 = new Day4();
		Assert.assertEquals(12648035, day4.ExecutePart2("/src/test/Inputs/Day4Input.txt"));
	}
}
