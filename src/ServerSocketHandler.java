import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class ServerSocketHandler implements Runnable {
    private Socket socket;
    private String user;
    private MapReservas reservaMap;
    private MapUsers userMap;
    private ListaVoos listaVoos;

    public ServerSocketHandler(Socket socket, MapReservas reservaMap, MapUsers usrMap, ListaVoos listaVoos){
        this.socket = socket;
        this.reservaMap = reservaMap;
        this.userMap = usrMap;
        this.listaVoos = listaVoos;
    }

    @Override
    public void run(){
        try {
            DataInputStream inStrm = new DataInputStream(socket.getInputStream());
            DataOutputStream outStrm = new DataOutputStream(socket.getOutputStream());

            autenticar(inStrm, outStrm);

            if (user.equals("admin")){
                menuAdmin(inStrm, outStrm);
            }
            else{
                menuCliente(inStrm, outStrm);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }


    }

    private void autenticar(DataInputStream inStrm, DataOutputStream outStrm) throws IOException {
        String[] input = new String[2];
        boolean notAuth = true;

        outStrm.writeUTF(Menu.menuLogin());
        while(notAuth){
            outStrm.writeUTF("Username:");
            input[0] = inStrm.readUTF();
            outStrm.writeUTF("Password:");
            input[1] = inStrm.readUTF();

            if(userMap.get(input[0]) != null && userMap.get(input[0]).equals(input[1])){
                notAuth = false;
                user = input[0];
                outStrm.writeUTF("0;" + "Autenticação com Sucesso!");
            }
            else {
                outStrm.writeUTF("0;" + "Auntenticação falhou!");
            }
        }
    }

    private void menuCliente(DataInputStream inStrm, DataOutputStream outStrm) throws IOException{
        int opcao = -1;
        while (opcao != 5) {
            System.out.println(reservaMap.toString()+"\n----------------------------------\n");
            outStrm.writeUTF(Menu.menuCliente());
            opcao = Integer.parseInt(inStrm.readUTF());
            switch (opcao){
                case 1: menuReservarViagem(inStrm, outStrm); break;
                case 2: menuCancelarViagem(inStrm, outStrm); break;
                case 3: menuListarVoos(outStrm); break;
                case 4: menuCalcularViagem(inStrm, outStrm); break;
                case 5: outStrm.writeUTF("LogOff"); break;
            }
        }
    }

    private void menuReservarViagem(DataInputStream inStrm, DataOutputStream outStrm) throws IOException{
        String[] input = new String[3];
        boolean communication = true;

        outStrm.writeUTF(Menu.menuReservarViagem());
        while(communication){
            outStrm.writeUTF("Indique Origem-Escalas-Destino");
            input[0] = inStrm.readUTF();
            outStrm.writeUTF("Data Inicial, formato AAAA-MM-DD");
            input[1] = inStrm.readUTF();
            outStrm.writeUTF("Data Final, formato AAAA-MM-DD");
            input[2] = inStrm.readUTF();

            ArrayList<String> voos = new ArrayList<>(Arrays.asList(input[0].split("-")));

            if (listaVoos.verificaVoos(voos) && verificaData(input[1]) && verificaData(input[2])){
                int reserva = reservaMap.fazerReserva(user, voos, LocalDate.parse(input[1]), LocalDate.parse(input[2]));
                if (reserva != -1){
                    outStrm.writeUTF("0;" + "Reserva Sucedida! Código: " + reserva);
                    communication = false;
                }
                else{
                    outStrm.writeUTF("0;" + "Reserva não efetuada, intervalo impossível ou falta de capacidade");
                }
            }
            else {
                outStrm.writeUTF("0;" + "Informação Inválida, voo ou datas incorreto/as");
            }
        }
    }

    public void menuCancelarViagem(DataInputStream inStrm, DataOutputStream outStrm) throws IOException {
        String input;
        boolean communication = true;

        outStrm.writeUTF(Menu.menuCancelarViagem());
        while(communication){
            outStrm.writeUTF("Indique o código da reserva a cancelar:");
            input = inStrm.readUTF();

            if (verificaInt(input)){
                if (reservaMap.cancelarReserva(Integer.parseInt(input), user)) {
                    outStrm.writeUTF("0;" + "Reserva Cancelada!");
                    communication = false;
                }
                else {
                    outStrm.writeUTF("0;" + "Erro. Reserva inexistente!");
                }
            }
            else {
                outStrm.writeUTF("0;" + "Erro. O código tem de ser numeral!");
            }
        }
    }

    public void menuListarVoos(DataOutputStream outStrm) throws IOException {
        outStrm.writeUTF(Menu.menuListarVoos());
        outStrm.writeUTF("1;");
        listaVoos.serialize(outStrm);
    }

    public void menuCalcularViagem(DataInputStream inStrm, DataOutputStream outStrm) throws IOException {
        String input[] = new String[2];
        boolean communication = true;

        outStrm.writeUTF(Menu.menuCalcularViagem());
        while(communication){
            outStrm.writeUTF("Origem:");
            input[0] = inStrm.readUTF();
            outStrm.writeUTF("Destino:");
            input[1] = inStrm.readUTF();

            ArrayList<String> listaVoos = new ArrayList<>(Arrays.asList(input));

            if (true){//reservaMap.calculaViagem(listaVoos)) {
                outStrm.writeUTF("0;" + "Por fazer, listar voos");
                communication = false;
            }
            else {
                outStrm.writeUTF("0;" + "Erro. Não existem voos para o levar ao seu Destino");
            }
        }
    }

    private void menuAdmin(DataInputStream inStrm, DataOutputStream outStrm) throws IOException{
        int opcao = -1;
        while (opcao != 3) {
            System.out.println(reservaMap.toString()+"\n----------------------------------\n");
            outStrm.writeUTF(Menu.menuAdmin());
            opcao = Integer.parseInt(inStrm.readUTF());

            switch (opcao){
                case 1: menuInserirVoo(inStrm, outStrm); break;
                case 2: menuEncerrarDia(inStrm, outStrm); break;
                case 3: outStrm.writeUTF("LogOff"); break;
            }
        }
    }

    private void menuInserirVoo(DataInputStream inStrm, DataOutputStream outStrm) throws IOException {
        String[] input = new String[3];
        boolean communication = true;

        outStrm.writeUTF(Menu.menuInserirVoo());
        while(communication){
            outStrm.writeUTF("Origem:");
            input[0] = inStrm.readUTF();
            outStrm.writeUTF("Destino:");
            input[1] = inStrm.readUTF();
            outStrm.writeUTF("Capacidade:");
            input[2] = inStrm.readUTF();

            if(verificaInt(input[2])){
                if (reservaMap.novoVoo(input[0]+"-"+input[1], Integer.parseInt(input[2]))){
                    listaVoos.add(new Voo(input[0],input[1],Integer.parseInt(input[2])));
                    outStrm.writeUTF("0;" + "Voo Adicionado.");
                    communication = false;
                }
                else{
                    outStrm.writeUTF("0;" + "Erro! Voo repetido.");
                }
            }
            else {
                outStrm.writeUTF("0;" + "Erro! Capacidade tem de ser um número.");
            }
        }
    }

    private void menuEncerrarDia(DataInputStream inStrm, DataOutputStream outStrm) throws IOException {
        String input;
        boolean communication = true;

        outStrm.writeUTF(Menu.menuEncerrarDia());
        while(communication){
            input = inStrm.readUTF();

            if(verificaInt(input) && input.equals("1")){
                reservaMap.encerrarDia();
                outStrm.writeUTF("0;" + "Dia Encerrado!");
                communication = false;
            }
            else {
                if (input.equals("2")) {
                    communication = false;
                }
                else{
                    outStrm.writeUTF("0;" + "Erro! Opcao incorrecta.");
                }
            }
        }
    }

    private boolean verificaData(String data){
        return Pattern.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}", data);
    }

    private boolean verificaInt(String numero){
        return Pattern.matches("[0-9]+", numero);
    }
}
