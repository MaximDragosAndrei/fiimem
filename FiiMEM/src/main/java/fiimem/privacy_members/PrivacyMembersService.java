/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiimem.privacy_members;

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
public class PrivacyMembersService {
    static List<PrivacyMember> getAllPrivacyMembers() {
        ArrayList<PrivacyMember> result = new ArrayList<PrivacyMember>();
		Connection con = MainApp.getDBConnection();
		String query = "SELECT * FROM Privacy_Members";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				PrivacyMember pmember = new PrivacyMember();
                                pmember.setPid(rs.getInt("TID"));
                                pmember.setMid(rs.getInt("MID"));
				result.add(pmember);
			}
			stmt.close();
			rs.close();
			if (result.isEmpty())
				return null;
			else
				return result;
		} catch (Exception exc) {
			System.out.printf("[error][getAllPrivacyMembers] %s\n", exc.getMessage());
		}
		return null;
    }
    
    static List<PrivacyMember> getPrivacyMembers(int id){
        ArrayList<PrivacyMember> result = new ArrayList<PrivacyMember>();
		Connection con = MainApp.getDBConnection();
		String query = "SELECT * FROM Privacy_Members = "+id;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				PrivacyMember pmember = new PrivacyMember();
                                pmember.setPid(rs.getInt("TID"));
                                pmember.setMid(rs.getInt("MID"));
				result.add(pmember);
			}
			stmt.close();
			rs.close();
			if (result.isEmpty())
				return null;
			else
				return result;
		} catch (Exception exc) {
			System.out.printf("[error][getPrivacyMembers] %s\n", exc.getMessage());
		}
		return null;       
    }
    
    static int updatePrivacyMembers(int id, PrivacyMember pmember) {
        int result;
		Connection con = MainApp.getDBConnection();
		String query = "UPDATE Privacy_Members SET MID = ?, PID = ? WHERE MID = ?";

		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1,pmember.getMid());
                        pstmt.setInt(2, pmember.getPid());
			pstmt.setInt(3, id);
			result = pstmt.executeUpdate();
			pstmt.close();
			return result;
		} catch (Exception exc) {
			System.out.printf("[error][updatePrivacyMembers] %s\n", exc.getMessage());
		}
		return 0;
    }
    
    static int deletePrivacyMembers(int id){
        int result;
		Connection con = MainApp.getDBConnection();
		String query = "DELETE FROM Privacy_Members WHERE MID = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();
			pstmt.close();
			return result;
		} catch (Exception exc) {
			System.out.printf("[error][deletePrivacyMembers] %s\n", exc.getMessage());
		}
		return 0;        
    }
    
    static int insertPrivacyMembers(PrivacyMember pmember) {
        int result;
		Connection con = MainApp.getDBConnection();
		String query = "INSERT INTO Privacy_Members (PID, MID)"
                        + "VALUES (?,?)";

		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, pmember.getPid());
			pstmt.setInt(2, pmember.getMid());
			result = pstmt.executeUpdate();
			pstmt.close();
			return result;
		} catch (Exception exc) {
			System.out.printf("[error][insertPrivacyMembers] %s\n", exc.getMessage());
		}
		return 0;
    }

}
