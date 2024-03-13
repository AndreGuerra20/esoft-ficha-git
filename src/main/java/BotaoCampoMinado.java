import javax.swing.*;
import java.awt.*;

public class BotaoCampoMinado  extends JButton {
    private int estado;

    private int linha;
    private int coluna;

    public BotaoCampoMinado(int linha,int coluna) {
        this.estado = CampoMinado.TAPADO;
        this.linha = linha;
        this.coluna = coluna;
    }
    public int getLinha(){
        return linha;
    }
    public int getColuna(){
        return coluna;
    }
    public void setEstado(int estado) {
        this.estado = estado;
        switch (estado) {
            case CampoMinado.VAZIO:
                setText("");
                setBackground(Color.DARK_GRAY);
                break;
            case CampoMinado.TAPADO:
                setText("");
                setBackground(null);
                break;
            case CampoMinado.DUVIDA:
                setText("?");
                setBackground(Color.yellow);
                break;
            case CampoMinado.MARCADO:
                setText("!");
                setBackground(Color.red);
                break;
            case CampoMinado.REBENTADO:
                setText("*");
                setBackground(Color.orange);
                break;
            /*case 1:setText("1");setBackground(Color.darkGray);break;
            case 2:setText("2");setBackground(Color.darkGray);break;
            case 3:setText("3");setBackground(Color.darkGray);break;
            case 4:setText("4");setBackground(Color.darkGray);break;
            case 5:setText("5");setBackground(Color.darkGray);break;
            case 6:setText("6");setBackground(Color.darkGray);break;
            case 7:setText("7");setBackground(Color.darkGray);break;
            case 8:setText("8");setBackground(Color.darkGray);break;*/
            default:
                setText(String.valueOf(estado));
                setBackground(Color.DARK_GRAY);
        }
    }

}
