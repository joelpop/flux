package com.vaadin.flux.ui.component.editor;

import com.vaadin.flux.ui.component.editorgroup.Editor;

public class VisualEditor extends Editor {

    public VisualEditor(String name, String content) {
        super(name);

        this.getContent().add(content);
    }
}
