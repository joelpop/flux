package com.vaadin.flux.ui.dragdrop;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.dnd.*;
import com.vaadin.flux.util.DomUtil;
import com.vaadin.flux.util.LibraryClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public final class DragDropKit extends LibraryClass {
    private static final Logger LOG = LoggerFactory.getLogger(DragDropKit.class);

    public static final String OVERLAY_VISIBILITY_PROPERTY = "--overlay-visibility";


    private DragDropKit() {
        // utility class - do not instantiate
    }


    // DRAG

    /**
     * Initializes a component to be dragged for moving to a drop target component with an overlay.
     * <p>
     * Names the CSS classes of the drop targets' overlays used to control their visibility.
     * When a drag operation starts, the drop targets' overlays are made visible, and
     * when the drag operation ends, they are re-hidden.
     * </p>
     *
     * @param dragComponent Component to be made draggable.
     * @param dropTargetOverlayVisibilityClassNames names of drop target classes used to control their overlays' visibility.
     */
    public static void initForMoveDrag(Component dragComponent, String... dropTargetOverlayVisibilityClassNames) {
        var dragSourceComponent = DragSource.create(dragComponent);
        dragSourceComponent.setEffectAllowed(EffectAllowed.MOVE);
        dragSourceComponent.addDragStartListener(event ->
                Arrays.stream(dropTargetOverlayVisibilityClassNames)
                        .forEach(dropTargetOverlayVisibilityClassName ->
                                DomUtil.setClassProperty(dropTargetOverlayVisibilityClassName,
                                        OVERLAY_VISIBILITY_PROPERTY, "visible")));
        dragSourceComponent.addDragEndListener(event ->
                Arrays.stream(dropTargetOverlayVisibilityClassNames)
                        .forEach(dropTargetOverlayVisibilityClassName ->
                                DomUtil.setClassProperty(dropTargetOverlayVisibilityClassName,
                                        OVERLAY_VISIBILITY_PROPERTY, "hidden")));

        if (LOG.isDebugEnabled()) {
            dragSourceComponent.addDragStartListener(event ->
                    onDebugDragStartWithOverlay(event, dropTargetOverlayVisibilityClassNames));
            dragSourceComponent.addDragEndListener(event ->
                    onDebugDragEndWithOverlay(event, dropTargetOverlayVisibilityClassNames));
        }
    }

    // debugging event handler
    @SuppressWarnings("java:S2629")
    private static <S extends Component>
    void onDebugDragStartWithOverlay(DragStartEvent<S> event, String... dropTargetOverlayVisibilityClassNames) {
        LOG.debug("Drag Start: {}{}...",
                event.getComponent(),
                Arrays.stream(dropTargetOverlayVisibilityClassNames)
                        .map(".%s {visibility: visible;}"::formatted)
                        .collect(Collectors.joining(" ", " [", "]")));
    }

    // debugging event handler
    @SuppressWarnings("java:S2629")
    private static <S extends Component>
    void onDebugDragEndWithOverlay(DragEndEvent<S> event, String... dropTargetOverlayVisibilityClassNames) {
        LOG.debug("Drag End: {} {} ({}){}",
                event.getComponent(),
                event.isSuccessful() ? "->" : "-X",
                event.getDropEffect(),
                Arrays.stream(dropTargetOverlayVisibilityClassNames)
                        .map(".%s {visibility: hidden;}"::formatted)
                        .collect(Collectors.joining(" ", " [", "]")));
    }


    // DROP (for Components)

    /**
     * Initializes a component to be a drop target for moving.
     * <p>
     * The drag source's {@code removeFromParent} method is used to remove it from its parent component.
     * The drop target's {@code add} method is used to add the dragged component to the drop target.
     * </p>
     *
     * @param dropComponent Component to be made receptive of drop.
     * @param dragSourceClass Class of component being dragged.
     * @param <T> Type of component receiving the drop.
     * @param <S> Type of component being dragged.
     */
    public static <T extends Component & HasComponents, S extends Component>
    void initForMoveDrop(T dropComponent, Class<S> dragSourceClass) {
        initForMoveDrop(dropComponent, dragSourceClass, T::add);
    }

    /**
     * Initializes a component to be a drop target for moving.
     * <p>
     * The drag source's {@code removeFromParent} method is used to remove it from its parent component.
     * The supplied {@code add} method is used to add the dragged component to the drop target.
     * </p>
     *
     * @param dropComponent Component to be made receptive of drop.
     * @param dragSourceClass Class of component being dragged.
     * @param consumeDrop Method for handling dragged component by drop component.
     * @param <T> Type of component receiving the drop.
     * @param <S> Type of component being dragged.
     */
    public static <T extends Component, S extends Component>
    void initForMoveDrop(T dropComponent, Class<S> dragSourceClass, BiConsumer<T, S> consumeDrop) {
        var dropTarget = DropTarget.create(dropComponent);
        dropTarget.setDropEffect(DropEffect.MOVE);
        dropTarget.addDropListener(event -> onMoveDrop(event, dragSourceClass, consumeDrop));
    }

    /**
     * Initializes a component to be a drop target for moving.
     * <p>
     * The drag source's {@code removeFromParent} method is used to remove it from its parent component
     * if autoremove is {@code true}.
     * The supplied {@code add} method is used to add the dragged component to the drop target.
     * </p>
     *
     * @param dropComponent Component to be made receptive of drop.
     * @param dragSourceClass Class of component being dragged.
     * @param consumeDrop Method for handling dragged component by drop component.
     * @param autoRemove Autoremove dragged component if true, leave it if false.
     * @param <T> Type of component receiving the drop.
     * @param <S> Type of component being dragged.
     */
    public static <T extends Component, S extends Component>
    void initForMoveDrop(T dropComponent, Class<S> dragSourceClass, BiConsumer<T, S> consumeDrop, boolean autoRemove) {
        var dropTarget = DropTarget.create(dropComponent);
        dropTarget.setDropEffect(DropEffect.MOVE);
        dropTarget.addDropListener(event -> onMoveDrop(event, dragSourceClass, consumeDrop, autoRemove));
    }

//    private static <T extends Component & HasComponents, S extends Component>
//    void onMoveDrop(DropEvent<T> event, Class<S> dragSourceClass) {
//        onMoveDrop(event, dragSourceClass, T::add);
//    }

    private static <T extends Component, S extends Component>
    void onMoveDrop(DropEvent<T> event, Class<S> dragSourceClass, BiConsumer<T, S> addTo) {
        onMoveDrop(event, dragSourceClass, addTo, true);
    }

    private static <T extends Component, S extends Component>
    void onMoveDrop(DropEvent<T> event, Class<S> dragSourceClass, BiConsumer<T, S> addTo, boolean autoRemove) {
        var dragSourceComponentOpt = event.getDragSourceComponent();
        var dropTargetComponent = event.getComponent();

        if (LOG.isDebugEnabled()) {
            LOG.debug("Drop: {} -> {} ({})",
                    dragSourceComponentOpt.map(Object::toString).orElse("?"),
                    dropTargetComponent,
                    event.getDropEffect());
        }

        dragSourceComponentOpt
                // skip move operation if dragging onto self directly
                .filter(dragComponent -> !Objects.equals(dragComponent, dropTargetComponent))
                // skip move operation if dragging onto self via drop zone of own overlay
                .filter(dragComponent -> !DropTargetOverlay.isOverlaying(dropTargetComponent, dragComponent))
                // perform move operation
                .ifPresent(dragComponent -> {
                    if (event.getDropEffect() == DropEffect.MOVE) {
                        dragSourceComponentOpt
                                .filter(dragSourceClass::isInstance)
                                .map(dragSourceClass::cast)
                                .ifPresent(dragSourceComponent -> {
                                    if (autoRemove) {
                                        dragSourceComponent.removeFromParent();
                                    }
                                    addTo.accept(dropTargetComponent, dragSourceComponent);
                                });
                    }
                });
    }


    // DROP (for DropTargetOverlays)

    /**
     *
     * @param dropTargetOverlay          Overlay component containing zones receptive of drops.
     * @param dragSourceClass            Class of component being dragged.
     * @param <S>                        Type of component being dragged.
     */
    public static <S extends Component>
    void initForMoveDrop(DropTargetOverlay dropTargetOverlay, Class<S> dragSourceClass) {
        dropTargetOverlay.getDropZones().forEach(dropZone ->
                initForMoveDrop(dropZone.getDropComponent(), dragSourceClass,
                        (component, s) -> dropZone.receiveDrop(s)));
    }
}
