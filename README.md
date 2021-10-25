# Ray Casting Racing Game

This is my senior year independent study on ray casting conducted
from September to December 2020.

## Screenshots

![Screenshot](assets/screen1.png)
![Screenshot](assets/screen2.png)

## Features

### Basic components

**The observer** has vision and can move and turn. The motion system is based on a **bicycle model**.

**The map** consists of edges and can be imported through Tikz.

### Ray-tracing

**Intersection detection and distance calculation** between rays (from the observer) and edges (from the map) are done through vector geometry.

**Distance-to-height transformation** is done through a semi-inverse function based on convex lens optics.

### Map display

**Fixed-camera mode** and **fixed-map mode** can be toggled.

## Controls

### Observer control

|Key|Function|
|:---:|:---|
|`W`|Move forward|
|`S`|Move backward|
|`A`|Turn counterclockwise|
|`D`|Turn clockwise|
|&#8593;|Boost forward|
|&#8595;|Boost backward|
|&#8592;|Boost counterclockwise|
|&#8594;|Boost clockwise|
|`[`|Decrease focal length|
|`]`|Increase focal length|

### Map control

|Key|Function|
|:---:|:---|
|`-`|Zoom out|
|`=`|Zoom in|
|`9`|Scale out|
|`0`|Scale in|
|`\`|Toggle mode|
|`[space]`|Reset camera|

