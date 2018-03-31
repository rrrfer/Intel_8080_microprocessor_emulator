package view;

import presenter.IMainPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainWindow extends JFrame implements IMainView {

    // Presenter
    private IMainPresenter presenter;

    // View components
    private JPanel rootPanel;
    private JTable memoryTable;
    private JTable registersAndFlagsTable;
    private JTextField addressTextField;
    private JTextField cmdLoadTextField;
    private JLabel inputFromPortLabel;

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
        cmdLoadTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_F1: {
                        help();
                        break;
                    }
                    case KeyEvent.VK_F2: {
                        showCodeEditor();
                        break;
                    }
                    case KeyEvent.VK_F5: {
                        run();
                        break;
                    }
                    case KeyEvent.VK_F9: {
                        step();
                        break;
                    }
                    case KeyEvent.VK_F12: {
                        stop();
                        break;
                    }
                }
            }
        });
    }

    private void createMenuBar() {
        JMenuItem openItem = new JMenuItem("Open...");
        openItem.setFont(menuBarFont);
        JMenuItem saveItem = new JMenuItem("Save...");
        saveItem.setFont(menuBarFont);
        JMenuItem exitItem = new JMenuItem("Exit");
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

        JMenu fileMenu = new JMenu("File");
        fileMenu.setFont(menuBarFont);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenuItem showCodeEditor = new JMenuItem("Show code editor (F2)");
        showCodeEditor.setFont(menuBarFont);
        JMenuItem runItem = new JMenuItem("Run              (F5)");
        runItem.setFont(menuBarFont);
        JMenuItem stepItem = new JMenuItem("Step             (F9)");
        stepItem.setFont(menuBarFont);
        JMenuItem stopItem = new JMenuItem("Stop             (F12)");
        stopItem.setFont(menuBarFont);
        JMenuItem resetRegisterItem = new JMenuItem("Reset registers");
        resetRegisterItem.setFont(menuBarFont);
        JMenuItem resetMemoryItem = new JMenuItem("Reset memory");
        resetMemoryItem.setFont(menuBarFont);
        JMenuItem clearScreensItem = new JMenuItem("Clear screens");
        clearScreensItem.setFont(menuBarFont);

        showCodeEditor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCodeEditor();
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

        JMenu emulatorMenu = new JMenu("Emulator");
        emulatorMenu.setFont(menuBarFont);
        emulatorMenu.add(showCodeEditor);
        emulatorMenu.addSeparator();
        emulatorMenu.add(runItem);
        emulatorMenu.add(stepItem);
        emulatorMenu.add(stopItem);
        emulatorMenu.addSeparator();
        emulatorMenu.add(resetRegisterItem);
        emulatorMenu.add(resetMemoryItem);
        emulatorMenu.addSeparator();
        emulatorMenu.add(clearScreensItem);

        JMenuItem helpItem = new JMenuItem("Help (F1)");
        helpItem.setFont(menuBarFont);
        JMenuItem aboutItem = new JMenuItem("About...");
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

        JMenu helpMenu = new JMenu("Help");
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
        registersAndFlagsTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        registersAndFlagsTable.setEnabled(false);
        registersAndFlagsTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        registersAndFlagsTable.setDefaultRenderer(registersAndFlagsTable.getColumnClass(1),
                new RegistersAndFlagsTableCellRenderer());
    }

    private void settingUI() {
        cmdLoadTextField.setEditable(false);
        registersAndFlagsTable.setFocusable(false);
        memoryTable.setFocusable(false);
    }

    // Actions
    private void open() {
        presenter.open("test.i8080");
    }

    private void save() {
        presenter.save("test.8080");
    }

    private void exit() {
        System.exit(1);
    }

    private void showCodeEditor() {
        System.out.println("Action: show code editor");
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
        System.out.println("Action: help");
    }

    private void about() {
        System.out.println("Action: about");
    }

    @Override
    public void updateCodeEditor(String programText) {
        // TODO
    }

    @Override
    public void updateMemoryTable(String[][] dataSource) {
        for (int i = 0; i < dataSourceForMemoryTable.length; ++i) {
            for (int j = 0; j < dataSourceForMemoryTable[i].length; ++j) {
                dataSourceForMemoryTable[i][j] = dataSource[i][j];
            }
        }
        if (memoryTableModel != null) {
            memoryTableModel.fireTableDataChanged();
        }
    }

    @Override
    public void updateRegistersAndFlagsTable(String[][] dataSource) {
        for (int i = 0; i < dataSourceForRegistersAndFlagsTable.length; ++i) {
            for (int j = 0; j < dataSourceForRegistersAndFlagsTable[i].length; ++j) {
                dataSourceForRegistersAndFlagsTable[i][j] = dataSource[i][j];
            }
        }
        if (registersAndFlagsTableModel != null) {
            registersAndFlagsTableModel.fireTableDataChanged();
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
}