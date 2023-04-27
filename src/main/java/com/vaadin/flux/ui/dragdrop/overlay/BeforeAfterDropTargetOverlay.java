package com.vaadin.flux.ui.dragdrop.overlay;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flux.ui.dragdrop.AbstractDropTargetOverlay;
import com.vaadin.flux.ui.dragdrop.DragDropKit;
import com.vaadin.flux.ui.dragdrop.DropZone;

import java.util.function.Consumer;

/**
 * <pre>
 * +-----------------------------------------------+
 * | +-beforeDiv--------------+ +-afterDiv-------+ |
 * | |                        | |                | |
 * | |                        | |                | |
 * | |                        | |                | |
 * | |                        | |                | |
 * | |                        | |                | |
 * | |                        | |                | |
 * | |                        | |                | |
 * | |                        | |                | |
 * | |                        | |                | |
 * | +------------------------+ +----------------+ |
 * +-----------------------------------------------+
 *
 * </pre>
 */
public class BeforeAfterDropTargetOverlay extends AbstractDropTargetOverlay {
    public static final String OVERLAY_CLASS_NAME = "before-after-drop-target-overlay";
    public static final String DROP_ZONE_CLASS_NAME = "before-after-drop-zone";

    /**
     * Create a {@code BeforeAfterDropTargetOverlay} for a component.
     *
     * @param overlaidComponent target component overlaid during drag operation
     * @param beforeConsumer method to handle drag source being dropped on the "before" drop zone
     * @param afterConsumer method to handle drag source being dropped on the "after" drop zone
     */
    private BeforeAfterDropTargetOverlay(Component overlaidComponent,
                                         Consumer<Component> beforeConsumer,
                                         Consumer<Component> afterConsumer) {
        super(overlaidComponent, OVERLAY_CLASS_NAME);

        var beforeDiv = new Div();
        beforeDiv.addClassNames(DROP_ZONE_CLASS_NAME, "before-zone");
        beforeDiv.setHeightFull();
        beforeDiv.setWidth("60%");

        var afterDiv = new Div();
        afterDiv.addClassNames(DROP_ZONE_CLASS_NAME, "after-zone");
        afterDiv.setHeightFull();
        afterDiv.setWidth("40%");

        var content = getContent();
        content.setSizeFull();
        content.add(beforeDiv);
        content.add(afterDiv);

        addDropZone(new DropZone(beforeDiv, beforeConsumer));
        addDropZone(new DropZone(afterDiv, afterConsumer));
    }

    /**
     * Factory method to create a {@code BeforeAfterDropTargetOverlay} for a component.
     *
     * @param overlaidComponent target component overlaid during drag operation
     * @param dragSourceClass class of drag source component to be received by drop target overlay
     * @param dropTargetOverlayVisibilityClassName name of CSS class used to control the drop target overlay's visibility
     * @param beforeConsumer method to handle drag source being dropped on the "before" drop zone
     * @param afterConsumer method to handle drag source being dropped on the "after" drop zone
     * @param <S> Type of component being dragged
     */
    public static <S extends Component>
    void initForMoveDrop(Component overlaidComponent, Class<S> dragSourceClass,
                         String dropTargetOverlayVisibilityClassName,
                         Consumer<Component> beforeConsumer,
                         Consumer<Component> afterConsumer) {
        var dropTargetOverlay = new BeforeAfterDropTargetOverlay(overlaidComponent,
                beforeConsumer, afterConsumer);
        dropTargetOverlay.addClassNames(dropTargetOverlayVisibilityClassName);

        overlaidComponent.getStyle().set("position", "relative");
        overlaidComponent.getElement().appendChild(dropTargetOverlay.getElement());

        DragDropKit.initForMoveDrop(dropTargetOverlay, dragSourceClass);
    }
}
