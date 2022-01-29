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

    //Asettaa palan laudalle jos sarakkeessa on tilaa.
    public boolean asetaPala(int sarake, int pelaaja){
        //Jos sarake on täynnä palautetaan false
        if(lauta[0][sarake] != 0){
            return false;
        }
        int i = 0;
        while(true){
            if(lauta[i+1][sarake] != 0){
                lauta[i][sarake] = pelaaja;
                uudenPalanSarake = sarake;
                uudenPalanRivi = i;
                break;
            }
            i++;
        }
        return true;
    }

    public boolean tarkistaVoitto(){        
        //Tarkistetaan onko voittoa vaakaan
        int palojaVaakaan = 0;
        //Tarkistetaan vasen
        if(uudenPalanSarake != 0 && lauta[uudenPalanRivi][uudenPalanSarake-1] == lauta[uudenPalanRivi][uudenPalanSarake]){
            palojaVaakaan++;
            if(uudenPalanSarake-1 != 0 && lauta[uudenPalanRivi][uudenPalanSarake-2] == lauta[uudenPalanRivi][uudenPalanSarake]){
                palojaVaakaan++;
                if(uudenPalanSarake-2 != 0 && lauta[uudenPalanRivi][uudenPalanSarake-3] == lauta[uudenPalanRivi][uudenPalanSarake]){
                    palojaVaakaan++;            
                }
            }
        }
        //Tarkistetaan oikea
        if(uudenPalanSarake != 6 && lauta[uudenPalanRivi][uudenPalanSarake+1] == lauta[uudenPalanRivi][uudenPalanSarake]){
            palojaVaakaan++;
            if(uudenPalanSarake+1 != 6 && lauta[uudenPalanRivi][uudenPalanSarake+2] == lauta[uudenPalanRivi][uudenPalanSarake]){
                palojaVaakaan++;
                if(uudenPalanSarake+2 != 6 && lauta[uudenPalanRivi][uudenPalanSarake+3] == lauta[uudenPalanRivi][uudenPalanSarake]){
                    palojaVaakaan++;            
                }
            }
        }
        if(palojaVaakaan > 3){
            return true;
        }

        //Tarkistetaan onko voittoa pystyyn
        if(uudenPalanRivi < 2){
            if(lauta[uudenPalanRivi][uudenPalanSarake] == lauta[uudenPalanRivi-1][uudenPalanSarake] &&
               lauta[uudenPalanRivi][uudenPalanSarake] == lauta[uudenPalanRivi-2][uudenPalanSarake] &&
               lauta[uudenPalanRivi][uudenPalanSarake] == lauta[uudenPalanRivi-3][uudenPalanSarake]){
               return true;
            }
        }

        //Tarkastetaan onko voittoa vinoon alavasemmalta ylösoikeaan
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
        if(palatVinoon1 > 3){
            return true;
        }

        //Tarkastetaan onko voittoa vinoon ylävasemmalta alaoikealle
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
        if(palatVinoon2 > 3){
            return true;
        }
        
        return false;
    }
      
}
