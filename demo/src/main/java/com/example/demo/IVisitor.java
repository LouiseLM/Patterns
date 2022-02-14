package com.example.demo;

public interface IVisitor {

    void visit(Division division);

    void visit(Header header);

    void visit(Passage passage);

    String output();
}
