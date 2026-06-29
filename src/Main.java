import ui.MenuPrincipal;
import repositorio.ClinicaRepositorio;

public class Main {
    public static void main(String[] args) {
        try {
            ClinicaRepositorio repositorio = new ClinicaRepositorio();
            MenuPrincipal menu = new MenuPrincipal(repositorio);
            menu.iniciar();
        } catch (Exception e) {
            System.err.println("Erro fatal não tratado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}