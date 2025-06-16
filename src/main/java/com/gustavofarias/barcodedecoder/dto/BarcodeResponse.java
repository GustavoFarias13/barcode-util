package com.gustavofarias.barcodedecoder.dto;

import lombok.*;

@Data // inclui @Getter, @Setter, @ToString, @EqualsAndHashCode, e @RequiredArgsConstructor
@NoArgsConstructor // construtor vazio
@AllArgsConstructor // construtor com todos os campos
@Builder // padrão builder para facilitar criação do objeto
public class BarcodeResponse {

    private String codigo;
    private boolean valido;
    private String erro;
    private String prefixoPais;
    private String pais;
    private String codigoFabricante;
    private String codigoProduto;
    private String digitoVerificador;

}
