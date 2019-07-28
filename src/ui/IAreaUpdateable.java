package ui;

/**
 *
 * @author patric
 */
public interface IAreaUpdateable {
    /**
     * Updates the tablet area
     * 
     * @param xOffset
     * @param yOffset
     * @param width
     * @param height 
     */
    void setNewArea(int xOffset, int yOffset, int width, int height);
    
    /**
     * Updates the area the tablet area gets mapped to
     * 
     * @param xOffset
     * @param yOffset
     * @param width
     * @param height 
     */
    void setMapped(int xOffset, int yOffset, int width, int height);
}