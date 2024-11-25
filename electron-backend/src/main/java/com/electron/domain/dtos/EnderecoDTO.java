package com.electron.domain.dtos;

import com.electron.domain.enums.TipoEndereco;
import com.electron.validation.ValidCep;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {

    private Long id;

    @NotNull(message = "Tipo de endereço é obrigatório")
    private TipoEndereco tipoEndereco;

    @NotBlank(message = "CEP é obrigatório")
    @ValidCep(message = "CEP inválido")
    private String cep;

    @NotBlank(message = "Logradouro é obrigatório")
    @Size(max = 255, message = "Logradouro não pode ter mais que 255 caracteres")
    private String logradouro;

    @NotBlank(message = "Número é obrigatório")
    @Size(max = 10, message = "Número não pode ter mais que 10 caracteres")
    private String numero;

    @NotBlank(message = "Bairro é obrigatório")
    @Size(max = 100, message = "Bairro não pode ter mais que 100 caracteres")
    private String bairro;

    @Size(max = 100, message = "Complemento não pode ter mais que 100 caracteres")
    private String complemento;

    @Pattern(regexp = "\\(\\d{2}\\) \\d{4}-\\d{4}|\\(\\d{2}\\) \\d{5}-\\d{4}",
            message = "Telefone deve estar no formato (XX) XXXX-XXXX ou (XX) XXXXX-XXXX")
    private String telefone;

    @NotNull(message = "Pessoa é obrigatória")
    private Long pessoaId;

    @NotNull(message = "Município é obrigatório")
    private Long municipioId;

}
