package model;

public class Consulta implements Agendavel, Exportavel {

    private static final String TIPO_PADRAO = "inicial";
    private static final String STATUS_PADRAO = "agendada";

    private String cpfPaciente;
    private String nomeProfissional;
    private String data;
    private String horario;
    private String tipo;
    private String status;

    public Consulta(String cpfPaciente, String nomeProfissional, String data, String horario, String tipo, String status) {
        this.cpfPaciente = cpfPaciente;
        this.nomeProfissional = nomeProfissional;
        this.data = data;
        this.horario = horario;
        this.tipo = tipo;
        this.status = status;
    }

    public Consulta(String cpfPaciente, String nomeProfissional, String data, String horario, String tipo) {
        this(cpfPaciente, nomeProfissional, data, horario, tipo, STATUS_PADRAO);
    }

    public Consulta(String cpfPaciente, String nomeProfissional, String data, String horario) {
        this(cpfPaciente, nomeProfissional, data, horario, TIPO_PADRAO, STATUS_PADRAO);
    }

    public String getCpfPaciente() { 
        return cpfPaciente; 
    }
    public String getNomeProfissional() { 
        return nomeProfissional; 
    }
    public String getData() { 
        return data; 
    }
    public String getHorario() { 
        return horario; 
    }
    public String getTipo() { 
        return tipo; 
    }
    public String getStatus() { 
        return status; 
    }

    public void setCpfPaciente(String cpfPaciente) { 
        this.cpfPaciente = cpfPaciente; 
    }
    public void setNomeProfissional(String nomeProfissional) { 
        this.nomeProfissional = nomeProfissional; 
    }
    public void setData(String data) { 
        this.data = data; 
    }
    public void setHorario(String horario) { 
        this.horario = horario; 
    }
    public void setTipo(String tipo) { 
        this.tipo = tipo; 
    }
    public void setStatus(String status) { 
        this.status = status; 
    }

    @Override
    public void agendar() {
        this.status = STATUS_PADRAO;
    }

    @Override
    public void cancelar() {
        this.status = "cancelada";
    }

    public String cancelar(String motivo) {
        this.status = "cancelada";
        return "Consulta cancelada. Motivo: " + motivo;
    }

    @Override
    public void remarcar() {
        this.status = "remarcada";
    }

    public void realizar() {
        this.status = "realizada";
    }

    @Override
    public String exportarDados() {
        return "[CONSULTA] " + formatarDados();
    }

    public String exibirResumo() {
        return "Paciente(CPF): " + cpfPaciente 
                + " | Prof: " + nomeProfissional
                + " | Data: " + data 
                + " | Hora: " + horario
                + " | Tipo: " + tipo 
                + " | Status: " + status;
    }

    private String formatarDados() {
        return "CPF: " + cpfPaciente 
                + " | Prof: " + nomeProfissional
                + " | Data: " + data 
                + " | Hora: " + horario
                + " | Tipo: " + tipo 
                + " | Status: " + status;
    }
}