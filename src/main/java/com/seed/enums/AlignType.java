package com.seed.enums;

public enum AlignType {
	LEFT("1"), CENTER("2"), RIGHT("3");

	private final String aligntype;

	private AlignType(String aligntype) {
		this.aligntype = aligntype;
	}

	public String getAligntype() {
		return this.aligntype;
	}

	public static AlignType parse(String aligntype) {
		if (aligntype != null) {
			for (AlignType type : AlignType.values()) {
				if (aligntype.equalsIgnoreCase(type.aligntype))
					return type;
			}
		}

		return AlignType.CENTER;
	}
} 