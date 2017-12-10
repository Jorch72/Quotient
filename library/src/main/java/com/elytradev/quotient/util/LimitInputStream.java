package com.elytradev.quotient.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LimitInputStream extends FilterInputStream {

	private int remaining;
	private int mark = -1;

	public LimitInputStream(InputStream in, int limit) {
		super(in);
		if (in == null) throw new IllegalArgumentException("in cannot be null");
		if (limit < 0) throw new IllegalArgumentException("limit cannot be negative");
		remaining = limit;
	}

	@Override
	public int read() throws IOException {
		if (remaining == 0) return -1;
		int b = in.read();
		if (b != -1) {
			remaining--;
		}
		return b;
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		if (remaining == 0) return -1;
		len = Math.min(len, remaining);
		int amt = in.read(b, off, len);
		if (amt != -1) {
			remaining -= amt;
		}
		return amt;
	}

	public int remaining() {
		return remaining;
	}
	
	@Override
	public int available() throws IOException {
		return Math.min(in.available(), remaining);
	}

	@Override
	public synchronized void mark(int readlimit) {
		in.mark(readlimit);
		mark = remaining;
	}
	
	@Override
	public synchronized void reset() throws IOException {
		if (!in.markSupported()) throw new IOException("mark not supported");
		if (mark == -1) throw new IOException("mark not set");
		in.reset();
		remaining = mark;
	}

	@Override
	public long skip(long n) throws IOException {
		long toSkip = Math.min(n, remaining);
		long amt = in.skip(toSkip);
		remaining -= amt;
		return amt;
	}
	
}
