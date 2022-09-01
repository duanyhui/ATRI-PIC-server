package duan.atripic;

import duan.atripic.entity.Admin;
import duan.atripic.entity.LoginUser;
import duan.atripic.mapper.AdminMapper;
import duan.atripic.service.impl.AdminServiceImpl;
import duan.atripic.service.impl.UserDetailsServiceImpl;
import duan.atripic.utils.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class AtriPicApplicationTests {

    @Autowired
    private AdminMapper adminMapper;
    @Test
    void contextLoads() {
        List<Admin> list= adminMapper.selectList(null);
        System.out.println(list);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void TestPwdEncoder() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String encode = passwordEncoder.encode("123456");
        String encode1 = passwordEncoder.encode("123456");
        System.out.println(encode);
        System.out.println(encode1);

        boolean matches = passwordEncoder.matches("123456", encode);
        System.out.println(matches);


        String encode2 = passwordEncoder.encode("123456");
        System.out.println(encode2);

        boolean matches1 = passwordEncoder.matches("123456", encode2);
        System.out.println(matches1);
    }


    private  static  Long JWT;
    @Value("${token.expireTime}")
    public void setExpireTime(long expireTime) {
        JWT = expireTime;
    }
    @Test
    void ValueTest() {

        System.out.println(JWT);
    }

    @Test
    public void BCryptPasswordEncoderTest() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123456");
        String encode1 = passwordEncoder.encode("123456");
        System.out.println(encode);
        System.out.println(encode1);
        boolean matches = passwordEncoder.matches("123456", encode);
        System.out.println(matches);
    }


    @Autowired
    private RedisCache redisCache;
    @Test
    public void RedisTest(){
        LoginUser loginUser = new LoginUser();
        loginUser = redisCache.getCacheObject("1");
        System.out.println(loginUser);
        System.out.println(redisCache.getCacheObject("1").toString());
        try {
            System.out.println(redisCache.getCacheObject("2").toString());
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

    @Autowired
    private AdminServiceImpl adminService;


    @Test
    public void getAuthoritiesTest(){
        Admin admin = new Admin(1,"1","123456","ROLE_ROOT","1","1");
        System.out.println(adminService.login(admin));
        LoginUser loginUser = new LoginUser();

        loginUser = new LoginUser(admin);

        System.out.println(loginUser.getAuthorities());


    }





}
