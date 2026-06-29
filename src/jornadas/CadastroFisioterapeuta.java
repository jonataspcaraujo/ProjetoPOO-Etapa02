package jornadas;

import repositorio.ClinicaRepositorio;
import model.*;
import java.util.Scanner;

public class CadastroFisioterapeuta {
    private ClinicaRepositorio repositorio;
    private Scanner sc = new Scanner(System.in);

    public CadastroFisioterapeuta(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nCADASTRO FISIOTERAPEUTA");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        Fisioterapeuta fisio = new Fisioterapeuta(nome, cpf);
        System.out.print("Valor Consulta: ");
        
        try { 
            fisio.setValorConsulta(Double.parseDouble(sc.nextLine())); 
        } catch(Exception e){}

        repositorio.adicionarProfissional(fisio);
        System.out.println(fisio.exibirResumo());
        System.out.println("Fisioterapeuta cadastrado!");
    }
}
