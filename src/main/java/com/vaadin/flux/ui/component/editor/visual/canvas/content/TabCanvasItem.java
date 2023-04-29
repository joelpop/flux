package com.vaadin.flux.ui.component.editor.visual.canvas.content;

import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flux.ui.component.editor.visual.canvas.ContentCanvasItem;

public class TabCanvasItem extends ContentCanvasItem<Tab> {

    public TabCanvasItem() {
        getContent().setLabel("Tab");
    }
}
