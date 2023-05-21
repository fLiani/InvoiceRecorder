import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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

    public Gui()
    {
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

            int option = fileChooser.showOpenDialog(frame);
            if(option == JFileChooser.APPROVE_OPTION)
            {
                invoices = fileChooser.getSelectedFiles();
            }
        }
    }
}
