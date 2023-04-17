package com.vaadin.flux.ui.component.dock;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flux.ui.dragdrop.DragDropKit;

public abstract class Dock extends Composite<FlexLayout> implements HasSize {

    private final FlexLayout content;

    Dock(FlexLayout.FlexDirection flexDirection) {
        content = getContent();
        content.setFlexDirection(flexDirection);

        DragDropKit.initForMoveDrop(this, DockItem.class, Dock::add);
    }

    public void add(DockItem dockItem) {
        content.add(dockItem);
    }

}
