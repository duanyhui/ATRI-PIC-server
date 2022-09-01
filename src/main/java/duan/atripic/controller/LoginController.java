package duan.atripic.controller;


import duan.atripic.entity.Admin;
import duan.atripic.entity.Result;
import duan.atripic.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author duanyhui
 * @since 2022-08-29
 */
@RestController
public class LoginController {

    /**
     * <p>
     *     普通用户登录
     * </p>
     */

    @Autowired
    private AdminServiceImpl adminService;

    @PostMapping("/user/login")
    public Result login(@RequestBody Admin admin){
        return adminService.login(admin);

    }

    @RequestMapping("/user/logout")
    public Result logout(){
        return adminService.logout();
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }

}

