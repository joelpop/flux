package com.vaadin.flux.ui.tool;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flux.ui.component.toolgroup.Tool;

public class DocumentationTool extends Tool {

    public DocumentationTool() {
        super("Documentation", "Documentation for selected component.");

        getContent().add(getHelpText());

        getContent().add(new FlexLayout(new Html("""
            <div>
            <br/>
            <b>FLUX</b>
            <ul>
              <li>a portmanteau of Flow and UX
              <li>a substance used to promote fusion (now known as Hilla ;-) )
              <li>any effect that appears to pass or travel (whether it actually moves or not) through a surface or substance
              <li>word origin: The word flux comes from Latin: <i>fluxus</i> means "flow", and <i>fluere</i> is "to flow".
            </ul>
            </div>""")));

    }
}
