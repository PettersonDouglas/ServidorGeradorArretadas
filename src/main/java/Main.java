import ConexaoBD.ConexaoDb;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(3334);//Classe responsável por esperar conexão
            System.out.println("Aguardando conexão");
            Socket cliente; //conexão entre duas máquinas
            ConexaoDb conexao = ConexaoDb.getInstance();// conexão com o db
            while(true){
                cliente = servidor.accept();//escuta a conexão
                System.out.println("Conectado a: "+cliente.getInetAddress().getHostAddress());
                Scanner scanner = new Scanner(cliente.getInputStream());
                String msg = scanner.nextLine();
                if(msg.equals("") || msg.equals("sair")){
                    break;
                }
                conexao.insertCodigo(msg);


            }
            conexao.closeConex();
            servidor.close();
            cliente.close();

        }catch (Exception e){
            System.out.println("erro");
        }
    }
}
