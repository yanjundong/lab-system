package com.coder.labsystem.config;

import com.coder.labsystem.core.user.service.UserBasicInfoService;
import com.coder.labsystem.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author : JQK
 * @date : 2021-04-19 18:23
 * @description : spring-security配置
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)  //开启权限注解，如 @PreAuthorize
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserBasicInfoService userBasicInfoService;
    private final CustomSuccessHandler customSuccessHandler;
    private final CustomFailureHandler customFailureHandler;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthEntry customAuthEntry;

    @Autowired
    public WebSecurityConfig(UserBasicInfoService userBasicInfoService, CustomSuccessHandler customSuccessHandler,
                             CustomFailureHandler customFailureHandler, CustomAccessDeniedHandler customAccessDeniedHandler,
                             CustomAuthEntry customAuthEntry) {
        this.userBasicInfoService = userBasicInfoService;
        this.customSuccessHandler = customSuccessHandler;
        this.customFailureHandler = customFailureHandler;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
        this.customAuthEntry = customAuthEntry;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userBasicInfoService)
                //用户密码的加密规则
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/v2/**")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/configuration/**")
                .antMatchers("/webjars/**", "/images/**")
                .antMatchers("/druid/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(HttpMethod.POST, "/user").permitAll()  //注册用户
                .antMatchers(HttpMethod.GET, "/file/**").permitAll()
                /*.antMatchers(HttpMethod.GET, "/user/account/repetition").permitAll()
                .antMatchers("/actuator/**").permitAll()*/
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/user/login")
                .usernameParameter("username")
                .successHandler(customSuccessHandler)
                .failureHandler(customFailureHandler)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(customAuthEntry)
                .accessDeniedHandler(customAccessDeniedHandler)
                .and()
                .cors().disable()
                .csrf().disable();
        /*不生成session*/
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setUsernameParameter("username");
        filter.setPasswordParameter("password");
        filter.setFilterProcessesUrl("/login");
        filter.setAuthenticationSuccessHandler(customSuccessHandler);
        filter.setAuthenticationFailureHandler(customFailureHandler);
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    /** 角色的继承，领导拥有下属的所有权限 */
    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_TEACHER > ROLE_ADMIN > NORMAL_USER";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

