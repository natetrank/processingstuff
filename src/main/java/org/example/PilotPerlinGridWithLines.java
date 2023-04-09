package org.example;

import processing.core.PApplet;

public class PilotPerlinGridWithLines extends PApplet {

  float xz = 1000;
  float yz = 1000;
  int spacing = 50;
  float thickness = 0.3f;
  float scalar = 0.001f;
  float colorz = 1000;
  int color1;
  int color2;

  public void settings() {
    size(900, 600);
  }

  public void setup() {
    color1 = color(0, 0, 0);
    color2 = color(255, 0, 0);
  }

  private float ns(int x, int y, float z, float scalar, int minVal, int maxVal) {
    return map(noise(x*scalar, y*scalar, z*scalar),
            0, 1,
            minVal, maxVal);
  }

  public void draw() {
    background(0);
    stroke(255);
    strokeWeight(thickness);

    for (int x = 0; x < width; x+=spacing) {
      for (int y = 0; y < height; y+=spacing) {
        float xOff = ns(x, y, xz, scalar, -100, 100);
        float yOff = ns(x, y, yz, scalar, -100, 100);
        stroke(lerpColor(color1, color2, noise(x, y, colorz)));

        if (x < width - spacing) {
          float rightXOff = ns(x + spacing, y, xz, scalar, -100, 100);
          float rightYOff = ns(x + spacing, y, yz, scalar, -100, 100);
          line(x + xOff, y + yOff,
                  x + spacing + rightXOff, y + rightYOff);
        }
        
        if (y < height - spacing) {
          float downXOff = ns(x, y + spacing, xz, scalar, -100, 100);
          float downYOff = ns(x, y + spacing, yz, scalar, -100, 100);
          line(x + xOff, y + yOff,
                  x + downXOff, y + spacing + downYOff);
        }
      }
    }

    xz = map(mouseX, 0, width, 0, 200);
    yz = map(mouseY, 0, height, 0, 200);
    colorz += 0.05f;
  }

  public void keyPressed() {
    if (key == CODED) {
      switch (keyCode) {
        case UP -> scalar += 0.001f;
        case DOWN -> scalar -= 0.001f;
        case LEFT -> {
          if ((thickness -= 0.5f) < 0) {
            thickness = 0f;
          }
        }
        case RIGHT -> thickness += 0.5f;
        case SHIFT -> {
          if ((spacing = spacing / 2) < 4) {
            spacing = 4;
          }
        }
        case CONTROL -> spacing = spacing * 2;
        default -> {
        }
      }
    }

    System.out.println(String.format("Scalar: %s", scalar));
    System.out.println(String.format("Thickness: %s", thickness));
    System.out.println(String.format("Spacing: %s", spacing));
  }

  public static void main(String[] args) {
    PApplet.main("org.example.PilotPerlinGridWithLines");
  }
}