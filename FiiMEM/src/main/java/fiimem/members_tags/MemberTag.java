/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiimem.members_tags;

/**
 *
 * @author andy
 */
public class MemberTag {
    private int tid, mid;
    public void setTid(int tid){
        this.tid=tid;
    }
    public void setMid(int mid){
        this.mid=mid;
    }
    public int getTid(){
        return tid;
    }
    public int getMid(){
        return mid;
    }
}
