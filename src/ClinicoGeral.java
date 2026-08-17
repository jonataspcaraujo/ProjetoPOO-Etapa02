//classe filha de Profissional, especialização de clinico geral

public class ClinicoGeral extends Profissional {

    private String encaminhamento;

    // Inicialização de ClinicoGeral apenas com dados base
    public ClinicoGeral(String nome, String cpf) {
        super(nome, cpf);
        this.encaminhamento = "Sem encaminhamento";
    }

    // Inicialização de ClinicoGeral com dados completos
    public ClinicoGeral(String nome, String cpf, String registro, double valorConsulta, String encaminhamento) {
        super(nome, cpf, registro, valorConsulta);
        this.encaminhamento = encaminhamento;
    }

    // Inicialização de Profissional com dados completos
    public ClinicoGeral(String nome, String cpf, String registro, double valorConsulta) {
        super(nome, cpf);
        this.registro = registro;
        this.valorConsulta = valorConsulta;
    }

    // Acesso dos dados para outra classe
    public String getEncaminhamento() { return encaminhamento; }
    public void setEncaminhamento(String encaminhamento) { this.encaminhamento = encaminhamento; }

    // Gerenciamento de encaminhamentos do profissional
    public void registrarEncaminhamento(String paciente, String especialidadeDestino, String motivo) {
        System.out.println("--- REGISTRO CLÍNICA GERAL ---");
        System.out.println("Paciente: " + paciente);
        System.out.println("Encaminhado para: " + especialidadeDestino);
        System.out.println("Motivo: " + motivo);
    }

    // Método para exibir o resumo específico do profissional
    @Override
    public void exibirResumo() {
        System.out.println("====| Clínico Geral |====");
        exibirDados();
        System.out.println("Especialidade: Clínica Geral");
        System.out.println("Encaminhamento: " + encaminhamento);
        System.out.println("========================");
    }
}