package com.vaadin.flux.ui.component.editorgroup;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;

import java.util.Collections;
import java.util.List;

public abstract class Editor extends Composite<Div> {
    private final String name;
    private final Icon icon;
    private final transient List<EditorAction> editorActions;

    protected Editor(String name, Icon icon, EditorAction... editorActions) {
        this.name = name;
        this.icon = icon;

        this.editorActions = List.of(editorActions);
    }

    protected Editor(String name, EditorAction... editorActions) {
        this(name, null, editorActions);
    }


    public String getName() {
        return name;
    }

    public Icon getIcon() {
        return icon;
    }

    public List<EditorAction> getEditorActions() {
        return Collections.unmodifiableList(editorActions);
    }
}
