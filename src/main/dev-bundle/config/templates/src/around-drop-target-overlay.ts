import { css, html, LitElement } from 'lit';

class AroundDropTargetOverlay extends LitElement {
    static get styles() {
        return css`
          :host {
            --overlay-visibility: hidden;
            
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            width: 100%;
            height: 100%;
            z-index: 999999;
            visibility: var(--overlay-visibility, hidden);
          }
          
          .around-overlay {
            position: relative;
            height: 100%;
            width: 100%;
          }

          .drop-zone {
          }

          .drop-zone.v-drag-over-target {
            fill: yellow;
          }

          svg {
            stroke: black;
          }
        `;
    }

    render() {
        return html`
            <svg class="around-overlay">

                <svg viewbox="0 0 100 50" preserveAspectRatio="xMidYMin">
                    <path class="drop-zone above-zone" d="M 0,0
                           L 100,0 50,50
                           z" fill="rgba(0,127,255,0.5)" />
                </svg>

                <svg viewbox="0 0 50 100" preserveAspectRatio="xMinYMid">
                    <path class="drop-zone before-zone" d="M 0,0
                            L 50,50 0,100
                            z" fill="rgba(255,0,127,0.5)" />
                </svg>

                <svg viewbox="0 0 50 100" preserveAspectRatio="xMaxYMid">
                    <path class="drop-zone after-zone" d="M 50,0
                           L 50,100 0,50
                           z" fill="rgba(0,255,127,0.5)" />
                </svg>

                <svg viewbox="0 0 100 50" preserveAspectRatio="xMidYMax">
                    <path class="drop-zone below-zone" d="M 0,50
                           L 50,0 100,50
                           z" fill="rgba(255,127,0,0.5)" />
                </svg>

            </svg>
        `;
    }
}

customElements.define('around-drop-target-overlay', AroundDropTargetOverlay);
