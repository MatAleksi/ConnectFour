package main;

public class Peli {
    private int[][] lauta;
    private int uudenPalanSarake;
    private int uudenPalanRivi;

    public Peli(){
        this.lauta = new int[6][7];   
        for(int x=0;x<7;x++){
            for(int y=0;y<6;y++){
                lauta[y][x] = 0;
            }
        }
    }
    
    public int[][] getLauta(){
        return lauta;
    }


    //Asettaa palan laudalle jos sarakkeessa on tilaa.
    public boolean asetaPala(int sarake, int pelaaja){
        //Jos sarake on täynnä palautetaan false
        if(lauta[0][sarake] != 0){
            return false;
        }
        int i = 0;
        while(true){
            if(lauta[i+1][sarake] != 0){  
                break;
            }
            if(i ==4){
                i++;
                break;
            }
            i++;
        }
        lauta[i][sarake] = pelaaja;
        uudenPalanSarake = sarake;
        uudenPalanRivi = i;
        return true;
    }
    
    public boolean tarkistaVaaka(){
        //Tarkistetaan onko voittoa vaakaan
        int palojaVaakaan = 0;
        //Tarkistetaan vasen
        if(uudenPalanSarake != 0){
           if(lauta[uudenPalanRivi][uudenPalanSarake-1] == lauta[uudenPalanRivi][uudenPalanSarake]){
                palojaVaakaan++;
                if(uudenPalanSarake-1 != 0){
                    if(lauta[uudenPalanRivi][uudenPalanSarake-2] == lauta[uudenPalanRivi][uudenPalanSarake]){
                        palojaVaakaan++;
                        if(uudenPalanSarake-2 != 0){
                            if(lauta[uudenPalanRivi][uudenPalanSarake-3] == lauta[uudenPalanRivi][uudenPalanSarake]){
                                palojaVaakaan++;
                            }
                        }
                    }
                }
            }
        }
        //Tarkistetaan oikea
        if(uudenPalanSarake != 6){
           if(lauta[uudenPalanRivi][uudenPalanSarake+1] == lauta[uudenPalanRivi][uudenPalanSarake]){
                palojaVaakaan++;
                if(uudenPalanSarake+1 != 6){
                    if(lauta[uudenPalanRivi][uudenPalanSarake+2] == lauta[uudenPalanRivi][uudenPalanSarake]){
                        palojaVaakaan++;
                        if(uudenPalanSarake+2 != 6){
                            if(lauta[uudenPalanRivi][uudenPalanSarake+3] == lauta[uudenPalanRivi][uudenPalanSarake]){
                                palojaVaakaan++;
                            }
                        }
                    }
                }
            }
        }
        if(palojaVaakaan > 2){
            return true;
        }
        return false;
    }

    public boolean tarkistaPysty(){
        //Tarkistetaan onko voittoa pystyyn
        if(uudenPalanRivi < 3){
            if(lauta[uudenPalanRivi][uudenPalanSarake] == lauta[uudenPalanRivi+1][uudenPalanSarake] &&
               lauta[uudenPalanRivi][uudenPalanSarake] == lauta[uudenPalanRivi+2][uudenPalanSarake] &&
               lauta[uudenPalanRivi][uudenPalanSarake] == lauta[uudenPalanRivi+3][uudenPalanSarake]){
                return true;
            }
        }
        return false;
    }

    public boolean tarkistaAlhaaltaOikealle(){
        int palatVinoon1 = 0;
        //Tarkastetaan oikealta vasemmalle
        if(uudenPalanSarake != 0 && uudenPalanRivi != 5 && lauta[uudenPalanRivi+1][uudenPalanSarake-1] == lauta[uudenPalanRivi][uudenPalanSarake]){
            palatVinoon1++;
            if(uudenPalanSarake-1 != 0 && uudenPalanRivi+1 != 5 && lauta[uudenPalanRivi+2][uudenPalanSarake-2] == lauta[uudenPalanRivi][uudenPalanSarake]){
                palatVinoon1++;
                if(uudenPalanSarake-2 != 0 && uudenPalanRivi+2 != 5 && lauta[uudenPalanRivi+3][uudenPalanSarake-3] == lauta[uudenPalanRivi][uudenPalanSarake]){
                    palatVinoon1++;            
                }
            }
        }
        //Tarkastetaan vasemmalta oikealle
        if(uudenPalanSarake != 6 && uudenPalanRivi != 0 && lauta[uudenPalanRivi-1][uudenPalanSarake+1] == lauta[uudenPalanRivi][uudenPalanSarake]){
            palatVinoon1++;
            if(uudenPalanSarake+1 != 6 && uudenPalanRivi-1 != 0 && lauta[uudenPalanRivi-2][uudenPalanSarake+2] == lauta[uudenPalanRivi][uudenPalanSarake]){
                palatVinoon1++;
                if(uudenPalanSarake+2 != 6 && uudenPalanRivi-2 != 0 && lauta[uudenPalanRivi-3][uudenPalanSarake+3] == lauta[uudenPalanRivi][uudenPalanSarake]){
                    palatVinoon1++;            
                }
            }
        }
        if(palatVinoon1 > 2){
            return true;
        }
        return false;
    }

    public boolean tarkistaAlhaaltaVasemmalle(){
        int palatVinoon2 = 0;
        //Tarkastetaan oikealta vasemmalle
        if(uudenPalanSarake != 0 && uudenPalanRivi != 0 && lauta[uudenPalanRivi-1][uudenPalanSarake-1] == lauta[uudenPalanRivi][uudenPalanSarake]){
            palatVinoon2++;
            if(uudenPalanSarake-1 != 0 && uudenPalanRivi-1 != 0 && lauta[uudenPalanRivi-2][uudenPalanSarake-2] == lauta[uudenPalanRivi][uudenPalanSarake]){
                palatVinoon2++;
                if(uudenPalanSarake-2 != 0 && uudenPalanRivi-2 != 0 && lauta[uudenPalanRivi-3][uudenPalanSarake-3] == lauta[uudenPalanRivi][uudenPalanSarake]){
                    palatVinoon2++;            
                }
            }
        }
        //Tarkastetaan vasemmalta oikealle
        if(uudenPalanSarake != 6 && uudenPalanRivi != 5 && lauta[uudenPalanRivi+1][uudenPalanSarake+1] == lauta[uudenPalanRivi][uudenPalanSarake]){
            palatVinoon2++;
            if(uudenPalanSarake+1 != 6 && uudenPalanRivi+1 != 5 && lauta[uudenPalanRivi+2][uudenPalanSarake+2] == lauta[uudenPalanRivi][uudenPalanSarake]){
                palatVinoon2++;
                if(uudenPalanSarake+2 != 6 && uudenPalanRivi+2 != 5 && lauta[uudenPalanRivi+3][uudenPalanSarake+3] == lauta[uudenPalanRivi][uudenPalanSarake]){
                    palatVinoon2++;            
                }
            }
        }
        if(palatVinoon2 > 2){
            return true;
        }
        return false;
    }

    public boolean tarkistaVoitot(){        
        if(tarkistaVaaka() || tarkistaPysty() || tarkistaAlhaaltaOikealle() || tarkistaAlhaaltaVasemmalle()){
            return true;
        }
        return false;
    }
      
}
