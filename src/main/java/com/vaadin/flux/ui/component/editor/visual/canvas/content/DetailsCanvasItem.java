package com.vaadin.flux.ui.component.editor.visual.canvas.content;

import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flux.ui.component.editor.visual.canvas.ContentCanvasItem;

public class DetailsCanvasItem extends ContentCanvasItem<Details> {

    public DetailsCanvasItem() {
        getContent().setSummaryText("Details");
        getContent().addContent(new Span("Content"));
        getContent().setOpened(true);
    }
}
