package org.example;

import java.util.ArrayList;
import java.util.List;

public class Grid {

  private List<List<Point>> points;

  public Grid(List<List<Point>> points) {
    this.points = points;
  }

  public static Grid newGrid(int width, int height, int spacing) {
    List<List<Point>> points = new ArrayList<>();

    for (int i = 0; i < width; i += spacing) {
      List<Point> column = new ArrayList<>();
      for (int j = 0; j < height; j += spacing) {
        column.add(new Point(i, j));
      }
      points.add(column);
    }

    return new Grid(points);
  }

  public int getWidth() {
    return this.points.size();
  }

  public int getHeight() {
    return points.size() > 1 ? points.get(0).size() : 0;
  }

  public Point getPoint(int x, int y) {
    return this.points.get(x).get(y);
  }

}
