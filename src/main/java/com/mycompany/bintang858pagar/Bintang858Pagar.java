/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bintang858pagar;

import javax.swing.JOptionPane;

/**
 *
 * @author DTM PC
 */
public class Bintang858Pagar {

    public static void main(String[] args) {
        Pulsa pulsa = new Pulsa(100000);
        mainMenu(pulsa);
    }
    
    static void mainMenu(Pulsa pulsa) {
        String jumlahText = "Pulsa anda: Rp" + pulsa.getPulsa() + "\n";
        String text = """
                      Menu
                      1. Transfer Pulsa
                      2. Minta Pulsa
                      """;
        String optInput = JOptionPane.showInputDialog(null, (jumlahText + text), "*858# USSD Code", 3);
        try {
            NumberFormatException nfe = new NumberFormatException("Invalid input");
            while (true) {
                if (optInput != null && !optInput.isBlank()){
                    switch (optInput.charAt(0)) {
                    case '1': transferPulsa(pulsa); break;
                    case '2': mintaPulsa(pulsa); break;
                    //case '3': cekPulsa(pulsa); break;
                    default: throw nfe;
                    }
                } else if (optInput != null && optInput.isBlank()) {
                    throw nfe;
                } else {
                    break;
                }
                jumlahText = "Pulsa anda: Rp" + pulsa.getPulsa() + "\n";
                optInput = JOptionPane.showInputDialog(null, (jumlahText + text), "*858# USSD Code", 3);
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, nfe, "ERROR", 0);
        }
    }
    
    static void transferPulsa(Pulsa pulsa) {
        NumberFormatException nfe = new NumberFormatException("Invalid input");
        String telpText = "Input nomor telepon yang ingin anda transfer pulsanya";
        String telpInput = JOptionPane.showInputDialog(null, telpText, "Transfer Pulsa", 3);
        if (telpInput != null) {
            Integer.parseInt(telpInput);
            String pulsaText = "Input jumlah pulsa yang ingin anda kirim ke " + telpInput;
            String pulsaInput = JOptionPane.showInputDialog(null, pulsaText, "Transfer Pulsa", 3);
            if (pulsaInput != null && pulsaInput.charAt(0) != '-') {
                int pulsaTF = Integer.parseInt(pulsaInput);
                if (pulsaTF <= pulsa.getPulsa()) {
                    String confirmText = "Anda akan mengirim pulsa sebesar Rp" + pulsaTF 
                            + " ke nomor telepon " + telpInput + "\n"
                            + "Apakah anda ingin melanjutkan?";
                    int choice = JOptionPane.showConfirmDialog(null, confirmText, "Transfer Pulsa", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        //Query transfer pulsa
                        pulsa.substractPulsa(pulsaTF);
                        JOptionPane.showMessageDialog(null, "Transfer pulsa berhasil dilakukan!", "Transfer Pulsa", 1);
                    } else {
                        JOptionPane.showMessageDialog(null, "Transfer pulsa dibatalkan", "Transfer Pulsa", 1);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Saldo pulsa anda tidak mencukupi", "Transfer Pulsa", 0);
                }
            } else if (pulsaInput != null && pulsaInput.charAt(0) == '-') {
                throw nfe;
            }
        }
    }
    
    static void mintaPulsa(Pulsa pulsa) {
        NumberFormatException nfe = new NumberFormatException("Invalid input");
        String telpText = "Input nomor telepon yang ingin anda minta pulsanya";
        String telpInput = JOptionPane.showInputDialog(null, telpText, "Minta Pulsa", 3);
        if (telpInput != null && !telpInput.isBlank()) {
            String pulsaText = "Input jumlah pulsa yang ingin anda minta ke " + telpInput;
            String pulsaInput = JOptionPane.showInputDialog(null, pulsaText, "Minta Pulsa", 3);
            if (pulsaInput != null && pulsaInput.charAt(0) != '-') {
                int pulsaTF = Integer.parseInt(pulsaInput);
                String confirmText = "Anda akan meminta pulsa sebesar Rp" + pulsaTF 
                        + " ke nomor telepon " + telpInput + "\n"
                        + "Apakah anda ingin melanjutkan?";
                int choice = JOptionPane.showConfirmDialog(null, confirmText, "Minta Pulsa", JOptionPane.YES_NO_CANCEL_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    //Query transfer pulsa
                    pulsa.addPulsa(pulsaTF);
                    JOptionPane.showMessageDialog(null, "Minta pulsa berhasil dilakukan!", "Transfer Pulsa", 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Minta pulsa dibatalkan", "Transfer Pulsa", 1);
                }
            } else if (pulsaInput != null && pulsaInput.charAt(0) == '-') {
                throw nfe;
            }
        } else if (telpInput != null && telpInput.isBlank()) {
            throw nfe;
        }
    }
        
//    static void cekPulsa(Pulsa pulsa) {
//        String pulsaText = "Saldo pulsa anda:\nRp" + pulsa.getPulsa();
//        JOptionPane.showMessageDialog(null, pulsaText, "Cek Pulsa", 1);
//    }
}

class Pulsa {
    private int jumlah;
    
    Pulsa(int jumlah) {
        this.jumlah = jumlah;
    }
    
    void setPulsa(int jumlah) {
        this.jumlah = jumlah;
    }
    
    int getPulsa() {
        return this.jumlah;
    }
    
    void addPulsa(int jumlah) {
        this.jumlah += jumlah;
    }
    
    void substractPulsa(int jumlah) {
        this.jumlah -= jumlah;
    }
}
