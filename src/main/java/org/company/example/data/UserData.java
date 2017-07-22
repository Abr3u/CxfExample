package org.company.example.data;

import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

@XmlRootElement(name = "UserData")
public class UserData {

	@NotNull
	@Size(max = 9, min = 9)
	private String nif;
	@NotNull
	private String name;
	@NotNull
	private String address;
	@NotNull
	@Size(max = 9, min = 9)
	private String contact;

	
	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNif() {
		return nif;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb, Locale.US);
		formatter.format("NIF:%s\nName:%s\nAddress:%s\nContatc:%s\n", getNif(), getName(), getAddress(), getContact());
		   
		return sb.toString();
	}
}
