package com.vaadin.flux.ui.component.split;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.splitlayout.SplitLayout;

public class SplitItem<C extends Component> extends Composite<C> {

    public Split splitUpward(SplitItem<?> splitItem) {
        return new Split(splitItem, this, SplitLayout.Orientation.VERTICAL);
    }

    public Split splitLeftward(SplitItem<?> splitItem) {
        return new Split(splitItem, this, SplitLayout.Orientation.HORIZONTAL);
    }

    public Split splitRightward(SplitItem<?> splitItem) {
        return new Split(this, splitItem, SplitLayout.Orientation.HORIZONTAL);
    }

    public Split splitDownward(SplitItem<?> splitItem) {
        return new Split(this, splitItem, SplitLayout.Orientation.VERTICAL);
    }
}
