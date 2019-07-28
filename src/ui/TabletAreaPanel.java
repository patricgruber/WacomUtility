/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author patric
 */
public class TabletAreaPanel extends javax.swing.JPanel {

    private double scale;
    private Rectangle area;
    private Rectangle maxArea;
    
    public TabletAreaPanel() {
        initComponents();
    }
    
    public void init(final Rectangle area, final Rectangle maxArea, final IAreaUpdateable parent) {
        this.area = area;
        this.maxArea = maxArea;
        calculateScale();
    }
        
    public void calculateScale() {
        int usableHeight = this.getHeight()-10*2; //10px border;
        scale = usableHeight/1.0/maxArea.height;
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
                
        Graphics2D g2d = (Graphics2D)g;
               
        if (maxArea != null && area != null) {
            calculateScale();
            
            int scaledWidth = (int)(maxArea.width*scale);
            int marginLeft = (this.getWidth()-scaledWidth)/2;

            g.setColor(Color.black);
            g.drawRect(marginLeft,10,scaledWidth,(int)(maxArea.height*scale));

            g.setColor(Color.blue);
            int xOffset = marginLeft+(int)(area.x*scale);
            int yOffset = 10+(int)(area.y*scale);
            int width = (int)(area.width*scale);
            int height = (int)(area.height*scale);
            
            //System.out.println(new Rectangle(xOffset,yOffset,width,height));
            
            g.drawRect(xOffset,yOffset,width,height);
        }
    }
            
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(254, 254, 254));
        setBorder(null);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseReleased

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseMoved

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        calculateScale();
    }//GEN-LAST:event_formComponentResized


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
