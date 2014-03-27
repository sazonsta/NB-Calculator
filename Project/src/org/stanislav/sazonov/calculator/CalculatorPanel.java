/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stanislav.sazonov.calculator;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.math.BigDecimal;
import java.util.Arrays;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

/**
 *
 * @author ssazonov
 */
public class CalculatorPanel extends javax.swing.JPanel {

    /** Creates new form CalculatorPanel */
    public CalculatorPanel() {
        initComponents();
        setSplitPanelAppearane();
        setActions();
    }

    private int precision = 2;

    private void setActions() {
        jTextField1.addFocusListener(new FocusListener() {
            private boolean noFocusedYet = true;

            @Override
            public void focusGained(FocusEvent e) {
                if (noFocusedYet) {
                    jTextField1.setText("");
                    jTextField1.setFont(jTextField1.getFont().deriveFont(Font.PLAIN));
                    noFocusedYet = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });

        jTextField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
            }

        });

        jTextField2.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Object[] possibilities = {"1", "2", "3", "4", "5", "6", "7", "8"};

                    String s = (String) JOptionPane.showInputDialog(
                            jTextField2,
                            "Set precision:",
                            "Calculator settings",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            possibilities,
                            String.valueOf(precision));

                    if ((s != null)) {
                        precision = Integer.valueOf(s);
                        calculate();
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    private String evaluate(String toEval) {
        try {
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");
            Object eval = engine.eval(toEval);
            if (eval == null) {
                return "ERR";
            }
            try {
                double ret = Double.valueOf(eval.toString());
                BigDecimal bd = new BigDecimal(ret);
                BigDecimal rounded = bd.setScale(precision, BigDecimal.ROUND_HALF_UP);
                return String.valueOf(rounded.doubleValue());
            } catch (NumberFormatException e) {
                return eval.toString();
            }
        } catch (ScriptException ex) {
            return "ERR";
        }
    }
    
    private void calculate() {
        String s = evaluate(jTextField1.getText());
        jTextField2.setText(s);
        if (s.equals("ERR")) {
            jTextField2.setForeground(Color.red);
        } else {
            jTextField2.setForeground(Color.black);
        }
    }

    private void setSplitPanelAppearane() {
        BasicSplitPaneDivider divider = ((BasicSplitPaneUI) jSplitPane1.getUI()).getDivider();
        divider.setLayout(new BorderLayout());
        divider.add(new EQ());
    }

    private class EQ extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            int width = this.getWidth();
            int h1 = this.getHeight() / 2 - 2;
            int h2 = this.getHeight() / 2 + 2;

            Line2D line1 = new Line2D.Double(3, h1, width - 3, h1);
            Line2D line2 = new Line2D.Double(3, h2, width - 3, h2);
            g2.setColor(Color.black);
            g2.setStroke(new BasicStroke(2));
            g2.draw(line1);
            g2.draw(line2);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(CalculatorPanel.class, "CalculatorPanel.jButton1.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setPreferredSize(new java.awt.Dimension(300, 25));

        jSplitPane1.setDividerLocation(220);
        jSplitPane1.setDividerSize(15);

        jTextField1.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jTextField1.setText(org.openide.util.NbBundle.getMessage(CalculatorPanel.class, "CalculatorPanel.jTextField1.text")); // NOI18N
        jSplitPane1.setLeftComponent(jTextField1);

        jTextField2.setEditable(false);
        jTextField2.setForeground(new java.awt.Color(255, 0, 0));
        jTextField2.setText(org.openide.util.NbBundle.getMessage(CalculatorPanel.class, "CalculatorPanel.jTextField2.text")); // NOI18N
        jTextField2.setOpaque(false);
        jSplitPane1.setRightComponent(jTextField2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
