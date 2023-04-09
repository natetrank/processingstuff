package org.example;

import processing.core.PApplet;

import static org.example.Grid.newGrid;

public class PilotPerlinGrid extends PApplet {

  float xz = 1000;
  float yz = 1000;
  int dotSizing = 2;
  int density = 8;
  float lineSpacing = 0.5f;
  float scalar = 0.001f;
  Grid grid;

  public void settings() {
    size(900, 600);
  }

  public void setup() {
    grid = newGrid(width, height, density);
  }

  private float ns(int x, int y, float z, float scalar, int minVal, int maxVal) {
    return map(noise(x*scalar, y*scalar, z*scalar),
            0, 1,
            minVal, maxVal);
  }

  public void draw() {
    background(255);
    fill(0);
    noStroke();
//    for (int x = 0; x < width; x += dotSpacing) {
//      for (int y = 0; y < height; y += dotSpacing) {
//        float xoff = ns(x, y, xz, scalar, -100, 100);
//        float yoff = ns(x, y, yz, scalar, -100, 100);
//        ellipse(x + xoff, y + yoff, dotSizing, dotSizing);
//
//        System.out.printf("Original (%s, %s)%n", x, y);
//      }
//    }

//    for (int x = 0; x < grid.getWidth(); x++) {
//      for (int y = 0; y < grid.getHeight(); y++) {
//        Point point = grid.getPoint(x, y);
//        float xoff = ns(point.getX(), point.getY(), xz, scalar, -100, 100);
//        float yoff = ns(point.getX(), point.getY(), yz, scalar, -100, 100);
//        ellipse(point.getX() + xoff, point.getY() + yoff, dotSizing, dotSizing);
//      }
//    }

    for (int x = 1; x < grid.getWidth() - 1; x++) {
      for (int y = 1; y < grid.getHeight() - 1; y++) {
        Point upLeft = grid.getPoint(x, y);
        Point upRight = grid.getPoint(x + 1, y);
        Point lowLeft = grid.getPoint(x, y + 1);
        Point lowRight = grid.getPoint(x + 1, y + 1);
        float upLeftXOff = ns(upLeft.getX(), upLeft.getY(), xz, scalar, -100, 100);
        float upLeftYOff = ns(upLeft.getX(), upLeft.getY(), yz, scalar, -100, 100);
        float upRightXOff = ns(upRight.getX(), upRight.getY(), xz, scalar, -100, 100);
        float upRightYOff = ns(upRight.getX(), upRight.getY(), yz, scalar, -100, 100);
        float lowLeftXOff = ns(lowLeft.getX(), lowLeft.getY(), xz, scalar, -100, 100);
        float lowLeftYOff = ns(lowLeft.getX(), lowLeft.getY(), yz, scalar, -100, 100);
        float lowRightXOff = ns(lowRight.getX(), lowRight.getY(), xz, scalar, -100, 100);
        float lowRightYOff = ns(lowRight.getX(), lowRight.getY(), yz, scalar, -100, 100);
        quad(upLeft.getX() + upLeftXOff + lineSpacing,
                upLeft.getY() + upLeftYOff + lineSpacing,
                upRight.getX() + upRightXOff - lineSpacing,
                upRight.getY() + upRightYOff + lineSpacing,
                lowRight.getX() + lowRightXOff - lineSpacing,
                lowRight.getY() + lowRightYOff + lineSpacing,
                lowLeft.getX() + lowLeftXOff + lineSpacing,
                lowLeft.getY() + lowLeftYOff - lineSpacing);
      }
    }

    xz = map(mouseX, 0, width, 0, 200);
    yz = map(mouseY, 0, height, 0, 200);
  }

  public void keyPressed() {
    if (key == CODED) {
      if (keyCode == UP) {
        scalar += 0.001f;
      }
      else if (keyCode == DOWN) {
        scalar -= 0.001f;
      }

      System.out.println(String.format("Scalar: %s", scalar));
    }
  }

  public static void main(String[] args) {
    PApplet.main("org.example.PilotPerlinGrid");
  }
}