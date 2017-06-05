/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiimem.members;

import fiimem.MainApp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import security.HashConvertor;

/**
 *
 * @author andy
 */
public class MembersService {

    static List<Member> getAllMembers() {
        ArrayList<Member> result = new ArrayList<Member>();
        Connection con = MainApp.getDBConnection();
        String query = "SELECT * FROM Members";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Member member = new Member();
                member.setAddress(rs.getString("ADDRESS"));
                member.setBithdate(rs.getString("BITHDATE"));
                member.setDeceaseddate(rs.getString("DECEASEDDATE"));
                member.setEmail(rs.getString("EMAIL"));
                member.setFictiv(rs.getInt("FICTIV"));
                member.setFirstname(rs.getString("FIRSTNAME"));
                member.setMid(rs.getInt("MID"));
                member.setPassword(rs.getString("PASSWORD"));
                member.setPhone(rs.getInt("PHONE"));
                member.setSurname(rs.getString("SURNAME"));
                member.setUsername(rs.getString("USERNAME"));
                result.add(member);
            }
            stmt.close();
            rs.close();
            if (result.isEmpty()) {
                System.out.println("[success][back][getAllMembers]: No members");
                return null;
            } else {
                System.out.println("[success][back][getAllMembers]");
                return result;
            }
        } catch (Exception exc) {
            System.out.printf("[error][back][getAllMembers] %s\n", exc.getMessage());
        }
        return null;
    }

    static Member getMembers(int id) {
        Member result = null;
        Connection con = MainApp.getDBConnection();
        String query = "SELECT * FROM Members WHERE MID = " + id;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Member member = new Member();
                member.setAddress(rs.getString("ADDRESS"));
                member.setBithdate(rs.getString("BITHDATE"));
                member.setDeceaseddate(rs.getString("DECEASEDDATE"));
                member.setEmail(rs.getString("EMAIL"));
                member.setFictiv(rs.getInt("FICTIV"));
                member.setFirstname(rs.getString("FIRSTNAME"));
                member.setMid(rs.getInt("MID"));
                member.setPassword(rs.getString("PASSWORD"));
                member.setPhone(rs.getInt("PHONE"));
                member.setSurname(rs.getString("SURNAME"));
                member.setUsername(rs.getString("USERNAME"));
                result = member;
            }
            stmt.close();
            rs.close();
            if (result == null) {
                System.out.println("[success][back][getMembers]: No such member");
                return null;
            } else {
                System.out.println("[success][back][getMembers]");
                return result;
            }
        } catch (Exception exc) {
            System.out.printf("[error][back][getMembers] %s\n", exc.getMessage());
        }
        return null;
    }

//    static int updatePassword() {
//        int result;
//        HashConvertor hash = new HashConvertor();
//        Connection con = MainApp.getDBConnection();
//        String query = "UPDATE members SET password = ? where mid = ?";
//        try {
//            PreparedStatement pstmt = con.prepareStatement(query);
//            String selectQuery = "SELECT * FROM Members";
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery(selectQuery);
//            while (rs.next()) {
//                int mid = rs.getInt("MID");
//                String password = rs.getString("PASSWORD");
//                pstmt.setString(1, hash.convert(password));
//                pstmt.setInt(2, mid);
//                result = pstmt.executeUpdate();
//            }
//            pstmt.close();
//            return 1;
//        } catch (Exception exc) {
//            System.out.printf("[error][back][updatePassword] %s\n", exc.getMessage());
//        }
//        return 0;
//    }
    static int updateMembers(int id, Member member) {
        int result;
        Connection con = MainApp.getDBConnection();
        HashConvertor hash = new HashConvertor();
        String query = "UPDATE members SET mid = ?, surname = ?, firstname = ?, username = ?,"
                + "email = ?, address = ?, phone = ?, password = ?, fictiv = ?, bithdate = ?, "
                + "DECEASEDDATE = ? where mid = ?";

        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, member.getMid());
            pstmt.setString(2, member.getSurname());
            pstmt.setString(3, member.getFirstname());
            pstmt.setString(4, member.getUsername());
            pstmt.setString(5, member.getEmail());
            pstmt.setString(6, member.getAddress());
            pstmt.setInt(7, member.getPhone());
            pstmt.setString(8, hash.convert(member.getPassword()));
            pstmt.setInt(9, member.getFictiv());
            pstmt.setString(10, member.getBithdate());
            pstmt.setString(11, member.getDeceaseddate());
            pstmt.setInt(12, id);
            result = pstmt.executeUpdate();
            pstmt.close();
            System.out.println("[success][back][updateMembers]");
            return result;
        } catch (Exception exc) {
            System.out.printf("[error][back][updateMembers] %s\n", exc.getMessage());
        }
        return 0;
    }

    static int deleteMembers(int id) {
        int result;
        Connection con = MainApp.getDBConnection();
        String query = "DELETE FROM MEMBERS WHERE mid = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            result = pstmt.executeUpdate();
            pstmt.close();
            System.out.println("[success][back][deleteMembers]");
            return result;
        } catch (Exception exc) {
            System.out.printf("[error][back][deleteMembers] %s\n", exc.getMessage());
        }
        return 0;
    }

    static int insertMembers(Member member) {
        int result;
        Connection con = MainApp.getDBConnection();
        HashConvertor hash = new HashConvertor();
        String query = "INSERT INTO MEMBERS (MID, SURNAME, FIRSTNAME, USERNAME, EMAIL, ADDRESS"
                + ",PHONE, PASSWORD, FICTIV, BITHDATE, DECEASEDDATE)" + "VALUES (mid_seq.nextval,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            //pstmt.setInt(1, member.getMid());
            pstmt.setString(1, member.getSurname());
            pstmt.setString(2, member.getFirstname());
            pstmt.setString(3, member.getUsername());
            pstmt.setString(4, member.getEmail());
            pstmt.setString(5, member.getAddress());
            pstmt.setInt(6, member.getPhone());
            pstmt.setString(7, hash.convert(member.getPassword()));
            pstmt.setInt(8, member.getFictiv());
            pstmt.setString(9, member.getBithdate());
            pstmt.setString(10, member.getDeceaseddate());
            result = pstmt.executeUpdate();
            pstmt.close();
            return result;
        } catch (Exception exc) {
            System.out.printf("[error][back][insertMembers] %s\n", exc.getMessage());
        }
        return 0;
    }

    public static Integer checkPassword(LoginDetails login) {
        int result = 0;
        Connection con = MainApp.getDBConnection();
        HashConvertor hash = new HashConvertor();
        String query = "SELECT PASSWORD FROM Members WHERE EMAIL = '" + login.getEmail() + "'";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String saltedPassword = rs.getString("PASSWORD");
                String salt = saltedPassword.substring(saltedPassword.length() - 10, saltedPassword.length());
                if (saltedPassword.equals(hash.convert(login.getPassword(), salt))) {
                    result = 1;
                }
            }
            stmt.close();
            rs.close();
            return result;
        } catch (Exception exc) {
            System.out.printf("[error][back][checkPassword] %s\n", exc.getMessage());
        }
        return 0;
    }

}
