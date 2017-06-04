/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author andy
 */
public class Member {

    private int mid;
    private String surname;
    private String firstname;
    private String username;
    private String email;
    private String address;
    private int phone;
    private String password;
    private int ficitv;
    private String bithdate;
    private String deceaseddate;

    public void setMid(int mid) {
        this.mid = mid;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFictiv(int fictiv) {
        this.ficitv = ficitv;
    }

    public void setBithdate(String bithdate) {
        this.bithdate = bithdate;
    }

    public void setDeceaseddate(String deceaseddate) {
        this.deceaseddate = deceaseddate;
    }

    public int getMid() {
        return mid;
    }

    public String getSurname() {
        return surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public int getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public int getFictiv() {
        return ficitv;
    }

    public String getBithdate() {
        return bithdate;
    }

    public String getDeceaseddate() {
        return deceaseddate;
    }

}
