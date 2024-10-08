public class Point {
    public double x;
    public double y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double dist(Point p) {
        return Math.sqrt(Math.pow(p.x - this.x, 2) + Math.pow(p.y - this.y, 2));
    }

}
