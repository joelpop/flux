package com.vaadin.flux.ui.dragdrop.overlay;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flux.ui.dragdrop.AbstractDropTargetOverlay;
import com.vaadin.flux.ui.dragdrop.DragDropKit;
import com.vaadin.flux.ui.dragdrop.DropZone;

import java.util.function.Consumer;

/**
 * <pre>
 * wide                     tall
 * +--------------------+   +-----------+
 * |\                  /|   |\         /|
 * | \                / |   | \       / |
 * |  \              /  |   |  \     /  |
 * |   \            /   |   |   \   /   |
 * |    \__________/    |   |    \ /    |
 * |    /          \    |   |     |     |
 * |   /            \   |   |     |     |
 * |  /              \  |   |     |     |
 * | /                \ |   |     |     |
 * |/                  \|   |     |     |
 * +--------------------+   |    / \    |
 *                          |   /   \   |
 *                          |  /     \  |
 *                          | /       \ |
 *                          |/         \|
 *                          +-----------+
 * </pre>
 */
public class AroundDropTargetOverlay extends AbstractDropTargetOverlay {
    public static final String OVERLAY_CLASS_NAME = "around-drop-target-overlay";
    public static final String DROP_ZONE_CLASS_NAME = "around-drop-zone";

    /**
     * Create an {@code AroundDropTargetOverlay} for a component.
     *
     * @param overlaidComponent target component overlaid during drag operation
     * @param beforeConsumer method to handle drag source being dropped on the "before" drop zone
     * @param aboveConsumer method to handle drag source being dropped on the "above" drop zone
     * @param belowConsumer method to handle drag source being dropped on the "below" drop zone
     * @param afterConsumer method to handle drag source being dropped on the "after" drop zone
     */
    private AroundDropTargetOverlay(Component overlaidComponent,
                                    Consumer<Component> beforeConsumer,
                                    Consumer<Component> aboveConsumer,
                                    Consumer<Component> belowConsumer,
                                    Consumer<Component> afterConsumer) {
        super(overlaidComponent, OVERLAY_CLASS_NAME);

        var beforeDiv = new Div();
        beforeDiv.addClassNames(DROP_ZONE_CLASS_NAME, "before-zone");

        var aboveDiv = new Div();
        aboveDiv.addClassNames(DROP_ZONE_CLASS_NAME, "above-zone");

        var belowDiv = new Div();
        belowDiv.addClassNames(DROP_ZONE_CLASS_NAME, "below-zone");

        var afterDiv = new Div();
        afterDiv.addClassNames(DROP_ZONE_CLASS_NAME, "after-zone");

        var content = getContent();
        content.setSizeFull();
        content.add(beforeDiv);
        content.add(aboveDiv);
        content.add(belowDiv);
        content.add(afterDiv);

        addDropZone(new DropZone(beforeDiv, beforeConsumer));
        addDropZone(new DropZone(aboveDiv, aboveConsumer));
        addDropZone(new DropZone(belowDiv, belowConsumer));
        addDropZone(new DropZone(afterDiv, afterConsumer));

        addAttachListener(this::onAroundDropTargetOverlayAttach);
    }

    private void onAroundDropTargetOverlayAttach(AttachEvent attachEvent) {
        attachEvent.getUI().getPage().executeJs("""
                  $0.style.setProperty('--around-overlay-min-dim', '100');
                  new ResizeObserver(entries => {
                    for (const entry of entries) {
                      const { width, height } = entry.contentRect;
                      console.log('>>>>> ' + $0.parentElement.tagName + ': ' + width + 'x' + height);
                      entry.target.style.setProperty('--around-overlay-min-dim', Math.min(width, height));
                    }
                  }).observe($0);
                """, this.getElement());
        // TODO call the above when attaching, not just when resizing?
    }

    /**
     * Factory method to create an {@code AroundDropTargetOverlay} for a component.
     *
     * @param overlaidComponent target component overlaid during drag operation
     * @param dragSourceClass class of drag source component to be received by drop target overlay
     * @param dropTargetOverlayVisibilityClassName name of CSS class used to control the drop target overlay's visibility
     * @param beforeConsumer method to handle drag source being dropped on the "before" drop zone
     * @param aboveConsumer method to handle drag source being dropped on the "above" drop zone
     * @param belowConsumer method to handle drag source being dropped on the "below" drop zone
     * @param afterConsumer method to handle drag source being dropped on the "after" drop zone
     * @param <S> Type of component being dragged
     */
    @SuppressWarnings("java:S107")
    public static <S extends Component>
    void initForMoveDrop(Component overlaidComponent, Class<S> dragSourceClass,
                         String dropTargetOverlayVisibilityClassName,
                         Consumer<Component> beforeConsumer,
                         Consumer<Component> aboveConsumer,
                         Consumer<Component> belowConsumer,
                         Consumer<Component> afterConsumer) {
        var dropTargetOverlay = new AroundDropTargetOverlay(overlaidComponent,
                beforeConsumer, aboveConsumer, belowConsumer, afterConsumer);
        dropTargetOverlay.addClassNames(dropTargetOverlayVisibilityClassName);

        overlaidComponent.getStyle().set("position", "relative");
        overlaidComponent.getElement().appendChild(dropTargetOverlay.getElement());

        DragDropKit.initForMoveDrop(dropTargetOverlay, dragSourceClass);
    }
}
