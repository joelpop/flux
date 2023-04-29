package com.vaadin.flux.ui.component.editor.visual;

import com.vaadin.flux.ui.component.editor.visual.canvas.CanvasItemCompartment;
import com.vaadin.flux.ui.component.editor.visual.canvas.VoidContentCanvasItem;
import com.vaadin.flux.ui.component.editor.visual.canvas.content.*;
import com.vaadin.flux.ui.component.editorgroup.Editor;

public class VisualEditor extends Editor {

//    private final CanvasItemCompartment canvasItemCompartment;

    public VisualEditor(String name) {
        super(name);

        var canvasItemCompartment = new CanvasItemCompartment();


        var iconCanvasItem = new IconCanvasItem();
        var textFieldCanvasItem = new TextFieldCanvasItem();
        var buttonCanvasItem = new ButtonCanvasItem();
        var tabCanvasItem = new TabCanvasItem();
        var closeTabsCanvasItem = new CloseTabsCanvasItem();
        canvasItemCompartment.addAfter(new VoidContentCanvasItem(), textFieldCanvasItem);
        textFieldCanvasItem.addBelow(buttonCanvasItem);
        textFieldCanvasItem.addBefore(iconCanvasItem);
        buttonCanvasItem.addBefore(tabCanvasItem);
        buttonCanvasItem.addBefore(closeTabsCanvasItem);


        var content = getContent();
        content.setSizeFull();
        content.add(canvasItemCompartment);
    }

//    public CanvasItemCompartment getCanvasItemCompartment() {
//        return canvasItemCompartment;
//    }
}
