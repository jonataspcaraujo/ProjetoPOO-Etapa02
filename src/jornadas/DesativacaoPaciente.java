package jornadas;

import repositorio.ClinicaRepositorio;
import model.*;
import exceptions.*;
import java.util.Scanner;

public class DesativacaoPaciente {
    private ClinicaRepositorio repositorio;
    private Scanner sc = new Scanner(System.in);

    public DesativacaoPaciente(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nDESATIVAR PACIENTE");
        System.out.print("CPF: ");
        
        try {
            Paciente p = repositorio.buscarPaciente(sc.nextLine());
            p.desativar();
            System.out.println("Paciente desativado.");
        } catch (PacienteNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
