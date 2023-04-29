package com.vaadin.flux.ui.view.workspace;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flux.ui.component.Workspace;
import com.vaadin.flux.ui.component.dock.Dock;
import com.vaadin.flux.ui.component.dock.DockItem;

/**
 *
 * <pre>
 * +-content(VerticalLayout)---------------------------------------------------------------+
 * | +-topDock---------------------------------------------------------------------------+ |
 * | | +-menuDockable-----------------+ +-toolbarDockable------------------------------+ | |
 * | | |                              | |                                              | | |
 * | | +------------------------------+ +----------------------------------------------+ | |
 * | | +-componentPalletDockable-------------------------------------------------------+ | |
 * | | | ~ ! @ # $ % ^ & * ( ) _ + { } | : " < > ? < > ` - = [ ] \ ; ' , . /           | | |
 * | | +-------------------------------------------------------------------------------+ | |
 * | +-----------------------------------------------------------------------------------+ |
 * | +----+ +-workspace-----------------------------------------------------------+ +----+ |
 * | |    | |                                                                     | |    | |
 * | |    l |                                                                     | |    r |
 * | |    e |                                                                     | |    i |
 * | |    f |                                                                     | |    g |
 * | |    t |                                                                     | |    h |
 * | |    D |                                                                     | |    t |
 * | |    o |                                                                     | |    D |
 * | |    c |                                                                     | |    o |
 * | |    k |                                                                     | |    c |
 * | |    | |                                                                     | |    k |
 * | |    | |                                                                     | |    | |
 * | +----+ +---------------------------------------------------------------------+ +----+ |
 * | +-bottomDock------------------------------------------------------------------------+ |
 * | |                                                                                   | |
 * | +-----------------------------------------------------------------------------------+ |
 * | +-statusBar-------------------------------------------------------------------------+ |
 * | |                                                                                   | |
 * | +-----------------------------------------------------------------------------------+ |
 * +---------------------------------------------------------------------------------------+
 *
 * </pre>
 */
@PageTitle("Flux")
@Route(value = WorkspaceView.ROUTE)
public class WorkspaceView extends Composite<VerticalLayout> {
    public static final String ROUTE = "";

    private final Dock topDock;
    private final Dock leftDock;
    private final Workspace workspace;
    private final Dock rightDock;
    private final Dock bottomDock;
    private final HorizontalLayout statusBar;

    public WorkspaceView() {
        topDock = new Dock().asVertical();
        topDock.setWidthFull();

        leftDock = new Dock().asHorizontal();
        leftDock.setHeightFull();

        workspace = new Workspace();
        workspace.setSizeFull();

        rightDock = new Dock().asHorizontal();
        rightDock.setHeightFull();

        var center = new HorizontalLayout();
        center.setSizeFull();
        center.setSpacing(false);
        center.add(leftDock);
        center.add(workspace);
        center.add(rightDock);

        bottomDock = new Dock().asVertical();
        bottomDock.setWidthFull();

        statusBar = new HorizontalLayout();
        statusBar.setWidthFull();

        var content = getContent();
        content.setSizeFull();
        content.setPadding(false);
        content.setSpacing(false);
        content.add(topDock);
        content.add(center);
        content.add(bottomDock);
        content.add(statusBar);


        init();
    }

    private void init() {
        topDock.add(new DockItem("TOP 1"));
        topDock.add(new DockItem("TOP 2"));
        leftDock.add(new DockItem("LEFT 1"));
        leftDock.add(new DockItem("LEFT 2"));
        rightDock.add(new DockItem("RIGHT 1"));
        rightDock.add(new DockItem("RIGHT 2"));
        bottomDock.add(new DockItem("BOTTOM 1"));
        bottomDock.add(new DockItem("BOTTOM 2"));

        statusBar.add(new Text("Status"));
    }
}
