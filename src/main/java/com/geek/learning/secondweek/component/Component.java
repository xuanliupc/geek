package com.geek.learning.secondweek.component;

public class Component {
    protected String componentName;
    protected String showName;

    public Component(String showName) {
        this.showName = showName;
    }

    public Component(String componentName, String showName) {
        this.componentName = componentName;
        this.showName = showName;
    }

    public void print() {
        System.out.println(String.format("print %s(%s)", componentName, showName));
    }
}
