package com.simplante.config;

import com.simplante.security.CorsFilter;
import com.simplante.security.JWTAuthentificationFilter;
import com.simplante.security.JWTAuthorizationFilter;
import com.simplante.security.UserDetailServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * @author Moïse Coulanges
 * @version 0.1
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private UserDetailServiceImpl userDetailsService;
    private PasswordEncoder passwordEncoder;

    public SecurityConfiguration(UserDetailServiceImpl userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    /**
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers("/", "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js")
                .antMatchers("/h2/**");
    }

    /**
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login/**", "/api/users/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/posts", "/api/categories/**").permitAll()
                .antMatchers("/api/categories/**").hasAuthority("ADMIN")
                .antMatchers("/api/**").authenticated().and()
                .addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class)
                .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilter(new JWTAuthentificationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

/*     @Bean
     CorsConfigurationSource corsConfigurationSource() {
     CorsConfiguration configuration = new CorsConfiguration();
     configuration.setAllowedOrigins(Arrays.asList("*","http://localhost:4200"));
     configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
     configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Origin","X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));
     configuration.setAllowCredentials(true);
     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
     source.registerCorsConfiguration("/**", configuration);
     return source;
     }*/

}
