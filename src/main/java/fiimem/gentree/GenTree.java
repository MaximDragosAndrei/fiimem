/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiimem.gentree;

/**
 *
 * @author andy
 */
public class GenTree{
    private int mid;
    private int mid2;
    private int relationship;
    
    
    public void setMid(int mid){
        this.mid = mid;
    }
    public void setMid2(int mid2){
        this.mid2 = mid2;
    }
    public void setRelationship(int relationship){
        this.relationship = relationship;
    }
   
    public int getMid(){
        return mid;
    }
    public int getMid2(){
        return mid2;
    }
    public int getRelationship(){
        return relationship;
    }
    
}
