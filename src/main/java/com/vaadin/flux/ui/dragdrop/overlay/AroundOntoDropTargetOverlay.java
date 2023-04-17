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
 * +----------------------------------------+
 * | +------------------------------------+ |
 * | | +---+ +--------------------+ +---+ | |
 * | | |   | |                    | |   | | |
 * | | +---+ +--------------------+ +---+ | |
 * | +------------------------------------+ |
 * | +------------------------------------+ |
 * | | +---+ +--------------------+ +---+ | |
 * | | |   | |                    | |   | | |
 * | | |   | |                    | |   | | |
 * | | |   | |                    | |   | | |
 * | | |   | |                    | |   | | |
 * | | |   | |                    | |   | | |
 * | | |   | |                    | |   | | |
 * | | |   | |                    | |   | | |
 * | | |   | |                    | |   | | |
 * | | |   | |                    | |   | | |
 * | | +---+ +--------------------+ +---+ | |
 * | +------------------------------------+ |
 * | +------------------------------------+ |
 * | | +---+ +--------------------+ +---+ | |
 * | | |   | |                    | |   | | |
 * | | +---+ +--------------------+ +---+ | |
 * | +------------------------------------+ |
 * +----------------------------------------+
 * </pre>
 */
public class AroundOntoDropTargetOverlay extends AbstractDropTargetOverlay {
    public static final String OVERLAY_CLASS_NAME = "around-onto-drop-target-overlay";
    public static final String DROP_ZONE_CLASS_NAME = "around-onto-drop-zone";
    public static final String DROP_SPACER_CLASS_NAME = "around-onto-drop-spacer";

    /**
     * Create an {@code AroundOntoDropTargetOverlay} for a component.
     *
     * @param overlaidComponent target component overlaid during drag operation
     * @param aboveConsumer method to handle drag source being dropped on the "above" drop zone
     * @param beforeConsumer method to handle drag source being dropped on the "before" drop zone
     * @param ontoConsumer method to handle drag source upon being dropped onto the "onto" drop zone
     * @param afterConsumer method to handle drag source being dropped on the "after" drop zone
     * @param belowConsumer method to handle drag source being dropped on the "below" drop zone
     */
    private AroundOntoDropTargetOverlay(Component overlaidComponent,
                                        Consumer<Component> aboveConsumer,
                                        Consumer<Component> beforeConsumer,
                                        Consumer<Component> ontoConsumer,
                                        Consumer<Component> afterConsumer,
                                        Consumer<Component> belowConsumer) {
        super(overlaidComponent, OVERLAY_CLASS_NAME);

        var topLeftDiv = new Div();
        topLeftDiv.addClassNames(DROP_SPACER_CLASS_NAME, "top-left-spacer");

        var aboveDiv = new Div();
        aboveDiv.addClassNames(DROP_ZONE_CLASS_NAME, "above-zone");

        var topRightDiv = new Div();
        topRightDiv.addClassNames(DROP_SPACER_CLASS_NAME, "top-right-spacer");

        var top = new FlexLayout();
        top.setWidthFull();
        top.add(topLeftDiv);
        top.add(aboveDiv);
        top.add(topRightDiv);

        var beforeDiv = new Div();
        beforeDiv.addClassNames(DROP_ZONE_CLASS_NAME, "before-zone");

        var ontoDiv = new Div();
        ontoDiv.addClassNames(DROP_ZONE_CLASS_NAME, "onto-zone");

        var afterDiv = new Div();
        afterDiv.addClassNames(DROP_ZONE_CLASS_NAME, "after-zone");

        var middle = new FlexLayout();
        middle.setSizeFull();
        middle.add(beforeDiv);
        middle.add(ontoDiv);
        middle.add(afterDiv);

        var bottomLeftDiv = new Div();
        bottomLeftDiv.addClassNames(DROP_SPACER_CLASS_NAME, "bottom-left-spacer");

        var belowDiv = new Div();
        belowDiv.addClassNames(DROP_ZONE_CLASS_NAME, "below-zone");

        var bottomRightDiv = new Div();
        bottomRightDiv.addClassNames(DROP_SPACER_CLASS_NAME, "bottom-right-spacer");

        var bottom = new FlexLayout();
        bottom.setWidthFull();
        bottom.add(bottomLeftDiv);
        bottom.add(belowDiv);
        bottom.add(bottomRightDiv);

        var overlay = getContent();
        overlay.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        overlay.setSizeFull();
        overlay.add(top);
        overlay.add(middle);
        overlay.add(bottom);

        addDropZone(new DropZone(aboveDiv, aboveConsumer));
        addDropZone(new DropZone(beforeDiv, beforeConsumer));
        addDropZone(new DropZone(ontoDiv, ontoConsumer));
        addDropZone(new DropZone(afterDiv, afterConsumer));
        addDropZone(new DropZone(belowDiv, belowConsumer));
    }

    /**
     * Factory method to create an {@code AroundOntoDropTargetOverlay} for a component.
     *
     * @param overlaidComponent target component overlaid during drag operation
     * @param dragSourceClass class of drag source component to be received by drop target overlay
     * @param dropTargetOverlayVisibilityClassName name of CSS class used to control the drop target overlay's visibility
     * @param aboveConsumer method to handle drag source being dropped on the "above" drop zone
     * @param beforeConsumer method to handle drag source being dropped on the "before" drop zone
     * @param ontoConsumer method to handle drag source upon being dropped onto the "onto" drop zone
     * @param afterConsumer method to handle drag source being dropped on the "after" drop zone
     * @param belowConsumer method to handle drag source being dropped on the "below" drop zone
     * @param <S> Type of component being dragged
     */
    @SuppressWarnings("java:S107")
    public static <S extends Component>
    void initForMoveDrop(Component overlaidComponent, Class<S> dragSourceClass,
                         String dropTargetOverlayVisibilityClassName,
                         Consumer<Component> aboveConsumer,
                         Consumer<Component> beforeConsumer,
                         Consumer<Component> ontoConsumer,
                         Consumer<Component> afterConsumer,
                         Consumer<Component> belowConsumer) {
        var dropTargetOverlay = new AroundOntoDropTargetOverlay(overlaidComponent,
                aboveConsumer, beforeConsumer, ontoConsumer, afterConsumer, belowConsumer);
        dropTargetOverlay.addClassNames(dropTargetOverlayVisibilityClassName);

        overlaidComponent.getStyle().set("position", "relative");
        overlaidComponent.getElement().appendChild(dropTargetOverlay.getElement());

        DragDropKit.initForMoveDrop(dropTargetOverlay, dragSourceClass);
    }
}
