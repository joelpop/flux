package com.vaadin.flux.ui.component.editor.visual.canvas.content;

import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flux.ui.component.editor.visual.canvas.ContentCanvasItem;

public class IconCanvasItem extends ContentCanvasItem<Icon> {

    public IconCanvasItem() {
        getContent().getElement().setAttribute("icon", "vaadin:user");
    }
}
