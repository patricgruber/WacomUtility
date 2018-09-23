# WacomUtility
This tool is a simple frontend for the xsetwacom tool on linux.

The tool uses xsetwacom to get the available tablets and to set the settings.

You can set the tablet area, the output area and if touch is enabled.

You can also set custom properties in a table.

# Dependencies
* xsetwacom
* Java JDK8+

# FAQ
## What is the option *xy* for? / What options are available?
Check ``xsetwacom list parameters``

## How do I get *xsetwacom*? / It's not working with my tablet!
Check [Linux Wacom Project](https://linuxwacom.github.io/)

## What are the best options for osu! ?
I recommend ``RawSample 1`` and ``Suppress 0`` 

# Tested with
* __OS__: Manjaro Linux 64bit
* __Desktop Environment__: Gnome 3.28.2
* __Java__: OpenJDK 8
