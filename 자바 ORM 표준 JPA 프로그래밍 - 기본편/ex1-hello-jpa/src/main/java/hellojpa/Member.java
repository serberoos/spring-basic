package hellojpa;

import javax.persistence.*;

@Entity
@SequenceGenerator(name="member_seq_generator", sequenceName = "member_seq")
public class Member {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO) // DB 방언에 맞춰서 자연스럽게 생성
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB에 위임
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
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
