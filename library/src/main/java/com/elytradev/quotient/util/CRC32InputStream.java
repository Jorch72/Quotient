package com.elytradev.quotient.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;

public class CRC32InputStream extends FilterInputStream {

	private final CRC32 crc;
	
	public CRC32InputStream(InputStream in) {
		super(in);
		this.crc = new CRC32();
	}
	
	@Override
	public int read() throws IOException {
		int i = super.read();
		if (i >= 0) crc.update(i);
		return i;
	}
	
	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		int amt = super.read(b, off, len);
		if (amt >= 0) crc.update(b, off, amt);
		return amt;
	}
	
	public int getCRC() {
		return (int)crc.getValue();
	}
	
}
