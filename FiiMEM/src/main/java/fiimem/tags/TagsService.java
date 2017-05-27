/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiimem.tags;

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
public class TagsService {

    static List<Tag> getAllTags() {
        ArrayList<Tag> result = new ArrayList<Tag>();
        Connection con = MainApp.getDBConnection();
        String query = "SELECT * FROM Tags";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Tag tag = new Tag();
                tag.setTid(rs.getInt("TID"));
                tag.setName(rs.getString("NAME"));
                result.add(tag);
            }
            stmt.close();
            rs.close();
            if (result.isEmpty()) {
                return null;
            } else {
                return result;
            }
        } catch (Exception exc) {
            System.out.printf("[error][getAllTags] %s\n", exc.getMessage());
        }
        return null;
    }

    static Tag getTags(int id) {
        Tag result = null;
        Connection con = MainApp.getDBConnection();
        String query = "SELECT * FROM Tags WHERE TID = " + id;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Tag tag = new Tag();
                tag.setTid(rs.getInt("TID"));
                tag.setName(rs.getString("NAME"));
                result = tag;
            }
            stmt.close();
            rs.close();
            if (result == null) {
                return null;
            } else {
                return result;
            }
        } catch (Exception exc) {
            System.out.printf("[error][getTags] %s\n", exc.getMessage());
        }
        return null;
    }

    static int updateTags(int id, Tag tag) {
        int result;
        Connection con = MainApp.getDBConnection();
        String query = "UPDATE TAGS SET TID = ?, NAME = ? WHERE TID = ?";

        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, tag.getTid());
            pstmt.setString(2, tag.getName());
            pstmt.setInt(3, id);
            result = pstmt.executeUpdate();
            pstmt.close();
            return result;
        } catch (Exception exc) {
            System.out.printf("[error][updateTags] %s\n", exc.getMessage());
        }
        return 0;
    }

    static int deleteTags(int id) {
        int result;
        Connection con = MainApp.getDBConnection();
        String query = "DELETE FROM TAGS WHERE TID = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            result = pstmt.executeUpdate();
            pstmt.close();
            return result;
        } catch (Exception exc) {
            System.out.printf("[error][deleteTags] %s\n", exc.getMessage());
        }
        return 0;
    }

    static int insertTags(Tag tag) {
        int result;
        Connection con = MainApp.getDBConnection();
        String query = "INSERT INTO TAGS (TID, NAME)"
                + "VALUES (?,?)";

        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, tag.getTid());
            pstmt.setString(2, tag.getName());
            result = pstmt.executeUpdate();
            pstmt.close();
            return result;
        } catch (Exception exc) {
            System.out.printf("[error][insertTags] %s\n", exc.getMessage());
        }
        return 0;
    }

}
