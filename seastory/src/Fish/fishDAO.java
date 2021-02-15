package Fish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Guest.conn;
import Guest.interDAO;
import Guest.superguest;

public class fishDAO extends conn implements interDAO {

	private static fishDAO fdao = null;

	public fishDAO() {
		
		Connection Connection = super.connection();
	
	}

	public static fishDAO getInstance() {
		if (fdao == null) {
			fdao = new fishDAO();
		
		}
		return fdao;
	}

	@Override
	public void insert(String id) { // 사용안함
	}

	@Override
	public void update(String id, int point, int fishcnt) { // 사용안함
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<superguest> gselect() { // 사용안함
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<fishDTO> fselect() {
		Connection conn = connection();
		PreparedStatement ppst = null;
		ResultSet rs = null;
		ArrayList<fishDTO> flist = null;

		try {

			ppst = conn.prepareStatement("select * from fish");
			rs = ppst.executeQuery();

			if (rs.next()) {
				flist = new ArrayList<fishDTO>();

				do {
					fishDTO sf = new fishDTO();

					sf.setName(rs.getString("name"));
					sf.setSpeed(rs.getInt("speed"));
					sf.setMovement(rs.getInt("movement"));
					sf.setProbablity(rs.getInt("probablity"));

					flist.add(sf);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("SQL Error");
		} finally {
			try {
				if (ppst != null)
					ppst.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				System.out.println("connection close error");
			}
		}
		return flist;
	}
}
