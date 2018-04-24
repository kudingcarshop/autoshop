package com.kuding.customer.view;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class CustomerEditView {
	
	private MultipartFile file;
	
	@NotBlank(message="{customer.edit.save.name_blank}")
	@Size(min=2,max=50,message="{customer.edit.save.name_size}")
	private String name;
	
	@Pattern(regexp="^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$",message="{customer.edit.save.phoneNumber_error}")
	private String phoneNumber;

	public MultipartFile getFile() {
		return file;
	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
