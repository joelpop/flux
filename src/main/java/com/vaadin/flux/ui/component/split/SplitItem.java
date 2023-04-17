package com.vaadin.flux.ui.component.split;

import com.vaadin.flow.component.splitlayout.SplitLayout;

public interface SplitItem {

    default SplitItem splitUpward(SplitItem splitItem) {
        return new Split(splitItem, this, SplitLayout.Orientation.VERTICAL);
    }

    default SplitItem splitLeftward(SplitItem splitItem) {
        return new Split(splitItem, this, SplitLayout.Orientation.HORIZONTAL);
    }

    default SplitItem splitRightward(SplitItem splitItem) {
        return new Split(this, splitItem, SplitLayout.Orientation.HORIZONTAL);
    }

    default SplitItem splitDownward(SplitItem splitItem) {
        return new Split(this, splitItem, SplitLayout.Orientation.VERTICAL);
    }

}
