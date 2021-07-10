package br.com.pcnl.dao;

import br.com.pcnl.jbdc.ConnectionFactory;
import br.com.pcnl.model.Clientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class ClientesDAO {

    private Connection connection;

    public ClientesDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void cadastrarClientes(Clientes cli) {
        try {

            String sql = "INSERT INTO tb_clientes_cnl(nome,cpf,email,celular,cidade,estado)" + " value(?,?,?,?,?,?)";

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, cli.getNome());
            stmt.setString(2, cli.getCpf());
            stmt.setString(3, cli.getEmail());
            stmt.setString(4, cli.getCelular());
            stmt.setString(5, cli.getCidade());
            stmt.setString(6, cli.getEstado());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro " + e);

        }
    }
}
