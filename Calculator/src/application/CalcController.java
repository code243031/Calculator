package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.text.DecimalFormat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CalcController implements Initializable {
	
	@FXML Label display_main;		// ��� ��� ���÷���
	@FXML Label display_sub;
	
	@FXML Button key_1;				// ���� Ű 0~9
	@FXML Button key_2;
	@FXML Button key_3;
	@FXML Button key_4;
	@FXML Button key_5;
	@FXML Button key_6;
	@FXML Button key_7;
	@FXML Button key_8;
	@FXML Button key_9;
	@FXML Button key_0;
	@FXML Button key_c;				// clear - ��� ����� �����.
	@FXML Button key_ce;			// clear entry - ������ �Է��� ���� ����.
	@FXML Button key_plus;			// ����
	@FXML Button key_minus;			// ����
	@FXML Button key_mul;			// ����
	@FXML Button key_div;			// ������
	@FXML Button key_equal;			// ��� ��� (=)
	@FXML Button key_backspace;		// �Է� �ϳ� �����
	@FXML Button key_root;			// ��Ʈ ����
	@FXML Button key_per;			// �ۼ�Ƽ���� ������� EX) 9 -> 0.09
	@FXML Button key_pow;			// ���� ����
	@FXML Button key_den;			// ���� ���ڸ� �и�� �Ͽ� ���ڰ� 1�� ���� ���� ������
	// �� �Ʒ��� ���� �̱���
	@FXML Button key_pn;			// +/-
	@FXML Button key_dot;			// .
				
	public Calculate cal = new Calculate();	// ���� �� ����
	private String res = null;				// ���÷��̿� ��� ���ڿ�
	private String res_sub = null;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		key_1.setOnAction(event->pressNumKeyAction(event));
		key_2.setOnAction(event->pressNumKeyAction(event));
		key_3.setOnAction(event->pressNumKeyAction(event));
		key_4.setOnAction(event->pressNumKeyAction(event));
		key_5.setOnAction(event->pressNumKeyAction(event));
		key_6.setOnAction(event->pressNumKeyAction(event));
		key_7.setOnAction(event->pressNumKeyAction(event));
		key_8.setOnAction(event->pressNumKeyAction(event));
		key_9.setOnAction(event->pressNumKeyAction(event));
		key_0.setOnAction(event->pressNumKeyAction(event));
		
		key_plus.setOnAction(event->pressDivisorKeyAction(event));
		key_minus.setOnAction(event->pressDivisorKeyAction(event));
		key_mul.setOnAction(event->pressDivisorKeyAction(event));
		key_div.setOnAction(event->pressDivisorKeyAction(event));
		
		key_equal.setOnAction(event->pressEqualKeyAction(event));
		
		key_c.setOnAction(event->pressClearKeyAction(event));
		key_ce.setOnAction(event->pressClearKeyAction(event));
		
		key_backspace.setOnAction(event->pressBackspaceKeyAction(event));
		
		key_root.setOnAction(event->pressRootKeyAction(event));
		
		key_per.setOnAction(event->pressPerKeyAction(event));
		
		key_pow.setOnAction(event->pressPowKeyAction(event));
		
		key_den.setOnAction(event->pressDenominatorKeyAction(event));
	}
	
	public void pressDenominatorKeyAction(ActionEvent e) {
		String res = null;
		for(; cal.top_dividend > 0;) {
			int i = cal.pop_dividend();
			if(res == null) {
				res = Integer.toString(i);
			}
			else {
				res = Integer.toString(i) + res;
			}
		}
		double target = 1 / Double.parseDouble(res);
		display_main.setText(Double.toString(target));
	}
	
	public void pressPowKeyAction(ActionEvent e) {
		String res = null;
		for(; cal.top_dividend > 0;) {
			int i = cal.pop_dividend();
			if(res == null) {
				res = Integer.toString(i);
			}
			else {
				res = Integer.toString(i) + res;
			}
		}
		int pow_target = Integer.parseInt(res);
		display_main.setText(Integer.toString(cal.setPow(pow_target, 2)));
	}
	
	public void pressPerKeyAction(ActionEvent e) {
		String res = null;
		for(; cal.top_dividend > 0;) {
			int i = cal.pop_dividend();
			if(res == null) {
				res = Integer.toString(i);
			}
			else {
				res = Integer.toString(i) + res;
			}
		}
		
		int per_target = Integer.parseInt(res);
		double per_result = 0.01 * (double) per_target;
		
		display_main.setText(Double.toString(per_result));
	}
	
	public void pressRootKeyAction(ActionEvent e) { // ���� ������.
		resultSetting();
		int d = cal.setSqrt(cal.pop_result());
		
		res = Integer.toString(d);
//		for(int i = 0; i < 10; i++)
//		{
//			if (d % (double)i == 0.0) {
//				res = Double.toString(d);
//			}
//		}
		
		display_main.setText(res);
	}
	
	public void pressBackspaceKeyAction(ActionEvent e) {
		try {
			cal.pop_dividend();
			res = res.substring(0, res.length() - 1);
			display_main.setText(res);
		} catch(Exception e1) {
			System.out.println("����..");
		}
	}
	
	public void pressClearKeyAction(ActionEvent e) {
		while(cal.top_result != 0) {
			cal.pop_result();
		}
		while(cal.top_dividend != 0) {
			cal.pop_dividend();
		}
		while(cal.top_divisor != 0) {
			cal.pop_divisor();
		}
		res = null;
		res_sub = null;
		display_main.setText("0");
		display_sub.setText(res_sub);
	}
	
	public void pressEqualKeyAction(ActionEvent e) {
		// �ƹ��͵� ���� ���¿��� equal�� ������ �� �Ѿ�� ó��, 
		int[] arr_type = new int[20];
		String[] arr_div = new String[10];
		int cnt_i = 0;
		int cnt_j = 0;
		int p = 0;
		
		String res = null;
		for(; cal.top_dividend > 0;) {
			int i = cal.pop_dividend();
			if(res == null) {
				res = Integer.toString(i);
			}
			else {
				res = Integer.toString(i) + res;
			}
		}
		cal.push_result(Integer.parseInt(res));
		
		// ���� ���⼭ ��� ó��
		for(int i = 0; cal.top_result != 0; i++) {
			arr_type[i] = cal.pop_result();
			cnt_j++;
		}
		for(int i = 0; cal.top_divisor != 0; i++) {
			arr_div[i] = cal.pop_divisor();
			cnt_i++;
		}

		for(int i = 0; i < cnt_i; i++) {
			for(int j = 0; j < cnt_j; j++) {
				if(p != 0) {
					p = cal.calc_FourRule(p, arr_type[j], arr_div[i]);
				}
				else {
					p = cal.calc_FourRule(arr_type[j+1], arr_type[j], arr_div[i]);
					j++;
				}
			}
		}
		
		this.res = Integer.toString(p);
		System.out.println("���:" + this.res);
		display_sub.setText(res_sub + res +"=" + this.res);
		display_main.setText(this.res);
		
		res_sub = null;
		this.res = null;
		
//		resultSetting();
	}
	
	public void pressDivisorKeyAction(ActionEvent e) {
		String type = e.getSource().toString();
		
		if(type.matches(".*id=key_plus.*")) {
			cal.push_divisor("+");
		}
		else if(type.matches(".*id=key_minus.*")) {
			cal.push_divisor("-");
		}
		else if(type.matches(".*id=key_mul.*")) {
			cal.push_divisor("*");
		}
		else if(type.matches(".*id=key_div.*")) {
			cal.push_divisor("/");
		}
		subDisplaySetting();
	}
	
	public void pressNumKeyAction(ActionEvent e) {
		// ���� ����� ? ���ñ�����
		String type = e.getSource().toString();
		
		if(display_main.getText().length() > cal.calc_stack_dividend.length) {
			// ���߿� �˾�â���� ��ü
			System.out.println("�� �Է��� �� �����!");
		}
		else {
			if(type.matches(".*id=key_1.*")) {
				cal.push_dividend(1);
			}
			else if(type.matches(".*id=key_2.*")) {
				cal.push_dividend(2);
			}
			else if(type.matches(".*id=key_3.*")) {
				cal.push_dividend(3);
			}
			else if(type.matches(".*id=key_4.*")) {
				cal.push_dividend(4);
			}
			else if(type.matches(".*id=key_5.*")) {
				cal.push_dividend(5);
			}
			else if(type.matches(".*id=key_6.*")) {
				cal.push_dividend(6);
			}
			else if(type.matches(".*id=key_7.*")) {
				cal.push_dividend(7);
			}
			else if(type.matches(".*id=key_8.*")) {
				cal.push_dividend(8);
			}
			else if(type.matches(".*id=key_9.*")) {
				cal.push_dividend(9);
			}
			else if(type.matches(".*id=key_0.*")) {
				cal.push_dividend(0);
			}
			else {
				System.out.print("���� �߸����..");
				System.exit(-1);
			}
			mainDisplaySetting();
		}
	}
	
	public void mainDisplaySetting() {

		if(res == null) {
			res = Integer.toString(cal.calc_stack_dividend[cal.top_dividend-1]);
		}
		else {
			res = res + Integer.toString(cal.calc_stack_dividend[cal.top_dividend-1]);
		}
		display_main.setText(res);
	}
	
	public void subDisplaySetting() {
		String div = cal.calc_stack_divisor[cal.top_divisor - 1];

		if(res_sub == null) {
			res_sub = this.res + div;
		}
		else {
			res_sub = res_sub + this.res + div;
		}
		
		resultSetting();
	}
	
	public void resultSetting() {
		String res = null;
		for(; cal.top_dividend > 0;) {
			int i = cal.pop_dividend();
			if(res == null) {
				res = Integer.toString(i);
			}
			else {
				res = Integer.toString(i) + res;
			}
		}
		cal.push_result(Integer.parseInt(res));
		this.res = null;
		display_sub.setText(res_sub);
	}
}
