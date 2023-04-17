package com.vaadin.flux.ui.tool;

import com.vaadin.flux.ui.component.toolgroup.Tool;

public class StructureTool extends Tool {

    public StructureTool() {
        super("Structure", """
            If a canvas editor tab is selected: displays the hierarchy of components in that canvas editor.
            If a code editor is selected: displays the methods/members/etc. for the code in that code editor.
            This tool can also be used to select and drop components into the component hierarchy
            instead of onto the canvas editor.
            This may be especially useful when the component hierarchy is complex and/or selection or targeting
            a component is otherwise elusive.
            It can also be used to rearrange the component hierarchy and code elements
            instead of via the respective editor.
        """);

        getContent().add(getHelpText());
    }
}
