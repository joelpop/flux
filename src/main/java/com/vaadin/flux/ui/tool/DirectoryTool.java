package com.vaadin.flux.ui.tool;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vaadin.flux.ui.component.toolgroup.Tool;
import com.vaadin.flux.ui.dragdrop.DragDropKit;
import com.vaadin.flux.ui.dragdrop.overlay.AroundDropTargetOverlay;

public class DirectoryTool extends Tool {

    public DirectoryTool() {
        super("Directory", "Listing of views, dialogs, & other custom components in this project.");

        getContent().add(getHelpText());



        var dragSource = new Div();
        dragSource.addClassNames(LumoUtility.Background.PRIMARY_50);
        dragSource.setWidth("100px");
        dragSource.setHeight("20px");

        var dropTarget = new Div();
        dropTarget.addClassNames(LumoUtility.Background.CONTRAST_5);
        dropTarget.setSizeFull();

        var content = getContent();
        content.setSizeFull();
        content.add(dragSource);
        content.add(dropTarget);

        var VISIBILITY_CLASS_NAME = "something";
        DragDropKit.initForMoveDrag(dragSource, VISIBILITY_CLASS_NAME);
        AroundDropTargetOverlay.initForMoveDrop(dropTarget,
                Div.class, VISIBILITY_CLASS_NAME,
                above -> {},
                before -> {},
                after -> {},
                below -> {});

    }
}
