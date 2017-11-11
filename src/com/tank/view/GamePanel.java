package com.tank.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import com.tank.controller.Controller;
import com.tank.enterties.Missile;
import com.tank.enterties.Tank;
import com.tank.utils.Global;

public class GamePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 174955824230218931L;
	
	Image offScreenImage = null;
	private Tank tank;
	private Tank tankEnemy;
	private Missile missile;
	private Controller controller;
	
	public void display(Controller controller) {

		System.out.println("GamePanel's display");
		
		/*this.tankEnemy = tankEnemy;
		this.tank = tank;
		this.missile = missile;*/
		this.controller = controller;
		this.repaint();
	}

	
	
	@Override
	public void update(Graphics g) {

		if (offScreenImage == null) {
			this.createImage(Global.WIDTH*Global.CELL, Global.HEIGHT*Global.CELL);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		gOffScreen.setColor(new Color(0xcfcfcf));
		gOffScreen.fillRect(0, 0, Global.WIDTH*Global.CELL, Global.HEIGHT*Global.CELL);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}



	@Override
	protected void paintComponent(Graphics g) {
		//重新清洗屏幕
		g.setColor(new Color(0xcfcfcf));
		g.fillRect(0, 0, Global.WIDTH*Global.CELL, Global.HEIGHT*Global.CELL);
		
		g.setColor(Color.BLUE);
		
		controller.tank.drawMe(g);
		controller.missile.drawMe(g);
		
		if (controller.tankEnemy != null) {
			
			g.setColor(Color.red);
			controller.tankEnemy.drawMe(g);
		}
		
		//显示爆炸
		controller.explode.drawMe(g);
	}
	

	
}
