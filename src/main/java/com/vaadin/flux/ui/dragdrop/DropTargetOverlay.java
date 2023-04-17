package com.vaadin.flux.ui.dragdrop;

import com.vaadin.flow.component.Component;

import java.util.Set;

public interface DropTargetOverlay {

    boolean isOverlaying(Component component);

    Set<DropZone> getDropZones();
}
