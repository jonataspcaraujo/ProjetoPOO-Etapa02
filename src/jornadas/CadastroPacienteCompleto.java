package jornadas;

import repositorio.ClinicaRepositorio;
import model.*;
import exceptions.*;
import java.util.Scanner;

public class CadastroPacienteCompleto {
    private ClinicaRepositorio repositorio;
    private Scanner sc = new Scanner(System.in);

    public CadastroPacienteCompleto(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nCADASTRO COMPLETO");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        
        int idade = -1;
        while (idade < 0) {
            System.out.print("Idade: ");
            try {
                idade = Integer.parseInt(sc.nextLine());
                if (idade < 0) System.out.println("Idade não pode ser negativa.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Digite um numero.");
            }
        }
        
        System.out.print("Telefone: ");
        String tel = sc.nextLine();
        
        System.out.print("Possui convenio? (1-Sim / 2-Nao): ");
        int convOpt = 2;
        try {
            convOpt = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {}
        
        Convenio conv = null;
        
        if (convOpt == 1) {
            System.out.print("Nome do Convenio: ");
            String nomeConv = sc.nextLine();
            conv = repositorio.buscarConvenioPorNome(nomeConv);
            if (conv == null) {
                System.out.println("Convenio não encontrado. Paciente ficara sem convenio.");
            }
        }

        try {
            Paciente p = (conv != null) ? new Paciente(nome, cpf, idade, tel, conv) : new Paciente(nome, cpf, idade, tel);
            repositorio.adicionarPaciente(p);
            System.out.println("Paciente cadastrado com sucesso!");
        } catch (PacienteNaoEncontradoException e) {
            System.out.println("Erro (Duplicidade): " + e.getMessage());
        }
    }
}
