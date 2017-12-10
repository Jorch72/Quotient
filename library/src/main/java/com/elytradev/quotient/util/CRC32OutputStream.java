package com.elytradev.quotient.util;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.CRC32;

public class CRC32OutputStream extends FilterOutputStream {

	private final CRC32 crc;
	
	public CRC32OutputStream(OutputStream out) {
		super(out);
		this.crc = new CRC32();
	}
	
	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		crc.update(b, off, len);
		out.write(b, off, len);
	}
	
	@Override
	public void write(int b) throws IOException {
		crc.update(b);
		out.write(b);
	}
	
	public int getCRC() {
		return (int)crc.getValue();
	}
	
}
