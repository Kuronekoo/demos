package cn.demo.controllor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/freemarker")
public class HelloFreemarkerController {


    @RequestMapping("/hello")
    public ModelAndView hello(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","hello, freemarker");
        mv.setViewName("helloFreemarker");
        return mv;
    }
}
