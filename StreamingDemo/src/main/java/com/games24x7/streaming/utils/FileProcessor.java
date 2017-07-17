package com.games24x7.streaming.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.zip.ZipInputStream;

import org.springframework.stereotype.Component;

@Component
public class FileProcessor {

	public void writeDataFromFiles(Writer writer) throws IOException {
		Path dir = Paths.get("/home/anil/Desktop/blogs/streaming/data");

		if (Files.notExists(dir)) {
			System.out.println("No File in the directory");
		}

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir);) {
			for (Path path : stream) {
				readFileExtensionWise(path, writer);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readFileExtensionWise(Path filePath, Writer writer) throws IOException {
		if (filePath.getFileName().toString().endsWith(".zip")) {
			try (InputStream inputStream = readFromZipFile(filePath.toString())) {

				String inpStr = "";
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((inpStr = reader.readLine()) != null) {
					//Apply transformation here
					writer.write(inpStr + "\n");
				}

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else {
			try (Stream<String> stream = Files.lines(filePath);) {

				stream.forEach((line) -> {
					try {
						//Apply transformation here
						writer.write(line + "\n");
					} catch (IOException e) {
						e.printStackTrace();
					}
				});

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	public InputStream readFromZipFile(String fileDir) {

		ZipInputStream zipInputStream = null;
		try {
			zipInputStream = new ZipInputStream(new FileInputStream(fileDir));
			zipInputStream.getNextEntry();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return zipInputStream;
	}

}
