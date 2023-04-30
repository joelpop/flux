package com.vaadin.flux.ui.component.editor.visual.canvas;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class ContainerCanvasItem extends CanvasItem<FlexLayout> implements CanvasItemParent {

    private final FlexLayout content;

    public ContainerCanvasItem(FlexLayout.FlexDirection flexDirection,
                               CanvasItem<?> primaryCanvasItem, CanvasItem<?> secondaryCanvasItem,
                               boolean after) {
        content = getContent();
        content.setFlexDirection(flexDirection);
        content.add(primaryCanvasItem);
        content.addComponentAtIndex(after ? 1 : 0, secondaryCanvasItem);


        content.addClassNames(LumoUtility.Margin.SMALL,
                LumoUtility.Border.ALL,
                LumoUtility.BorderColor.CONTRAST_10,
                LumoUtility.BorderRadius.MEDIUM,
                LumoUtility.Padding.SMALL,
                LumoUtility.Gap.MEDIUM);
    }

    @Override
    public void addBefore(CanvasItem<?> existingCanvasItem, CanvasItem<?> newCanvasItem) {
        smartAdd(existingCanvasItem, newCanvasItem, FlexLayout.FlexDirection.ROW, false);
    }

    @Override
    public void addAbove(CanvasItem<?> existingCanvasItem, CanvasItem<?> newCanvasItem) {
        smartAdd(existingCanvasItem, newCanvasItem, FlexLayout.FlexDirection.COLUMN, false);
    }

    @Override
    public void addBelow(CanvasItem<?> existingCanvasItem, CanvasItem<?> newCanvasItem) {
        smartAdd(existingCanvasItem, newCanvasItem, FlexLayout.FlexDirection.COLUMN, true);
    }

    @Override
    public void addAfter(CanvasItem<?> existingCanvasItem, CanvasItem<?> newCanvasItem) {
        smartAdd(existingCanvasItem, newCanvasItem, FlexLayout.FlexDirection.ROW, true);
    }

    private void smartAdd(CanvasItem<?> existingCanvasItem, CanvasItem<?> newCanvasItem,
                          FlexLayout.FlexDirection desiredFlexDirection, boolean after) {
        var existingIndex = content.indexOf(existingCanvasItem);
        if (content.getFlexDirection() == desiredFlexDirection) {
            content.addComponentAtIndex(existingIndex + (after ? 1 : 0), newCanvasItem);
        }
        else {
            content.remove(existingCanvasItem);

            var containerCanvasItem = new ContainerCanvasItem(desiredFlexDirection,
                    existingCanvasItem, newCanvasItem, after);

            content.addComponentAtIndex(existingIndex, containerCanvasItem);
        }
    }

    @Override
    public void adjustParentage() {
        // if i have only one child...
        if (content.getChildren().count() == 1) {
            // get my only child
            content.getChildren()
                    .filter(CanvasItem.class::isInstance)
                    .map(CanvasItem.class::cast)
                    .findFirst()
                    .ifPresent(onlyChild -> {
                        // get my parent
                        var myCanvasItemParent = getCanvasItemParent();

                        // get my index among my siblings in my parent
                        var siblingIndex = myCanvasItemParent.indexOf(this);

                        // remove me from my parent
                        removeFromParent();

                        // add my child to my parent in my former index among my siblings
                        myCanvasItemParent.addComponentAtIndex(siblingIndex, onlyChild);
                    });
        }
    }

    @Override
    public int indexOf(Component component) {
        return content.indexOf(component);
    }

    @Override
    public void addComponentAtIndex(int index, Component component) {
        content.addComponentAtIndex(index, component);
    }
}
