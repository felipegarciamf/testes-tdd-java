package br.com.caelum.leilao.teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeNoException;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;
import junit.framework.Assert;

public class AvaliadorTest {
	
	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario jose;
	private Usuario maria;
	
	
	@BeforeClass
	public static void testandoBeforeClass() {
	  System.out.println("before class");
	}
	
	@Before
	public void setUp() {
		this.leiloeiro = new Avaliador();
		System.out.println("cria avaliador");
		
		 this.joao = new Usuario("Joao");
		 this.jose = new Usuario("jose");
		 this.maria = new Usuario("maria");
	}
	
	
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		
		
		Leilao leilao = new Leilao("playstation3");
		
		leilao.propoe(new Lance(joao, 1000));
		leilao.propoe(new Lance(jose, 2000));
		leilao.propoe(new Lance(maria, 3000));
		
		
		// parte 2: acao
	
		leiloeiro.avalia(leilao);

		
		// parte 3: validacao
		double maiorEsperado = 3000.0;
		double menorEsperado = 1000.0;
		double mediaEsperada = 2000.0;
		
		assertEquals(mediaEsperada, leiloeiro.getMedia(),0.0001);
		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(),0.0001);
		assertEquals(menorEsperado, leiloeiro.getMenorLance(),0.0001);
	}
	
	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		
		Leilao leilao = new Leilao("CAsa");
		
		leilao.propoe(new Lance(joao, 1000.0));
		
		leiloeiro.avalia(leilao);
		
		assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000.0, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveEncontrarOsTresMaioresLances() {
		
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao, 100.0)
				.lance(maria,200.0)
				.lance(joao, 300.0)
				.lance(maria, 400.0)
				.constroi();
		
		leiloeiro.avalia(leilao);
		
		List<Lance> tresMaiores = leiloeiro.getTresMaiores();
		assertEquals(3, tresMaiores.size());
		assertEquals(100.0, tresMaiores.get(0).getValor(), 0.0001);
		assertEquals(200.0, tresMaiores.get(1).getValor(), 0.0001);
		assertEquals(300.0, tresMaiores.get(2).getValor(), 0.0001);	
	}
	
    @Test
    public void deveEntenderLeilaoComLancesEmOrdemRandomica() {
       
        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao,200.0));
        leilao.propoe(new Lance(maria,450.0));
        leilao.propoe(new Lance(joao,120.0));
        leilao.propoe(new Lance(maria,700.0));
        leilao.propoe(new Lance(joao,630.0));
        leilao.propoe(new Lance(maria,230.0));

     
        leiloeiro.avalia(leilao);

        assertEquals(700.0, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(120.0, leiloeiro.getMenorLance(), 0.0001);
    }
    
    
    @Test
    public void deveEntenderLeilaoComLancesEmOrdemDecrescente() {
     
        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao,400.0));
        leilao.propoe(new Lance(maria,300.0));
        leilao.propoe(new Lance(joao,200.0));
        leilao.propoe(new Lance(maria,100.0));

      
        leiloeiro.avalia(leilao);

        assertEquals(400.0, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(100.0, leiloeiro.getMenorLance(), 0.0001);
    }
    
    

    @Test
    public void deveDevolverTodosLancesCasoNaoHajaNoMinimo3() {
     
        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao, 100.0));
        leilao.propoe(new Lance(maria, 200.0));

      
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(2, maiores.size());
        assertEquals(100, maiores.get(0).getValor(), 0.00001);
        assertEquals(200, maiores.get(1).getValor(), 0.00001);
    }

    @Test
    public void deveDevolverListaVaziaCasoNaoHajaLances() {
        Leilao leilao = new Leilao("Playstation 3 Novo");

      
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(0, maiores.size());
    }
    
    
    @Test(expected=RuntimeException.class)
    public void naoDeveAvaliarLeiloesSemNenhumLanceDado() {
    	Leilao leilao = new CriadorDeLeilao().para("Playstation 3").constroi();
    	leiloeiro.avalia(leilao);
    }
    
    
	@After
	public void finaliza() {
		System.out.println("fim");
	}
	
	@AfterClass
	public static void testandoAfterClass() {
	  System.out.println("after class");
	}

}
