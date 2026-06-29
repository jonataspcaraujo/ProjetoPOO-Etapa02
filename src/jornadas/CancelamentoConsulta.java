package jornadas;

import repositorio.ClinicaRepositorio;
import model.*;
import exceptions.*;
import java.util.Scanner;

public class CancelamentoConsulta {
    private ClinicaRepositorio repositorio;
    private Scanner sc = new Scanner(System.in);

    public CancelamentoConsulta(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nCANCELAR CONSULTA");
        
        System.out.print("Indice da Consulta: ");
        int indice;

        try { 
            indice = Integer.parseInt(sc.nextLine()); 
        } catch (Exception e) { 
            System.out.println("Indice invalido."); 
            return; 
        }
        
        try {
            Consulta c = repositorio.getConsulta(indice);
            if (c.getStatus().equals("realizada")) 
                throw new OperacaoInvalidaException("Consulta ja realizada.");
            if (c.getStatus().equals("cancelada")) 
                throw new OperacaoInvalidaException("Consulta ja cancelada.");
            
            System.out.print("Motivo: ");
            c.cancelar(sc.nextLine());
            
            repositorio.getMultas().add(50.0);

            System.out.println("Consulta cancelada. Multa aplicada se aplicavel.");
        } catch (ConsultaNaoEncontradaException | OperacaoInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}