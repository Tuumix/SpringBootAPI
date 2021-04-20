package dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteFiltroDTO {
	private String nome;
	private String cpfCnpj;
	private String cidade;
	private String uf;
}
