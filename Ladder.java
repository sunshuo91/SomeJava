import java.util.Arrays;

public class TESTING {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.print(stair(5));
	}
	
    static long stair(int n) {
        
    	
    	long count = 1;
    	int arr[] = new int[n];
    	
    	for(int i = 0; i < n; i++) {
    		arr[i] = 1;
    	}
    	
    	count = count + helper(arr,0);
        
    	return count;
    }
    
    static long helper(int arr[], int index) {
    	
    	long count = 0;
    	
    	System.out.println(Arrays.toString(arr));
    	
    	for(int i = index; i < arr.length-1; i++) {
    		if(arr[i]==1&&arr[i+1]==1) {
    			
    			int arr2[] = new int[arr.length - 1];
    			
    			for(int j = 0; j < i; j++) {
    				arr2[j] = arr[i];
    			}
    			arr2[i] = 2;
    			for(int j = i+1; j < arr2.length; j++) {
    				arr2[j] = arr[i+2];
    			}    			
    			count = count + 1 + helper(arr2, i);
    		}
    		
    		if(arr[i]==2&&arr[i+1]==1) {
    			
    			int arr2[] = new int[arr.length - 1];
    			
    			for(int j = 0; j < i; j++) {
    				arr2[j] = arr[i];
    			}
    			arr2[i] = 3;
    			for(int j = i+1; j < arr2.length; j++) {
    				arr2[j] = arr[i+2];
    			}    			
    			count = count + 1 + helper(arr2,i);
    		}
    	}
    	
    	return count;
    	
    }




}
