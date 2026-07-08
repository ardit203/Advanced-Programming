package Exam.SecondMidtermExam.Task40;

class Point2D implements ICluster<Point2D> {
    long id;
    float x;
    float y;

    public Point2D(long id, float x, float y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }


    @Override
    public long getId() {
        return id;
    }

    @Override
    public double getDistance(Point2D other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
//        double dist = Math.hypot(dx, dy);

        return Math.sqrt(dx * dx + dy * dy);
    }
}