package br.edu.ifnmg.bookaroom.campussala;

import br.edu.ifnmg.bookaroom.campus.Campus;
import br.edu.ifnmg.bookaroom.sala.Sala;

public class CampusSala {
    private Campus campus;
    private Sala sala;

    public CampusSala(Campus campus, Sala sala) {
        this.campus = campus;
        this.sala = sala;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}
