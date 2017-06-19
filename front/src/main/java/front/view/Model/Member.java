package front.view.Model;

/**
 * Created by andy on 19.06.2017.
 */
public class Member {
    private int mid;
    private String surname;
    private String firstname;
    private String username;
    private String email;
    private String address;
    private String phone;
    private String password;
    private int fictiv;
    private String bithdate;
    private String deceaseddate;

    public Member() {
        fictiv = 0;
        deceaseddate = null;
    }


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

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFictiv(int fictiv) {
        this.fictiv = fictiv;
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

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public int getFictiv() {
        return fictiv;
    }

    public String getBithdate() {
        return bithdate;
    }

    public String getDeceaseddate() {
        return deceaseddate;
    }
//    public Member(RegisterMember regMember){
//        fictiv=0;
//        deceaseddate = null;
//        surname = regMember.getSurname();
//        firstname=regMember.getFirstname();
//        username = regMember.getUsername();
//        email = regMember.getEmail();
//        address = regMember.getAddress();
//        phone = regMember.getPhone();
//        password = regMember.getPassword();
//        bithdate = regMember.getBithdate();
//    }
}
