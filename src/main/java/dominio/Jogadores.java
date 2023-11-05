package dominio;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Builder
@Value
public class Jogadores {
    Integer id;
    String nome;
    Date DataNascimento;
    String Posicao;
    String Clube;
    String Nacionalidade;

    public Jogadores(Integer id, String nome, Date dataNascimento, String posicao, String clube, String nacionalidade) {
        this.id = id;
        this.nome = nome;
        DataNascimento = dataNascimento;
        Posicao = posicao;
        Clube = clube;
        Nacionalidade = nacionalidade;
    }

    @Override
    public String toString() {
        return "Jogador - ID: " + id + " " +
                "Nome: " + nome + " " +
                "Data de Nascimento: " + DataNascimento + " "+
                "Posição: " + Posicao + " " +
                "Clube: " + Clube + " " +
                "Nacionalidade: " + Nacionalidade + " " + "\n";
    }
}
