package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // db 종류에 따라 autoincrement를 위임
    private Long id;

    private String name;


    @OneToMany(mappedBy = "parent",           // 1. ALL(PERSIST + REMOVE) - 부모와 자식간의 관계가 명확하게 종속적일 때(life cycle이 일치) - 단일 소유자인 경우
               cascade = CascadeType.PERSIST, // 2. em.persist(parent)만 해도 childList도 함께 db 저장됨
               orphanRemoval = true)          // 1. childList에 빠진 요소는 자동으로 삭제(delete query)됨 - 참조하는 곳이 한 곳일때!
                                              // 2. 부모가 지워지면 자식도 같이 지워짐 == CascadeType.REMOVE
    private List<Child> childList = new ArrayList<>();

    public void addChild(Child child) {
        childList.add(child);
        child.setParent(this);
    }

}
