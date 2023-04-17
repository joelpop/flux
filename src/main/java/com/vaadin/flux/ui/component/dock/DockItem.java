package com.vaadin.flux.ui.component.dock;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vaadin.flux.ui.dragdrop.DragDropKit;

public class DockItem extends Composite<FlexLayout> implements HasSize {

    public DockItem() {
        DragDropKit.initForMoveDrag(this);
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
        getContent().add(new Text(text));

        addClassNames(LumoUtility.Border.ALL,
                LumoUtility.BorderColor.CONTRAST_30,
                LumoUtility.BorderRadius.MEDIUM,
                LumoUtility.Background.CONTRAST_10,
                LumoUtility.FontSize.XSMALL);
    }
}
