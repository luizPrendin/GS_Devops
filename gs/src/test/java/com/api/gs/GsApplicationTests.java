package com.api.gs;

import com.api.gs.model.Aparelho;
import com.api.gs.model.Consumo;
import com.api.gs.model.PrecoEnergia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GsApplicationTests {

	private Aparelho aparelho;
	private Consumo consumo1;
	private Consumo consumo2;
	private PrecoEnergia precoEnergia;

	@BeforeEach
	public void setUp() {
		// Criar um aparelho
		aparelho = new Aparelho();
		aparelho.setNome("Geladeira");
		aparelho.setPotencia(150L);
		aparelho.setTipo("Eletrodoméstico");
		aparelho.setConsumos(new ArrayList<>());

		// Criar dois consumos para o aparelho
		consumo1 = new Consumo();
		consumo1.setData("2024-11-20");
		consumo1.setConsumoKwh(2.5);
		consumo1.setCustoEstimado(1.25);
		consumo1.setAparelho(aparelho);

		consumo2 = new Consumo();
		consumo2.setData("2024-11-21");
		consumo2.setConsumoKwh(3.0);
		consumo2.setCustoEstimado(1.50);
		consumo2.setAparelho(aparelho);

		// Associar os consumos ao aparelho
		aparelho.getConsumos().add(consumo1);
		aparelho.getConsumos().add(consumo2);

		// Criar um preço de energia
		precoEnergia = new PrecoEnergia();
		precoEnergia.setData("2024-11-22");
		precoEnergia.setPrecoKwh(0.5);
	}

	@Test
	public void testAparelhoConsumosAssociation() {
		// Verifica se o aparelho possui 2 consumos
		assertEquals(2, aparelho.getConsumos().size());

		// Verifica se os consumos estão associados corretamente ao aparelho
		assertEquals(aparelho, consumo1.getAparelho());
		assertEquals(aparelho, consumo2.getAparelho());
	}

	@Test
	public void testAparelhoAttributes() {
		// Verifica os atributos do aparelho
		assertEquals("Geladeira", aparelho.getNome());
		assertEquals(150L, aparelho.getPotencia());
		assertEquals("Eletrodoméstico", aparelho.getTipo());
	}

	@Test
	public void testConsumoAttributes() {
		// Verifica os atributos do primeiro consumo
		assertEquals("2024-11-20", consumo1.getData());
		assertEquals(2.5, consumo1.getConsumoKwh());
		assertEquals(1.25, consumo1.getCustoEstimado());

		// Verifica os atributos do segundo consumo
		assertEquals("2024-11-21", consumo2.getData());
		assertEquals(3.0, consumo2.getConsumoKwh());
		assertEquals(1.50, consumo2.getCustoEstimado());
	}

	@Test
	public void testPrecoEnergiaAttributes() {
		// Verifica os atributos do preço de energia
		assertEquals("2024-11-22", precoEnergia.getData());
		assertEquals(0.5, precoEnergia.getPrecoKwh());
	}

}
