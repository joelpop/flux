package com.vaadin.flux.ui.component.editorgroup;


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
 * | +-editorContent(Div)---------------------------------------------------------------------+ |
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
public class EditorGroup extends Composite<FlexLayout> implements SplitItem, HasSize {
    private final CloseTabs closeTabs;
    private final FlexLayout actionBar;
    private final Div editorContent;

    public EditorGroup() {
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

        editorContent = new Div();

        var content = getContent();
        content.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        content.add(dragBar);
        content.add(editorContent);
    }

    public EditorGroup(Editor... editors) {
        this();
        add(editors);
    }

    private void onSelectedTabChange(Tabs.SelectedChangeEvent event) {
        actionBar.removeAll();
        editorContent.removeAll();
        if (event.getSelectedTab() instanceof EditorTab editorTab) {
            var editor = editorTab.getEditor();
            editor.getEditorActions().forEach(editorTab::add);
            editorContent.add(editor);
        }
    }

    public void add(Editor... editors) {
        Arrays.stream(editors)
                .map(EditorTab::new)
                .forEach(closeTabs::add);
    }

    private void split(EditorGroup editorGroup) {
        getParent().ifPresent(System.out::println);
        getParent()
                .filter(Split.class::isInstance)
                .map(Split.class::cast)
                .ifPresent(parentSplit -> {
                    System.out.println("Found Parent Split");
//                    parentSplit.replaceSplitItem(this, splitItem);
//                            var splitItem = splitRightward(editorGroup);

                });
    }


    private class DragBar extends FlexLayout {
        private static final String VISIBILITY_CLASS_NAME = "onto-drop-target-editorbar";

        public DragBar() {
            OntoDropTargetOverlay.initForMoveDrop(this, EditorTab.class,
                    VISIBILITY_CLASS_NAME, this::addTab);
        }

        private void addTab(Component component) {
            if (component instanceof EditorTab editorTab) {
                EditorGroup.this.closeTabs.add(editorTab);
            }
        }
    }

    private class EditorTab extends CloseTab {
        private static final String VISIBILITY_CLASS_NAME = "before-after-drop-target-visibility-editortab";

        private final Editor editor;

        public EditorTab(Editor editor) {
            super(editor.getName());
            this.editor = editor;

            DragDropKit.initForMoveDrag(this, VISIBILITY_CLASS_NAME);
            DragDropKit.initForMoveDrag(this, DragBar.VISIBILITY_CLASS_NAME);
            BeforeAfterDropTargetOverlay.initForMoveDrop(this, EditorTab.class,
                    VISIBILITY_CLASS_NAME, this::insertBefore, this::insertAfter);
        }

        public Editor getEditor() {
            return editor;
        }

        private void insertBefore(Component component) {
            if (component instanceof EditorTab editorTab) {
                System.out.println("INDEX: " + closeTabs.indexOf(this));
                System.out.println("Insert " + editorTab.editor.getName() + " before " + editor.getName());
                var selected = editorTab.isSelected();
                closeTabs.addTabAtIndex(closeTabs.indexOf(this), editorTab);
                if (selected) {
                    closeTabs.setSelectedTab(editorTab);
                }
            }
        }

        private void insertAfter(Component component) {
            if (component instanceof EditorTab editorTab) {
                System.out.println("INDEX: " + closeTabs.indexOf(this) + 1);
                System.out.println("Insert " + editorTab.editor.getName() + " after " + editor.getName());
                var selected = editorTab.isSelected();
                closeTabs.addTabAtIndex(closeTabs.indexOf(this) + 1, editorTab);
                if (selected) {
                    closeTabs.setSelectedTab(editorTab);
                }
            }
        }
    }
}
