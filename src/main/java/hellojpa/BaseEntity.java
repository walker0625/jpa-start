package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass // 공통 요소(컬럼)만을 제공하는 부모클래스(별도의 table이 생기지는 않음), BaseEntity로 조회 불가
public abstract class BaseEntity {

    private String createdBy;
    private LocalDateTime createdDate;
    private String modifiedBy;
    private LocalDateTime modifiedDate;

}
