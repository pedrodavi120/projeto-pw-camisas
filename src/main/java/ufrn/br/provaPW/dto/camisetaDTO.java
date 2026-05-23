package ufrn.br.provaPW.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

@Data
public class camisetaDTO {

    private Long id;

    @NotBlank(message = "O código é obrigatório")
    @Pattern(regexp = "^[A-Z]{3}-\\d{4}$", message = "Formato inválido. Deve corresponder a AAA-9999 (Ex: TSX-4001).")
    private String codigoSku;
    
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "A marca é obrigatória")
    private String marca;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @NotNull(message = "O preço é obrigatório")
    private BigDecimal preco;

    @NotBlank(message = "O tamanho é obrigatório")
    @Pattern(regexp = "P|M|G|GG", message = "O tamanho deve ser P, M, G ou GG")
    private String tamanho;

    private String imgUrl;

    // Getters e setters manuais para garantir compilação caso o Lombok falhe
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCodigoSku() { return codigoSku; }
    public void setCodigoSku(String codigoSku) { this.codigoSku = codigoSku; }
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
    public String getImgUrl() { return imgUrl; }
    public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl; }
}
