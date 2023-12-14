package com.AdventOfCode2023;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class Day14Tests {
	@Test
	public void Test1() throws IOException {
		var day14 = new Day14();
		Assert.assertEquals(136, day14.ExecutePart1("/src/test/Inputs/Day14Example.txt"));
	}


	@Test
	public void Part1() throws IOException {
		var day14 = new Day14();
		// 112561 too low
		Assert.assertEquals(113486, day14.ExecutePart1("/src/test/Inputs/Day14Input.txt"));
	}
}
