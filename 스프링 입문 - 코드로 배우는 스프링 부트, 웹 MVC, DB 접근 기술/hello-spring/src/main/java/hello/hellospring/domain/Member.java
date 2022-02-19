package hello.hellospring.domain;
import javax.persistence.*;

// 도메인
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 알아서 생성해주는 IDENTITY
    private Long id; // 시스템이 저장하는 임의의 값
    //@Column(name="username")
    private String name; // 고객이 회원가입 할때 적는 이름.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        return name;
    }
}
