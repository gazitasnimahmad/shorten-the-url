package com.example.shorturl.model;

import javax.persistence.*;

@Entity
@Table(name = "Url")
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long urlId;

    @Column(nullable = false)
    private String urlName;

    public Url(){}

    public Url(long urlId, String urlName) {
        this.urlId = urlId;
        this.urlName = urlName;
    }

    public long getUrlId() {
        return urlId;
    }

    public void setUrlId(long urlId) {
        this.urlId = urlId;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    @Override
    public String toString() {
        return "Url{" +
                "urlId=" + urlId +
                ", urlName='" + urlName + '\'' +
                '}';
    }
}
