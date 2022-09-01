package duan.atripic.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping("/testroot")
    @PreAuthorize("hasAuthority('ROLE_ROOT')")
    public String test(){
        return "testroot";
    }

    @RequestMapping("/testuser")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String testuser(){
        return "testuser";
    }
}
