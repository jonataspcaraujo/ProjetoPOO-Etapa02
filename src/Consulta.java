public class Consulta implements Agendavel, Exportavel { // Adicionado Exportavel
    public String cpfPaciente;
    public String nomeProfissional;
    public String data;
    public String horario;
    public String tipo;
    public String status;
    private String motivoCancelamento = ""; // Guardar o motivo se for cancelada

    // Construtor 1 (sem tipo - assume inicial)
    public Consulta(String cpfPaciente, String nomeProfissional, String data, String horario) {
        this.cpfPaciente = cpfPaciente;
        this.nomeProfissional = nomeProfissional;
        this.data = data;
        this.horario = horario;
        this.tipo = "inicial";
        this.status = "agendada";
    }

    // Construtor 2
    public Consulta(String cpfPaciente, String nomeProfissional, String data, String horario, String tipo) {
        this.cpfPaciente = cpfPaciente;
        this.nomeProfissional = nomeProfissional;
        this.data = data;
        this.horario = horario;
        this.tipo = tipo;
        this.status = "agendada";
    }

    // Construtor 3 (usado na remarcacao)
    public Consulta(String cpfPaciente, String nomeProfissional, String data,
                    String horario, String tipo, String status) {
        this.cpfPaciente = cpfPaciente;
        this.nomeProfissional = nomeProfissional;
        this.data = data;
        this.horario = horario;
        this.tipo = tipo;
        this.status = status;
    }

    @Override
    public void cancelar() throws OperacaoInvalidaException {
        if (this.status.equalsIgnoreCase("realizada")) {
            throw new OperacaoInvalidaException("Erro: Não é possível cancelar uma consulta já realizada.");
        }
        this.status = "cancelada";
    }

    // cancelar com motivo - Lança exceção se tentar cancelar o que já foi fechado
    public String cancelar(String motivo) throws OperacaoInvalidaException {
        if (this.status.equalsIgnoreCase("realizada")) {
            throw new OperacaoInvalidaException("Erro: Não é possível cancelar uma consulta já realizada.");
        }
        this.status = "cancelada";
        this.motivoCancelamento = motivo;
        return "Consulta cancelada. Motivo: " + motivo;
    }

    @Override
    public void remarcar() throws OperacaoInvalidaException {
        if (this.status.equalsIgnoreCase("realizada") || this.status.equalsIgnoreCase("cancelada")) {
            throw new OperacaoInvalidaException("Erro: Não é possível remarcar uma consulta " + this.status + ".");
        }
        this.status = "remarcada";
    }

    public void realizar() throws OperacaoInvalidaException {
        if (this.status.equalsIgnoreCase("cancelada")) {
            throw new OperacaoInvalidaException("Erro: Não é possível realizar uma consulta que foi cancelada.");
        }
        this.status = "realizada";
    }

    public String getNomeProfissional(){ return this.nomeProfissional; }

    public String exibirResumo() {
        return "Paciente(CPF): " + cpfPaciente + " | Prof: " + nomeProfissional
                + " | Data: " + data + " | Hora: " + horario
                + " | Tipo: " + tipo + " | Status: " + status;
    }

    // ---- IMPLEMENTAÇÃO DA INTERFACE EXPORTAVEL (Exigência do professor) ----
    @Override
    public String exportarDados() {
        return "CONSULTA;" + cpfPaciente + ";" + nomeProfissional + ";" + data + ";" + horario + ";" + tipo + ";" + status;
    }
}