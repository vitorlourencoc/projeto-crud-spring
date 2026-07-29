package com.vitorlourencoc.projeto_crud_spring.business;

import com.vitorlourencoc.projeto_crud_spring.infrastructure.Usuario;
import com.vitorlourencoc.projeto_crud_spring.infrastructure.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    // Injecao de depedencia via construtor
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.repository = usuarioRepository;
    }

    public void salvarUsuario(Usuario usuario){
        repository.saveAndFlush(usuario);
    }

    public Usuario buscarUsuarioPorEmail(String email){

        return repository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Email não encontrado!")
        );
    }

    public Usuario buscarUsuarioPorTelefone(String telefone){

        return repository.findByTelefone(telefone).orElseThrow(
                () -> new RuntimeException("Telefone não encontrado!")
        );
    }

    public void deletarUsuarioPorEmail(String email){
        repository.deleteByEmail(email);
    }

    public void deletarUsuarioPorTelefone(String telefone){
        repository.deleteByTelefone(telefone);
    }

    public void atualizarUsuarioPorId(Integer id, Usuario usuario){
        Usuario usuarioEntity = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Id não encontrado!")
        );
        Usuario usuarioAtualizado = usuario.builder()
                        .email(usuario.getEmail() != null ? usuario.getEmail() : usuarioEntity.getEmail())
                        .nome(usuario.getNome() != null ? usuario.getNome() : usuarioEntity.getNome())
                        .telefone(usuario.getTelefone() != null ? usuario.getTelefone() : usuarioEntity.getTelefone())
                        .id(usuarioEntity.getId()).build();
        repository.saveAndFlush(usuarioAtualizado);
    }



}
