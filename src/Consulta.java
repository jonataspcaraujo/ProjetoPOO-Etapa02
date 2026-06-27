public class Consulta implements Agendavel, Exportavel {
    private String cpfPaciente;
    private String nomeProfissional;
    private String data;
    private String horario;
    private String tipo;
    private String status;

    // sem tipo - assume inicial
    public Consulta(String cpfPaciente, String nomeProfissional, String data, String horario) {
        this.cpfPaciente = cpfPaciente;
        this.nomeProfissional = nomeProfissional;
        this.data = data;
        this.horario = horario;
        this.tipo = "inicial";
        this.status = "Agendada";
    }

    public Consulta(String cpfPaciente, String nomeProfissional, String data, String horario, String tipo) {
        this.cpfPaciente = cpfPaciente;
        this.nomeProfissional = nomeProfissional;
        this.data = data;
        this.horario = horario;
        this.tipo = tipo;
        this.status = "Agendada";
    }
// esse aqui a gente usa na remarcacao pra poder setar o status direto
    public Consulta(String cpfPaciente, String nomeProfissional, String data,
                    String horario, String tipo, String status) {
        this.cpfPaciente = cpfPaciente;
        this.nomeProfissional = nomeProfissional;
        this.data = data;
        this.horario = horario;
        this.tipo = tipo;
        this.status = status;
    }

    public String getCpfPaciente() { return cpfPaciente; }
    public String getNomeProfissional() { return nomeProfissional; }
    public String getData() { return data; }
    public String getHorario() { return horario; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    @Override
    public void agendar() {
        // Define ou reafirma o status como agendado
        this.status = "Agendada";
        System.out.println("Consulta para o CPF " + cpfPaciente + " foi agendada com sucesso!");
    }

    @Override
    public void cancelar(){

        if ("Realizada".equals(this.status)) {
            System.out.println("Não é possível cancelar uma consulta que já foi realizada.");
        }
        this.status = "Cancelada";
        System.out.println("Consulta cancelada com sucesso.");
    }

    // cancelar com motivo - retorna a msg formatada
    public String cancelar(String motivo) {
        this.status = "Cancelada";
        return "Consulta cancelada. Motivo: " + motivo;
    }
    // SOBRESCRITA 
    @Override
    public void remarcar() {
        this.status = "Remarcada";
        System.out.println("Consulta remarcada com sucesso.");
    }

    public void realizar() {
        this.status = "Realizada";
    }

    public String exibirResumo() {
        return "Paciente(CPF): " + cpfPaciente + " | Prof: " + nomeProfissional
                + " | Data: " + data + " | Hora: " + horario
                + " | Tipo: " + tipo + " | Status: " + status;
    }
    // SOBRESCRITA
    @Override
    public String exportarDados() {
        return "TIPO:Consulta | CPF_PACIENTE:" + getCpfPaciente() + 
               " | PROFISSIONAL:" + getNomeProfissional() + 
               " | DATA:" + getData() + 
               " | HORARIO:" + getHorario() + 
               " | STATUS:" + getStatus() + 
               " | TIPO_CONSULTA:" + getTipo();
    }
}
