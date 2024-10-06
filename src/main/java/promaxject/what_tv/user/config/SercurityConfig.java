package promaxject.what_tv.user.config;//package com.group.what_tv.user.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@RequiredArgsConstructor
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SercurityConfig extends WebSecurityConfigurerAdapter {
//    private final UserDetailsService userDetailsService;
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/css/**" , "/js/**", "/images/**", "/favicon.ico");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable() // 보호 비활성화
//                .authorizeRequests()
//                .antMatchers("/", "/home")// 해당 경로는 누구나 접근할 수 있도록 허용
//                .permitAll()
//                .anyRequest().authenticated() // 그 외 모든 요청은 인증된 사용자만 접근 가능 (authenticated())
//                .and()
//                .formLogin()
//                .loginPage("/login") // 로그인 페이지는 /login.
//                .loginProcessingUrl("/loginProc") // 로그인 처리 URL은 loginProc.
//                .defaultSuccessUrl("/") // 로그인 성공 시 기본 리다이렉트 URL은 /.
//                .and()
//                .logout()
//                .logoutSuccessUrl("/") // 로그아웃 성공 후 /로 리다이렉트.
//                .invalidateHttpSession(true); // 로그아웃 시 세션 무효화 (invalidateHttpSession(true)).
//    }
//
//}
