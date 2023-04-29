package com.vaadin.flux.ui.component.toolgroup;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flux.ui.component.closetab.CloseTab;
import com.vaadin.flux.ui.component.closetab.CloseTabs;
import com.vaadin.flux.ui.component.split.Split;
import com.vaadin.flux.ui.component.split.SplitItem;
import com.vaadin.flux.ui.dragdrop.DragDropKit;
import com.vaadin.flux.ui.dragdrop.overlay.AroundDropTargetOverlay;
import com.vaadin.flux.ui.dragdrop.overlay.BeforeAfterDropTargetOverlay;
import com.vaadin.flux.ui.dragdrop.overlay.OntoDropTargetOverlay;

import java.util.Arrays;

/**
 *
 * <pre>
 * +-content(FlexLayout)------------------------------------------------------------------------+
 * | +-dragBar--------------------------------------------------------------------------------+ |
 * | | +-closeTabs---------------------------------+            +-iconBar(FlexLayout)-------+ | |
 * | | |   ____________ ____________ ____________  |            | +-actionBar-+ +-zoomBar-+ | | |
 * | | |  / (x) label  \ (x) label  \ (x) label  \ |            | | @ # $ %   | |  #   -  | | | |
 * | | | +-------------+------------+------------+ |            | +-----------+ +---------+ | | |
 * | | +-------------------------------------------+            +---------------------------+ | |
 * | +----------------------------------------------------------------------------------------+ |
 * | +-toolContent(Div)-----------------------------------------------------------------------+ |
 * | |                                                                                        | |
 * | |                                                                                        | |
 * | |                                                                                        | |
 * | |                                                                                        | |
 * | |                                                                                        | |
 * | |                                                                                        | |
 * | |                                                                                        | |
 * | |                                                                                        | |
 * | +----------------------------------------------------------------------------------------+ |
 * +--------------------------------------------------------------------------------------------+
 * </pre>
 */
public class ToolGroup extends SplitItem<FlexLayout> implements HasSize {
    private static final String VISIBILITY_CLASS_NAME = "onto-drop-target-toolgroup";

    private final CloseTabs closeTabs;
    private final FlexLayout actionBar;
    private final FlexLayout toolContent;

    public ToolGroup() {
        super();

        closeTabs = new CloseTabs();
        closeTabs.setWidthFull();
        closeTabs.addSelectedChangeListener(this::onSelectedTabChange);

        actionBar = new FlexLayout();

        var maximizeButton = new Button(VaadinIcon.EXPAND_FULL.create());
        maximizeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_SMALL);

        var minimizeButton = new Button(VaadinIcon.COMPRESS.create());
        minimizeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_SMALL);

        var zoomBar = new FlexLayout();
        zoomBar.add(maximizeButton);
        zoomBar.add(minimizeButton);

        var iconBar = new FlexLayout();
        iconBar.add(actionBar);
        iconBar.add(zoomBar);

        var dragBar = new DragBar();
        dragBar.setWidthFull();
        dragBar.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        dragBar.add(closeTabs);
        dragBar.add(iconBar);

        toolContent = new FlexLayout();
        toolContent.setSizeFull();

        var content = getContent();
        content.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        content.add(dragBar);
        content.add(toolContent);


        AroundDropTargetOverlay.initForMoveDrop(this,
                DragBar.class, VISIBILITY_CLASS_NAME,
                this::splitLeftward,
                this::splitUpward,
                this::splitDownward,
                this::splitRightward);
    }

    public ToolGroup(Tool... tools) {
        this();
        add(tools);
    }

    private void onSelectedTabChange(Tabs.SelectedChangeEvent event) {
        actionBar.removeAll();
        toolContent.removeAll();
        if (event.getSelectedTab() instanceof ToolTab toolTab) {
            var tool = toolTab.getTool();
            tool.getToolActions().forEach(toolTab::add);
            toolContent.add(tool);
        }
    }

    public void add(Tool... tools) {
        Arrays.stream(tools)
                .map(ToolTab::new)
                .forEach(closeTabs::add);
    }

    private void splitLeftward(Component component) {
        // TODO requires implementation of "drag proxies" so a ToolGroup will be provided here
    }

    private void splitUpward(Component component) {
        // TODO requires implementation of "drag proxies" so a ToolGroup will be provided here
    }

    private void splitDownward(Component component) {
        // TODO requires implementation of "drag proxies" so a ToolGroup will be provided here
    }

    private void splitRightward(Component component) {
        // TODO requires implementation of "drag proxies" so a ToolGroup will be provided here
    }

    private void split(ToolGroup toolGroup) {
        getParent().ifPresent(System.out::println);
        getParent()
                .filter(Split.class::isInstance)
                .map(Split.class::cast)
                .ifPresent(parentSplit -> {
                    System.out.println("Found Parent Split");
//                    parentSplit.replaceSplitItem(this, splitItem);
//                            var splitItem = splitRightward(toolGroup);

                });
    }


    private class DragBar extends FlexLayout {
        private static final String VISIBILITY_CLASS_NAME = "onto-drop-target-toolbar";

        public DragBar() {
            // TODO implement "drag proxies" so entire ToolGroup is moved, not just its DragBar
//            DragDropKit.initForMoveDrag(this, ToolGroup.this, ToolGroup.VISIBILITY_CLASS_NAME);
            DragDropKit.initForMoveDrag(this, ToolGroup.VISIBILITY_CLASS_NAME);
            OntoDropTargetOverlay.initForMoveDrop(this, ToolTab.class,
                    VISIBILITY_CLASS_NAME, this::addOnto);
        }

        private void addOnto(Component component) {
            if (component instanceof ToolTab toolTab) {
                ToolGroup.this.closeTabs.add(toolTab);
            }
        }
    }

    private class ToolTab extends CloseTab {
        private static final String VISIBILITY_CLASS_NAME = "before-after-drop-target-visibility-tooltab";

        private final Tool tool;

        public ToolTab(Tool tool) {
            super(tool.getName());
            this.tool = tool;

            DragDropKit.initForMoveDrag(this, VISIBILITY_CLASS_NAME);
            DragDropKit.initForMoveDrag(this, DragBar.VISIBILITY_CLASS_NAME);
            BeforeAfterDropTargetOverlay.initForMoveDrop(this, ToolTab.class,
                    VISIBILITY_CLASS_NAME, this::addBefore, this::addAfter);
        }

        public Tool getTool() {
            return tool;
        }

        private void addBefore(Component component) {
            if (component instanceof ToolTab toolTab) {
                System.out.println("INDEX: " + closeTabs.indexOf(this));
                System.out.println("Insert " + toolTab.tool.getName() + " before " + tool.getName());
                var selected = toolTab.isSelected();
                closeTabs.addTabAtIndex(closeTabs.indexOf(this), toolTab);
                if (selected) {
                    closeTabs.setSelectedTab(toolTab);
                }
            }
        }

        private void addAfter(Component component) {
            if (component instanceof ToolTab toolTab) {
                System.out.println("INDEX: " + closeTabs.indexOf(this) + 1);
                System.out.println("Insert " + toolTab.tool.getName() + " after " + tool.getName());
                var selected = toolTab.isSelected();
                closeTabs.addTabAtIndex(closeTabs.indexOf(this) + 1, toolTab);
                if (selected) {
                    closeTabs.setSelectedTab(toolTab);
                }
            }
        }
    }
}
