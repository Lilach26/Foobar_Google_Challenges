import java.util.Base64;

public class Solution7 
{
	public static void main(String[] args)
	{
		String encrypted_message ="<Your message>".replace(" ", "");
		byte[] key = "<Your google userName>".getBytes();
		byte[] decodedBytes = Base64.getDecoder().decode(encrypted_message);
		byte[] message = new byte[decodedBytes.length];
		
		for (int i = 0, j = 0; i < message.length; i++, j = (j + 1) % key.length)
			message[i] = (byte)(decodedBytes[i] ^ key[j]); 
		
		System.out.print(new String(message));	
	}
}
