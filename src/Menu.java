public class Menu {
    public static String menuLogin(){
        return "0;" +
               "-------- Menu Login --------";
    }

    public static String menuCliente(){
        return "-------- Menu Cliente --------\n" +
               "1) Reserva de Voos\n" +
               "2) Cancelar Reserva\n" +
               "3) Obter Lista de Voos\n" +
               "4) Calcular Viagem\n" +
               "5) Sair";
    }

    public static String menuReservarViagem(){
        return "0;" +
               "------- Reservar Viagem ------";
    }

    public static String menuCancelarViagem(){
        return "0;" +
                "------ Cancelar Viagem ------";
    }

    public static String menuListarVoos(){
        return "0;" +
                "------ Listagem de Voos -----";
    }

    public static String menuCalcularViagem(){
        return "0;" +
                "------ Calcular Viagem ------";
    }

    public static String menuAdmin(){
        return "--------- Menu Admin ---------\n" +
               "1) Inserir Novo Voo\n" +
               "2) Encerrar Dia\n" +
               "3) Sair\n";
    }

    public static String menuInserirVoo(){
        return "0;" +
               "-------- Inserir Voo --------";
    }

    public static String menuEncerrarDia(){
        return "0;" +
               "------- Encerrar Dia -------\n" +
               "1) Sim\n" +
               "2) Nao";
    }
}
