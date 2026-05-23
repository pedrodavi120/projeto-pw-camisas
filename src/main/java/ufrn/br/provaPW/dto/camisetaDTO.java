package ufrn.br.provaPW.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
@Data

public class camisetaDTO {

    private Long id;

    @NotBlank(message = " O codigo é obrigatório")
    private String Codigo;
    
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

    private String imagemUrl;

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    // Lombok aparentemente não gerou alguns métodos em tempo de compilação
    // Adicionando explicitamente getters/setters usados pelo controller
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return this.Codigo;
    }

    public void setCodigo(String codigo) {
        this.Codigo = codigo;
    }
}
