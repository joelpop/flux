package com.vaadin.flux.ui.component.editor.visual.canvas;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.FlexLayout;

public class CanvasItemCompartment extends Composite<FlexLayout> implements CanvasItemParent {

    private final FlexLayout content;
    private final transient VoidContentCanvasItem voidContentCanvasItem;

    public CanvasItemCompartment() {
        voidContentCanvasItem = new VoidContentCanvasItem();

        content = getContent();
        content.setSizeFull();
        content.add(voidContentCanvasItem);
    }

    public void setCanvasItem(CanvasItem<?> canvasItem) {
        content.removeAll();
        content.add(canvasItem);
    }

    @Override
    public void addBefore(CanvasItem<?> existingCanvasItem, CanvasItem<?> newCanvasItem) {
        smartAdd(existingCanvasItem, newCanvasItem, FlexLayout.FlexDirection.ROW, false);
    }

    @Override
    public void addAbove(CanvasItem<?> existingCanvasItem, CanvasItem<?> newCanvasItem) {
        smartAdd(existingCanvasItem, newCanvasItem, FlexLayout.FlexDirection.COLUMN, false);
    }

    @Override
    public void addBelow(CanvasItem<?> existingCanvasItem, CanvasItem<?> newCanvasItem) {
        smartAdd(existingCanvasItem, newCanvasItem, FlexLayout.FlexDirection.COLUMN, true);
    }

    @Override
    public void addAfter(CanvasItem<?> existingCanvasItem, CanvasItem<?> newCanvasItem) {
        smartAdd(existingCanvasItem, newCanvasItem, FlexLayout.FlexDirection.ROW, true);
    }

    private void smartAdd(CanvasItem<?> existingCanvasItem, CanvasItem<?> newCanvasItem,
                          FlexLayout.FlexDirection desiredFlexDirection, boolean after) {
        if (existingCanvasItem instanceof VoidContentCanvasItem) {
            setCanvasItem(newCanvasItem);
        }
        else {
            content.remove(existingCanvasItem);

            var containerCanvasItem = new ContainerCanvasItem(desiredFlexDirection,
                    existingCanvasItem, newCanvasItem, after);

            content.add(containerCanvasItem);
        }
    }

    @Override
    public void adjustParentage() {
        if (getChildren().findAny().isEmpty()) {
            setCanvasItem(voidContentCanvasItem);
        }
    }

    @Override
    public int indexOf(Component component) {
        return content.indexOf(component);
    }

    @Override
    public void addComponentAtIndex(int index, Component component) {
        content.addComponentAtIndex(index, component);
    }
}
