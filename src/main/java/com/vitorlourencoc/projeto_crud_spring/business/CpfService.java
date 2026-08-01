package com.vitorlourencoc.projeto_crud_spring.business;

import org.springframework.stereotype.Service;

@Service
public class CpfService {

    public String removerMascara(String cpf){
        String cpfSemMascara = cpf.replace(".", "").replace("-","");
        return cpfSemMascara;
    }

    public boolean verificarTamanho(String cpf){
        return cpf.length() == 11;
    }

    public boolean verificarSomenteNumero(String cpf) {
        for (int i = 0; i < cpf.length(); i++) {
            char character = cpf.charAt(i);
            if (!Character.isDigit(character)){
                return false;
            }
        }
        return true;
    }

    public boolean verificarNumerosIguais(String cpf){
        char primeiroNumero = cpf.charAt(0);
        for (int i = 1; i < cpf.length() ; i++) {
            if (primeiroNumero != cpf.charAt(i)){
                return false;
            }
        }
        return true;
    }

    public boolean verificarPrimeiroDigito(String cpf){
        int primeiroDigito;
        int soma = 0;

        for (int i = 0; i <9; i++) {
            int numero = (cpf.charAt(i) - '0') * (10 - i);
            soma = soma + numero;
        }
        if (soma % 11 < 2) {
            primeiroDigito = 0;
        }
        else {
            primeiroDigito = 11 - (soma % 11);
        }
        return primeiroDigito == (cpf.charAt(9)) - '0';
    }

    public boolean verificarSegundoDigito(String cpf){
        int segundoDigito;
        int soma = 0;

        for (int i = 0; i <10; i++) {
            int numero = (cpf.charAt(i) - '0') * (11 - i);
            soma = soma + numero;
        }
        if (soma % 11 < 2) {
            segundoDigito = 0;
        }
        else {
            segundoDigito = 11 - (soma % 11);
        }
        return segundoDigito == (cpf.charAt(10)) - '0';
    }

    public boolean validarCPF(String cpf){
        String cpfSemMascara = removerMascara(cpf);
        if (verificarTamanho(cpfSemMascara)
                && verificarSomenteNumero(cpfSemMascara)
                && !verificarNumerosIguais(cpfSemMascara)
                && verificarPrimeiroDigito(cpfSemMascara)
                && verificarSegundoDigito(cpfSemMascara))
            return true;
        else{
            return false;
        }
    }
}
