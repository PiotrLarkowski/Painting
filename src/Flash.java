public class Flash {
    int xCoordinate;
    int yCoordinate;
    boolean isVertical;
    int speed;

    public Flash(int xCoordinate, int yCoordinate, boolean isVertical, int speed){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.isVertical = isVertical;
        this.speed = speed;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public int getSpeed() {
        return speed;
    }
}
