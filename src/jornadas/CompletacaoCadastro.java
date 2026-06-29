package jornadas;

import repositorio.ClinicaRepositorio;
import model.*;
import exceptions.*;
import java.util.Scanner;

public class CompletacaoCadastro {
    private ClinicaRepositorio repositorio;
    private Scanner sc = new Scanner(System.in);

    public CompletacaoCadastro(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nCOMPLEMENTAR CADASTRO");
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        
        try {
            Paciente p = repositorio.buscarPaciente(cpf);
            int idade = -1;
            while(idade < 0) {
                System.out.print("Idade: ");
                try { idade = Integer.parseInt(sc.nextLine()); } 
                catch (NumberFormatException e) { System.out.println("Numero invalido!"); }
            }
            System.out.print("Telefone: ");
            String tel = sc.nextLine();
            
            System.out.print("Vai informar convenio? (1-Sim / 2-Nao): ");
            int opt = 2;
            try { opt = Integer.parseInt(sc.nextLine()); } catch (Exception e) {}
            
            if (opt == 1) {
                System.out.print("Nome do Convenio: ");
                Convenio conv = repositorio.buscarConvenioPorNome(sc.nextLine());
                p.complementar(idade, tel, conv);
            } else {
                p.complementar(idade, tel);
            }
            System.out.println("Cadastro atualizado!");
        } catch (PacienteNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
