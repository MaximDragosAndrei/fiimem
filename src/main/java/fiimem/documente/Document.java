/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiimem.documente;

/**
 *
 * @author Asus
 */
public class Document {
     private int fid;
        private int membersmid;
        private String address;
        private String name;
        private String format;
        private byte[] byteArray;
        
        public void setByteArray(byte[] byteArray){
            this.byteArray = byteArray;
        }
        public void setFid(int fid){
            this.fid=fid;
        }
        public void setFormat(String format){
            this.format=format;
        }
        public void setMembersmid(int membersmid){
            this.membersmid=membersmid;
        }
        public void setAddress(String address){
            this.address=address;
        }
        public void setName(String name){
            this.name=name;
        }
        public int getFid(){
            return fid;
        }
        public int getMembersmid(){
            return membersmid;
        }
        public String getAddress(){
            return address;
        }
        public String getName(){
            return name;
        }
        public String getFormat(){
            return format;
        }
        public byte[] getByteArray(){
            return byteArray;
        }
    
    
}
