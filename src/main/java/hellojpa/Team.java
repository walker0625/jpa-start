package hellojpa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TEAM")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // db 종류에 따라 autoincrement를 위임
    @Column(name = "TEAM_ID")
    private Long id;

    @Column(name = "name") // DB 컬럼명
    private String teamname;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    /* 연관관계 편의 메소드는 한쪽에만 설정(무한 루프 등의 문제 발생 가능)
    public void addMembers(Member member) {
        member.setTeam(this);
        members.add(member);
    }
    */

}
