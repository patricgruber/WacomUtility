package ui;

import general.Settings;
import java.awt.Color;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import wrapper.Tablet;
import wrapper.TerminalWrapper;
import wrapper.WrapperException;

public class MainWindow extends javax.swing.JFrame {
    
    private final List<JPanel> panels;
    
    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        
        settingsErrorLabel.setVisible(false);
        
        panels = Arrays.asList(settingsPanel, welcomePanel);
        hideAllPanels();
        welcomePanel.setVisible(true);
        Settings.init();
        Settings.load();
        
        initTabletNames();
        
        refreshSettings();
    }
    
    private String selectedTablet() {
        return (String)tabletNameCombo.getSelectedItem();
    }
    
    private void initTabletNames() {
        tabletNameCombo.removeAllItems();
        
        try {
            TerminalWrapper.getAllTabletNames()
                    .forEach(tabletName -> tabletNameCombo.addItem(tabletName));
        } catch (WrapperException e) {
            System.err.println(e.getMessage());
            return;
        }
        
        if (tabletNameCombo.getItemCount() == 0) {
            settingsErrorLabel.setText("Not tablets found!");
            settingsErrorLabel.setVisible(true);
            rawSampleSL.setEnabled(false);
            rawSampleTxt.setEnabled(false);
            suppressSL.setEnabled(false);
            suppressTxt.setEnabled(false);
            touchCB.setEnabled(false);
            areaX.setEnabled(false);
            areaY.setEnabled(false);
            areaWidth.setEnabled(false);
            areaHeight.setEnabled(false);
            mappedX.setEnabled(false);
            mappedY.setEnabled(false);
            mappedWidth.setEnabled(false);
            mappedHeight.setEnabled(false);
            customPropertyAdd.setEnabled(false);
            customPropertyTable.setEnabled(false);
            settingsSaveBtn.setEnabled(false);
            settingsApplyBtn.setEnabled(false);
            settingsLoadBtn.setEnabled(false);
        }
    }
    
    private void hideAllPanels() {
        panels.forEach(panel -> panel.setVisible(false));
    }
    
    private void refreshSettings() {
        rawSampleSL.setValue(Integer.parseInt(Settings.get("RawSample")));
        suppressSL.setValue(Integer.parseInt(Settings.get("Suppress")));
        touchCB.setSelected(Boolean.parseBoolean(Settings.get("Touch")));
        tabletNameCombo.setSelectedItem(Settings.get("TabletName"));

        areaX.setText(Settings.get("AreaXOffset"));
        areaY.setText(Settings.get("AreaYOffset"));
        areaWidth.setText(Settings.get("AreaWidth"));
        areaHeight.setText(Settings.get("AreaHeight"));
        
        mappedX.setText(Settings.get("MappedXOffset"));
        mappedY.setText(Settings.get("MappedYOffset"));
        mappedWidth.setText(Settings.get("MappedWidth"));
        mappedHeight.setText(Settings.get("MappedHeight"));

        /*final Set<String> customProperties = Settings.getCustomProperties();
        final DefaultTableModel tm = (DefaultTableModel)customPropertyTable.getModel();
        while (tm.getRowCount()>0) tm.removeRow(0);

        customProperties.forEach(prop -> 
                tm.addRow(new String[]{prop, Settings.get(prop)}));*/
    }
    
    private void setSettings() {
        Settings.set("TabletName", (String)tabletNameCombo.getSelectedItem());
        Settings.set("RawSample", rawSampleSL.getValue()+"");
        Settings.set("Suppress", suppressSL.getValue()+"");
        Settings.set("Touch", touchCB.isSelected()?"on":"off");
        
        Settings.set("AreaXOffset", areaX.getText());
        Settings.set("AreaYOffset", areaY.getText());
        Settings.set("AreaWidth", areaWidth.getText());
        Settings.set("AreaHeight", areaHeight.getText());
        
        Settings.set("MappedXOffset", mappedX.getText());
        Settings.set("MappedYOffset", mappedY.getText());
        Settings.set("MappedWidth", mappedWidth.getText());
        Settings.set("MappedHeight", mappedHeight.getText());
        
        /*final DefaultTableModel tm = (DefaultTableModel)customPropertyTable.getModel();        
        for (int i=0;i<tm.getRowCount();i++) {
            String key = (String)tm.getValueAt(i, 0);
            String val = (String)tm.getValueAt(i, 1);
            if (key.length() == 0 || val.length() == 0) continue;
            Settings.set(key, val);
        }*/
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        settingsPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rawSampleSL = new javax.swing.JSlider();
        suppressSL = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        touchCB = new javax.swing.JCheckBox();
        settingsSaveBtn = new javax.swing.JButton();
        settingsLoadBtn = new javax.swing.JButton();
        settingsApplyBtn = new javax.swing.JButton();
        tabletNameCombo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        settingsErrorLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        areaWidth = new javax.swing.JTextField();
        areaHeight = new javax.swing.JTextField();
        areaX = new javax.swing.JTextField();
        areaY = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        mappedWidth = new javax.swing.JTextField();
        mappedHeight = new javax.swing.JTextField();
        mappedX = new javax.swing.JTextField();
        mappedY = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        rawSampleTxt = new javax.swing.JTextField();
        suppressTxt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        customPropertyTable = new javax.swing.JTable();
        customPropertyAdd = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        welcomePanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        welcomeMenuItem = new javax.swing.JMenuItem();
        settingsMenuItem = new javax.swing.JMenuItem();
        helpMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        settingsPanel.setFont(settingsPanel.getFont().deriveFont((settingsPanel.getFont().getStyle() & ~java.awt.Font.ITALIC) & ~java.awt.Font.BOLD, 12));

        jLabel1.setText("RawSample");
        jLabel1.setToolTipText("Number of raw data used to filter the points");

        rawSampleSL.setMaximum(20);
        rawSampleSL.setMinimum(1);
        rawSampleSL.setValue(4);
        rawSampleSL.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rawSampleSLStateChanged(evt);
            }
        });

        suppressSL.setMaximum(20);
        suppressSL.setValue(2);
        suppressSL.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                suppressSLStateChanged(evt);
            }
        });

        jLabel2.setText("Suppress");
        jLabel2.setToolTipText("Number of points trimmed");

        touchCB.setSelected(true);
        touchCB.setText("Touch Events");
        touchCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                touchCBActionPerformed(evt);
            }
        });

        settingsSaveBtn.setText("Save");
        settingsSaveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsSaveBtnActionPerformed(evt);
            }
        });

        settingsLoadBtn.setText("Load");
        settingsLoadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsLoadBtnActionPerformed(evt);
            }
        });

        settingsApplyBtn.setText("Apply");
        settingsApplyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsApplyBtnActionPerformed(evt);
            }
        });

        jLabel7.setText("Select tablet:");

        settingsErrorLabel.setFont(new java.awt.Font("Noto Sans", 1, 15)); // NOI18N
        settingsErrorLabel.setForeground(new java.awt.Color(255, 27, 0));
        settingsErrorLabel.setText("No tablets found!");

        jLabel8.setText("Area");
        jLabel8.setToolTipText("The tablet area");

        areaWidth.setToolTipText("Width");
        areaWidth.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldKeyPressed(evt);
            }
        });

        areaHeight.setToolTipText("Height");
        areaHeight.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldKeyPressed(evt);
            }
        });

        areaX.setToolTipText("top left x");
        areaX.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldKeyPressed(evt);
            }
        });

        areaY.setToolTipText("top left y");
        areaY.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldKeyPressed(evt);
            }
        });

        jLabel9.setText("MapTo");
        jLabel9.setToolTipText("The box on the screen the tablet area should be mapped to");

        mappedWidth.setToolTipText("Width");
        mappedWidth.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldKeyPressed(evt);
            }
        });

        mappedHeight.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldKeyPressed(evt);
            }
        });

        mappedX.setToolTipText("top left x");
        mappedX.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldKeyPressed(evt);
            }
        });

        mappedY.setToolTipText("top left y");
        mappedY.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldKeyPressed(evt);
            }
        });

        jLabel10.setText("in tablet coordinates");

        jLabel11.setText("in px");

        rawSampleTxt.setText("4");
        rawSampleTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rawSampleTxtActionPerformed(evt);
            }
        });

        suppressTxt.setText("2");
        suppressTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suppressTxtActionPerformed(evt);
            }
        });

        customPropertyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PropertyName", "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(customPropertyTable);

        customPropertyAdd.setText("Add custom property");
        customPropertyAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customPropertyAddActionPerformed(evt);
            }
        });

        jLabel12.setText("on your own risk!");

        javax.swing.GroupLayout settingsPanelLayout = new javax.swing.GroupLayout(settingsPanel);
        settingsPanel.setLayout(settingsPanelLayout);
        settingsPanelLayout.setHorizontalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, settingsPanelLayout.createSequentialGroup()
                        .addComponent(settingsErrorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(settingsApplyBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(settingsSaveBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(settingsLoadBtn))
                    .addGroup(settingsPanelLayout.createSequentialGroup()
                        .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(12, 12, 12)
                        .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(suppressSL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rawSampleSL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rawSampleTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(suppressTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator2)
                    .addComponent(jScrollPane1)
                    .addGroup(settingsPanelLayout.createSequentialGroup()
                        .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(settingsPanelLayout.createSequentialGroup()
                                .addComponent(customPropertyAdd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12))
                            .addComponent(jLabel7)
                            .addGroup(settingsPanelLayout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addComponent(tabletNameCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(touchCB)
                            .addGroup(settingsPanelLayout.createSequentialGroup()
                                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addGap(44, 44, 44)
                                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(mappedX)
                                    .addComponent(areaX, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(areaY)
                                    .addComponent(mappedY, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(mappedWidth)
                                    .addComponent(areaWidth, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(mappedHeight)
                                    .addComponent(areaHeight, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11))))
                        .addGap(0, 163, Short.MAX_VALUE)))
                .addContainerGap())
        );
        settingsPanelLayout.setVerticalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tabletNameCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(settingsPanelLayout.createSequentialGroup()
                        .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(rawSampleSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rawSampleTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(suppressSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addComponent(suppressTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(touchCB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(settingsPanelLayout.createSequentialGroup()
                        .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(areaX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(areaY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mappedX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mappedY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addGroup(settingsPanelLayout.createSequentialGroup()
                        .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(areaWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(areaHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mappedWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mappedHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customPropertyAdd)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(settingsSaveBtn)
                    .addComponent(settingsLoadBtn)
                    .addComponent(settingsApplyBtn)
                    .addComponent(settingsErrorLabel))
                .addContainerGap())
        );

        welcomePanel.setFont(welcomePanel.getFont().deriveFont((welcomePanel.getFont().getStyle() & ~java.awt.Font.ITALIC) & ~java.awt.Font.BOLD, 12));

        jLabel3.setFont(new java.awt.Font("Noto Sans", 0, 24)); // NOI18N
        jLabel3.setText("Welcome to the Wacom Utility for Linux!");

        jLabel4.setText("If you want to contribute, visit the GitHub page: ");

        jLabel5.setText("github.com/TheGuy2112/WacomUtility");

        jLabel6.setText("This is still in pre-alpha and only supports a little amount of features. ");

        javax.swing.GroupLayout welcomePanelLayout = new javax.swing.GroupLayout(welcomePanel);
        welcomePanel.setLayout(welcomePanelLayout);
        welcomePanelLayout.setHorizontalGroup(
            welcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(welcomePanelLayout.createSequentialGroup()
                .addGroup(welcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(welcomePanelLayout.createSequentialGroup()
                        .addGap(233, 233, 233)
                        .addComponent(jLabel4))
                    .addGroup(welcomePanelLayout.createSequentialGroup()
                        .addGap(260, 260, 260)
                        .addComponent(jLabel5))
                    .addGroup(welcomePanelLayout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addGroup(welcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3))))
                .addContainerGap(204, Short.MAX_VALUE))
        );
        welcomePanelLayout.setVerticalGroup(
            welcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(welcomePanelLayout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addGap(177, 177, 177)
                .addComponent(jLabel6)
                .addContainerGap(178, Short.MAX_VALUE))
        );

        jMenuBar1.setFont(jMenuBar1.getFont().deriveFont((jMenuBar1.getFont().getStyle() & ~java.awt.Font.ITALIC) & ~java.awt.Font.BOLD, 12));

        jMenu1.setText("View");

        welcomeMenuItem.setText("Welcome");
        welcomeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                welcomeMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(welcomeMenuItem);

        settingsMenuItem.setText("Settings");
        settingsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(settingsMenuItem);

        helpMenuItem.setText("Help");
        helpMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(helpMenuItem);
        jMenu1.add(jSeparator1);

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(exitMenuItem);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(settingsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(welcomePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(settingsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(welcomePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        dispose();
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void settingsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsMenuItemActionPerformed
        hideAllPanels();
        settingsPanel.setVisible(true);
    }//GEN-LAST:event_settingsMenuItemActionPerformed

    private void welcomeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_welcomeMenuItemActionPerformed
        hideAllPanels();
        welcomePanel.setVisible(true);
    }//GEN-LAST:event_welcomeMenuItemActionPerformed

    private void settingsApplyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsApplyBtnActionPerformed
        setSettings();
        Settings.apply();
    }//GEN-LAST:event_settingsApplyBtnActionPerformed

    private void settingsLoadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsLoadBtnActionPerformed
        Settings.load();
        refreshSettings();
    }//GEN-LAST:event_settingsLoadBtnActionPerformed

    private void settingsSaveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsSaveBtnActionPerformed
        setSettings();
        Settings.save();
    }//GEN-LAST:event_settingsSaveBtnActionPerformed

    private void suppressSLStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_suppressSLStateChanged
        suppressTxt.setText(suppressSL.getValue()+"");
    }//GEN-LAST:event_suppressSLStateChanged

    private void rawSampleSLStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rawSampleSLStateChanged
        rawSampleTxt.setText(rawSampleSL.getValue()+"");
    }//GEN-LAST:event_rawSampleSLStateChanged

    private void rawSampleTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rawSampleTxtActionPerformed
        try {
            int v = Integer.parseInt(rawSampleTxt.getText());
            if (v > rawSampleSL.getMaximum()) v = rawSampleSL.getMaximum();
            else if (v < rawSampleSL.getMinimum()) v = rawSampleSL.getMinimum();
            rawSampleSL.setValue(v);
        } catch (NumberFormatException ne) {} 
        finally {rawSampleTxt.setText(rawSampleSL.getValue()+"");}
    }//GEN-LAST:event_rawSampleTxtActionPerformed

    private void suppressTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suppressTxtActionPerformed
        try {
            int v = Integer.parseInt(suppressTxt.getText());
            if (v > suppressSL.getMaximum()) v = suppressSL.getMaximum();
            else if (v < suppressSL.getMinimum()) v = suppressSL.getMinimum();
            suppressSL.setValue(v);
        } catch (NumberFormatException ne) {} 
        finally {suppressTxt.setText(suppressSL.getValue()+"");}
    }//GEN-LAST:event_suppressTxtActionPerformed

    private void customPropertyAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customPropertyAddActionPerformed
        DefaultTableModel t = (DefaultTableModel)customPropertyTable.getModel();
        t.addRow(new String[]{"New property", "value"});
    }//GEN-LAST:event_customPropertyAddActionPerformed

    private void touchCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_touchCBActionPerformed
        Settings.set("Touch", touchCB.isSelected()?"on":"off");
    }//GEN-LAST:event_touchCBActionPerformed

    private void textFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldKeyPressed
        final JTextField src = (JTextField)evt.getSource();
        if (Character.isLetterOrDigit(evt.getKeyChar())) {
            final String text = src.getText()+evt.getKeyChar();
            try {
                Integer.parseInt(text);
                src.setForeground(null);
            } catch (NumberFormatException ne) {
                src.setForeground(Color.RED);
            }
        }
    }//GEN-LAST:event_textFieldKeyPressed

    private void helpMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpMenuItemActionPerformed
       if (Desktop.isDesktopSupported())
           try {
               Desktop.getDesktop().browse(
                       new URI("http://github.com/TheGuy2112/WacomUtility"));
       } catch (URISyntaxException | IOException ex) {}
    }//GEN-LAST:event_helpMenuItemActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainWindow().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField areaHeight;
    private javax.swing.JTextField areaWidth;
    private javax.swing.JTextField areaX;
    private javax.swing.JTextField areaY;
    private javax.swing.JButton customPropertyAdd;
    private javax.swing.JTable customPropertyTable;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenuItem helpMenuItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField mappedHeight;
    private javax.swing.JTextField mappedWidth;
    private javax.swing.JTextField mappedX;
    private javax.swing.JTextField mappedY;
    private javax.swing.JSlider rawSampleSL;
    private javax.swing.JTextField rawSampleTxt;
    private javax.swing.JButton settingsApplyBtn;
    private javax.swing.JLabel settingsErrorLabel;
    private javax.swing.JButton settingsLoadBtn;
    private javax.swing.JMenuItem settingsMenuItem;
    private javax.swing.JPanel settingsPanel;
    private javax.swing.JButton settingsSaveBtn;
    private javax.swing.JSlider suppressSL;
    private javax.swing.JTextField suppressTxt;
    private javax.swing.JComboBox<String> tabletNameCombo;
    private javax.swing.JCheckBox touchCB;
    private javax.swing.JMenuItem welcomeMenuItem;
    private javax.swing.JPanel welcomePanel;
    // End of variables declaration//GEN-END:variables
}
