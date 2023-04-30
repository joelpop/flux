package com.vaadin.flux.ui.component.editor.visual.canvas.content;

import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flux.ui.component.editor.visual.canvas.ContentCanvasItem;

public class AvatarCanvasItem extends ContentCanvasItem<Avatar> {

    public AvatarCanvasItem() {
        getContent().setAbbreviation("A");
        getContent().setName("Avatar");
    }
}
