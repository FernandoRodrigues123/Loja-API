package com.gestaoDeLoja.repository;

import com.gestaoDeLoja.usuarios.GestorUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface GestorUsuarioRepository extends JpaRepository<GestorUsuario,Long> {

    UserDetails findByLogin(String login);
}
