package upload;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import rows.FourPointsRow;

public class Upload {
	private static final String n = "\r\n";
	private static final String semilicon = ";";
	private int dayToDelivery;

	String fileName;
	private String fileNameNomenclature;

	private String finalData = "";
	private String finalDataNomenclature = "";

	private final int DEFAULT_dayToDelivery = 1;

	private HashMap<String, FourPointsRow> allDataMap;

	public Upload() {
		allDataMap = null;
		dayToDelivery = DEFAULT_dayToDelivery;
	}

	public Upload(int dayToDelivery, String fileName, String fileNameNomenclature,
			HashMap<String, FourPointsRow> allDataMap) {
		super();
		this.dayToDelivery = dayToDelivery;
		this.fileName = fileName;
		this.fileNameNomenclature = fileNameNomenclature;
		this.allDataMap = allDataMap;
	}

	public int getDayToDelivery() {
		return dayToDelivery;
	}

	public void setDayToDelivery(int dayToDelivery) {
		this.dayToDelivery = dayToDelivery;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileNameNomenclature() {
		return fileNameNomenclature;
	}

	public void setFileNameNomenclature(String fileNameNomenclature) {
		this.fileNameNomenclature = fileNameNomenclature;
	}

	public HashMap<String, FourPointsRow> getAllDataMap() {
		return allDataMap;
	}

	public void setAllDataMap(HashMap<String, FourPointsRow> allDataMap) {
		this.allDataMap = allDataMap;
	}

	public void writeFiles() throws IOException {
		if (allDataMap != null) {
			System.out.println("Upload map.size()= " + allDataMap.size());
			for (Map.Entry<String, FourPointsRow> entry : allDataMap.entrySet()) {
				String key = entry.getKey();
				FourPointsRow value = entry.getValue();
				int price = 0, leftover = 0;
				try {

					price = Integer.parseInt(value.getPriceRRC().substring(0, value.getPriceRRC().length() - 2));

					if (price == 0) {
						price = Integer
								.parseInt(value.getPriceRoznica().substring(0, value.getPriceRoznica().length() - 2));
					}

					if (value.getLeftOver().equals("более 40")) {
						leftover = 41;
					} else {
						leftover = Integer.parseInt(value.getLeftOver().substring(0, value.getLeftOver().length() - 2));
					}
					if (price != 0 && price > 0 && leftover > 0) {

						finalData += key + semilicon + "" + semilicon + "" + semilicon + "" + semilicon + price
								+ semilicon + leftover + semilicon + dayToDelivery + semilicon + "" + semilicon + ""
								+ semilicon + "" + n;
						finalDataNomenclature += key + semilicon + entry.getValue().getName() + n;

					}
				} catch (Exception ex) {
					//System.out.println(ex);
				}

			}
			FileOutputStream outputStreamData = new FileOutputStream(fileName);
			FileOutputStream outputStreamTwo = new FileOutputStream(fileNameNomenclature);

			byte[] strToBytesData = finalData.getBytes();
			outputStreamData.write(strToBytesData);
			outputStreamData.close();

			byte[] strToBytesNomenclature = finalDataNomenclature.getBytes();
			outputStreamTwo.write(strToBytesNomenclature);
			outputStreamTwo.close();

		}
	}

}
