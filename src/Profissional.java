import java.util.ArrayList;
import java.util.List;

public abstract class Profissional extends Pessoa {

    protected String registro;
    protected double valorConsulta;
    protected List<HorarioDisponivel> horariosDisponiveis = new ArrayList<>();

    // Inicialização de Profissional apenas com dados base
    protected Profissional(String nome, String cpf) {
        super(nome, cpf);
    }

    // Inicialização de Profissional com dados completos
    protected Profissional(String nome, String cpf, String registro, double valorConsulta) {
        super(nome, cpf);
        this.registro = registro;
        this.valorConsulta = valorConsulta;
    }

    // Inicialização de Profissional vazio
    protected Profissional() {}

    // Acesso dos dados para outra classe
    public String getRegistro() { return registro; }
    public double getValorConsulta() { return valorConsulta; }
    public List<HorarioDisponivel> getHorariosDisponiveis() { return horariosDisponiveis; }

    public void setRegistro(String registro) { this.registro = registro; }
    public void setValorConsulta(double valorConsulta) { this.valorConsulta = valorConsulta; }

    // Gerenciamento de horários do profissional
    public void adicionarHorario(HorarioDisponivel horario) {
        horariosDisponiveis.add(horario);
    }

    // Método para exibir os dados base do profissional
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Registro: " + this.registro);
        System.out.println("Valor da Consulta: R$ " + this.valorConsulta);
    }

    // Método para exibir o resumo específico do profissional
    public abstract void exibirResumo();
}


