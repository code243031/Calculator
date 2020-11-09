package application;

import java.math.*;
import java.text.DecimalFormat;

public class Calculate {
	// �������� ��� ó���� ���� ���ǵ� Ŭ����
	// ��꿡 �ʿ��� ��ҵ�
	int calc_stack_dividend[] = new int[20];		// �Էµ� ���ڸ� �� �ϳ��� �����ϴ� ����
	String[] calc_stack_divisor = new String[15];	// �����ڸ� �����ϴ� ����
	int calc_stack_result[] = new int[20];			// �Էµ� ���ڸ� �ϳ��� ������ ����� �����ϴ� ����
	
	int top_dividend = 0;
	int top_divisor = 0;
	int top_result = 0;
	
	public Calculate() {
		
	}
	
	// ���� ����
	public int setPow(int a, int b) {
		int res= (int) Math.pow(a, b);
		
		return res;
	}
	
	// ��Ʈ����
	public int setSqrt(int a) {
		int res;

		res = (int) Math.sqrt(a);
		
		return res;
	}
	
	public double setSqrt(double a) {
		double res;
		
		res = Math.sqrt(a);
		
		return res;
	}
	
	// ��Ģ����
	public int calc_FourRule(int a, int b, String div) {
		int res = 0;

		if(div.equals("+")) {
			res = a + b;
		}
		else if(div.equals("-")) {
			res = a - b;
		}
		else if(div.equals("*")) {
			res = a * b;
		}
		else if(div.equals("/")) {
			res = a / b;
		}
		
		return res;
	}
	
	// ������ push/pop
	public void push_dividend(int n) {
		if(top_dividend > calc_stack_dividend.length) {
			return;
		}
		else {			
			calc_stack_dividend[top_dividend] = n;
			System.out.println("push[" + top_dividend + "]:" + calc_stack_dividend[top_dividend] + "," + top_dividend);
			top_dividend++;
		}
	}
	
	public int pop_dividend() {
		if(top_dividend == 0) {
			System.out.println("����");
			return -1;
		}
		else {
			int res;
			top_dividend--;
			System.out.println("pop[" + top_dividend + "]:" + calc_stack_dividend[top_dividend] + "," + top_dividend);
			res = calc_stack_dividend[top_dividend];
			calc_stack_dividend[top_dividend] = 0;
			return res;
		}
	}
	
	// ���� push/pop
	public void push_divisor(String str) {
		// TODO Auto-generated method stub
		if(top_divisor > calc_stack_divisor.length) {
			return;
		}
		else {			
			calc_stack_divisor[top_divisor] = str;
			System.out.println("push[" + top_divisor + "]:" + calc_stack_divisor[top_divisor] + "," + top_divisor);
			top_divisor++;
		}
	}
	
	public String pop_divisor() {
		if(top_divisor == 0) {
			return null;
		}
		else {
			String res;
			top_divisor--;
			System.out.println("pop[" + top_divisor + "]:" + calc_stack_divisor[top_divisor] + "," + top_divisor);
			res = calc_stack_divisor[top_divisor];
			calc_stack_divisor[top_divisor] = null;
			return res;
		}
	}
	
	// ����� push/pop
		public void push_result(int n) {
			// TODO Auto-generated method stub
			if(top_result > calc_stack_result.length) {
				return;
			}
			else {
				calc_stack_result[top_result] = n;
				System.out.println("push[" + top_result + "]:" + calc_stack_result[top_result] + "," + top_result);
				top_result++;
				System.out.println("���� ž:" + top_result);
			}
		}
		
		public int pop_result() {
			if(top_result == 0) {
				System.out.println("������ �������");
				return -1;
			}
			else {
				int res;
				top_result--;
				System.out.println("pop[" + top_result + "]:" + calc_stack_result[top_result] + "," + top_result);
				res = calc_stack_result[top_result];
				calc_stack_result[top_result] = 0;
				return res;
			}
		}
}
