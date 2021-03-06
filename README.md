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
   Change position of shape ( delta )
```java
   _shape._shift_by(_delta_x, _delta_y);
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
   Prompt for point in polygon
```java
   boolean _contain_point = _shape_a._contains_point(_point);
```
   Toggle collision interactions
```java
   _shape_a._toggle_collision(_activated_collision);
``` 
   Set collision push magnitute
```java
   _shape_a._set_push_magnitute(_push_magnitute);
``` 

   
