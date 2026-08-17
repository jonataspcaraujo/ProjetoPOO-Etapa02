import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

//Classe que reúne a lógica de negócio da clínica
public class ClinicaServico {
    
    private List<Pessoa> todasAsPessoas = new ArrayList<>(); //Lista de todas as pessoas cadastradas no sistema
    private Scanner sc = new Scanner(System.in);

/*=========================| PACIENTE |=========================*/ 
    private HashMap<String, Paciente> mapaPacientes = new HashMap<>(); //Dicionário de pacientes
    private HashSet<String> cpfsCadastrados = new HashSet<>(); //Set para garantir que não existirão cpfs repetidos no sistema

    //Cadastra Pacientes
    public void cadastrarPaciente(String nome, String cpf){

        /*Validando se o cpf já existe nos cadastros
          Se o cpf for novo, adiciona nas coleções*/
        if(cpfsCadastrados.contains(cpf)){
            System.out.println("Erro: Paciente já cadastrado!"); // TODO: Implementar exception
            return;
        }

        cpfsCadastrados.add(cpf); //Set

        //tipo do cadastro
        System.out.print("Tipo (1-Minimo / 2-Com idade e tel / 3-Completo): ");
        int tipo = Integer.parseInt(sc.nextLine());
        Paciente paciente;

        //Estrutura de decisão para executar o tipo correto de cadastro
        switch(tipo){
            case 1: //cadastro mínimo
                paciente = new Paciente(nome, cpf);
                mapaPacientes.put(cpf, paciente);
                todasAsPessoas.add(paciente);
                break; 
            case 2: //cadastro simples + idade e telefone
                System.out.print("Idade: ");
                Integer idade = Integer.parseInt(sc.nextLine());
                System.out.print("Telefone: ");
                String tel = sc.nextLine();
                paciente = new Paciente(nome, cpf, idade, tel);
                mapaPacientes.put(cpf, paciente);
                todasAsPessoas.add(paciente);
                break;
            case 3: //cadastro completo adicionando as informações de convênio
                System.out.print("Idade: ");
                idade = Integer.parseInt(sc.nextLine());
                System.out.print("Telefone: ");
                tel = sc.nextLine();
                System.out.println("--- Informações do convênio ---");
                System.out.println("Nome do convênio: (saúdeplus / vidamais / bemestar)");
                String nomeConv = sc.nextLine();
                Convenio conv = new Convenio(nomeConv);
                paciente = new Paciente(nome, cpf, conv, tel, idade);
                mapaPacientes.put(cpf, paciente);
                todasAsPessoas.add(paciente);
                break;
        }
    }

    //Atualiza o cadastro de Pacientes com idade e telefone
    public void atualizarPaciente(String cpf, int idade, String telefone){
        
        Paciente p = buscarPorCpf(cpf);
        p.setIdade(idade);
        p.setTelefone(telefone);
    }

    //SOBRECARGA: Atualiza o cadastro de Pacientes com idade, telefone e Convenio
    public void atualizarPaciente(String cpf, int idade, String telefone, String nomeConvenio){
        
        Paciente p = buscarPorCpf(cpf);
        p.setIdade(idade);
        p.setTelefone(telefone);
        p.getConvenio().setNomeConvenio(nomeConvenio);
    }

    //Busca um paciente pelo cpf no Dicionário
    public Paciente buscarPorCpf(String cpf){
        
        return mapaPacientes.get(cpf); //TODO: implementar excessão pra caso não encontre
    }

    //Listar todos os Pacientes
    public void listarPacientes(){

        //Percorre a lista de Pessoas e chama exibirResumo() só quando a pessoa for um Paciente
        for(Pessoa p : todasAsPessoas){
            if(p instanceof Paciente){
                p.exibirDados();
            }
        }
    }

    //Desativar o paciente alterando seu status para inativo
    public void desativarPaciente(String cpf){

        //Procura o Paciente no dicionário e altera seu atributo status
        buscarPorCpf(cpf).setStatus(false);
    }
/* ============================================================== */
/*=======================| PROFISSIONAL |========================*/ 
    private HashMap<String, Profissional> mapaProfissionais = new HashMap<>(); //Dicionário de profissionais
    
