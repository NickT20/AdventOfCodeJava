package com.AdventOfCode2023;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

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
}
