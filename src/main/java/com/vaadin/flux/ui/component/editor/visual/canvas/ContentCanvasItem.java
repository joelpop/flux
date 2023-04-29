package com.vaadin.flux.ui.component.editor.visual.canvas;

import com.vaadin.flow.component.Component;
import com.vaadin.flux.ui.dragdrop.DragDropKit;
import com.vaadin.flux.ui.dragdrop.overlay.AroundDropTargetOverlay;

public class ContentCanvasItem<C extends Component> extends CanvasItem<C> {
    public static final String VISIBILITY_CLASS_NAME = "around-drop-target-canvas-item";

    public ContentCanvasItem() {
        DragDropKit.initForMoveDrag(this, VISIBILITY_CLASS_NAME);
        AroundDropTargetOverlay.initForMoveDrop(this, ContentCanvasItem.class,
                VISIBILITY_CLASS_NAME,
                this::addBefore, this::addAbove, this::addBelow, this::addAfter);
    }

    private void addBefore(Component component) {
        if (component instanceof ContentCanvasItem<?> contentCanvasItem) {
            getCanvasItemParent().addBefore(this, contentCanvasItem);
        }
    }

    private void addAbove(Component component) {
        if (component instanceof ContentCanvasItem<?> contentCanvasItem) {
            getCanvasItemParent().addAbove(this, contentCanvasItem);
        }
    }

    private void addBelow(Component component) {
        if (component instanceof ContentCanvasItem<?> contentCanvasItem) {
            getCanvasItemParent().addBelow(this, contentCanvasItem);
        }
    }

    private void addAfter(Component component) {
        if (component instanceof ContentCanvasItem<?> contentCanvasItem) {
            getCanvasItemParent().addAfter(this, contentCanvasItem);
        }
    }

    @Override
    public void removeFromParent() {
        var canvasItemParent = getCanvasItemParent();
        super.removeFromParent();
        canvasItemParent.adjustParentage();
    }
}
