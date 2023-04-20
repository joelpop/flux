import { css, html, LitElement } from 'lit';

class AroundOntoDropTargetOverlay extends LitElement {
    static get styles() {
        return css`
          :host {
            display: flex;
            height: 100%;
            width: 100%;
          }
        `;
    }

    render() {
        return html`
            <svg id="around-onto-overlay" viewbox="0 0 100 100" preserveAspectRatio="none">
                <path id="above-zone"
                      d="
                        M 0,0
                        L 100,0 80,20 20,20
                        z"
                      fill="blue"></path>
                <path id="before-zone"
                      d="
                        M 0,0
                        L 20,20 20,80 0,100
                        z"
                      fill="red"></path>
                <path id="onto"
                      d="
                        M 20,20
                        L 80,20 80,80 20,80
                        z"
                      fill="lightgreen"></path>
                <path id="after-zone"
                      d="
                        M 100,0
                        L 100,100 80,80 80,20
                        z"
                      fill="orange"></path>
                <path id="below-zone"
                      d="
                        M 0,100
                        L 20,80 80,80 100,100 
                        z"
                      fill="violet"></path>
            </svg>
        `;
    }
}

customElements.define('around-onto-drop-target-overlay', AroundOntoDropTargetOverlay);
