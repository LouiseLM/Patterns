package com.example.demo;

/**
 * Do not use more than once
 */
public class HTMLVisitor implements IVisitor{
    private String output = "";
    private int indent = 0;

    @Override public void visit(Division division) {
        output = output + "<div>\n".indent(indent);
        indent = indent + 2;
        for (Text i : division.getTextList()) {
            i.accept(this);
        }
        indent = indent - 2;
        output = output + "</div>\n".indent(indent);
    }

    @Override public void visit(Header header) {
        output = output + ("<h1>" + header.getTitle() + "</h1>" + "\n").indent(indent);
    }

    @Override public void visit(Passage passage) {
        output = output + ("<p>" + passage.getContents() + "</p>" + "\n").indent(indent);
    }

    @Override public String output() {
        return output.strip();
    }
}
