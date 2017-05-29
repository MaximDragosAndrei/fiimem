/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiimem.privacy;

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
public class PrivacyService {
    static List<Privacy> getAllPrivacy() {
        ArrayList<Privacy> result = new ArrayList<Privacy>();
		Connection con = MainApp.getDBConnection();
		String query = "SELECT * FROM Privacy";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Privacy privacy = new Privacy();
                                privacy.setPid(rs.getInt("PID"));
                                privacy.setRights("RIGHTS");
				result.add(privacy);
			}
			stmt.close();
			rs.close();
			if (result.isEmpty())
				return null;
			else
				return result;
		} catch (Exception exc) {
			System.out.printf("[error][getAllPrivacy] %s\n", exc.getMessage());
		}
		return null;
    }
    
    static Privacy getPrivacy(int id){
        Privacy result = null;
        Connection con = MainApp.getDBConnection();
		String query = "SELECT * FROM Privacy WHERE PID = "+id;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Privacy privacy = new Privacy();
                                privacy.setPid(rs.getInt("PID"));
                                privacy.setRights("RIGHTS");
				result=privacy;
			}
			stmt.close();
			rs.close();
			if (result==null)
				return null;
			else
				return result;
		} catch (Exception exc) {
			System.out.printf("[error][getPrivacy] %s\n", exc.getMessage());
		}
		return null;        
    }
    
    static int updatePrivacy(int id, Privacy privacy) {
        int result;
		Connection con = MainApp.getDBConnection();
		String query = "UPDATE PRIVACY SET PID = ?, RIGHTS = ? WHERE PID = ?";

		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1,privacy.getPid());
                        pstmt.setString(2, privacy.getRights());
			pstmt.setInt(3, id);
			result = pstmt.executeUpdate();
			pstmt.close();
			return result;
		} catch (Exception exc) {
			System.out.printf("[error][updatePrivacy] %s\n", exc.getMessage());
		}
		return 0;
    }
    
    static int deletePrivacy(int id){
        int result;
		Connection con = MainApp.getDBConnection();
		String query = "DELETE FROM PRIVACY WHERE PID = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();
			pstmt.close();
			return result;
		} catch (Exception exc) {
			System.out.printf("[error][deletePrivacy] %s\n", exc.getMessage());
		}
		return 0;        
    }
    
    static int insertPrivacy(Privacy privacy) {
        int result;
		Connection con = MainApp.getDBConnection();
		String query = "INSERT INTO PRIVACY (PID, RIGHTS)"
                        + "VALUES (?,?)";

		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, privacy.getPid());
			pstmt.setString(2, privacy.getRights());
			result = pstmt.executeUpdate();
			pstmt.close();
			return result;
		} catch (Exception exc) {
			System.out.printf("[error][insertPrivacy] %s\n", exc.getMessage());
		}
		return 0;
    }
}
