package com.seed.enums;

public enum TranAction {
		None("00"), 
		Search("01"), 
		Create("02"), 
		Modify("03"), 
		Delete("04"),
		Export("05"),
		Import("06"),
		Deal("07"), 
		Submit("08"),
		Upload("09"),
		Valid("11"), 		
		InValid("12"),
		Check("13"), 
		UnCheck("14"), 
		Special01("15"), 
		Special02("16"), 
		Special03("17");
		
		private final String tranaction;
		
		private TranAction(String tranaction) {
			this.tranaction = tranaction;
		}
		
		public String getTranaction() {
			return this.tranaction;		
		}
		
		public static TranAction parse(String tranaction) {
			if (tranaction != null) {
				for (TranAction type : TranAction.values()) {
					if (tranaction.equalsIgnoreCase(type.tranaction))
						return type;
				}
			}
			
			return TranAction.None;
		}
		
		public static TranAction parse(int tranaction) {
			String value = "";
			
			if (tranaction > 9)
				value = String.valueOf(tranaction);
			else 
				value = '0' + String.valueOf(tranaction);
			
			return parse(value);
		}
	}
