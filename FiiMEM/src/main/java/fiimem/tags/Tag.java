/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiimem.tags;

/**
 *
 * @author andy
 */
public class Tag {
    private int tid;
    private String name;
    
    public void setTid(int tid){
        this.tid=tid;
    }
    public void setName(String name){
        this.name=name;
    }
    public int getTid(){
        return tid;
    }
    public String getName(){
        return name;
    }
}
