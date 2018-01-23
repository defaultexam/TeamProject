package com.spring.common.util;

public class Util {
	public static int nvl(String text) {
		return nvl(text, 0);
	}

	/********************************
	 * nvl()�޼ҵ�� ���ڿ��� ���ڷ� ��ȯ�ϴ� �޼ҵ�.
	 * 
	 * @param(���ڷ� ��ȯ�� ���ڿ�, �ʱⰪ���� ����� ��(��ü��)) ���� : ���� ó���� üũ���ܿ� ��üũ���ܷ� ����. üũ ���ܴ�
	 *            ��������� / ��Ʈ��ũ ����� / �����ͺ��̽� �����. �������� �� üũ ���ܷ� �ν�.
	 * @return int
	 **********************************/

	public static int nvl(String text, int def) {
		int ret = def;

		try {
			ret = Integer.parseInt(text);
		} catch (Exception e) {
			ret = def;
		}
		return ret;
	}

}
