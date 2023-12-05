package com.AdventOfCode2023;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class Day3Tests {
	@Test
	public void Test1() throws IOException {
		var day3 = new Day3();
		Assert.assertEquals(4361, day3.ExecutePart1("/src/test/Inputs/Day3Example.txt", 12, 14, 13));
	}

	@Test
	public void Part1() throws IOException {
		var day3 = new Day3();
		Assert.assertEquals(517021, day3.ExecutePart1("/src/test/Inputs/Day3Input.txt", 12, 14, 13));
	}

	@Test
	public void Test2() throws IOException {
		var day3 = new Day3();
		Assert.assertEquals(467835, day3.ExecutePart2("/src/test/Inputs/Day3Example.txt", 12, 14, 13));
	}

	@Test
	public void Part2() throws IOException {
		var day3 = new Day3();
		// 75590810 too low
		Assert.assertEquals(81296995, day3.ExecutePart2("/src/test/Inputs/Day3Input.txt", 12, 14, 13));
	}
}
