package com.carvalho.helpdesk.services;

import com.carvalho.helpdesk.domain.Pessoa;
import com.carvalho.helpdesk.domain.repository.PessoaRepository;
import com.carvalho.helpdesk.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Serviço para implementar Usuário pelo Usernamme que em nosso caso é o email
 * @author Alex de Carvalho
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PessoaRepository repository;

    /**
     * Carregar usuário por e-mail
     * @param email
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Pessoa> user = repository.findByEmail(email);
        if (user.isPresent()){
            return new UserSS(user.get().getId(), user.get().getEmail(), user.get().getSenha(), user.get().getPerfis());
        }
        throw  new UsernameNotFoundException(email);
    }
}
