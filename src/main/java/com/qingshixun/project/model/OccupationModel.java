package com.qingshixun.project.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_occupation")
public class OccupationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true,nullable = false)
    private String occupation;

    public OccupationModel() {
    }

    private Date createtime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
