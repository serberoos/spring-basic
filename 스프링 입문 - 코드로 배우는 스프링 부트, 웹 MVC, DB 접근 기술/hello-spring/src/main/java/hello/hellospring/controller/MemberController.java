package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
//    private MemberService memberService;
// 필드 주입 DI
    //@Autowired private  MemberService memberService;
    private final MemberService memberService;

    // 생성자 Dependancy Injection :DI
    @Autowired // spring container에 있는 memberService 를 가져다 실행한다.
    public MemberController(MemberService memberService){ //memberService could not be found
        // 현재 memberService는 순수한 자바 클래스임 => 스프링이 알수 있는 방법이 없다. @어노테이션도 없음. @Service를 써주어야함.
        this.memberService = memberService;
    }

    //setter DI
//    @Autowired
//    public void setMemberService(MemberService memberService){
//        this.memberService = memberService;
//    }
    // 누군가 memberservice 를 set하게 되면 누군가가 호출해서 잘못 바꿔쳐질 수 있음. (호출되면 안되는 메서드가 호출됨.)
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);

        return "members/memberList";
    }

}
