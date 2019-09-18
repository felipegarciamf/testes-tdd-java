package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import org.junit.Test;
import org.junit.internal.builders.AnnotatedBuilder;

public class LeilaoTest {

	@Test
	public void deveReceberUmLance() {
		
		Leilao leilao = new Leilao("Mackbook");
		assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000));
		assertEquals(1, leilao.getLances().size());
		
		assertEquals(2000.0, leilao.getLances().get(0).getValor(),0.0001);
		
	}
	
	@Test
	public void deveReceberVariosLances() {
		Leilao leilao = new Leilao("MackBook");
		leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000.0));
		leilao.propoe(new Lance(new Usuario("Steve Wozniak"), 3000.0));
		
		assertEquals(2, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(3000.0, leilao.getLances().get(1).getValor(), 0.00001);
	}
	
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
	
		Leilao leilao = new Leilao("Mackbook");
		Usuario steveJobs = new Usuario("Steve Jobs");
		leilao.propoe(new Lance(steveJobs, 2000.0));
		leilao.propoe(new Lance(steveJobs, 3000.0));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test 
	public void naoDeveAceitarMaisQue5LancesDeUmMesmoUsuario() {
		Leilao leilao = new Leilao("Mackbook");
		Usuario steveJobs = new Usuario("SteveJobs");
		Usuario billgates = new Usuario("billgates");

		leilao.propoe(new Lance(steveJobs, 2000));
		leilao.propoe(new Lance(billgates, 3000));
		leilao.propoe(new Lance(steveJobs, 4000));
		leilao.propoe(new Lance(billgates, 5000));
		leilao.propoe(new Lance(steveJobs, 6000));
		leilao.propoe(new Lance(billgates, 7000));
		leilao.propoe(new Lance(steveJobs, 8000));
		leilao.propoe(new Lance(billgates, 9000));
		leilao.propoe(new Lance(steveJobs, 10000));
		leilao.propoe(new Lance(billgates, 31000));
		
		// deve ser ignorado
		leilao.propoe(new Lance(steveJobs, 8000));
		
		assertEquals(10, leilao.getLances().size());
		assertEquals(31000, leilao.getLances().get(leilao.getLances().size()-1).getValor(),0.00001);
	}
	
	@Test
	public void deveDobrarOUltimoLanceDado() {
		Leilao leilao = new Leilao("Mackbook");
		Usuario stevejobs = new Usuario("SteveJobs");
		Usuario billgates = new Usuario("Bill Gates");
		
		leilao.propoe(new Lance(stevejobs, 2000));
		leilao.propoe(new Lance(billgates, 3000));
		
		leilao.dobraLance(stevejobs);
		
		assertEquals(4000, leilao.getLances().get(2).getValor(),0.0001);
	}
	
	@Test
	public void naoDeveDobrarCasoNaoHajaLanceAnterior() {
		Leilao leilao = new Leilao("Mackbook");
		Usuario steveJobs = new Usuario("SteveJobs");
		
		leilao.dobraLance(steveJobs);
		assertEquals(0, leilao.getLances().size());
	}
	
	
	@Test 
	public void deveRetornarAnoBissexto() {
		AnoBissexto anoBissexto = new AnoBissexto();
		
		assertEquals(true, anoBissexto.getAnoBissexto(2016));
		assertEquals(true, anoBissexto.getAnoBissexto(2012));
	
	}
	
}
