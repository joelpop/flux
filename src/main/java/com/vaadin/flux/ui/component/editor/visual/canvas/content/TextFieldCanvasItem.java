package com.vaadin.flux.ui.component.editor.visual.canvas.content;

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flux.ui.component.editor.visual.canvas.ContentCanvasItem;

public class TextFieldCanvasItem extends ContentCanvasItem<TextField> {

    public TextFieldCanvasItem() {
        getContent().setLabel("Text Field");
    }
}
