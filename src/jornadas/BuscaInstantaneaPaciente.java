package jornadas;

import repositorio.ClinicaRepositorio;
import model.*;
import exceptions.*;
import java.util.Scanner;

public class BuscaInstantaneaPaciente {
    private ClinicaRepositorio repositorio;
    private Scanner sc = new Scanner(System.in);

    public BuscaInstantaneaPaciente(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nBUSCA INSTANTANEA");
        System.out.print("CPF: ");
        try {
            Paciente p = repositorio.buscarPaciente(sc.nextLine());
            System.out.println(p.exibirResumo());
        } catch (PacienteNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
