package com.games24x7.streaming.services;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.ws.rs.core.StreamingOutput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.games24x7.streaming.utils.FileProcessor;

@Component
public class DownloadService {

	@Autowired
	FileProcessor fileProcessor;

	public StreamingResponseBody getSpringMVCStream(String internalFileName) {
		StreamingResponseBody stream = new StreamingResponseBody() {

			@Override
			public void writeTo(OutputStream output) throws IOException {
				writeToStream(output, internalFileName);
			}
		};
		return stream;
	}

	public StreamingOutput getJAXRSStream(String internalFileName) {
		StreamingOutput stream = new StreamingOutput() {

			@Override
			public void write(OutputStream output) throws IOException {

				writeToStream(output, internalFileName);
			}

		};
		return stream;
	}

	public void writeToStream(OutputStream os, String internalFileName) throws IOException {
		ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream(os));
		ZipEntry e = new ZipEntry(internalFileName);
		zipOut.putNextEntry(e);
		Writer writer = new BufferedWriter(new OutputStreamWriter(zipOut, Charset.forName("UTF-8").newEncoder()));
		try {
			fileProcessor.writeDataFromFiles(writer);
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.flush();
					writer.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			if (os != null) {
				try {
					os.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		}
	}

}
