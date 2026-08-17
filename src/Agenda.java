import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Agenda {
    // R10 - ArrayList escolhido porque a ordem cronológica de marcação das consultas importa
    // e precisamos iterar sequencialmente para gerar relatórios e buscar conflitos de horários.
    private List<Consulta> listaConsultas = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    // Método de validação de horário exigido.
    public static boolean validarFormatoHora(String horaStr) {
        if (horaStr == null || horaStr.length() != 5) {
            return false;
        }
        if (horaStr.charAt(2) != ':') {
            return false;
        }
        for (int i = 0; i < horaStr.length(); i++) {
            if (i == 2) continue;
            if (!Character.isDigit(horaStr.charAt(i))) {
                return false;
            }
        }
        int hora = Integer.parseInt(horaStr.substring(0, 2));
        if (hora < 0 || hora > 23) {
            return false;
        }
        int minutos = Integer.parseInt(horaStr.substring(3, 5));
        if (minutos < 0 || minutos > 59) {
            return false;
        }
        return true;
    }

    // Verifica se o profissional já tem uma consulta agendada no mesmo dia e horário.
    public boolean existeConflito(String profissional, String data, String horario) {
        for (Consulta c : listaConsultas) {
            if (c.nomeProfissional.equalsIgnoreCase(profissional)
                    && c.data.equals(data)
                    && c.horario.equals(horario)
                    && c.status.equalsIgnoreCase("agendada")) {
                return true;
            }
        }
        return false;
    }

    // Método Centralizado de Agendamento.
    public void agendarConsulta(String cpfPaciente, String nomeProfissional, String data, String horario, String tipo)
            throws HorarioIndisponivelException, OperacaoInvalidaException {

        if (!validarFormatoHora(horario)) {
            throw new OperacaoInvalidaException("Erro de agendamento: O horário '" + horario + "' é inválido. Use o padrão HH:mm (Ex: 14:30).");
        }

        if (existeConflito(nomeProfissional, data, horario)) {
            throw new HorarioIndisponivelException("Erro: O profissional " + nomeProfissional + " já está ocupado em " + data + " às " + horario + ".");
        }

        Consulta novaConsulta = new Consulta(cpfPaciente, nomeProfissional, data, horario, tipo);
        this.listaConsultas.add(novaConsulta);
        System.out.println("Sucesso: Consulta agendada com êxito!");
    }

    // Método Centralizado de Cancelamento.
    public void cancelarConsulta(String cpfPaciente, String data, String horario, String motivo)
            throws OperacaoInvalidaException {

        boolean encontrada = false;
        for (Consulta c : listaConsultas) {
            if (c.cpfPaciente.equals(cpfPaciente) && c.data.equals(data) && c.horario.equals(horario)) {
                c.cancelar(motivo);
                encontrada = true;
                System.out.println("Sucesso: " + c.exibirResumo());
                break;
            }
        }

        if (!encontrada) {
            throw new OperacaoInvalidaException("Erro: Nenhuma consulta ativa encontrada para os dados informados.");
        }
    }

    //Método Centralizado de Remarcação.
    public void remarcarConsulta(String cpfPaciente, String novaData, String novoHorario)
        throws OperacaoInvalidaException {

        boolean encontrada = false;
        for (Consulta c : listaConsultas) {
            if (c.cpfPaciente.equals(cpfPaciente) && c.data.equals(novaData) && c.horario.equals(novoHorario)) {
                c.data = novaData;
                c.horario = novoHorario;    
                c.remarcar();
                encontrada = true;
                System.out.println("Sucesso: " + c.exibirResumo());
                break;
            }
        }
        if (!encontrada) {
            throw new OperacaoInvalidaException("Erro: Nenhuma consulta ativa encontrada para os dados informados.");
        }        
    }

    //Método para buscar uma consulta pelo cpf do Paciente
    public void buscaPorCpf(String cpf)
        throws OperacaoInvalidaException {
        
        boolean encontrada = false;
        for (int i = 0; i < listaConsultas.size(); i++) {
            if(listaConsultas.get(i).cpfPaciente.equals(cpf)){
                System.out.println("Índice: " +i+ "\n" + listaConsultas.get(i).exibirResumo());
                encontrada = true;
            }
        }
        if (!encontrada) {
            throw new OperacaoInvalidaException("Erro: Nenhuma consulta ativa encontrada para os dados informados.");
        }
    }

    /**
     * MÉTODO DE RESUMO COMPLETO.
     */
    public void exibirResumoConsultas() {
        System.out.println("\n=== RELATÓRIO GERAL DE CONSULTAS ===");
        if (listaConsultas.isEmpty()) {
            System.out.println("Nenhuma consulta registrada no sistema até o momento.");
        } else {
            for (Consulta c : listaConsultas) {
                System.out.println(c.exibirResumo());
            }
        }
        System.out.println("=====================================\n");
    }

    public List<Consulta> getListaConsultas() {
        return this.listaConsultas;
    }
}
