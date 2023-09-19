package hellojpa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor // jpa의 entity는 기본 생성자가 필수
@Entity
@Table(name = "MEMBER") // DB 테이블명
public class Member extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // db 종류에 따라 autoincrement를 위임
    @Column(name = "MEMBER_ID")
    private Long id;

    private Integer age;

    @Column(name = "name") // DB 컬럼명
    private String username;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCKER_ID")
    Locker locker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Lob
    private String description;

    @Embedded
    private Period period;

    @Embedded
    private Address address;

    // 값타입 컬렉션에 수정을 하면 전체 data를 다 delete하고 수정으로 남는 값들만 새롭게 insert 처리(10000개 중 1개 지우면 insert 9999번 실행됨)
    // 차라리 entity로 바꿔서 일대다 관계로 변경하는 것 추천
    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns = @JoinColumn(name = "MEMBER_ID"))
    @Column(name = "FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();

    // 값타입 컬렉션을 entity로 승격하여 사용하는 것 추천(id가 생성되므로 관리가 용이)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MEMBER_ID")
    private List<AddressEntity> addressEntityHistory = new ArrayList<>();

    // 단순한 selectbox 값 처리의 경우 같은 경우에만 값타입 컬렉션 사용 추천
    @ElementCollection
    @CollectionTable(name = "ADDRESS", joinColumns = @JoinColumn(name = "MEMBER_ID"))
    private List<Address> addressHistory = new ArrayList<>();

    /*
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // date
    private LocalDate localDate;

    // timestamp(datetime)
    private LocalDateTime localDateTime;
    */

    public Member(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    // setTeam 보다는 좀 더 의미있는 이름으로 지정
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this); // 양방향 매핑 편의를 위해 세팅
    }

}
