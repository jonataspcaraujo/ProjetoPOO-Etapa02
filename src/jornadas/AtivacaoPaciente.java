package jornadas;

import repositorio.ClinicaRepositorio;
import model.*;
import exceptions.*;
import java.util.Scanner;

public class AtivacaoPaciente {
    private ClinicaRepositorio repositorio;
    private Scanner sc = new Scanner(System.in);

    public AtivacaoPaciente(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nATIVAR PACIENTE");
        System.out.print("CPF: ");
        try {
            Paciente p = repositorio.buscarPaciente(sc.nextLine());
            p.setAtivo(true);
            System.out.println("Paciente reativado.");
        } catch (PacienteNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
