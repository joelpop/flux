package com.vaadin.flux.ui.dragdrop;

import com.vaadin.flow.component.Component;

import java.util.Set;

public interface DropTargetOverlay {

    /**
     * Determines if this drop target overlay is overlaying the supplied component.
     * <p>
     * You can use this to check to see if a drop is being attempted
     * by a drag source component onto its own overlay.
     * </p>
     *
     * @param component a component that could be overlaid by this drop target overlay
     * @return {@code true}, if this drop target overlay is overlaying the component;
     *         {@code false}, otherwise
     */
    boolean isOverlaying(Component component);

    /**
     * Return the drop zones of this drop target overlay.
     *
     * @return the drop zones of this drop target overlay
     */
    Set<DropZone> getDropZones();

    /**
     * Add a drop zone to this drop target overlay.
     *
     * @param dropZone the drop zone to add to this drop target overlay
     */
    void addDropZone(DropZone dropZone);


    /**
     * Determines if dropTargetComponent is a drop zone or a drop target overlay
     * of a drag source component.
     *
     * @param dropTargetComponent a component that might be a drop zone or a drop target overlay
     * @param dragSourceComponent a component that could be overlaid by the drop target component
     * @return {@code true}, if this drop target component is a drop zone or a drop target overlay
     * that is overlaying the component;
     *         {@code false}, otherwise
     */
    static boolean isOverlaying(Component dropTargetComponent, Component dragSourceComponent) {
        var dropTargetOverlay = (dropTargetComponent instanceof DropTargetOverlay dto) ?
                dto :
                dropTargetComponent.findAncestor(DropTargetOverlay.class);

        if (dropTargetOverlay == null) {
            // dropTargetComponent is not even a drop target overlay, so no, not overlaying
            return false;
        }

        return dropTargetOverlay.isOverlaying(dragSourceComponent);
    }
}
