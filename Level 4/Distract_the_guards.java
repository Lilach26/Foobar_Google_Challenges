import java.util.ArrayList;
import java.util.HashSet;

public class Solution5 
{
	public static int solution(int[] banana_list)
	{
		int i, j, pair, index, size = banana_list.length, count = 0;
		ArrayList<HashSet<Integer>> matches = new ArrayList<HashSet<Integer>>();
		
		if (size == 1)
			return 1;
		
		if (size == 2)
		{
			if ((banana_list[0] == banana_list[1]))
				return 2;
			return checkLoop(banana_list[0], banana_list[1]) ? 0 : 2;
		}
		
		for (i = 0; i < size; i++)
		{
			matches.add(new HashSet<Integer>());
			
			for (j = 0; j < size; j++)
			{
				if (checkLoop(banana_list[i], banana_list[j]))
					matches.get(i).add(j);
			}
		}
		
		for (i = 0; i < size; i++)
		{
			pair = matches.get(i).size();
			if (pair <= 1)
				continue;
			
			while (pair >= 2)
			{
				index = -1;
				for (int iter : matches.get(i))
				{
					if (index == -1 || matches.get(iter).size() > matches.get(index).size())
						index = iter;
				}
				
				pair--;
				matches.get(i).remove(index);
				matches.get(index).remove(i);
			}
		}
		
		for (HashSet<Integer> match : matches)
		{
			if (match.isEmpty())
				count++;
		}
		
		return count;
	}

	public static boolean checkLoop(int a, int b)
	{
		int sum = a + b;
		while (sum % 2 == 0)
			sum /= 2;
		
		return (a % sum != 0) ? true : false;
	}
}
