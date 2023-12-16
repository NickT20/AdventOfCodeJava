package com.AdventOfCode2023;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class Day16Tests {
	@Test
	public void Test1() throws IOException {
		var day16 = new Day16();
		Assert.assertEquals(46, day16.ExecutePart1("/src/test/Inputs/Day16Example.txt"));
	}

	@Test
	public void Part1() throws IOException {
		var day16 = new Day16();
		Assert.assertEquals(6855, day16.ExecutePart1("/src/test/Inputs/Day16Input.txt"));
	}

	@Test
	public void Test2() throws IOException {
		var day16 = new Day16();
		Assert.assertEquals(51, day16.ExecutePart2("/src/test/Inputs/Day16Example.txt"));
	}

	@Test
	public void Part2() throws IOException {
		var day16 = new Day16();
		Assert.assertEquals(7513, day16.ExecutePart2("/src/test/Inputs/Day16Input.txt"));
	}
}
