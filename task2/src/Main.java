import java.io.*;

public class Main {

    // circle parameters
    static float x0 = 0;
    static float y0 = 0;
    static float radius= 0;

    public static void main(String[] args) {

        float x = 0, y = 0; // current point coordinates
        int numPoints = 0; // amount of points
        BufferedReader reader;

        //Fill circle parameters from first file
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
            String inputStr = reader.readLine();
            String[] inputArgs = inputStr.split(" ");
            x0 = Float.parseFloat(inputArgs[0]);
            //System.out.println(x0);
            y0 = Float.parseFloat(inputArgs[1]);
            //System.out.println(y0);
            radius = Float.parseFloat(reader.readLine());
            //System.out.println(radius);
            reader.close();

        } catch (Exception e) {
            System.out.println("Error reading first file! " + e.getMessage());
        }

        //Read points parameters from second file and calculate pos
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[1])));
            for (String inputStr = ""; (inputStr = reader.readLine()) != null; numPoints++) {
                String[] inputArgs = inputStr.split(" ");
                x = Float.parseFloat(inputArgs[0]);
                y = Float.parseFloat(inputArgs[1]);
                System.out.println(posRelativeToCircle(x, y));
            }
            reader.close();

        } catch (Exception e) {
            System.out.println("Error reading second file! " + e.getMessage());
        }

        if (numPoints == 0) {
            System.out.println("No points in the second file!");
        }

    }

    private static int posRelativeToCircle(float x, float y) {

        if (Math.pow(x-x0, 2) + Math.pow(y-y0, 2) == radius*radius)
            return 0;
        else {
            if (Math.pow(x - x0, 2) + Math.pow(y - y0, 2) < radius * radius) return 1;
            else return 2;
        }

    }
}
