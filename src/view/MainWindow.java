package view;

import presenter.IMainPresenter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MainWindow extends JFrame implements IMainView {

    // Presenter
    private IMainPresenter presenter;

    // View components
    private JPanel rootPanel;
    private JTable memoryTable;
    private JTable registersAndFlagsTable;
    private JTextField addressTextField;
    private JTabbedPane emulatorTabbedPanel;
    private JEditorPane codeEditorPanel;
    private JEditorPane transteResultTextPanel;
    private JScrollPane memoryTableScrollPanel;
    private JEditorPane outputPortEditorPanel;
    private JEditorPane inputPortEditorPanel;

    private JMenu fileMenu;
    private JMenuItem openItem;
    private JMenuItem saveItem;
    private JMenuItem exitItem;

    private JMenu emulatorMenu;
    private JMenuItem compileProgramItem;
    private JMenuItem runItem;
    private JMenuItem stepItem;
    private JMenuItem stopItem;
    private JMenuItem resetRegisterItem;
    private JMenuItem resetMemoryItem;
    private JMenuItem clearScreensItem;

    private JMenu helpMenu;
    private JMenuItem helpItem;
    private JMenuItem aboutItem;

    // Helps components
    private MemoryTableModel memoryTableModel;
    private RegistersAndFlagsTableModel registersAndFlagsTableModel;

    // Data Source
    private String[][] dataSourceForMemoryTable = new String[65536][3];
    private String[][] dataSourceForRegistersAndFlagsTable = new String[13][4];

    // FONTS
    public static final Font menuBarFont = new Font(Font.MONOSPACED, Font.PLAIN, 12);

    public MainWindow(IMainPresenter presenter) {
        this.presenter = presenter;
    }

    private void createUI() {
        createMenuBar();
        createMemoryTable();
        createRegistersAndFlagsTable();
        settingUI();
    }

    private void bindHotkey() {
        inputPortEditorPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_F1: {
                        if (helpItem.isEnabled()) {
                            help();
                        }
                        break;
                    }
                    case KeyEvent.VK_F2: {
                        if (compileProgramItem.isEnabled()) {
                            loadProgram();
                            break;
                        }
                    }
                    case KeyEvent.VK_F5: {
                        if (runItem.isEnabled()) {
                            run();
                        }
                        break;
                    }
                    case KeyEvent.VK_F9: {
                        if (stepItem.isEnabled()) {
                            step();
                        }
                        break;
                    }
                    case KeyEvent.VK_F12: {
                        if (stopItem.isEnabled()) {
                            stop();
                        }
                        break;
                    }
                    case KeyEvent.VK_ESCAPE: {
                        if (resetRegisterItem.isEnabled()) {
                            resetRegisters();
                            updateMemoryTable(dataSourceForMemoryTable, 0);
                            updateRegistersAndFlagsTable(dataSourceForRegistersAndFlagsTable);
                        }
                    }
                }
            }
        });

        codeEditorPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F2) {
                    if (compileProgramItem.isEnabled()) {
                        loadProgram();
                    }
                }
            }
        });
    }

    private void createMenuBar() {
        openItem = new JMenuItem("Open...");
        openItem.setFont(menuBarFont);
        saveItem = new JMenuItem("Save...");
        saveItem.setFont(menuBarFont);
        exitItem = new JMenuItem("Exit");
        exitItem.setFont(menuBarFont);

        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                open();
            }
        });

        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });

        fileMenu = new JMenu("File");
        fileMenu.setFont(menuBarFont);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        compileProgramItem = new JMenuItem("Compile          (F2)");
        compileProgramItem.setFont(menuBarFont);
        runItem = new JMenuItem("Run              (F5)");
        runItem.setFont(menuBarFont);
        stepItem = new JMenuItem("Step             (F9)");
        stepItem.setFont(menuBarFont);
        stopItem = new JMenuItem("Stop             (F12)");
        stopItem.setFont(menuBarFont);
        resetRegisterItem = new JMenuItem("Reset registers  (Esc)");
        resetRegisterItem.setFont(menuBarFont);
        resetMemoryItem = new JMenuItem("Reset memory");
        resetMemoryItem.setFont(menuBarFont);
        clearScreensItem = new JMenuItem("Clear screens");
        clearScreensItem.setFont(menuBarFont);

        compileProgramItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadProgram();
            }
        });

        runItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                run();
            }
        });

        stepItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                step();
            }
        });

        stopItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stop();
            }
        });

        resetRegisterItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetRegisters();
            }
        });

        resetMemoryItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetMemory();
            }
        });

        clearScreensItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearScreens();
            }
        });

        emulatorMenu = new JMenu("Emulator");
        emulatorMenu.setFont(menuBarFont);
        emulatorMenu.add(compileProgramItem);
        emulatorMenu.addSeparator();
        emulatorMenu.add(runItem);
        emulatorMenu.add(stepItem);
        emulatorMenu.add(stopItem);
        emulatorMenu.addSeparator();
        emulatorMenu.add(resetRegisterItem);
        emulatorMenu.add(resetMemoryItem);
        emulatorMenu.addSeparator();
        emulatorMenu.add(clearScreensItem);

        helpItem = new JMenuItem("Help (F1)");
        helpItem.setFont(menuBarFont);
        aboutItem = new JMenuItem("About...");
        aboutItem.setFont(menuBarFont);

        helpItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                help();
            }
        });

        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                about();
            }
        });

        helpMenu = new JMenu("Help");
        helpMenu.setFont(menuBarFont);
        helpMenu.add(helpItem);
        helpMenu.add(aboutItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(emulatorMenu);
        menuBar.add(helpMenu);

        this.setJMenuBar(menuBar);
    }

    private void createMemoryTable() {
        memoryTable.setModel(memoryTableModel);
        memoryTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        memoryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        memoryTable.setRowSelectionInterval(0, 0);
        memoryTable.setFocusable(false);
        memoryTable.setDefaultRenderer(memoryTable.getColumnClass(1), new MemoryTableCellRenderer());
        memoryTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        memoryTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        memoryTable.getColumnModel().getColumn(2).setPreferredWidth(20);
    }

    private void createRegistersAndFlagsTable() {
        registersAndFlagsTable.setModel(registersAndFlagsTableModel);
        registersAndFlagsTable.setFocusable(false);
        registersAndFlagsTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        registersAndFlagsTable.setEnabled(false);
        registersAndFlagsTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        registersAndFlagsTable.setDefaultRenderer(registersAndFlagsTable.getColumnClass(1),
                new RegistersAndFlagsTableCellRenderer());
    }

    private void settingUI() {
        inputPortEditorPanel.setEditable(false);
        outputPortEditorPanel.setFocusable(false);
        registersAndFlagsTable.setFocusable(false);
        memoryTable.setFocusable(false);
        stopItem.setEnabled(false);
        codeEditorPanel.getDocument().putProperty(PlainDocument.tabSizeAttribute, 2);
        emulatorTabbedPanel.setFocusable(false);
    }

    // Actions
    private void open() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("i8080", "i8080"));
        int result = fileChooser.showOpenDialog(MainWindow.this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                codeEditorPanel.setText(loadProgramTextFromFile(fileChooser.getSelectedFile().getPath()));
                JOptionPane.showMessageDialog(MainWindow.this, "Load successful");
                emulatorTabbedPanel.setSelectedIndex(1);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(MainWindow.this,
                        "Opening failed", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String loadProgramTextFromFile(String path) {
        try {
            BufferedReader bufferedReader
                    = new BufferedReader(new FileReader(new File(path)));
            StringBuilder program = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                program.append(line).append(System.lineSeparator());
            }
            return program.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void loadProgram() {
        presenter.loadProgram(codeEditorPanel.getText());
    }

    private void save() {

    }

    private void exit() {
        System.exit(1);
    }

    private void run() {
        presenter.run();
    }

    private void step() {
        presenter.step();
    }

    private void stop() {
        presenter.stop();
    }

    private void resetRegisters() {
        presenter.resetRegisters();
    }

    private void resetMemory() {
        presenter.resetMemory();
    }

    private void clearScreens() {
        presenter.clearScreens();
    }

    private void help() {

    }

    private void about() {

    }

    @Override
    public void updateMemoryTable(String[][] dataSource, int PC) {
        for (int i = 0; i < dataSourceForMemoryTable.length; ++i) {
            for (int j = 0; j < dataSourceForMemoryTable[i].length; ++j) {
                dataSourceForMemoryTable[i][j] = dataSource[i][j];
            }
        }
        if (memoryTableModel != null) {
            memoryTableModel.fireTableDataChanged();
            memoryTable.setRowSelectionInterval(PC, PC);
        }

        updateScrollPane();
    }

    public void updateScrollPane() {

        try {
            Thread.sleep(10);
        } catch (InterruptedException ignored) {}

        if (memoryTable.getSelectedRow() > 10) {
            memoryTableScrollPanel.getViewport().setViewPosition(new Point(0, memoryTable.getSelectedRow() * 16 - 160));
        } else {
            memoryTableScrollPanel.getViewport().setViewPosition(new Point(0, 0));
        }
    }

    @Override
    public void updateRegistersAndFlagsTable(String[][] dataSource) {
        ArrayList<Integer> selectedRow = new ArrayList<>();
        for (int i = 0; i < dataSourceForRegistersAndFlagsTable.length; ++i) {
            for (int j = 0; j < dataSourceForRegistersAndFlagsTable[i].length; ++j) {
                if (dataSourceForRegistersAndFlagsTable[i][j] != null) {
                    if (!dataSourceForRegistersAndFlagsTable[i][j].equals(dataSource[i][j])) {
                        selectedRow.add(i);
                    }
                }
                dataSourceForRegistersAndFlagsTable[i][j] = dataSource[i][j];
            }
        }
        if (registersAndFlagsTableModel != null) {
            registersAndFlagsTableModel.fireTableDataChanged();
        }

        for (int i = 0; i < selectedRow.size(); ++i) {
            registersAndFlagsTable.addRowSelectionInterval(selectedRow.get(i), selectedRow.get(i));
        }
    }

    @Override
    public void create() {
        memoryTableModel = new MemoryTableModel(dataSourceForMemoryTable);
        registersAndFlagsTableModel =
                new RegistersAndFlagsTableModel(dataSourceForRegistersAndFlagsTable);

        setTitle("Intel 8080 Emulator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(rootPanel);

        pack();
        setResizable(false);
        setLocationRelativeTo(null);

        createUI();
        bindHotkey();

        setVisible(true);
    }

    @Override
    public void setRunningMode(boolean isRunningMode) {
        fileMenu.setEnabled(!isRunningMode);
        helpMenu.setEnabled(!isRunningMode);
        compileProgramItem.setEnabled(!isRunningMode);
        runItem.setEnabled(!isRunningMode);
        stepItem.setEnabled(!isRunningMode);
        resetRegisterItem.setEnabled(!isRunningMode);
        resetMemoryItem.setEnabled(!isRunningMode);
        clearScreensItem.setEnabled(!isRunningMode);
        stopItem.setEnabled(isRunningMode);
    }

    @Override
    public void updateCodeEditor(String translateResult, boolean hasErrors) {
        transteResultTextPanel.setText(translateResult);
        if (!hasErrors) {
            emulatorTabbedPanel.setSelectedIndex(0);
        }
    }
}