package application;

import java.math.*;
import java.text.DecimalFormat;

public class Calculate {
	// 내부적인 계산 처리를 위해 정의된 클래스
	// 계산에 필요한 요소들
	int calc_stack_dividend[] = new int[20];		// 입력된 숫자를 각 하나씩 저장하는 스택
	String[] calc_stack_divisor = new String[15];	// 연산자를 저장하는 스택
	int calc_stack_result[] = new int[20];			// 입력된 숫자를 하나로 조합한 결과를 저장하는 스택
	
	int top_dividend = 0;
	int top_divisor = 0;
	int top_result = 0;
	
	public Calculate() {
		
	}
	
	// 제곱 연산
	public int setPow(int a, int b) {
		int res= (int) Math.pow(a, b);
		
		return res;
	}
	
	// 루트연산
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
	
	// 사칙연산
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
	
	// 피젯수 push/pop
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
			System.out.println("없음");
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
	
	// 젯수 push/pop
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
	
	// 결과물 push/pop
		public void push_result(int n) {
			// TODO Auto-generated method stub
			if(top_result > calc_stack_result.length) {
				return;
			}
			else {
				calc_stack_result[top_result] = n;
				System.out.println("push[" + top_result + "]:" + calc_stack_result[top_result] + "," + top_result);
				top_result++;
				System.out.println("현재 탑:" + top_result);
			}
		}
		
		public int pop_result() {
			if(top_result == 0) {
				System.out.println("스택이 비어있음");
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
