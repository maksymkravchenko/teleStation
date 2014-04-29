/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bionic.telestation.entity;

import java.util.Objects;

/**
 *
 * @author MAX
 */
public class User {
    private Integer idUser;
    private UserType userTypeId;
    private String name;
    private String password;
    private Boolean loggedin;
    private Boolean banned;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public UserType getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(UserType userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedin() {
        return loggedin;
    }

    public void setLoggedin(Boolean loggedin) {
        this.loggedin = loggedin;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.idUser;
        hash = 37 * hash + Objects.hashCode(this.userTypeId);
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.password);
        hash = 37 * hash + (this.loggedin ? 1 : 0);
        hash = 37 * hash + (this.banned ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } 
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.idUser != other.idUser || !Objects.equals(this.userTypeId, other.userTypeId) ||
                !Objects.equals(this.name, other.name) ||
                !Objects.equals(this.password, other.password) ||
                this.loggedin != other.loggedin || this.banned != other.banned) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "idUser=" + idUser + ", userTypeId=" + userTypeId + 
                ", name=" + name + ", password=" + password + ", loggedin=" + 
                loggedin + ", banned=" + banned + '}';
    }

   
}
