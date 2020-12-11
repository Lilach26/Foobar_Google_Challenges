import java.math.BigInteger;

public class Solution4 
{
	public static int solution(String x)
	{
		BigInteger two = new BigInteger("2");
		BigInteger three = new BigInteger("3");
		BigInteger num = new BigInteger(x);
		int count = 0;

		if (num.compareTo(BigInteger.ONE) == -1)
			return 0;
		
		while (!num.equals(BigInteger.ONE))
		{
			if (num.equals(three))
			{
				count += 2;
				break;
			}
			
			if (num.mod(two).equals(BigInteger.ZERO))
			{
				num = num.divide(two);
				count++;
			}
			
			else
			{
				if (((num.add(BigInteger.ONE).divide(two).mod(two)).equals(BigInteger.ZERO)))
				{
					num = num.add(BigInteger.ONE);
					count++;
				}
				
				else
				{
					num = num.subtract(BigInteger.ONE);
					count++;
				}
			}
		}

		return count;
	}
