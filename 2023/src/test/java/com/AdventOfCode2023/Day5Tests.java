package com.AdventOfCode2023;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class Day5Tests {
	@Test
	public void Test1() throws IOException {
		var day5 = new Day5();
		Assert.assertEquals(13, day5.ExecutePart1("/src/test/Inputs/Day5Example.txt"));
	}
}
