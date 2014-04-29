/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bionic.telestation.entity;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author MAX
 */
public class Service {

    private Integer idService;
    private ServiceType serviceTypeId;
    private Integer recipient;
    private Integer sender;
    private Date date;
    private Integer duration;

    public int getIdService() {
        return idService;
    }

    public void setIdService(Integer idService) {
        this.idService = idService;
    }

    public ServiceType getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(ServiceType serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public int getRecipient() {
        return recipient;
    }

    public void setRecipient(Integer recipient) {
        this.recipient = recipient;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(Integer sender) {
        this.sender = sender;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.idService;
        hash = 79 * hash + Objects.hashCode(this.serviceTypeId);
        hash = 79 * hash + Objects.hashCode(this.recipient);
        hash = 79 * hash + Objects.hashCode(this.sender);
        hash = 79 * hash + Objects.hashCode(this.date);
        hash = 79 * hash + this.duration;
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
        final Service other = (Service) obj;
        if (this.idService != other.idService || !Objects.equals(this.serviceTypeId, other.serviceTypeId)
                || !Objects.equals(this.recipient, other.recipient)
                || !Objects.equals(this.sender, other.sender)
                || !Objects.equals(this.date, other.date)
                || this.duration != other.duration) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Service{" + "idService=" + idService + ", seviceTypeId="
                + serviceTypeId + ", recipient=" + recipient + ", sender="
                + sender + ", date=" + date + ", duration=" + duration + '}';
    }
}
