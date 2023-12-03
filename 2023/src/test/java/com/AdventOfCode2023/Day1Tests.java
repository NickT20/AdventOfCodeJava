package com.AdventOfCode2023;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class Day1Tests {
	@Test
	public void Test1() throws IOException {
		var day1 = new Day1();
		Assert.assertEquals(142, day1.ExecutePart1("C:\\GitHub\\AdventOfCodeJava\\2023\\src\\test\\Inputs\\Day1Example.txt"));
	}

	@Test
	public void Part1() throws IOException {
		var day1 = new Day1();
		Assert.assertEquals(55607, day1.ExecutePart1("C:\\GitHub\\AdventOfCodeJava\\2023\\src\\test\\Inputs\\Day1Input.txt"));
	}

	@Test
	public void Test2() throws IOException {
		var day1 = new Day1();
		Assert.assertEquals(281, day1.ExecutePart2("C:\\GitHub\\AdventOfCodeJava\\2023\\src\\test\\Inputs\\Day1Example2.txt"));
	}

	@Test
	public void Part2() throws IOException {
		var day1 = new Day1();
		// // 55309
		Assert.assertEquals(55291, day1.ExecutePart2("C:\\GitHub\\AdventOfCodeJava\\2023\\src\\test\\Inputs\\Day1Input.txt"));
	}
}
