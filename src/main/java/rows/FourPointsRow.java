package rows;

public class FourPointsRow {
	private String manufacturerCode;
	private String priceRRC;
	private String leftOver;
	private String name;
	private String priceRoznica;

	public FourPointsRow() {
		super();
	}

	public FourPointsRow(String manufacturerCode, String priceRRC, String leftOver, String name, String priceRoznica) {
		super();
		this.manufacturerCode = manufacturerCode;
		this.priceRRC = priceRRC;
		this.leftOver = leftOver;
		this.name = name;
		this.priceRoznica = priceRoznica;
	}

	public String getManufacturerCode() {
		return manufacturerCode;
	}

	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
	}

	public String getPriceRRC() {
		return priceRRC;
	}

	public void setPriceRRC(String priceRRC) {
		this.priceRRC = priceRRC;
	}

	public String getLeftOver() {
		return leftOver;
	}

	public void setLeftOver(String leftOver) {
		this.leftOver = leftOver;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPriceRoznica() {
		return priceRoznica;
	}

	public void setPriceRoznica(String priceRoznica) {
		this.priceRoznica = priceRoznica;
	}

	@Override
	public String toString() {
		return "FourPointsRow [manufacturerCode=" + manufacturerCode + ", priceRRC=" + priceRRC + ", leftOver="
				+ leftOver + ", name=" + name + ", priceRoznica=" + priceRoznica + "]";
	}

}
