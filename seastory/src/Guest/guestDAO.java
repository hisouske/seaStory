package Guest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Fish.fishDTO;

public class guestDAO extends conn implements interDAO {
	private static guestDAO gdao = null;

	public guestDAO() {
		Connection Connection = super.connection();
	}

	public static guestDAO getInstance() {
		if (gdao == null) {
			gdao = new guestDAO();
		}
		return gdao;
	}

	@Override
	public void insert(String id) {
		Connection conn = connection();
		PreparedStatement ppst = null;
		try {
			ppst = conn.prepareStatement("insert into guest values(?, ? ,? )");
			ppst.setString(1, id);
			ppst.setInt(2, 0);
			ppst.setInt(3, 0);
			ppst.executeUpdate();
		} catch (Exception e) {
			System.out.println("SQL Error2222");
		} finally {
			try {
				if (ppst != null)
					ppst.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("connection close error");
			}
		}
	}

	@Override
	public void update(String id, int point, int fishcnt) {
		Connection conn = connection();
		PreparedStatement ppst = null;

		try {

			ppst = conn.prepareStatement("update guest set point=?,fishcnt=? where id=?");

			ppst.setInt(1, point);
			ppst.setInt(2, fishcnt);
			ppst.setString(3, id);

			ppst.executeUpdate();
		} catch (Exception e) {

		} finally {
			try {
				if (ppst != null)
					ppst.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("connection close error");
			}
		}

	}

	@Override
	public ArrayList<superguest> gselect() {
		Connection conn = connection();
		PreparedStatement ppst = null;
		ResultSet rs = null;
		ArrayList<superguest> glist = null;

		try {

			ppst = conn.prepareStatement("select * from guest");
			rs = ppst.executeQuery();

			if (rs.next()) {
				glist = new ArrayList<superguest>();

				do {
					superguest sg = new superguest();
					sg.setId(rs.getString("id"));
					sg.setPoint(rs.getInt("point"));
					sg.setFishcnt(rs.getInt("fishcnt"));

					glist.add(sg);
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
		return glist;
	}
	public ArrayList<superguest> ggselect() {
		Connection conn = connection();
		PreparedStatement ppst = null;
		ResultSet rs = null;
		ArrayList<superguest> glist = null;

		try {

			ppst = conn.prepareStatement("select * from guest");
			rs = ppst.executeQuery();

			if (rs.next()) {
				glist = new ArrayList<superguest>();

				do {
					superguest sg = new superguest();
					sg.setId(rs.getString("id"));
					sg.setPoint(rs.getInt("point"));
					sg.setFishcnt(rs.getInt("fishcnt"));

					glist.add(sg);
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
		return glist;
	}
	@Override
	public ArrayList<fishDTO> fselect() { // 사용안함
		// TODO Auto-generated method stub
		return null;
	}
}
