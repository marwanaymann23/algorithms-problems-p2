	import java.util.*;

public class problem_5 {
	
	    static int maxCaught(char arr[], int n, int k)
	    {
	        int result = 0;
	        ArrayList<Integer> thief = new ArrayList<Integer>();
	        ArrayList<Integer> police = new ArrayList<Integer>();

	        for (int i = 0; i < n; i++) {
	            if (arr[i] == 'P')
	            	police.add(i);
	            else if (arr[i] == 'T')
	            	thief.add(i);
	        } 

	        int t_counter = 0, p_counter = 0;
	        while (t_counter < thief.size() && p_counter < police.size()) {

	            if (Math.abs(thief.get(t_counter) - police.get(p_counter)) <= k) {
	            	result++;
	            	t_counter++;
	            	p_counter++;
	            } 
	           
	            else if (thief.get(t_counter) < police.get(p_counter))
	            	t_counter++;
	            else
	            	p_counter++;
	        }
	        return result;
	    }


	    public static void main(String args[])
	    {
	        int k, n;
	        char arr1[] =new char[] { 'P', 'T', 'T',  'P', 'T' };
	        k = 1;
	        n = 5;
	        System.out.println("Maximum thieves caught: " +maxCaught(arr1, n, k));
	                            
	        char arr2[] =new char[] { 'T', 'T', 'P', 'P', 'T', 'P' };
	        k = 2;
	        n = 6;
	        System.out.println("Maximum thieves caught: " +maxCaught(arr2, n, k));
	                            
	    }
	}