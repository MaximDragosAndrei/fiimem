/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiimem.filme;
import fiimem.MainApp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Asus
 */
public class FilmeService {
    static List<Film> getAllFilme() {
        ArrayList<Film> result = new ArrayList<Film>();
        Connection con = MainApp.getDBConnection();
        String query = "SELECT * FROM Files";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Film file = new Film();
                file.setAddress(rs.getString("ADDRESS"));
                file.setFid(rs.getInt("FID"));
                file.setFormat(rs.getString("FORMAT"));
                file.setByteArray(rs.getBytes("FILES"));
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
            System.out.printf("[error][getAllFilme] %s\n", exc.getMessage());
        }
        return null;
    }

    static Film getFilme(int id) {
        Film result = null;
        Connection con = MainApp.getDBConnection();
        String query = "SELECT * FROM Files WHERE FID = " + id;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Film file = new Film();
                file.setAddress(rs.getString("ADDRESS"));
                file.setFid(rs.getInt("FID"));
                file.setFormat(rs.getString("FORMAT"));
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

   
    static int deleteFilme(int id) {
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
}
