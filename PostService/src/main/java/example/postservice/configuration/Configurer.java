package example.postservice.configuration;

import example.postservice.configuration.interceptor.CommentInterceptor;
import example.postservice.configuration.interceptor.PostInterceptor;
import example.postservice.service.CommentService;
import example.postservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class Configurer implements WebMvcConfigurer {

    private final PostService postService;
    private final CommentService commentService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/", "/join", "/login", "/member/**", "/error", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/post/**").authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/post")
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/"));

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PostInterceptor(postService))
                .order(1)
                .excludePathPatterns("/post/add")
                .addPathPatterns("/post/edit/*", "/post/*");

        registry.addInterceptor(new CommentInterceptor(commentService))
                .order(2)
                .addPathPatterns("/post/*/comment/edit/*", "/post/*/comment/*");
    }
}
