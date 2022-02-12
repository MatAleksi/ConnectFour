package main;


public class Solmu {
       
    private int sarake;
    private int arvo;

    public Solmu(int sarake, int arvo){
        this.arvo=arvo;
        this.sarake=sarake;
    }
    
    public int getSarake(){
        return sarake;
    }

    public int getArvo(){
        return arvo;
    }

    public void setArvo(int uusiArvo){
        arvo = uusiArvo;
    }    
}
