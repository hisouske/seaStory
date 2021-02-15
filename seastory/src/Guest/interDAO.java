package Guest;

import java.util.ArrayList;

import Fish.fishDTO;

public interface interDAO {

	
	void insert(String id);
	void update(String id, int point, int fishcnt);
	ArrayList<superguest> gselect();
	ArrayList<fishDTO> fselect();
}
