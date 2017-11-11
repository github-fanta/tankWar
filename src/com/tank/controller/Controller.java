package com.tank.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.tank.enterties.Explode;
import com.tank.enterties.Missile;
import com.tank.enterties.Tank;
import com.tank.view.GamePanel;

public class Controller extends KeyAdapter{

	public Tank tank = new Tank();
	public Tank tankEnemy = new Tank(300,400,true);
	public Missile missile;
	public GamePanel gamePanel;
	public Explode explode = new Explode();

	public Controller(Tank tank, Missile missile, GamePanel gamePanel) {
		
		this.tank = tank;
		this.missile = missile;
		this.gamePanel = gamePanel;
	}
	
	//拿到坦克和子弹，应该去让GamePanel去显示
	public void displayAll() {
		
		gamePanel.display(Controller.this);
	}
	
	
	//键盘监听事件
	@Override
	public void keyPressed(KeyEvent e) {
		
		switch (e.getKeyCode()) {
		
		case KeyEvent.VK_UP:
			tank.changeDirection(Tank.UP);
			tank.move();
			break;
		case KeyEvent.VK_DOWN:
			tank.changeDirection(Tank.DOWN);
			tank.move();
			break;
		case KeyEvent.VK_LEFT:
			tank.changeDirection(Tank.LEFT);
			tank.move();
			break;
		case KeyEvent.VK_RIGHT:
			tank.changeDirection(Tank.RIGHT);
			tank.move();
			break;
			
		//按下space发射子弹
		case KeyEvent.VK_SPACE:
			tank.fire(missile);
			break;
		default:
			break;
		};
	}


	public void newGame() {
		
		//让子弹开始自我驱动
		missile.missileStart();
		while(true) {
			//检测碰撞
			detectCollision();
			detectLife();
			displayAll();
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

	private void detectCollision() {
		
		if (missile.getMissiles() != null && missile.getMissiles().size() > 0) {
			//去打坦克，判断击中，置生命值
			missile.hitTank(tankEnemy, explode);

		}
	}

	private void detectLife() {
		
		if (tankEnemy != null) {
			
			if (tankEnemy.life == false) {
				tankEnemy = null;
			}
		}
		
		if (tank.life == false) {
			tank = null;
		}
		
	}
	
	
}
