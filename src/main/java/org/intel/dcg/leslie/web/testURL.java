package org.intel.dcg.leslie.web;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class testURL {
    //get the data from application.properties
    @Value("${org.intel.dcg.name}")
    private String result;

    @RequestMapping("/")
    public String index() {
        return result;
    }

    @RequestMapping("/test2")
    public String index2() {
        return "hhh";
    }
}
