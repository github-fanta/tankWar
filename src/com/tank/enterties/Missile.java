package com.tank.enterties;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

import com.tank.utils.Global;

public class Missile {

	
	Explode explode;
	private ArrayList<SigleMissile> missiles = new ArrayList<SigleMissile>();
	
	
	
	public ArrayList<SigleMissile> getMissiles() {
		return missiles;
	}

	//发射新子弹加入ArrayList
	public void fireMissile(Tank tank) {
		SigleMissile sigleMissile = new SigleMissile();
		sigleMissile.direction = tank.direction;
		sigleMissile.missile.x = tank.endX;
		sigleMissile.missile.y = tank.endY;
		missiles.add(sigleMissile);
	}
	
	public void drawMe(Graphics g) {
		System.out.println("Missile's drawMe");
		g.setColor(Color.BLACK);
		for (SigleMissile sigleMissile : missiles) {
			g.fillOval(sigleMissile.missile.x, sigleMissile.missile.y, 10, 10);
		}
	}
	
	public void move() {
		System.out.println("Missile's move");
		//对于屏幕上的每一个子弹都根据其方向移动一次
		for (SigleMissile sigleMissile : missiles) {
						
			switch (sigleMissile.direction) {
			
			case Tank.UP:
				sigleMissile.missile.y -= 15;
				break;
			case Tank.DOWN:
				sigleMissile.missile.y += 15;
				break;
			case Tank.LEFT:
				sigleMissile.missile.x -= 15;
				break;
			case Tank.RIGHT:
				sigleMissile.missile.x += 15;
				break;
			default:
				break;
			}
		}
	}

	private class MissileDriver implements Runnable{

		@Override
		public void run() {

			while(true) {
				move();
				cleanRefesh();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void missileStart() {
		new Thread(new MissileDriver()).start();
	}
	
	
	//清理出界和死亡的子弹
	private void cleanRefesh() {
		
		if (missiles != null && missiles.size() > 0) {
			
			Iterator<SigleMissile> iterator = missiles.iterator();
			while(iterator.hasNext()) {
				SigleMissile sigleMissile = iterator.next();
				if (sigleMissile.missile.x > Global.WIDTH*Global.CELL ||
						sigleMissile.missile.x < 0 ||
						sigleMissile.missile.y > Global.HEIGHT*Global.CELL||
						sigleMissile.missile.y < 0 ||
						sigleMissile.life == false){
					iterator.remove();
				}
			}
			
		}
	}
	
	
	
	
	
	
	//单个子弹，子弹类的内部类
	class SigleMissile {
		Point missile = new Point();
		int direction;
		Boolean life = true;
	}

	
	public void hitTank(Tank tankEnemy, Explode explode) {
		
			
		if (tankEnemy != null) {
			
			Iterator<SigleMissile> iterator = missiles.iterator();
			while(iterator.hasNext()) {
				SigleMissile sigleMissile = iterator.next();
				
				//借用Rectangle类判定碰撞
				if (new Rectangle(sigleMissile.missile.x, sigleMissile.missile.y, Global.WIDTH, Global.HEIGHT).
						intersects(new Rectangle(tankEnemy.x, tankEnemy.y, Global.WIDTH, Global.HEIGHT))) {
					//如果碰撞，则将坦克和子弹都置为死
					tankEnemy.life = false;
					sigleMissile.life = false;
					
					//将爆炸效果加入爆炸List中，给GamePanel显示
					explode.explodes.add(explode.new SigleExplodes(tankEnemy.x, tankEnemy.y));
					
				}
			}
		}
		
	}




}
