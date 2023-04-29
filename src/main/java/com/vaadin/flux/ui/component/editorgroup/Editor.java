package com.vaadin.flux.ui.component.editorgroup;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;

import java.util.Collections;
import java.util.List;

public abstract class Editor extends Composite<FlexLayout> {
    private final String name;
    private final Icon icon;
    private final transient List<EditorAction> editorActions;

    protected Editor(String name, Icon icon, EditorAction... editorActions) {
        this.name = name;
        this.icon = icon;

        this.editorActions = List.of(editorActions);

        var content = getContent();
        content.setSizeFull();
        content.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
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
