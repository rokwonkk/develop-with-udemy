package com.rokwonkk.springboot.demosecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    // add support for JDBC ... no more hardcoded users :-)
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        //define query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, pw, active from members where user_id = ?");

        //define query to retrieve the authorities/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id =?");

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 요청에 대한 인증 규칙을 설정
        http.authorizeHttpRequests(configurer ->
                        // 모든 요청에 대해서 인증이 필요( 어떤 요청이든 인증된 사용자만 접근 가능)
                        configurer
                            .requestMatchers("/").hasRole("EMPLOYEE") // 루트 경로에 대한 요청은 EMPLOYEE 권한을 가진 사용자만 접근 가능
                            .requestMatchers("/leaders/**").hasRole("MANAGER") // /leaders 경로에 대한 요청은 MANAGER 권한을 가진 사용자만 접근 가능
                            .requestMatchers("/systems/**").hasRole("ADMIN") // /systems 경로에 대한 요청은 ADMIN 권한을 가진 사용자만 접근 가능
                            .anyRequest()
                            .authenticated()
            )
            .formLogin(form -> // 폼 기반 로그인 설정
                form
                    .loginPage("/showMyLoginPage") // 로그인 페이지의 URL 설정 인증되지 않은 상태에서 보호된 페이지에 접근시도시 이 URL로 리다이렉트
                    .loginProcessingUrl("/authenticateTheUser") // 로그인 폼이 제출되었을 때 처리할 URL. 스프링 시큐리티가 제공하는 로그인 처리 서블릿에 매핑됨.
                    .permitAll() // 모든 사용자가 로그인 페이지와 로그인 처리 URL 대한 접근 허용
            )
            .logout(logout -> // 로그아웃 설정
                logout.permitAll() // 모든 사용자가 로그아웃 URL에 접근할 수 있도록 허용
            )
            .exceptionHandling(configurer -> // 예외 처리 설정
                configurer
                    .accessDeniedPage("/access-denied") // 접근 거부 페이지 설정
            );
        return http.build(); // HttpSecurity 객체를 빌드해 SecurityFilterChain을 반환. 필터 체인을 구성하는 데 사용
    }
    /*
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {

        UserDetails rok = User.builder()
                .username("rok")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails su = User.builder()
                .username("su")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails rin = User.builder()
                .username("rin")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(rok, su, rin);
    }
*/
}