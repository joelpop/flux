package com.vaadin.flux.ui.component.editorgroup;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;

public class EditorAction extends Composite<Div> {
    private final Icon icon;
    private final String label;
    private final transient Runnable action;

    protected EditorAction(Icon icon, String label, Runnable action) {
        this.icon = icon;
        this.label = label;
        this.action = action;

        var content = getContent();
        content.add(icon);
        content.add(new Span(label));
    }

    public Icon getIcon() {
        return icon;
    }

    public String getLabel() {
        return label;
    }

    public void run() {
        action.run();
    }
}
