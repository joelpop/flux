package com.vaadin.flux.ui.dragdrop.overlay;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flux.ui.dragdrop.AbstractDropTargetOverlay;
import com.vaadin.flux.ui.dragdrop.DragDropKit;
import com.vaadin.flux.ui.dragdrop.DropTargetOverlay;
import com.vaadin.flux.ui.dragdrop.DropZone;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

/**
 * <pre>
 * +-----------------------------------------------+
 * |\                                             /|
 * | \                                           / |
 * |  \                                         /  |
 * |   \                                       /   |
 * |    \_____________________________________/    |
 * |    /                                     \    |
 * |   /                                       \   |
 * |  /                                         \  |
 * | /                                           \ |
 * |/                                             \|
 * +-----------------------------------------------+
 * </pre>
 */
@Tag("around-drop-target-overlay")
@JsModule("./src/around-drop-target-overlay.ts")
public class AroundDropTargetOverlay extends LitTemplate implements DropTargetOverlay {
    private final Component overlaidComponent;
    private final transient Set<DropZone> dropZones;

    /**
     * Create an {@code AroundDropTargetOverlay} for a component.
     *
     * @param overlaidComponent target component overlaid during drag operation
     * @param aboveConsumer method to handle drag source being dropped on the "above" drop zone
     * @param beforeConsumer method to handle drag source being dropped on the "before" drop zone
     * @param afterConsumer method to handle drag source being dropped on the "after" drop zone
     * @param belowConsumer method to handle drag source being dropped on the "below" drop zone
     */
    private AroundDropTargetOverlay(Component overlaidComponent,
                                    Consumer<Component> aboveConsumer,
                                    Consumer<Component> beforeConsumer,
                                    Consumer<Component> afterConsumer,
                                    Consumer<Component> belowConsumer) {
        this.overlaidComponent = overlaidComponent;
        this.dropZones = new HashSet<>();

//        addDropZone(new DropZone(aboveDiv, aboveConsumer));
//        addDropZone(new DropZone(beforeDiv, beforeConsumer));
//        addDropZone(new DropZone(afterDiv, afterConsumer));
//        addDropZone(new DropZone(belowDiv, belowConsumer));
    }

    /**
     * Factory method to create an {@code AroundDropTargetOverlay} for a component.
     *
     * @param overlaidComponent target component overlaid during drag operation
     * @param dragSourceClass class of drag source component to be received by drop target overlay
     * @param dropTargetOverlayVisibilityClassName name of CSS class used to control the drop target overlay's visibility
     * @param aboveConsumer method to handle drag source being dropped on the "above" drop zone
     * @param beforeConsumer method to handle drag source being dropped on the "before" drop zone
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
                         Consumer<Component> afterConsumer,
                         Consumer<Component> belowConsumer) {
        var dropTargetOverlay = new AroundDropTargetOverlay(overlaidComponent,
                aboveConsumer, beforeConsumer, afterConsumer, belowConsumer);
        dropTargetOverlay.addClassNames(dropTargetOverlayVisibilityClassName);

        overlaidComponent.getStyle().set("position", "relative");
        overlaidComponent.getElement().appendChild(dropTargetOverlay.getElement());

        DragDropKit.initForMoveDrop(dropTargetOverlay, dragSourceClass);
    }

    @Override
    public boolean isOverlaying(Component component) {
        return Objects.equals(component, overlaidComponent);
    }

    @Override
    public Set<DropZone> getDropZones() {
        return Collections.unmodifiableSet(dropZones);
    }

    @Override
    public void addDropZone(DropZone dropZone) {
        dropZones.add(dropZone);
    }
}
