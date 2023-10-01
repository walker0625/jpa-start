package hellojpa;

import lombok.*;

import javax.persistence.Embeddable;
import java.util.Objects;

// 다른 Entity에서 공유해서 사용하는 경우 인스턴스를 복사(new)해서 사용해야 함(같은 인스턴스를 사용 후 수정하면 update가 n번 실행됨)
// 생성자로만 인스턴스를 만들 수 있게 하고(일부 수정시 생성자로 재생성하여 교체), setter를 금지하여 불변객체로 사용
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {

    private String city;
    private String street;
    private String zipcode;

}
