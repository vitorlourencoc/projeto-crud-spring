package com.vitorlourencoc.projeto_crud_spring.infrastructure;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    Optional<UsuarioEntity> findByEmail(String email);

    Optional<UsuarioEntity> findByTelefone(String telefone);

    Optional<UsuarioEntity> findByCpf(String cpf);

    @Transactional
    void deleteByEmail(String email);

    @Transactional
    void deleteByTelefone(String telefone);

    @Transactional
    void deleteByCpf(String cpf);
}
