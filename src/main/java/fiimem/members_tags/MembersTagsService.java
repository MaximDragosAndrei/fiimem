/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiimem.members_tags;

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
public class MembersTagsService {
    static List<MemberTag> getAllMembersTags() {
       ArrayList<MemberTag> result = new ArrayList<MemberTag>();
		Connection con = MainApp.getDBConnection();
		String query = "SELECT * FROM Members_Tags";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				MemberTag memberTag = new MemberTag();
                                memberTag.setTid(rs.getInt("TID"));
                                memberTag.setMid(rs.getInt("MID"));
				result.add(memberTag);
			}
			stmt.close();
			rs.close();
			if (result.isEmpty())
				return null;
			else
				return result;
		} catch (Exception exc) {
			System.out.printf("[error][getAllMembersTags] %s\n", exc.getMessage());
		}
		return null;
    }
    
    static List<MemberTag> getMembersTags(int id){
        ArrayList<MemberTag> result = new ArrayList<MemberTag>();
        Connection con = MainApp.getDBConnection();
		String query = "SELECT * FROM Members_Tags WHERE MID = "+id;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				MemberTag memberTag = new MemberTag();
                                memberTag.setTid(rs.getInt("TID"));
                                memberTag.setMid(rs.getInt("MID"));
				result.add(memberTag);
			}
			stmt.close();
			rs.close();
			if (result.isEmpty())
				return null;
			else
				return result;
		} catch (Exception exc) {
			System.out.printf("[error][getMembersTags] %s\n", exc.getMessage());
		}
		return null;        
    }
    
    static int updateMembersTags(int id, MemberTag memberTag) {
        int result;
		Connection con = MainApp.getDBConnection();
		String query = "UPDATE MEMBERS_TAGS SET TID = ?, MID = ? WHERE MID = ?";

		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1,memberTag.getTid());
                        pstmt.setInt(2, memberTag.getMid());
			pstmt.setInt(3, id);
			result = pstmt.executeUpdate();
			pstmt.close();
			return result;
		} catch (Exception exc) {
			System.out.printf("[error][updateMembersTags] %s\n", exc.getMessage());
		}
		return 0;
    }
    
    static int deleteMembersTags(int id){
        int result;
		Connection con = MainApp.getDBConnection();
		String query = "DELETE FROM MEMBERS_TAGS WHERE TID = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();
			pstmt.close();
			return result;
		} catch (Exception exc) {
			System.out.printf("[error][deleteMembersTags] %s\n", exc.getMessage());
		}
		return 0;        
    }
    
    static int insertMembersTags(MemberTag memberTag) {
        int result;
		Connection con = MainApp.getDBConnection();
		String query = "INSERT INTO MEMBERS_TAGS (TID, MID)"
                        + "VALUES (?,?)";

		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, memberTag.getTid());
			pstmt.setInt(2, memberTag.getMid());
			result = pstmt.executeUpdate();
			pstmt.close();
			return result;
		} catch (Exception exc) {
			System.out.printf("[error][insertMembersTags] %s\n", exc.getMessage());
		}
		return 0;
    }
}
