/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hemascarpark;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author janith
 */
public class User {
    
    private final SimpleStringProperty ID;
    private final SimpleStringProperty firstname;
    private final SimpleStringProperty lastname;
    private final SimpleStringProperty email;
     private final SimpleStringProperty MobileNo;
    private final SimpleStringProperty username;
    private final SimpleStringProperty memberType;
    private final SimpleStringProperty password;

    public User( String id, String fn,String ln,String em,String un,String mt,String pw, String mobileN) {
        
        this.ID =new SimpleStringProperty(id);
        this.firstname=new SimpleStringProperty(fn);
        this.lastname=new SimpleStringProperty(ln);
        this.email= new SimpleStringProperty(em);
        this.username = new SimpleStringProperty(un);
        this.memberType =  new SimpleStringProperty(mt);
        this.password =  new SimpleStringProperty(pw);
        this.MobileNo =  new SimpleStringProperty(mobileN);
    }
    
    //--------------------------------------------------------------------
    public String getID() {
        return ID.get();
    }

    public String getFirstname() {
        return firstname.get();
    }

    public String getLastname() {
        return lastname.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getUsername() {
        return username.get();
    }

    public String getMemberType() {
        return memberType.get();
    }

    public String getPassword() {
        return password.get();
    }
    
    /**
     *
     * @return
     */
    public String getMobileNo() {
        return MobileNo.get();
    }

//--------------------------------------------------------------------
    
    
   public void setID(String id) {
        ID.set(id);
    }

    public void setfirstname(String fn) {
        firstname.set(fn);
    }

    public void setlastname(String ln) {
        lastname.set(ln);
    }

     public void setEmail(String em) {
        email.set(em);
    }

    public void setUsername(String un) {
        username.set(un);
    }

    public void setMemberType(String mt) {
        memberType.set(mt);
    }

    public void setPassword(String pw) {
        password.set(pw);
    }

    
    public void setMobileNo(String mobileN) {
        MobileNo.set(mobileN);
    }
   
    
    
}
