package com.vaadin.flux.ui.component.editor.visual.canvas.content;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flux.ui.component.editor.visual.canvas.ContentCanvasItem;

public class AccordionCanvasItem extends ContentCanvasItem<Accordion> {

    public AccordionCanvasItem() {
        getContent().add(new AccordionPanel("Accordion Panel 1"));
        getContent().add(new AccordionPanel("Accordion Panel 2"));
        getContent().add(new AccordionPanel("Accordion Panel 3"));
    }
}
