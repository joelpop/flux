package com.vaadin.flux.ui.component.closetab;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.tabs.Tabs;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 *
 * <pre>
 * +-(TabSheet)--------------------------------+
 * |   ____________ ____________ ____________  |
 * |  / (x) label  \ (x) label  \ (x) label  \ |
 * | +-------------+------------+------------+ |
 * | |                                       | |
 * | |                                       | |
 * | |                                       | |
 * | |                                       | |
 * | |                                       | |
 * | |                                       | |
 * | +---------------------------------------+ |
 * +-------------------------------------------+
 * </pre>
 */
public class CloseTabSheet extends TabSheet {
    private final Map<Tab, Component> tabContents;

    public CloseTabSheet() {
        tabContents = new HashMap<>();
    }

    private void add(CloseTab closeTab) {
        closeTab.getParent()
                .filter(Tabs.class::isInstance)
                .map(Tabs.class::cast)
                .map(Component::getParent)
                .flatMap(Function.identity())
                .filter(CloseTabSheet.class::isInstance)
                .map(CloseTabSheet.class::cast)
                .ifPresent(closeTabSheet -> {
                    var content = closeTabSheet.tabContents.remove(closeTab);
                    System.out.printf("Found Tab (%s) in TabSheet (%s) with Content (%s)%n", closeTab, closeTabSheet, content);
                    closeTabSheet.remove(closeTab);
                    add(closeTab, content);
                });
    }

    @Override
    public Tab add(Tab tab, Component content, int position) {
        System.out.printf("Adding Tab (%s) to TabSheet (%s) with Content (%s)%n", tab, this, content);
        tabContents.put(tab, content);
        return super.add(tab, content, position);
    }

    @Override
    public void remove(Tab tab) {
        System.out.printf("Removing Tab (%s) from TabSheet (%s)%n", tab, this);
        super.remove(tab);
        /*return*/ tabContents.remove(tab);
    }

    public Component getComponent(Tab tab) {
        return tabContents.get(tab);
    }
}
