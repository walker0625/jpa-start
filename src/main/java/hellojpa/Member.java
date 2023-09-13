package hellojpa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor // jpa의 entity는 기본 생성자가 필수
@Entity
@Table(name = "MEMBER") // DB 테이블명
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // db 종류에 따라 autoincrement를 위임
    @Column(name = "MEMBER_ID")
    private Long id;

    private Integer age;

    @Column(name = "name") // DB 컬럼명
    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Lob
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // date
    private LocalDate localDate;

    // timestamp(datetime)
    private LocalDateTime localDateTime;

    public Member(Long id, String username) {
        this.id = id;
        this.username = username;
    }

}
