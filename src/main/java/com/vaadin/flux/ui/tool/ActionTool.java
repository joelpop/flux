package com.vaadin.flux.ui.tool;

import com.vaadin.flux.ui.component.toolgroup.Tool;

public class ActionTool extends Tool {

    public ActionTool() {
        super("Action", "Actions to perform in response to events.");

        getContent().add(getHelpText());
    }
}