    //Cadastrar profissionais
    public void cadastrarProfissional(String nome, String especialidade, int tipoCadastro){
        
        Profissional profissional;
        System.out.println("CPF: ");
        String cpf = sc.nextLine();

        //cadastro simples com especialização
        if (tipoCadastro == 1) {
            
            switch(especialidade){
                case "clinica geral":
                    profissional = new ClinicoGeral(nome, cpf);
                    mapaProfissionais.put(nome, profissional);
                    todasAsPessoas.add(profissional);
                    break;

                case "fisioterapia":
                    profissional = new Fisioterapeuta(nome, cpf);
                    mapaProfissionais.put(nome, profissional);
                    todasAsPessoas.add(profissional);
                    break;
                
                case "psicologia":
                    profissional = new Psicologo(nome, cpf);
                    mapaProfissionais.put(nome, profissional);
                    todasAsPessoas.add(profissional);
                    break;
                
                case "nutricao":
                    profissional = new Psicologo(nome, cpf);
                    mapaProfissionais.put(nome, profissional);
                    todasAsPessoas.add(profissional);
                    break;
            }
        
        //cadastro completo com especialização
        } else if (tipoCadastro == 2) {
            System.out.print("Registro: ");
            String reg = sc.nextLine();
            System.out.print("Valor consulta: ");
            double valor = Double.parseDouble(sc.nextLine());
            switch(especialidade){
                case "clinica geral":
                    profissional = new ClinicoGeral(nome, cpf, reg, valor);
                    mapaProfissionais.put(nome, profissional);
                    todasAsPessoas.add(profissional);
                    break;

                case "fisioterapia":
                    profissional = new Fisioterapeuta(nome, cpf, reg, valor);
                    mapaProfissionais.put(nome, profissional);
                    todasAsPessoas.add(profissional);
                    break;
                
                case "psicologia":
                    profissional = new Psicologo(nome, cpf, reg, valor);
                    mapaProfissionais.put(nome, profissional);
                    todasAsPessoas.add(profissional);
                    break;
                
                case "nutricao":
                    profissional = new Psicologo(nome, cpf, reg, valor);
                    mapaProfissionais.put(nome, profissional);
                    todasAsPessoas.add(profissional);
                    break;
            }
        }
    }

    //Atualizar cadastro só com registro e valor de consultas
    public void atualizarProfissional(String nome, String registro, double valorConsulta){

        buscarProfissionalNome(nome).setRegistro(registro);
        buscarProfissionalNome(nome).setValorConsulta(valorConsulta);
    }

    //SOBRECARGA: Atualizar cadastro completamente
    public void atualizarProfissional(String nome, String registro, double valorConsulta, int quantosDias){

        buscarProfissionalNome(nome).setRegistro(registro);
        buscarProfissionalNome(nome).setValorConsulta(valorConsulta);
        //leitura dos dias
        for(int i = 1; i <= quantosDias; i++){
            System.out.print("Dia " +i+ ": ");
            String dia = sc.nextLine();
            HorarioDisponivel dias = new HorarioDisponivel(dia, null);
            buscarProfissionalNome(nome).adicionarHorario(dias);
        }
    }

    //Buscar profissional por nome no Dicionário, retornando um objeto
    public Profissional buscarProfissionalNome(String nome){
        
        //Verifica se a chave existe no mapa
        if (mapaProfissionais == null || !mapaProfissionais.containsKey(nome)) {
            System.out.println("Aviso: Chave não encontrada ou mapa inválido.");
            return null; 
        } //TODO: exception aqui pra validação

        return mapaProfissionais.get(nome);
    }

    //Listar todos os profissionais
    public void listarProfissionais(){

        //Percorre a lista de Pessoas e exibe as informações só quando a pessoa for um Profissional
        for(Pessoa p : todasAsPessoas){
            if(p instanceof Profissional){
                p.exibirDados();
            }
        }
    }

    //SOBRECARGA: Listar filtrando por especialidade.
    public void listarProfissionais(int op){

        switch(op){
            case 1: //Clínico geral
                for(Pessoa p : todasAsPessoas){
                    if(p instanceof ClinicoGeral){
                        p.exibirDados();
                    }
                }
                break;
            case 2: //Fisioterapeuta
                for(Pessoa p : todasAsPessoas){
                    if(p instanceof Fisioterapeuta){
                        p.exibirDados();
                    }
                }
                break;
            case 3: //Nutricionista
                for(Pessoa p : todasAsPessoas){
                    if(p instanceof Nutricionista){
                        p.exibirDados();
                    }
                }
                break;
            case 4: //Psicólogo
                for(Pessoa p : todasAsPessoas){
                    if(p instanceof Psicologo){
                        p.exibirDados();
                    }
                }
                break;
        }
    }

/* ============================================================== */
/*=========================| CONSULTA |=========================*/
    //criação do objeto agenda para realizar algumas operações relacionadas à consultas, bem como armazenar as consultas
    private Agenda agenda = new Agenda();

