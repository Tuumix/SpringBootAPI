package dtos;

import java.util.Calendar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Contabil {
	private Calendar dataLancamento;
	private String descricao;
	private char tipo;
	private double valor;
	private double saldo;
}
