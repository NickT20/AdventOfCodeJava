package com.AdventOfCode2023;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class Day13Tests {
	@Test
	public void Test1() throws IOException {
		var day13 = new Day13();
		Assert.assertEquals(405, day13.ExecutePart1("/src/test/Inputs/Day13Example.txt"));
	}

	@Test
	public void Part1() throws IOException {
		var day13 = new Day13();
		Assert.assertEquals(30705, day13.ExecutePart1("/src/test/Inputs/Day13Input.txt"));
	}

	@Test
	public void Test2() throws IOException {
		var day13 = new Day13();
		Assert.assertEquals(405, day13.ExecutePart2("/src/test/Inputs/Day13Example.txt"));
	}
}
