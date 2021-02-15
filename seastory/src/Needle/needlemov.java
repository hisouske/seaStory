package Needle;

import java.util.Random;

public class needlemov extends myneedle {
	public boolean flag = true;
	public boolean flag2 = true;

	public needlemov() {
		autopoint();
	}

	public void autopoint() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (flag) {
					try {
						if (getX() == 890) {
							while (getX() > 1 && flag) {
								
								Thread.sleep(speed);
								setX(-1);
							}
						}
						if (getX() == 1) {
							while (getX() < 890 && flag) {
								Thread.sleep(speed);
								setX(1);

							}
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
