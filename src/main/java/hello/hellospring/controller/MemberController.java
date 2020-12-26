package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    // 컴포넌트 스캔. MemberController 생성 시 Spring Bean에 등록된 MemberService를 연결
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
