import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {



    private static List<Pessoa> todasAsPessoas = new ArrayList<>();

    // HashMap<String, Paciente>: busca por chave (cpf)
    private static Map<String, Paciente> pacientesMap = new HashMap<>();
    private static Map<String, Profissional> profissionaisMap = new HashMap<>();

    static Convenio saudePlus = new Convenio("SaudePlus", 40.0, new HashSet<String>(Arrays.asList("clinica geral", "psicologia")));
    static Convenio vidaMais = new Convenio("VidaMais", 30.0, new HashSet<String>(Arrays.asList("clinica geral", "nutricao")));
    static Convenio bemEstar = new Convenio("BemEstar", 50.0, new HashSet<String>(Arrays.asList("clinica geral", "fisioterapia")));

    // ArrayList<>: ordem de inserção importa - acesso por índice necessário
    private static List<Consulta> consultas = new ArrayList<>();
    private static List<Atendimento> atendimentos = new ArrayList<>();
    private static List<Pagamento> pagamentos = new ArrayList<>();
    private static List<Double> multas = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

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
            System.out.println("\n--- PACIENTES ---");
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

    public static void cadastrarPaciente() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        // verifica se ja existe
        if (pacientesMap.containsKey(cpf)) {
            System.out.println("CPF ja cadastrado!");
            return;
        }

        System.out.print("Tipo (1-Minimo / 2-Com idade e tel / 3-Completo): ");
        int tipo = Integer.parseInt(sc.nextLine());

        if (tipo == 1) {
            Paciente novoPaciente = new Paciente(nome, cpf);
            pacientesMap.put(novoPaciente.getCpf(), novoPaciente);
            todasAsPessoas.add(novoPaciente);
        } else if (tipo == 2) {
            System.out.print("Idade: ");
            int idade = Integer.parseInt(sc.nextLine());
            System.out.print("Telefone: ");
            String tel = sc.nextLine();
            Paciente novoPaciente = new Paciente(nome, cpf, tel, idade);
            pacientesMap.put(novoPaciente.getCpf(), novoPaciente);
            todasAsPessoas.add(novoPaciente);
        } else {
            System.out.print("Idade: ");
            int idade = Integer.parseInt(sc.nextLine());
            System.out.print("Telefone: ");
            String tel = sc.nextLine();
            System.out.print("Convenio: ");
            String conv = sc.nextLine();
            
            Paciente novoPaciente = new Paciente(nome, cpf, tel, idade, conv.equals("SaudePlus") ? saudePlus : conv.equals("VidaMais") ? vidaMais : conv.equals("BemEstar") ? bemEstar : null);
            pacientesMap.put(novoPaciente.getCpf(), novoPaciente);
            todasAsPessoas.add(novoPaciente);
        }
        
        System.out.println("Paciente cadastrado com sucesso!");
    }

    public static void complementarPaciente() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        int idx = pacientesMap.containsKey(cpf) ? 1 : -1;
        if (idx == -1) {
            System.out.println("Paciente nao encontrado.");
            return;
        }

        System.out.print("Vai informar convenio? (1-Nao / 2-Sim): ");
        int tipo = Integer.parseInt(sc.nextLine());

        System.out.print("Idade: ");
        int idade = Integer.parseInt(sc.nextLine());
        System.out.print("Telefone: ");
        String tel = sc.nextLine();

        if (tipo == 1) {
            pacientesMap.get(cpf).complementar(idade, tel);
        } else {
            System.out.print("Convenio: ");
            String conv = sc.nextLine();
            pacientesMap.get(cpf).complementar(idade, tel, conv.equals("SaudePlus") ? saudePlus : conv.equals("VidaMais") ? vidaMais : conv.equals("BemEstar") ? bemEstar : null);
        }
        System.out.println("Cadastro atualizado!");
    }

    public static void buscarPaciente() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        int idx = pacientesMap.containsKey(cpf) ? 1 : -1;
        if (idx == -1) {
            System.out.println("Paciente nao encontrado.");
        } else {
            System.out.println(pacientesMap.get(cpf).exibirResumo());
        }
    }

    public static void listarPacientes() {
        if (pacientesMap.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
            return;
        }
        for (Paciente paciente : pacientesMap.values()) {
            System.out.println(paciente.exibirResumo());
        }
    }

    public static void desativarPaciente() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        int idx = pacientesMap.containsKey(cpf) ? 1 : -1;
        if (idx == -1) {
            System.out.println("Paciente nao encontrado.");
        } else {
            pacientesMap.get(cpf).desativar();
            System.out.println("Paciente desativado.");
        }
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
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();
        System.out.print("Idade: ");
        int idade = Integer.parseInt(sc.nextLine());
        System.out.print("Especialidade (clinica geral/fisioterapia/psicologia/nutricao): ");
        String esp = sc.nextLine();

        if (!Profissional.especialidadeValida(esp)) {
            System.out.println("Especialidade invalida!");
            return;
        }

        System.out.print("Tipo (1-Minimo / 2-Com registro e valor / 3-Completo): ");
        int tipo = Integer.parseInt(sc.nextLine());

        if (tipo == 1) {
            if (esp.equals("fisioterapia")) {
                System.out.print("Total de sessoes padrao: ");
                int totalSessoes = Integer.parseInt(sc.nextLine());
                Profissional profissionalSelecionado = new Fisioterapeuta(nome, cpf, telefone, idade, totalSessoes);
                profissionaisMap.put(nome, profissionalSelecionado);
                todasAsPessoas.add(profissionalSelecionado);
            }
            else if (esp.equals("psicologia")) {
                System.out.print("Abordagem: ");
                String abordagem = sc.nextLine();
                Profissional profissionalSelecionado = new Psicologo(nome, cpf, telefone, idade, abordagem);
                profissionaisMap.put(nome, profissionalSelecionado);
                todasAsPessoas.add(profissionalSelecionado);
            }
            else if (esp.equals("nutricao")) {
                Profissional profissionalSelecionado = new Nutricionista(nome, cpf, telefone, idade, null);
                profissionaisMap.put(nome, profissionalSelecionado);
                todasAsPessoas.add(profissionalSelecionado);
            }
            else if (esp.equals("clinica geral")) {
                Profissional profissionalSelecionado = new ClinicoGeral(nome, cpf, telefone, idade, null);
                profissionaisMap.put(nome, profissionalSelecionado);
                todasAsPessoas.add(profissionalSelecionado);
            }
            // else if (esp.equals("clinica geral")) { ... }
        } else if (tipo == 2) {
            System.out.print("Registro: ");
            String reg = sc.nextLine();
            System.out.print("Valor consulta: ");
            double valor = Double.parseDouble(sc.nextLine());
        

            if (esp.equals("fisioterapia")) {
                System.out.print("Total de sessoes padrao: ");
                int totalSessoes = Integer.parseInt(sc.nextLine());
                Profissional profissionalSelecionado = new Fisioterapeuta(nome, cpf, telefone, idade, reg, valor, null, totalSessoes);
                profissionaisMap.put(nome, profissionalSelecionado);
                todasAsPessoas.add(profissionalSelecionado);
            }
            else if (esp.equals("psicologia")) {
                System.out.print("Abordagem: ");
                String abordagem = sc.nextLine();
                Profissional profissionalSelecionado = new Psicologo(nome, cpf, telefone, idade, reg, valor, null,  abordagem);
                profissionaisMap.put(nome, profissionalSelecionado);
                todasAsPessoas.add(profissionalSelecionado);
            }
            else if (esp.equals("nutricao")) {
                Profissional profissionalSelecionado = new Nutricionista(nome, cpf, telefone, idade, reg, valor, null, null);
                profissionaisMap.put(nome, profissionalSelecionado);
                todasAsPessoas.add(profissionalSelecionado);
            }
            else if (esp.equals("clinica geral")) {
                Profissional profissionalSelecionado = new ClinicoGeral(nome, cpf, telefone, idade, reg, valor, null, null);
                profissionaisMap.put(nome, profissionalSelecionado);
                todasAsPessoas.add(profissionalSelecionado);
            }
            
        } else {
            System.out.print("Registro: ");
            String reg = sc.nextLine();
            System.out.print("Valor consulta: ");
            double valor = Double.parseDouble(sc.nextLine());
            System.out.print("Quantos horários/turnos atende? ");
            int qtd = Integer.parseInt(sc.nextLine());
            List<HorarioDisponivel> listaHorarios = new java.util.ArrayList<>();

            for (int i = 0; i < qtd; i++) {
                System.out.print("Dia do horário " + (i+1) + " (ex: segunda): ");
                String dia = sc.nextLine();
                System.out.print("Turno do horário " + (i+1) + " (manha/tarde): ");
                String turno = sc.nextLine();
                
                listaHorarios.add(new HorarioDisponivel(dia, turno));
            }
            if (esp.equals("fisioterapia")) {
                System.out.print("Total de sessoes padrao: ");
                int totalSessoes = Integer.parseInt(sc.nextLine());
                Profissional profissionalSelecionado = new Fisioterapeuta(nome, cpf, telefone, idade, reg, valor, listaHorarios, totalSessoes);
                profissionaisMap.put(nome, profissionalSelecionado);
                todasAsPessoas.add(profissionalSelecionado);
            }
            else if (esp.equals("psicologia")) {
                System.out.print("Abordagem: ");
                String abordagem = sc.nextLine();
                Profissional profissionalSelecionado = new Psicologo(nome, cpf, telefone, idade, reg, valor, listaHorarios, abordagem);
                profissionaisMap.put(nome, profissionalSelecionado);
                todasAsPessoas.add(profissionalSelecionado);
            }
            else if (esp.equals("nutricao")) {
                Profissional profissionalSelecionado = new Nutricionista(nome, cpf, telefone, idade, reg, valor, listaHorarios,null);
                profissionaisMap.put(nome, profissionalSelecionado);
                todasAsPessoas.add(profissionalSelecionado);
            }
            else if (esp.equals("clinica geral")) {
                Profissional profissionalSelecionado = new ClinicoGeral(nome, cpf, telefone, idade, reg, valor, listaHorarios,null);
                profissionaisMap.put(nome, profissionalSelecionado);
                todasAsPessoas.add(profissionalSelecionado);
            }
        }
        System.out.println("Profissional cadastrado!");
    }

    public static void atualizarProfissional() {
        System.out.print("Nome do profissional: ");
        String nome = sc.nextLine();
        if (!profissionaisMap.containsKey(nome)) {
            System.out.println("Profissional nao encontrado.");
            return;
        }

        System.out.print("Vai informar dias? (1-Nao / 2-Sim): ");
        int tipo = Integer.parseInt(sc.nextLine());

        System.out.print("Registro: ");
        String reg = sc.nextLine();
        System.out.print("Valor consulta: ");
        double valor = Double.parseDouble(sc.nextLine());

        if (tipo == 1) {
            profissionaisMap.get(nome).atualizar(reg, valor);
        } else {
            System.out.print("Quantos horários/turnos atende? ");
            int qtd = Integer.parseInt(sc.nextLine());
            List<HorarioDisponivel> listaHorarios = new java.util.ArrayList<>();

            for (int i = 0; i < qtd; i++) {
                System.out.print("Dia do horário " + (i+1) + " (ex: segunda): ");
                String dia = sc.nextLine();
                System.out.print("Turno do horário " + (i+1) + " (manha/tarde): ");
                String turno = sc.nextLine();
                
                listaHorarios.add(new HorarioDisponivel(dia, turno));
            }
            profissionaisMap.get(nome).atualizar(reg, valor, listaHorarios);
        }
        System.out.println("Profissional atualizado!");
    }

    public static void listarProfissionais() {
        if (profissionaisMap.isEmpty()) {
            System.out.println("Nenhum profissional cadastrado.");
            return;
        }
        for (Profissional profissional : profissionaisMap.values()) {
            System.out.println(profissional.exibirResumo());
        }
    }

    public static void filtrarProfissionais() {
        System.out.print("Especialidade: ");
        String esp = sc.nextLine();
        boolean achou = false;
        for (Profissional profissional : profissionaisMap.values()) {
            if (profissional.getEspecialidade().equals(esp)) {
                System.out.println(profissional.exibirResumo());
                achou = true;
            }
        }
        if (!achou) System.out.println("Nenhum profissional com essa especialidade.");
    }


    // ---- CONSULTAS ----

    public static void menuConsultas() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- CONSULTAS ---");
            System.out.println("1 - Agendar (escolher profissional)");
            System.out.println("2 - Agendar (busca por especialidade)");
            System.out.println("3 - Cancelar");
            System.out.println("4 - Remarcar");
            System.out.println("5 - Listar todas");
            System.out.println("6 - Buscar por CPF");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1: agendarComProfissional(); break;
                case 2: agendarPorEspecialidade(); break;
                case 3: cancelarConsulta(); break;
                case 4: remarcarConsulta(); break;
                case 5: listarConsultas(); break;
                case 6: buscarConsultasPorPaciente(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    public static void agendarComProfissional() {
        System.out.print("CPF do paciente: ");
        String cpf = sc.nextLine();
        Paciente paciente = pacientesMap.get(cpf);
        if (paciente == null) {
            System.out.println("Paciente nao encontrado.");
            return;
        }
        if (!pacientesMap.get(cpf).isAtivo()) {
            System.out.println("Paciente inativo. Nao e possivel agendar.");
            return;
        }

        System.out.print("Nome do profissional: ");
        String nomeP = sc.nextLine();
        Profissional prof = profissionaisMap.get(nomeP);
        if (prof == null) {
            System.out.println("Profissional nao encontrado.");
            return;
        }
        if (prof.getValorConsulta() == 0) {
            System.out.println("Profissional sem valor definido. Nao pode agendar.");
            return;
        }

        System.out.print("Data (DD/MM/AAAA): ");
        String data = sc.nextLine();
        System.out.print("Horario (HH:MM): ");
        String horario = sc.nextLine();

        // verifica dia da semana
        String diaSemana = descobrirDiaSemana(data);
        if (!prof.atendeNoDia(diaSemana)) {
            System.out.println("Profissional nao atende nesse dia.");
            return;
        }

        // verifica conflito
        if (temConflito(nomeP, data, horario)) {
            System.out.println("Horario ocupado!");
            String sugestao = sugerirHorario(nomeP, data);
            if (sugestao.equals("")) {
                System.out.println("Nenhum horario disponivel nesse dia.");
                return;
            }
            System.out.println("Sugestao: " + sugestao);
            System.out.print("Aceita? (1-Sim / 2-Nao): ");
            int aceita = Integer.parseInt(sc.nextLine());
            if (aceita == 1) {
                horario = sugestao;
            } else {
                return;
            }
        }

        System.out.print("Informar tipo? (1-Nao / 2-Sim): ");
        int infoTipo = Integer.parseInt(sc.nextLine());

        if (infoTipo == 1) {
            Agendavel novaConsulta = new Consulta(cpf, nomeP, data, horario);
            novaConsulta.agendar();
            consultas.add((Consulta) novaConsulta);
        } else {
            System.out.print("Tipo (inicial/retorno/avaliacao): ");
            String tipo = sc.nextLine();
            Agendavel novaConsulta = new Consulta(cpf, nomeP, data, horario, tipo);
            novaConsulta.agendar();
            consultas.add((Consulta) novaConsulta);
        }
        
        System.out.println("Consulta agendada com sucesso!");
    }

    public static void agendarPorEspecialidade() {
        System.out.print("CPF do paciente: ");
        String cpf = sc.nextLine();
        Paciente paciente = pacientesMap.get(cpf);
        if (paciente == null) {
            System.out.println("Paciente nao encontrado.");
            return;
        }
        if (!paciente.isAtivo()) {
            System.out.println("Paciente inativo. Nao e possivel agendar.");
            return;
        }

        System.out.print("Especialidade: ");
        String esp = sc.nextLine();
        System.out.print("Data (DD/MM/AAAA): ");
        String data = sc.nextLine();
        System.out.print("Horario (HH:MM): ");
        String horario = sc.nextLine();

        String diaSemana = descobrirDiaSemana(data);

        // procura profissional disponivel;
        Profissional profissionalEscolhido = null;
        for (Profissional p : profissionaisMap.values()) {
            if (p.getEspecialidade().equals(esp)
                    && p.getValorConsulta() > 0
                    && p.atendeNoDia(diaSemana)
                    && !temConflito(p.getNome(), data, horario)) {
                profissionalEscolhido = p;
                break;
            }
        }

        if (profissionalEscolhido == null) {
            System.out.println("Nenhum profissional disponivel.");
            return;
        }

        Agendavel novaConsulta = new Consulta(cpf, profissionalEscolhido.getNome(), data, horario);
        novaConsulta.agendar();

        consultas.add((Consulta) novaConsulta); 
        
        System.out.println("Consulta agendada com " + profissionalEscolhido.getNome() + "!");
    }

    public static void cancelarConsulta() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Data (DD/MM/AAAA): ");
        String data = sc.nextLine();
        System.out.print("Horario (HH:MM): ");
        String horario = sc.nextLine();

        // localiza a consulta
        int idx = -1;
        for (Consulta c : consultas) {
            if (c.getCpfPaciente().equals(cpf) && c.getData().equals(data)
                    && c.getHorario().equals(horario)) {
                idx = consultas.indexOf(c);
                break;
            }
        }

        if (idx == -1) {
            System.out.println("Consulta nao encontrada.");
            return;
        }
        if (consultas.get(idx).getStatus().equals("Realizada")) {
            System.out.println("Consulta ja realizada. Nao pode cancelar.");
            return;
        }
        if (consultas.get(idx).getStatus().equals("Cancelada")) {
            System.out.println("Consulta ja cancelada.");
            return;
        }

        // calculo da multa
        System.out.print("Horario atual (HH:MM): ");
        String horaAtual = sc.nextLine();

        int hConsulta = Integer.parseInt(horario.substring(0, 2));
        int hAgora = Integer.parseInt(horaAtual.substring(0, 2));
        int diff = hConsulta - hAgora;

        if (diff < 2) {
            System.out.println("Multa de R$50.00 aplicada!");
            multas.add(50.0);
        }

        System.out.print("Informar motivo? (1-Nao / 2-Sim): ");
        int temMotivo = Integer.parseInt(sc.nextLine());

        if (temMotivo == 1) {
            consultas.get(idx).cancelar();
        } else {
            System.out.print("Motivo: ");
            String motivo = sc.nextLine();
            String msg = consultas.get(idx).cancelar(motivo);
            System.out.println(msg);
        }
        System.out.println("Consulta cancelada.");
    }

    public static void remarcarConsulta() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Data original (DD/MM/AAAA): ");
        String dataOrig = sc.nextLine();
        System.out.print("Horario original (HH:MM): ");
        String horarioOrig = sc.nextLine();

        int idx = -1;
        for (Consulta c : consultas) {
            if (c.getCpfPaciente().equals(cpf) && c.getData().equals(dataOrig)
                    && c.getHorario().equals(horarioOrig)
                    && c.getStatus().equals("Agendada")) {
                idx = consultas.indexOf(c);
                break;
            }
        }

        if (idx == -1) {
            System.out.println("Consulta agendada nao encontrada.");
            return;
        }

        System.out.print("Apenas trocar horario no mesmo dia? (1-Sim / 2-Nao): ");
        int tipo = Integer.parseInt(sc.nextLine());

        String novaData;
        String novoHorario;

        if (tipo == 1) {
            novaData = dataOrig;
            System.out.print("Novo horario: ");
            novoHorario = sc.nextLine();
        } else {
            System.out.print("Nova data (DD/MM/AAAA): ");
            novaData = sc.nextLine();
            System.out.print("Novo horario (HH:MM): ");
            novoHorario = sc.nextLine();
        }

        String nomeProf = consultas.get(idx).getNomeProfissional();
        Profissional prof = profissionaisMap.get(nomeProf);
        // se mudou de dia, verifica se prof atende
        if (tipo == 2) {
            String dia = descobrirDiaSemana(novaData);
            if (!prof.atendeNoDia(dia)) {
                System.out.println("Profissional nao atende nesse dia.");
                return;
            }
        }

        if (temConflito(nomeProf, novaData, novoHorario)) {
            System.out.println("Horario ocupado. Nao foi possivel remarcar.");
            return;
        }

        consultas.get(idx).remarcar();
        Agendavel novaConsulta = new Consulta(cpf, nomeProf, novaData, novoHorario, consultas.get(idx).getTipo());
        consultas.add((Consulta) novaConsulta);
    }

    public static void listarConsultas() {
        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta cadastrada.");
            return;
        }
        for (Consulta c : consultas) {
            System.out.println( c.exibirResumo());
        }
    }

    public static void buscarConsultasPorPaciente() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        boolean achou = false;
        for (Consulta c : consultas) {
            if (c.getCpfPaciente().equals(cpf)) {
                System.out.println( c.exibirResumo());
                achou = true;
            }
        }
        if (!achou) System.out.println("Nenhuma consulta encontrada.");
    }

    // verifica se ja tem consulta nesse horario com esse profissional
    public static boolean temConflito(String nomeProf, String data, String horario) {
        for (Consulta c : consultas) {
            if (c.getNomeProfissional().equals(nomeProf) && c.getData().equals(data) 
                && c.getHorario().equals(horario) && "Agendada".equals(c.getStatus())) {
                return true;
            }
        }
        return false;
    }

    // sugere proximo horario livre (de hora em hora, 08h ate 18h)
    public static String sugerirHorario(String nomeProf, String data) {
        for (int h = 8; h <= 18; h++) {
            String teste;
            if (h < 10) {
                teste = "0" + h + ":00";
            } else {
                teste = h + ":00";
            }
            if (!temConflito(nomeProf, data, teste)) {
                return teste;
            }
        }
        return "";
    }

    // descobre dia da semana a partir da data
    // operação opcional
    public static String descobrirDiaSemana(String data) {
        int dia = Integer.parseInt(data.substring(0, 2));
        int mes = Integer.parseInt(data.substring(3, 5));
        int ano = Integer.parseInt(data.substring(6, 10));

        // ajuste pra formula funcionar com janeiro e fevereiro
        if (mes < 3) {
            mes = mes + 12;
            ano = ano - 1;
        }

        int k = ano % 100;
        int j = ano / 100;

        // formula de zeller
        int resultado = (dia + (13 * (mes + 1)) / 5 + k + k/4 + j/4 - 2*j) % 7;
        if (resultado < 0) resultado = resultado + 7;

        // 0=sabado, 1=domingo, 2=segunda...
        String[] nomes = {"sabado", "domingo", "segunda", "terca", "quarta", "quinta", "sexta"};
        //System.out.println("DEBUG dia semana: " + nomes[resultado]); // pra testar
        return nomes[resultado];
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
        System.out.print("CPF da consulta: ");
        String cpf = sc.nextLine();

        int idxConsulta = -1;
        for (int i = 0; i < consultas.size(); i++) {
            if (consultas.get(i).getCpfPaciente().equals(cpf)) {
                idxConsulta = i;
                break;
            }
        }

        if (idxConsulta == -1) {
            System.out.println("Consulta nao encontrada.");
            return;
        }

        Consulta consultaSelecionada = consultas.get(idxConsulta);

        if (!consultaSelecionada.getStatus().equals("Agendada")) {
            System.out.println("So pode registrar atendimento em consulta agendada.");
            return;
        }

        System.out.print("Observacoes: ");
        String obs = sc.nextLine();

        System.out.print("Tipo de registro (1-So obs / 2-Com diagnostico / 3-Completo): ");
        int tipo = Integer.parseInt(sc.nextLine());

        Atendimento atendimento = null;
        if (tipo == 1) {
            atendimento = new Atendimento(consultas.get(idxConsulta), obs);

        } else if (tipo == 2) {
            System.out.print("Diagnostico: ");
            String diag = sc.nextLine();
            atendimento = new Atendimento(consultas.get(idxConsulta), obs, diag);

        } else {
            System.out.print("Diagnostico: ");
            String diag = sc.nextLine();

            System.out.print("Como informar procedimentos? (1-Um por vez / 2-Todos de uma vez): ");
            int forma = Integer.parseInt(sc.nextLine());

            List<String> procs = new ArrayList<>();
            int qtdProcs = 0;

            if (forma == 1) {
                String proc = "";
                while (!proc.equals("fim") && qtdProcs < 10) {
                    System.out.print("Procedimento (ou 'fim'): ");
                    proc = sc.nextLine();
                    if (!proc.equals("fim")) {
                        procs.add(proc);
                        qtdProcs++;
                    }
                }
            } else {
                System.out.print("Quantos? ");
                qtdProcs = Integer.parseInt(sc.nextLine());
                if (qtdProcs > 10) qtdProcs = 10;
                for (int i = 0; i < qtdProcs; i++) {
                    System.out.print("Proc " + (i+1) + ": ");
                    procs.add(sc.nextLine());
                }
            }
            atendimento = new Atendimento(consultas.get(idxConsulta), obs, diag, procs);

        }
        Profissional prof = profissionaisMap.get(consultaSelecionada.getNomeProfissional());
        if (prof != null) {
            // DYNAMIC CASTING
            if (prof instanceof Nutricionista) {
                System.out.print("Digite o Plano Alimentar para o prontuário deste paciente: ");
                String plano = sc.nextLine();
                
                Nutricionista nutri = (Nutricionista) prof;
                nutri.setPlanoAlimentar(plano);
                
            } else if (prof instanceof ClinicoGeral) {
                System.out.print("Digite o Encaminhamento para o prontuário deste paciente: ");
                String encaminhamento = sc.nextLine();
                
                ClinicoGeral clinico = (ClinicoGeral) prof;
                clinico.setEncaminhamento(encaminhamento);
            }
            
            // LIGAÇÃO DINÂMICA
            prof.registrarEspecifico(atendimento);
        }

        // Salva o atendimento na lista dinâmica e conclui o processo
        atendimentos.add(atendimento);
        consultaSelecionada.realizar();
        System.out.println("\n--- RESUMO ---");
        System.out.println(atendimentos.get(atendimentos.size() - 1).exibirResumo());
        System.out.println("Consulta marcada como realizada.");
    }

    // ---- PAGAMENTOS ----

    public static void menuPagamentos() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- PAGAMENTOS ---");
            System.out.println("1 - Pagamento direto");
            System.out.println("2 - Pagamento automatico");
            System.out.println("3 - Listar pagamentos");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1: pagamentoDireto(); break;
                case 2: pagamentoAutomatico(); break;
                case 3: listarPagamentos(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    public static void pagamentoDireto() {
        System.out.print("CPF da consulta: ");
        String cpf = sc.nextLine();

        int idxConsulta = -1;
        for (int i = 0; i < consultas.size(); i++) {
            if (consultas.get(i).getCpfPaciente().equals(cpf)) {
                idxConsulta = i;
                break;
            }
        }

        if (idxConsulta == -1) {
            System.out.println("Consulta nao encontrada.");
            return;
        }
        Consulta consultaSelecionada = consultas.get(idxConsulta);
        System.out.print("Valor: ");
        double valor = Double.parseDouble(sc.nextLine());
        System.out.print("Tipo (dinheiro/cartao/convenio): ");
        String tipoPag = sc.nextLine();

        Pagamento pagamento = null;
        if (tipoPag.equals("cartao")) {
            System.out.print("Parcelas (1 a 3): ");
            int parc = Integer.parseInt(sc.nextLine());
            if (parc < 1) parc = 1;
            if (parc > 3) parc = 3;
            pagamento = new PagamentoCartao(consultaSelecionada, valor, parc);
            pagamentos.add(pagamento);
            if (parc > 1) {
                double vlrParc = Math.round((valor / parc) * 100.0) / 100.0;
                System.out.println("Pagamento em " + parc + "x de R$" + vlrParc);
            }
        } else if (tipoPag.equals("convenio")) {
            System.out.print("Nome do convenio: ");
            String nomeConv = sc.nextLine();
            Convenio convenio = null;
            if (nomeConv.equals("SaudePlus")) {
                convenio = saudePlus;
            } else if (nomeConv.equals("VidaMais")) {
                convenio = vidaMais;
            } else if (nomeConv.equals("BemEstar")) {
                convenio = bemEstar;
            } else {
                System.out.println("Convenio invalido.");
                return;
            }
            pagamento = new PagamentoConvenio(consultaSelecionada, valor, convenio);
            pagamentos.add(pagamento);
        } else if (tipoPag.equals("dinheiro")) {
            pagamento = new PagamentoDinheiro(consultaSelecionada, valor);
            pagamentos.add(pagamento);
        }
        System.out.println("Pagamento registrado!");
    }

    public static void pagamentoAutomatico() {
        System.out.print("CPF da consulta: ");
        String cpf = sc.nextLine();

        int idxConsulta = -1;
        for (int i = 0; i < consultas.size(); i++) {
            if (consultas.get(i).getCpfPaciente().equals(cpf)) {
                idxConsulta = i;
                break;
            }
        }

        if (idxConsulta == -1) {
            System.out.println("Consulta nao encontrada.");
            return;
        }

        Consulta consultaSelecionada = consultas.get(idxConsulta);
        
        // obtem valor do profissional
        String nomeProf = consultaSelecionada.getNomeProfissional();
        double valorBase = profissionaisMap.get(nomeProf).getValorConsulta();

        // verifica convenio e tipo
        String cpfPac = consultaSelecionada.getCpfPaciente();
        Convenio conv = pacientesMap.get(cpfPac).getConvenioNome();
        boolean temConvenio = conv != null;
        boolean ehRetorno = consultaSelecionada.getTipo().equals("retorno");

        double desconto = 0;
        if (ehRetorno) desconto = desconto + 20;
        if (temConvenio) desconto = desconto + 40;

        System.out.print("Tem multa pendente? (1-Nao / 2-Sim): ");
        int temMulta = Integer.parseInt(sc.nextLine());
        double valorMulta = 0;

        double valorFinal;
        if (temMulta == 1 && desconto == 0) {
            valorFinal = PagamentoConvenio.calcularValor(valorBase);
        } else if (temMulta == 1) {
            valorFinal = PagamentoConvenio.calcularValor(valorBase, desconto);
        } else {
            System.out.print("Valor da multa: ");
            valorMulta = Double.parseDouble(sc.nextLine());
            valorFinal = PagamentoConvenio.calcularValor(valorBase, desconto, valorMulta);
        }
        
        // mostra detalhes
        System.out.println("Valor base: R$" + valorBase);
        System.out.println("Desconto: " + desconto + "%");
        if (valorMulta > 0) System.out.println("Multa: R$" + valorMulta);
        double vlrFinalArredondado = Math.round(valorFinal * 100.0) / 100.0;
        System.out.println("Valor final: R$" + vlrFinalArredondado);

        System.out.print("Tipo (dinheiro/cartao/convenio): ");
        String tipoPag = sc.nextLine();
        Pagamento pagamento = null;
        if (tipoPag.equals("cartao")) {
            System.out.print("Parcelas (1 a 3): ");
            int parc = Integer.parseInt(sc.nextLine());
            if (parc < 1) parc = 1;
            if (parc > 3) parc = 3;
            pagamento = new PagamentoCartao(consultaSelecionada, valorFinal, parc);
            pagamentos.add(pagamento);
            double vlrParc = Math.round((valorFinal / parc) * 100.0) / 100.0;
            System.out.println("Pagamento em " + parc + "x de R$" + vlrParc);
        } else if (tipoPag.equals("dinheiro")) {
            pagamento = new PagamentoDinheiro(consultaSelecionada, valorFinal);
            pagamentos.add(pagamento);
        } else if (tipoPag.equals("convenio")){
            pagamento = new PagamentoConvenio(consultaSelecionada, valorBase, conv);
            pagamentos.add(pagamento);
        } else {
            System.out.println("Tipo de pagamento invalido.");
            return;
        }

        System.out.println("Pagamento registrado!");
    }

    public static void listarPagamentos() {
        if (pagamentos.isEmpty()) {
            System.out.println("Nenhum pagamento registrado.");
            return;
        }
        for (Pagamento pagamento : pagamentos) {
            System.out.println(pagamento.exibirResumo());
        }
    }

    // ---- RELATORIOS ----

    public static void menuRelatorios() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- RELATORIOS ---");
            System.out.println("1 - Geral");
            System.out.println("2 - Por profissional");
            System.out.println("3 - Por periodo");
            System.out.println("4 - Resumo financeiro");
            System.out.println("5 - Relatorio unificado de pessoas");
            System.out.println("6 - Exportar Dados Operacionais");
            System.out.println("0 - Voltar");
            System.out.print("Opcao: ");
            op = Integer.parseInt(sc.nextLine());

            

            switch (op) {
                case 1:
                    Relatorio.gerarRelatorio(consultas, consultas.size(), atendimentos, atendimentos.size());
                    break;
                case 2:
                    System.out.print("Nome do profissional: ");
                    String nome = sc.nextLine();
                    Relatorio.gerarRelatorio(consultas, consultas.size(), atendimentos, atendimentos.size(), nome);
                    break;
                case 3:
                    System.out.print("Data inicio (DD/MM/AAAA): ");
                    String ini = sc.nextLine();
                    System.out.print("Data fim (DD/MM/AAAA): ");
                    String fim = sc.nextLine();
                    Relatorio.gerarRelatorio(consultas, consultas.size(), atendimentos, atendimentos.size(), ini, fim);
                    break;
                case 4:
                    Relatorio.gerarResumoFinanceiro(consultas, consultas.size(), pagamentos, pagamentos.size(), multas, multas.size());
                    break;
                case 5:
                    Relatorio.relatorioUnificado(todasAsPessoas);
                    break;
                case 6:
                    executarJornadaExportacao();
                break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }   
        }
        public static void executarJornadaExportacao() {
            System.out.println("=== EXPORTAÇÃO DE DADOS OPERACIONAIS ===");
            
            List<Exportavel> listaExportaveis = new ArrayList<>();
            
            listaExportaveis.addAll(consultas);
            listaExportaveis.addAll(atendimentos);
            listaExportaveis.addAll(pagamentos);
            
            if (listaExportaveis.isEmpty()) {
                System.out.println("Nenhum dado disponível no sistema para exportação.");
                return;
            }
            
            for (Exportavel item : listaExportaveis) {
                // LIGAÇÃO DINÂMICA: o método chamado depende do tipo REAL do objeto
                System.out.println(item.exportarDados());
            }
            System.out.println("====================================================");
        }
}
