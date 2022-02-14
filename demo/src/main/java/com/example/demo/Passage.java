package com.example.demo;

public class Passage implements Text {
    final private String contents;

    public Passage(String contents) {
        this.contents = contents;
    }

    public String getContents() {
        return contents;
    }

    @Override public void accept(IVisitor v) {
        v.visit(this);
    }

}
