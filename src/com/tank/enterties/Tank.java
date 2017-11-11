package com.tank.enterties;

import java.awt.Graphics;
import java.awt.Point;

import com.tank.utils.Global;

public class Tank extends Point{
	
	public static final int UP = 1;
	public static final int DOWN = -1;
	public static final int LEFT = 2;
	public static final int RIGHT = -2;
	public static final int SPEED = 8;
	int direction;
	//炮筒的起始点
	int startX;
	int startY;
	int endX;	//炮筒口
	int endY;
	public Boolean enemy = false;
	public Boolean life = true;
	
	public Tank(int x, int y, Boolean enemy){
		
		this.x = x;
		this.y = y;
		
		direction = DOWN;
		setGunBarrel(direction);
		enemy = true;
		life = true;
		
	}
	public Tank() {
		this.x = (Global.WIDTH/2)*Global.CELL;//x和y都是格子数，坐标数
		this.y = (Global.HEIGHT-1)*Global.CELL;
		direction = UP;
		setGunBarrel(direction);
		enemy = false;
		life = true;
	}
	
	//根据方向设置炮筒朝向
	private void setGunBarrel(int direction) {
		
		//起点不变
		startX = (int) (this.getX() + Global.CELL/2);
		startY = (int) (this.getY() + Global.CELL/2);

		//设置炮筒终点
		switch (direction) {
		case UP:
			endX = (int) (this.getX() + Global.CELL/2);
			endY = (int) (this.getY() - Global.CELL/2);
			break;
		case DOWN:
			endX = (int) (this.getX() + Global.CELL/2);
			endY = (int) (this.getY() + Global.CELL*1.5);
			break;
		case LEFT:
			endX = (int) (this.getX() - Global.CELL/2);
			endY = (int) (this.getY() + Global.CELL/2);
			break;
		case RIGHT:
			endX = (int) (this.getX() + Global.CELL*1.5);
			endY = (int) (this.getY() + Global.CELL/2);
			break;
		default:
			break;
		}

	}

	public void move() {
		System.out.println("Tank's move");
		
		switch (this.direction) {
		case UP:
			this.y-=SPEED;
			break;
		case DOWN:
			this.y+=SPEED;
			break;
		case LEFT:
			this.x-=SPEED;
			break;
		case RIGHT:
			this.x+=SPEED;
			break;
		default:
			break;
		}
		
		if (this.x < 0) this.x = 0;
		if (this.y < 0) this.y = 0;
		if(this.x > Global.WIDTH*Global.CELL-Global.CELL) this.x = Global.WIDTH*Global.CELL-Global.CELL;
		if(this.y > Global.HEIGHT*Global.CELL-Global.CELL) this.y = Global.HEIGHT*Global.CELL-Global.CELL;
		
		setGunBarrel(direction);
	}
	
	public void fire(Missile missile) {
		System.out.println("Tank's fire");
		
		missile.fireMissile(Tank.this);
		
		
	}
	
	public void drawMe(Graphics g) {
		
		System.out.println("Tank' drawMe");
		g.fill3DRect(this.x, this.y, Global.CELL, Global.CELL, true);
		g.drawLine(startX, startY, endX, endY);
		g.drawLine(startX-1, startY-1, endX-1, endY-1);
	}
	
	public void changeDirection(int direction){
		System.out.println("Tank's changeDirection");
		this.direction = direction;
	}
	
	//坦克死掉
	public void die() {
		this.life = false;
	}
	
}
