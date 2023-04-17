package com.vaadin.flux.ui.dragdrop;

import com.vaadin.flow.component.Component;

import java.util.function.Consumer;

public class DropZone {
    private final Component dropComponent;
    private final Consumer<Component> dropConsumer;

    public DropZone(Component dropComponent, Consumer<Component> dropConsumer) {
        this.dropComponent = dropComponent;
        this.dropConsumer = dropConsumer;
    }

    public Component getDropComponent() {
        return dropComponent;
    }

    void receiveDrop(Component component) {
        dropConsumer.accept(component);
    }
}
