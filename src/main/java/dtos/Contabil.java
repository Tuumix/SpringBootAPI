package dtos;

import java.util.Calendar;

import enums.Tipo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contabil {
	private Calendar dataLancamento;
	private String descricao;
	private Tipo tipo;
	private double valor;
	private double saldo;
}
