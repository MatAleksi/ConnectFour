
package test;

import main.Tekoaly;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class TekoalyTest {
    private Tekoaly tekoaly;
     
    @Before
    public void setUp(){
        tekoaly = new Tekoaly(1);
    }
    
    @Test
    public void pisteytys4Samaa(){
        int[] rivi = new int[4];
        for(int i = 0;i<4;i++){
            rivi[i] = 1;
        }
        int pisteet = tekoaly.pisteytys(rivi); 
        assertEquals(1000, pisteet);           
    }
    
}
