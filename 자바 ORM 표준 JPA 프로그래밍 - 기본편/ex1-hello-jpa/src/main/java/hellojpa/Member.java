package hellojpa;

import javax.persistence.*;

@Entity
@TableGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        table = "MY_SEQUENCES", // table 명
        pkColumnValue = "MEMBER_SEQ", allocationSize = 1
)
public class Member {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO) // DB 방언에 맞춰서 자연스럽게 생성
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB에 위임
    @GeneratedValue(strategy = GenerationType.TABLE,
        generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    @Column(name = "name", nullable = false)
    private String username;

    public Member(){
    } //기본생성자가 필요.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
