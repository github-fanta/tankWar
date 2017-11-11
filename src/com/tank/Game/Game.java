package com.tank.Game;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.tank.controller.Controller;
import com.tank.enterties.Missile;
import com.tank.enterties.Tank;
import com.tank.utils.Global;
import com.tank.view.GamePanel;

public class Game {

	
	public static void main(String args[]) {
		
		 Tank tank = new Tank();
		 Missile missile = new Missile();
		 GamePanel gamePanel = new GamePanel();
		 Controller controller = new Controller(tank, missile, gamePanel);
		
		
		JFrame frame = new JFrame("TankWar");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(Global.WIDTH*Global.CELL+15, Global.HEIGHT*Global.CELL+35);
		gamePanel.setSize(Global.WIDTH*Global.CELL, Global.HEIGHT*Global.CELL);
		frame.add(gamePanel, BorderLayout.CENTER);
		
		gamePanel.addKeyListener(controller);
		frame.addKeyListener(controller);
		frame.setVisible(true);
		
		controller.newGame();
	}
}
