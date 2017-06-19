/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiimem.files;

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
public class FilesService {

    static List<File> getAllFiles() {
        ArrayList<File> result = new ArrayList<File>();
        Connection con = MainApp.getDBConnection();
        String query = "SELECT * FROM Files";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                File file = new File();
                file.setAddress(rs.getString("ADDRESS"));
                file.setFid(rs.getInt("FID"));
                file.setFormat(rs.getString("FORMAT"));
                file.setMembersmid(rs.getInt("MEMBERSMID"));
                file.setName(rs.getString("NAME"));
                result.add(file);
            }
            stmt.close();
            rs.close();
            if (result.isEmpty()) {
                return null;
            } else {
                return result;
            }
        } catch (Exception exc) {
            System.out.printf("[error][getAllFiles] %s\n", exc.getMessage());
        }
        return null;
    }

    static File getFiles(int id) {
        File result = null;
        Connection con = MainApp.getDBConnection();
        String query = "SELECT * FROM Files WHERE FID = " + id;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                File file = new File();
                file.setAddress(rs.getString("ADDRESS"));
                file.setFid(rs.getInt("FID"));
                file.setFormat(rs.getString("FORMAT"));
                //file.setByteArray(rs.getBytes("FILES"));
                //file.setIs(rs.getBinaryStream("FILES"));
                file.setByteArray(rs.getBytes("FILES"));
                file.setMembersmid(rs.getInt("MEMBERSMID"));
                file.setName(rs.getString("NAME"));
                result = file;
            }
            stmt.close();
            rs.close();
            if (result == null) {
                return null;
            } else {
                return result;
            }
        } catch (Exception exc) {
            System.out.printf("[error][getFiles] %s\n", exc.getMessage());
        }
        return null;
    }

    static int updateFiles(int id, File file) {
        int result;
        Connection con = MainApp.getDBConnection();
        String query = "UPDATE FILES SET FID = ?, MEMBERSMID = ?, ADDRESS = ?, NAME = ?,"
                + "FORMAT = ?, FILES = ?  WHERE FID = ?";

        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, file.getFid());
            pstmt.setInt(2, file.getMembersmid());
            pstmt.setString(3, file.getAddress());
            pstmt.setString(4, file.getName());
            pstmt.setString(5, file.getFormat());
            //pstmt.setBlob(6, file.getByteArray());
            //pstmt.setBinaryStream(6, file.getIs());
            pstmt.setBytes(6, file.getByteArray());
            pstmt.setInt(7, id);
            result = pstmt.executeUpdate();
            pstmt.close();
            return result;
        } catch (Exception exc) {
            System.out.printf("[error][updateFiles] %s\n", exc.getMessage());
        }
        return 0;
    }

    static int deleteFiles(int id) {
        int result;
        Connection con = MainApp.getDBConnection();
        String query = "DELETE FROM FILES WHERE FID = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            result = pstmt.executeUpdate();
            pstmt.close();
            return result;
        } catch (Exception exc) {
            System.out.printf("[error][deleteFiles] %s\n", exc.getMessage());
        }
        return 0;
    }

    static int insertFiles(File file) {
        int result;
        Connection con = MainApp.getDBConnection();
        String query = "INSERT INTO FILES (FID, MEMBERSMID, ADDRESS, NAME, FORMAT, FILES)"
                + "VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, file.getFid());
            pstmt.setInt(2, file.getMembersmid());
            pstmt.setString(3, file.getAddress());
            pstmt.setString(4, file.getName());
            pstmt.setString(5, file.getFormat());
            //pstmt.setBlob(6, file.getIs());
            //pstmt.setBlob(6, file.getBlob());
            pstmt.setBytes(6, file.getByteArray());
            result = pstmt.executeUpdate();
            pstmt.close();
            return result;
        } catch (Exception exc) {
            System.out.printf("[error][insertFiles] %s\n", exc.getMessage());
        }
        return 0;
    }
}
