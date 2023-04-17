package com.vaadin.flux.ui.component.editor;

import com.vaadin.flux.ui.component.editorgroup.Editor;

public class SourceEditor extends Editor {

    public SourceEditor(String name, String content) {
        super(name);

        this.getContent().add(content);
    }
}
