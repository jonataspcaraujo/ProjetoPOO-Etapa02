package jornadas;

import repositorio.ClinicaRepositorio;
import model.*;
import exceptions.*;
import java.util.Scanner;

public class CadastroPacienteSimplificado {
    private ClinicaRepositorio repositorio;
    private Scanner sc = new Scanner(System.in);

    public CadastroPacienteSimplificado(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nCADASTRO SIMPLIFICADO");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        try {
            Paciente p = new Paciente(nome, cpf);
            repositorio.adicionarPaciente(p);
            System.out.println("Paciente cadastrado com sucesso!");
        } catch (PacienteNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
