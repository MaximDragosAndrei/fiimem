/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiimem.privacy_members;

/**
 *
 * @author andy
 */
public class PrivacyMember {
        private int mid,pid;
        public void setMid(int mid){
            this.mid=mid;
        }
        public void setPid(int pid){
            this.pid=pid;
        }
        public int getMid(){
            return mid;
        }
        public int getPid(){
            return pid;
        }
}
