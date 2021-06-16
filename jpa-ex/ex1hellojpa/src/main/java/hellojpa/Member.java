package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
//테이블 명이 클래스명과 다른 경우
//@Table(name = "USER")
public class Member {

    public Member(){}

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    private Long id;
    //@Column(name = "username")
    private String name;

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
}
