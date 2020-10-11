package com.geek.learning.secondweek.component;

import java.util.ArrayList;
import java.util.List;

public class WinForm extends Component {
    private List<Component> componentList;

    public WinForm(String showName) {
        super("WinForm", showName);
    }

    public void addComponent(Component component) {
        if (componentList == null) {
            synchronized (this) {
                if (componentList == null) {
                    componentList = new ArrayList<>();
                }
            }
        }
        componentList.add(component);
    }

    @Override
    public void print() {
        super.print();
        for (Component component : componentList) {
            component.print();
        }
    }
}
