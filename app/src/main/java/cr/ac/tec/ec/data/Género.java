package cr.ac.tec.ec.data;

/**
 * Created by CASA on 6/2/2018.
 */

public class Género {
    @com.google.gson.annotations.SerializedName("id")
    private int IdGen;
    private String Descripción;

    public int getIdGen() {
        return IdGen;
    }

    public void setIdGen(int idGen) {
        IdGen = idGen;
    }

    public String getDescripción() {
        return Descripción;
    }

    public void setDescripción(String descripción) {
        Descripción = descripción;
    }
}
