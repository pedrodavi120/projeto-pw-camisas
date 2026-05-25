package ufrn.br.provaPW.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Questão 14
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Questão 15: Controle de Acesso Baseado em Papéis (RBAC)
                .requestMatchers("/css/**", "/images/**", "/js/**").permitAll()
                .requestMatchers("/login", "/logout").permitAll()
                // Questão 15: Cadastro e Exclusão exclusivos para ADMIN
                .requestMatchers("/cadastro", "/editar", "/deletar", "/restaurar", "/salvar", "/admin").hasRole("ADMIN")
                // Questão 15: Demais rotas exigem autenticação
                .anyRequest().authenticated()
            )
            .formLogin(form -> form // Questão 15: Página de login padrão
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Questão 14: Usuários em memória com senhas hasheadas por BCrypt
        UserDetails admin = User.withUsername("Administrador")
                .password(passwordEncoder().encode("admin123"))
                .roles("ADMIN")
                .build();

        UserDetails visitante = User.withUsername("Visitante")
                .password(passwordEncoder().encode("visitante123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, visitante);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Questão 14: Definição do BCryptPasswordEncoder
        return new BCryptPasswordEncoder();
    }
}
