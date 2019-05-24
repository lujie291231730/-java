package com.seed.enums;

public enum VAlignType {
	TOP("1"), MIDDLE("2"), BOTTOM("3");

	private final String valigntype;

	private VAlignType(String valigntype) {
		this.valigntype = valigntype;
	}

	public String getValigntype() {
		return this.valigntype;
	}

	public static VAlignType parse(String valigntype) {
		if (valigntype != null) {
			for (VAlignType type : VAlignType.values()) {
				if (valigntype.equalsIgnoreCase(type.valigntype))
					return type;
			}
		}

		return VAlignType.MIDDLE;
	}
} 