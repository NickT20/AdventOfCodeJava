package com.AdventOfCode2023;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

@SpringBootTest
class Day7Tests {
	@Test
	public void Test1() throws IOException {
		var day7 = new Day7();
		Assert.assertEquals(6440, day7.ExecutePart1("/src/test/Inputs/Day7Example.txt"));
	}

	@Test
	public void Part1() throws IOException {
		var day7 = new Day7();
		Assert.assertEquals(248569531, day7.ExecutePart1("/src/test/Inputs/Day7Input.txt"));
	}

	@Test
	public void Test2() throws IOException {
		var day7 = new Day7();
		Assert.assertEquals(5905, day7.ExecutePart2("/src/test/Inputs/Day7Example.txt"));
	}

	@Test
	public void Part2() throws IOException {
		var day7 = new Day7();
		// 251714835 too high
		// 251056624 too high
		// 250795764 too high

		Assert.assertEquals(250382098, day7.ExecutePart2("/src/test/Inputs/Day7Input.txt"));
	}
}
