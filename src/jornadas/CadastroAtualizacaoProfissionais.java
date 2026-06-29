package jornadas;

import repositorio.ClinicaRepositorio;
import model.*;
import exceptions.*;
import java.util.Scanner;

public class CadastroAtualizacaoProfissionais {
    private ClinicaRepositorio repositorio;
    private Scanner sc = new Scanner(System.in);

    public CadastroAtualizacaoProfissionais(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nCADASTRO/ATUALIZACAO PROFISSIONAL GERAL");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        
        System.out.println("Especialidade (clinica geral / nutricao): ");
        String esp = sc.nextLine().toLowerCase();
        
        Profissional prof = null;
        if (esp.equals("clinica geral")) prof = new ClinicoGeral(nome, cpf);
        else if (esp.equals("nutricao")) prof = new Nutricionista(nome, cpf);
        else {
            System.out.println("Use Cadastro especifico para Fisioterapia ou Psicologia.");
            return;
        }
        
        System.out.print("Valor Consulta: ");
        try { prof.setValorConsulta(Double.parseDouble(sc.nextLine())); } catch(Exception e){}
        
        repositorio.adicionarProfissional(prof);
        System.out.println("Profissional cadastrado!");
    }
}
