package com.AdventOfCode2023;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class Day15Tests {
	@Test
	public void Test1() throws IOException {
		var day15 = new Day15();
		Assert.assertEquals(1320, day15.ExecutePart1("/src/test/Inputs/Day15Example.txt"));
	}

	@Test
	public void Part1() throws IOException {
		var day15 = new Day15();
		Assert.assertEquals(1320, day15.ExecutePart1("/src/test/Inputs/Day15Input.txt"));
	}

	@Test
	public void Test2() throws IOException {
		var day15 = new Day15();
		Assert.assertEquals(145, day15.ExecutePart2("/src/test/Inputs/Day15Example.txt"));
	}

	@Test
	public void Part2() throws IOException {
		var day15 = new Day15();
		Assert.assertEquals(264021, day15.ExecutePart2("/src/test/Inputs/Day15Input.txt"));
	}
}
