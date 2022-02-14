package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class PlainTextVisitor implements IVisitor{
    private String output = "";

    @Override public void visit(Division division) {
        for (Text i : division.getTextList()) {
            i.accept(this);
        }
    }

    @Override public void visit(Header header) {
        output = output + header.getTitle() + "\n";
    }

    @Override public void visit(Passage passage) {
        output = output + passage.getContents() + "\n";
    }

    @Override public String output() {
        return output.strip();
    }
}
