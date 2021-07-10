package br.com.pcnl.jbdc;

import javax.swing.JOptionPane;

public class ConnectionTest {

    public static void main(String[] args) {

        try {

            new ConnectionFactory().getConnection();
            JOptionPane.showMessageDialog(null, "Conectado com sucesso!");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Ops aconteceu o erro: " + erro);
        }

    }
}
