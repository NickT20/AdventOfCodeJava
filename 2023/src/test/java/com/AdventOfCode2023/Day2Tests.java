package com.AdventOfCode2023;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class Day2Tests {
	@Test
	public void Test1() throws IOException {
		var day2 = new Day2();
		Assert.assertEquals(8, day2.ExecutePart1("/src/test/Inputs/Day2Example.txt", 12, 14, 13));
	}

	@Test
	public void Part1() throws IOException {
		var day2 = new Day2();
		Assert.assertEquals(2265, day2.ExecutePart1("/src/test/Inputs/Day2Input.txt", 12, 14, 13));
	}

	@Test
	public void Test2() throws IOException {
		var day2 = new Day2();
		Assert.assertEquals(2286, day2.ExecutePart2("/src/test/Inputs/Day2Example.txt", 12, 14, 13));
	}

	@Test
	public void Part2() throws IOException {
		var day2 = new Day2();
		Assert.assertEquals(64097, day2.ExecutePart2("/src/test/Inputs/Day2Input.txt", 12, 14, 13));
	}
}
