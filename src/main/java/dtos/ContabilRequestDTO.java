package dtos;

import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContabilRequestDTO {
	private int id;
	private Calendar dataInicial;
	private Calendar dataFinal;
}
