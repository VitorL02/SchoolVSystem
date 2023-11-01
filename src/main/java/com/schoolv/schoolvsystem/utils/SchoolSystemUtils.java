package com.schoolv.schoolvsystem.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SchoolSystemUtils {
    private static final String REGEX = "[A-Z0-9]{7}";
    public static  final String RECUPERACAO_SENHA_MENSAGEM = "Solicitação feita com sucesso, esse codigo foi enviado para seu email! Utilize para recuperar a senha, ele expira em 5 minutos!";


    public static String geradorCodigoRecuperacaoSenha(){
            StringBuilder code = new StringBuilder(7);
            Pattern pattern = Pattern.compile(REGEX);
            Matcher matcher = pattern.matcher("");

            while (!matcher.matches()) {
                code.setLength(0); // Limpa o StringBuilder
                for (int i = 0; i < 7; i++) {
                    int randomIndex = (int) (Math.random() * 36); // 26 letras + 10 dígitos
                    char randomChar = (randomIndex < 26) ? (char) ('A' + randomIndex) : (char) ('0' + randomIndex - 26);
                    code.append(randomChar);
                }
                matcher.reset(code.toString());
            }
            return code.toString();
    }


}
