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

          #around-overlay {
            --top-left: calc(var(--around-overlay-min-dim) * 1px / 2);
            --bottom-right: calc(100% - var(--top-left));
            
            position: relative;
            left: 0;
            top: 0;
            right: 0;
            bottom: 0;
            width: 100%;
            height: 100%;
          }

          .drop-zone {
            box-sizing: border-box;
            position: absolute;
            opacity: 0.2;
          }

          #before-zone {
            left: 0;
            top: 0;
            width: 50%;
            height: 100%;
            clip-path: polygon(0 0, var(--top-left) var(--top-left), var(--top-left) var(--bottom-right), 0 100%);
            border-left: 5px solid lightgray;
            background-color: green;
          }

          #above-zone {
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            clip-path: polygon(0 0, var(--top-left) var(--top-left), var(--bottom-right) var(--top-left), 100% 0);
            border-top: 5px solid lightgray;
            background-color: purple;
          }

          #below-zone {
            left: 0;
            bottom: 0;
            width: 100%;
            height: 50%;
            clip-path: polygon(0 100%, var(--top-left) var(--bottom-right), var(--bottom-right) var(--bottom-right), 100% 100%);
            border-bottom: 5px solid lightgray;
            background-color: orange;
          }

          #after-zone {
            right: 0;
            top: 0;
            width: 50%;
            height: 100%;
            clip-path: polygon(100% 0, var(--bottom-right) var(--top-left), var(--bottom-right) var(--bottom-right), 100% 100%);
            border-right: 5px solid lightgray;
            background-color: red;
          }

          #before-zone.v-drag-over-target,
          #above-zone.v-drag-over-target,
          #below-zone.v-drag-over-target,
          #after-zone.v-drag-over-target {
            opacity: 0.9;
          }
          
          #before-zone.v-drag-over-target {
            border-left: 3px solid rgba(127, 127, 255, 0.3);
          }

          #above-zone.v-drag-over-target {
            border-top: 3px solid rgba(127, 127, 255, 0.3);
          }

          #below-zone.v-drag-over-target {
            border-bottom: 3px solid rgba(127, 127, 255, 0.3);
          }

          #after-zone.v-drag-over-target {
            border-right: 3px solid rgba(127, 127, 255, 0.3);
          }
        `;
    }

    constructor() {
        super();

        console.log('HERE I WAS')
        // new ResizeObserver(entries=> {
        //     for (const entry of entries) {
        //         const { width, height } = entry.contentRect;
        //         console.log('HERE I AM')
        //         entry.target.style.setProperty('--around-overlay-min-dim', Math.min(width, height));
        //     }
        // }).observe(window.document.getElementById("around-overlay"));
    }

    render() {
        return html`
            <div id="around-overlay">
                <div id="before-zone" class="drop-zone"></div>
                <div id="above-zone" class="drop-zone"></div>
                <div id="below-zone" class="drop-zone"></div>
                <div id="after-zone" class="drop-zone"></div>
            </div>
        `;
    }
}

customElements.define('around-drop-target-overlay', AroundDropTargetOverlay);
