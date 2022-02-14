package com.example.demo;

public class Header implements Text{
    final private String title;

    public Header(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override public void accept(IVisitor v) {
        v.visit(this);
    }
}
