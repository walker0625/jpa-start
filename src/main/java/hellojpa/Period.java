package hellojpa;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

// 다른 Entity에서 공유해서 사용하는 경우 인스턴스를 복사(new)해서 사용해야 함(같은 인스턴스를 사용 후 수정하면 update가 n번 실행됨)
// 생성자로만 인스턴스를 만들 수 있게 하고(일부 수정시 생성자로 재생성하여 교체), setter를 금지하여 불변객체로 사용
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Period {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
