package front.dto;

public class ProfessorEntity {

    private String nume;
    private String prenume;
    private String PCNP;

    public ProfessorEntity() {
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getPCNP() {
        return PCNP;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setPCNP(String PCNP) {
        this.PCNP = PCNP;
    }

}
