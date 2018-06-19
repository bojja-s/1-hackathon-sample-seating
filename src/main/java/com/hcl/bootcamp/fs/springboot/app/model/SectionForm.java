package com.hcl.bootcamp.fs.springboot.app.model;

import lombok.Data;

@Data
public class SectionForm {

	private Long sectionId;
	private String sectionName;
	private Long seatId;
	private String seatName;
	private Long secretValue;

}
