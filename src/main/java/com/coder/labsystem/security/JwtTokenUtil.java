package com.coder.labsystem.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author : JQK
 * @date : 2020-07-29 23:04
 * @description : jwt工具类
 */
@Component
public class JwtTokenUtil {
    private final static Logger LOG = LoggerFactory.getLogger(JwtTokenUtil.class);

    /** token 的过期时间，7天 */
    private static final long EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;

    /** 生成token的秘钥 */
    private static final String TOKEN_SECRET = "MwFbMdmg0236MxkA6J-hXLC3IirHwZdnO6CmxZYAQY52UHA";

    /** 生成 token
     * @param account 用户账号
     * @param roles 用户角色
     * */
    public static String sign(String id, String account, String roles) {
        try {
            String token = Jwts.builder().claim("authorities", roles)
                    .claim("id", id)
                    .setSubject(account)
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                    .signWith(SignatureAlgorithm.HS512, TOKEN_SECRET).compact();

            return token;
        } catch(Exception e) {
            return null;
        }
    }

    /**
     * 验证令牌
     * @param token
     * @param username
     * @return
     */
    public static Boolean validateToken(String token, String username) {
        Claims claims = parseToken(token);
        if (null == claims) {
            return false;
        }
        return (claims.getSubject().equals(username) && !isTokenExpired(token));
    }

    /** 解析 token */
    public static Claims parseToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(TOKEN_SECRET).parseClaimsJws(token.replace("Bearer", "")).getBody();
            return claims;
        } catch(Exception e) {
            LOG.error("解析token出错，token信息为{}", token);
            return null;
        }
    }

    /** 校验 token 是否过期 */
    public static boolean isTokenExpired(String token) {
        Claims claims = parseToken(token);
        if (null == claims) {
            return true;
        }
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }

}
