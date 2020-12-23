import java.math.BigDecimal;
import java.math.BigInteger;

public class Solution6 
{
	private static BigDecimal sqrtTwo = new BigDecimal("1.41421356237309504880168872420969807856967187537694807317667973799073247846210703885038753432764157273501384623");
	private static BigInteger zero = BigInteger.ZERO;
	private static BigInteger one = BigInteger.ONE;
	
	public static String solution(String s)
	{
		BigInteger result = new BigInteger(s);
		return BeattySequenceA001951(result).toString();
	}
	
	public static BigInteger BeattySequenceA001951(BigInteger number)
	{
		if (number.equals(zero) || number.compareTo(zero) < 0)
			return zero;
		
		BigInteger term = sqrtTwo.subtract(BigDecimal.ONE).multiply(new BigDecimal(number)).toBigInteger();
		BigInteger first = number.multiply(number.add(one)).shiftRight(1);
		BigInteger second = term.multiply(term.add(one)).shiftRight(1);
		
		return number.multiply(term).add(first).subtract(second).subtract(BeattySequenceA001951(term));
	}
	
	public static void main(String[] args)
	{
		System.out.println(solution("77"));
    System.out.print(solution("5"));
	}
}
