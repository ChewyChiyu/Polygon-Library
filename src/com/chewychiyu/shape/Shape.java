package com.chewychiyu.shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Shape {

	private Point2D location_;
	private Polygon poly_;
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
		poly_ = new Polygon();
		for(int _side = 0; _side < sides_; _side++){
			int _point_from_x = (int)(side_length_*Math.cos(((2*Math.PI)*_side)/sides_));
			int _point_from_y = (int)(side_length_*Math.sin(((2*Math.PI)*_side)/sides_));
			poly_.addPoint(_point_from_x, _point_from_y);
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
		location_.setLocation(_new_x, _new_y);
	}

	public void _rotate_by(double _angle){
		angle_+= _angle;
	}

	public void _draw_border(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform _saved_trans = g2d.getTransform();
		g2d.setColor(color_);
		g2d.translate(location_.getX(),location_.getY());
		g2d.rotate(angle_);
		g2d.drawPolygon(poly_);
		g2d.setTransform(_saved_trans);
	}

	public void _draw_fill(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform _saved_trans = g2d.getTransform();
		g2d.setColor(color_);
		g2d.translate(location_.getX(),location_.getY());
		g2d.rotate(angle_);
		g2d.fillPolygon(poly_);
		g2d.setTransform(_saved_trans);
	}

	public Point2D _get_center(){
		return location_;
	}

	public Color _get_color(){
		return color_;
	}
}
