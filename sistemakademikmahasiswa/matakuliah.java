/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemakademikmahasiswa;

public class matakuliah {
    String id,mk,j,r,d;
    int s;

    public matakuliah(String id, String mk, int s, String j,String r ,String d ) {
        this.id = id;
        this.mk = mk;
        this.s = s;
        this.j = j;        
        this.r = r;
        this.d = d;
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

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }
    
}
