package Fish;

import java.awt.Image;

import javax.swing.ImageIcon;

public class superfish {

	String name = null;
	int speed = 0;
	int movement = 0;
	int probablity = 0;
	int x = 0;
	int y = 0;
	boolean flag = true;
	int width = 0;
	int height = 0;
	private Image fishimg = null;

	superfish(fishDTO fishdto, int x, int y) {
		this.x = x;
		this.y = y;
		
		this.name = fishdto.getName();
		this.speed = fishdto.getSpeed();
		this.movement = fishdto.getMovement();
		this.probablity = fishdto.getProbablity();

		fishimg = new ImageIcon("./img/" + name + ".png").getImage();

		this.width = fishimg.getWidth(null);
		this.height = fishimg.getHeight(null);

	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public boolean isFlag() {
		return flag;
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = this.x + x;
	}

	public void setX(int x, String a) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		
		this.y = this.y + y;
	}

	public void setY(int y, String a) {
		this.y = y;
	}

	public Image getFishimg() {
		return fishimg;
	}

	public void setFishimg(Image fishimg) {
		this.fishimg = fishimg;
	}

	public int getSpeed() {
		return speed;
	}

	public int getMovement() {
		return movement;
	}

	public int getProbablity() {
		return probablity;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
