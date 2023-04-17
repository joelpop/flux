package com.vaadin.flux.ui.tool;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flux.ui.component.toolgroup.Tool;

public class DocumentationTool extends Tool {

    public DocumentationTool() {
        super("Documentation", "Documentation for selected component.");

        var content = getContent();
        content.add(new Div(new Span(getHelpText())));

        content.add(new Html("""
            <div>
            <br/>
            <b>FLUX</b>
            <ul>
              <li>a portmanteau of Flow and UX
              <li>a substance used to promote fusion (now known as Hilla ;-) )
              <li>any effect that appears to pass or travel (whether it actually moves or not) through a surface or substance
              <li>word origin: The word flux comes from Latin: <i>fluxus</i> means "flow", and <i>fluere</i> is "to flow".
            </ul>
            </div>"""));

    }
}
