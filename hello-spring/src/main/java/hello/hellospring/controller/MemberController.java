package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired // spring container에 있는 memberService 를 가져다 실행한다.
    public MemberController(MemberService memberService){ //memberService could not be found
        // 현재 memberService는 순수한 자바 클래스임 => 스프링이 알수 있는 방법이 없다. @어노테이션도 없음. @Service를 써주어야함.
        this.memberService = memberService;
    }
}
