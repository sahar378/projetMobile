package isetb.tp7.testprojet.model;

public class Tache {
    private int idt;
    private String nomt;
    private String descrption;

    public int getIdt() {
        return idt;
    }

    public void setIdt(int idt) {
        this.idt = idt;
    }

    public String getNomt() {
        return nomt;
    }

    public void setNomt(String nomt) {
        this.nomt = nomt;
    }

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }

    public Tache() {
    }

    public Tache(String nomt, String descrption) {
        this.nomt = nomt;
        this.descrption = descrption;
    }

    public Tache(int idt, String nomt, String descrption) {
        this.idt = idt;
        this.nomt = nomt;
        this.descrption = descrption;
    }
}
