package com.vaadin.flux.ui.component.dock;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vaadin.flux.ui.dragdrop.DragDropKit;
import com.vaadin.flux.ui.dragdrop.overlay.AroundDropTargetOverlay;

public class DockItem extends Composite<FlexLayout> implements HasSize {
    public static final String VISIBILITY_CLASS_NAME = "around-drop-target-dockitem";

    public DockItem() {
        DragDropKit.initForMoveDrag(this, Dock.VISIBILITY_CLASS_NAME);
        DragDropKit.initForMoveDrag(this, VISIBILITY_CLASS_NAME);

        AroundDropTargetOverlay.initForMoveDrop(this, DockItem.class,
                VISIBILITY_CLASS_NAME,
                this::onDropBefore, this::onDropAbove, this::onDropBelow, this::onDropAfter);
    }

    private void onDropBefore(Component component) {
        if (component instanceof DockItem dockItem) {
            var dock = findAncestor(Dock.class);
            if (dock != null) {
                dock.addBefore(this, dockItem);
            }
        }
    }

    private void onDropAbove(Component component) {
        onDropBefore(component);
    }

    private void onDropBelow(Component component) {
        onDropAfter(component);
    }

    private void onDropAfter(Component component) {
        if (component instanceof DockItem dockItem) {
            var dock = findAncestor(Dock.class);
            if (dock != null) {
                dock.addAfter(this, dockItem);
            }
        }
    }

    /**
     *
     * @param text
     *
     * @deprecated Here just for development.
     */
    @Deprecated(forRemoval = true)
    public DockItem(String text) {
        this();

        var content = getContent();
        content.add(new Text(text));

        addClassNames(LumoUtility.Border.ALL,
                LumoUtility.BorderColor.CONTRAST_30,
                LumoUtility.BorderRadius.MEDIUM,
                LumoUtility.Background.CONTRAST_10,
                LumoUtility.FontSize.XSMALL);
    }
}
