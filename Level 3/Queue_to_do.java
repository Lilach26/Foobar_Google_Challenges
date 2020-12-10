
public class Solution3 
{
	public static int solution(int start, int length)
	{
		int i, j, xor = start, temp = start;
		
		for (i = 0; i < length; i++)
		{
			for (j = 0; j < length + 1; j++)
			{
				if (i == 0 && j == 0)
				{
					temp++;
					continue;
				}
				
				if ((j + i) % length == 0 && j != 0)
					break;
				
				xor ^= temp;
				temp++;
			}
			temp += i;
		}	

		return xor;
	}
