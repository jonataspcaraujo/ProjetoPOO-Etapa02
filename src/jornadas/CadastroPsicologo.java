package jornadas;

import repositorio.ClinicaRepositorio;
import model.*;
import java.util.Scanner;

public class CadastroPsicologo {
    private ClinicaRepositorio repositorio;
    private Scanner sc = new Scanner(System.in);

    public CadastroPsicologo(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nCADASTRO PSICOLOGO");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        
        Psicologo psi = new Psicologo(nome, cpf);
        System.out.print("Abordagem Terapeutica: ");
        psi.setAbordagem(sc.nextLine());
        System.out.print("Valor Consulta: ");

        try { 
            psi.setValorConsulta(Double.parseDouble(sc.nextLine())); 
        } catch(Exception e){}
        
        repositorio.adicionarProfissional(psi);
        System.out.println(psi.exibirResumo());
        System.out.println("Psicologo cadastrado!");
    }
}
