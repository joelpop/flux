package com.vaadin.flux.ui.tool;

import com.vaadin.flux.ui.component.toolgroup.Tool;

public class DirectoryTool extends Tool {
    public static final String BOX_OVERLAY_CLASS = "box-overlay";
    public static final String BOX_TYPE1_OVERLAY_VISIBILITY_CLASS = "box-type1-overlay-visibility";
    public static final String BOX_TYPE2_OVERLAY_VISIBILITY_CLASS = "box-type2-overlay-visibility";
    public static final String OVERLAY_VISIBILITY_PROPERTY = "--overlay-visibility";

    public DirectoryTool() {
        super("Directory", "Listing of views, dialogs, & other custom components in this project.");

        getContent().add(getHelpText());
    }
}
