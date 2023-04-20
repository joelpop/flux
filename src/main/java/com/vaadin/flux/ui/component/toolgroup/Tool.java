package com.vaadin.flux.ui.component.toolgroup;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;

import java.util.List;

public abstract class Tool extends Composite<Div> implements HasSize {
    private final String name;
    private final Icon icon;
    private final String helpText;
    private final transient List<ToolAction> toolActions;

    protected Tool(String name, Icon icon, String helpText, ToolAction... toolActions) {
        this.name = name;
        this.icon = icon;
        this.helpText = helpText;

        this.toolActions = List.of(toolActions);
    }

    protected Tool(String name, Icon icon, ToolAction... toolActions) {
        this(name, icon, "", toolActions);
    }

    protected Tool(String name, String helpText, ToolAction... toolActions) {
        this(name, null, helpText, toolActions);
    }

    protected Tool(String name, ToolAction... toolActions) {
        this(name, "", toolActions);
    }


    public String getName() {
        return name;
    }

    public Icon getIcon() {
        return icon;
    }

    public String getHelpText() {
        return helpText;
    }

    public List<ToolAction> getToolActions() {
        return toolActions;
    }
}
