//For bob to decrypt ciphertext

import java.io.*;
import java.util.Scanner;

class Autokey{
	public static String autoDecryption(String msg, String key) 
	{ 
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
		String currentKey = key; 
		String decryptMsg = ""; 
		for (int x = 0; x < msg.length(); x++) { 
			int get1 = alphabet.indexOf(msg.charAt(x)); 
			int get2 = alphabet.indexOf(currentKey.charAt(x)); 
			int total = (get1 - get2) % 26; 
			total = (total < 0) ? total + 26 : total; 
			decryptMsg += alphabet.charAt(total); 
			currentKey += alphabet.charAt(total); 
		} 
		return decryptMsg; 
	} 
}
class vigenre
{ 
	static String generateKey(String str, String key) 
	{ 
	    int x = str.length(); 
	  
	    for (int i = 0; ; i++) 
	    { 
	        if (x == i) 
	            i = 0; 
	        if (key.length() == str.length()) 
	            break; 
	        key+=(key.charAt(i)); 
	    } 
	    return key; 
	} 
 
static String originalText(String cipher_text, String key) 
{ 
    String orig_text=""; 
  
    for (int i = 0 ; i < cipher_text.length() &&  
                            i < key.length(); i++) 
    { 
        
        int x = (cipher_text.charAt(i) -  
                    key.charAt(i) + 26) %26; 
  
        
        x += 'A'; 
        orig_text+=(char)(x); 
    } 
    return orig_text.toLowerCase(); 
} 
}
class Playfair
{
    private String KeyWord        = new String();
    private String Key            = new String();
    private char   matrix_arr[][] = new char[5][5];
 
    public void setKey(String k)
    {
        String K_adjust = new String();
        boolean flag = false;
        K_adjust = K_adjust + k.charAt(0);
        for (int i = 1; i < k.length(); i++)
        {
            for (int j = 0; j < K_adjust.length(); j++)
            {
                if (k.charAt(i) == K_adjust.charAt(j))
                {
                    flag = true;
                }
            }
            if (flag == false)
                K_adjust = K_adjust + k.charAt(i);
            flag = false;
        }
        KeyWord = K_adjust;
    }
 
    public void KeyGen()
    {
        boolean flag = true;
        char current;
        Key = KeyWord;
        for (int i = 0; i < 26; i++)
        {
            current = (char) (i + 97);
            if (current == 'j')
                continue;
            for (int j = 0; j < KeyWord.length(); j++)
            {
                if (current == KeyWord.charAt(j))
                {
                    flag = false;
                    break;
                }
            }
            if (flag)
                Key = Key + current;
            flag = true;
        }
        matrix();
    }
 
    private void matrix()
    {
        int counter = 0;
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                matrix_arr[i][j] = Key.charAt(counter);
                counter++;
            }
            System.out.println();
        }
    }
 
    private String format(String old_text)
    {
        int i = 0;
        int len = 0;
        String text = new String();
        len = old_text.length();
        for (int tmp = 0; tmp < len; tmp++)
        {
            if (old_text.charAt(tmp) == 'j')
            {
                text = text + 'i';
            }
            else
                text = text + old_text.charAt(tmp);
        }
        len = text.length();
        for (i = 0; i < len; i = i + 2)
        {
            if (text.charAt(i + 1) == text.charAt(i))
            {
                text = text.substring(0, i + 1) + 'x' + text.substring(i + 1);
            }
        }
        return text;
    }
 
    private String[] Divid2Pairs(String new_string)
    {
        String Original = format(new_string);
        int size = Original.length();
        if (size % 2 != 0)
        {
            size++;
            Original = Original + 'x';
        }
        String x[] = new String[size / 2];
        int counter = 0;
        for (int i = 0; i < size / 2; i++)
        {
            x[i] = Original.substring(counter, counter + 2);
            counter = counter + 2;
        }
        return x;
    }
 
    public int[] GetDiminsions(char letter)
    {
        int[] key = new int[2];
        if (letter == 'j')
            letter = 'i';
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                if (matrix_arr[i][j] == letter)
                {
                    key[0] = i;
                    key[1] = j;
                    break;
                }
            }
        }
        return key;
    }

 
    public String decryptMessage(String Code)
    {
        String Original = new String();
        String src_arr[] = Divid2Pairs(Code);
        char one;
        char two;
        int part1[] = new int[2];
        int part2[] = new int[2];
        for (int i = 0; i < src_arr.length; i++)
        {
            one = src_arr[i].charAt(0);
            two = src_arr[i].charAt(1);
            part1 = GetDiminsions(one);
            part2 = GetDiminsions(two);
            if (part1[0] == part2[0])
            {
                if (part1[1] > 0)
                    part1[1]--;
                else
                    part1[1] = 4;
                if (part2[1] > 0)
                    part2[1]--;
                else
                    part2[1] = 4;
            }
            else if (part1[1] == part2[1])
            {
                if (part1[0] > 0)
                    part1[0]--;
                else
                    part1[0] = 4;
                if (part2[0] > 0)
                    part2[0]--;
                else
                    part2[0] = 4;
            }
            else
            {
                int temp = part1[1];
                part1[1] = part2[1];
                part2[1] = temp;
            }
            Original = Original + matrix_arr[part1[0]][part1[1]]
                    + matrix_arr[part2[0]][part2[1]];
        }
        return Original;
    }
}
class Hill {
	
