package view;

import presenter.IMainPresenter;
import presenter.MainPresenter;

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
    private JTabbedPane emulatorTabbedPanel;
    private JEditorPane codeEditorTextPanel;
    private JEditorPane translateResultTextPanel;
    private JScrollPane memoryTableScrollPanel;
    private JEditorPane consoleOutputTextPanel;
    private JTextField consoleInputTextPanel;

    // JMenuBar
    private JMenuBar menuBar;

    private JMenu fileMenu;
    private JMenuItem openItem;
    private JMenuItem saveItem;
    private JMenuItem exitItem;

    private JMenu emulatorMenu;
    private JMenuItem translationItem;
    private JMenuItem runItem;
    private JMenuItem stepItem;
    private JMenuItem stopItem;
    private JMenuItem resetRegisterItem;
    private JMenuItem resetMemoryItem;
    private JMenuItem clearScreensItem;
    private JMenuItem deleteAllBreakpointsItem;

    private JMenu helpMenu;
    private JMenuItem helpItem;
    private JMenuItem aboutItem;

    // Data model components
    private MemoryTableModel memoryTableModel;
    private RegistersAndFlagsTableModel registersAndFlagsTableModel;

    // Data Source for model components
    private String[][] dataSourceForMemoryTable = new String[65536][3];
    private String[][] dataSourceForRegistersAndFlagsTable = new String[13][4];

    // Input
    private String inputString;

    // Breakpoints
    public static final ArrayList<Integer> breakpoints = new ArrayList<>();

    // Resources
    public static final Font mainFont = new Font(Font.MONOSPACED, Font.PLAIN, 12);
    public static final Color selectedColor = new Color(44, 192, 8);

    public MainWindow(IMainPresenter presenter,
                      String[][] dataSourceForMemoryTable,
                      String[][] dataSourceForRegistersAndFlagsTable) {
        this.presenter = presenter;
        this.dataSourceForMemoryTable = dataSourceForMemoryTable;
        this.dataSourceForRegistersAndFlagsTable = dataSourceForRegistersAndFlagsTable;

        memoryTableModel = new MemoryTableModel(dataSourceForMemoryTable);
        registersAndFlagsTableModel =
                new RegistersAndFlagsTableModel(dataSourceForRegistersAndFlagsTable);

        setTitle("Intel 8080 Emulator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(rootPanel);

        createUI();

        pack();
        setResizable(false);
        setLocationRelativeTo(null);

        setVisible(true);
    }

    // Create&Setting GUI
    private void createUI() {
        createMenuBar();
        createMemoryTable();
        createRegistersAndFlagsTable();
        applySettingUI();
        setActionsListeners();
    }

    private void setActionsListeners() {
        consoleInputTextPanel.addKeyListener(new KeyAdapter() {
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
                        if (translationItem.isEnabled()) {
                            translation();
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
                        }
                        break;
                    }
                    case KeyEvent.VK_ENTER: {
                        if (consoleInputTextPanel.isEditable()) {
                            inputString = consoleInputTextPanel.getText();
                        }
                        break;
                    }
                }
            }
        });

        codeEditorTextPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F2) {
                    if (translationItem.isEnabled()) {
                        translation();
                    }
                }
            }
        });

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

        translationItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                translation();
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

        deleteAllBreakpointsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAllBreakpoints();
            }
        });

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
    }

    private void createMenuBar() {
        openItem = new JMenuItem("Open...");
        openItem.setFont(mainFont);
        saveItem = new JMenuItem("Save...");
        saveItem.setFont(mainFont);
        exitItem = new JMenuItem("Exit");
        exitItem.setFont(mainFont);

        fileMenu = new JMenu("File");
        fileMenu.setFont(mainFont);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        translationItem = new JMenuItem("Translation      (F2)");
        translationItem.setFont(mainFont);
        runItem = new JMenuItem("Run             (F5)");
        runItem.setFont(mainFont);
        stepItem = new JMenuItem("Step             (F9)");
        stepItem.setFont(mainFont);
        stopItem =                 new JMenuItem("Stop             (F12)");
        stopItem.setFont(mainFont);
        resetRegisterItem = new JMenuItem("Reset registers  (Esc)");
        resetRegisterItem.setFont(mainFont);
        resetMemoryItem = new JMenuItem("Reset memory");
        resetMemoryItem.setFont(mainFont);
        clearScreensItem = new JMenuItem("Clear screens");
        clearScreensItem.setFont(mainFont);
        deleteAllBreakpointsItem = new JMenuItem("Delete all breakpoints");
        deleteAllBreakpointsItem.setFont(mainFont);

        emulatorMenu = new JMenu("Emulator");
        emulatorMenu.setFont(mainFont);
        emulatorMenu.add(translationItem);
        emulatorMenu.addSeparator();
        emulatorMenu.add(runItem);
        emulatorMenu.add(stepItem);
        emulatorMenu.add(stopItem);
        emulatorMenu.addSeparator();
        emulatorMenu.add(resetRegisterItem);
        emulatorMenu.add(resetMemoryItem);
        emulatorMenu.addSeparator();
        emulatorMenu.add(clearScreensItem);
        emulatorMenu.add(deleteAllBreakpointsItem);

        helpItem = new JMenuItem("Help (F1)");
        helpItem.setFont(mainFont);
        aboutItem = new JMenuItem("About...");
        aboutItem.setFont(mainFont);

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
        registersAndFlagsTable.setDefaultRenderer(registersAndFlagsTable.getColumnClass(1),
                new RegistersAndFlagsTableCellRenderer());
        registersAndFlagsTable.getColumnModel().getColumn(0).setPreferredWidth(90);
    }

    private void applySettingUI() {

        consoleInputTextPanel.setEditable(false);

        consoleOutputTextPanel.setFocusable(false);
        registersAndFlagsTable.setFocusable(false);
        memoryTable.setFocusable(false);
        emulatorTabbedPanel.setFocusable(false);
        menuBar.setFocusable(false);

        stopItem.setEnabled(false);

        codeEditorTextPanel.getDocument().putProperty(PlainDocument.tabSizeAttribute, 2);
        codeEditorTextPanel.setFont(mainFont);
    }

    // Actions
    private void open() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("i8080", "i8080"));
        int result = fileChooser.showOpenDialog(MainWindow.this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                String path = fileChooser.getSelectedFile().getPath();
                presenter.loadProgramFromFile(path);
                emulatorTabbedPanel.setSelectedIndex(1);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(MainWindow.this,
                        "Opening failed", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void save() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("i8080", "i8080"));
        int result = fileChooser.showSaveDialog(MainWindow.this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                String path = fileChooser.getSelectedFile().getPath();
                String programText = codeEditorTextPanel.getText();
                presenter.saveProgramInFile(path, programText);
                JOptionPane.showMessageDialog(MainWindow.this, "Save successful");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(MainWindow.this,
                        "Saving failed", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void translation() {
        presenter.translation(codeEditorTextPanel.getText());
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

    private void deleteAllBreakpoints() {
        while (breakpoints.size() != 0) {
            presenter.setBreakpoint(breakpoints.remove(0));
        }
        int selectedRow = memoryTable.getSelectedRow();
        memoryTableModel.fireTableDataChanged();
        this.setProgramCounterPosition(selectedRow);
    }

    private void help() {
        // TODO Написать справку
    }

    private void about() {
        // TODO Написать о программе
    }

    // IMainView
    @Override
    public void setMemoryDataTable(String[][] dataSource) {
        System.arraycopy(dataSource, 0,
                            dataSourceForMemoryTable, 0, dataSource.length);
        memoryTableModel.fireTableDataChanged();
    }

    @Override
    public void setProgramCounterPosition(int programCounterPosition) {
        memoryTable.setRowSelectionInterval(programCounterPosition, programCounterPosition);
        int tableSize = memoryTable.getRowCount();
        int scrollMax = memoryTableScrollPanel.getVerticalScrollBar().getMaximum();
        if (programCounterPosition > 10) {
            memoryTableScrollPanel.getVerticalScrollBar().setValue(scrollMax /
                    tableSize * (programCounterPosition - 10));
        } else {
            memoryTableScrollPanel.getVerticalScrollBar().setValue(0);
        }
        memoryTableModel.fireTableRowsUpdated(programCounterPosition - 20, programCounterPosition + 20);
    }

    @Override
    public void setRegistersAndFlagsDataTable(String[][] dataSource) {
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

        registersAndFlagsTableModel.fireTableDataChanged();

        for (int i = 0; i < selectedRow.size(); ++i) {
            registersAndFlagsTable.addRowSelectionInterval(selectedRow.get(i), selectedRow.get(i));
        }
    }

    @Override
    public void setProgramText(String codeSource) {
        codeEditorTextPanel.setText(codeSource);
    }

    @Override
    public void setTranslationResultText(String translationResult) {
        translateResultTextPanel.setText(translationResult);
    }

    @Override
    public void setConsoleOutData(String consoleOutData) {
        consoleOutputTextPanel.setText(consoleOutData);
    }

    @Override
    public void setConsoleInData(String consoleInData) {
        consoleInputTextPanel.setText(consoleInData);
    }

    @Override
    public void setPermissionForAction(int mode) {
        switch (mode) {
            case MainPresenter.DEFAULT_MODE: {
                setPermissionForAction_DefaultMode();
                break;
            }
            case MainPresenter.RUN_MODE: {
                setPermissionForAction_RunMode();
                break;
            }
            case MainPresenter.IO_MODE: {
                setPermissionForAction_IOMode();
                break;
            }
            default: break;
        }
    }

    @Override
    public int consoleIn() {
        consoleInputTextPanel.setEditable(true);
        consoleInputTextPanel.requestFocus();
        while (true) {
            if (inputString != null) {
                int value = otherRadix2Dec(inputString);
                if (value >= 0 && value < 256) {
                    inputString = null;
                    return value;
                }
            }

            try {
                Thread.sleep(250);
            } catch (InterruptedException ignored) {}
        }
    }

    // Helps
    private void setPermissionForAction_DefaultMode() {

        consoleInputTextPanel.setEditable(false);
        consoleInputTextPanel.setBackground(Color.WHITE);

        fileMenu.setEnabled(true);
        openItem.setEnabled(true);
        saveItem.setEnabled(true);
        exitItem.setEnabled(true);

        emulatorMenu.setEnabled(true);
        translationItem.setEnabled(true);
        stepItem.setEnabled(true);
        runItem.setEnabled(true);
        resetMemoryItem.setEnabled(true);
        resetRegisterItem.setEnabled(true);
        clearScreensItem.setEnabled(true);
        deleteAllBreakpointsItem.setEnabled(true);

        helpMenu.setEnabled(true);
        helpItem.setEnabled(true);
        aboutItem.setEnabled(true);

        stopItem.setEnabled(false);
    }

    private void setPermissionForAction_RunMode() {

        consoleInputTextPanel.setEditable(false);
        consoleInputTextPanel.setBackground(Color.WHITE);

        openItem.setEnabled(false);
        saveItem.setEnabled(false);
        exitItem.setEnabled(true);

        emulatorMenu.setEnabled(true);
        translationItem.setEnabled(false);
        stepItem.setEnabled(false);
        runItem.setEnabled(false);
        resetMemoryItem.setEnabled(false);
        resetRegisterItem.setEnabled(false);
        clearScreensItem.setEnabled(false);
        deleteAllBreakpointsItem.setEnabled(false);

        helpMenu.setEnabled(false);
        helpItem.setEnabled(false);
        aboutItem.setEnabled(false);

        stopItem.setEnabled(true);
    }

    private void setPermissionForAction_IOMode() {

        consoleInputTextPanel.setEditable(true);
        consoleInputTextPanel.setBackground(MainWindow.selectedColor);

        openItem.setEnabled(false);
        saveItem.setEnabled(false);
        exitItem.setEnabled(true);

        emulatorMenu.setEnabled(false);
        translationItem.setEnabled(false);
        stepItem.setEnabled(false);
        runItem.setEnabled(false);
        resetMemoryItem.setEnabled(false);
        resetRegisterItem.setEnabled(false);
        clearScreensItem.setEnabled(false);
        deleteAllBreakpointsItem.setEnabled(false);

        helpMenu.setEnabled(false);
        helpItem.setEnabled(false);
        aboutItem.setEnabled(false);

        stopItem.setEnabled(false);
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
}