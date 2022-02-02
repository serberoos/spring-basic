package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("hello-string")
    @ResponseBody // 응답 body 부에 아래 내용을 직접 넣어주겠다. (사실 많이 안쓰는 방법)
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // hello spring
        // return "<html>hello " + name + "/<html>"; # 이런식으로 html형태로 내려줄 수도 있지만 굳이?
    }// 기존의 template 엔진을 사용하는 방식과의 차이점은 그냥 문자열 자체가 그대로 ResponseBody에 포함되어 전송되는 것.

    //문자가 아닌 Data를 전송하려면?
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //객체를 리턴하는 경우. API 방식 = 객체가 들어오면 기본 정책으로 json이 리턴된다. [JsonConverter] ## 예전에는 xml과 json이 격돌했었음.
        // MappingJackson2HttpMessageConverter
    }

    static class Hello { // 클래스 안에서 클래스를 쓸 수 있음 => 자바에서 정식으로 지원하는 문법이다. (객체를 이런식으로 전달해줄 수 있음.)
        private String name;
        //Getter Setter
        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }

    }
    /*
        정리
        1. 정적 컨텐츠 : 파일을 그대로 내려준다.
        2. MVC와 Template 엔진 : 템플릿 엔진을 모델 뷰 컨트롤러 방식으로 쪼개서 랜더링후 랜더링된 html을 내려준다.
        3. api 방식 : 객체를 내려준다. MappingJackson2HttpMessageConverter | @ResponseBody
     */
}
