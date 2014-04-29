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
public class UserType {
    private Integer idUser;
    private String typeName;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.idUser;
        hash = 23 * hash + Objects.hashCode(this.typeName);
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
        final UserType other = (UserType) obj;
        if (this.idUser != other.idUser || !Objects.equals(this.typeName, other.typeName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserType{" + "idUser=" + idUser + ", typeName=" + typeName + '}';
    }
    
}
