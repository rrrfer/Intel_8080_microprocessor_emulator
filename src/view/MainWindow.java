package view;

import presenter.IMainPresenter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
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
    private JEditorPane codeEditorPane;
    private JEditorPane transteResultTextPanel;
    private JScrollPane memoryTableScrollPanel;
    private JEditorPane outputPortEditorPanel;
    private JTextField inputPortEditorPanel;

    // JMenuBar
    private JMenuBar menuBar;

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

    // Run
    private boolean isRunningMode = false;

    // Input
    private String inputString;

    // Data Source
    private String[][] dataSourceForMemoryTable = new String[65536][3];
    private String[][] dataSourceForRegistersAndFlagsTable = new String[13][4];

    // Breakpoints
    public static final ArrayList<Integer> breakpoints = new ArrayList<>();

    // Res
    public static final Font mainFont = new Font(Font.MONOSPACED, Font.PLAIN, 12);
    public static final Color selectedColor = new Color(44, 192, 8);

    public MainWindow(IMainPresenter presenter) {
        this.presenter = presenter;
    }

    // Create&Setting GUI
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
                            translationProgram();
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
                            updateMemoryTable(dataSourceForMemoryTable, 0, true);
                            updateRegistersAndFlagsTable(dataSourceForRegistersAndFlagsTable);
                        }
                        break;
                    }
                    case KeyEvent.VK_ENTER: {
                        if (inputPortEditorPanel.isEditable()) {
                            inputString = inputPortEditorPanel.getText();
                        }
                        break;
                    }
                }
            }
        });

        codeEditorPane.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F2) {
                    if (compileProgramItem.isEnabled()) {
                        translationProgram();
                    }
                }
            }
        });
    }

    private void createMenuBar() {
        openItem = new JMenuItem("Open...");
        openItem.setFont(mainFont);
        saveItem = new JMenuItem("Save...");
        saveItem.setFont(mainFont);
        exitItem = new JMenuItem("Exit");
        exitItem.setFont(mainFont);

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
        fileMenu.setFont(mainFont);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        compileProgramItem = new JMenuItem("Translation      (F2)");
        compileProgramItem.setFont(mainFont);
        runItem = new JMenuItem("Run              (F5)");
        runItem.setFont(mainFont);
        stepItem = new JMenuItem("Step             (F9)");
        stepItem.setFont(mainFont);
        stopItem = new JMenuItem("Stop             (F12)");
        stopItem.setFont(mainFont);
        resetRegisterItem = new JMenuItem("Reset registers  (Esc)");
        resetRegisterItem.setFont(mainFont);
        resetMemoryItem = new JMenuItem("Reset memory");
        resetMemoryItem.setFont(mainFont);
        clearScreensItem = new JMenuItem("Clear screens");
        clearScreensItem.setFont(mainFont);

        compileProgramItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                translationProgram();
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
        emulatorMenu.setFont(mainFont);
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
        helpItem.setFont(mainFont);
        aboutItem = new JMenuItem("About...");
        aboutItem.setFont(mainFont);

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
        helpMenu.setFont(mainFont);
        helpMenu.add(helpItem);
        helpMenu.add(aboutItem);

        menuBar = new JMenuBar();
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
        emulatorTabbedPanel.setFocusable(false);
        menuBar.setFocusable(false);

        stopItem.setEnabled(false);
        codeEditorPane.getDocument().putProperty(PlainDocument.tabSizeAttribute, 2);
        codeEditorPane.setFont(mainFont);

        memoryTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    presenter.setProgramCounter(memoryTable.getSelectedRow());
                } else if (e.getButton() == MouseEvent.BUTTON3) {

                    int oldRowSelection = memoryTable.getSelectedRow();

                    int row = memoryTable.rowAtPoint(e.getPoint());
                    memoryTable.setRowSelectionInterval(row, row);

                    if (breakpoints.contains(memoryTable.getSelectedRow())) {
                        breakpoints.remove((Integer) memoryTable.getSelectedRow());
                    } else {
                        breakpoints.add(memoryTable.getSelectedRow());
                    }
                    presenter.setBreakpoint(memoryTable.getSelectedRow());
                    memoryTable.setRowSelectionInterval(oldRowSelection, oldRowSelection);
                }
            }
        });
    }

    // Actions
    private void open() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("i8080", "i8080"));
        int result = fileChooser.showOpenDialog(MainWindow.this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                codeEditorPane.setText(loadProgramTextFromFile(fileChooser.getSelectedFile().getPath()));
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

    private void translationProgram() {
        presenter.loadProgram(codeEditorPane.getText());
    }

    private void save() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("i8080", "i8080"));
        int result = fileChooser.showOpenDialog(MainWindow.this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                String path = fileChooser.getSelectedFile().getPath();
                if (!path.endsWith(".i8080")) {
                    path = path + ".i8080";
                }
                FileWriter fileWriter = new FileWriter(path);
                fileWriter.write(codeEditorPane.getText());
                fileWriter.close();
                JOptionPane.showMessageDialog(MainWindow.this, "Save successful");
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(MainWindow.this,
                        "Saving failed", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
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
        clearOutConsole();
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

    // IMainView
    @Override
    public void updateMemoryTable(String[][] dataSource, int PC, boolean scroll) {
        for (int i = 0; i < dataSourceForMemoryTable.length; ++i) {
            for (int j = 0; j < dataSourceForMemoryTable[i].length; ++j) {
                dataSourceForMemoryTable[i][j] = dataSource[i][j];
            }
        }
        if (memoryTableModel != null) {
            memoryTableModel.fireTableDataChanged();
            memoryTable.setRowSelectionInterval(PC, PC);
        }

        if (scroll) {
            updateScrollPane();
        }
    }

    private void updateScrollPane() {
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

        createUI();
        bindHotkey();

        pack();
        setResizable(false);
        setLocationRelativeTo(null);

        setVisible(true);
    }

    @Override
    public void setRunningMode(boolean isRunningMode) {
        this.isRunningMode = isRunningMode;
        fileMenu.setEnabled(!isRunningMode);
        helpMenu.setEnabled(!isRunningMode);
        compileProgramItem.setEnabled(!isRunningMode);
        runItem.setEnabled(!isRunningMode);
        stepItem.setEnabled(!isRunningMode);
        resetRegisterItem.setEnabled(!isRunningMode);
        resetMemoryItem.setEnabled(!isRunningMode);
        clearScreensItem.setEnabled(!isRunningMode);
        // ==========================================
        stopItem.setEnabled(isRunningMode);
    }

    @Override
    public void updateCodeEditor(String translateResult, boolean hasErrors) {
        transteResultTextPanel.setText(translateResult);
        if (!hasErrors) {
            clearOutConsole();
            emulatorTabbedPanel.setSelectedIndex(0);
            inputPortEditorPanel.requestFocus();
        }
    }

    @Override
    public void consoleOut(int value) {
        String outputString = outputPortEditorPanel.getText();
        outputString = outputString + " " + Integer.toString(value);
        String[] strs = outputString.split(System.lineSeparator());
        if (strs[strs.length - 1].length() > 45) {
            outputString = outputString + System.lineSeparator();
        }
        outputPortEditorPanel.setText(outputString);
    }

    private void clearOutConsole() {
        outputPortEditorPanel.setText("");
    }

    @Override
    public int consoleIn() {
        setEnableAction(false);
        inputPortEditorPanel.setEditable(true);
        inputPortEditorPanel.requestFocus();
        while (true) {
            if (inputString != null) {
                int value = otherRadix2Dec(inputString);
                if (value >= 0) {
                    inputPortEditorPanel.setEditable(false);
                    inputPortEditorPanel.setText("");
                    inputString = null;
                    if (!isRunningMode) {
                        setEnableAction(true);
                    } else {
                        stopItem.setEnabled(true);
                    }
                    return value;
                }
            }
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {}
        }
    }

    private int otherRadix2Dec(String number) {
        int address;
        if (number.charAt(0) == '0' && number.length() > 1) {
            if (number.charAt(1) == 'x') {
                try {
                    address = Integer.parseInt(number.substring(2), 16);
                    return address;
                } catch (NumberFormatException e) {
                    return -1;
                }
            } else if (number.charAt(1) == 'b') {
                try {
                    address = Integer.parseInt(number.substring(2), 2);
                    return address;
                } catch (NumberFormatException e) {
                    return -1;
                }
            } else {
                try {
                    address = Integer.parseInt(number);
                    return address;
                } catch (NumberFormatException e) {
                    return -1;
                }
            }
        } else {
            try {
                address = Integer.parseInt(number);
                return address;
            } catch (NumberFormatException e) {
                return -1;
            }
        }
    }

    private void setEnableAction(boolean isEnable) {
        fileMenu.setEnabled(isEnable);
        compileProgramItem.setEnabled(isEnable);
        stepItem.setEnabled(isEnable);
        runItem.setEnabled(isEnable);
        stopItem.setEnabled(isEnable);
        resetRegisterItem.setEnabled(isEnable);
        resetMemoryItem.setEnabled(isEnable);
        clearScreensItem.setEnabled(isEnable);
        helpMenu.setEnabled(isEnable);
    }
}