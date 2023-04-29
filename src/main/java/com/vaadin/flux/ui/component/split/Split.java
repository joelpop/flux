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
public class Split extends Composite<SplitLayout> implements SplitItem, HasSize {

    public Split(SplitItem primarySplitItem, SplitItem secondarySplitItem, SplitLayout.Orientation orientation) {
        // TODO see if SplitItem can be converted from an interface to a class extending Composite
        if (!(primarySplitItem instanceof Component primaryComponent)) {
            throw new IllegalArgumentException("primarySplitItem must be a Component");
        }
        if (!(secondarySplitItem instanceof Component secondaryComponent)) {
            throw new IllegalArgumentException("secondarySplitItem must be a Component");
        }

        var content = getContent();
        content.setSizeFull();
        content.addThemeVariants(SplitLayoutVariant.LUMO_SMALL);
        content.setOrientation(orientation);
        content.addToPrimary(primaryComponent);
        content.addToSecondary(secondaryComponent);
    }

//    public void replaceSplitItem(SplitItem splitItem) {
//        var content = getContent();
//        content.getPrimaryComponent()
//    }
}
