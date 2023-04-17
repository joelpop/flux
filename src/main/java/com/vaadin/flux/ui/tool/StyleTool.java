package com.vaadin.flux.ui.tool;

import com.vaadin.flux.ui.component.toolgroup.Tool;

public class StyleTool extends Tool {

    public StyleTool() {
        super("Style", "Custom styling for this component.");

        getContent().add(getHelpText());
    }
}
