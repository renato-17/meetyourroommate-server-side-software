package com.acme.meetyourroommate.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "campuses")
public class Campus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    private String name;

    @NotNull
    @Size(max = 100)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="study_center_id", nullable = false)
    private StudyCenter studyCenter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public StudyCenter getStudyCenter() {
        return studyCenter;
    }

    public void setStudyCenter(StudyCenter studyCenter) {
        this.studyCenter = studyCenter;
    }
}
