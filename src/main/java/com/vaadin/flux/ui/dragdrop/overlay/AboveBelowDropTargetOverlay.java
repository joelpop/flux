package com.vaadin.flux.ui.dragdrop.overlay;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flux.ui.dragdrop.AbstractDropTargetOverlay;
import com.vaadin.flux.ui.dragdrop.DragDropKit;
import com.vaadin.flux.ui.dragdrop.DropZone;

import java.util.function.Consumer;

/**
 * <pre>
 * +------------------------+
 * | +-aboveDiv-----------+ |
 * | |                    | |
 * | |                    | |
 * | |                    | |
 * | |                    | |
 * | |                    | |
 * | |                    | |
 * | +--------------------+ |
 * | +-belowDiv-----------+ |
 * | |                    | |
 * | |                    | |
 * | |                    | |
 * | |                    | |
 * | +--------------------+ |
 * +------------------------+
 * </pre>
 */
public class AboveBelowDropTargetOverlay extends AbstractDropTargetOverlay {
    public static final String OVERLAY_CLASS_NAME = "above-below-drop-target-overlay";
    public static final String DROP_ZONE_CLASS_NAME = "above-below-drop-zone";

    /**
     * Create an {@code AboveBelowDropTargetOverlay} for a component.
     *
     * @param overlaidComponent target component overlaid during drag operation
     * @param aboveConsumer method to handle drag source being dropped on the "above" drop zone
     * @param belowConsumer method to handle drag source being dropped on the "below" drop zone
     */
    private AboveBelowDropTargetOverlay(Component overlaidComponent,
                                        Consumer<Component> aboveConsumer,
                                        Consumer<Component> belowConsumer) {
        super(overlaidComponent, OVERLAY_CLASS_NAME);

        var aboveDiv = new Div();
        aboveDiv.addClassNames(DROP_ZONE_CLASS_NAME, "above-zone");
        aboveDiv.setWidthFull();
        aboveDiv.setHeight("60%");

        var belowDiv = new Div();
        belowDiv.addClassNames(DROP_ZONE_CLASS_NAME, "below-zone");
        belowDiv.setWidthFull();
        belowDiv.setHeight("40%");

        var overlay = getContent();
        overlay.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        overlay.setSizeFull();
        overlay.add(aboveDiv);
        overlay.add(belowDiv);

        addDropZone(new DropZone(aboveDiv, aboveConsumer));
        addDropZone(new DropZone(belowDiv, belowConsumer));
    }

    /**
     * Factory method to create an {@code AboveBelowDropTargetOverlay} for a component.
     *
     * @param overlaidComponent target component overlaid during drag operation
     * @param dragSourceClass class of drag source component to be received by drop target overlay
     * @param dropTargetOverlayVisibilityClassName name of CSS class used to control the drop target overlay's visibility
     * @param aboveConsumer method to handle drag source being dropped on the "above" drop zone
     * @param belowConsumer method to handle drag source being dropped on the "below" drop zone
     * @param <S> Type of component being dragged
     */
    public static <S extends Component>
    void initForMoveDrop(Component overlaidComponent, Class<S> dragSourceClass,
                         String dropTargetOverlayVisibilityClassName,
                         Consumer<Component> aboveConsumer,
                         Consumer<Component> belowConsumer) {
        var dropTargetOverlay = new AboveBelowDropTargetOverlay(overlaidComponent,
                aboveConsumer, belowConsumer);
        dropTargetOverlay.addClassNames(dropTargetOverlayVisibilityClassName);

        overlaidComponent.getStyle().set("position", "relative");
        overlaidComponent.getElement().appendChild(dropTargetOverlay.getElement());

        DragDropKit.initForMoveDrop(dropTargetOverlay, dragSourceClass);
    }
}
