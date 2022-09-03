package duan.atripic.service.impl;

import duan.atripic.entity.Admin;
import duan.atripic.entity.LoginUser;
import duan.atripic.entity.Result;
import duan.atripic.mapper.AdminMapper;
import duan.atripic.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import duan.atripic.utils.JwtUtil;
import duan.atripic.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author duanyhui
 * @since 2022-08-29
 */
@Slf4j
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;


    /**
     * 用户登录并返回token
     * @param
     * @return token
     */
    @Override
    public Result login(Admin admin) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(admin.getUsername(),admin.getPwd());
        Authentication authenticate = authenticationManager.authenticate(token);
        //认证未通过抛出异常
        if(Objects.isNull(authenticate)){
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        //认证通过，用uid生成一个jwt存入Result中返回给前端

        LoginUser loginUser= (LoginUser) authenticate.getPrincipal();
        String uid = loginUser.getAdmin().getUid().toString();
        String jwt = JwtUtil.createJWT(uid);

        Map<String,String> map=new HashMap<>();
        map.put("token",jwt);

        //把完整的用户信息存入Redis中，key为uid，value为完整的用户信息
        redisCache.setCacheObject(uid,loginUser);
        log.info("用户uid:"+loginUser.getAdmin().getUid()+"登录成功,权限为"+loginUser.getAuthorities());
        //System.out.println(loginUser.getAdmin().getRole());
        redisCache.setCacheObject("ROLE",loginUser.getAdmin().getRole());
        //TODO 封装角色权限信息到Redis中

        return Result.succ(map);


    }

    @Override
    public Result logout() {
        //获取SecurityContextHolder中的用户uid
        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();

        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String uid = loginUser.getAdmin().getUid().toString();
        //删除Redis中的用户信息
        redisCache.deleteObject(uid);
        return Result.succ("退出成功");

    }
}
