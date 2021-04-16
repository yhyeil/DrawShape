package knox.drawshapes;

import java.awt.Point;

class BoundingBox
{
    private int left;
    private int right;
    private int top;
    private int bottom;
    private Point[] corners = new Point[4];
    
    BoundingBox(int left, int right, int top, int bottom){
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
        this.corners[0] = new Point(left, top);
        this.corners[1] = new Point(left, bottom);
        this.corners[2] = new Point(right, bottom);
        this.corners[3] = new Point(right, top);
    }
    
    boolean contains(Point p){
        return p.x >= left && p.x <= right && p.y >= top && p.y <= bottom;
    }
    
    public String toString() {
        return String.format("left=%d right=%d top=%d bottom=%d", this.left, this.right, this.top, this.bottom);
    }
    
    public void move(int dx, int dy) {
    	for (Point p : this.corners) {
    		p.x += dx;
    		p.y += dy;
    	}
    	this.left += dx;
    	this.right += dx;
    	this.top += dy;
    	this.bottom += dy;
    }
    
    boolean intersects(BoundingBox other){
        // are any of my corners in their bounding box?
        for (Point corner : corners){
            if (other.contains(corner)){
                return true;
            }
        }
        // are any of their corners in my bounding box?
        for (Point corner : other.corners){
            if (this.contains(corner)){
                return true;
            }
        }
        // Check for the case where we intersect but no corners are contained
        if (this.left < other.left && this.right > other.right && 
                this.top > other.top && this.bottom < other.bottom)
        {
            return true;
        }
        return this.left > other.left && this.right < other.right &&
                this.top < other.top && this.bottom > other.bottom;
    }
}
