package repositorio;

import exceptions.*;
import model.*;
import java.util.*;

public class ClinicaRepositorio {
    private Map<String, Paciente> mapaPacientes = new HashMap<>();
    private Map<String, Profissional> mapaProfissionais = new HashMap<>();
    private Set<String> cpfsCadastrados = new HashSet<>();
    private List<Consulta> consultas = new ArrayList<>();
    private List<Atendimento> atendimentos = new ArrayList<>();
    private List<Pagamento> pagamentos = new ArrayList<>();
    private List<Double> multas = new ArrayList<>();
    private List<Pessoa> todasAsPessoas = new ArrayList<>();
    private List<Convenio> convenios = new ArrayList<>();
    private List<HorarioDisponivel> horariosDisponiveis = new ArrayList<>();

    public ClinicaRepositorio() {
        inicializarConvenios();
    }

    private void inicializarConvenios() {
        convenios.add(new Convenio("SaudePlus", 40.0, Arrays.asList("clinica geral", "fisioterapia", "nutricao")));
        convenios.add(new Convenio("VidaMais", 30.0, Arrays.asList("clinica geral", "psicologia")));
        convenios.add(new Convenio("BemEstar", 50.0, Arrays.asList("clinica geral", "fisioterapia", "psicologia", "nutricao")));
    }

    public Map<String, Paciente> getMapaPacientes() { return mapaPacientes; }
    public Map<String, Profissional> getMapaProfissionais() { return mapaProfissionais; }
    public Set<String> getCpfsCadastrados() { return cpfsCadastrados; }
    public List<Consulta> getConsultas() { return consultas; }
    public List<Atendimento> getAtendimentos() { return atendimentos; }
    public List<Pagamento> getPagamentos() { return pagamentos; }
    public List<Double> getMultas() { return multas; }
    public List<Pessoa> getTodasAsPessoas() { return todasAsPessoas; }
    public List<Convenio> getConvenios() { return convenios; }
    public List<HorarioDisponivel> getHorariosDisponiveis() { return horariosDisponiveis; }

    public void adicionarPaciente(Paciente p) throws PacienteNaoEncontradoException {
        if (!cpfsCadastrados.add(p.getCpf())) {
            throw new PacienteNaoEncontradoException("CPF " + p.getCpf() + " ja esta cadastrado no sistema.");
        }
        mapaPacientes.put(p.getCpf(), p);
        todasAsPessoas.add(p);
    }
    
    public Paciente buscarPaciente(String cpf) throws PacienteNaoEncontradoException {
        Paciente p = mapaPacientes.get(cpf);
        if (p == null) throw new PacienteNaoEncontradoException("Paciente com CPF " + cpf + " nao encontrado.");
        return p;
    }
    
    public void adicionarProfissional(Profissional p) {
        mapaProfissionais.put(p.getNome(), p);
        todasAsPessoas.add(p);
    }
    
    public Profissional buscarProfissional(String nome) throws ProfissionalNaoEncontradoException {
        Profissional p = mapaProfissionais.get(nome);
        if (p == null) throw new ProfissionalNaoEncontradoException("Profissional \"" + nome + "\" nao encontrado.");
        return p;
    }

    public Consulta getConsulta(int indice) throws ConsultaNaoEncontradaException {
        if (indice < 0 || indice >= consultas.size()) throw new ConsultaNaoEncontradaException("Consulta de indice " + indice + " nao existe.");
        return consultas.get(indice);
    }
    
    public void adicionarConsulta(Consulta c) { 
        consultas.add(c); 
    }
    public void adicionarAtendimento(Atendimento a) { 
        atendimentos.add(a); 
    }
    public void adicionarPagamento(Pagamento p) { 
        pagamentos.add(p); 
    }
    public void adicionarMulta(double m) { 
        multas.add(m); 
    }
    public HorarioDisponivel cadastrarHorario(String dia, String turno) {
        HorarioDisponivel h = new HorarioDisponivel(dia, turno);
        horariosDisponiveis.add(h);
        return h;
    }
    public Convenio buscarConvenioPorNome(String nome) {
        for (Convenio c : convenios) if (c.getNome().equalsIgnoreCase(nome)) return c;
        return null;
    }
    public Atendimento buscarAtendimentoPorConsulta(int indiceConsulta) {
        for (Atendimento a : atendimentos) if (a.getIndiceConsulta() == indiceConsulta) return a;
        return null;
    }
}
