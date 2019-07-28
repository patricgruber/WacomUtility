/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author patric
 */
public class TabletAreaPanel extends javax.swing.JPanel {

    private final static int CORNER_SIZE=10;
    private final static int BORDER=15;
    private final static int MIN_SIZE=50;
    
    private double scale;
    private Rectangle area;
    private Rectangle maxArea;
    
    private Point oldPoint;
    private Area selectedCorner = Area.NONE;
    
    private IAreaUpdateable parent;
    
    public TabletAreaPanel() {
        initComponents();
    }
    
    public void init(final Rectangle area, final Rectangle maxArea, final IAreaUpdateable parent) {
        this.area = area;
        this.maxArea = maxArea;
        this.parent = parent;
        calculateScale();
    }
    
    public void checkArea() {
        int xOffset = area.x;
        int yOffset = area.y;
        int width = area.width;
        int height = area.height;
        
        if (xOffset < 0) xOffset = 0;
        if (width > maxArea.width) width = maxArea.width;
        if (width < MIN_SIZE/scale) width = (int)(MIN_SIZE/scale);
        if (xOffset+width > maxArea.width) xOffset = maxArea.width-width;
        
        if (yOffset < 0) yOffset = 0;
        if (height < MIN_SIZE/scale) height = (int)(MIN_SIZE/scale);
        if (height > maxArea.height) height = maxArea.height;
        if (yOffset+height > maxArea.height) yOffset = maxArea.height-height;
        
        this.area = new Rectangle(xOffset, yOffset, width, height);
        //updateParentArea();
    }
    
    public void setNewArea(int xOffset, int yOffset, int width, int height) {
        this.area = new Rectangle(xOffset, yOffset, width, height);
        checkArea();        
        repaint();
    }
        
    private void calculateScale() {
        int usableHeight = this.getHeight()-BORDER*2; //10px border;
        scale = usableHeight/1.0/maxArea.height;
    }
    
    private Area getSelectedArea(Point p) {
            int scaledWidth = (int)(maxArea.width*scale);
            int marginLeft = (this.getWidth()-scaledWidth)/2;

            int xOffset = marginLeft+(int)(area.x*scale);
            int yOffset = BORDER+(int)(area.y*scale);
            int width = (int)(area.width*scale);
            int height = (int)(area.height*scale);
            
            p.x -= xOffset;
            p.y -= yOffset;
            
            if (p.x < 0 || p.y < 0 
                    || p.x > width || p.y > height) return Area.NONE;
            
            if (p.x <= CORNER_SIZE) {
                if (p.y <= CORNER_SIZE)
                    return Area.UPPER_LEFT_CORNER;
                else if (p.y >= height-CORNER_SIZE)
                    return Area.LOWER_LEFT_CORNER;
            }
            
            if (p.x >= width-CORNER_SIZE) {
                if (p.y <= CORNER_SIZE)
                    return Area.UPPER_RIGHT_CORNER;
                else if (p.y >= height-CORNER_SIZE)
                    return Area.LOWER_RIGHT_CORNER;
            }
            
            return Area.MIDDLE;
    }
    
    private void updateParentArea() {
        parent.setNewArea(area.x, area.y, area.width, area.height);
    }
        
