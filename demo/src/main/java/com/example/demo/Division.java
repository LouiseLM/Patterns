package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Division implements Text{
    private List<Text> textList = new ArrayList<>();

    public List<Text> getTextList() {
        return textList;
    }

    @Override public void accept(IVisitor v) {
        v.visit(this);
    }

    public void addText(Text t) {
        textList.add(t);
    }

    public void removeText(Text t) {
        textList.remove(t);
    }
}
