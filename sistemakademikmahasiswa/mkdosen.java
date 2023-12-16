/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemakademikmahasiswa;

/**
 *
 * @author imran sukron hamid
 */
public class mkdosen {
   public String id,mk,j;
    public int s;

    public mkdosen(String id, String mk, int s, String j ) {
        this.id = id;
        this.mk = mk;
        this.s = s;
        this.j = j;        
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }

    public String getJ() {
        return j;
    }

    public void setJ(String j) {
        this.j = j;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    
    
}
