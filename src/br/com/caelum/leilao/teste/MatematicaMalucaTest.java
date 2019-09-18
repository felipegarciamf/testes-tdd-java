package br.com.caelum.leilao.teste;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.matematica.MatematicaMaluca;

public class MatematicaMalucaTest {

	@Test
	public void deveSerMaiorQueTrinta() {
		
		MatematicaMaluca matematica = new MatematicaMaluca();
		assertEquals(40*4, matematica.contaMaluca(40),0.0001);
		
	}
	
	@Test
	public void deveSerMenorQueDez() {
		MatematicaMaluca matematica = new MatematicaMaluca();
		assertEquals(7*2, matematica.contaMaluca(7));
	}
	
	
	@Test
	public void deveEstarEntreDezETrinta() {
		MatematicaMaluca matematica = new MatematicaMaluca();
		assertEquals(11*3, matematica.contaMaluca(11));
	}
}
