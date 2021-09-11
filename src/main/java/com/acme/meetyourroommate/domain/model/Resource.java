package com.acme.meetyourroommate.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name= "property_resources")
public class Resource extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Lob
    private String link;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_detail_id",nullable = false)
    private PropertyDetail propertyDetail;

    public Long getId() {
        return id;
    }

    public void setId(Long idPropertyResource) {
        this.id = idPropertyResource;
    }

    public PropertyDetail getPropertyDetail() {
        return propertyDetail;
    }

    public void setPropertyDetail(PropertyDetail propertyDetail) {
        this.propertyDetail = propertyDetail;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}