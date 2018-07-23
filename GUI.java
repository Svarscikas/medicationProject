import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Arnas on 7/19/2018.
 */
public class GUI {


    private JTextField medicationName;
    private JTextField medicationNumber;
    private JComboBox measureBox;
    private JButton pridėtiVaistąButton;
    private JPanel MainPanel;
    private JButton peržiūrėtiVaistusButton;
    private JTable dataTable;
    private JButton goBack;
    private JButton filter;
    private JTextField textField1;
    private JPanel SecondPanel;
    private JPanel FirstPanel;
    private JScrollPane pane;
    private Report report = new Report();
    MyTableModel myData;
    Object[][] googleData;
    Object[] googleColumns;

    List<Medication> medicationList = new ArrayList<Medication>();

    private void getGoogleData() throws IOException {
        SheetDataUser user = new SheetDataUser();
        String text = user.getJsonString();
        JsonDecodeDemo decode = new JsonDecodeDemo(text);
        googleData = decode.getArr();
        googleColumns = decode.getHeader();
    }

    void createData(){


    }

    public GUI(){
        String[] measureStrings = {"Kg", "Vnt", "Tabl"};
        JFrame frame = new JFrame("vaistai");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(MainPanel);
        frame.pack();
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        measureBox.setModel(new DefaultComboBoxModel(measureStrings));
        measureBox.setSelectedIndex(-1);
        medicationNumber.setText("");
        medicationName.setText("");

        pridėtiVaistąButton.addActionListener(e -> {
            String name = medicationName.getText();
            String number = medicationNumber.getText();
            if(measureBox.getSelectedItem() == null) {
                report.ReportMessage("Jus nepasirinkote matavimo vieneto");
            }
            else {
                String measure = measureBox.getSelectedItem().toString();
                Medication temp = new Medication();
                if (temp.verifyData(name, measure, number)) {
                    temp.setParameters(name, measure, number);
                    medicationList.add(temp);
                    measureBox.setSelectedIndex(-1);
                    medicationName.setText("");
                    medicationNumber.setText("");
                    report.SuccessMessage("Vaistas sėkmingai pridėtas!");
                }
            }
        });


        peržiūrėtiVaistusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myData = new MyTableModel();
                try {
                    getGoogleData();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                myData.setTableData(googleColumns,googleData);
                dataTable.setModel(myData);
                dataTable.setPreferredScrollableViewportSize(new Dimension(500,50));
                //FirstPanel.setEnabled(false);
                //FirstPanel.setVisible(false);
                //SecondPanel.setVisible(true);
                //SecondPanel.setEnabled(true);
                //frame.setSize(800,600);
                //frame.setLocationRelativeTo(null);

            }
        });
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              /*  FirstPanel.setEnabled(true);
                FirstPanel.setVisible(true);
                SecondPanel.setVisible(false);
                SecondPanel.setEnabled(false);
                frame.setSize(600,300);
                frame.setLocationRelativeTo(null);*/
            }
        });
    }

    public static void main(String[] args) {

        new GUI();
    }
}
