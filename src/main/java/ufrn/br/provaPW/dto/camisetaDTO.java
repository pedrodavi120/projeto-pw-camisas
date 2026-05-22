package ufrn.br.provaPW.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
@Data

public class camisetaDTO {
    
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @NotNull(message = "O preço é obrigatório")
    private BigDecimal preco;

    @NotBlank(message = "O tamanho é obrigatório")
    @Pattern(regexp = "P|M|G|GG", message = "O tamanho deve ser P, M, G ou GG")
    private String tamanho;

    private String imagemUrl;
}
