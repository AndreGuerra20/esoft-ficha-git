import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MinesFinder extends JFrame {
    private JPanel painelPrincipal;
    private JButton jogoFacilButton;
    private JButton jogoDificilButton;
    private JButton jogoMedioButton;
    private JButton sairButton;
    private JPanel painelCentro;
    private JPanel painelOeste;
    private JLabel lblNomeFacil;
    private JLabel lblNomeMedio;
    private JLabel lblNomeDificil;
    private TabelaRecordes recordesFacil;
    private TabelaRecordes recordesMedio;
    private TabelaRecordes recordesDificil;

    public MinesFinder(String title) {
        super(title);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();
        sairButton.addActionListener(this::sairButtonActionPerformed);
        jogoFacilButton.addActionListener(this::jogoFacilButtonActionPerformed);
        jogoMedioButton.addActionListener(this::btnJogoMedioActionPerformed);
        jogoDificilButton.addActionListener(this::btnJogoDificilActionPerformed);
        recordesFacil = new TabelaRecordes();
        recordesMedio = new TabelaRecordes();
        recordesDificil = new TabelaRecordes();
        lerRecordesDoDisco();

        lblNomeFacil.setText(recordesFacil.getNomeJogador() + ' ' +recordesFacil.getTempoDeJogo()/1000);
        lblNomeMedio.setText(recordesMedio.getNomeJogador() + ' ' + recordesMedio.getTempoDeJogo()/1000);
        lblNomeDificil.setText(recordesDificil.getNomeJogador() + ' ' + recordesDificil.getTempoDeJogo()/1000);

        recordesFacil.addTabelaRecordesListener(new TabelaRecordesListener() {
            @Override
            public void recordesActualizados(TabelaRecordes recordes) {
                recordesFacilActualizado(recordes);
            }
        });
        recordesMedio.addTabelaRecordesListener(new TabelaRecordesListener() {
            @Override
            public void recordesActualizados(TabelaRecordes recordes) {
                recordesMedioActualizado(recordes);
            }
        });
        recordesDificil.addTabelaRecordesListener(new TabelaRecordesListener() {
            @Override
            public void recordesActualizados(TabelaRecordes recordes) {
                recordesDificilActualizado(recordes);
            }
        });
    }
    public static void main(String[] args) {
        new MinesFinder("Mines Finder").setVisible(true);
    }
    private void sairButtonActionPerformed(ActionEvent e) {
        System.exit(0);
    }
    private void jogoFacilButtonActionPerformed(ActionEvent e) {
        var janela = new JanelaDeJogo(new CampoMinado(9,9,3),recordesFacil);
        janela.setVisible(true);
    }
    private void btnJogoMedioActionPerformed(ActionEvent e) {
        var janela = new JanelaDeJogo(new CampoMinado(16,16,40),recordesMedio);
        janela.setVisible(true);
    }
    private void btnJogoDificilActionPerformed(ActionEvent e) {
        var janela = new JanelaDeJogo(new CampoMinado(16,16,90),recordesDificil);
        janela.setVisible(true);
    }
    private void recordesFacilActualizado(TabelaRecordes recordes) {
        lblNomeFacil.setText(recordes.getNomeJogador() + ' ' + recordes.getTempoDeJogo()/1000);
    }
    private void recordesMedioActualizado(TabelaRecordes recordes) {
        lblNomeMedio.setText(recordes.getNomeJogador() + ' ' + recordes.getTempoDeJogo()/1000);
    }
    private void recordesDificilActualizado(TabelaRecordes recordes) {
        lblNomeDificil.setText(recordes.getNomeJogador() + ' ' + recordes.getTempoDeJogo()/1000);
    }
    private void guardarRecordesDisco() {
        ObjectOutputStream oos = null;
        try {
            File f =new
                    File(System.getProperty("user.home")+File.separator+"minesfinder.recordes");
            oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(recordesFacil);
            oos.writeObject(recordesMedio);
            oos.writeObject(recordesDificil);
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(MinesFinder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void lerRecordesDoDisco() {
        ObjectInputStream ois = null;
        File f = new
                File(System.getProperty("user.home")+File.separator+"minesfinder.recordes");
        if (f.canRead()) {
            try {
                ois = new ObjectInputStream(new FileInputStream(f));
                recordesFacil=(TabelaRecordes) ois.readObject();
                recordesMedio=(TabelaRecordes) ois.readObject();
                recordesDificil=(TabelaRecordes) ois.readObject();
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(MinesFinder.class.getName()).log(Level.SEVERE,
                        null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MinesFinder.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        }
    }
}