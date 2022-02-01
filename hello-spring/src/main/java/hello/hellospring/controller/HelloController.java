package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @GetMapping("hello") // GetMapping은 HTTP GET을 의미
    public String hello(Model model){
        model.addAttribute("data", "spring!!"); // attributeValue 에서 hello!! 값이 ${data}로 치환 된다.
        return "hello";
    }
    @GetMapping("hello-mvc")
    // public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model){ # 이렇게 하면 name값을 넘기지 않아도 됨
    public String helloMvc(@RequestParam("name") String name, Model model){ // 이경우 requried가 기본으로 true이기 때문에 값을 넘거야한다. GET 방식 => localhost:8080/hello-mvc?name=spring!
        model.addAttribute("name", name);
        return "hello-template"; // html 문서는 템플릿이 아닌 변환이 되어서 넘어간다.
    }
}
