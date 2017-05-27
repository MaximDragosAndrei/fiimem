/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiimem.tags_files;

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
public class TagsFilesService {
     static List<TagFile> getAllTagsFiles() {
        ArrayList<TagFile> result = new ArrayList<TagFile>();
        Connection con = MainApp.getDBConnection();
        String query = "SELECT * FROM Tags_Files";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                TagFile tfile = new TagFile();
                tfile.setTid(rs.getInt("TID"));
                tfile.setFid(rs.getInt("FID"));
                tfile.setFilesmembersmid(rs.getInt("FILESMEMBERSMID"));
                result.add(tfile);
            }
            stmt.close();
            rs.close();
            if (result.isEmpty()) {
                return null;
            } else {
                return result;
            }
        } catch (Exception exc) {
            System.out.printf("[error][getAllTagsFiles] %s\n", exc.getMessage());
        }
        return null;
    }

    static ArrayList<TagFile> getTagsFiles(int id) {
        ArrayList<TagFile> result = new ArrayList<TagFile>();
        Connection con = MainApp.getDBConnection();
        String query = "SELECT * FROM Tags_Files WHERE FILESMEMBERSMID = "+id;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                TagFile tfile = new TagFile();
                tfile.setTid(rs.getInt("TID"));
                tfile.setFid(rs.getInt("FID"));
                tfile.setFilesmembersmid(rs.getInt("FILESMEMBERSMID"));
                result.add(tfile);
            }
            stmt.close();
            rs.close();
            if (result.isEmpty()) {
                return null;
            } else {
                return result;
            }
        } catch (Exception exc) {
            System.out.printf("[error][getTagsFiles] %s\n", exc.getMessage());
        }
        return null;
    }

    static int updateTagsFiles(int id, TagFile tfile) {
        int result;
        Connection con = MainApp.getDBConnection();
        String query = "UPDATE Tags_Files SET TID = ?, FID = ?, FILESMEMBERSMID = ? WHERE FILESMEMBERSMID = ?";

        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, tfile.getTid());
            pstmt.setInt(2, tfile.getFid());
            pstmt.setInt(3,tfile.getFilesmembersmid());
            pstmt.setInt(4, id);
            result = pstmt.executeUpdate();
            pstmt.close();
            return result;
        } catch (Exception exc) {
            System.out.printf("[error][updateTagsFiles] %s\n", exc.getMessage());
        }
        return 0;
    }

    static int deleteTagsFiles(int id) {
        int result;
        Connection con = MainApp.getDBConnection();
        String query = "DELETE FROM Tags_Files WHERE FILESMEMBERSMID = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            result = pstmt.executeUpdate();
            pstmt.close();
            return result;
        } catch (Exception exc) {
            System.out.printf("[error][deleteTagsFiles] %s\n", exc.getMessage());
        }
        return 0;
    }

    static int insertTagsFiles(TagFile tfile) {
        int result;
        Connection con = MainApp.getDBConnection();
        String query = "INSERT INTO Tags_Files (TID, FID, FILESMEMBERSMID)"
                + "VALUES (?,?,?)";

        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, tfile.getTid());
            pstmt.setInt(2, tfile.getFid());
            pstmt.setInt(3, tfile.getFilesmembersmid());
            result = pstmt.executeUpdate();
            pstmt.close();
            return result;
        } catch (Exception exc) {
            System.out.printf("[error][insertTagsFiles] %s\n", exc.getMessage());
        }
        return 0;
    }
}
