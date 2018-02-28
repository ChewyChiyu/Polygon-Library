package com.chewychiyu.shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Shape {

	private Point2D location_;
	private Polygon physics_body_;
	private Polygon draw_body_;
	private double side_length_;
	private double sides_;
	private double angle_;
	private Color color_;
	
	public Shape(double _x_pos, double _y_pos, double _side_length, int _sides){
		location_ = new Point2D.Double(_x_pos, _y_pos);
		side_length_ = _side_length;
		sides_ = _sides;
		angle_ = 0;
		color_ = Color.BLACK;
		_form_facets();
	}

	public void _set_color(Color _new_color){
		color_ = _new_color;
	}

	public void _set_color(){
		color_ = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
	}

	private void _form_facets(){
		physics_body_ = new Polygon();
		draw_body_ = new Polygon();
		for(int _side = 0; _side < sides_; _side++){
			int _point_from_x = (int)(side_length_*Math.cos(((2*Math.PI)*_side)/sides_));
			int _point_from_y = (int)(side_length_*Math.sin(((2*Math.PI)*_side)/sides_));
			draw_body_.addPoint(_point_from_x, _point_from_y);
			physics_body_.addPoint((int) location_.getX() + _point_from_x, (int) location_.getY() + _point_from_y);
		}
	}

	public void _change_facets_by(int _sides){
		if(sides_ + _sides >= 3){
			sides_+=_sides;
			_form_facets();
		}
	}

	public void _change_size_by(int _length){
		if(side_length_ + _length > 0){
			side_length_ += _length;
			_form_facets();
		}
	}

	public void _shift_to(double _new_x, double _new_y){
		for(int _index = 0; _index < physics_body_.npoints; _index++){
			physics_body_.xpoints[_index] += ( _new_x - location_.getX() );
			physics_body_.ypoints[_index] += ( _new_y - location_.getY() );
		}
		location_.setLocation(_new_x, _new_y);
	}

	public void _rotate_by(double _angle){
		angle_+= _angle;
	}

	public void _draw_border(Graphics g){
		g.setColor(color_);
		g.drawPolygon(_get_rotation_mask(physics_body_,angle_));
	}

	public void _draw_fill(Graphics g){
		g.setColor(color_);
		g.fillPolygon(_get_rotation_mask(physics_body_,angle_));
	}

	public Point2D _get_center(){
		return location_;
	}

	public Color _get_color(){
		return color_;
	}
	
	public Polygon _get_rotation_mask(Polygon _poly, double angle){
		Polygon _rotation_mask = new Polygon();
		for(int _index = 0 ; _index < _poly.npoints; _index++){
		    double _storage;
		    _storage = (_poly.xpoints[_index]-location_.getX()) * Math.cos(angle) - ((_poly.ypoints[_index]-location_.getY()) * Math.sin(angle));
		    int _trans_y_pos   = (int) (location_.getY() + ((_poly.xpoints[_index]-location_.getX()) * Math.sin(angle)) + (((_poly.ypoints[_index]-location_.getY()) * Math.cos(angle))) );
		    int _trans_x_pos   = (int) (location_.getX() + _storage );
		    _rotation_mask.addPoint(_trans_x_pos, _trans_y_pos);
		}
		return _rotation_mask;
	}
	
	
	public Point[] _get_axis(){
		Polygon _rotation_mask = _get_rotation_mask(physics_body_,angle_);
		Point[] _axes = new Point[_rotation_mask.npoints];
		for(int _index = 0; _index < _axes.length-1; _index++ ){
			Point _vertex_from = new Point(_rotation_mask.xpoints[_index],_rotation_mask.ypoints[_index]);
			Point _vertex_to = new Point(_rotation_mask.xpoints[_index+1],_rotation_mask.ypoints[_index+1]);
			Point _edge = new Point(_vertex_from.x - _vertex_to.x, _vertex_from.y - _vertex_to.y);
			Point _normal = new Point(_edge.y,-_edge.x);
			_axes[_index] = _normal;
		}
		Point _vertex_from = new Point(_rotation_mask.xpoints[_rotation_mask.npoints-1],_rotation_mask.ypoints[_rotation_mask.npoints-1]);
		Point _vertex_to = new Point(_rotation_mask.xpoints[0],_rotation_mask.ypoints[0]);
		_axes[_rotation_mask.npoints-1] = new Point(_vertex_from.y-_vertex_to.y,-(_vertex_from.x-_vertex_to.x));
		return _axes;
	}

	public Point _get_projection(Point _axis){
		Polygon _rotation_mask = _get_rotation_mask(physics_body_,angle_);
		int _min = (_axis.x*_rotation_mask.xpoints[0])+(_axis.y*_rotation_mask.ypoints[0]);
		int _max = _min;
		for (int _index = 1; _index < _rotation_mask.npoints; _index++) {
			int _dot_product = (_axis.x*_rotation_mask.xpoints[_index])+(_axis.y*_rotation_mask.ypoints[_index]);
			if (_dot_product < _min) {
				_min = _dot_product;
			} else if (_dot_product > _max) {
				_max = _dot_product;
			}
		}
		return new Point(_min,_max);
	}

	public boolean _collided_with(Shape _s){
		Point[] _self_axes = _get_axis();
		Point[] _other_axes = _s._get_axis();
		for (int _index = 0; _index < _self_axes.length; _index++) {
			Point _axis = _self_axes[_index];
			Point _self_projection = _get_projection(_axis);
			Point _other_projection = _s._get_projection(_axis);
			if(_self_projection.y < _other_projection.x || _other_projection.y < _self_projection.x){
				return false;
			}
		}
		for (int _index = 0; _index < _other_axes.length; _index++) {
			Point _axis = _other_axes[_index];
			Point _self_projection = _get_projection(_axis);
			Point _other_projection = _s._get_projection(_axis);
			if(_self_projection.y < _other_projection.x || _other_projection.y < _self_projection.x){
				return false;
			}
		}
		return true;
	}
}
