/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiimem.privacy;

/**
 *
 * @author andy
 */
public class Privacy {
    private int pid;
    private String rights;
    
    public void setPid(int pid){
        this.pid=pid;
    }
    public void setRights(String rights){
        this.rights=rights;
    }
    public int getPid(){
        return pid;
    }
    public String getRights(){
        return rights;
    }
    
}
