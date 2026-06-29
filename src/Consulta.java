public class Consulta implements Agendavel, Exportavel {
    public String cpfPaciente;
    public String nomeProfissional;
    public String data;
    public String horario;
    public String tipo;
    public String status;
    
    // sem tipo - assume inicial
    public Consulta(String cpfPaciente, String nomeProfissional, String data, String horario) {
        this.cpfPaciente = cpfPaciente;
        this.nomeProfissional = nomeProfissional;
        this.data = data;
        this.horario = horario;
        this.tipo = "inicial";
        this.status = "agendada";
    }

    public Consulta(String cpfPaciente, String nomeProfissional, String data, String horario, String tipo) {
        this.cpfPaciente = cpfPaciente;
        this.nomeProfissional = nomeProfissional;
        this.data = data;
        this.horario = horario;
        this.tipo = tipo;
        this.status = "agendada";
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

    public void agendar(String data, String horario) {
        this.data = data;
        this.horario = horario;
        this.status = "agendada";
    }

    public void remarcar(String novaData, String novoHorario) {
        this.data = novaData;
        this.horario = novoHorario;
        this.status = "remarcada";
    }

    public void cancelar() {
        this.status = "cancelada";
    }


    // cancelar com motivo - retorna a msg formatada
    public String cancelar(String motivo) {
        this.status = "cancelada";
        return "Consulta cancelada. Motivo: " + motivo;
    }

    public void realizar() {
        this.status = "realizada";
    }


    public String exportarResumo() {
        return "Paciente(CPF): " + cpfPaciente + " | Prof: " + nomeProfissional
                + " | Data: " + data + " | Hora: " + horario
                + " | Tipo: " + tipo + " | Status: " + status;
    }

    // (mantive pra não quebrar seu Main agora)
    public String exibirResumo() {
        return exportarResumo();
    }
}