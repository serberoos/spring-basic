package hellojpa;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
public class Member {
    @Id
    private Long id;

    @Column(name = "name", nullable = false)
    private String username;

    private int age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime; // 최신버전 쓰는경우 그냥 이거 쓰면됨 @Temporal 말고

    @Lob // varchar를 넘어서는 큰 컨텐츠??
    private String description;

    public Member(){
    } //기본생성자가 필요.
}
