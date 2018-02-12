package com.cj.pc;

public class IE_Browser {
	public static void main(String[] args) {
		
		square();
	}
	public static void square()
	{

		java.util.Scanner scanner = new java.util.Scanner(System.in); 
		int gugu = scanner.nextInt();
		
		for(int i=1;i<=gugu;i++)
		{
			for(int n=1;n<=9;n++)
			{
				System.out.println(i*n);
			}
			System.out.println("\n");
		}
	}

	}
