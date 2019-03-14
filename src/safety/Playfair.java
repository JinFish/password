package safety;

import java.util.Arrays;
import java.util.Scanner;

public class Playfair {
	static char [][]table= {{'M','O','N','A','R'},
						    {'C','H','Y','B','D'},
							{'E','F','G','I','K'},
							{'L','P','Q','S','T'},
							{'U','V','W','X','Z'}};
	
	static char[] removeDup(char[]text) {
		char[]text_real=new char[text.length+1];
		for(int i=0;i<text.length-1;i=i+2) {
			if(text[i]==text[i+1]) {
				for(int j=0;j<=i;j++) {
					text_real[j]=text[j];
				}
				text_real[i+1]='x';
				for(int j=i+1;j<text.length;j++) {
					text_real[j+1]=text[j];
				}
				return removeDup(text_real);
			}
		}
		return text;
	}
	
	static char [] isOdd(char []text) {
		if (text.length%2==1) {
			char[]text_real=new char[text.length+1];
			for(int i=0;i<text.length;i++) {
				text_real[i]=text[i];
			}
			if (text[text.length-1]=='z') {
				text_real[text.length]='a';
			}else {
				text_real[text.length]=(char)((byte)(text[text.length-1])+1);
			}
			
			return text_real;
		}
		return text;
	}
	
	static char[] encryption(char[]text) {
		char []text_real=new char[text.length];
		for (int i = 0; i < text_real.length; i=i+2) {
			int []num1=new int[2];
			int []num2=new int[2];
			for(int j=0;j<table.length;j++) {
				for(int k=0;k<table[j].length;k++) {
					if(text[i]==table[j][k]) {
						num1[0]=j;
						num1[1]=k;
					}
					if (text[i]=='j') {
						num1[0]=2;
						num1[1]=3;
					}
					if (text[i+1]==table[j][k]) {
						num2[0]=j;
						num2[1]=k;
					}
					if (text[i+1]=='j') {
						num2[0]=2;
						num2[1]=3;
					}
				}
			}
			if (num1[0]==num2[0]) {
				text_real[i]=table[num1[0]][(num1[1]+1)%5];
				text_real[i+1]=table[num2[0]][(num2[1]+1)%5];
			}else if (num1[1]==num2[1]) {
				text_real[i]=table[(num1[0]+1)%5][num1[1]];
				text_real[i+1]=table[(num2[0]+1)%5][num2[1]];
			}else {
				text_real[i]=table[num1[0]][num2[1]];
				text_real[i+1]=table[num2[0]][num1[1]];
			}
		}
		return text_real;
	}
	
	static char[] decryption(char[]text) {
		char []text_real=new char[text.length];
		for (int i = 0; i < text_real.length; i=i+2) {
			int []num1=new int[2];
			int []num2=new int[2];
			for(int j=0;j<table.length;j++) {
				for(int k=0;k<table[j].length;k++) {
					if(text[i]==table[j][k]) {
						num1[0]=j;
						num1[1]=k;
					}
					if (text[i]=='j') {
						num1[0]=2;
						num1[1]=3;
					}
					if (text[i+1]==table[j][k]) {
						num2[0]=j;
						num2[1]=k;
					}
					if (text[i+1]=='j') {
						num2[0]=2;
						num2[1]=3;
					}
				}
			}
			if (num1[0]==num2[0]) {
				if (num1[1]==0) {
					text_real[i]=table[num1[0]][4];
				}else {
					text_real[i]=table[num1[0]][(num1[1]-1)%5];
				}
				if (num2[1]==0) {
					text_real[i+1]=table[num2[0]][4];
				}else {
					text_real[i+1]=table[num2[0]][(num2[1]-1)%5];
				}
				
			}else if (num1[1]==num2[1]) {
				if (num1[0]==0) {
					text_real[i]=table[4][num1[1]];
				}else {
					text_real[i]=table[(num1[0]-1)%5][num1[1]];
				}
				if (num2[0]==0) {
					text_real[i+1]=table[4][num2[1]];
				}else {
					text_real[i+1]=table[(num2[0]-1)%5][num2[1]];
				}
				
				
			}else {
				text_real[i]=table[num1[0]][num2[1]];
				text_real[i+1]=table[num2[0]][num1[1]];
			}
		}
		return text_real;
	}
	
	public static void main(String[] args) {
		System.out.println("����������");
		Scanner scanner=new Scanner(System.in);
		String text=scanner.next();
		//��ת��Ϊȫ��Сд
		char[]test=text.toLowerCase().toCharArray();
		
		//ȥ��
		char[]test_rem=removeDup(test);
		
		//�ж��Ƿ�Ϊ������
		char[]test_odd=isOdd(test_rem);
		
		//ת��Ϊȫ����д
		char test_real[]=new String(test_odd).toUpperCase().toCharArray();
		
		//����
		char[] test_encry=encryption(test_real);
		System.out.print("����Ϊ:");
		for (int i = 0; i < test_encry.length; i++) {
			System.out.print(test_encry[i]);
		}
		
		//����
		char[]test_decry=new String(test_encry).toLowerCase().toCharArray();
		char []test_decry_real=decryption(test_decry);
		
		System.out.println("����Ϊ:");
		for (int i = 0; i < test_decry_real.length; i++) {
			System.out.print(test_decry_real[i]);
		}
		
	}
	
	}

