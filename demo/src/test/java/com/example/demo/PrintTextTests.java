package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest
class PrintTextTests {

	IVisitor v = new PlainTextVisitor();

	@Test
	void contextLoads() {
	}

	@Test
	void singlePassageNoDivisionTest() {
		Text passage = new Passage("Quality content");
		passage.accept(v);

		assertEquals("Quality content", v.output());
	}

	@Test
	void multiplePassageNoDivisionTest() {
		Text p1 = new Passage("Quality content");
		Text p2 = new Passage("Quantity Guaranteed");

		p1.accept(v);
		p2.accept(v);

		assertEquals("""
				Quality content
				Quantity Guaranteed""", v.output());
	}

	@Test
	void singlePassageSingleDivisionTest() {
		Text passage = new Passage("Quality content");
		Division division = new Division();
		division.addText(passage);

		division.accept(v);

		assertEquals("Quality content", v.output());
	}

	@Test
	void multiplePassageSingleDivisionTest() {
		Text passage = new Passage("Quality content");
		Text passage2 = new Passage("Quantity guaranteed");
		Division division = new Division();
		division.addText(passage);
		division.addText(passage2);

		division.accept(v);

		assertEquals("""
				Quality content
				Quantity guaranteed""", v.output());
	}

	@Test
	void multipleDivisionTest() {
		Text p1 = new Passage("This");
		Text p2 = new Passage("is");
		Division d1 = new Division();
		d1.addText(p1);
		d1.addText(p2);

		Text p3 = new Passage("a");
		Text p4 = new Passage("test");
		Division d2 = new Division();
		d2.addText(p3);
		d2.addText(p4);

		d1.accept(v);
		d2.accept(v);

		assertEquals("""
				This
				is
				a
				test""", v.output());
	}

	@Test
	void divisionInsideDivisionTest() {
		Text p1 = new Passage("First");
		Division d1 = new Division();
		d1.addText(p1);

		Text p2 = new Passage("Second");
		Division d2 = new Division();
		d2.addText(p2);

		Text p3 = new Passage("Third");
		Division d3 = new Division();
		d3.addText(d2);
		d3.addText(p3);

		d1.accept(v);
		d3.accept(v);

		assertEquals("""
				First
				Second
				Third""", v.output());
	}

	@Test
	void divisionInsideDivisionTest2() {
		Text p1 = new Passage("First");
		Division d1 = new Division();
		d1.addText(p1);

		Text p2 = new Passage("Second");
		Division d2 = new Division();
		d2.addText(d1);
		d2.addText(p2);

		Text p3 = new Passage("Third");
		Division d3 = new Division();
		d3.addText(d2);
		d3.addText(p3);

		d3.accept(v);

		assertEquals("""
				First
				Second
				Third""", v.output());
	}

	@Test
	void headersInsideDivisionsTest() {
		Text h1 = new Header("Numbers");
		Text h2 = new Header("Letters");

		Text p1 = new Passage("First");
		Division d1 = new Division();
		d1.addText(p1);
		d1.addText(h1);

		Text p2 = new Passage("Second");
		Division d2 = new Division();
		d2.addText(d1);
		d2.addText(p2);

		Text p3 = new Passage("Third");
		Division d3 = new Division();
		d3.addText(d2);
		d3.addText(p3);
		d3.addText(h2);

		d3.accept(v);

		assertEquals("""
				First
				Numbers
				Second
				Third
				Letters""", v.output());
	}

	@Test
	void visitorTest() {
		Text h1 = new Header("Numbers");
		Text h2 = new Header("Letters");

		Text p1 = new Passage("First");
		Division d1 = new Division();
		d1.addText(p1);
		d1.addText(h1);

		Text p2 = new Passage("Second");
		Division d2 = new Division();
		d2.addText(d1);
		d2.addText(p2);

		Text p3 = new Passage("Third");
		Division d3 = new Division();
		d3.addText(d2);
		d3.addText(p3);
		d3.addText(h2);

		d3.accept(v);
		System.out.println(v.output());
	}

}
