package javaapplication40;

import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
public class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt(); // width of the building.
        int H = in.nextInt(); // height of the building.
        int N = in.nextInt(); // maximum number of turns before game over.
        int X0 = in.nextInt();
        int Y0 = in.nextInt();

        Quadrant q = new Quadrant();
        q.down = H-1;
        q.left = 0;
        q.up = 0;
        q.right = W-1;
        Dim bPos = new Dim();
        bPos.height = Y0;
        bPos.width = X0;
        // game loop
        while (true) {
            String bombDir = in.next(); // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)
           
            q = newQuadrant(q, bPos, bombDir);
            bPos = idealPos(q);
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");


            // the location of the next window Batman should jump to.
            System.out.println(bPos.width + " " + bPos.height);
        }
    }

    private static Quadrant newQuadrant(Quadrant oldQuadrant, Dim bPos, String bombDir) {
        //System.out.println("before " + oldQuadrant.left + " " + oldQuadrant.up + " "+ 
        //oldQuadrant.right + "  " + oldQuadrant.down);
        if (bombDir.contains("R")) {
            oldQuadrant.left += bPos.width + 1;
        }
        if (bombDir.contains("L")) {
            oldQuadrant.right -= bPos.width - 1;
        }
        if (bombDir.contains("U")) {
            oldQuadrant.down -= bPos.height - 1;
        }
        if (bombDir.contains("D")) {
            oldQuadrant.up += bPos.height + 1;
        }
        //System.out.println("after " + oldQuadrant.left + " " + oldQuadrant.up + " "+ 
        //oldQuadrant.right + "  " + oldQuadrant.down);
        return oldQuadrant;
    }

    private static Dim idealPos(Quadrant quadrant) {
        Dim newPos = new Dim();
        newPos.width = quadrant.left+Math.abs(quadrant.right-quadrant.left)/2;
        newPos.height = quadrant.up+Math.abs(quadrant.down-quadrant.up)/2;
        return newPos;
    }
}

class Dim {
    public int width;
    public int height;
}

class Quadrant {
    public int right;
    public int left;
    public int down;
    public int up;
}