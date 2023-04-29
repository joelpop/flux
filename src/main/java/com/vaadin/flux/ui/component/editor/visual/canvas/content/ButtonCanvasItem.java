package com.vaadin.flux.ui.component.editor.visual.canvas.content;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flux.ui.component.editor.visual.canvas.ContentCanvasItem;

public class ButtonCanvasItem extends ContentCanvasItem<Button> {

    public ButtonCanvasItem() {
        getContent().setText("Button");
    }
}
