package main;

import java.lang.reflect.Array;

public class Tekoaly {
    private int maksimoitavaPelaaja;
    private int minimoitavaPelaaja;

    public Tekoaly(int maxvuoro){
        maksimoitavaPelaaja = maxvuoro;
    }

    public int haeParasSiirto(Peli peli, int pelattu){
        int[][] kopio = new int[6][7];
        kopio = peli.getLauta();
        int syvyys = pelattu + 1;
        if(syvyys > 10) syvyys = 10;
        int parasSiirto = alfaBeta(kopio, 10, Integer.MIN_VALUE, Integer.MAX_VALUE, true, minimoitavaPelaaja, 0)[0];
        return parasSiirto;
    }
    //AlfaBeta-algoritmi
    public int[] alfaBeta(int[][] lauta, int syvyys, int alfa, int beta, boolean maximointi, int vuoro, int edellinen){
        //tarkastetaan onko edellinen pelaaja voittanut pelin
        if(onkoVoitto(lauta, vuoro, edellinen)){
            if(!maximointi){
                return new int[] {-1, 1000000000};
            }else{
                return new int[] {-1, -1000000000};
            }
        }
        //tarkastetaan onko maksimisyvyys saavutettu tai onko lauta täysi  
        if(syvyys == 0 || !onkoTilaaLaudalla(lauta)){
            if(!onkoTilaaLaudalla(lauta)){
                return new int[] {-1, 0};
            }
            return new int[] {-1, siirronPisteytys(lauta)};
        }
        //Maximoidaan pelaaja
        if(maximointi){
            int arvo = Integer.MIN_VALUE;
            int parasSarake = 3;
            for(int i=0;i<7;i++){
                if(onkoTilaaSarakkeessa(i, lauta)){                   
                    int y = 0;
                    while(true){
                        if(lauta[y+1][i] != 0){  
                            break;
                        }
                        if(y ==4){
                            y++;
                            break;
                        }
                        y++;
                    }
                    lauta[y][i] = vuoro;                    
                    if(vuoro == 1){
                        vuoro = 2;
                    }else{
                        vuoro = 1;
                    } 
                    int uusiArvo = Math.max(arvo, (alfaBeta(lauta, syvyys-1, alfa, beta, false, vuoro, i))[1]);
                    lauta[y][i] = 0;
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
            return new int[] {parasSarake, arvo};
        //Minimisoidaan pelaaja
        }else{
            int arvo = Integer.MAX_VALUE;
            int huonoinSarake = 3;
            for(int i=0;i<7;i++){
                if(onkoTilaaSarakkeessa(i, lauta)){                                    
                    int y = 0;
                    while(true){
                        if(lauta[y+1][i] != 0){  
                            break;
                        }
                        if(y ==4){
                            y++;
                            break;
                        }
                        y++;
                    } 
                    lauta[y][i] = vuoro;
                    if(vuoro == 1){
                        vuoro = 2;
                    }else{
                        vuoro = 1;
                    }                    
                    int uusiArvo = Math.min(arvo, (alfaBeta(lauta, syvyys-1, alfa, beta, true, vuoro, i))[1]);
                    lauta[y][i] = 0;
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
            return new int[] {arvo, huonoinSarake};
        }
    }
    
    public boolean onkoVoitto(int[][] lauta, int vuoro, int edellinen){
        Peli testipeli = new Peli(lauta, vuoro, edellinen);
        testipeli.setEdellinenSiirto(edellinen);
        return testipeli.tarkistaVoitot();
    }   

    public boolean onkoTilaaSarakkeessa(int sarake, int[][] lauta){
        if(lauta[0][sarake] != 0){
            return false;
        }
        return true;
    }    
    public boolean onkoTilaaLaudalla(int[][] lauta){
        for(int i=0;i<7;i++){
            if(lauta[0][i] == 0){
                return true;
            }
        }
        return false;
    }

    //Pisteiden laskeminen siirroille
    public int siirronPisteytys(int[][] lauta){
        int pisteet = 0;
        //Pisteet keskikolumnille
        for(int i=0;i<6;i++){
            if(lauta[i][3] == maksimoitavaPelaaja){
                pisteet += 5;
            }
        }
        //Pisteet vaakatasossa
        for(int x=0;x<6;x++){
            int[] rivi = new int[7];
            for(int i=0;i<7;i++){
                rivi[i] = lauta[x][i];
            }      
            for(int i=0;i<4;i++){
                int[] osaRivi = new int[4];
                System.arraycopy(rivi, i, osaRivi, 0, 4);
                pisteet += pisteytys(osaRivi);       
            }    
        }

        //Pisteet pystyyn
        for(int x=0;x<7;x++){
            int[] sarake = new int[6];
            for(int i=0;i<6;i++){
                sarake[i] = lauta[i][x];
            }
            for(int i=0;i<3;i++){
                int[] osaSarake = new int[4];
                System.arraycopy(sarake, i, osaSarake, 0, 4);  
                pisteet += pisteytys(osaSarake);  
            }
        }
        //Pisteet vinoihin suuntiin
        for(int i=0;i<3;i++){
            for(int j=0;j<4;j++){
                int[] rivi = new int[4];
                for(int x=0;x<4;x++){
                    rivi[x] = lauta[i+x][j+x];
                }
                pisteet += pisteytys(rivi);
            }
        }

        for(int i=0;i<3;i++){
            for(int j=0;j<4;j++){
                int[] rivi = new int[4];
                for(int x=0;x<4;x++){
                    rivi[x] = lauta[i+3-x][j+x];
                }
                pisteet += pisteytys(rivi);
            }
        } 
        return pisteet;  
    }
    
    //Pisteiden laskeminen yhdelle suunnalle
    public int pisteytys(int[] neljaPalaa){
        int pisteet = 0;
        int palat = 0;
        int tyhjat = 0;
        int vPalat = 0;
        //Lasketaan saadun neljän palan rivin tyhjät ruudut, omat ruudut, ja vastustajan ruudut
        for(int i=0;i<4;i++){
            if(neljaPalaa[i] == 0){
                tyhjat++;
            }else if(neljaPalaa[i] == maksimoitavaPelaaja){
                palat++;
            }else{
                vPalat++;
            }
        }
        //Pisteytetään neljän palan rivi
        if(palat == 4){
            pisteet +=1000;
        }else if(palat == 3 && tyhjat == 1){
            pisteet += 5;
        }else if(palat == 2 && tyhjat == 2){
            pisteet += 2;
        }
        if(vPalat == 3 && tyhjat == 1){
            pisteet -= 50;
        }else if(vPalat == 2 && tyhjat == 2){
            pisteet -=10;
        }
        return pisteet;          
    }
    
}
