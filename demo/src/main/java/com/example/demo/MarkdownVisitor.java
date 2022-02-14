package com.example.demo;

public class MarkdownVisitor implements IVisitor{

    String output = "";

    @Override public void visit(Division division) {
        for (Text i : division.getTextList()) {
            i.accept(this);
        }
    }

    @Override public void visit(Header header) {
        output = "# " + output + "\n";
    }

    @Override public void visit(Passage passage) {
        output = output + "\n\n";
    }

    @Override public String output() {
        return output.strip();
    }
}
