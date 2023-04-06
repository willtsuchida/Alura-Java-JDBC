package br.com.alura.bytebank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection recuperarConexao() {
        try {
            // era Connection connection = DriverManager
            return DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/byte_bank?user=root&password=root");
            //1º param URL - Tipo conexao (jdbc) : tipo banco (mysql) : host (//localhost) : porta (3306 -default sql) / DataBase (byte_bank) + (&) PARAMETROS  user + senha        
            //close(); Como eh retorno vamos fechar na parte do cod que finaliza a operaçao
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
