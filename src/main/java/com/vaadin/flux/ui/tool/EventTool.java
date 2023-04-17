package com.vaadin.flux.ui.tool;

import com.vaadin.flux.ui.component.toolgroup.Tool;

public class EventTool extends Tool {

    public EventTool() {
        super("Event", "Events supported by this component and the selected actions they run when fired.");

        getContent().add(getHelpText());
    }
}
