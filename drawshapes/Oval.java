package knox.drawshapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Oval extends AbstractShape{
	private int width;
	private int height;
	
	public Oval(Point center, int width, int height,Color color) {
		super(new Point(center.x,center.y), color);
		boundingBox= new BoundingBox(center.x-width/2,center.x+width/2,center.y-height/2,center.y+height/2);
		this.width=width;
		this.height=height;
	}
	
	@Override
    public void draw(Graphics g) {
        if (isSelected()){
            g.setColor(getColor().darker());
        } else {
            g.setColor(getColor());
        }
        g.fillOval((int)getAnchorPoint().getX() - width/2,
                (int)getAnchorPoint().getY() - height/2,
                width,
                height);
    }
	
	public String toString() {
        return String.format("OVAL %d %d %d %d %s %s", 
                this.getAnchorPoint().x, 
                this.getAnchorPoint().y,
                this.width,
                this.height,
                Util.colorToString(this.getColor()),
                this.isSelected());
    }
	
	@Override
	public void scale(double factor) {
		this.width = (int)(factor * this.width);
		this.height=(int)(factor*this.height);
	}
}
