package entity.ship;

import entity.Entity;
import main.Handler;
import tiles.Tile;

public abstract class Ship extends Entity{
	
	public static final float DEFAULT_SPEED=0.2f;
	public static final int DEFAULT_SHIP_WIDTH=70,
							DEFAULT_SHIP_HEIGHT=12;
	
	protected float speed;
	protected float xMove, yMove;
	
	public Ship(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		speed=DEFAULT_SPEED;
		xMove=0;
		yMove=0;
	}
	
	public void move(float angle){
		if(!checkEntityCollisions(xMove, 0f))
			moveX(angle);
		if(!checkEntityCollisions(0f, yMove))
			moveY(angle);
		
	}
	
	public void moveX(float angle){
		if(xMove > 0){ //right
			int tx = (int) (x + xMove + rotatedRect.getBounds2D().getX() + rotatedRect.getBounds2D().getWidth()) / Tile.TILEWIDTH;
			
			if(!collisionWithTile(tx, (int) (y + rotatedRect.getBounds2D().getY()) / Tile.TILEHEIGHT) &&
					!collisionWithTile(tx, (int) (y + rotatedRect.getBounds2D().getY() + rotatedRect.getBounds2D().getHeight()) / Tile.TILEHEIGHT)){
				x += xMove * Math.cos(Math.toRadians(angle));
			}
				
			
		}else if(xMove < 0){ //left
			int tx = (int) (x + xMove + rotatedRect.getBounds2D().getX()) / Tile.TILEWIDTH;
			
			if(!collisionWithTile(tx, (int) (y + rotatedRect.getBounds2D().getY()) / Tile.TILEHEIGHT) &&
					!collisionWithTile(tx, (int) (y + rotatedRect.getBounds2D().getY() + rotatedRect.getBounds2D().getHeight()) / Tile.TILEHEIGHT)){
				x += xMove * Math.cos(Math.toRadians(angle));
			}
		}
	}
	
	public void moveY(float angle){
		if(yMove <= 0){ //up
			int ty= (int) (y + yMove + rotatedRect.getBounds2D().getY()) / Tile.TILEHEIGHT;
			
			if(!collisionWithTile((int) (x + rotatedRect.getBounds2D().getX()) / Tile.TILEWIDTH, ty) &&
					!collisionWithTile((int) (x + rotatedRect.getBounds2D().getX() + rotatedRect.getBounds2D().getWidth()) / Tile.TILEWIDTH, ty)){
						y += yMove * Math.sin(Math.toRadians(angle));
	
			}
			 
		}else if (yMove>0){ //down
			int ty= (int) (y + yMove + rotatedRect.getBounds2D().getY() + rotatedRect.getBounds2D().getHeight()) / Tile.TILEHEIGHT;
			
			if(!collisionWithTile((int) (x + rotatedRect.getBounds2D().getX()) / Tile.TILEWIDTH, ty) &&
					!collisionWithTile((int) (x + rotatedRect.getBounds2D().getX() + rotatedRect.getBounds2D().getWidth()) / Tile.TILEWIDTH, ty)){
				y += yMove * Math.sin(Math.toRadians(angle));
			}
			
		}
	}
	
	protected boolean collisionWithTile(int x, int y){
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
	//GETTERS AND SETTERS

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
}
