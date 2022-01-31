package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello") // GetMapping은 HTTP GET을 의미
    public String hello(Model model){
        model.addAttribute("data", "spring!!"); // attributeValue 에서 hello!! 값이 ${data}로 치환 된다.
        return "hello";
    }
}
