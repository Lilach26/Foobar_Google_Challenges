import java.util.HashMap;

public class Solution
{
	public static int[] solution(int[] data, int n)
	{
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int i, temp = 0, index = 0, size = data.length, count;
		
		//check edge case - if we get n = 0 or an empty array, return empty array
		if (n == 0 || size == 0)
		{
			System.out.print("");
			return data;
		}
		
		//insert into the HashMap the occurrence of each array's element
		for (i = 0; i < size; i++)
		{
			if (map.containsKey(data[i]))
			{
				count = map.get(data[i]);
				map.put(data[i], count + 1);
			}
			
			else
				map.put(data[i], 1);
		}
		
		//count how many elements should be in the new array
		for (i = 0; i < size; i++)
		{
			if (map.get(data[i]) <= n)
			{
				temp++;
			}
		}
		
		//create new array with the compatible size
		int[] newArr = new int[temp];
		
		//insert the elements which thier occurrences in the original array are less or equal to n
		for (i = 0; i < size; i++)
		{
			if (map.get(data[i]) <= n)
			{
				newArr[index] = data[i];
				index++;
			}
		}
		
		return newArr;
	}
}
