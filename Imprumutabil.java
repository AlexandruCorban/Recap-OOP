import java.time.LocalDate;

public interface Imprumutabil {
        void imprumuta(String numeCititor, LocalDate dataReturnare);
        void returneaza();
        boolean esteDisponibil();
        String getNumeCititor();
        LocalDate getDataReturnare();
}
