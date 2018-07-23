import javax.swing.table.AbstractTableModel;

/**
 * Created by Arnas on 7/19/2018.
 */
public class Medication {


    Report report = new Report();
    String name;
    String measure;
    String nomenclature;

    public String getNomenclature() {
        return nomenclature;
    }

    public void setParameters(String name, String measure, String nomenclature) {
        this.name = name;
        this.measure = measure;
        this.nomenclature = nomenclature;
    }



    public Medication() {
    }

    public boolean verifyData(String name, String measure, String nomenclature) {
        if (name.equals("")) {
            report.ReportMessage("Reikia ivesti vaisto pavadinima");
            return false;
        }
        if (nomenclature.length() != 7) {
            report.ReportMessage("Nomenklaturos numeris sudarytas is 7 skaitmenu");
            return false;
        }
        if(!nomenclature.matches("[0-9]+")) {
            report.ReportMessage("Nomenklaturinis numeris sudarytas tik is skaiciu");
            return false;
        }
        else return true;
    }

}
