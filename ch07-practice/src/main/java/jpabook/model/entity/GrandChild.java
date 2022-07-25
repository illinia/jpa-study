package jpabook.model.entity;

import javax.persistence.*;

@Entity
public class GrandChild {
    @Id
    @GeneratedValue
    @Column(name = "GRANDCHILD_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CHILD_ID")
    private Child child;

    private String name;
}
