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
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsuarioController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SecurityConfiguration securityConfiguration;
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacaoDTO dadosAutenticacao){
        var authToken = new UsernamePasswordAuthenticationToken(dadosAutenticacao.getLogin(),dadosAutenticacao.getSenha());
        var auth =  manager.authenticate(authToken);
        var tokenJWT = tokenService.gerarToken((Usuario) auth.getPrincipal());
        DecodedJWT decodedJWT =  JWT.decode(tokenJWT);

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT,decodedJWT.getExpiresAtAsInstant()));

    }

    @PostMapping("/register")
    public ResponseEntity realizarCadastro(@RequestBody @Valid RegistroUsuarioDTO registroUsuarioDTO){
        try{
            usuarioService.cadastraUsuario(registroUsuarioDTO);
        }catch (Exception e){
            return new  ResponseEntity<>(new Retorno(HttpStatus.INTERNAL_SERVER_ERROR,e.getCause().toString()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(new Retorno(HttpStatus.OK,"Usuario cadastrado com sucesso"));

    }

    @PostMapping("/register/professor")
    public ResponseEntity realizarCadastroProfessor(@RequestBody @Valid RegistroUsuarioDTO registroUsuarioDTO){
        try{
            usuarioService.cadastraProfessor(registroUsuarioDTO);
        }catch (Exception e){
            return new  ResponseEntity<>(new Retorno(HttpStatus.INTERNAL_SERVER_ERROR,e.getCause().toString()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(new Retorno(HttpStatus.OK,"Usuario cadastrado com sucesso"));

    }

    @PostMapping("/register/staff")
    public ResponseEntity realizarCadastroStaff(@RequestBody @Valid RegistroUsuarioDTO registroUsuarioDTO){
        try{
            usuarioService.cadastraStaff(registroUsuarioDTO);
        }catch (Exception e){
            return new  ResponseEntity<>(new Retorno(HttpStatus.INTERNAL_SERVER_ERROR,e.getCause().toString()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(new Retorno(HttpStatus.OK,"Usuario cadastrado com sucesso"));

    }


    @GetMapping("/teste")
    public ResponseEntity teste(){
       return ResponseEntity.ok("teste");
    }



}
