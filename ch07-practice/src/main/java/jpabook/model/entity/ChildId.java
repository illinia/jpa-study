package jpabook.model.entity;

import java.io.Serializable;

public class ChildId implements Serializable {
    private String parent;
    private String childId;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
