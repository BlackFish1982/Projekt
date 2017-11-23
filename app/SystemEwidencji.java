package app;
import java.util.ArrayList;
import java.util.List;
import static java.lang.System.out;

public class SystemEwidencji {
    private List<Uzytkownik>listaUzytkownikow;
    
    public SystemEwidencji(){
        this.listaUzytkownikow = new ArrayList<Uzytkownik>();
    }

    public void dodajKierowce(Uzytkownik uzytkownikDoDodania){
        this.listaUzytkownikow.add(uzytkownikDoDodania);
        out.println("Użytkownik dodany!\n");
    }
    
    public void wyswietlWszystkichKierowcow(){
        out.println("Lista użytkowników w systemie:\n");
        for(int i = 0; i < this.listaUzytkownikow.size(); i++){
            out.println(this.listaUzytkownikow.get(i).toString());
        }
    }
    
    public Uzytkownik wyszukajKierowce(String pesel)
    {
        for(int i = 0; i < this.listaUzytkownikow.size(); i++){
            Boolean pasujacyUzytkownikDoPeselu = this.listaUzytkownikow.get(i).sprawdzPesel(pesel);
            if (pasujacyUzytkownikDoPeselu){
                out.println(this.listaUzytkownikow.get(i));
                return this.listaUzytkownikow.get(i);
            }
        }
        out.println("Brak użytkowników o podanym peselu w bazie ewidencji!\n");

        return null;
    }
    public void dodajPunkty(Policjant aktualnyPolicjant, String pesel, int liczbaPunktow){
        if (!aktualnyPolicjant.getIdentyfikator().isEmpty() && aktualnyPolicjant.sprawdzUprawnienia()){
            Uzytkownik uzytkownikDoUkarania = this.wyszukajKierowce(pesel);
            uzytkownikDoUkarania.zmienIloscPunktow(liczbaPunktow);
            out.println("Punkty zostały dodane!\n");

        }else{
            out.println("Nie można dodać punktów!\n");
        }
    }

    public void sprawdzPunkty(Uzytkownik aktualnyUzytkownik){
        int liczbaPunktowUzytkownika = aktualnyUzytkownik.getPunktyKarne();
        out.println("Liczba punktów: " + liczbaPunktowUzytkownika + "\n");
    }

    public void kasujPunkty(Policjant aktualnyPolicjant, String pesel){
        if (!aktualnyPolicjant.getIdentyfikator().isEmpty() && aktualnyPolicjant.sprawdzUprawnienia()){
            Uzytkownik uzytkownikDoUsunieciaPunktow = this.wyszukajKierowce(pesel);
            uzytkownikDoUsunieciaPunktow.kasujPunkty(0);
        }
    }
}

