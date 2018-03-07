package org.intel.dcg.leslie.web;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

// not @RestController
@Controller
@RequestMapping("/sap")
public class testsapui {
//    @RequestMapping(value="",method = RequestMethod.GET)
//    public ModelAndView index() {
//        ModelAndView modelAndView = new ModelAndView("main");
//        return modelAndView;
//    }
    @RequestMapping(value="",method = RequestMethod.GET)
    public String indexTest() {
        return "/main.html";
    }
    @RequestMapping(value="/test",method = RequestMethod.GET)
    public String indexTest2() {
        return "/test/mockServer.html";
    }
    @RequestMapping(value="/index",method = RequestMethod.GET)
    public String indexTest3() {
        return "/index.html";
    }
}
