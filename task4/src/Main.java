import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Integer> nums = new ArrayList<Integer>();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])))) {
            String inputStr;
            int sum = 0, average = 0, step = 0;

            while ((inputStr = reader.readLine()) != null) {
                int num = Integer.parseInt(inputStr);
                sum += num;
                nums.add(num);
            }
            average = sum / nums.size();
            for (Integer num : nums) {
                step += Math.abs(average-num);
            }

            System.out.println(step);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
