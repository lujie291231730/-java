package com.seed.enums;

public enum AuthBound {
	ALL("1"), DEPT("2"), SELF("3");

	private final String authbound;

	private AuthBound(String authbound) {
		this.authbound = authbound;
	}

	public String getAuthbound() {
		return this.authbound;
	}

	public static AuthBound parse(String authbound) {
		if (authbound != null) {
			for (AuthBound type : AuthBound.values()) {
				if (authbound.equalsIgnoreCase(type.authbound))
					return type;
			}
		}

		return AuthBound.DEPT;
	}
} 