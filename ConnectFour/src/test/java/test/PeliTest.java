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
            peli.asetaPala(2);
        }
        assertTrue(peli.tarkistaPysty());
    }
}
