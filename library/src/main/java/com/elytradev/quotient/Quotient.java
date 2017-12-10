package com.elytradev.quotient;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.net.URL;

/**
 * Main entry point to the Quotient library, with many convenience methods to
 * read and write Quotient-format schematics into and out of QSchematic
 * instances.
 */
public final class Quotient {

	private static final byte[] MAGIC_NUMBER = { -62, -86, 51, -33, 102, 12, -54, -117, 13, -97, -60, 93, -1, 46, -105, 117 };
	
	public static QSchematic read(InputStream in) throws IOException, MalformedSchematicException {
		return null; // TODO
	}
	
	public static void write(QSchematic volume, OutputStream out) throws IOException {
		// TODO
	}
	
	/**
	 * @return the 16-byte (128-bit) magic number at the beginning of all
	 * 		Quotient-format files
	 */
	public static byte[] getMagicNumber() {
		return MAGIC_NUMBER.clone();
	}
	
	// -- CONVENIENCE METHODS -- //
	
	public static QSchematic read(byte[] bys) throws MalformedSchematicException {
		try {
			return read(new ByteArrayInputStream(bys));
		} catch (MalformedSchematicException e) {
			throw e;
		} catch (IOException e) {
			// shouldn't ever happen
			throw new UncheckedIOException(e);
		}
	}
	
	public static QSchematic read(URL url) throws IOException, MalformedSchematicException {
		try (InputStream in = url.openStream()) {
			return read(in);
		}
	}
	
	public static QSchematic read(File file) throws IOException, MalformedSchematicException {
		try (FileInputStream fis = new FileInputStream(file)) {
			return read(fis);
		}
	}
	
	
	
	private Quotient() {}
}
