/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bionic.telestation.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author MAX
 */
public class Check {

    private Integer checkId;
    private User userId;
    private Service serviceId;
    private BigDecimal price;
    private Boolean expired;
    private Boolean paid;
    private Date creationDate;
    private Date expireDate;

    public Integer getCheckId() {
        return checkId;
    }

    public void setCheckId(Integer checkId) {
        this.checkId = checkId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Service getServiceId() {
        return serviceId;
    }

    public void setServiceId(Service serviceId) {
        this.serviceId = serviceId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date ecpireDate) {
        this.expireDate = ecpireDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.checkId;
        hash = 97 * hash + Objects.hashCode(this.userId);
        hash = 97 * hash + Objects.hashCode(this.serviceId);
        hash = 97 * hash + Objects.hashCode(this.price);
        hash = 97 * hash + (this.expired ? 1 : 0);
        hash = 97 * hash + (this.paid ? 1 : 0);
        hash = 97 * hash + Objects.hashCode(this.creationDate);
        hash = 97 * hash + Objects.hashCode(this.expireDate);
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
        final Check other = (Check) obj;
        if (this.checkId != other.checkId || !Objects.equals(this.userId, other.userId)
                || !Objects.equals(this.serviceId, other.serviceId)
                || !Objects.equals(this.price, other.price)
                || this.expired != other.expired || this.paid != other.paid
                || !Objects.equals(this.creationDate, other.creationDate)
                || !Objects.equals(this.expireDate, other.expireDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Check{" + "checkId=" + checkId + ", userId=" + userId
                + ", serviceId=" + serviceId + ", price=" + price
                + ", expired=" + expired + ", paid=" + paid
                + ", creationDate=" + creationDate + ", expireDate=" + expireDate + '}';
    }
}
