package com.vaadin.flux.ui.component.editor.visual.canvas;

import com.vaadin.flow.component.Component;

public interface CanvasItemParent {

    void addBefore(CanvasItem<?> canvasItem, CanvasItem<?> newCanvasItem);

    void addAbove(CanvasItem<?> canvasItem, CanvasItem<?> newCanvasItem);

    void addBelow(CanvasItem<?> canvasItem, CanvasItem<?> newCanvasItem);

    void addAfter(CanvasItem<?> canvasItem, CanvasItem<?> newCanvasItem);

    void adjustParentage();

    int indexOf(Component component);

    void addComponentAtIndex(int index, Component component);
}
