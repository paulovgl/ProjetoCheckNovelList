package br.com.pcnl.dao;

import br.com.pcnl.jbdc.ConnectionFactory;
import br.com.pcnl.model.Clientes;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro " + e);

        }
    }
    
    public List<Clientes> listarClientes(){
        try {
            List<Clientes> lista = new ArrayList<>();
            
            String sql = "SELECT * FROM tb_clientes_cnl";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()){
                Clientes cli = new Clientes();
                
                cli.setNome(rs.getString("nome"));
                cli.setCpf(rs.getString("cpf"));
                cli.setEmail(rs.getString("email"));
                cli.setCelular(rs.getString("celular"));
                cli.setCidade(rs.getString("cidade"));
                cli.setEstado(rs.getString("estado"));
                
                lista.add(cli);
            }
            return lista;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
            return null;
        }
    }
}
