package com.tank.enterties;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Explode {

	public class SigleExplodes{
		int x;
		int y;
		boolean live = true;
		
		public SigleExplodes(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public List<SigleExplodes> explodes = new ArrayList<SigleExplodes>();
	
	int[] diameter = {15, 15,15,18, 18, 18, 46, 46,46, 40, 40, 42, 59, 59, 59, 59,54, 54, 54, 30, 30,30, 14, 14, 14, 14, 6};
	int step = 0;

	
	public void drawMe(Graphics g) {
		
		if (explodes != null && explodes.size() > 0) {
			Iterator<SigleExplodes> iterator = explodes.iterator();
			while(iterator.hasNext()) {
				SigleExplodes sigleExplode = iterator.next();
				if (sigleExplode.live == false){
					iterator.remove();
					continue;
				}

				g.setColor(Color.YELLOW);
				g.fill3DRect(sigleExplode.x, sigleExplode.y, diameter[step], diameter[step], true);
				step ++;
				sigleExplode.live = false;
				
				if (step == diameter.length) {
					step = 0;
					return;
				}
			}

		}

	}
}
