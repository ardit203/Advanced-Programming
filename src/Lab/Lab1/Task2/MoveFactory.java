package Lab.Lab1.Task2;

interface Move {
    void move(Movable movable);
}

public class MoveFactory {
    private static final Move MOVE_UP = m -> m.moveUp();
    private static final Move MOVE_DOWN = m -> m.moveDown();
    private static final Move MOVE_RIGHT = m -> m.moveRight();
    private static final Move MOVE_LEFT = m -> m.moveLeft();

    public static Move move(TYPE type, DIRECTION direction, Movable movable) {
        if (type == movable.getType()) {
            if (direction == DIRECTION.UP) {
                return MOVE_UP;
            } else if (direction == DIRECTION.DOWN) {
                return MOVE_DOWN;
            } else if (direction == DIRECTION.RIGHT) {
                return MOVE_RIGHT;
            } else if (direction == DIRECTION.LEFT) {
                return MOVE_LEFT;
            }
        }
        throw new UnsupportedMoveException();
    }
}
