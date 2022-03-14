package test;

import main.Peli;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class PeliTest {
    private Peli peli;

    @Before
    public void setUp() {
        peli = new Peli();
    }
    
    @Test
    public void peliLuo0Taulukon(){
        boolean tarkistus = true;
        for(int i=0;i<7;i++){
            for(int j=0;j<6;j++){
                if(peli.getLauta()[j][i] != 0){
                    tarkistus = false;
                }
            }
        }
        assertTrue(tarkistus);
    }
    
    @Test
    public void asetaPalaAsettaaPalanOikein(){
        peli.asetaPala(2);
        assertEquals(1, peli.getLauta()[5][2]);
    }
    
    @Test
    public void asetaPalaFalseJosTaysi(){
        for(int i=0;i<6;i++){
            peli.asetaPala(2);
        }
        assertFalse(peli.asetaPala(2));
    }
    
    @Test
    public void tarkistaPystyPalauttaaTrue(){
        for(int i=0;i<4;i++){
            peli.asetaPala(1);
            peli.setVuoro(1);
        }
        assertTrue(peli.tarkistaPysty());
    }

    @Test
    public void tarkistaVaakaVasenToimii(){
        for(int i=0;i<4;i++){
            peli.asetaPala(i);
            peli.setVuoro(1);
        }
        assertTrue(peli.tarkistaVaaka());
    }

    @Test
    public void tarkistaVaakaOikeaToimii(){
        for(int i=6;i>2;i--){
            peli.asetaPala(i);
            peli.setVuoro(1);
        }
        assertTrue(peli.tarkistaVaaka());
    }
    
    @Test
    public void tarkistaUusiPeli(){
        int[][] lauta = new int[6][7];
        lauta[5][5] = 1;
        Peli peli2 = new Peli(lauta, 1, 5);
        assertEquals(1, peli2.getLauta()[5][5]);
    }

    @Test
    public void tarkistaUusiPeliEdellinen(){
        int[][] lauta = new int[6][7];
        lauta[5][5] = 1;
        Peli peli2 = new Peli(lauta, 1, 5);
        assertEquals(5, peli2.getEdellinenSiirto());
    }
    
    @Test 
    public void tarkistaSetEdellinen(){
        peli.setEdellinenSiirto(5);
        assertEquals(5, peli.getEdellinenRivi());
    }
    
    @Test 
    public void setVuoroToimii(){
        peli.setVuoro(2);
        assertEquals(2, peli.getVuoro());
    }
    
    @Test
    public void lautaTaysiPalauttaaTrue(){
        for(int i = 0;i<7;i++){
            for(int j = 0;j<6;j++){
                peli.asetaPala(i);
            }
        }
        assertTrue(peli.lautaTaysi());
    }
    
    @Test
    public void lautaTaysiPalauttaaFalse(){
        peli.asetaPala(3);
        assertTrue(!peli.lautaTaysi());
    }

    @Test
    public void tarkistaEdellisenRivinAsetus(){
        int[][] lauta = new int[6][7];
        lauta[5][5] = 1;
        Peli peli2 = new Peli(lauta, 1, 5);
        peli2.setEdellinenSiirto(5);
        assertEquals(5, peli2.getEdellinenRivi());
    }    
    
    @Test
    public void setLautaToimii(){
        int[][] lauta = new int[6][7];
        for(int i = 0;i<7;i++){
            for(int j = 0;j<6;j++){
                lauta[j][i] = 1;
            }
        }
        peli.setLauta(lauta);
        boolean tarkastus = true;
        for(int i = 0;i<7;i++){
            for(int j = 0;j<6;j++){
                if(peli.getLauta()[j][i] != 1) tarkastus = false;
            }
        }
        assertTrue(tarkastus);
    }
}
