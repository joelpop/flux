package com.vaadin.flux.ui.component.editor.visual.canvas;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;

import java.util.Optional;

public abstract class CanvasItem<C extends Component> extends Composite<C> {

    private final C content;

    protected CanvasItem() {
        content = getContent();
    }

    protected C getCanvasComponent() {
        return content;
    }

    public CanvasItemParent getCanvasItemParent() {
        return findAncestor(CanvasItemParent.class);
    }

    public void addBefore(CanvasItem<?> canvasItem) {
        Optional.ofNullable(getCanvasItemParent())
                .ifPresent(canvasItemParent -> canvasItemParent.addBefore(this, canvasItem));
    }

    public void addAbove(CanvasItem<?> canvasItem) {
        Optional.ofNullable(getCanvasItemParent())
                .ifPresent(canvasItemParent -> canvasItemParent.addAbove(this, canvasItem));
    }

    public void addBelow(CanvasItem<?> canvasItem) {
        Optional.ofNullable(getCanvasItemParent())
                .ifPresent(canvasItemParent -> canvasItemParent.addBelow(this, canvasItem));
    }

    public void addAfter(CanvasItem<?> canvasItem) {
        Optional.ofNullable(getCanvasItemParent())
                .ifPresent(canvasItemParent -> canvasItemParent.addAfter(this, canvasItem));
    }
}
