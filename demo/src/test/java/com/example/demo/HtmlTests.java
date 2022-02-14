package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

public class HtmlTests {

    IVisitor v = new HTMLVisitor();

    @Test
    void singlePassageNoDivisionTest() {
        Text passage = new Passage("Quality content");

        passage.accept(v);

        assertEquals("<p>Quality content</p>", v.output());
    }

    @Test
    void multiplePassageNoDivisionTest() {
        Text p1 = new Passage("Quality content");
        Text p2 = new Passage("Quantity guaranteed");

        p1.accept(v);
        p2.accept(v);

        assertEquals("""
                <p>Quality content</p>
                <p>Quantity guaranteed</p>""", v.output());
    }

    @Test
    void singlePassageSingleDivisionTest() {
        Text passage = new Passage("Quality content");
        Division division = new Division();
        division.addText(passage);

        division.accept(v);

        assertEquals("""
                <div>
                  <p>Quality content</p>
                </div>""", v.output());
    }

    @Test
    void emptyDivisionTest() {
        Division division = new Division();

        division.accept(v);

        assertEquals("""
                <div>
                </div>""", v.output());
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
                <div>
                  <p>Quality content</p>
                  <p>Quantity guaranteed</p>
                </div>""", v.output());
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
                <div>
                  <p>This</p>
                  <p>is</p>
                </div>
                <div>
                  <p>a</p>
                  <p>test</p>
                </div>""", v.output());
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
                <div>
                  <p>First</p>
                </div>
                <div>
                  <div>
                    <p>Second</p>
                  </div>
                  <p>Third</p>
                </div>""", v.output());
    }

    @Test
    void divisionInsideDivisionX2Test() {
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
                <div>
                  <div>
                    <div>
                      <p>First</p>
                    </div>
                    <p>Second</p>
                  </div>
                  <p>Third</p>
                </div>""", v.output());
    }

    @Test
    void headerInOuterDivTest() {
        Text h1 = new Header("Numbers");

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
        d3.addText(h1);

        d3.accept(v);

        assertEquals("""
                <div>
                  <div>
                    <div>
                      <p>First</p>
                    </div>
                    <p>Second</p>
                  </div>
                  <p>Third</p>
                  <h1>Numbers</h1>
                </div>""", v.output());
    }

    @Test
    void headerOuterInnerDivTest() {
        Text h1 = new Header("Numbers");
        Text h2 = new Header("Letters");

        Text p1 = new Passage("First");
        Division d1 = new Division();
        d1.addText(p1);
        d1.addText(h2);

        Text p2 = new Passage("Second");
        Division d2 = new Division();
        d2.addText(d1);
        d2.addText(p2);

        Text p3 = new Passage("Third");
        Division d3 = new Division();
        d3.addText(d2);
        d3.addText(p3);
        d3.addText(h1);

        d3.accept(v);

        assertEquals("""
                <div>
                  <div>
                    <div>
                      <p>First</p>
                      <h1>Letters</h1>
                    </div>
                    <p>Second</p>
                  </div>
                  <p>Third</p>
                  <h1>Numbers</h1>
                </div>""", v.output());
    }
}
