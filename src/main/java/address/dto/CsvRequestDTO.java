package address.dto;

import address.utils.AddressEnum;

public class CsvRequestDTO {

	private AddressEnum column;
	private String value;

	public AddressEnum getColumn() {
		return column;
	}

	public void setColumn(AddressEnum column) {
		this.column = column;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
