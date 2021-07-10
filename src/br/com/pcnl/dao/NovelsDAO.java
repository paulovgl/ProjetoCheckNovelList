package br.com.pcnl.dao;

import br.com.pcnl.jbdc.ConnectionFactory;
import br.com.pcnl.model.Novels;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class NovelsDAO {

    private Connection connection;

    public NovelsDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void cadastrarNovels(Novels nov) {

        try {

            String sql = "INSERT INTO tb_novels_cnl(nome,autor,genero,link)" + " value(?,?,?,?)";

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, nov.getTitulo());
            stmt.setString(2, nov.getAutor());
            stmt.setString(3, nov.getGenero());
            stmt.setString(4, nov.getLink());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Novel cadastrada com sucesso");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro " + e);

        }
    }
    
    public List<Novels> listarNovels(){
        try {
            List<Novels> lista = new ArrayList<>();
            
            String sql = "SELECT * FROM tb_novels_cnl";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()){
                Novels nov = new Novels();
                
                nov.setTitulo(rs.getString("nome"));
                nov.setAutor(rs.getString("autor"));
                nov.setGenero(rs.getString("genero"));
                nov.setLink(rs.getString("link"));
                
                lista.add(nov);
            }
            
            return lista;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
            return null;
        }
    }
}
