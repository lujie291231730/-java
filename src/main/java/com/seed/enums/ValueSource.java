package com.seed.enums;

public enum ValueSource {
	TEXT("01"), DATA("02"), EMPTY("03"), IMAGE("04"), CHART("05");

	private final String valuesource;

	private ValueSource(String valuesource) {
		this.valuesource = valuesource;
	}

	public String getValuesource() {
		return this.valuesource;
	}

	public static ValueSource parse(String valuesource) {
		if (valuesource != null) {
			for (ValueSource type : ValueSource.values()) {
				if (valuesource.equalsIgnoreCase(type.valuesource))
					return type;
			}
		}

		return ValueSource.TEXT;
	}
}
