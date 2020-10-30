package com.deadlockarena.database;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.deadlockArena.database")
public class DeadlockArenaDatabase {

	public static final Logger LOG = LoggerFactory.getLogger(DeadlockArenaDatabase.class);

	private static final String LOCAL_URL = "C:/Users/zsaordenio/Documents/eclipse-worksapce/deadlock-arena/";
	private static final String[] TABLE_NAMES = { /* "PICTURE", "MUSIC" , "SOUND" */ };
	private static final String[] DIRECTORIES = { /* "pics/", "music/" , "sound/" */ };

	private static Uploader uploader;

	public static void main(final String[] args) {
		DeadlockArenaDatabase.uploader = new Uploader();
		DeadlockArenaDatabase.uploader.connect();

		for (int i = 0; i < DeadlockArenaDatabase.DIRECTORIES.length; i++) {
			final String url = DeadlockArenaDatabase.LOCAL_URL + DeadlockArenaDatabase.DIRECTORIES[i];
			final String[] fileNames = new File(url).list();
			for (final String fileName : fileNames) {
				try {
					final String fullUrl = url + fileName;
					final byte[] bytes = DeadlockArenaDatabase.DIRECTORIES[i].equals("pics/")
							? DeadlockArenaDatabase.pngToBlob(fullUrl)
									: DeadlockArenaDatabase.wavToBlob(fullUrl);

							DeadlockArenaDatabase.LOG.debug("Full URL is :" + fullUrl + "\nWav file length: " + bytes.length);
							DeadlockArenaDatabase.uploader.uploadBlob(fileName, DeadlockArenaDatabase.TABLE_NAMES[i], bytes);
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}
		DeadlockArenaDatabase.uploader.readBlob("PICTURE");
	}

	private static byte[] wavToBlob(final String filePath) throws IOException {
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		final BufferedInputStream in = new BufferedInputStream(new FileInputStream(filePath));
		int read;
		final byte[] buff = new byte[1024];
		while ((read = in.read(buff)) > 0) {
			out.write(buff, 0, read);
		}
		out.flush();
		in.close();
		return out.toByteArray();
	}

	private static byte[] pngToBlob(final String filePath) throws IOException {
		final File file = new File(filePath);
		final byte[] fileContent = new byte[(int) file.length()];
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
			inputStream.read(fileContent);
		} catch (final IOException e) {
			throw new IOException("Unable to convert file to byte array. " + e.getMessage());
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
		return fileContent;
	}

}
