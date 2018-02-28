package com.chewychiyu.polygon_test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.chewychiyu.shape.Shape;

@SuppressWarnings("serial")
public class TestClass extends JPanel{
	
	
	Dimension screen_dim_ = new Dimension(800,800);
	Shape poly_;
	Shape poly2_;
	
	public static void main(String[] args){
		new TestClass();
	}
	
	TestClass(){
		panel();
		poly_ = new Shape(300,300,200,3);
		poly2_ = new Shape(300,300,200,4);
		addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				poly_._shift_to(e.getX(), e.getY());
				poly_._set_color(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
				repaint();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		
		});
		addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent e) {
				poly_._shift_to(e.getX(), e.getY());
				repaint();
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
		});
		
		addMouseWheelListener(new MouseWheelListener(){

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if(e.getWheelRotation() < 0){
					//poly_._change_facets_by(1);
					poly_._rotate_by(Math.PI/60);
					//poly_._change_size_by(10);
				}else if(e.getWheelRotation() > 0){
					//poly_._change_facets_by(-1);
				    poly_._rotate_by(-Math.PI/60);
					//poly_._change_size_by(-10);
				}		
				repaint();
			}
			
		});

		
	
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		poly_._draw_fill(g);
		poly2_._draw_fill(g);
		if(poly_._collided_with(poly2_)){
			poly_._set_color(Color.RED);
			poly2_._set_color(Color.RED);
		}else{
			poly_._set_color(Color.BLACK);
			poly2_._set_color(Color.BLACK);
		}
	}
	
	public void panel(){
		JFrame _frame = new JFrame("Polygon Lib");
		_frame.add(this);
		_frame.setPreferredSize(screen_dim_);
		_frame.pack();
		_frame.setVisible(true);
		_frame.setResizable(false);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
}
