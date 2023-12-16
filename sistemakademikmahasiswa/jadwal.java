/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemakademikmahasiswa;

/**
 *
 * @author imran sukron hamid
 */
public class jadwal {
    String mkul,jdl,dos,ruangan;
    int sks;

    public jadwal(String mkul, String jdl,int sks ,String dos, String ruangan ) {
        this.mkul = mkul;
        this.jdl = jdl;
        this.sks = sks;
        this.dos = dos;
        this.ruangan = ruangan;
        
    }

    public String getMkul() {
        return mkul;
    }

    public void setMkul(String mkul) {
        this.mkul = mkul;
    }

    public String getJdl() {
        return jdl;
    }

    public void setJdl(String jdl) {
        this.jdl = jdl;
    }

    public String getDos() {
        return dos;
    }

    public void setDos(String dos) {
        this.dos = dos;
    }

    public String getRuangan() {
        return ruangan;
    }

    public void setRuangan(String ruangan) {
        this.ruangan = ruangan;
    }

    public int getSks() {
        return sks;
    }

    public void setSks(int sks) {
        this.sks = sks;
    }
    
}
