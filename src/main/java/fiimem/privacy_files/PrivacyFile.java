/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiimem.privacy_files;

/**
 *
 * @author andy
 */
public class PrivacyFile {
    private int pid, fid, filesmembersmid;
    public void setPid(int pid){
        this.pid=pid;
    }
    public void setFid(int fid){
        this.fid=fid;
    }
    public void setFilesmembersmid(int filesmembersmid){
        this.filesmembersmid=filesmembersmid;
    }
    public int getPid(){
        return pid;
    }
    public int getFid(){
        return fid;
    }
    public int getFilesmembersmid(){
        return filesmembersmid;
    }
}