	int[] lm;
    int[][] km;    
    int[] rm;
    int [][] invK;
    
    public void performDivision(String temp, int s)
    {
        while (temp.length() > s)
        {
            String line = temp.substring(0, s);
            temp = temp.substring(s, temp.length());
            calLineMatrix(line);
            multiplyLineByInvKey(line.length());
            showResult(line.length());
        }
        if (temp.length() == s){
            
            calLineMatrix(temp);
            this.multiplyLineByInvKey(temp.length());
            showResult(temp.length());
 
                
            
        }
        else if (temp.length() < s)
        {
            for (int i = temp.length(); i < s; i++)
                temp = temp + 'x';
            calLineMatrix(temp);
            multiplyLineByInvKey(temp.length());
            showResult(temp.length());
            
                
        }
    }
 
 
    public void calKeyMatrix(String key, int len)
    {
        km = new int[len][len];
        int k = 0;
        for (int i = 0; i < len; i++)
        {
            for (int j = 0; j < len; j++)
            {
                km[i][j] = ((int) key.charAt(k)) - 97;
                k++;
            }
        }
    }
 
    public void calLineMatrix(String line)
    {
        lm = new int[line.length()];
        for (int i = 0; i < line.length(); i++)
        {
            lm[i] = ((int) line.charAt(i)) - 97;
        }
    }
 

    public void multiplyLineByInvKey(int len)
    {
        
        rm = new int[len];
        for (int i = 0; i < len; i++)
        {
            for (int j = 0; j < len; j++)
            {
                rm[i] += invK[i][j] * lm[j];
            }
            rm[i] %= 26;
        }
    }
    
 
    public void showResult(int len)
    {
        String result = "";
        for (int i = 0; i < len; i++)
        {
            result += (char) (rm[i] + 97);
        }
        System.out.print(result);
    }
 
   
 
    public int calDeterminant(int A[][], int N)
    {
        int resultOfDet;
        switch (N) {
            case 1:
                resultOfDet = A[0][0];
                break;
            case 2:
                resultOfDet = A[0][0] * A[1][1] - A[1][0] * A[0][1];
                break;
            default:
                resultOfDet = 0;
                for (int j1 = 0; j1 < N; j1++)
                {
                    int m[][] = new int[N - 1][N - 1];
                    for (int i = 1; i < N; i++)
                    {
                        int j2 = 0;
                        for (int j = 0; j < N; j++)
                        {
                            if (j == j1)
                                continue;
                            m[i - 1][j2] = A[i][j];
                            j2++;
                        }
                    }
                    resultOfDet += Math.pow(-1.0, 1.0 + j1 + 1.0) * A[0][j1]
                            * calDeterminant(m, N - 1);
                }   break;
        }
        return resultOfDet;
    }
 
