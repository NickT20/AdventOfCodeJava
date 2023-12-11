package com.AdventOfCode2023;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class Day10Tests {
	@Test
	public void Test1() throws IOException {
		var day10 = new Day10();
		Assert.assertEquals(4, day10.ExecutePart1("/src/test/Inputs/Day10Example.txt"));
	}

	@Test
	public void Test2() throws IOException {
		var day10 = new Day10();
		Assert.assertEquals(8, day10.ExecutePart1("/src/test/Inputs/Day10Example2.txt"));
	}

	@Test
	public void Part1() throws IOException {
		var day10 = new Day10();
		Assert.assertEquals(6738, day10.ExecutePart1("/src/test/Inputs/Day10Input.txt"));
	}

	@Test
	public void Test3() throws IOException {
		var day10 = new Day10();
		Assert.assertEquals(10, day10.ExecutePart2("/src/test/Inputs/Day10ExamplePart2.txt"));
	}

	@Test
	public void Part2() throws IOException {
		var day10 = new Day10();
		// 580 too high
		// 518 too low
		Assert.assertEquals(579, day10.ExecutePart2("/src/test/Inputs/Day10Input.txt"));
	}
}
