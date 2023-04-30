package com.vaadin.flux.ui.component.editor.visual;

import com.vaadin.flux.ui.component.editor.visual.canvas.CanvasItemCompartment;
import com.vaadin.flux.ui.component.editor.visual.canvas.VoidContentCanvasItem;
import com.vaadin.flux.ui.component.editor.visual.canvas.content.AccordionCanvasItem;
import com.vaadin.flux.ui.component.editor.visual.canvas.content.ButtonCanvasItem;
import com.vaadin.flux.ui.component.editor.visual.canvas.content.DetailsCanvasItem;
import com.vaadin.flux.ui.component.editor.visual.canvas.content.TabsCanvasItem;
import com.vaadin.flux.ui.component.editorgroup.Editor;

public class VisualEditor extends Editor {

//    private final CanvasItemCompartment canvasItemCompartment;

    public VisualEditor(String name) {
        super(name);

        var canvasItemCompartment = new CanvasItemCompartment();


        var accordionCanvasItem = new AccordionCanvasItem();
        var buttonCanvasItem = new ButtonCanvasItem();
        var tabsCanvasItem = new TabsCanvasItem();
        var detailsCanvasItem = new DetailsCanvasItem();

        canvasItemCompartment.addBelow(new VoidContentCanvasItem(), accordionCanvasItem);
        accordionCanvasItem.addBelow(tabsCanvasItem);
        tabsCanvasItem.addBelow(detailsCanvasItem);
        detailsCanvasItem.addBelow(buttonCanvasItem);


        var content = getContent();
        content.setSizeFull();
        content.add(canvasItemCompartment);
    }

//    public CanvasItemCompartment getCanvasItemCompartment() {
//        return canvasItemCompartment;
//    }
}
