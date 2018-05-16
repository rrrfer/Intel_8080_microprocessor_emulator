package view;

import com.sun.istack.internal.NotNull;
import model.emulator.IExternalPeripheral;
import presenter.IMainPresenter;
import presenter.MainPresenter;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.PlainDocument;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class MainWindow extends JFrame implements IMainView {

    // Presenter
    private IMainPresenter presenter;

    // View components
    private ScreensWindow screensWindow;
    private AboutWindow aboutWindow;
    private HelpWindow helpWindow;
    private IScreenView pixelScreenView;
    private IScreenView characterScreenView;
    private JFrame cellEditorFrame;
    private JButton okButton;
    private int rowIndex;

    private JPanel rootPanel;
    private JTable memoryTable;
    private JTable registersAndFlagsTable;
    private JTable label2AddressTable;
    private JTabbedPane emulatorTabbedPanel;
    private JEditorPane codeEditorTextPanel;
    private JEditorPane translationResultTextPanel;
    private JScrollPane memoryTableScrollPanel;
    private JEditorPane consoleOutputTextPanel;
    private JTextField consoleInputTextPanel;
    private JTable externalPeripheralTable;

    // JMenuBar
    private JMenuBar menuBar;

    private JMenu fileMenu;
    private JMenuItem openItem;
    private JMenuItem saveItem;
    private JMenuItem saveAsItem;
    private JMenuItem exitItem;

    private JMenu emulatorMenu;
    private JMenuItem translationItem;
    private JMenuItem runItem;
    private JMenuItem stepItem;
    private JMenuItem stopItem;
    private JMenuItem resetRegisterItem;
    private JMenuItem resetMemoryItem;
    private JMenuItem showHideScreens;
    private JMenuItem switchTabItem;
    private JMenuItem deleteAllBreakpointsItem;

    private JMenu helpMenu;
    private JMenuItem helpItem;
    private JMenuItem aboutItem;

    // Data model components
    private MemoryTableModel memoryTableModel;
    private RegistersAndFlagsTableModel registersAndFlagsTableModel;
    private Label2AddressTableModel label2AddressTableModel;
    private ExternalPeripheralTableModel externalPeripheralTableModel;

    // Input
    private String inputString;

    // Breakpoints
    static int[] breakpoints = new int[65536];
    static int selectedRow = 0;

    // Resources
    static final Font mainFont = new Font(Font.MONOSPACED, Font.PLAIN, 12);
    static final Color greenColor = new Color(44, 192, 8);
    static final Color redColor = new Color(122, 0, 6);

    private UndoManager undoManager;

    private ArrayList<IExternalPeripheral> externalPeripherals;

    public MainWindow(@NotNull IMainPresenter presenter, @NotNull String[][] dataSourceForMemoryTable,
                      @NotNull String[][] dataSourceForRegisterTable, @NotNull int[][] dataSourceForPixelScreen,
                      @NotNull int[][] dataSourceForCharacterScreen_Color,
                      @NotNull int[][] dataSourceForCharacterScreen_Character,
                      @NotNull ArrayList<String> dataSourceForLabel2AddressTable,
                      @NotNull ArrayList<IExternalPeripheral> externalPeripherals) {
        this.presenter = presenter;

        setTitle("Intel 8080 Emulator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createUI(dataSourceForMemoryTable, dataSourceForRegisterTable, dataSourceForPixelScreen,
                dataSourceForCharacterScreen_Color, dataSourceForCharacterScreen_Character,
                dataSourceForLabel2AddressTable, externalPeripherals);

        this.externalPeripherals = externalPeripherals;

        setContentPane(rootPanel);

        pack();
        setResizable(false);

        setLocationRelativeTo(null);

        setVisible(true);
    }

    // Create&Setting GUI
    private void createUI(String[][] dataSourceForMemoryTable,
                          String[][] dataSourceForRegisterTable, int[][] dataSourceForPixelScreen,
                          int[][] dataSourceForCharacterScreen_Color,
                          int[][] dataSourceForCharacterScreen_Character,
                          ArrayList<String> dataSourceForLabel2AddressTable,
                          ArrayList<IExternalPeripheral> externalPeripherals) {

        createMenuBar();
        createMemoryTable(dataSourceForMemoryTable);
        createRegistersAndFlagsTable(dataSourceForRegisterTable);

        createLabel2AddressTable(dataSourceForLabel2AddressTable);

        createExternalPeripheralTable(externalPeripherals);

        createScreens(dataSourceForPixelScreen,
                dataSourceForCharacterScreen_Color,
                dataSourceForCharacterScreen_Character);

        createAboutWindow();
        createHelpWindow();

        createCellEditWindow();

        settingUI();
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
                        }
                        break;
                    }
                    case KeyEvent.VK_F3: {
                        if (showHideScreens.isEnabled()) {
                            showHideScreens();
                        }
                        break;
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
                        if (consoleInputTextPanel.isEditable() &&
                                !consoleInputTextPanel.getText().isEmpty()) {
                            inputString = consoleInputTextPanel.getText();
                        }
                        break;
                    }
                    case KeyEvent.VK_O: {
                        if (openItem.isEnabled()) {
                            if (e.isControlDown()) {
                                open();
                            }
                        }
                        break;
                    }
                    case KeyEvent.VK_S: {
                        if (saveItem.isEnabled()
                                && e.isControlDown()
                                && !e.isAltDown()) {
                            save();
                        }

                        if (saveAsItem.isEnabled()
                                && e.isControlDown()
                                && e.isAltDown()) {
                            saveAs();
                        }
                        break;
                    }
                    case KeyEvent.VK_SPACE: {
                        if (switchTabItem.isEnabled() && e.isControlDown()) {
                            switchTab();
                        }
                    }
                }
            }
        });

        codeEditorTextPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F1) {
                    if (helpItem.isEnabled()) {
                        help();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_F2) {
                    if (translationItem.isEnabled()) {
                        translation();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_O) {
                    if (openItem.isEnabled()) {
                        if (e.isControlDown()) {
                            open();
                        }
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    if (saveItem.isEnabled() && !e.isAltDown() && e.isControlDown()) {
                        save();
                    }
                    if (saveAsItem.isEnabled() && e.isAltDown() && e.isControlDown()) {
                        saveAs();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (switchTabItem.isEnabled() && e.isControlDown()) {
                        switchTab();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_F3) {
                    if (showHideScreens.isEnabled()) {
                        showHideScreens();
                    }
                }
                if (e.isControlDown()) {
                    if (e.getKeyCode() == KeyEvent.VK_Z) {
                        if (undoManager.canUndo()) {
                            undoManager.undo();
                        }
                    }
                    if (e.getKeyCode() == KeyEvent.VK_Y) {
                        if (undoManager.canRedo()) {
                            undoManager.redo();
                        }
                    }
                }
            }
        });

        codeEditorTextPanel.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                editable();
                translationResultTextPanel.setForeground(Color.BLACK);
                translationResultTextPanel.setBackground(Color.ORANGE);
            }
        });

        codeEditorTextPanel.getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                undoManager.addEdit(e.getEdit());
            }
        });

        memoryTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (memoryTable.isEnabled()) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        presenter.setProgramCounter(memoryTable.getSelectedRow());
                    } else if (e.getButton() == MouseEvent.BUTTON3) {
                        int row = memoryTable.rowAtPoint(e.getPoint());
                        memoryTable.setRowSelectionInterval(row, row);
                        presenter.setBreakpoint(memoryTable.getSelectedRow());
                    }
                }
            }
        });

        externalPeripheralTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (openItem.isEnabled()) {
                        rowIndex = externalPeripheralTable.rowAtPoint(e.getPoint());
                        cellEditorFrame.setVisible(true);
                    }
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

        saveAsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAs();
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

        switchTabItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchTab();
            }
        });

        showHideScreens.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHideScreens();
            }
        });

        deleteAllBreakpointsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAllBreakpoints();
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
        openItem = new JMenuItem("Open...        CTRL+O");
        openItem.setFont(mainFont);
        saveItem = new JMenuItem("Save...        CTRL+S");
        saveItem.setFont(mainFont);
        saveAsItem = new JMenuItem("Save As... CTRL+ALT+S");
        saveAsItem.setFont(mainFont);
        exitItem = new JMenuItem("Exit");
        exitItem.setFont(mainFont);

        fileMenu = new JMenu("File");
        fileMenu.setFont(mainFont);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        translationItem = new JMenuItem("Translation                F2");
        translationItem.setFont(mainFont);
        runItem = new JMenuItem("Run                        F5");
        runItem.setFont(mainFont);
        stepItem = new JMenuItem("Step                       F9");
        stepItem.setFont(mainFont);
        stopItem =                 new JMenuItem("Stop                      F12");
        stopItem.setFont(mainFont);
        resetRegisterItem = new JMenuItem("Reset Registers           Esc");
        resetRegisterItem.setFont(mainFont);
        resetMemoryItem = new JMenuItem("Reset Memory");
        resetMemoryItem.setFont(mainFont);
        showHideScreens = new JMenuItem  ("Show/Hide Screens          F3");
        showHideScreens.setFont(mainFont);
        switchTabItem = new JMenuItem("Switch tabs        CTRL+SPACE");
        switchTabItem.setFont(mainFont);
        deleteAllBreakpointsItem = new JMenuItem("Delete All Breakpoints");
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
        emulatorMenu.add(switchTabItem);
        emulatorMenu.add(showHideScreens);
        emulatorMenu.addSeparator();
        emulatorMenu.add(deleteAllBreakpointsItem);

        helpItem = new JMenuItem("Help      F1");
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

    private void createMemoryTable(String[][] dataSourceForMemoryTable) {

        memoryTableModel = new MemoryTableModel(dataSourceForMemoryTable);
        memoryTable.setModel(memoryTableModel);
        memoryTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        memoryTable.setEnabled(false);
        memoryTable.setFocusable(false);
        memoryTable.setDefaultRenderer(memoryTable.getColumnClass(1), new MemoryTableCellRenderer());
        memoryTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        memoryTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        memoryTable.getColumnModel().getColumn(2).setPreferredWidth(20);
    }

    private void createRegistersAndFlagsTable(String[][] dataSourceForRegisterTable) {

        registersAndFlagsTableModel = new RegistersAndFlagsTableModel(dataSourceForRegisterTable);
        registersAndFlagsTable.setModel(registersAndFlagsTableModel);
        registersAndFlagsTable.setFocusable(false);
        registersAndFlagsTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        registersAndFlagsTable.setEnabled(false);
        registersAndFlagsTable.setDefaultRenderer(registersAndFlagsTable.getColumnClass(1),
                new RegistersAndFlagsTableCellRenderer());
        registersAndFlagsTable.getColumnModel().getColumn(0).setPreferredWidth(90);
    }

    private void createLabel2AddressTable(ArrayList<String> dataSourceForLabel2AddressTable) {
        label2AddressTableModel = new Label2AddressTableModel(dataSourceForLabel2AddressTable);
        label2AddressTable.setModel(label2AddressTableModel);
        label2AddressTable.setFocusable(false);
        label2AddressTable.setEnabled(false);
    }

    private void createExternalPeripheralTable(ArrayList<IExternalPeripheral> externalPeripherals) {
        externalPeripheralTableModel = new ExternalPeripheralTableModel(externalPeripherals);
        externalPeripheralTable.setModel(externalPeripheralTableModel);
        externalPeripheralTable.setDefaultRenderer(externalPeripheralTable.getColumnClass(1),
                new ExternalPeripheralTableCellRenderer());
        externalPeripheralTable.setFocusable(false);
        externalPeripheralTable.getColumnModel().getColumn(0).setMaxWidth(50);
        externalPeripheralTable.getColumnModel().getColumn(2).setMaxWidth(75);
    }

    private void createScreens(int[][] dataSourceForPixelScreen, int[][] dataSourceForCharacterScreen_Color,
                               int[][] dataSourceForCharacterScreen_Character) {

        pixelScreenView = new PixelScreenView(256, 256, 1);
        characterScreenView = new CharacterScreenView(240, 240, 12);

        pixelScreenView.setColorData(dataSourceForPixelScreen);
        characterScreenView.setColorData(dataSourceForCharacterScreen_Color);
        characterScreenView.setCharData(dataSourceForCharacterScreen_Character);

        screensWindow = new ScreensWindow((PixelScreenView) pixelScreenView,
                (CharacterScreenView) characterScreenView, this);
    }

    private void createAboutWindow() {
        aboutWindow = new AboutWindow();
    }

    private void createHelpWindow() {
        helpWindow = new HelpWindow();
    }

    private void createCellEditWindow() {
        JTextField textField = new JTextField(5);
        JButton okButton = new JButton("Ok");
        cellEditorFrame = new JFrame();
        cellEditorFrame.setTitle("Port setting");
        cellEditorFrame.setLayout(new FlowLayout());
        cellEditorFrame.setSize(250, 68);
        cellEditorFrame.setResizable(false);
        cellEditorFrame.add(textField);
        cellEditorFrame.add(okButton);
        cellEditorFrame.setAlwaysOnTop(true);
        cellEditorFrame.setLocationRelativeTo(MainWindow.this);

        okButton.setFocusable(false);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int value = Integer.valueOf(textField.getText());
                    if (value >= 0 && value < 256) {
                        externalPeripherals.get(rowIndex).setPort(value);
                        externalPeripheralTableModel.fireTableDataChanged();
                        cellEditorFrame.setVisible(false);
                        textField.setText("");
                    }
                } catch (NumberFormatException ignored) {}
            }
        });
    }

    private void settingUI() {

        consoleInputTextPanel.setEditable(false);

        consoleOutputTextPanel.setFocusable(false);
        registersAndFlagsTable.setFocusable(false);
        memoryTable.setFocusable(false);
        emulatorTabbedPanel.setFocusable(false);
        menuBar.setFocusable(false);
        fileMenu.setFocusable(false);
        emulatorMenu.setFocusable(false);
        helpMenu.setFocusable(false);
        openItem.setFocusable(false);
        saveItem.setFocusable(false);
        saveAsItem.setFocusable(false);
        exitItem.setFocusable(false);
        translationItem.setFocusable(false);
        stepItem.setFocusable(false);
        runItem.setFocusable(false);
        stopItem.setFocusable(false);
        resetMemoryItem.setFocusable(false);
        resetRegisterItem.setFocusable(false);
        switchTabItem.setFocusable(false);
        showHideScreens.setFocusable(false);
        deleteAllBreakpointsItem.setFocusable(false);
        helpItem.setFocusable(false);
        aboutItem.setFocusable(false);

        stopItem.setEnabled(false);

        codeEditorTextPanel.getDocument().putProperty(PlainDocument.tabSizeAttribute, 2);
        codeEditorTextPanel.setFont(mainFont);

        this.undoManager = new UndoManager();
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
                undoManager.discardAllEdits();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(MainWindow.this,
                        "Opening failed", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void save() {
        try {
            String programText = codeEditorTextPanel.getText();
            if (!presenter.saveProgramInFile(programText)) {
                saveAs();
            }
        } catch (IOException ignored) {
            JOptionPane.showMessageDialog(MainWindow.this,
                    "Saving failed", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveAs() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("i8080", "i8080"));
        int result = fileChooser.showSaveDialog(MainWindow.this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                String path = fileChooser.getSelectedFile().getPath();
                String programText = codeEditorTextPanel.getText();
                presenter.saveAsProgramInFile(path, programText);
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

    private void externalPeripheral() {
        System.out.println("Click!");
    }

    private void switchTab() {
        int currentTab = emulatorTabbedPanel.getSelectedIndex();
        int size = emulatorTabbedPanel.getTabCount();
        emulatorTabbedPanel.setSelectedIndex((currentTab + 1) % size);
        if (emulatorTabbedPanel.getSelectedIndex() == 0) {
            consoleInputTextPanel.requestFocus();
        } else {
            codeEditorTextPanel.requestFocus();
        }
    }

    private void showHideScreens() {
        if (screensWindow.isVisible()) {
            screensWindow._hide();
        } else {
            screensWindow._show();
        }
    }

    private void editable() {
        if (!getTitle().equals("Intel 8080 Emulator")) {
            if (getTitle().charAt(getTitle().length() - 1) != '*') {
                setTitle(getTitle() + "*");
            }
        }
    }

    private void removeAllBreakpoints() {
        presenter.removeAllBreakpoints();
    }

    private void help() {
        helpWindow.setVisible(!helpWindow.isVisible());
    }

    private void about() {
        aboutWindow.setVisible(!aboutWindow.isVisible());
    }

    // IMainView
    @Override
    public void setProgramCounterPosition(int programCounterPosition, boolean isScroll) {
        selectedRow = programCounterPosition;

        if (isScroll) {
            int tableSize = memoryTable.getRowCount();
            int scrollMax = memoryTableScrollPanel.getVerticalScrollBar().getMaximum();
            if (programCounterPosition > 10) {
                memoryTableScrollPanel.getVerticalScrollBar().setValue(scrollMax /
                        tableSize * (programCounterPosition - 10));
            } else {
                memoryTableScrollPanel.getVerticalScrollBar().setValue(0);
            }
        }

        if (programCounterPosition < 30) {
            memoryTableModel.fireTableRowsUpdated(0, programCounterPosition + 30);
        } else {
            memoryTableModel.fireTableRowsUpdated(programCounterPosition - 30, programCounterPosition + 30);
        }
    }

    @Override
    public void setBreakpoints(int[] breakpointsData) {
        breakpoints = breakpointsData;
        memoryTableModel.fireTableDataChanged();
    }

    @Override
    public void setProgramText(String codeSource) {
        codeEditorTextPanel.setText(codeSource);
    }

    @Override
    public void setTranslationResult(String translationResult, boolean hasTranslationErrors) {
        translationResultTextPanel.setText(translationResult);

        // TODO Поковыряться тут
        if (hasTranslationErrors) {
            // Помощь в поиске ошибки в программе
            String rowNumberStr = translationResult
                    .split(System.lineSeparator())[1]
                    .split(" ")[4];
            int errorRowNumber = Integer.valueOf(rowNumberStr) - 1;
            String errorString = codeEditorTextPanel.getText()
                    .split(System.lineSeparator())[errorRowNumber];

            int startErrorIndex = 0;
            String[] programRows = codeEditorTextPanel.getText()
                    .split(System.lineSeparator());
            for (int i = 0; i < errorRowNumber; ++i) {
                startErrorIndex += programRows[i].length() + 1;
            }

            codeEditorTextPanel
                    .select(startErrorIndex, startErrorIndex + errorString.length());
            translationResultTextPanel.setBackground(redColor);
            translationResultTextPanel.setForeground(Color.WHITE);
        } else {
            translationResultTextPanel.setBackground(greenColor);
            translationResultTextPanel.setForeground(Color.BLACK);
        }
    }

    @Override
    public void setConsoleOutputData(String consoleOutData) {
        consoleOutputTextPanel.setText(consoleOutData);
    }

    @Override
    public void clearInputConsole() {
        consoleInputTextPanel.setText("");
    }

    @Override
    public void setPermissionForActions(int mode) {
        switch (mode) {
            case MainPresenter.DEFAULT_ACTION_MODE: {
                setPermissionForAction_DefaultMode();
                break;
            }
            case MainPresenter.RUN_ACTION_MODE: {
                setPermissionForAction_RunMode();
                break;
            }
            case MainPresenter.IO_ACTION_MODE: {
                setPermissionForAction_IOMode();
                break;
            }
            default: break;
        }
    }

    @Override
    public void setNameEditedFileInTitle(String filename) {
        setTitle("Intel 8080 Emulator: " + filename);
    }

    @Override
    public int requestOfInput() {
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

    @Override
    public void memoryTableUpdate() {
        memoryTableModel.fireTableDataChanged();
    }

    @Override
    public void registersTableUpdate() {
        registersAndFlagsTableModel.fireTableDataChanged();
    }

    @Override
    public void label2AddressTableUpdate() {
        label2AddressTableModel.fireTableDataChanged();
    }

    @Override
    public void externalPeripheralUpdate() {
        externalPeripheralTableModel.fireTableDataChanged();
    }

    @Override
    public void pixelScreenUpdate() {
        pixelScreenView.update();
    }

    @Override
    public void characterScreenUpdate() {
        characterScreenView.update();
    }

    private void setPermissionForAction_DefaultMode() {

        emulatorTabbedPanel.setSelectedIndex(0);
        emulatorTabbedPanel.setEnabledAt(1, true);
        consoleInputTextPanel.requestFocus();

        consoleInputTextPanel.setEditable(false);
        consoleInputTextPanel.setBackground(Color.WHITE);

        memoryTable.setEnabled(true);

        fileMenu.setEnabled(true);
        openItem.setEnabled(true);
        saveItem.setEnabled(true);
        saveAsItem.setEnabled(true);
        exitItem.setEnabled(true);

        emulatorMenu.setEnabled(true);
        translationItem.setEnabled(true);
        stepItem.setEnabled(true);
        runItem.setEnabled(true);
        resetMemoryItem.setEnabled(true);
        resetRegisterItem.setEnabled(true);
        switchTabItem.setEnabled(true);
        showHideScreens.setEnabled(true);
        deleteAllBreakpointsItem.setEnabled(true);

        helpMenu.setEnabled(true);
        helpItem.setEnabled(true);
        aboutItem.setEnabled(true);

        stopItem.setEnabled(false);
    }

    private void setPermissionForAction_RunMode() {

        emulatorTabbedPanel.setSelectedIndex(0);
        emulatorTabbedPanel.setEnabledAt(1, false);
        consoleInputTextPanel.requestFocus();

        memoryTable.setEnabled(false);

        consoleInputTextPanel.setEditable(false);
        consoleInputTextPanel.setBackground(Color.WHITE);

        openItem.setEnabled(false);
        saveItem.setEnabled(false);
        saveAsItem.setEnabled(false);
        exitItem.setEnabled(true);

        emulatorMenu.setEnabled(true);
        translationItem.setEnabled(false);
        stepItem.setEnabled(false);
        runItem.setEnabled(false);
        resetMemoryItem.setEnabled(false);
        resetRegisterItem.setEnabled(false);
        switchTabItem.setEnabled(false);
        showHideScreens.setEnabled(true);
        deleteAllBreakpointsItem.setEnabled(false);

        helpMenu.setEnabled(false);
        helpItem.setEnabled(false);
        aboutItem.setEnabled(false);

        stopItem.setEnabled(true);
    }

    private void setPermissionForAction_IOMode() {

        emulatorTabbedPanel.setSelectedIndex(0);
        emulatorTabbedPanel.setEnabledAt(1, false);
        consoleInputTextPanel.requestFocus();

        memoryTable.setEnabled(false);

        consoleInputTextPanel.setEditable(true);
        consoleInputTextPanel.setBackground(MainWindow.greenColor);

        openItem.setEnabled(false);
        saveItem.setEnabled(false);
        saveAsItem.setEnabled(false);
        exitItem.setEnabled(true);

        emulatorMenu.setEnabled(true);
        translationItem.setEnabled(false);
        stepItem.setEnabled(false);
        runItem.setEnabled(false);
        stopItem.setEnabled(true);
        resetMemoryItem.setEnabled(false);
        resetRegisterItem.setEnabled(false);
        switchTabItem.setEnabled(false);
        showHideScreens.setEnabled(false);
        deleteAllBreakpointsItem.setEnabled(false);

        helpMenu.setEnabled(false);
        helpItem.setEnabled(false);
        aboutItem.setEnabled(false);
    }

    // TODO Поковыряться тут
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}