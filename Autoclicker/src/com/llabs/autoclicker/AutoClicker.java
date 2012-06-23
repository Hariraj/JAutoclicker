/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AutoClicker.java
 *
 * Created on Dec 22, 2010, 11:11:58 AM
 */
package com.llabs.autoclicker;

import java.awt.AWTException;
import java.awt.CheckboxMenuItem;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.MouseInfo;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Hariraj
 */
public class AutoClicker extends javax.swing.JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static ClickerThread clickerThread;
    private static int count = 0;
    static FormBean formBean;
    static AutoClicker autoClicker;
   //   private static final int F11 = 92;
    /** Creates new form SRBidder */

//    public void initJIntellitype() {
//        try {
//            JIntellitype.getInstance().addHotKeyListener(this);
//        } catch (RuntimeException ex) {
//             Logger.getLogger(AutoClicker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public AutoClicker() {
        initComponents();
//        initJIntellitype();
//        JIntellitype.getInstance().registerHotKey(F11, "F11");
        formBean = getFormBean();
        jTextField1.setText(String.valueOf(formBean.getX1()));
        jTextField2.setText(String.valueOf(formBean.getX2()));
        jTextField3.setText(String.valueOf(formBean.getX3()));
        jTextField4.setText(String.valueOf(formBean.getY1()));
        jTextField5.setText(String.valueOf(formBean.getY2()));
        jTextField6.setText(String.valueOf(formBean.getY3()));
        jTextField7.setText(String.valueOf(formBean.getD1() / 1000));
        jTextField8.setText(String.valueOf(formBean.getD2() / 1000));
        jTextField9.setText(String.valueOf(formBean.getD3() / 1000));
        jTextField10.setText(String.valueOf(formBean.getL1()));
        jTextField11.setText(String.valueOf(formBean.getL2()));
        jTextField12.setText(String.valueOf(formBean.getL3()));
        jTextField13.setText(String.valueOf(formBean.getCount()));
        jComboBox1.setSelectedIndex(formBean.getC1());
        jComboBox2.setSelectedIndex(formBean.getC2());
        jComboBox2.setSelectedIndex(formBean.getC3());
    }
        private static void createAndShowGUI() {
        //Check the SystemTray support
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon =
                new TrayIcon(createImage("images/click.png", "tray icon"));
        final SystemTray tray = SystemTray.getSystemTray();
        // Create a popup menu components
        MenuItem aboutItem = new MenuItem("About");
        CheckboxMenuItem cb1 = new CheckboxMenuItem("Hide to System Tray");
        CheckboxMenuItem cb2 = new CheckboxMenuItem("Pause");
        MenuItem exitItem = new MenuItem("Exit");
        //Add components to popup menu
        popup.add(aboutItem);
        popup.addSeparator();
        popup.add(cb1);
        popup.add(cb2);
        popup.addSeparator();
        popup.add(exitItem);
        trayIcon.setPopupMenu(popup);
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
            return;
        }

        trayIcon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "This dialog box is run from System Tray");
            }
        });
        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "This dialog box is run from the About menu item");
               
            }
        });
        cb1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                int cb1Id = e.getStateChange();
                if (cb1Id == ItemEvent.SELECTED){
                    trayIcon.setImageAutoSize(true);
                     autoClicker.setVisible(false);             
                } else {
                    trayIcon.setImageAutoSize(false);
                     autoClicker.setVisible(true);
                }
            }
        });

        cb2.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                int cb2Id = e.getStateChange();
                if (cb2Id == ItemEvent.SELECTED){
                    trayIcon.setToolTip("Autoclicker Paused");
                    if(clickerThread!=null){
                        clickerThread.suspend();
                         clickerThread.setRunningstate(2);//Suspended
                    }
                } else {
                    trayIcon.setToolTip(null);
                    if(clickerThread!=null){
                        clickerThread.resume();
                         clickerThread.setRunningstate(1);//Running
                    }
                }
            }
        });

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tray.remove(trayIcon);
//                 JIntellitype.getInstance().unregisterHotKey(F11);
//                  JIntellitype.getInstance().cleanUp();
                System.exit(0);

            }
        });
    }

    //Obtain the image URL
    protected static Image createImage(String path, String description) {
        URL imageURL = AutoClicker.class.getResource(path);
        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }

    
    public boolean isIntNumber(String num) {
        try {
            if(Integer.parseInt(num) < 0){
                return false;
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        new javax.swing.JDesktopPane();
        new javax.swing.JDesktopPane();
        jLabel2 = new javax.swing.JLabel();
        jDesktopPane3 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jDesktopPane4 = new javax.swing.JDesktopPane();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Auto Clicker");

        jDesktopPane3.setBackground(new java.awt.Color(255, 255, 255));
        jDesktopPane3.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18));
        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setText("Lionking Labs");
        jLabel1.setBounds(510, 290, 160, 30);
        jDesktopPane3.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Position 1");
        jLabel3.setBounds(30, 50, 70, 20);
        jDesktopPane3.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("Position 2");
        jLabel4.setBounds(30, 90, 60, 20);
        jDesktopPane3.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setText("Position 3");
        jLabel5.setBounds(30, 130, 70, 20);
        jDesktopPane3.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField1.setText("0");
        jTextField1.setBounds(110, 50, 50, 20);
        jDesktopPane3.add(jTextField1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField2.setText("0");
        jTextField2.setBounds(110, 90, 50, 20);
        jDesktopPane3.add(jTextField2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField3.setText("0");
        jTextField3.setBounds(110, 130, 50, 20);
        jDesktopPane3.add(jTextField3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton1.setText("Start");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.setBounds(20, 210, 120, 30);
        jDesktopPane3.add(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField4.setText("0");
        jTextField4.setBounds(170, 50, 50, 20);
        jDesktopPane3.add(jTextField4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField5.setText("0");
        jTextField5.setBounds(170, 90, 50, 20);
        jDesktopPane3.add(jTextField5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jTextField6.setText("0");
        jTextField6.setBounds(170, 130, 50, 20);
        jDesktopPane3.add(jTextField6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton2.setText("Exit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.setBounds(20, 290, 120, 30);
        jDesktopPane3.add(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel6.setFont(new java.awt.Font("KodchiangUPC", 3, 28));
        jLabel6.setForeground(new java.awt.Color(255, 51, 51));
        jLabel6.setText("Auto Clicker");
        jLabel6.setBounds(280, 0, 160, 30);
        jDesktopPane3.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jDesktopPane4.setBackground(new java.awt.Color(204, 204, 204));
        jDesktopPane4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel8.setText("position where u want to repeat the click using ");
        jLabel8.setBounds(10, 20, 290, 30);
        jDesktopPane4.add(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel7.setText("Click on Fill Coordinates and move the mouse to the ");
        jLabel7.setBounds(10, 0, 300, 30);
        jDesktopPane4.add(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel9.setText("autoclicker, this app will auto fill the Coordinates.");
        jLabel9.setBounds(10, 40, 300, 30);
        jDesktopPane4.add(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jDesktopPane4.setBounds(170, 250, 310, 70);
        jDesktopPane3.add(jDesktopPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton3.setText("Pause");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jButton3.setBounds(20, 250, 120, 30);
        jDesktopPane3.add(jButton3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel10.setText("X");
        jLabel10.setBounds(130, 20, 20, 30);
        jDesktopPane3.add(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel11.setText("Y");
        jLabel11.setBounds(180, 20, 20, 30);
        jDesktopPane3.add(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField7.setText("5");
        jTextField7.setBounds(240, 50, 40, 20);
        jDesktopPane3.add(jTextField7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField8.setText("5");
        jTextField8.setBounds(240, 90, 40, 20);
        jDesktopPane3.add(jTextField8, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField9.setText("5");
        jTextField9.setBounds(240, 130, 40, 20);
        jDesktopPane3.add(jTextField9, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton4.setText("Fill Coordinates");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jButton4.setBounds(500, 50, 130, 23);
        jDesktopPane3.add(jButton4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton5.setText("Fill Coordinates");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jButton5.setBounds(500, 90, 130, 23);
        jDesktopPane3.add(jButton5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton6.setText("Fill Coordinates");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jButton6.setBounds(500, 130, 130, 23);
        jDesktopPane3.add(jButton6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel12.setText("Delay (sec)");
        jLabel12.setBounds(230, 20, 80, 30);
        jDesktopPane3.add(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField10.setText("1");
        jTextField10.setBounds(300, 50, 50, 20);
        jDesktopPane3.add(jTextField10, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField11.setText("1");
        jTextField11.setBounds(300, 90, 50, 20);
        jDesktopPane3.add(jTextField11, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField12.setText("1");
        jTextField12.setBounds(300, 130, 50, 20);
        jDesktopPane3.add(jTextField12, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel13.setText("Loops");
        jLabel13.setBounds(310, 20, 60, 30);
        jDesktopPane3.add(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Left Click", "Middle Click", "Right Click", "Left Doble Click", "Right Double Click" }));
        jComboBox1.setBounds(370, 50, 100, 20);
        jDesktopPane3.add(jComboBox1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Left Click", "Middle Click", "Right Click", "Left Doble Click", "Right Double Click" }));
        jComboBox2.setBounds(370, 90, 100, 20);
        jDesktopPane3.add(jComboBox2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Left Click", "Middle Click", "Right Click", "Left Doble Click", "Right Double Click" }));
        jComboBox3.setBounds(370, 130, 100, 20);
        jDesktopPane3.add(jComboBox3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel14.setText("Exit Autoclcker after ");
        jLabel14.setBounds(180, 210, 120, 30);
        jDesktopPane3.add(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField13.setText("1");
        jTextField13.setBounds(320, 210, 60, 30);
        jDesktopPane3.add(jTextField13, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel15.setText("Cycle(s).");
        jLabel15.setBounds(390, 210, 70, 30);
        jDesktopPane3.add(jLabel15, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton7.setText("Hide To System Tray");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jButton7.setBounds(510, 250, 140, 30);
        jDesktopPane3.add(jButton7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {                      
            String xs1 = jTextField1.getText();
            String xs2 = jTextField2.getText();
            String xs3 = jTextField3.getText();
            String ys1 = jTextField4.getText();
            String ys2 = jTextField5.getText();
            String ys3 = jTextField6.getText();
            String ds1 = jTextField7.getText();
            String ds2 = jTextField8.getText();
            String ds3 = jTextField9.getText();
            String ls1 = jTextField10.getText();
            String ls2 = jTextField11.getText();
            String ls3 = jTextField12.getText();
            String counterstr = jTextField13.getText();
            if (isIntNumber(xs1)) {
                formBean.setX1(Integer.parseInt(xs1));
            }
            if (isIntNumber(xs2)) {
                formBean.setX2(Integer.parseInt(xs2));
            }
            if (isIntNumber(xs3)) {
                formBean.setX3(Integer.parseInt(xs3));
            }
            if (isIntNumber(ys1)) {
                formBean.setY1(Integer.parseInt(ys1));
            }
            if (isIntNumber(ys2)) {
                formBean.setY2(Integer.parseInt(ys2));
            }
            if (isIntNumber(ys3)) {
                formBean.setY3(Integer.parseInt(ys3));
            }
            if (isIntNumber(ds1)) {
                formBean.setD1(Integer.parseInt(ds1)*1000);
            }
            if (isIntNumber(ds2)) {
                formBean.setD2(Integer.parseInt(ds2)*1000);
            }
            if (isIntNumber(ds3)) {
                formBean.setD3(Integer.parseInt(ds3)*1000);
            }
            if (isIntNumber(ls1)) {
                formBean.setL1(Integer.parseInt(ls1));
            }
            if (isIntNumber(ls2)) {
                formBean.setL2(Integer.parseInt(ls2));
            }
            if (isIntNumber(ls3)) {
                formBean.setL3(Integer.parseInt(ls3));
            }
            if (isIntNumber(counterstr)) {
                formBean.setCount(Integer.parseInt(counterstr));
            }
            formBean.setC1(jComboBox1.getSelectedIndex());
            formBean.setC2(jComboBox2.getSelectedIndex());
            formBean.setC3(jComboBox3.getSelectedIndex());
            clickerThread = new ClickerThread(formBean);
            writeToFile( formBean, "formBean.ser");

            clickerThread.start();
            clickerThread.setRunningstate(1);//Running
        } catch (Exception e) {
            
            Logger.getLogger(AutoClicker.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//        JIntellitype.getInstance().unregisterHotKey(F11);
//        JIntellitype.getInstance().cleanUp();
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        count++;
        if (count % 2 == 0) {
            jButton3.setText("Resume");
            clickerThread.suspend();
            clickerThread.setRunningstate(2);//Suspended
        } else {
            jButton3.setText("Pause");
            clickerThread.resume();
            clickerThread.setRunningstate(1);//Running
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            Thread.sleep(3000);
            jTextField1.setText("" + MouseInfo.getPointerInfo().getLocation().x);
            jTextField4.setText("" + MouseInfo.getPointerInfo().getLocation().y);
        } catch (InterruptedException ex) {
            Logger.getLogger(AutoClicker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            Thread.sleep(3000);
            jTextField2.setText("" + MouseInfo.getPointerInfo().getLocation().x);
            jTextField5.setText("" + MouseInfo.getPointerInfo().getLocation().y);
        } catch (InterruptedException ex) {
            Logger.getLogger(AutoClicker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            Thread.sleep(3000);
            jTextField3.setText("" + MouseInfo.getPointerInfo().getLocation().x);
            jTextField6.setText("" + MouseInfo.getPointerInfo().getLocation().y);
        } catch (InterruptedException ex) {
            Logger.getLogger(AutoClicker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        autoClicker.setVisible(false);
    }//GEN-LAST:event_jButton7ActionPerformed

        /**
     * Reads it serialized or create a new one if it doens't exists
     */
    private static FormBean getFormBean(){
        File file = new File("formBean.ser");
        if( !file.exists() ) {
            formBean = new FormBean();
            formBean.setCount(1);
             return formBean;

        } else {
            return ( FormBean ) readObjectFrom( file );
        }
    }

        /**
     * write the object to a file
     */
    private static void writeToFile( Serializable s , String fileName ) {
        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream( new FileOutputStream( new File( fileName )));
            oos.writeObject( s );
        } catch( IOException ioe ){

        } finally {
            if( oos != null ) try {
                oos.close();
            } catch( IOException ioe ){}
        }

    }
    /**
     * Read an object from the file
     */
    private static Object readObjectFrom( File f ) {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream( new FileInputStream( f )) ;
            return ois.readObject();
        } catch( ClassNotFoundException cnfe ){
            return null;
        } catch( IOException ioe ) {
            return null;
        } finally {
            if( ois != null ) try {
                ois.close();
            } catch( IOException ioe ){}
        }
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

         try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
        } catch (IllegalAccessException ex) {
        } catch (InstantiationException ex) {
        } catch (ClassNotFoundException ex) {
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
              autoClicker = new AutoClicker();
              autoClicker.setVisible(true);
                autoClicker.setIconImage(Toolkit.getDefaultToolkit().getImage(AutoClicker.class.getResource("images/click.png")));
                createAndShowGUI();
            }
        });
     
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JDesktopPane jDesktopPane3;
    private javax.swing.JDesktopPane jDesktopPane4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables

//    public void onHotKey(int aIdentifier) {
//       if(clickerThread != null){
//           if(clickerThread.getRunningstate()==1){
//            clickerThread.suspend();
//            clickerThread.setRunningstate(2);
//       }
//       else{
//            clickerThread.resume();
//            clickerThread.setRunningstate(1);
//        }
//        }
//    }
}
