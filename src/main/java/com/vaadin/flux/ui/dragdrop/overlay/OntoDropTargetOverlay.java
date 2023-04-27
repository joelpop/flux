package com.vaadin.flux.ui.dragdrop.overlay;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flux.ui.dragdrop.AbstractDropTargetOverlay;
import com.vaadin.flux.ui.dragdrop.DragDropKit;
import com.vaadin.flux.ui.dragdrop.DropZone;

import java.util.function.Consumer;

/**
 * <pre>
 * +------------------------+
 * | +-ontoDiv------------+ |
 * | |                    | |
 * | |                    | |
 * | |                    | |
 * | |                    | |
 * | |                    | |
 * | |                    | |
 * | |                    | |
 * | |                    | |
 * | |                    | |
 * | |                    | |
 * | +--------------------+ |
 * +------------------------+
 * </pre>
 */
public class OntoDropTargetOverlay extends AbstractDropTargetOverlay {
    public static final String OVERLAY_CLASS_NAME = "onto-drop-target-overlay";
    public static final String DROP_ZONE_CLASS_NAME = "onto-drop-zone";

    /**
     * Create an {@code OntoDropTargetOverlay} for a component.
     *
     * @param overlaidComponent target component overlaid during drag operation
     * @param ontoConsumer method to handle drag source upon being dropped onto the "onto" drop zone
     */
    private OntoDropTargetOverlay(Component overlaidComponent, Consumer<Component> ontoConsumer) {
        super(overlaidComponent, OVERLAY_CLASS_NAME);

        var ontoDiv = new Div();
        ontoDiv.addClassNames(DROP_ZONE_CLASS_NAME, "onto-zone");
        ontoDiv.setSizeFull();

        var content = getContent();
        content.setSizeFull();
        content.add(ontoDiv);

        addDropZone(new DropZone(ontoDiv, ontoConsumer));
    }

    /**
     * Factory method to create an {@code OntoDropTargetOverlay} for a component.
     *
     * @param overlaidComponent target component overlaid during drag operation
     * @param dragSourceClass class of drag source component to be received by drop target overlay
     * @param dropTargetOverlayVisibilityClassName name of CSS class used to control the drop target overlay's visibility
     * @param ontoConsumer method to handle drag source upon being dropped onto the "onto" drop zone
     * @param <S> Type of component being dragged
     */
    public static <S extends Component>
    void initForMoveDrop(Component overlaidComponent, Class<S> dragSourceClass,
                         String dropTargetOverlayVisibilityClassName,
                         Consumer<Component> ontoConsumer) {
        var dropTargetOverlay = new OntoDropTargetOverlay(overlaidComponent, ontoConsumer);
        dropTargetOverlay.addClassNames(dropTargetOverlayVisibilityClassName);

        overlaidComponent.getStyle().set("position", "relative");
        overlaidComponent.getElement().appendChild(dropTargetOverlay.getElement());

        DragDropKit.initForMoveDrop(dropTargetOverlay, dragSourceClass);
    }
}
