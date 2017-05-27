/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiimem.tags_files;

/**
 *
 * @author andy
 */
public class TagFile {
    private int tid, fid, filesmembersmid;
    public void setTid(int tid){
        this.tid=tid;
    }
    public void setFid(int fid){
        this.fid=fid;
    }
    public void setFilesmembersmid(int filesmembersmid){
        this.filesmembersmid=filesmembersmid;
    }
    public int getTid(){
        return tid;
    }
    public int getFid(){
        return fid;
    }
    public int getFilesmembersmid(){
        return filesmembersmid;
    }
}
