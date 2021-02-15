package Needle;

import java.awt.Image;

import javax.swing.ImageIcon;

public class myneedle {
	int x = 890;
	int y = 6;

	int speed = 5;
	private Image needleimg = null;

	int width = 0;
	int height = 0;

	myneedle() {

		needleimg = new ImageIcon("./img/바늘.png").getImage();
		this.width = needleimg.getWidth(null);
		this.height = needleimg.getHeight(null);
	}

	public Image getNeedleimg() {
		return needleimg;
	}

	public int getX() {
		return x;
	}

	public void setX(int x, String trash) {

		this.x = x;

	}

	public void setY(int y, String trash) {

		this.y = y;

	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setX(int x) {

		this.x = this.x + x;

	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		if (this.y < 696 && this.y > 5) {
			this.y = this.y + y;

		}
	}

}
