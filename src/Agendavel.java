public interface Agendavel {

    void agendar(String data, String horario);

    void remarcar(String novaData, String novoHorario);

    void cancelar();
}