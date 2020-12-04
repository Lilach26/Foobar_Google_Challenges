import java.util.HashMap;

public class Solution
{
	public static int[] solution(int[] data, int n)
	{
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int i, temp = 0, index = 0, size = data.length, count;
		
		if (n == 0 || size == 0)
		{
			System.out.print("");
			return data;
		}
		
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
		
		for (i = 0; i < size; i++)
		{
			if (map.get(data[i]) <= n)
			{
				temp++;
			}
		}
		
		int[] newArr = new int[temp];
		
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















