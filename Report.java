import javax.swing.*;

/**
 * Created by Arnas on 7/20/2018.
 */
public class Report {

    public void ReportMessage(String message) {
        JOptionPane.showMessageDialog(new JPanel(),
                message,
                "Warning",
                JOptionPane.WARNING_MESSAGE);
    }
    public void SuccessMessage(String message) {
        JOptionPane.showMessageDialog(new JPanel(),
                message,
                "Pranesimas",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
