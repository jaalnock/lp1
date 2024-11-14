import java.util.*;

public class fractional_knap{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of objects:-");
        int n = sc.nextInt();
        System.out.println("Enter the max weight");
        int W = sc.nextInt();

        int p[]=new int[n];
        int w[] = new int[n];
        float x[] =new float[n];

        System.out.println("Enter the weights of objects:- ");
        for (int i = 0; i < n; i++) {
            w[i] = sc.nextInt();
        }
        System.out.println("Enter the profit of objects:- ");
        for (int i = 0; i < n; i++) {
            p[i] = sc.nextInt();
        }

        float k[]=new float[n];
        for (int i = 0; i < n; i++) {
            k[i] = (float) p[i] / w[i];
        }
        for (int i=0;i<n-1;i++){
            for(int j=0;j<n-i-1;j++){
                if(k[j]<k[j+1]){
                    float temp=k[j];
                    k[j]=k[j+1];
                    k[j+1]=temp;

                    int temp1=w[j];
                    w[j]=w[j+1];
                    w[j+1]=temp1;

                    int temp2=p[j];
                    p[j]=p[j+1];
                    p[j+1]=temp2;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (w[i] <= W) {
                x[i] = 1;
                W -= w[i];
            } else if (w[i] > W && W > 0) {
                x[i] = (float) W / w[i];
                W = 0;
            } else {
                x[i] = 0;
            }
        }

        float max_value = 0;
        for (int i = 0; i < n; i++) {
            max_value += p[i] * x[i];
        }
        System.out.println("The maximum profit is:- " + max_value);
        sc.close();
    }
}
