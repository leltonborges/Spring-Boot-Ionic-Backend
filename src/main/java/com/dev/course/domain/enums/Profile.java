package com.dev.course.domain.enums;

public enum Profile {

	ADMIN (1, "ROLE_ADMIN"),
	CLIENT (2, "ROLE_CLIENT");
	
	private Integer code;
	private String description;
	
	private Profile(Integer code, String description) {
		this.code = code;
		this.description = description; 
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static Profile toEnum(Integer code) {
		for(Profile p : Profile.values()) {
			if(p.getCode().equals(code)) {
				return p;
			}
		}
		throw new IllegalArgumentException("Invalided id: "+ code);
	}
}
