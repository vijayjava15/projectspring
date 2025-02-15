package com.urlShortner.urlShortner.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Table(name = "url_shortner")
public class UrlShortner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String uniqueKey;
    private String fullUrl;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date updatedDate;
    private boolean isExpired;

    public UrlShortner(Long id, String uniqueKey, String fullUrl, Date createdDate, Date updatedDate, boolean isExpired) {
        this.id = id;
        this.uniqueKey = uniqueKey;
        this.fullUrl = fullUrl;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.isExpired = isExpired;
    }

    public UrlShortner() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }
}
