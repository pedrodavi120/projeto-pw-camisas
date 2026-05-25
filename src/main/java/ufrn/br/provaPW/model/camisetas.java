package ufrn.br.provaPW.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class camisetas {
    // Questão 1: Atributo ID e outros 7 atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String marca;
    private String descricao;
    private BigDecimal preco;
    private String tamanho;

    // Questão 1 e 10: Atributo isDeleted (Long para timestamp)
    private Long isDeleted;
    
    private String imgUrl;
    private String codigoSku;

    // Métodos getters/setters manuais caso haja problemas com Lombok no compilador do usuário
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    public String getTamanho() { return tamanho; }
    public void setTamanho(String tamanho) { this.tamanho = tamanho; }
    public Long getIsDeleted() { return isDeleted; }
    public void setIsDeleted(Long isDeleted) { this.isDeleted = isDeleted; }
    public String getImgUrl() { return imgUrl; }
    public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl; }
    public String getCodigoSku() { return codigoSku; }
    public void setCodigoSku(String codigoSku) { this.codigoSku = codigoSku; }
}
