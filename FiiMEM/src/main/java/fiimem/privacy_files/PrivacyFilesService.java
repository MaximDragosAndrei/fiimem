/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiimem.privacy_files;

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
public class PrivacyFilesService {

    static List<PrivacyFile> getAllPrivacyFiles() {
        ArrayList<PrivacyFile> result = new ArrayList<PrivacyFile>();
        Connection con = MainApp.getDBConnection();
        String query = "SELECT * FROM Privacy_Files";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                PrivacyFile pfile = new PrivacyFile();
                pfile.setPid(rs.getInt("PID"));
                pfile.setFid(rs.getInt("FID"));
                pfile.setFilesmembersmid(rs.getInt("FILESMEMBERSMID"));
                result.add(pfile);
            }
            stmt.close();
            rs.close();
            if (result.isEmpty()) {
                return null;
            } else {
                return result;
            }
        } catch (Exception exc) {
            System.out.printf("[error][getAllPrivacyFiles] %s\n", exc.getMessage());
        }
        return null;
    }

    static ArrayList<PrivacyFile> getPrivacyFiles(int id) {
        ArrayList<PrivacyFile> result = new ArrayList<PrivacyFile>();
        Connection con = MainApp.getDBConnection();
        String query = "SELECT * FROM Privacy_Files WHERE FILESMEMBERSMID = "+id;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                PrivacyFile pfile = new PrivacyFile();
                pfile.setPid(rs.getInt("PID"));
                pfile.setFid(rs.getInt("FID"));
                pfile.setFilesmembersmid(rs.getInt("FILESMEMBERSMID"));
                result.add(pfile);
            }
            stmt.close();
            rs.close();
            if (result.isEmpty()) {
                return null;
            } else {
                return result;
            }
        } catch (Exception exc) {
            System.out.printf("[error][getPrivacyFiles] %s\n", exc.getMessage());
        }
        return null;
    }

    static int updatePrivacyFiles(int id, PrivacyFile pfile) {
        int result;
        Connection con = MainApp.getDBConnection();
        String query = "UPDATE Privacy_Files SET PID = ?, FID = ?, FILESMEMBERSMID = ? WHERE FILESMEMBERSMID = ?";

        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, pfile.getPid());
            pstmt.setInt(2, pfile.getFid());
            pstmt.setInt(3,pfile.getFilesmembersmid());
            pstmt.setInt(4, id);
            result = pstmt.executeUpdate();
            pstmt.close();
            return result;
        } catch (Exception exc) {
            System.out.printf("[error][updatePrivacyFiles] %s\n", exc.getMessage());
        }
        return 0;
    }

    static int deletePrivacyFiles(int id) {
        int result;
        Connection con = MainApp.getDBConnection();
        String query = "DELETE FROM Privacy_Files WHERE FILESMEMBERSMID = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            result = pstmt.executeUpdate();
            pstmt.close();
            return result;
        } catch (Exception exc) {
            System.out.printf("[error][deletePrivacyFiles] %s\n", exc.getMessage());
        }
        return 0;
    }

    static int insertPrivacyFiles(PrivacyFile pfile) {
        int result;
        Connection con = MainApp.getDBConnection();
        String query = "INSERT INTO Privacy_Files (PID, FID, FILESMEMBERSMID)"
                + "VALUES (?,?,?)";

        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, pfile.getPid());
            pstmt.setInt(2, pfile.getFid());
            pstmt.setInt(3, pfile.getFilesmembersmid());
            result = pstmt.executeUpdate();
            pstmt.close();
            return result;
        } catch (Exception exc) {
            System.out.printf("[error][insertPrivacyFiles] %s\n", exc.getMessage());
        }
        return 0;
    }
}
