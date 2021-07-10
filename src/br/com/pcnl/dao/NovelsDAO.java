package br.com.pcnl.dao;

import br.com.pcnl.jbdc.ConnectionFactory;
import br.com.pcnl.model.Novels;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

            stmt.setString(1, nov.getNome());
            stmt.setString(2, nov.getAutor());
            stmt.setString(3, nov.getGenero());
            stmt.setString(4, nov.getLink());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Novel cadastrada com sucesso");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro " + e);

        }
    }
}
