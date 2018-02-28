# Polygon-Library
A simple polygon helper library for java

## Installation
drag and drop com.chewychiyu.shape into project folder

## Usage
   Construct new polygon object
```java
   Shape _shape = new Shape(_x_pos,_y_pos,_side_length,_sides);
```
   Set the color of shape ( random or preset )
```java
   _shape._set_color();
   _shape._set_color(_RED_COLOR);
```
   Change the facet count
```java
   _shape._change_facets_by(_facet_delta);
```
   Change the facet length
```java
   _shape._change_size_by(_facet_length);
```
   Change position of shape
```java
   _shape._shift_to(_new_pos_x, _new_pos_y);
```
   Rotate the shape by
```java
   _shape._rotate_by(_angle);
```
   Draw shape ( filled and border )
```java
   _shape._draw_fill(_graphics);
   _shape._draw_border(_graphics);
```
   Get center location
```java
   Point2D _center = _shape._get_center();
```
   Get color
```java
   Color _c = _shape._get_color();
```
   Prompt for collision
```java
   boolean _did_collide = _shape_a._collided_with(_shape_b);
```
   
   
