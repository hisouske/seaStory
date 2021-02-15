package Fish;

import java.util.Random;

public class fish_03 extends superfish {

	fish_03(int x, int y, fishDTO fishdto) {
		super(fishdto, x, y);
		autopoint();
	}

	private void autopoint() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				Random r = new Random();
				while (getY() > 1 && flag) {
					try {
						Thread.sleep(speed);

						if (getX() > 850) {
							setX(-1);
							setY(r.nextInt(1));

						}

						if (getX() > 300) {
							setX(-2);
							setY(r.nextInt(2));

						} else {

							setX(-1);
							setY(-r.nextInt(2));

						}

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				while (flag == false && getY() > 0) {
					try {
						Thread.sleep(7);
						if (getY() > 0) {
							setY(-1);
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
