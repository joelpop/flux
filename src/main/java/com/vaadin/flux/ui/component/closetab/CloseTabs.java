package com.vaadin.flux.ui.component.closetab;

import com.vaadin.flow.component.tabs.Tabs;

/**
 *
 * <pre>
 * +-(Tabs)------------------------------------+
 * |   ____________ ____________ ____________  |
 * |  / (x) label  \ (x) label  \ (x) label  \ |
 * | +-------------+------------+------------+ |
 * +-------------------------------------------+
 * </pre>
 */
public class CloseTabs extends Tabs {
    public CloseTabs() {
        //
    }

    public CloseTabs(CloseTab... closeTabs) {
        this();
        add(closeTabs);
    }

    public CloseTabs(boolean autoselect, CloseTab... closeTabs) {
        this(closeTabs);
        setAutoselect(autoselect);
    }
}
