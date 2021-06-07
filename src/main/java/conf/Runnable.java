package conf;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;

import gmail.GmailQuickstart;
import parsers.FourPointsParser;
import rows.FourPointsRow;
import sender.Sender;
import upload.Upload;

public class Runnable {

	public static void main(String[] args) throws IOException, GeneralSecurityException {
		if (args.length != 0) {

			String path_from = args[0]; // Корневая папка
			String path_to = args[1]; // Куда кладем .csv - выгрузку и номеклатуру
			String pathToSaveFile = args[2]; // куда будем класть выгрузку provider'a
			String fileNameUpload = args[3]; // имя отправляемого файла
			String emailProvider = args[4]; // email provider
			String idEmailInSubj = args[5]; //
			int dayToDelivery = Integer.valueOf(args[6]); //

			File folderToSave = new File(pathToSaveFile);

			GmailQuickstart gmail = new GmailQuickstart(pathToSaveFile, emailProvider, idEmailInSubj);

			gmail.setTokenDirectory(path_from + "\\tokens");

			GmailQuickstart.clearFolder(folderToSave);// очищаем папку provider'a
			// GmailQuickstart.clearFolder(new File(PathToNomenclature));// очищаем папку
			// номенклатуры

			gmail.run();

			String fileName = getLastModifiedFile(folderToSave);

			FourPointsParser parser = new FourPointsParser();
			parser.setFilenameFrom(fileName);

			HashMap<String, FourPointsRow> moySkladMap = parser.Parse();

//						moySkladMap.entrySet().stream().forEach(e -> {
//							System.out.println("\"" + e.getKey() + "\"; \"" + e.getValue().getPrice() + "\"; \""
//									+ e.getValue().getLeftOver() + "\"");
//						});

			Upload upload = new Upload(dayToDelivery, path_to + "\\" + fileNameUpload,
					path_to + "\\" + "Nomenclature.csv", moySkladMap);
			upload.writeFiles();

			Sender sender = new Sender();// 30 seconds timeout
			System.out.println("Sending file: " + path_from + "\\" + fileNameUpload);
			sender.setData(path_from, fileNameUpload);
			sender.send();

		} else {
			System.out.println("Not enough args");
		}
	}

	public static String getLastModifiedFile(File folderToSave) {
		File folder = folderToSave;

		File[] matchingFiles = folder.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".xlsx");
			}
		});
		File lastFile = matchingFiles[0];

		System.out.println("Last modified file :" + lastFile);

		return lastFile.getPath();

	}

}
