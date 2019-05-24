package com.seed.enums;

public enum FileType {
	WORD("01"), IMAGE("02"), EXCEL("03"), PDF("04"), PPT("05"), ZIP("06"), OTHER("99");
	
	private final String filetype;
	
	private FileType(String filetype) {
		this.filetype = filetype;
	}
	
	public String getFiletype() {
		return this.filetype;		
	}
	
	public static FileType parse(String filetype) {
		if (filetype != null) {
			for (FileType type : FileType.values()) {
				if (filetype.equalsIgnoreCase(type.filetype))
					return type;
			}
		}
		
		return FileType.OTHER;
	}
}
