
public class Decryption {
	private Key receiverKey;
	private Key senderKey;
	private String message = "", encryption = "", senderPublic;
	private final int KEY_LENGTH = 6;
	
	public Decryption(String recieverPrivate, String senderPublic, String encryption){
		this.encryption= encryption;
		this.senderPublic = senderPublic;
		this.receiverKey = new Key(recieverPrivate); //creates a key for the receiver so the public key can be generated for decoding
		decryptMessage();
	}

	private void decryptMessage() { //uses the receivers public key to decoding the message
		
		for(int i = 0; i < encryption.length(); i++){
			int n = (int)encryption.charAt(i);
			char c = receiverKey.getPublicKey().charAt(i%KEY_LENGTH);

			int cnum = characterToDigit(c);
			n -= cnum;
			if(n < 32){
				n+=95;
			}
			message = message + new String(Character.toChars(n));
			
		}
		//checks to see if the signature to the message corresponds to the public key of the sender
		senderKey = new Key(message.substring(message.length() - 6),senderPublic);	
		if(senderKey.validateKeys()){
			message = message.substring(0, message.length() - 6);
		}else{
			message = "Error. Keys invalid";
		}
	
	}
		

	private static int characterToDigit(char c){//indexing characters to digits for decoding
		switch(c){
		case '1': return 1; 
		case '2': return 2; 
		case '3': return 3;
		case '4': return 4;
		case '5': return 5;
		case '6': return 6;
		case '7': return 7;
		case '8': return 8;
		case '9': return 9;
		case '0': return 0;
		case 'A': return 10;
		case 'B': return 11;
		case 'C': return 12;
		case 'D': return 13;
		case 'E': return 14;
		case 'F': return 15;
		case 'G': return 16;
		case 'H': return 17;
		case 'I': return 18;
		case 'J': return 19;
		case 'K': return 20;
		case 'L': return 21;
		case 'M': return 22;
		case 'N': return 23;
		case 'O': return 24;
		case 'P': return 25;
		case 'Q': return 26;
		case 'R': return 27;
		case 'S': return 28;
		case 'T': return 29;
		case 'U': return 30;
		case 'W': return 31;
		case 'X': return 32;
		case 'Y': return 33;
		case 'Z': return 34;
		default: return ' ';
		}
	}
	
	public String getMessage(){//getter for decoded message
			return message;
		}
	public String getEncryption(){//getter for encrypted message
		return encryption;
	}
	
}
	