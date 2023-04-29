package com.vaadin.flux.ui.component.editor.visual.canvas.content;

import com.vaadin.flux.ui.component.closetab.CloseTab;
import com.vaadin.flux.ui.component.closetab.CloseTabs;
import com.vaadin.flux.ui.component.editor.visual.canvas.ContentCanvasItem;

public class CloseTabsCanvasItem extends ContentCanvasItem<CloseTabs> {

    public CloseTabsCanvasItem() {
        getContent().add(new CloseTab("CloseTab"));
    }
}
