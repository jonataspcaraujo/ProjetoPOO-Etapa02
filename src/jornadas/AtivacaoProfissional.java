package jornadas;

import repositorio.ClinicaRepositorio;
import model.*;
import exceptions.*;
import java.util.Scanner;

public class AtivacaoProfissional {
    private ClinicaRepositorio repositorio;
    private Scanner sc = new Scanner(System.in);

    public AtivacaoProfissional(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nATIVAR PROFISSIONAL");
        System.out.print("Nome do Profissional: ");
        try {
            Profissional p = repositorio.buscarProfissional(sc.nextLine());
            System.out.println("Reativacao concluida.");
        } catch (ProfissionalNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
