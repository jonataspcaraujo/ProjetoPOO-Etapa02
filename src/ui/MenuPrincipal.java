package ui;

import java.util.Scanner;
import jornadas.*;
import repositorio.ClinicaRepositorio;

public class MenuPrincipal {
    private ClinicaRepositorio repositorio;
    private Scanner sc;

    public MenuPrincipal(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
        this.sc = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao = -1;
        
        while (opcao != 0) {
            System.out.println("\n");
            System.out.println("CLINICA VIDAPLENA");
            System.out.println("1 - Pacientes");
            System.out.println("2 - Profissionais");
            System.out.println("3 - Consultas");
            System.out.println("4 - Atendimentos");
            System.out.println("5 - Pagamentos");
            System.out.println("6 - Relatorios");
            System.out.println("7 - Horarios");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opcao invalida! Digite um numero.");
                opcao = -1;
                continue;
            }

            switch (opcao) {
                case 1: 
                    menuPacientes();
                    break;

                case 2: 
                    menuProfissionais(); 
                    break;

                case 3: 
                    menuConsultas(); 
                    break;
                    
                case 4:
                    new jornadas.RegistroAtendimento(repositorio).executar();
                    break;
                    
                case 5:
                    new jornadas.ProcessamentoPagamentos(repositorio).executar();
                    break;
                    
                case 6:
                    new relatorios.GerarRelatorios(repositorio).executar();
                    break;

                case 0: 
                    break;

                default: 
                    System.out.println("Opcao invalida!");
                    break;
            }
        }
        System.out.println("Sistema encerrado.");
    }
    
    private void menuPacientes() {
        int op = -1;

        while (op != 0) {
            System.out.println("\\nPACIENTES");
            System.out.println("1 - Cadastrar Simplificado");
            System.out.println("2 - Cadastrar Completo");
            System.out.println("3 - Complementar Cadastro");
            System.out.println("4 - Buscar Instantanea");
            System.out.println("5 - Desativar");
            System.out.println("6 - Reativar");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                op = Integer.parseInt(sc.nextLine());
            } 
            catch (NumberFormatException e) {
                System.out.println("Opcao invalida!");
                continue;
            }

            switch (op) {
                case 1: 
                    new CadastroPacienteSimplificado(repositorio).executar(); 
                    break;
                case 2: 
                    new CadastroPacienteCompleto(repositorio).executar(); 
                    break;
                case 3: 
                    new CompletacaoCadastro(repositorio).executar(); break;
                case 4: 
                    new BuscaInstantaneaPaciente(repositorio).executar(); 
                    break;
                case 5: 
                    new DesativacaoPaciente(repositorio).executar(); 
                    break;
                case 6: 
                    new AtivacaoPaciente(repositorio).executar(); 
                    break;

                case 0: 
                    break;

                default: 
                    System.out.println("Opcao invalida!"); 
                    break;
            }
        }
    }
    
    private void menuProfissionais() {
        int op = -1;

        while (op != 0) {
            System.out.println("\\nPROFISSIONAIS");
            System.out.println("1 - Cadastrar Geral");
            System.out.println("2 - Cadastrar Fisioterapeuta");
            System.out.println("3 - Cadastrar Psicologo");
            System.out.println("4 - Atualizar Cadastro");
            System.out.println("5 - Desativar");
            System.out.println("6 - Reativar");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            try { 
                op = Integer.parseInt(sc.nextLine()); 
            } 
            catch (NumberFormatException e) { 
                continue; 
            }

            switch (op) {
                case 1: 
                    new CadastroAtualizacaoProfissionais(repositorio).executar(); 
                    break;
                case 2: 
                    new CadastroFisioterapeuta(repositorio).executar(); 
                    break;
                case 3: 
                    new CadastroPsicologo(repositorio).executar(); 
                    break;
                case 4: 
                    new CadastroAtualizacaoProfissionais(repositorio).executar(); 
                    break;
                case 5: 
                    new DesativacaoProfissional(repositorio).executar(); 
                    break;
                case 6: 
                    new AtivacaoProfissional(repositorio).executar(); 
                    break;
                case 0: 
                    break;
                default: 
                    System.out.println("Opcao invalida!"); 
                    break;
            }
        }
    }
    
    private void menuConsultas() {
        int op = -1;

        while (op != 0) {
            System.out.println("\\nCONSULTAS");
            System.out.println("1 - Agendar por Profissional");
            System.out.println("2 - Agendar por Especialidade");
            System.out.println("3 - Cancelar");
            System.out.println("4 - Remarcar");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            try { 
                op = Integer.parseInt(sc.nextLine()); 
            } 
            catch (NumberFormatException e) { 
                continue; 
            }

            switch (op) {
                case 1: 
                    new AgendamentoConsultaProfissional(repositorio).executar(); 
                    break;
                case 2: 
                    new AgendamentoEspecialista(repositorio).executar(); 
                    break;
                case 3: 
                    new CancelamentoConsulta(repositorio).executar(); 
                    break;
                case 4: 
                    new RemarcacaoConsulta(repositorio).executar(); 
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
