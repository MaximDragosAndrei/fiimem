/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiimem.gentree;

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
public class GenTreeService {

    static List<GenTree> getAllGenTree() {
         ArrayList<GenTree> result = new ArrayList<GenTree>();
		Connection con = MainApp.getDBConnection();
		String query = "SELECT * FROM GenTree";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				GenTree gentree = new GenTree();
                                gentree.setMid(rs.getInt("MID"));
//                                System.out.println("ok");
                                gentree.setMid2(rs.getInt("MID2"));
//                                System.out.println("ok");
                                gentree.setRelationship(rs.getInt("RELATIONSHIP"));
//                                System.out.println("ok");
				result.add(gentree);
			}
			stmt.close();
			rs.close();
			if (result.isEmpty())
				return null;
			else
				return result;
		} catch (Exception exc) {
			System.out.printf("[error][getAllGenTree] %s\n", exc.getMessage());
		}
		return null;
    }

    static GenTree getGenTree(int id) {
            GenTree result = null;
		Connection con = MainApp.getDBConnection();
		String query = "SELECT * FROM GenTree WHERE MID = " + id;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				GenTree gentree = new GenTree();
                                gentree.setMid(rs.getInt("MID"));
                                gentree.setMid2(rs.getInt("MID2"));
                                gentree.setRelationship(rs.getInt("RELATIONSHIP"));
				result=gentree;
			}
			stmt.close();
			rs.close();
			if (result == null)
				return null;
			else
				return result;
		} catch (Exception exc) {
			System.out.printf("[error][getGenTree] %s\n", exc.getMessage());
		}
		return null;
    }

    static int updateGenTree(int id, GenTree gentree) {
        int result;
		Connection con = MainApp.getDBConnection();
		String query = "UPDATE gentree SET mid = ?, mid2 = ?, relationship = ? where mid = ?";

		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, gentree.getMid());
			pstmt.setInt(2, gentree.getMid2());
                        pstmt.setInt(3, gentree.getRelationship());
			result = pstmt.executeUpdate();
			pstmt.close();
			return result;
		} catch (Exception exc) {
			System.out.printf("[error][updateGenTree] %s\n", exc.getMessage());
		}
		return 0;
    }

    static int deleteGenTree(int id) {
        int result;
		Connection con = MainApp.getDBConnection();
		String query = "DELETE FROM GENTREE WHERE mid = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();
			pstmt.close();
			return result;
		} catch (Exception exc) {
			System.out.printf("[error][deleteGenTree] %s\n", exc.getMessage());
		}
		return 0;
    }

    static int insertGenTree(GenTree gentree) {
        int result;
		Connection con = MainApp.getDBConnection();
		String query = "INSERT INTO GENTREE (MID, MID2, RELATIONSHIP " + "VALUES (?,?,?)";

		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, gentree.getMid());
			pstmt.setInt(1, gentree.getMid2());
                        pstmt.setInt(1, gentree.getRelationship());
			result = pstmt.executeUpdate();
			pstmt.close();
			return result;
		} catch (Exception exc) {
			System.out.printf("[error][insertGenTree] %s\n", exc.getMessage());
		}
		return 0;
    }
    
}