    //Agendar (escolher profissional)
    public void agendarConsulta(String cpf, String nomeProfissional, String data, String horario, String tipo){
        
        try{
            agenda.agendarConsulta(cpf, nomeProfissional, data, horario, tipo);
        }
        catch (HorarioIndisponivelException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        catch (OperacaoInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    //Cancelar
    public void cancelarConsulta(String cpf, String data, String horario, String motivo){

        try{
            agenda.cancelarConsulta(cpf, data, horario, motivo);
        }
        catch (OperacaoInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    //Remarcar
    public void remarcarConsulta(String cpf, String novaData, String novoHorario){

        try{
            agenda.remarcarConsulta(cpf, novaData, novoHorario);
        }
        catch (OperacaoInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    //Buscar por CPF
    public void buscarConsultasPorCpf(String cpf){
        
        try{
            agenda.buscaPorCpf(cpf);
        }
        catch (OperacaoInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
/* ============================================================== */
/*=========================| ATENDIMENTO |=========================*/
    private List<Consulta> consultas = agenda.getListaConsultas(); //Referência para a lista de consultas
    private List<Atendimento> atendimentos = new ArrayList<>(); //Lista de atendimentos

    //Registrar atendimento
    public void registrarAtendimento(String cpf, int indiceConsulta, String observacao, String diagnostico){
        
        Atendimento atendimento = new Atendimento(indiceConsulta, observacao, diagnostico);
        System.out.println("Deseja adicionar procedimentos? (1-Sim / 2-Não)");
        Integer op = Integer.parseInt(sc.nextLine());

        if(op == 1){
            System.out.println("Quantos procedimentos quer adicionar?");
            Integer resp = Integer.parseInt(sc.nextLine());

            for(int i = 0; i < resp; i++){
                System.out.println("Procedimento: ");
                String procedimento = sc.nextLine();
                atendimento.adicionarProcedimento(procedimento);
            }
        }
        atendimentos.add(atendimento);
        try{consultas.get(indiceConsulta).realizar();}
        catch (OperacaoInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
/* ============================================================== */
/*=========================| PAGAMENTOS |=========================*/
    
    private List<Pagamento> pagamentos = new ArrayList<>();
    
    //Registrar pagamento
    public void registrarPagamento(String cpf){
        
        buscarConsultasPorCpf(cpf); //Exibe as consultas de um paciente

        System.out.println("Selecione o índice da consulta que deseja realizar o pagamento:");
        Integer indice = Integer.parseInt(sc.nextLine());

        //Busca o profissional e o Paciente para acessar suas informações
        Profissional pr = buscarProfissionalNome(consultas.get(indice).nomeProfissional);
        Paciente pc = buscarPorCpf(cpf);

        System.out.println("O valor base da consulta foi: " +pr.getValorConsulta()+ 
            "\nQual a forma de pagamento desejada?\n(1) À vista | (2) Cartão | (3) Convênio");
        Integer op = Integer.parseInt(sc.nextLine());

        switch(op){
            case 1: //À vista (pix ou dinheiro)
                PagamentoPix pagamentoPix = new PagamentoPix(indice, pr.getValorConsulta(), "12345678910");
                pagamentoPix.setValorTotal(pagamentoPix.calcularValorFinal());
                pagamentos.add(pagamentoPix);
                break;
            case 2: //Pagamento com cartão de crédito
                System.out.println("Em quantas parcelas o pagamento será dividido?");
                Integer parcelas = Integer.parseInt(sc.nextLine());
                System.out.println("Qual a bandeira do cartão?");
                String bandeira = sc.nextLine();
                PagamentoCartao pagamentoCartao = new PagamentoCartao(indice, pr.getValorConsulta(), bandeira, parcelas);
                pagamentoCartao.setValorTotal(pagamentoCartao.calcularValorFinal());
                pagamentos.add(pagamentoCartao);
                break;
            case 3: //Pagamento com convênio
                PagamentoConvenio pagamentoConvenio = new PagamentoConvenio(indice, pr.getValorConsulta(), pc.getConvenio());
                pagamentoConvenio.setValorTotal(pagamentoConvenio.calcularValorFinal());
                pagamentos.add(pagamentoConvenio);
                break;
        }
    }
/* ============================================================== */
/*=========================| RELATÓRIOS |=========================*/

    //Listar todas as consultas
    public void listarConsultas(){
        agenda.exibirResumoConsultas();
    }

    //Listar todos os cadastros
    public void listarCadastros(){
        for (Pessoa p : todasAsPessoas){
            p.exibirDados();
        }
    }

    //Listar todos os pagamentos
    public void listarPagamentos(){
        for (Pagamento p : pagamentos){
            System.out.println(p.exibirResumo());
        }
    }

    //Resumo filtrado por profissional
    public void resumoPorProfissional(String nome){

        //Percorre a lista de Pessoas e exibe as informações só quando a pessoa for um Profissional
        for(Consulta c : consultas){
            if(c.getNomeProfissional().equals(nome)){
                c.exibirResumo();
            }
        }
    }
/* ============================================================== */
}
