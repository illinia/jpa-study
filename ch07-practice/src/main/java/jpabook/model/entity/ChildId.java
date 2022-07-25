package jpabook.model.entity;

import javax.persistence.Column;
import java.io.Serializable;

public class ChildId implements Serializable {
    private String parentId;

    @Column(name = "CHILD_ID")
    private String id;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
