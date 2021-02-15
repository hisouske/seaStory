package GameRoom;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Fish.fishpattern;
import Fish.superfish;
import Guest.guestDAO;
import Guest.interDAO;
import Guest.superguest;
import Needle.needlemov;

public class gameroom extends JFrame implements KeyListener {

	canvas cv = new canvas();
	JLabel label = new JLabel("");

	int fishtimer = 500;
	ArrayList<superfish> nowfish = new ArrayList<>();
	ArrayList<superfish> holdfish = new ArrayList<>();
	ArrayList<superguest> glist = new ArrayList<>();

	int nowpoint = 0;

	JLabel title = new JLabel("***바다이야기***");

	interDAO interdao = null;
	guestDAO gdao = new guestDAO();

	superfish sf = null;
	fishpattern fp = new fishpattern();
	needlemov nm = new needlemov();

	private Image netimg = new ImageIcon("./img/통발.png").getImage();
	int needlenum = 3;
	int spacechk = 0;
	int enterchk = 0;
	String id = null;

	Toolkit tk = Toolkit.getDefaultToolkit();
	Image bufferimg = null;
	Graphics buffer = null;

	gameroom(String id) {
		glistset();
		this.id = id;
		this.add(label);

		this.setBounds(170, 100, 912, 753);
		this.setLayout(new BorderLayout());
		this.add(cv, "Center");
		this.add(title, "South");
		this.addKeyListener(this);

		createfish();
		key();

		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});
		this.setVisible(true);
	}

	private void glistset() {
		glist = gdao.gselect();
	}

	private void key() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(35);
						keyaction();
						cv.repaint();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			private void keyaction() {
				if (nm != null && spacechk != 0) {
					if (spacechk == 1) {
						nm.flag = false;
						nm.setY(5);
						if (nm.getY() > 685) {
							spacechk = 2;
						}
//if (nm.getY()
					}
					if (spacechk == 2) {
						nm.setY(-5);
					}
				}
			}
		}).start();
	}

	private void createfish() {

		new Thread(new Runnable() {
			@Override
			public void run() {
				Random r = new Random();
				int cnt = 1;
				while (true) {
					try {
						Thread.sleep(fishtimer + r.nextInt(3000));
						int x = 900;
						int y = r.nextInt(500) + 200;

						if (nowfish.size() < 5) {
							if (cnt % 3 == 0) {
								sf = fp.pattern(2, x, y);
							} else if (cnt % 5 == 0) {
								sf = fp.pattern(3, x, y);
							} else if (cnt % 13 == 0) {
								sf = fp.pattern(4, x, y);
							} else if (cnt % 21 == 0) {
								sf = fp.pattern(5, x, y);
							} else {
								sf = fp.pattern(1, x, y);
							}
							nowfish.add(sf);
							cnt++;
							System.out.println("현재 물고기 수 :  " + cnt);
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	class canvas extends Canvas {

		canvas() {
			this.setSize(400, 500);
			this.setBackground(Color.black);

		}

		@Override
		public void update(Graphics a) {
			paint(a);
		}

		@Override
		public void paint(Graphics a) {
//			if (buffer == null) {
//				bufferimg = createImage(912, 753);
//				if (bufferimg !=null) {
//					buffer = bufferimg.getGraphics();
//				}
//			}
			bufferimg = createImage(900,750);
			buffer = bufferimg.getGraphics();
			
			Image seting;
			seting = new ImageIcon("D:\\myDate\\java_src\\seastory\\img\\낚시게임배경1.jpg").getImage();
			buffer.drawImage(seting, 0, 0, this);

			Image fishimg = null;
			a.drawImage(fishimg, 100, 100, this);
			for (int i = 0; i < nowfish.size(); i++) {
				fishimg = nowfish.get(i).getFishimg();
				if (nowfish.get(i).getY() < 1) {
					System.out.println(nowfish.get(i).getName() + "잡음");

					holdfish.add(nowfish.get(i));
					nowfish.remove(i);
					i--;
					break;
				}
				if (nowfish.get(i).getY() >= 750 || nowfish.get(i).getX() < 1) {
					nowfish.remove(i);
					i--;
				} else {
					buffer.drawImage(fishimg, nowfish.get(i).getX(), nowfish.get(i).getY(), this);
				}
			}

			for (int i = 0; i < holdfish.size(); i++) { // 통발넣기

//				superfish f = nowfish.get(i);
				fishimg = holdfish.get(i).getFishimg();
//				StringTokenizer slash = new StringTokenizer(f.getName(), "대각선");
//				f.setFishimg(new ImageIcon("./img/" + slash.nextToken() + ".png").getImage());
				buffer.drawImage(fishimg, 20, 590, this);
				buffer.drawImage(netimg, 0, 575, this);
			}

			if (nm != null) {
				buffer.drawImage(nm.getNeedleimg(), nm.getX(), nm.getY(), 7, 13, this);
				buffer.setColor(Color.white);
				buffer.drawLine(nm.getX() + nm.getWidth() / 2, 6, nm.getX() + nm.getWidth() / 2, nm.getY());

			}

			if (nm != null) {
				for (int i = 0; i < nowfish.size(); i++) {  //현재 물

					superfish f = nowfish.get(i);
					int fstart = f.getX() - 5;
					int fend = f.getX() + f.getWidth() / 7; /// 난이도 결정

					int needlex = nm.getX();
					int needley = nm.getY();

					if (fstart < needlex && needlex < fend && spacechk % 2 == 0) {
						int fishstart = f.getY() + f.getHeight() / 2 - 5;
						int fishend = f.getY() + f.getHeight() / 2 + 5;
						if (fishstart < needley + nm.getHeight() && needley + nm.getHeight() < fishend) {
							if (f.isFlag() == true) {
								nowpoint = nowpoint + f.getSpeed();
								f.setFlag(false);
								f.setY(nm.getY() + nm.getHeight() - 24, "trash");
								f.setFishimg(new ImageIcon("./img/" + f.getName() + "대각선" + ".png").getImage());
								break;
							}
						}
					}

					if (enterchk == 3) {
						buffer.setFont(new Font("나눔고딕", Font.BOLD, 80));
						buffer.drawString("GameOver", 270, 330);
						nm.setX(0, "trash");

						for (int j = 0; j < glist.size(); j++) {
							if (glist.get(j).getId().equals(id)) {
								gdao.update(id, glist.get(j).getPoint() + nowpoint,
										glist.get(j).getFishcnt() + holdfish.size());
							}
//						nm = null;
						}
					}
				}
				buffer.setFont(new Font("나눔고딕", Font.BOLD, 15));
				buffer.drawString(holdfish.size() + "마리", 180, 640);
//			String sum = " ";
//			for (int i = 0; i < holdfish.size(); i++) {
//				sum = sum + holdfish.get(i).getName();
//			}
//			a.setFont(new Font("나눔고딕", Font.BOLD, 15));
//			a.drawString(sum , 40, 570);
				a.drawImage(bufferimg, 0, 0, null);
			}

		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			if (spacechk != 2) {
				spacechk++;
			}
			break;
		case KeyEvent.VK_ENTER:
			enterchk++;
			System.out.println(enterchk);
			spacechk = 0;
			nm.setX(1, "trash");
			nm.setY(6, "trash");
			nm.flag = true;
			nm.autopoint();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
