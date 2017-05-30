/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiimem.history;

/**
 *
 * @author andy
 */
public class History {
    private int mid;
    private int hid;
    private String logindate;
    private String logout;
    
    
    public void setMid(int mid){
        this.mid = mid;
    }
    public void setHid(int hid){
        this.hid = hid;
    }
    public void setLogindate(String logindate){
        this.logindate = logindate;
    }
    public void setLogout(String logout){
        this.logout = logout;
    }
    
    public int getMid(){
        return mid;
    }
    public int getHid(){
        return hid;
    }
    public String getLogindate(){
        return logindate;
    }
    public String getLogout(){
        return logout;
    }
    
    
}
