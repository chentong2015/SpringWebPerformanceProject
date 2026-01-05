package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_batchs")
public class BatchEntity {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "name", columnDefinition = "varchar(32)")
    private String name;

    @Column(name = "m_label", columnDefinition = "varchar(64)")
    private String label;

    public BatchEntity() {
    }

    public BatchEntity(long id, String name, String label) {
        this.id = id;
        this.name = name;
        this.label = label;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
