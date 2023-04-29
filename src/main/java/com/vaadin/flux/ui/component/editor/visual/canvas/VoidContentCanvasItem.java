package com.vaadin.flux.ui.component.editor.visual.canvas;

import com.vaadin.flow.component.orderedlayout.FlexLayout;

public class VoidContentCanvasItem extends ContentCanvasItem<FlexLayout> {

    public VoidContentCanvasItem() {
        var content = getContent();
        content.addClassNames("void-content-canvas-item");
        content.setSizeFull();
    }
}
