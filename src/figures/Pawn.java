package figures;

import core.Globals;

public class Pawn extends Piece {
    int longMoveY = 2;

    public Pawn(int[] coordinates, boolean isWhite) {
        super(coordinates, isWhite);
        this.moveX = 0;
        this.moveY = 1;
    }

    @Override
    public String toString() {
        String color;
        if (this.isWhite) {
            color = "w";
        } else {
            color = "b";
        }
        return color + "P";
    }

    public boolean move(int rowDest, int lineDest) {

        if ((0 > rowDest || rowDest > 7) || (0 > lineDest || lineDest > 7)) {
            return false;
        }

        if (this.coordinates[1] != rowDest) {
            return false;
        }

        if (Math.abs(this.coordinates[0] - lineDest) == this.moveY) {
            this.coordinates[0] = this.coordinates[0] - lineDest;
        } else if (this.isFirstMove && (Math.abs(this.coordinates[0] - lineDest) == this.longMoveY)) {
            this.coordinates[0] = this.coordinates[0] - lineDest;
        } else {
            return false;
        }

        this.isFirstMove = false;
        this.coordinates[0] = lineDest; // Изменение row не имеет смысла
        return true;
    }
}