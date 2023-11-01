package com.schoolv.schoolvsystem.controllers;


import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.schoolv.schoolvsystem.dtos.*;
import com.schoolv.schoolvsystem.infra.SecurityConfiguration;
import com.schoolv.schoolvsystem.infra.TokenService;
import com.schoolv.schoolvsystem.models.users.Usuario;
import com.schoolv.schoolvsystem.services.UsuarioService;
import com.schoolv.schoolvsystem.utils.SchoolSystemUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

    private static final String USUARIO_COMPLEMENTAR_USUARIO_RESUMO = "Esse endpoint realiza o complemento do usuario nas informações";
    private static final String USUARIO_COMPLEMENTAR_USUARIO_DESCRICAO = "Esse endpoint realiza o registro dos dados complementais do usuario, podendo utilizar para atualizar sempre que necessario o usuario";

    private static final String USUARIO_RECUPERAR_CODIGO_SENHA_RESUMO= "Esse endpoint gera um codigo para recuperação de senha que dura 5 minutos";
    private static final String USUARIO_RECUPERAR_CODIGO_SENHA_DESCRICAO = "Esse endpoint retorna um codigo para a recuperação de senha que tem cerca de 5 minutos de duração, após a finalização da recuperação o codigo expira";

    private static final String USUARIO_RECUPERAR_SENHA_RESUMO= "Esse endpoint solicita o email, codigo de recuperação e nova senha, para alterar a senha do usuario";
    private static final String USUARIO_RECUPERAR_SENHA_DESCRICAO = "Esse endpoint retorna sucesso, caso todas as informações estejam corretas e a senha do usuario tenha sido alterada com sucesso";
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

    @PostMapping(value = "/recover_password_code",name = USUARIOS)
    @Tag(name=USUARIOS)
    @Operation(summary = USUARIO_RECUPERAR_CODIGO_SENHA_RESUMO,description = USUARIO_RECUPERAR_CODIGO_SENHA_DESCRICAO)
    public ResponseEntity geraCodigoRecuperacaoSenha(@RequestBody @Valid GerarRecuperacaoSenhaDTO gerarRecuperacaoSenhaDTO){
        String codigoRecuperacao = "";
        try{
            codigoRecuperacao = usuarioService.geraCodigoRecuperacaoSenha(gerarRecuperacaoSenhaDTO);
        }catch (Exception e){
            return new  ResponseEntity<>(new Retorno(HttpStatus.INTERNAL_SERVER_ERROR,e.getCause().toString()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        CodigoRecuperacaoDTO codigoRecuperacaoDTO = new CodigoRecuperacaoDTO(codigoRecuperacao);
        return ResponseEntity.ok(new Retorno(HttpStatus.OK, SchoolSystemUtils.RECUPERACAO_SENHA_MENSAGEM,codigoRecuperacaoDTO));

    }

    @PostMapping(value = "/recover_password",name = USUARIOS)
    @Tag(name=USUARIOS)
    @Operation(summary = USUARIO_RECUPERAR_SENHA_RESUMO,description = USUARIO_RECUPERAR_SENHA_DESCRICAO)
    public ResponseEntity recuperaSenha(@RequestBody @Valid RecuperarSenhaDTO recuperarSenhaDTO){
        try{
            usuarioService.recuperaSenhaUsuario(recuperarSenhaDTO);
        }catch (Exception e){
            return new  ResponseEntity<>(new Retorno(HttpStatus.INTERNAL_SERVER_ERROR,e.getCause().toString()),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    return ResponseEntity.ok(new Retorno(HttpStatus.OK, "Senha alterada com sucesso!"));

    }

    @PostMapping(value = "/finish_user",name = USUARIOS)
    @Tag(name=USUARIOS)
    @Operation(summary = USUARIO_COMPLEMENTAR_USUARIO_RESUMO,description = USUARIO_COMPLEMENTAR_USUARIO_DESCRICAO)
    public ResponseEntity completaInformacoesUsuario(@RequestHeader(HttpHeaders.AUTHORIZATION) String tokenJWT,@RequestBody @Valid ComplementoUsuarioDadosDTO complementoUsuarioDadosDTO){
        try{
            usuarioService.completaUsuario(tokenJWT,complementoUsuarioDadosDTO);
        }catch (Exception e){
            return new  ResponseEntity<>(new Retorno(HttpStatus.INTERNAL_SERVER_ERROR,e.getCause().toString()),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(new Retorno(HttpStatus.OK, "Usuario Alterado com sucesso!"));

    }
}