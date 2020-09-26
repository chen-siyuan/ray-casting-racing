# Ray-tracing

A Senior Year Computer Science I.S. Project.

## Features

### Ray-tracing

### Map display

## Controls

|Key|Function|
|---|---|
|`W`|Move forward|
|`S`|Move backward|
|`A`|Move left|
|`D`|Move right|
|`&#8592`|Turn counterclockwise|
|`&#8594`|Turn clockwise|


### Frame - intersection detection and distance calculation, observer control

#### Intersection detection and distance calculation

As for now, intersections would mostly be between rays (with position and direction) and edges (with two endpoints). This allows us to achieve fast intersection detection and distance calculation with vector geometry.

#### Observer control

The user would be able to control the movements (forward/backward + turning) of the observer. The user would not be able to change the number of rays nor the span of vision of the observer. **NEED TO ADD MOTION SYSTEM**

### Vision panel - distance-to-height transformation, vision display

#### Distance-to-height transformation

The input distances would be tranformed to heights through a semi-inverse function based on convex lens optics.

#### Vision display

The vision perceived by the observer would be displayed in form of a bar graph.

### Map panel - map display

The map (edges) and the range of vision (central ray + edge rays) of the observer would be displayed.

### Control panel - map edit

The user would be able to add/remove (straight) edges in the map. **LONG TERM**

