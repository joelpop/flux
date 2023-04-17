## Drag & Drop

| Drag Source | Drop Target | Overlay         | ABOVE Zone | BEFORE Zone | ONTO Zone | AFTER Zone | BELOW Zone |
|-------------|-------------|-----------------|:----------:|:-----------:|:---------:|:----------:|:----------:|
| DockItem    | DockItem(H) | BeforeAfter     |            |      X      |           |     X      |            |
| DockItem    | DockItem(V) | AboveBelow      |     X      |             |           |            |     X      |
| DockItem    | DockSlot(H) | AboveOntoBelow  |     X      |             |     X     |            |     X      |
| DockItem    | DockSlot(V) | BeforeOntoAfter |            |      X      |     X     |     X      |            |
| DockItem    | Dock        | Onto            |            |             |     X     |            |            |
| ToolTab     | ToolTab     | BeforeAfter     |            |      X      |           |     X      |            |
| ToolTab     | ToolGroup*  | Onto            |            |             |     X     |            |            |
| ToolGroup   | SplitPanel  | Around          |     X      |      X      |           |     X      |     X      |
**Both the tab bar and the content panel below it.* 

## Glossary

Dock
: 

DockItem
: 

Pallet
: 

PalletItem
: 

Tool
: 

ToolGroup
: 

ToolTab
: 

Editor
: 

EditorGroup
: 

EditorTab
: 

Canvas
: 

CodeEditor (not an ideal name)
: 

Split
: A panel with two parts (split items). Splits can be divided either horizontally or vertically. Dragging the contents of a split item (such as a tool group or editor group) and dropping it onto another split item will cause the dragged item to be removed from the original split and be relocated to a new split along with the target split item in the target split item's place. Because the original split will now contain only one item, it will be removed and its remaining item will assume its place.
