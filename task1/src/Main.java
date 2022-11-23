public class Main {

    public static void main(String[] args) {

        int n, m;
        //check for numeric input values
        try {
            n = Integer.parseInt(args[0]);
            m = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid input command line arguments!");
            System.out.println(e.getMessage());
            return;
        }

        //Validate input values
        if (n < 1 || m < 1) {
            System.out.println("Input values is out of bounds!");
            return;
        }

        //Fill circle array
        int[] arr = new int[n];
        for (int i = 0; i < n; ) {
            arr[i] = ++i;
        }

        --m; //index shift for one interval
        int i = 0; //index of circle array
        int j = 0; //num of intervals

        do{
            System.out.print(arr[i]);
            j++;
            i = m*j % n;
        } while (i != 0);

    }

}
