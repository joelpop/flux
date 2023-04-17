package com.vaadin.flux.ui.tool;

import com.vaadin.flux.ui.component.toolgroup.Tool;

public class DataModelTool extends Tool {

    public DataModelTool() {
        super("Data Model", "Listing of data objects used by components in this project.");

        getContent().add(getHelpText());
    }
}
