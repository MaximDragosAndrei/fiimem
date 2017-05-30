/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiimem.history;

import fiimem.MainApp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andy
 */
public class HistoryService {

    static List<History> getAllHistory() {
         ArrayList<History> result = new ArrayList<History>();
		Connection con = MainApp.getDBConnection();
		String query = "SELECT * FROM History";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				History history = new History();
                                history.setMid(rs.getInt("MID"));
//                                System.out.println("ok");
                                history.setHid(rs.getInt("HID"));
//                                System.out.println("ok");
                                history.setLogindate(rs.getString("LOGINDATE"));
//                                System.out.println("ok");
                                history.setLogout(rs.getString("LOGOUT"));
//                                System.out.println("ok");
				result.add(history);
			}
			stmt.close();
			rs.close();
			if (result.isEmpty())
				return null;
			else
				return result;
		} catch (Exception exc) {
			System.out.printf("[error][getAllHistory", exc.getMessage());
		}
		return null;
    }

    static History getHistory(int id) {
            History result = null;
		Connection con = MainApp.getDBConnection();
		String query = "SELECT * FROM History WHERE MID = " + id;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				History history = new History();
                                history.setMid(rs.getInt("MID"));
                                history.setHid(rs.getInt("HID"));
                                history.setLogindate(rs.getString("LOGINDATE"));
                                history.setLogout(rs.getString("LOGOUT"));
				result=history;
			}
			stmt.close();
			rs.close();
			if (result == null)
				return null;
			else
				return result;
		} catch (Exception exc) {
			System.out.printf("[error][getHistory] %s\n", exc.getMessage());
		}
		return null;
    }

    static int updateHistory(int id, History history) {
        int result;
		Connection con = MainApp.getDBConnection();
		String query = "UPDATE history SET mid = ?, hid = ?, logindate = ?, logout = ? where mid = ?";

		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, history.getMid());
                        pstmt.setInt(1, history.getHid());
			pstmt.setString(2, history.getLogindate());
                        pstmt.setString(2, history.getLogout());
			result = pstmt.executeUpdate();
			pstmt.close();
			return result;
		} catch (Exception exc) {
			System.out.printf("[error][updateHistory] %s\n", exc.getMessage());
		}
		return 0;
    }

    static int deleteHistory(int id) {
        int result;
		Connection con = MainApp.getDBConnection();
		String query = "DELETE FROM HISTORY WHERE mid = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();
			pstmt.close();
			return result;
		} catch (Exception exc) {
			System.out.printf("[error][deleteHistory] %s\n", exc.getMessage());
		}
		return 0;
    }

    static int insertHistory(History history) {
        int result;
		Connection con = MainApp.getDBConnection();
		String query = "INSERT INTO HISTORY (MID, HID, LOGINDATE, LOGOUT " + "VALUES (?,?,?,?)";

		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, history.getMid());
                        pstmt.setInt(1, history.getHid());
			pstmt.setString(2, history.getLogindate());
                        pstmt.setString(3, history.getLogout());
			result = pstmt.executeUpdate();
			pstmt.close();
			return result;
		} catch (Exception exc) {
			System.out.printf("[error][insertHistory] %s\n", exc.getMessage());
		}
		return 0;
    }
    
}
