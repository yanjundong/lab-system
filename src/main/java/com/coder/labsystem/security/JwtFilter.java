package com.coder.labsystem.security;

import com.coder.labsystem.model.entity.UserBasicInfo;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * 获取从前端传来的 token（存在于 header 中的 authorization）
 * 验证是否正确，有没有过期等，并获取用户信息存放于 security 上下文，方便后面的程序使用
 */
public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String token = req.getHeader("authorization");
        Authentication authentication = null;
        if (null != token) {
            //请求令牌不为空
            if (SecurityUtil.getAuthentication() == null) {
                //上下文中Authentication为空
                Claims claims = JwtTokenUtil.parseToken(token);
                if (claims != null && !JwtTokenUtil.isTokenExpired(token)) {
                    //获取当前登录用户名
                    String username = claims.getSubject();
                    //获取当前用户id
                    String id = claims.get("id", String.class);
                    List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
                    UserBasicInfo user = new UserBasicInfo(id, username, "", authorities);
                    authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
                }
            } else {
                if (JwtTokenUtil.validateToken(token, SecurityUtil.getCurrentUser().getUsername())) {
                    authentication = SecurityUtil.getAuthentication();
                }
            }
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(req,servletResponse);
    }
}
