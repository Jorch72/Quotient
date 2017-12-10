package com.elytradev.quotient.util;

public final class ASCII {

	public static boolean isLowerCase(char c) {
		 return (c >= 'a') && (c <= 'z');
	}
	
	public static boolean isUpperCase(char c) {
		 return (c >= 'A') && (c <= 'Z');
	}
	
	public static boolean isSymbol(char c) {
		return ((c >= ' ') && (c <= '@')) ||
				((c >= '[') && (c <= '`')) ||
				((c >= '{') && (c <= '~'));
	}
	
	public static boolean isControl(char c) {
		return (c <= 0x1F) || (c == 0x7F);
	}
	
	private ASCII() {}
	
}
