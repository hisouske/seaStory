package Fish;

import java.util.ArrayList;
public class fishpattern {
	ArrayList<fishDTO> fishlist = new ArrayList<>();
	fishDAO fishdao = new fishDAO();
	superfish sf = null;

	public superfish pattern(int fishtype, int x, int y) {
		fishdao = fishdao.getInstance();
		fishlist = fishdao.fselect();
		switch (fishtype) {
		case 1:
			sf = new fish_01(x, y, fishlist.get(0));
			break;
		case 2:
			sf = new fish_02(x, y, fishlist.get(1));
			break;
		case 3:
			sf = new fish_03(x, y, fishlist.get(2));
			break;
		case 4:
			sf = new fish_04(x, y, fishlist.get(3));
			break;
		case 5:
			sf = new fish_05(x, y, fishlist.get(4));
			break;
		}
		return sf;

	}

}
