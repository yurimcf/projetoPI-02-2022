package br.com.yuriCorp.projetoPi.Model.BEAN;

public class ValidaRG {
	//verifica se rg é valido
	public static boolean isRG(String rg) {
		if (rg.equals("000000000") || rg.equals("111111111") || rg.equals("222222222") || rg.equals("333333333")
				|| rg.equals("444444444") || rg.equals("555555555") || rg.equals("666666666") || rg.equals("777777777")
				|| rg.equals("888888888") || rg.equals("999999999") || (rg.length() != 9)) {
			return (false);
		}

		int a = rg.charAt(0) - '0'; // 1
		int b = rg.charAt(1) - '0'; // 2
		int c = rg.charAt(2) - '0'; // 3
		int d = rg.charAt(3) - '0'; // 4
		int e = rg.charAt(4) - '0'; // 5
		int f = rg.charAt(5) - '0'; // 6
		int g = rg.charAt(6) - '0'; // 7
		int h = rg.charAt(7) - '0'; // 8
		int i = rg.charAt(8) - '0'; // 9

		int result = (2 * a) + (3 * b) + (4 * c) + (5 * d) + (6 * e) + (7 * f) + (8 * g) + (9 * h) + (100 * i);
		return (result % 11) == 0;
	}
	
	//imprime o rg formatado
	public static String imprimeRG(String rg) {
        return (rg.substring(0, 2) + "." + rg.substring(2, 5) + "."
                + rg.substring(5, 8) + "-" + rg.substring(8));
    }
}
