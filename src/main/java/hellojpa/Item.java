package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED) // 상속관계 매핑(별도의 table로 생성됨 - 부모 table) > 일반적/정교한 table 구조
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) - DTYPE 기본으로 생성됨 > 단순한 table 구조
@DiscriminatorColumn(name = "DT") // DTYPE(default 컬럼명)이 생성됨 - 운영상 항상 있는 것을 추천
public abstract class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;

}
