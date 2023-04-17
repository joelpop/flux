package com.vaadin.flux.ui.tool;

import com.vaadin.flux.ui.component.toolgroup.Tool;

public class PropertyTool extends Tool {

    public PropertyTool() {
        super("Property", "Properties supported by this component and their values.");

        getContent().add(getHelpText());
    }
}
