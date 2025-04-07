package dev.java.CadastroDeNinjas.Ninjas;

import dev.java.CadastroDeNinjas.Missoes.MissoesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_cadastro")
@Data //cria getter e setter
@NoArgsConstructor
@AllArgsConstructor
public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private int idade;

    //@ManyToOne - um ninja tem uma unica missao.
    //O joinColumn gera uma terceira tabela que une a tabela de ninjas e a tabela de missoes
    @ManyToOne
    @JoinColumn(name = "missoes_id") //Foreing key ou chave estrangeira
    private MissoesModel missoes;


}
