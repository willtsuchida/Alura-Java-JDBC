package br.com.alura.bytebank.domain.conta;

import br.com.alura.bytebank.domain.cliente.Cliente;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContaDAO {
    
    private Connection conn;
    
    ContaDAO(Connection connection){
        this.conn = connection; 
    //quem quer usar a DAO é a SERVICE entao quem passa a Connection eh a ContaService
    //conn ta minha classe vai receber a connection do construtor (que a service vai passar)
    }
    
    public void salvar(DadosAberturaConta dadosDaConta){
        //recebo uma conta no parametro,
        var cliente = new Cliente(dadosDaConta.dadosCliente());
        var conta = new Conta(dadosDaConta.numero(), cliente);
        //extrair os dados de cliente e conta dessa conta
        
        String sql = "INSERT INTO conta (numero, saldo, cliente_nome, cliente_cpf, cliente_email)" +
                "VALUES (?, ?, ?, ?, ?)";
                        
        try{
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            
            preparedStatement.setInt(1, conta.getNumero());
            preparedStatement.setBigDecimal(2, BigDecimal.ZERO); // 0.0
            preparedStatement.setString(3, dadosDaConta.dadosCliente().nome());
            preparedStatement.setString(4, dadosDaConta.dadosCliente().cpf());
            preparedStatement.setString(5, dadosDaConta.dadosCliente().email());
            
            preparedStatement.execute();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
