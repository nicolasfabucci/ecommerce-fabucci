package com.nicolasfabucci.ecommercefabucci.service;

import com.nicolasfabucci.ecommercefabucci.handler.NotFoundException;
import com.nicolasfabucci.ecommercefabucci.models.documents.UsuarioDocument;
import com.nicolasfabucci.ecommercefabucci.repositories.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UsuarioRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UsuarioDocument user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    return UserDetailsImpl.build(user);
  }

  public Optional<UsuarioDocument> getUserById(final String username) {
    Optional<UsuarioDocument> user = userRepository.findByUsername(username);
    if(user.isPresent()) {
      log.info("Usuario encontrado" + LocalDate.now());
      return user;
    } else {
      log.error("Usuario no encontrado en la base de datos" + LocalDate.now());
      throw new NotFoundException("No existe usuario");
    }
  }

}
