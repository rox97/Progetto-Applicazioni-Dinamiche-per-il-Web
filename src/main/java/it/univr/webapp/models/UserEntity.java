package it.univr.webapp.models;

import javax.persistence.*;

@Entity
@javax.persistence.Table(name = "user", schema = "public", catalog = "users")
public class UserEntity {

    private String userCode;
    private String userName;
    private String userPassword;
    private Object userRole;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @javax.persistence.Column(name = "user_code")

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_password")
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Basic
    @Column(name = "user_role")
    public Object getUserRole() {
        return userRole;
    }

    public void setUserRole(Object userRole) {
        this.userRole = userRole;
    }
}
