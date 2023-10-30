package com.schoolv.schoolvsystem.services;

import com.schoolv.schoolvsystem.dtos.GerarRecuperacaoSenhaDTO;
import com.schoolv.schoolvsystem.dtos.RecuperarSenhaDTO;
import com.schoolv.schoolvsystem.dtos.RegistroUsuarioDTO;
import com.schoolv.schoolvsystem.enums.UserRoles;
import com.schoolv.schoolvsystem.infra.exceptions.ExceptionGenerica;
import com.schoolv.schoolvsystem.models.users.Roles;
import com.schoolv.schoolvsystem.models.users.Usuario;
import com.schoolv.schoolvsystem.models.users.UsuarioRecuperarSenha;
import com.schoolv.schoolvsystem.repositories.users.RecuperarSenhaRepository;
import com.schoolv.schoolvsystem.repositories.users.RoleRepository;
import com.schoolv.schoolvsystem.repositories.users.UsuarioRepository;
import com.schoolv.schoolvsystem.utils.SchoolSystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RecuperarSenhaRepository recuperarSenhaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(username);
    }

    public void cadastraUsuario(RegistroUsuarioDTO registroUsuarioDTO) {
        try {
            UserDetails usuarioExiste = usuarioRepository.findByLogin(registroUsuarioDTO.getEmail());
            if(usuarioExiste != null){
                throw new ExceptionGenerica("O usuario já existe! Favor solicitar redefinição de senha");
            }
            String encodePassword = passwordEncoder.encode(registroUsuarioDTO.getSenha());
            Usuario usuario = new Usuario(registroUsuarioDTO.getEmail(),encodePassword);
            usuario = usuarioRepository.save(usuario);
            roleRepository.save(new Roles(usuario, UserRoles.USER.getCodigoRole(),true));

        }catch (Exception e){
            throw new ExceptionGenerica("Erro ao cadastrar usuario ",e);
        }


    }


    public void cadastraProfessor(RegistroUsuarioDTO registroUsuarioDTO) {
        try {
            UserDetails usuarioExiste = usuarioRepository.findByLogin(registroUsuarioDTO.getEmail());
            if(usuarioExiste != null){
                throw new ExceptionGenerica("O usuario já existe! Favor solicitar redefinição de senha");
            }
            String encodePassword = passwordEncoder.encode(registroUsuarioDTO.getSenha());
            Usuario usuario = new Usuario(registroUsuarioDTO.getEmail(),encodePassword);
            usuario = usuarioRepository.save(usuario);
            roleRepository.save(new Roles(usuario, UserRoles.PROFESSOR.getCodigoRole(),true));

        }catch (Exception e){
            throw new ExceptionGenerica("Erro ao cadastrar usuario ",e);
        }


    }

    public void cadastraStaff(RegistroUsuarioDTO registroUsuarioDTO) {
        try {
            UserDetails usuarioExiste = usuarioRepository.findByLogin(registroUsuarioDTO.getEmail());
            if(usuarioExiste != null){
                throw new ExceptionGenerica("O usuario já existe! Favor solicitar redefinição de senha");
            }
            String encodePassword = passwordEncoder.encode(registroUsuarioDTO.getSenha());
            Usuario usuario = new Usuario(registroUsuarioDTO.getEmail(),encodePassword);
            usuario = usuarioRepository.save(usuario);
            roleRepository.save(new Roles(usuario, UserRoles.STAFF.getCodigoRole(),true));

        }catch (Exception e){
            throw new ExceptionGenerica("Erro ao cadastrar usuario ",e);
        }


    }

    public String geraCodigoRecuperacaoSenha(GerarRecuperacaoSenhaDTO gerarRecuperacaoSenhaDTO) {
        String codigoRecuperacao = "";
        try{
            UserDetails usuarioExiste = usuarioRepository.findByLogin(gerarRecuperacaoSenhaDTO.getEmailRecuperacao());
            if(usuarioExiste == null ){
                throw new ExceptionGenerica("O usuario não existe em nossos registros! Por favor registre-se");
            }
            UsuarioRecuperarSenha usuarioRecuperarSenha = new UsuarioRecuperarSenha();
            usuarioRecuperarSenha.setEmailRecuperacao(gerarRecuperacaoSenhaDTO.getEmailRecuperacao());
            usuarioRecuperarSenha.setCodigoRecuperacao(SchoolSystemUtils.geradorCodigoRecuperacaoSenha());
            UsuarioRecuperarSenha usuarioRecuperacao = recuperarSenhaRepository.save(usuarioRecuperarSenha);
            //TODO chamar o serviço de envio de emails

            codigoRecuperacao = usuarioRecuperacao.getCodigoRecuperacao();
        }catch (Exception e){
            throw new ExceptionGenerica("Erro ao recuperar senha do usuario ",e);
        }


        return codigoRecuperacao;
    }

    public void recuperaSenhaUsuario(RecuperarSenhaDTO recuperarSenhaDTO) {
       try{
           UsuarioRecuperarSenha usuarioRecuperacao = recuperarSenhaRepository.findByEmailRecuperacaoAndCodigoRecuperacao(recuperarSenhaDTO.getEmailRecuperacao(), recuperarSenhaDTO.getCodigoRecuperacao());
           if(usuarioRecuperacao == null){
               throw new ExceptionGenerica("O codigo informado está incorreto, informe o codigo certo para recuperar sua senha!");
           }
           Usuario usuario = (Usuario) usuarioRepository.findByLogin(recuperarSenhaDTO.getEmailRecuperacao());
           if(usuario == null){
               throw new ExceptionGenerica("Erro ao redifinir senha do usuario");
           }
           String encodePassword = passwordEncoder.encode(recuperarSenhaDTO.getNovaSenha());
           usuario.setSenha(encodePassword);
           usuarioRepository.save(usuario);

       }catch (Exception e){
           throw new ExceptionGenerica("Erro ao redifinir senha do usuario ",e);
       }

    }
}
