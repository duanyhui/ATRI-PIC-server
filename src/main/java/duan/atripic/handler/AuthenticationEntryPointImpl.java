package duan.atripic.handler;

import com.alibaba.fastjson.JSON;
import duan.atripic.entity.Result;
import duan.atripic.utils.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author duanyhui
 * @Description 未登录或者token失效时访问接口时的处理
 * @date 2022/9/2
 */
/**
 * 已弃用
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        logger.error("Request URL : {}, Exception :{}", request.getRequestURL(), authException.getMessage());
        Result result = Result.fail(401,"未登录或者token失效",null);

        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);


    }
}
