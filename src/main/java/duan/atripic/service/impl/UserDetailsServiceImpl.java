package duan.atripic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import duan.atripic.entity.Admin;
import duan.atripic.entity.LoginUser;
import duan.atripic.mapper.AdminMapper;
import duan.atripic.service.IAdminService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;


/**
 * <p>
 *  重写 UserDetailsService 实现类
 * </p>
 *
 * @author duanyhui
 * @since 2022-08-23
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getUsername, username);
        Admin admin = adminMapper.selectOne(wrapper);//查询用户信息，可能为空
        //判断用户是否存在
        if (Objects.isNull(admin)|| !Objects.equals(admin.getType(), "DEFAULT")) {
            throw new UsernameNotFoundException("用户不存在");
        }

        // TODO: 2020/9/1 将角色权限信息封装到LoginUser中

        //把数据库中的用户信息封装到 UserDetails 对象中
        List<String> role = Collections.singletonList(admin.getRole());
        return new LoginUser(admin);

        //查询对应的权限信息

    }



}

