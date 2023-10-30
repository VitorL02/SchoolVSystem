package com.schoolv.schoolvsystem.controllers;


import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.schoolv.schoolvsystem.dtos.DadosAutenticacaoDTO;
import com.schoolv.schoolvsystem.dtos.DadosTokenJWT;
import com.schoolv.schoolvsystem.dtos.RegistroUsuarioDTO;
import com.schoolv.schoolvsystem.dtos.Retorno;
import com.schoolv.schoolvsystem.infra.SecurityConfiguration;
import com.schoolv.schoolvsystem.infra.TokenService;
import com.schoolv.schoolvsystem.models.users.Usuario;
import com.schoolv.schoolvsystem.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users",name = "Usuarios")
public class UsuarioController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SecurityConfiguration securityConfiguration;
    @Autowired
    private UsuarioService usuarioService;

    private static final String USUARIO_LOGIN_RESUMO = "Esse endpoint realiza o login na aplicação";
    private static final String USUARIO_LOGIN_DESCRICAO = "Esse endpoint realiza o login na aplicação, retornando um tokenJWT que será utilizado em toda a aplicação.";
    private static final String USUARIO_REGISTRAR_RESUMO = "Esse endpoint realiza o registro de usuarios comuns na aplicação";
    private static final String USUARIO_REGISTRAR_DESCRICAO = "Esse endpoint realiza o registro de usuarios comuns na aplicação, permitindo com que ele faça login";
    private static final String USUARIO_REGISTRAR_PROFESSOR_RESUMO = "Esse endpoint realiza o registro de um professor na aplicação";
    private static final String USUARIO_REGISTRAR_PROFESSOR_DESCRICAO = "Esse endpoint realiza o registro do professor na aplicação, somente a STAFF e ADMINS podem registrar professores, por isso e necessario utilizar o token.";
    private static final String USUARIO_REGISTRAR_STAFF_RESUMO = "Esse endpoint realiza o registro da STAFF na aplicação";
    private static final String USUARIO_REGISTRAR_STAFF_DESCRICAO = "Esse endpoint realiza o registro de uma pessoa da STAFF na aplicação, somente ADMINS podem registrar novas STAFFS.";
    private static final String USUARIOS = "Usuarios";


    @PostMapping(value = "/login",name = USUARIOS)
    @Tag(name=USUARIOS)
    @Operation(summary = USUARIO_LOGIN_RESUMO,description = USUARIO_LOGIN_DESCRICAO)
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacaoDTO dadosAutenticacao){
        var authToken = new UsernamePasswordAuthenticationToken(dadosAutenticacao.getLogin(),dadosAutenticacao.getSenha());
        var auth =  manager.authenticate(authToken);
        var tokenJWT = tokenService.gerarToken((Usuario) auth.getPrincipal());
        DecodedJWT decodedJWT =  JWT.decode(tokenJWT);

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT,decodedJWT.getExpiresAtAsInstant()));

    }

    @PostMapping(value = "/register",name = USUARIOS)
    @Tag(name=USUARIOS)
    @Operation(summary = USUARIO_REGISTRAR_RESUMO,description = USUARIO_REGISTRAR_DESCRICAO)
    public ResponseEntity realizarCadastro(@RequestBody @Valid RegistroUsuarioDTO registroUsuarioDTO){
        try{
            usuarioService.cadastraUsuario(registroUsuarioDTO);
        }catch (Exception e){
            return new  ResponseEntity<>(new Retorno(HttpStatus.INTERNAL_SERVER_ERROR,e.getCause().toString()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(new Retorno(HttpStatus.OK,"Usuario cadastrado com sucesso"));

    }

    @PostMapping(value = "/register/professor",name = USUARIOS)
    @Tag(name=USUARIOS)
    @Operation(summary = USUARIO_REGISTRAR_PROFESSOR_RESUMO,description = USUARIO_REGISTRAR_PROFESSOR_DESCRICAO)
    public ResponseEntity realizarCadastroProfessor(@RequestBody @Valid RegistroUsuarioDTO registroUsuarioDTO){
        try{
            usuarioService.cadastraProfessor(registroUsuarioDTO);
        }catch (Exception e){
            return new  ResponseEntity<>(new Retorno(HttpStatus.INTERNAL_SERVER_ERROR,e.getCause().toString()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(new Retorno(HttpStatus.OK,"Usuario cadastrado com sucesso"));

    }

    @PostMapping(value = "/register/staff",name = USUARIOS)
    @Tag(name=USUARIOS)
    @Operation(summary = USUARIO_REGISTRAR_STAFF_RESUMO,description = USUARIO_REGISTRAR_STAFF_DESCRICAO)
    public ResponseEntity realizarCadastroStaff(@RequestBody @Valid RegistroUsuarioDTO registroUsuarioDTO){
        try{
            usuarioService.cadastraStaff(registroUsuarioDTO);
        }catch (Exception e){
            return new  ResponseEntity<>(new Retorno(HttpStatus.INTERNAL_SERVER_ERROR,e.getCause().toString()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(new Retorno(HttpStatus.OK,"Usuario cadastrado com sucesso"));

    }


}
