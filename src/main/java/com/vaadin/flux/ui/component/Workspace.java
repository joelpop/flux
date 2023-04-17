package com.vaadin.flux.ui.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flux.ui.component.editor.SourceEditor;
import com.vaadin.flux.ui.component.editor.VisualEditor;
import com.vaadin.flux.ui.component.editorgroup.EditorGroup;
import com.vaadin.flux.ui.component.split.SplitItem;
import com.vaadin.flux.ui.component.toolgroup.ToolGroup;
import com.vaadin.flux.ui.tool.*;

/**
 * Representative positioning
 *
 * <pre>
 * +-content(Div)------------------------------------------------------------------------------------------------------+
 * | +-rootSplit----------------------------+------------------------------------------------------------------------+ |
 * | | +-(Split)--------------------------+ # +-(Split)------------------------------------------------------------+ | |
 * | | | +-(ToolGroup)------------------+ | # | +-(Split)--------------------------+-----------------------------+ | | |
 * | | | |  _________ ________ ______   | | # | | +-editorGroup-----------------+ # +-(ToolGroup)-------------+ | | | |
 * | | | | /Directory\Template\Action\  | | # | | |  ________ _________          | # |  ________ _____ _____   | | | | |
 * | | | | +--------------------------+ | | # | | | /MainView\HelloView\         | # | /Property\Style\Event\  | | | | |
 * | | | | |                          | | | # | | | +--------------------------+ | # | +---------------------+ | | | | |
 * | | | | |                          | | | # | | | |                          | | # | |                     | | | | | |
 * | | | | |                          | | | # | | | |                          | | # | |                     | | | | | |
 * | | | | |                          | | | # | | | |                          | | # | |                     | | | | | |
 * | | | | |                          | | | # | | | |                          | | # | |                     | | | | | |
 * | | | | |                          | | | # | | | |                          | | # | |                     | | | | | |
 * | | | | |                          | | | # | | | |                          | | # | |                     | | | | | |
 * | | | | |                          | | | # | | | |                          | | # | |                     | | | | | |
 * | | | | |                          | | | # | | | |                          | | # | |                     | | | | | |
 * | | | | |                          | | | # | | | |                          | | # | |                     | | | | | |
 * | | | | |                          | | | # | | | |                          | | # | |                     | | | | | |
 * | | | | |                          | | | # | | | |                          | | # | |                     | | | | | |
 * | | | | +--------------------------+ | | # | | | |                          | | # | |                     | | | | | |
 * | | | +------------------------------+ | # | | | |                          | | # | |                     | | | | | |
 * | | +##################################+ # | | | |                          | | # | |                     | | | | | |
 * | | | +-(ToolGroup)------------------+ | # | | | |                          | | # | |                     | | | | | |
 * | | | |  _____________ _____         | | # | | | +--------------------------+ | # | +---------------------+ | | | | |
 * | | | | /ComponentTree\Theme\        | | # | | +------------------------------+ # +-------------------------+ | | | |
 * | | | | +--------------------------+ | | # | +----------------------------------+-----------------------------+ | | |
 * | | | | |                          | | | # +####################################################################+ | |
 * | | | | |                          | | | # | +-(ToolGroup)----------------------------------------------------+ | | |
 * | | | | |                          | | | # | |  _______ _____________                                         | | | |
 * | | | | |                          | | | # | | /Console\Documentation\                                        | | | |
 * | | | | |                          | | | # | | +------------------------------------------------------------+ | | | |
 * | | | | |                          | | | # | | |                                                            | | | | |
 * | | | | |                          | | | # | | |                                                            | | | | |
 * | | | | |                          | | | # | | |                                                            | | | | |
 * | | | | |                          | | | # | | |                                                            | | | | |
 * | | | | +--------------------------+ | | # | | +------------------------------------------------------------+ | | | |
 * | | | +------------------------------+ | # | +----------------------------------------------------------------+ | | |
 * | | +----------------------------------+ # +--------------------------------------------------------------------+ | |
 * | +--------------------------------------+------------------------------------------------------------------------+ |
 * +-------------------------------------------------------------------------------------------------------------------+
 *
 * </pre>
 */
public class Workspace extends Composite<Div> implements HasSize {

    public Workspace() {
        EditorGroup editorGroup;
        ToolGroup toolGroup;
        SplitItem rootSplitItem;

        editorGroup = new EditorGroup();
        editorGroup.setSizeFull();
        editorGroup.add(new VisualEditor("MainView",
                "(a canvas for visually editing the Main view)"));
        editorGroup.add(new VisualEditor("HelloView",
                "(a canvas for visually editing the Hello view)"));
        editorGroup.add(new SourceEditor("onSayHello",
                "(a source code editor for the \"onSayHello\" button click action)"));

        rootSplitItem = editorGroup;

        toolGroup = new ToolGroup();
        toolGroup.add(new PropertyTool());
        toolGroup.add(new StyleTool());
        toolGroup.add(new EventTool());
        rootSplitItem = rootSplitItem.splitRightward(toolGroup);

        toolGroup = new ToolGroup();
        toolGroup.add(new DocumentationTool());
        toolGroup.add(new ConsoleTool());
        rootSplitItem = rootSplitItem.splitDownward(toolGroup);

        toolGroup = new ToolGroup();
        toolGroup.add(new DirectoryTool());
        toolGroup.add(new DataModelTool());
        toolGroup.add(new TemplateTool());
        toolGroup.add(new ActionTool());

        var toolGroup2 = new ToolGroup();
        toolGroup2.add(new StructureTool());
        toolGroup2.add(new ThemeTool());
        rootSplitItem = rootSplitItem.splitLeftward(toolGroup.splitDownward(toolGroup2));

        var content = getContent();
        if (rootSplitItem instanceof Component component) {
            content.add(component);
        }
    }
}
