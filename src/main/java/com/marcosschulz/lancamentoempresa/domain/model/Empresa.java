package com.marcosschulz.lancamentoempresa.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter
@Getter
@Entity
@Table(name = "tb_empresa")
public class Empresa {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 120)
    @Column(name = "razao_social")
    private String razaoSocial;

    @Size(max = 200)
    private String fantasia;


    @NotBlank
    @CNPJ
    @Size(max = 20)
    @Column(name = "CNPJ")
    private String cnpj;

    @NotBlank
    @Size(max = 20)
    @Column(name = "IE")
    private String ie;

    @Size(max = 255)
    @Email
    private String email;

    @Size(max = 20)
    @Column(name = "telefone")
    private String fone;

    @Size(max = 200)
    private String logradouro;

    @Size(max = 100)
    private String cidade;

    @Size(max = 2)
    @Column(name = "estado")
    private String uf;

    @Size(max = 100)
    private String bairro;
    private Integer numero;

    @NotNull
    private Integer cep;

    @NotNull
    @Column(name = "ativo")
    private Boolean status;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;


}
