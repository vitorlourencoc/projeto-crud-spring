package com.vitorlourencoc.projeto_crud_spring.infrastructure;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByTelefone(String telefone);

    @Transactional //Caso de um algum erro não pode deletar
    void deleteByEmail(String email);

    @Transactional //Caso de um algum erro não pode deleta
    void deleteByTelefone(String telefone);




}
