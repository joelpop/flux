package com.vaadin.flux.ui.tool;

import com.vaadin.flux.ui.component.toolgroup.Tool;

public class DirectoryTool extends Tool {

    public DirectoryTool() {
        super("Directory", "Listing of views, dialogs, & other custom components in this project.");

        getContent().add(getHelpText());
    }
}
