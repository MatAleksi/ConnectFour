package main;

import main.Peli;
import main.Solmu;

public class Tekoaly {
    
    private Peli peli;
    private Peli testausPeli;    

    public Tekoaly(){
       peli = new Peli();
    }

    public Solmu alfaBeta(Peli lauta, int syvyys, int alfa, int beta, boolean maximointi){
        if(syvyys == 0 || lauta.tarkistaVoitot()){
            return (new Solmu(lauta.getEdellinenSiirto(), siirronPisteytys(lauta, lauta.getEdellinenSiirto())));
        }
        if(maximointi){
            int arvo = Integer.MIN_VALUE;
            int parasSarake = 3;
            for(int i=0;i<7;i++){
                if(!lauta.onkoSarakeTaysi(i)){                   
                    Peli kopio = lauta;
                    kopio.asetaPala(i);
                    int uusiArvo = Math.max(arvo, (alfaBeta(kopio, syvyys-1, alfa, beta, false)).getArvo());
                    if(uusiArvo > arvo){
                        arvo = uusiArvo;
                        parasSarake = i;
                    }
                    alfa = Math.max(alfa, arvo);
                    if(alfa >= beta){
                        break;
                    }
                }                
            }
            return new Solmu(parasSarake, arvo);
        }else{
            int arvo = Integer.MAX_VALUE;
            int huonoinSarake = 3;
            for(int i=0;i<7;i++){
                if(!lauta.onkoSarakeTaysi(i)){                   
                    Peli kopio = lauta;
                    kopio.asetaPala(i);
                    int uusiArvo = Math.min(arvo, (alfaBeta(kopio, syvyys-1, alfa, beta, false)).getArvo());
                    if(uusiArvo < arvo){
                        arvo = uusiArvo;
                        huonoinSarake = i;
                    }
                    beta = Math.min(beta, arvo);
                    if(beta <= alfa){
                        break;
                    }
                }                
            }
            return new Solmu(arvo, huonoinSarake);
        }
    }
    
    public int siirronPisteytys(Peli lauta, int sarake){
        int pisteet = 0;

        return pisteet;  
    }
}
