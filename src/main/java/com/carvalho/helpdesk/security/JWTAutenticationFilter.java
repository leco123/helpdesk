package com.carvalho.helpdesk.security;

import com.carvalho.helpdesk.domain.dtos.CredenciaisDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Gerenciar as autenticações
 * @author Alex de Carvalho
 */
@AllArgsConstructor
@NoArgsConstructor
public class JWTAutenticationFilter extends UsernamePasswordAuthenticationFilter {

   // Principal internface para estratégia da aplicação ele valida se usuário/principal esta autenticado ou não
    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;

    /**
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            // Lêr o corpo da requisição endpoint /login
            CredenciaisDTO credencial = new ObjectMapper().readValue(request.getInputStream(), CredenciaisDTO.class);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(credencial.getEmail(), credencial.getSenha(), new ArrayList<>());
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            return authentication;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Autenticado em caso de sucesso
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // pegando username
        String username = ((UserSS) authResult.getPrincipal()).getUsername();
        String token = jwtUtil.generateToken(username);
        response.setHeader("access-control-expose-headers","Authorization");
        response.setHeader("Authorization","Bearer "+token);
    }

    /**
     * Não conseguiu se autenticar
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(401);
        response.setContentType("application/json");
        response.getWriter().append(json());
    }

    private CharSequence json() {
        long date = new Date().getTime();
        return "{" +
                 "\"timestamp\": "+date+", "
                +"\"status\": 401, "
                +"\"error\": \"Não autorizado\", "
                +"\"message\": \"E-mail ou senha inválidos\", "
                +"\"path\": \"/login\" "
                +"}";
    }
}
