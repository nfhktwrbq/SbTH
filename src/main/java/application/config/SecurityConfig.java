package application.config;


import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
       // auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        //authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/app/create-user", "/app/login").permitAll()
                //.antMatchers("/app/login").permitAll()
                .antMatchers("/app/user-info", "/app/hello").hasAnyRole("USER", "ADMIN")
                .antMatchers("/app/user-all", "/app/admin-page").hasAnyRole("ADMIN")
                .antMatchers("/app/user").hasRole("USER")
                .anyRequest().authenticated()
                .and().formLogin()  //login configuration
                .loginPage("/app/login")
                .defaultSuccessUrl("/app/")
                .permitAll()
                //.loginProcessingUrl("/login")
               // .usernameParameter("app_username")
               // .passwordParameter("app_password")
               // .defaultSuccessUrl("/app/hello")
                .and().logout()    //logout configuration
                .logoutUrl("/app/logout")
                .logoutSuccessUrl("/app/login")
              //  .logoutSuccessUrl("/app/login")
               // .and().exceptionHandling() //exception handling configuration
               // .accessDeniedPage("/app/403");
        .permitAll().and().exceptionHandling().accessDeniedPage("/app/403");
    }
   /* @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }*/

    /*@Bean
    DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setDefaultRolePrefix("");
        return handler;
    }*/
}
