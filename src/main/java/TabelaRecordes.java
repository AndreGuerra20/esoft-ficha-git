import java.io.Serializable;
import java.util.ArrayList;

public class TabelaRecordes implements Serializable {
    private String nomeJogador;
    private long tempoDeJogo;
    private transient ArrayList<TabelaRecordesListener> listeners;

    public TabelaRecordes() {
        this.nomeJogador = "Anónimo";
        this.tempoDeJogo = 9999999;
        listeners = new ArrayList<>();
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public long getTempoDeJogo() {
        return tempoDeJogo;
    }

    public void setRecorde(String nome,long tempoDeJogo){
        if (tempoDeJogo<this.tempoDeJogo) {
            this.nomeJogador=nome;
            this.tempoDeJogo=tempoDeJogo;
            notifyRecordesActualizados();
        }

    }
    public void addTabelaRecordesListener(TabelaRecordesListener list) {
        if (listeners == null) listeners = new ArrayList<>();
        listeners.add(list);
    }
    public void removeTabelaRecordesListener(TabelaRecordesListener list) {
        if (listeners != null) listeners.remove(list);
    }
    private void notifyRecordesActualizados() {
        if (listeners != null) {
            for (TabelaRecordesListener list : listeners)
                list.recordesActualizados(this);
        }
    }


}
