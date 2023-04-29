package com.vaadin.flux.ui.component.split;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.splitlayout.SplitLayoutVariant;

/**
 *
 * <pre>
 * +-(SplitLayout)----------+------------------------+
 * | +-primarySplitItem---+ # +-secondarySplitItem-+ |
 * | |                    | # |                    | |
 * | |                    | # |                    | |
 * | |                    | # |                    | |
 * | |                    | # |                    | |
 * | |                    | # |                    | |
 * | |                    | # |                    | |
 * | |                    | # |                    | |
 * | |                    | # |                    | |
 * | |                    | # |                    | |
 * | |                    | # |                    | |
 * | |                    | # |                    | |
 * | +--------------------+ # +--------------------+ |
 * +------------------------+------------------------+
 * </pre>
 */
public class Split extends SplitItem<SplitLayout> implements HasSize {

    public Split(SplitItem<?> primarySplitItem, SplitItem<?> secondarySplitItem,
                 SplitLayout.Orientation orientation) {
        var content = getContent();
        content.setSizeFull();
        content.addThemeVariants(SplitLayoutVariant.LUMO_SMALL);
        content.setOrientation(orientation);
        content.addToPrimary(primarySplitItem);
        content.addToSecondary(secondarySplitItem);
    }

//    public void replaceSplitItem(SplitItem splitItem) {
//        var content = getContent();
//        content.getPrimaryComponent()
//    }
}
