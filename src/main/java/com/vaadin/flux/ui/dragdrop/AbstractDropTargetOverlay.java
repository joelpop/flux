package com.vaadin.flux.ui.dragdrop;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.FlexLayout;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class AbstractDropTargetOverlay extends Composite<FlexLayout> implements DropTargetOverlay {

    private final Component overlaidComponent;
    private final transient Set<DropZone> dropZones;

    protected AbstractDropTargetOverlay(Component overlaidComponent) {
        this.overlaidComponent = overlaidComponent;
        this.dropZones = new HashSet<>();
    }

    protected AbstractDropTargetOverlay(Component overlaidComponent, String overlayClassName) {
        this(overlaidComponent);

        addClassNames(overlayClassName);
    }

    @Override
    public boolean isOverlaying(Component component) {
        return Objects.equals(component, overlaidComponent);
    }

    @Override
    public Set<DropZone> getDropZones() {
        return Collections.unmodifiableSet(dropZones);
    }

    protected void addDropZone(DropZone dropZone) {
        dropZones.add(dropZone);
    }
}
