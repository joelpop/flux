package com.vaadin.flux.ui.tool;

import com.vaadin.flux.ui.component.toolgroup.Tool;

public class ConsoleTool extends Tool {

    public ConsoleTool() {
        super("Console", "Log of messages for user.");

        getContent().add(getHelpText());
    }
}
