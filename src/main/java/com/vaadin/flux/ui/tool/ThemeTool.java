package com.vaadin.flux.ui.tool;

import com.vaadin.flux.ui.component.toolgroup.Tool;

public class ThemeTool extends Tool {

    public ThemeTool() {
        super("Theme", "Theme customizations for this project.");

        getContent().add(getHelpText());
    }
}
