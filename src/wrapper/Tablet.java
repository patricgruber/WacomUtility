package wrapper;

import java.util.Optional;

/**
 * @author patric
 */
public class Tablet {
    
    private final String name;
    
    public Tablet(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    private void set(final String property, final String value) {
        try {
            TerminalWrapper.set(name, property, value);
        } catch(WrapperException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private Optional<String> get(final String property) {
        try {
            return Optional.of(TerminalWrapper.get(name, property));
        } catch(WrapperException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }
    
    public String getAreaString() {
        return get("Area").orElse("0 0 0 0");
    }
    
    public String getMaxAreaString() {
        String currArea = getAreaString();
        set("ResetArea", "");
        String maxArea = getAreaString();
        set("Area", currArea);
        return maxArea;
    }
    
    public void setArea(final int xOffset, final int yOffset, final int width, final int height) {
        String value = xOffset+" "+yOffset+" "+width+" "+height;
        set("Area", value);
    }
    
    public int getAreaXOffset() {
        String area = getAreaString();
        return Integer.parseInt(area.split(" ")[0]);
    }
    
    public int getAreaYOffset() {
        String area = getAreaString();
        return Integer.parseInt(area.split(" ")[1]);
    }
    
    public int getAreaWidth() {
        String area = getAreaString();
        return Integer.parseInt(area.split(" ")[2]);
    }
    
    public int getAreaHeight() {
        String area = getAreaString();
        return Integer.parseInt(area.split(" ")[3]);
    }
    
    public void setSuppress(int suppress) {
        set("Suppress", ""+suppress);
    }
    
    public int getSuppress() {
        return Integer.parseInt(get("Suppress").orElse("0"));
    }
    
    public void setRawSample(final int rawSample) {
        set("RawSample", ""+rawSample);
    }
    
    public int getRawSample() {
        return Integer.parseInt(get("RawSample").orElse("1"));
    }
    
    public void setThreshold(final int threshold) {
        set("Threshold", ""+threshold);
    }
    
    public int getThreshold() { 
        return Integer.parseInt(get("Threshold").orElse("27"));
    }
     
    public void setMapToOutput(final int xOffset, final int yOffset, final int width, final int height) {
        final String mapped = width+"x"+height+"+"+xOffset+"+"+yOffset;
        set("MapToOutput", mapped);
    }
    
    public void setTouch(final boolean activated) {
        final String value = activated?"on":"off";
        set("Touch", value);
    }
    
    public boolean getTouch() {
        final String value = get("Touch").orElse("off");
        return value.equals("on");
    }
}