/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bionic.telestation.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author MAX
 */
public class ServiceType {
    private Integer idservice_type;
    private String typeName;
    private BigDecimal price;

    public int getIdservice_type() {
        return idservice_type;
    }

    public void setIdservice_type(Integer idservice_type) {
        this.idservice_type = idservice_type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.idservice_type;
        hash = 67 * hash + Objects.hashCode(this.typeName);
        hash = 67 * hash + Objects.hashCode(this.price);
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
        final ServiceType other = (ServiceType) obj;
        if (this.idservice_type != other.idservice_type || !Objects.equals(this.typeName, other.typeName) ||
                !Objects.equals(this.price, other.price)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ServiceType{" + "idservice_type=" + idservice_type + ", typeName=" + typeName + ", price=" + price + '}';
    }
}
