package com.schoolv.schoolvsystem.services;

import com.schoolv.schoolvsystem.dtos.RegistroUsuarioDTO;
import com.schoolv.schoolvsystem.enums.UserRoles;
import com.schoolv.schoolvsystem.infra.exceptions.ExceptionGenerica;
import com.schoolv.schoolvsystem.models.users.Roles;
import com.schoolv.schoolvsystem.models.users.Usuario;
import com.schoolv.schoolvsystem.repositories.users.RoleRepository;
import com.schoolv.schoolvsystem.repositories.users.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

}
