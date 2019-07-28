# WacomUtility
This tool is a simple frontend for the xsetwacom tool on linux.

The tool uses xsetwacom to get the available tablets and to set the settings.

You can set the tablet area, the output area and if touch is enabled.

You can also set custom properties in a table.

This tool is still pre-alpha.

# Dependencies
* xsetwacom
* Java JDK8+

# FAQ
## The MapTo option doesn't work right
The MapTo setting uses absolute pixel coordinates on your whole display setup.

So if you have two displays and want to map the tablet to an area on the right display, you have to add the width of the left display to the _top left x_ coordinate.

## What is the option *xy* for? / What options are available?
Check ``xsetwacom list parameters``

## How do I get *xsetwacom*? / It's not working with my tablet!
Check [Linux Wacom Project](https://linuxwacom.github.io/)

## What are the best options for osu! ?
Depends on your playstyle.

You can try the following settings, but it is not guaranteed that they'll work for you.

| Area | Grip | RawSample | Suppress |
| ---- | ---- | --------- | -------- |
| Full Area | Dragging | 1 | 0 |
| Full Area | Hovering | 1 or 2 | 1 or 0 |
| Half Area | Dragging | 1 | 1 |
| Half Area | Hovering | 1 or 2 | 1 or 1 |
| Small Area | Dragging | 2 or 3 | 2 or 1 |
| Small Area | Hovering | 4 | 2 |

# Tested with
* __OS__: Arch Linux 64bit
* __Desktop Environment__: i3
* __Java__: OpenJDK 12
