package relatorios;

import repositorio.ClinicaRepositorio;
import java.util.Scanner;

public class GerarRelatorios {
    private ClinicaRepositorio repositorio;
    private Scanner sc = new Scanner(System.in);

    public GerarRelatorios(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        int op = -1;
        while (op != 0) {
            System.out.println("\nRELATORIOS GERENCIAIS");
            System.out.println("1 - Relatorio Unificado de Cadastros");
            System.out.println("2 - Relatorio Geral de Consultas");
            System.out.println("3 - Relatorio Financeiro");
            System.out.println("4 - Relatorio de Cancelamentos");
            System.out.println("5 - Relatorio de Multas");
            System.out.println("6 - Exportacao de Dados Operacionais");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            
            try { op = Integer.parseInt(sc.nextLine()); } catch (NumberFormatException e) { continue; }
            
            switch (op) {
                case 1: 
                    new RelatorioUnificadoCadastro(repositorio).executar(); 
                    break;
                case 2: 
                    new RelatorioGeralConsultas(repositorio).executar(); 
                    break;
                case 3: 
                    new RelatorioFinanceiro(repositorio).executar(); 
                    break;
                case 4: 
                    new RelatorioCancelamentos(repositorio).executar(); 
                    break;
                case 5: 
                    new RelatorioMultas(repositorio).executar(); 
                    break;
                case 6: 
                    new ExportacaoDadosOperacionais(repositorio).executar(); 
                    break;
                case 0: 
                    break;
                default: 
                    System.out.println("Opcao invalida!"); 
                    break;
            }
        }
    }
}