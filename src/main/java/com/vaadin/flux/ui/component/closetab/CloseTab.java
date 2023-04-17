package com.vaadin.flux.ui.component.closetab;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 *
 * <pre>
 *     +-(Tab)------------+
 *    / +-closeWrapper---+ \
 *   /  | (x) components |  \
 *  /   +----------------+   \
 * +--------------------------+
 * </pre>
 */
public class CloseTab extends Tab {

    private final CloseWrapper closeWrapper;

    public CloseTab(ClosePosition closePosition, Component... components) {
        this(closePosition);
        add(components);
    }

    public CloseTab(ClosePosition closePosition, String label) {
        this(closePosition);
        add(label);
    }

    public CloseTab(ClosePosition closePosition) {
        closeWrapper = new CloseWrapper(this::fireCloseEvent);
        setClosePosition(closePosition);

        super.add(List.of(closeWrapper));
    }

    public CloseTab(Component... components) {
        this(ClosePosition.BEFORE, components);
    }

    public CloseTab(String label) {
        this(ClosePosition.BEFORE, label);
    }

    public CloseTab() {
        this(ClosePosition.BEFORE);
    }

    public ClosePosition getClosePosition() {
        return closeWrapper.getClosePosition();
    }

    public void setClosePosition(ClosePosition closePosition) {
        closeWrapper.setClosePosition(closePosition);

    }

    @Override
    public void add(Component... components) {
        closeWrapper.add(components);
    }

    @Override
    public void add(Collection<Component> components) {
        closeWrapper.add(components);
    }

    @Override
    public void add(String text) {
        closeWrapper.add(text);
    }

    @Override
    public void remove(Component... components) {
        closeWrapper.remove(components);
    }

    @Override
    public void remove(Collection<Component> components) {
        closeWrapper.remove(components);
    }

    @Override
    public void addComponentAtIndex(int index, Component component) {
        closeWrapper.addComponentAtIndex(index, component);
    }

    @Override
    public void addComponentAsFirst(Component component) {
        closeWrapper.addComponentAsFirst(component);
    }

    @Override
    public String toString() {
        return "%s@%h".formatted(getClass().getCanonicalName(), hashCode());
    }

    /**
     * TODO This is a workaround for HasComponents.removeFromParent() not correctly removing a Tab from Tabs and TabSheet
     */
    @Override
    public void removeFromParent() {
        getParent()
                .filter(Tabs.class::isInstance)
                .map(Tabs.class::cast)
                .ifPresentOrElse(tabs -> tabs.getParent()
                                .filter(TabSheet.class::isInstance)
                                .map(TabSheet.class::cast)
                                .ifPresentOrElse(tabSheet -> tabSheet.remove(this),
                                        () -> tabs.remove(this)),
                        super::removeFromParent);
    }

    // CloseEvent

    /**
     * The event fired when the tab is closed.
     */
    public static class CloseEvent extends ComponentEvent<CloseTab> {
        /**
         * Create a close event.
         *
         * @param source the tab
         * @param fromClient {@code true} if the event originated from a client, {@code false} otherwise
         */
        public CloseEvent(CloseTab source, boolean fromClient) {
            super(source, fromClient);
        }
    }

    /**
     * Add a close listener to the tab.
     *
     * @param closeListener a listener to call when the tab is closed
     * @return a registration to facilitate removal of the close listener
     */
    public Registration addCloseListener(ComponentEventListener<CloseEvent> closeListener) {
        return addListener(CloseEvent.class, closeListener);
    }

    @SuppressWarnings("java:S3398")  // not moving to inner class to maintain pattern
    private void fireCloseEvent(boolean fromClient) {
        fireEvent(new CloseEvent(this, fromClient));
        removeFromParent();
    }


    public enum ClosePosition {
        NONE(null),
        BEFORE(FlexLayout.FlexDirection.ROW),
        AFTER(FlexLayout.FlexDirection.ROW_REVERSE);

        private final FlexLayout.FlexDirection flexDirection;

        ClosePosition(FlexLayout.FlexDirection flexDirection) {
            this.flexDirection = flexDirection;
        }

        public FlexLayout.FlexDirection getFlexDirection() {
            return flexDirection;
        }
    }

    private static class CloseWrapper extends Composite<FlexLayout> {
        private final transient Consumer<Boolean> closeEventConsumer;

        private final FlexLayout content;
        private final Icon closeIcon;
        private final FlexLayout wrapped;

        private ClosePosition closePosition;

        public CloseWrapper(Consumer<Boolean> closeEventConsumer) {
            this.closeEventConsumer = closeEventConsumer;

            closeIcon = VaadinIcon.CLOSE_CIRCLE.create();
            closeIcon.setSize("var(--lumo-font-size-xs)");
            closeIcon.addClickListener(this::onClick);

            wrapped = new FlexLayout();

            content = getContent();
            content.setAlignItems(FlexComponent.Alignment.CENTER);
            content.addClassNames(LumoUtility.Gap.SMALL);
            content.add(closeIcon);
            content.add(wrapped);

            setClosePosition(ClosePosition.NONE);
        }

        public ClosePosition getClosePosition() {
            return closePosition;
        }

        public void setClosePosition(ClosePosition closePosition) {
            this.closePosition = closePosition;

            var flexDirection = closePosition.getFlexDirection();
            closeIcon.setVisible(flexDirection != null);
            content.setFlexDirection(flexDirection);
        }

        public Stream<Component> getComponents() {
            return wrapped.getChildren();
        }

        public void add(String text) {
            wrapped.add(text);
        }

        public void add(Component... components) {
            wrapped.add(components);
        }

        public void add(Collection<Component> components) {
            wrapped.add(components);
        }

        public void addComponentAtIndex(int index, Component component) {
            wrapped.addComponentAtIndex(index, component);
        }

        public void addComponentAsFirst(Component component) {
            wrapped.addComponentAsFirst(component);
        }

        public void remove(Component... components) {
            wrapped.remove(components);
        }

        public void remove(Collection<Component> components) {
            wrapped.remove(components);
        }

        public void removeAll() {
            wrapped.removeAll();
        }

        private void onClick(ClickEvent<Icon> iconClickEvent) {
            closeEventConsumer.accept(true);
        }
    }
}
