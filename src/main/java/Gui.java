import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;


public class Gui implements ActionListener
{
    private JFrame frame;
    private JPanel panel;
    private Dimension screenSize;
    private Toolkit tk;
    private JButton importButton;
    private JButton executeButton;
    private JButton createDbButton;
    private JButton loadDbButton;
    private File[] invoices;
    private DatabaseManager dbManager;

    public Gui()
    {
        dbManager = new DatabaseManager();
        frame = new JFrame();
        panel = new JPanel();

        importButton = new JButton("Import");
        executeButton = new JButton("Execute");
        createDbButton = new JButton("Create DB");
        loadDbButton = new JButton("Load DB");

        tk = Toolkit.getDefaultToolkit();

        screenSize = tk.getScreenSize();

        frame.setSize(screenSize.width - 650, screenSize.height - 300);

        frame.setContentPane(panel);

        frame.setVisible(true);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel.add(importButton);
        panel.add(executeButton);
        panel.add(createDbButton);
        panel.add(loadDbButton);
        panel.updateUI();

        importButton.addActionListener(this);
        executeButton.addActionListener(this);
        createDbButton.addActionListener(this);
        loadDbButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == importButton)
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setMultiSelectionEnabled(true);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xls");
            fileChooser.addChoosableFileFilter(filter);

            int option = fileChooser.showOpenDialog(frame);
            if(option == JFileChooser.APPROVE_OPTION)
            {
                invoices = fileChooser.getSelectedFiles();
            }
            System.out.println("Done Reading");
        }

        if(e.getSource() == executeButton)
        {
            JFileChooser chooser = new JFileChooser();


            ExcelReader reader = new ExcelReader();
            Person p = reader.readFile(invoices[0]);

            for(File f : invoices)
            {
                try
                {
                    dbManager.fillTable(reader.readFile(f));
                }
                catch (SQLException ex)
                {
                    throw new RuntimeException(ex);
                }
            }

            ExcelFileBuilder excelFileBuilder = new ExcelFileBuilder();
            excelFileBuilder.createExcelFile("/Users/fabry/Documents/GitHub/InvoiceRecorder/SalesRecord.db",
                    "/Users/fabry/Desktop/excelfile.xls", panel);
        }

        if(e.getSource() == createDbButton)
        {
            dbManager.createDatabase();
            try
            {
                dbManager.createQuarter("1");
            } catch (SQLException ex)
            {
                throw new RuntimeException(ex);
            }
        }
    }
}
