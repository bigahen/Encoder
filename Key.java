import java.util.Random;

public class Key {
	private String privateKey = "";
	private String publicKey = "";
	private final int KEY_LENGTH = 6;
	
	public Key(){generateKeys();} //constructor for new key generation
	public Key(String privateKey, String publicKey){ //constructor for previously generated key
		this.privateKey = privateKey;
		this.publicKey = publicKey;
	}
	public Key(String privateKey){//constructor for when only private key is known
		this.privateKey = privateKey;
		publicKey = generatePublicKey();
	}
	
	private String generatePublicKey(){ //transforms private key into corresponding public key
		String pk = "";
		for(int i = 0; i < KEY_LENGTH; i++){
			int num = characterToDigitInitial(privateKey.charAt(i));
			if(num < 29){
				num+=6;
			}else{
				num-=29;
			}			
			if(num > 24){
				num-=(i+1);
				if(num < 25){
					num +=10;
				}
			}else{
				num-=(i+1);
				if(num<0){
					num+=25;
				}
			}		
			pk+= digitToCharacterSecondary(num);		
		}
		return pk;
	}
	
	private void generatePrivateKey(){ //generates random 6 character private key
		Random r = new Random();
		for(int i = 0; i < KEY_LENGTH; i++){
			char c = digitToCharacterInitial(r.nextInt(34));
			privateKey = privateKey + (c);
		}
		publicKey = generatePublicKey();
	}
	
	private void generateKeys(){ //method to call for new key pair
		generatePrivateKey();
	}
	
	public String getPublicKey(){ //publicKey getter
		return publicKey;
	}
	
	public String getPrivateKey(){ //privateKey getter
		return privateKey;
	}
	
	public boolean validateKeys(){ //method to validate that the two current keys pair together
		if(generatePublicKey().equals(publicKey)){
			return true;
		}
		return false;
	}
	
	private char digitToCharacterInitial(int num){ //initial transformation of digits to characters
		switch(num){
		case 1: return '1'; 
		case 2: return '2'; 
		case 3: return '3';
		case 4: return '4';
		case 5: return '5';
		case 6: return '6';
		case 7: return '7';
		case 8: return '8';
		case 9: return '9';
		case 0: return '0';
		case 10: return 'A';
		case 11: return 'B';
		case 12: return 'C';
		case 13: return 'D';
		case 14: return 'E';
		case 15: return 'F';
		case 16: return 'G';
		case 17: return 'H';
		case 18: return 'I';
		case 19: return 'J';
		case 20: return 'K';
		case 21: return 'L';
		case 22: return 'M';
		case 23: return 'N';
		case 24: return 'O';
		case 25: return 'P';
		case 26: return 'Q';
		case 27: return 'R';
		case 28: return 'S';
		case 29: return 'T';
		case 30: return 'U';
		case 31: return 'W';
		case 32: return 'X';
		case 33: return 'Y';
		case 34: return 'Z';
		default: return ' ';
		
		}
	}
	
	private int characterToDigitInitial(char c){//Initial transformation of characters to digits
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
	
	private char digitToCharacterSecondary(int num){//secondary transformation of digits to characters
		switch(num){
		case 25: return '1'; 
		case 26: return '2'; 
		case 27: return '3';
		case 28: return '4';
		case 29: return '5';
		case 30: return '6';
		case 31: return '7';
		case 32: return '8';
		case 33: return '9';
		case 34: return '0';
		case 24: return 'A';
		case 23: return 'B';
		case 22: return 'C';
		case 21: return 'D';
		case 20: return 'E';
		case 19: return 'F';
		case 18: return 'G';
		case 17: return 'H';
		case 16: return 'I';
		case 15: return 'J';
		case 14: return 'K';
		case 13: return 'L';
		case 12: return 'M';
		case 11: return 'N';
		case 10: return 'O';
		case 9: return 'P';
		case 8: return 'Q';
		case 7: return 'R';
		case 6: return 'S';
		case 5: return 'T';
		case 4: return 'U';
		case 3: return 'W';
		case 2: return 'X';
		case 1: return 'Y';
		case 0: return 'Z';
		default: return ' ';
		}
	}
}