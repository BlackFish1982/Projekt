package app;

public class Policjant {
    private String identyfikator;
    private Boolean uprawnienia;
    
    public Policjant(String identyfikator){
        this.identyfikator = identyfikator;
        if (identyfikator.isEmpty()){
            this.uprawnienia = false;
        }else{
            this.uprawnienia = true;
        }
    }

    public String getIdentyfikator(){
        return this.identyfikator;
    }

    public Boolean sprawdzUprawnienia(){
        return this.uprawnienia;
        
    }

    public String toString(){
        String wynik = "Policjant: \nIdentyfikator: " + this.identyfikator + "\nUprawnienia: " + this.uprawnienia + "\n";
        return wynik;
    }
}
