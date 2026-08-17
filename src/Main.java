import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static ClinicaServico cs = new ClinicaServico(); //Objeto para realizar operações de regra de negócio

    public static void main(String[] args) {

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== CLINICA VIDAPLENA ===");
            System.out.println("1 - Pacientes");
            System.out.println("2 - Profissionais");
            System.out.println("3 - Consultas");
            System.out.println("4 - Atendimentos");
            System.out.println("5 - Pagamentos");
            System.out.println("6 - Relatorios");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1: menuPacientes(); break;
                case 2: menuProfissionais(); break;
                case 3: menuConsultas(); break;
                case 4: menuAtendimentos(); break;
                case 5: menuPagamentos(); break;
                case 6: menuRelatorios(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
        System.out.println("Sistema encerrado.");
    }

    // ---- PACIENTES ----

    public static void menuPacientes() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n============| PACIENTES |============");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Complementar cadastro");
            System.out.println("3 - Buscar por CPF");
            System.out.println("4 - Listar todos");
            System.out.println("5 - Desativar");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1: cadastrarPaciente(); break;
                case 2: complementarPaciente(); break;
                case 3: buscarPaciente(); break;
                case 4: listarPacientes(); break;
                case 5: desativarPaciente(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    //Cadastro de pacientes
    public static void cadastrarPaciente() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        cs.cadastrarPaciente(nome, cpf);
        
        System.out.println("Cadastro finalizado");
    }

    //Atualização de pacientes
    public static void complementarPaciente() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Vai informar convenio? (1- Sim / 2- Não): ");
        int tipo = Integer.parseInt(sc.nextLine());

        System.out.print("Idade: ");
        int idade = Integer.parseInt(sc.nextLine());
        System.out.print("Telefone: ");
        String tel = sc.nextLine();

        if (tipo == 1) {
            cs.atualizarPaciente(cpf, idade, tel);
        } else {
            System.out.print("Convenio: ");
            String nomeConvenio = sc.nextLine();
            cs.atualizarPaciente(cpf, idade, tel, nomeConvenio);
        }
        System.out.println("Cadastro atualizado!");
    }

    public static void buscarPaciente() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        cs.buscarPorCpf(cpf).exibirDados();;
    }

    public static void listarPacientes() {
        System.out.println("=====| LISTA DE PACIENTES |=====");
        cs.listarPacientes();
    }

    public static void desativarPaciente() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        cs.desativarPaciente(cpf);
    }

    // ---- PROFISSIONAIS ----

    public static void menuProfissionais() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- PROFISSIONAIS ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Atualizar cadastro");
            System.out.println("3 - Listar todos");
            System.out.println("4 - Filtrar por especialidade");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1: cadastrarProfissional(); break;
                case 2: atualizarProfissional(); break;
                case 3: listarProfissionais(); break;
                case 4: filtrarProfissionais(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    public static void cadastrarProfissional() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Especialidade (clinica geral / fisioterapia / psicologia / nutricao): ");
        String esp = sc.nextLine();

        System.out.print("Tipo (1-Minimo / 2-Completo): ");
        int tipo = Integer.parseInt(sc.nextLine());

        cs.cadastrarProfissional(nome, esp, tipo);
        System.out.println("Profissional cadastrado!");
    }

    public static void atualizarProfissional() {
        System.out.print("Nome do profissional: ");
        String nome = sc.nextLine();

        System.out.print("Vai informar dias de disponibilidade? (1-Sim / 2-Não): ");
        int tipo = Integer.parseInt(sc.nextLine());

        System.out.print("Registro: ");
        String reg = sc.nextLine();
        System.out.print("Valor consulta: ");
        double valor = Double.parseDouble(sc.nextLine());
        Profissional p;

        if (tipo == 1) {
            System.out.print("Quantos dias? ");
            int qtd = Integer.parseInt(sc.nextLine());
            cs.atualizarProfissional(nome, reg, valor, qtd);
        } else {
            cs.atualizarProfissional(nome, reg, valor);
        }
        System.out.println("Profissional atualizado!");
    }

    public static void listarProfissionais() {
        cs.listarProfissionais();
    }

    public static void filtrarProfissionais() {
        System.out.println("Selecione o numero da especialidade: (1-Clinica geral / 2-Fisioterapia / 3-Nutricao / 4-Psicologia");
        Integer esp = Integer.parseInt(sc.nextLine());

        cs.listarProfissionais(esp);
    }

    // ---- CONSULTAS ----

    public static void menuConsultas() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- CONSULTAS ---");
            System.out.println("1 - Agendar (escolher profissional)");
            System.out.println("2 - Cancelar");
            System.out.println("3 - Remarcar");
            System.out.println("4 - Listar todas");
            System.out.println("5 - Buscar por CPF");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1: agendarComProfissional(); break;
                case 2: cancelarConsulta(); break;
                case 3: remarcarConsulta(); break;
                case 4: listarConsultas(); break;
                case 5: buscarConsultasPorPaciente(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    //Agendar escolhendo o profissional
    public static void agendarComProfissional() {
        System.out.print("CPF do paciente: ");
        String cpf = sc.nextLine();

        System.out.print("Nome do profissional: ");
        String nomeProf = sc.nextLine();

        System.out.print("Data (DD/MM/AAAA): ");
        String data = sc.nextLine();
        System.out.print("Horario (HH:MM): ");
        String horario = sc.nextLine();

        System.out.print("Informar tipo? (1-Nao / 2-Sim): ");
        int infoTipo = Integer.parseInt(sc.nextLine());

        if (infoTipo == 1) {
            cs.agendarConsulta(cpf, nomeProf, data, horario, "inicial");
        } else {
            System.out.print("Tipo (inicial/retorno/avaliacao): ");
            String tipo = sc.nextLine();
            cs.agendarConsulta(cpf, nomeProf, data, horario, tipo);
        }
        
        System.out.println("Encerrando agendamento...");
    }

    public static void cancelarConsulta() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Data (DD/MM/AAAA): ");
        String data = sc.nextLine();
        System.out.print("Horario (HH:MM): ");
        String horario = sc.nextLine();

        System.out.println("Deseja informar o motivo? (1-Sim / 2-Não");
        Integer op = Integer.parseInt(sc.nextLine());
        switch(op){
            case 1:
                System.out.print("Motivo: ");
                String motivo = sc.nextLine();
                cs.cancelarConsulta(cpf, data, horario, motivo);
                break;
            case 2:
                cs.cancelarConsulta(cpf, data, horario, "Sem motivo");
        }
        System.out.println("\nCancelamento finalizado");
    }

    public static void remarcarConsulta() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Data original (DD/MM/AAAA): ");
        String dataOrig = sc.nextLine();
        System.out.print("Horario original (HH:MM): ");
        String horarioOrig = sc.nextLine();
        
        System.out.print("Nova data (DD/MM/AAAA): ");
        String novaData = sc.nextLine();
        System.out.print("Novo horario (HH:MM): ");
        String novoHorario = sc.nextLine();

        cs.remarcarConsulta(cpf, novaData, novoHorario);
        System.out.println("\nRemarcação finalizada");
    }

    public static void listarConsultas() {
        cs.listarConsultas();
    }

    public static void buscarConsultasPorPaciente() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        cs.buscarConsultasPorCpf(cpf);
    }

    // ---- ATENDIMENTOS ----

    public static void menuAtendimentos() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- ATENDIMENTOS ---");
            System.out.println("1 - Registrar atendimento");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            op = Integer.parseInt(sc.nextLine());

            if (op == 1) registrarAtendimento();
        }
    }

    public static void registrarAtendimento() {
        
        System.out.println("CPF: ");
        String cpf = sc.nextLine();

        cs.buscarConsultasPorCpf(cpf);

        System.out.print("Indice da consulta: ");
        Integer indice = Integer.parseInt(sc.nextLine());

        System.out.println("Observações: ");
        String obs = sc.nextLine();
        System.out.println("Diagnóstico: ");
        String diag = sc.nextLine();

        cs.registrarAtendimento(cpf, indice, obs, diag);
        System.out.println("Consulta marcada como realizada.");
    }

    // ---- PAGAMENTOS ----

    public static void menuPagamentos() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- PAGAMENTOS ---");
            System.out.println("1 - Registrar pagamento");
            System.out.println("2 - Listar pagamentos");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1: registrarPagamento(); break;
                case 2: listarPagamentos(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    //Registrar pagamento
    public static void registrarPagamento() {
        System.out.println("CPF: ");
        String cpf = sc.nextLine();

        cs.registrarPagamento(cpf);
    }

    public static void listarPagamentos() {
        
        cs.listarPagamentos();
    }

    // ---- RELATORIOS ----

    public static void menuRelatorios() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- RELATORIOS ---");
            System.out.println("1 - Resumo de consultas");
            System.out.println("2 - Por profissional");
            System.out.println("3 - Resumo financeiro");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1:
                    cs.listarConsultas();
                    break;
                case 2:
                    System.out.print("Nome do profissional: ");
                    String nome = sc.nextLine();
                    cs.resumoPorProfissional(nome);
                    break;
                case 3:
                    cs.listarPagamentos();
                    break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }
}