    public void cofact(int num[][], int f)
    {
        int b[][], fac[][];
        b = new int[f][f];
        fac = new int[f][f];
        int p, q, m, n, i, j;
        for (q = 0; q < f; q++)
        {
            for (p = 0; p < f; p++)
            {
                m = 0;
                n = 0;
                for (i = 0; i < f; i++)
                {
                    for (j = 0; j < f; j++)
                    {
                        b[i][j] = 0;
                        if (i != q && j != p)
                        {
                            b[m][n] = num[i][j];
                            if (n < (f - 2))
                                n++;
                            else
                            {
                                n = 0;
                                m++;
                            }
                        }
                    }
                }
                fac[q][p] = (int) Math.pow(-1, q + p) * calDeterminant(b, f - 1);
            }
        }
        trans(fac, f);
    }
 
    void trans(int fac[][], int r)
    {
        int i, j;
        int b[][], inv[][];
        b = new int[r][r];
        inv = new int[r][r];
        int d = calDeterminant(km, r);
        int mi = mi(d % 26);
        mi %= 26;
        if (mi < 0)
            mi += 26;
        for (i = 0; i < r; i++)
        {
            for (j = 0; j < r; j++)
            {
                b[i][j] = fac[j][i];
            }
        }
        for (i = 0; i < r; i++)
        {
            for (j = 0; j < r; j++)
            {
                inv[i][j] = b[i][j] % 26;
                if (inv[i][j] < 0)
                    inv[i][j] += 26;
                inv[i][j] *= mi;
                inv[i][j] %= 26;
            }
        }

        
        invK = inv;
    }
 
    public int mi(int d)
    {
        int q, r1, r2, r, t1, t2, t;
        r1 = 26;
        r2 = d;
        t1 = 0;
        t2 = 1;
        while (r1 != 1 && r2 != 0)
        {
            q = r1 / r2;
            r = r1 % r2;
            t = t1 - (t2 * q);
            r1 = r2;
            r2 = r;
            t1 = t2;
            t2 = t;
        }
        return (t1 + t2);
    }
 
    public void matrixtoinvkey(int inv[][], int n)
    {
        String invkey = "";
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                invkey += (char) (inv[i][j] + 97);
            }
        }
    }
     public boolean check(String key, int len)
    {
        calKeyMatrix(key, len);
        int d = calDeterminant(km, len);
        d = d % 26;
        if (d == 0)
        {
            System.out.println("Key is not invertible");
            return false;
        }
        else if (d % 2 == 0 || d % 13 == 0)
        {
            System.out.println("Key is not invertible");
            return false;
        }
        else
        {
            return true;
        }
    }
     
}

public class Decrypt{
	public static void main(String[] args) 
	{ 
	char choice;
	Scanner sc = new Scanner(System.in);
    System.out.println("Enter a for Autokey decryption.");
    System.out.println("Enter h for hill cipher decrytion.");
    System.out.println("Enter v for vigenere decryption.");
	System.out.println("Enter p for playfair decryption.");
	String st = sc.next();
	choice = st.charAt(0);
	System.out.println("Enter ciphertext to decrypt");
	String enc = sc.next();
	System.out.println("Enter key to decrypt");
	String key = sc.next();
	switch(choice) {
	case 'a':
	enc = enc.toUpperCase(); 
	key = key.toUpperCase(); 
	String msg = Autokey.autoDecryption(enc, key); 
	System.out.println("Original/Decrypted Text : "+ msg.toLowerCase());
	break;
	case 'v':
		String cipher_text = enc.toUpperCase(); 
	    key = key.toUpperCase() ;
	    key = vigenre.generateKey(cipher_text, key);
		System.out.println("Original/Decrypted Text : "+ vigenre.originalText(cipher_text, key));
		break;
	case 'p':
	   Playfair x = new Playfair();
       x.setKey(key);
       x.KeyGen();
       System.out.println("Original/Decrypted Text : "+ x.decryptMessage(enc));
       break;
	case 'h':
		Hill obj = new Hill();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        double sq = Math.sqrt(key.length());
        int size = (int) sq;
        if (obj.check(key, size))
            {
                System.out.print("Original/Decrypted Text : ");
                obj.cofact(obj.km, size);
                obj.performDivision(enc, size);
            }
        break;
		
	}
}
}

