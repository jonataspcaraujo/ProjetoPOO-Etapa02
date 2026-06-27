public class HorarioDisponivel {
    private String diaSemana; 
    private String turno; 

    public HorarioDisponivel(String diaSemana, String turno) {
        this.diaSemana = diaSemana != null ? diaSemana : "";
        this.turno = turno != null ? turno : "";
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        if (diaSemana == null || diaSemana.isEmpty()) {
            System.out.println("Dia da semana não pode ser vazio.");
            return;
        }
        this.diaSemana = diaSemana;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        if (turno != null && (turno.equals("manha") || turno.equals("tarde"))) {
            this.turno = turno;
        } else {
            System.out.println("Turno inválido. Escolha entre 'manha' ou 'tarde'.");
        }
    }
    // SOBRESCRITA
    @Override
    public String toString() {
        return diaSemana + " (" + turno + ")";
    }
}