package jpabook.model.entity;

import javax.persistence.*;

@Entity
public class Member {
    @Id
    @Column(name = "MEMBER_ID")
    private String id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
