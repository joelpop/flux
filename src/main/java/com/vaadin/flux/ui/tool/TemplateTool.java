package com.vaadin.flux.ui.tool;

import com.vaadin.flux.ui.component.toolgroup.Tool;

public class TemplateTool extends Tool {

    public TemplateTool() {
        super("Template", "Prebuilt view and component templates.");

        getContent().add(getHelpText());
    }
}
