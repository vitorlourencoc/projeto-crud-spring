package com.vitorlourencoc.projeto_crud_spring.business;

import com.vitorlourencoc.projeto_crud_spring.infrastructure.UsuarioEntity;
import com.vitorlourencoc.projeto_crud_spring.infrastructure.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final CpfService cpfService;

    private final UsuarioRepository repository;

    public UsuarioService(CpfService cpfService, UsuarioRepository repository) {
        this.cpfService = cpfService;
        this.repository = repository;
    }

    //SALVANDO USUARIO

    public void salvarUsuario(UsuarioEntity usuario){

        if (cpfService.validarCPF(usuario.getCpf())){
            repository.saveAndFlush(usuario);
        }else{
            throw new RuntimeException("CPF invalido");
        }
    }

    //BUSCANDO USUARIO POR

    public UsuarioEntity buscarUsuarioPorEmail(String email){

        return repository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Email não encontrado!")
        );
    }

    public UsuarioEntity buscarUsuarioPorTelefone(String telefone){

        return repository.findByTelefone(telefone).orElseThrow(
                () -> new RuntimeException("Telefone não encontrado!")
        );
    }

    public UsuarioEntity buscarUsuarioPorCpf(String cpf){
        return repository.findByCpf(cpf).orElseThrow(
                () -> new RuntimeException("CPF não encontrado!")
        );
    }

    //DELETAR USUARIO POR

    public void deletarUsuarioPorEmail(String email){
        repository.deleteByEmail(email);
    }

    public void deletarUsuarioPorTelefone(String telefone){
        repository.deleteByTelefone(telefone);
    }

    public void deletarUsuarioPorCpf(String cpf){
        repository.deleteByCpf(cpf);
    }

    //ATUALIZANDO USUARIO

    public void atualizarUsuarioPorId(Integer id, UsuarioEntity usuario){
        UsuarioEntity usuarioEntity = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Id não encontrado!")
        );
        UsuarioEntity usuarioAtualizado = usuario.builder()
                .email(usuario.getEmail() != null ? usuario.getEmail() : usuarioEntity.getEmail())
                .nome(usuario.getNome() != null ? usuario.getNome() : usuarioEntity.getNome())
                .telefone(usuario.getTelefone() != null ? usuario.getTelefone() : usuarioEntity.getTelefone())
                .id(usuarioEntity.getId()).build();
        repository.saveAndFlush(usuarioAtualizado);
    }
}