    public void setFullArea() {
        area = maxArea;
        updateParentArea();
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
            g2d.setStroke(new BasicStroke(2));
            g.drawRect(marginLeft,BORDER,scaledWidth,(int)(maxArea.height*scale));

            g.setColor(Color.blue);
            g2d.setStroke(new BasicStroke(1));
            int xOffset = marginLeft+(int)(area.x*scale);
            int yOffset = BORDER+(int)(area.y*scale);
            int width = (int)(area.width*scale);
            int height = (int)(area.height*scale);
                        
            g.drawRect(xOffset,yOffset,width,height);
                       
            g.drawRect(xOffset,yOffset,CORNER_SIZE,CORNER_SIZE);
            g.drawRect(xOffset+width-CORNER_SIZE,yOffset,CORNER_SIZE,CORNER_SIZE);
            g.drawRect(xOffset+width-CORNER_SIZE,yOffset+height-CORNER_SIZE,CORNER_SIZE,CORNER_SIZE);
            g.drawRect(xOffset,yOffset+height-CORNER_SIZE,CORNER_SIZE,CORNER_SIZE);
            
            if (selectedCorner != Area.NONE) {
                switch (selectedCorner) {
                    case UPPER_LEFT_CORNER:
                        g.fillRect(xOffset,yOffset,CORNER_SIZE,CORNER_SIZE);
                        break;
                    case UPPER_RIGHT_CORNER:
                        g.fillRect(xOffset+width-CORNER_SIZE,yOffset,CORNER_SIZE,CORNER_SIZE);
                        break;
                    case LOWER_LEFT_CORNER:
                        g.fillRect(xOffset,yOffset+height-CORNER_SIZE,CORNER_SIZE,CORNER_SIZE);
                        break;
                    case LOWER_RIGHT_CORNER:
                        g.fillRect(xOffset+width-CORNER_SIZE,yOffset+height-CORNER_SIZE,CORNER_SIZE,CORNER_SIZE);
                        break;
                    case MIDDLE:
                        g.fillRect(xOffset+width/2-CORNER_SIZE/2,yOffset+height/2-CORNER_SIZE/2,CORNER_SIZE,CORNER_SIZE);
                        break;
                }
            }
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
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
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
        selectedCorner = getSelectedArea(evt.getPoint());
        if (selectedCorner != Area.NONE) {
            oldPoint = evt.getPoint();
        }
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        oldPoint = null;
        selectedCorner = Area.NONE;
        repaint();
    }//GEN-LAST:event_formMouseReleased

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        calculateScale();
    }//GEN-LAST:event_formComponentResized

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        if (oldPoint != null && selectedCorner != Area.NONE && 
                new Rectangle(0,0,this.getWidth(),this.getHeight()).contains(evt.getPoint())) {
            Point change = new Point(
                    (int)((evt.getPoint().x-oldPoint.x)/scale),
                    (int)((evt.getPoint().y-oldPoint.y)/scale));
            
            Rectangle oldArea = area;
            
            switch (selectedCorner) {
                case UPPER_LEFT_CORNER:
                    area.x += change.x;
                    area.y += change.y;
                    area.width -= change.x;
                    area.height -= change.y;
                    
                    if (area.x < 0 || area.width < MIN_SIZE/scale) {
                        area.x -= change.x;
                        area.width += change.x;
                    }
                    
                    if (area.y < 0 || area.height < MIN_SIZE/scale) {
                        area.y -= change.y;
                        area.height += change.y;
                    }
                    
                    break;
                case UPPER_RIGHT_CORNER:
                    area.width += change.x;
                    area.y += change.y;
                    area.height -= change.y;
                    
                    if (area.width < MIN_SIZE/scale || area.x+area.width > maxArea.width) {
                        area.width -= change.x;
                    }
                    
                    if (area.y < 0 || area.height < MIN_SIZE/scale) {
                        area.y -= change.y;
                        area.height += change.y;
                    }
                    
                    break;
                case LOWER_LEFT_CORNER:
                    area.x += change.x;
                    area.width -= change.x;
                    area.height += change.y;
                    
                    if (area.height < MIN_SIZE/scale || area.y+area.height > maxArea.height) {
                        area.height -= change.y;
                    }
                    
                    if (area.x < 0 || area.width < MIN_SIZE/scale) {
                        area.x -= change.x;
                        area.width += change.x;
                    }
                    
                    break;
                case LOWER_RIGHT_CORNER:
                    area.width += change.x;
                    area.height += change.y;
                    
                    if (area.height < MIN_SIZE/scale || area.y+area.height > maxArea.height) {
                        area.height -= change.y;
                    } 
                    
                    if (area.width < MIN_SIZE/scale || area.x+area.width > maxArea.width) {
                        area.width -= change.x;
                    }
                    
                    break;
                case MIDDLE:
                    area.x += change.x;
                    area.y += change.y;
                    
                    if (area.x < 0 || area.x+area.width > maxArea.width) {
                        area.x -= change.x;
                    }
                    
                    if (area.y < 0 || area.y+area.height > maxArea.height) {
                        area.y -= change.y;
                    }
                    
                    break;
            }
            
            updateParentArea();
            
            oldPoint = evt.getPoint();
        }
        repaint();
    }//GEN-LAST:event_formMouseDragged

    private enum Area {
        UPPER_LEFT_CORNER, UPPER_RIGHT_CORNER,
        LOWER_LEFT_CORNER, LOWER_RIGHT_CORNER,
        TOP, BOTTOM, LEFT, RIGHT,
        MIDDLE, NONE
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
