package main;

public class Tekoaly {
    private int maksimoitavaPelaaja;
    private int minimoitavaPelaaja;

    public Tekoaly(int vuoro){
        maksimoitavaPelaaja = vuoro;
        minimoitavaPelaaja = (maksimoitavaPelaaja+1)%2;
    }

    public int haeParasSiirto(Peli peli){
        int[][] kopio = new int[6][7];
        for(int i=0;i<6;i++){
            for(int j=0;j<7;j++){
                kopio[i][j] = peli.getLauta()[i][j];
            }
        } 
        Solmu parasSiirto = alfaBeta(kopio, 4, Integer.MIN_VALUE, Integer.MAX_VALUE, true, peli.getVuoro(), 0);
        System.out.println();
        return parasSiirto.getSarake();
    }
    //AlfaBeta-algoritmi
    public Solmu alfaBeta(int[][] lauta, int syvyys, int alfa, int beta, boolean maximointi, int vuoro, int edellinen){
        if(onkoVoitto(lauta, vuoro, edellinen)){
            if(maximointi){
                return new Solmu(-1, 10);
            }else{
                return new Solmu(-1, -10);
            }
        }
        System.out.println("jaigjoa");
        if(syvyys == 0 || onkoTilaaLaudalla(lauta)){
            if(onkoTilaaLaudalla(lauta)){
                return new Solmu(-1, siirronPisteytys(lauta, 0));
            }
            return new Solmu(-1, siirronPisteytys(lauta, vuoro));
        }
        //Maximoidaan pelaaja
        if(maximointi){
            int arvo = Integer.MIN_VALUE;
            int parasSarake = 3;
            for(int i=0;i<7;i++){
                if(!onkoTilaaSarakkeessa(i, lauta)){                   
                    int[][] kopio = new int[6][7];
                    for(int x=0;x<6;x++){
                        for(int j=0;j<7;j++){
                            kopio[x][j] = lauta[x][j];
                        }
                    }
                    int y = 0;
                    while(true){
                        if(kopio[y+1][i] != 0){  
                            break;
                        }
                        if(y ==4){
                            y++;
                            break;
                        }
                        y++;
                    } 
                    kopio[y][i] = vuoro;
                    if(vuoro == 1){
                        vuoro = 2;
                    }else{
                        vuoro = 1;
                    }
                    int uusiArvo = Math.max(arvo, (alfaBeta(kopio, syvyys-1, alfa, beta, false, vuoro, i)).getArvo());
                    kopio[y][i] = 0;
                    if(uusiArvo > arvo){
                        arvo = uusiArvo;
                        parasSarake = i;
                    }
                    alfa = Math.max(alfa, arvo);
                    if(alfa >= beta){
                        return new Solmu(parasSarake, arvo);
                    }
                }                
            }
            return new Solmu(parasSarake, arvo);
        //Minimisoidaan pelaaja
        }else{
            int arvo = Integer.MAX_VALUE;
            int huonoinSarake = 3;
            for(int i=0;i<7;i++){
                if(!onkoTilaaSarakkeessa(i, lauta)){                                     
                    int[][] kopio = new int[6][7];
                    for(int x=0;x<6;x++){
                        for(int j=0;j<7;j++){
                            kopio[x][j] = lauta[x][j];
                        }
                    }
                    int y = 0;
                    while(true){
                        if(kopio[y+1][i] != 0){  
                            break;
                        }
                        if(y ==4){
                            y++;
                            break;
                        }
                        y++;
                    } 
                    kopio[y][i] = vuoro;
                    if(vuoro == 1){
                        vuoro = 2;
                    }else{
                        vuoro = 1;
                    }
                    int uusiArvo = Math.min(arvo, (alfaBeta(kopio, syvyys-1, alfa, beta, true, vuoro, i)).getArvo());
                    kopio[y][i] = 0;
                    if(uusiArvo < arvo){
                        arvo = uusiArvo;
                        huonoinSarake = i;
                    }
                    beta = Math.min(beta, arvo);
                    if(beta <= alfa){
                        return new Solmu(arvo, huonoinSarake);
                    }
                }                
            }
            return new Solmu(arvo, huonoinSarake);
        }
    }
    
    public boolean onkoVoitto(int[][] lauta, int vuoro, int edellinen){
        Peli peli = new Peli();
        peli.setLauta(lauta);
        peli.setVuoro(vuoro);
        peli.setEdellinenSiirto(edellinen);
        return peli.tarkistaVoitot();
    }   

    public boolean onkoTilaaSarakkeessa(int sarake, int[][] lauta){
        if(lauta[0][sarake] != 0){
            return true;
        }
        return false;
    }    
    public boolean onkoTilaaLaudalla(int[][] lauta){
        for(int i=0;i<7;i++){
            if(onkoTilaaSarakkeessa(i, lauta)){
                return true;
            }
        }
        return false;
    }

    //Pisteiden laskeminen siirroille
    public int siirronPisteytys(int[][] lauta, int vuoro){
        int pisteet = 0;
        //Pisteet keskikolumnille
        int paloja = 0;
        for(int i=0;i<6;i++){
            if(lauta[i][3] == vuoro){
                paloja++;
            }
        }
        pisteet += paloja*5;

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
            pisteet -= 100;
        }

        return pisteet;          
    }
    
}
