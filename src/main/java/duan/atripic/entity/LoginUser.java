package duan.atripic.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser implements UserDetails {

    private Admin admin;

//    @JSONField(serialize = false)
//    private List<SimpleGrantedAuthority> RoleList;
//
//    public LoginUser(Admin admin) {
//        this.admin = admin;
//    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //把admin对象中的权限信息封装成SpringSecurity需要的SimpleGrantedAuthority对象

        List<GrantedAuthority> RoleList= new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(admin.getRole());
        RoleList.add(simpleGrantedAuthority);
        return RoleList;
//        List<String> permissions = Collections.singletonList(admin.getRole());
//        List<GrantedAuthority> NewList= new ArrayList<>();
//        for (String role : Collections.singletonList(admin.getRole())) {
//            SimpleGrantedAuthority simpleGrantedAuthority1 = new SimpleGrantedAuthority(role);
//            NewList.add(simpleGrantedAuthority1);
//        }
//        System.out.println(NewList);
//        System.out.println(list);


    }
//private List<String> permissions;
//public LoginUser(Admin user, List<String> permissions) {
//    this.admin = user;
//    this.permissions = permissions;
//}
//
////    @JSONField(serialize = false)
//    private List<SimpleGrantedAuthority> authorities;
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        if(authorities!=null){
//            return authorities;
//        }
//        //把permissions中String类型的权限信息封装成SimpleGrantedAuthority对象
////       authorities = new ArrayList<>();
////        for (String permission : permissions) {
////            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
////            authorities.add(authority);
////        }
//        authorities = permissions.stream()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//        return authorities;
//    }

    @Override
    public String getPassword() {
        return admin.getPwd();
    }

    @Override
    public String getUsername() {
        return admin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
