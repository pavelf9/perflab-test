package task2;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Task2 {

    public static void main(String[] args) {
        float xCircle = 0;
        float yCircle = 0;
        float circleRadius = 0;

        ArrayList<float[]> points = new ArrayList<>();

        try {
            Scanner scannerCircle = new Scanner(new File(args[0]));
            {
                String[] circlePosition = scannerCircle.nextLine().split(" ");
                xCircle = Float.parseFloat(circlePosition[0]);
                yCircle = Float.parseFloat(circlePosition[1]);
                circleRadius = Float.parseFloat(scannerCircle.nextLine());
            }
            scannerCircle.close();

            Scanner scannerPoints = new Scanner(new File(args[1]));
            {
                while (scannerPoints.hasNextLine()) {
                    String[] pointPosition = scannerPoints.nextLine().split(" ");
                    points.add(new float[]{Float.parseFloat(pointPosition[0]), Float.parseFloat(pointPosition[1])});
                    if (points.size() == 100) {
                        break;
                    }
                }
            }
            scannerPoints.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (float[] point : points) {
            System.out.println(inside(xCircle, yCircle, circleRadius, point[0], point[1]));
        }
    }

    private static int inside(float xCircle,
                              float yCircle,
                              float circleRadius,
                              float pointX,
                              float pointY) {
        double sqrtRadius = circleRadius * circleRadius;
        double result = ((xCircle - pointX) * (xCircle - pointX) + (yCircle - pointY) * (yCircle - pointY));

        if (result < sqrtRadius) {
            return 1;
        } else if (result > sqrtRadius) {
            return 2;
        } else {
            return 0;
        }
    }
}