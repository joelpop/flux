package com.vaadin.flux.util;

import com.vaadin.flow.component.UI;

public final class DomUtil extends LibraryClass {

    private DomUtil() {
        // utility class - do not instantiate
    }

    public static void setTagProperty(String tag, String property, String value) {
        UI.getCurrent().getPage()
                .executeJs("document.querySelector($0).style.setProperty($1, $2);",
                        tag, property, value);
    }

    public static void setGlobalProperty(String property, String value) {
        setTagProperty("html", property, value);
    }

    public static void setClassProperty(String className, String property, String value) {
        System.out.printf("Server: .%s { %s: %s }%n", className, property, value);
        UI.getCurrent().getPage()
                .executeJs("""
                    var found = false;
                    var cssRuleProperty = (document.all) ? 'rules': 'cssRules';
                    var styleSheetMaxNum = document.styleSheets.length - 1;
                    
                    sheetLoop: for (styleSheetNum = styleSheetMaxNum; styleSheetNum >= 0; --styleSheetNum) {
                        var cssRules = document.styleSheets[styleSheetNum][cssRuleProperty];
                        var selectorMaxNum = cssRules.length - 1;
                        for (selectorNum = selectorMaxNum; selectorNum >= 0; --selectorNum) {
                            var selector = cssRules[selectorNum];
                            if (selector.selectorText === $0) {
                                selector.style.setProperty($1, $2);
                                found = true;
                                break sheetLoop;
                            }
                        }
                    }
                    
                    if (!found) {
                        // TODO which stylesheet to use?
//                                    document.styleSheets[styleSheetMaxNum - 1].insertRule($0 + '{' + $1 + ':' + $2 + ';}');
                        document.styleSheets[0].insertRule($0 + '{' + $1 + ':' + $2 + ';}');
                    }
                    console.log('Browser: ' + $0 + '{' + $1 + ':' + $2 + ';}');
                """, ".%s".formatted(className), property, value);
    }
}
