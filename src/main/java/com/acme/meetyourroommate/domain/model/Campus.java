package com.acme.meetyourroommate.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

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

    @OneToMany(mappedBy = "campus", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> students;

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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
