package repository;

import db.SqlConnection;
import dominio.Jogadores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static db.SqlConnection.createConnection;


public class Jogadoresrepository {
    private Connection connection;

    public Jogadoresrepository() {
        this.connection = createConnection();
    }
    public List<Jogadores> listarJogadores() {
        String sql = "SELECT * FROM jogadores";
        List<Jogadores> jogadores = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Jogadores jogador = new Jogadores(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getDate("DataNascimento"),
                        resultSet.getString("Posicao"),
                        resultSet.getString("Clube"),
                        resultSet.getString("Nacionalidade")
                );
                jogadores.add(jogador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jogadores;
    }

    public void createJogador(Jogadores jogador) {
        String sql = "INSERT INTO jogadores (nome, DataNascimento, Posicao, Clube, Nacionalidade) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, jogador.getNome());
            statement.setDate(2, new java.sql.Date(jogador.getDataNascimento().getTime()));
            statement.setString(3, jogador.getPosicao());
            statement.setString(4, jogador.getClube());
            statement.setString(5, jogador.getNacionalidade());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateJogador(Jogadores jogador) {
        String sql = "UPDATE jogadores SET nome=?, DataNascimento=?, Posicao=?, Clube=?, Nacionalidade=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, jogador.getNome());
            statement.setDate(2, new java.sql.Date(jogador.getDataNascimento().getTime()));
            statement.setString(3, jogador.getPosicao());
            statement.setString(4, jogador.getClube());
            statement.setString(5, jogador.getNacionalidade());
            statement.setInt(6, jogador.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteJogador(int id) {
        String sql = "DELETE FROM jogadores WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Jogadores> pesquisarPorNome(String nome) {
        String sql = "SELECT * FROM jogadores WHERE nome=?";
        List<Jogadores> jogadores = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                jogadores.add(new Jogadores(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getDate("DataNascimento"),
                        resultSet.getString("Posicao"),
                        resultSet.getString("Clube"),
                        resultSet.getString("Nacionalidade")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jogadores;
    }

    public void fecharConexao() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}