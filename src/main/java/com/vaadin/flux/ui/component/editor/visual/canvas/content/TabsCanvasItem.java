package com.vaadin.flux.ui.component.editor.visual.canvas.content;

import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flux.ui.component.editor.visual.canvas.ContentCanvasItem;

public class TabsCanvasItem extends ContentCanvasItem<Tabs> {

    public TabsCanvasItem() {
        getContent().add(new Tab("Tab 1"), new Tab("Tab 2"), new Tab("Tab 3"));
    }
}
