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
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // db 종류에 따라 autoincrement를 위임
    @Column(name = "TEAM_ID")
    private Long id;

    @Column(name = "name") // DB 컬럼명
    private String teamname;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

}
