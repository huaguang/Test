import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
//TO DO:
//调用RSA算法生成公钥，私钥，同时使用公私钥加密与解密测试。
public class Key {
	KeyPairGenerator keyPairGen=null;
	KeyPair keypair=null;
	PublicKey pk=null;
	PrivateKey sk=null;
	byte[]pkData=null;
	byte[]skData=null;
	Cipher cipher=null;
	byte[]buffer=null;
	@SuppressWarnings("static-access")
	public Key(){
		try {
			keyPairGen=KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		keyPairGen.initialize(512);
		keypair=keyPairGen.generateKeyPair();
		pk=keypair.getPublic();
		sk=keypair.getPrivate();
		pkData=pk.getEncoded();
		skData=sk.getEncoded();
		System.out.println(pk.toString());
	/*	int temp=0;
		for(int i=0;i<pkData.length;i++){
			System.out.println((pkData[i]&0xf)+"  "+(pkData[i]>>4));
		}*/
		//下面进行加密、解密测试。
		//1.生成加解密工具实例cipher.
		try {
			cipher=Cipher.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2.初始化cipher为加密，使用私钥
		try {
			cipher.init(cipher.ENCRYPT_MODE,sk);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//3.加密
		try {
			buffer=cipher.doFinal("you are stupid!".getBytes());
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("加密结果：\t");
		for(int i=0;i<buffer.length;i++){
			System.out.print(buffer[i]);
		}
		System.out.println();
		//4.重新使用公钥初始化，为解密模式
		try {
			cipher.init(Cipher.DECRYPT_MODE, pk);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//5.解密。
		try {
			buffer=cipher.doFinal(buffer);
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("解密结果："+new String(buffer));
		
	}
}
