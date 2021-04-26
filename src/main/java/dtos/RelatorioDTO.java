package dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RelatorioDTO {
	private int id;
	private String nome;
	private String cpf_cnpj;
	private String telefone;
	private List<Contabil> Contabil;
}