package com.coder.labsystem.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * 自定义认证过滤器
 * 因为默认情况下，spring-security的登录，需要传入的 centent-type 为 application/x-www-form-urlencoded，
 * 如果想要支持 application/json 需要另外配置
 * CustomAuthenticationFilter 继承了 UsernamePasswordAuthenticationFilter ，
 * 如果前端传来的 centent-type 为 application/json 或者 application/json;charset=UTF-8，则使用 json 形式接受
 * 否则，就是用 父类的方法 request.getParameter(usernameParameter) 来接受参数信息
 * @author : JQK
 */
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (MediaType.APPLICATION_JSON_UTF8_VALUE.equals(request.getContentType())
                || MediaType.APPLICATION_JSON_VALUE.equals(request.getContentType())) {
            ObjectMapper mapper = new ObjectMapper();
            UsernamePasswordAuthenticationToken authRequest = null;
            try (InputStream is = request.getInputStream()) {
                Map<String,String> authenticationBean = mapper.readValue(is, Map.class);
                authRequest = new UsernamePasswordAuthenticationToken(
                        authenticationBean.get("username"), authenticationBean.get("password"));
            } catch (IOException e) {
                authRequest = new UsernamePasswordAuthenticationToken("", "");
            } finally {
                setDetails(request, authRequest);
                return this.getAuthenticationManager().authenticate(authRequest);
            }
        }
        else {
            return super.attemptAuthentication(request, response);
        }
    }
}
