package com.vaadin.flux.ui.component.dock;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasOrderedComponents;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flux.ui.dragdrop.overlay.OntoDropTargetOverlay;

public class Dock extends Composite<FlexLayout> implements HasSize, HasOrderedComponents {
    public static final String VISIBILITY_CLASS_NAME = "onto-drop-target-dock";

    private final FlexLayout content;

    public Dock() {
        content = getContent();

        OntoDropTargetOverlay.initForMoveDrop(this, DockItem.class, VISIBILITY_CLASS_NAME,
                this::addOnto);
    }

    public boolean isVertical() {
        return content.getFlexDirection() == FlexLayout.FlexDirection.COLUMN;
    }

    public Dock asVertical() {
        content.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        return this;
    }

    public boolean isHorizontal() {
        return content.getFlexDirection() == FlexLayout.FlexDirection.ROW;
    }

    public Dock asHorizontal() {
        content.setFlexDirection(FlexLayout.FlexDirection.ROW);
        return this;
    }

    public Dock add(DockItem dockItem) {
        content.add(dockItem);
        return this;
    }

    public void addBefore(DockItem dockItem, DockItem newDockItem) {
        content.addComponentAtIndex(content.indexOf(dockItem), newDockItem);
    }

    public void addAfter(DockItem dockItem, DockItem newDockItem) {
        content.addComponentAtIndex(content.indexOf(dockItem) + 1, newDockItem);
    }

    private void addOnto(Component component) {
        if (component instanceof DockItem dockItem) {
            add(dockItem);
        }
    }
}
