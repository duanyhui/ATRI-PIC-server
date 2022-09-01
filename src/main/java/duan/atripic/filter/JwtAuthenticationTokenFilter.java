package duan.atripic.filter;

import duan.atripic.entity.LoginUser;
import duan.atripic.utils.JwtUtil;
import duan.atripic.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * <p>
 *  重写请求过滤器，添加JWT验证
 * </p>
 *
 * @author duanyhui
 * @since 2022-08-30
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {


    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取请求头中的token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            //没有token，放行
            filterChain.doFilter(request, response);
            return;

        }

        //解析token0
        String uid;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            uid = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token无效");
        }

        //从redis中获取用户信息
        String redisKey = uid;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        if(Objects.isNull(loginUser)){
            throw new RuntimeException("redis中没有用户信息");
        }

        //把用户信息放入Holders中，以便后续使用
        UsernamePasswordAuthenticationToken authenticationToken =                //要获取权限信息放入authorities中
                new UsernamePasswordAuthenticationToken(loginUser,null,null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //放行
        filterChain.doFilter(request, response);
    }
}
