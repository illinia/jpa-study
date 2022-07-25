package jpabook.model.entity;

import java.io.Serializable;

public class GrandChildId implements Serializable {
    private ChildId child;
    private String id;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public ChildId getChild() {
        return child;
    }

    public void setChild(ChildId child) {
        this.child = child;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
