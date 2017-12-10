package com.elytradev.quotient;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.elytradev.quotient.chunk.QChunk_BDAT;
import com.elytradev.quotient.chunk.QChunk_PLTE;
import com.elytradev.quotient.chunk.QChunk_QEND;
import com.elytradev.quotient.chunk.QChunk_QHDR;
import com.elytradev.quotient.chunk.QChunk_bENT;
import com.elytradev.quotient.chunk.QChunk_eNTY;
import com.elytradev.quotient.chunk.QChunk_oFFs;
import com.elytradev.quotient.chunk.QChunk_tEXt;
import com.elytradev.quotient.util.CRC32InputStream;
import com.elytradev.quotient.util.LimitInputStream;

/**
 * A low-level reader for a Quotient file, allowing the registration of custom
 * chunks, compression schemes, etc.
 * @see Quotient
 */
public class QuotientReader {
	public static final Map<Integer, QCompressionHandler> DEFAULT_COMPRESSION_SCHEMES;
	public static final Map<String, Class<? extends QChunk>> DEFAULT_CHUNKS;
	
	static {
		Map<Integer, QCompressionHandler> defaultCompressionSchemes = new HashMap<>();
		defaultCompressionSchemes.put(0, new QCompressionHandler.None());
		defaultCompressionSchemes.put(1, new QCompressionHandler.Deflate());
		defaultCompressionSchemes.put(2, new QCompressionHandler.LateBinding("LZMA", "com.elytradev.quotient.lzma.QLzmaCompressionHandler"));
		DEFAULT_COMPRESSION_SCHEMES = Collections.unmodifiableMap(defaultCompressionSchemes);
		
		Map<String, Class<? extends QChunk>> defaultChunks = new HashMap<>();
		defaultChunks.put("QHDR", QChunk_QHDR.class);
		defaultChunks.put("PLTE", QChunk_PLTE.class);
		defaultChunks.put("BDAT", QChunk_BDAT.class);
		defaultChunks.put("QEND", QChunk_QEND.class);
		
		defaultChunks.put("bENT", QChunk_bENT.class);
		defaultChunks.put("eNTY", QChunk_eNTY.class);
		defaultChunks.put("tEXt", QChunk_tEXt.class);
		defaultChunks.put("oFST", QChunk_oFFs.class);
		DEFAULT_CHUNKS = Collections.unmodifiableMap(defaultChunks);
	}
	
	protected final Map<Integer, QCompressionHandler> compressionSchemes = DEFAULT_COMPRESSION_SCHEMES;
	protected final Map<String, Class<? extends QChunk>> chunks = DEFAULT_CHUNKS;
	
	private final byte[] buf = new byte[1024];
	
	private InputStream in;
	private List<InputStream> stack = new ArrayList<>();
	
	public QuotientReader(InputStream in) {
		this.in = in;
	}
	
	public void push(InputStream in) {
		stack.add(this.in);
		this.in = in;
	}
	
	public InputStream pop() {
		InputStream in = this.in;
		this.in = stack.remove(stack.size()-1);
		return in;
	}
	
	public void pushLimit(int limit) {
		push(new LimitInputStream(in, limit));
	}
	
	public int popLimit() {
		return ((LimitInputStream)pop()).remaining();
	}
	
	public void pushCRC() {
		push(new CRC32InputStream(in));
	}
	
	public int popCRC() {
		return ((CRC32InputStream)pop()).getCRC();
	}
	
	
	public int readUnsignedByte() throws IOException {
		int i = in.read();
		if (i < 0) throw new EOFException();
		return i;
	}
	
	public byte readByte() throws IOException {
		return (byte)readUnsignedByte();
	}

	public int readUnsignedShort() throws IOException {
		read(buf, 0, 2);
		int b1 = buf[0]&0xFF;
		int b2 = buf[1]&0xFF;
		return (b1 << 8) | b2;
	}
	
	public short readShort() throws IOException {
		return (short)readUnsignedShort();
	}

	public int readUnsignedMedium() throws IOException {
		read(buf, 0, 3);
		int b1 = buf[0]&0xFF;
		int b2 = buf[1]&0xFF;
		int b3 = buf[2]&0xFF;
		return (b1 << 16) | (b2 << 8) | b3;
	}

	public long readUnsignedInt() throws IOException {
		read(buf, 0, 4);
		long b1 = buf[0]&0xFF;
		long b2 = buf[1]&0xFF;
		long b3 = buf[2]&0xFF;
		long b4 = buf[3]&0xFF;
		return (b1 << 24) | (b2 << 16) | (b3 << 8) | b4;
	}
	
	public int readInt() throws IOException {
		return (int)readUnsignedInt();
	}

	public long readUnsignedStretch() throws IOException {
		read(buf, 0, 6);
		long b1 = buf[0]&0xFF;
		long b2 = buf[1]&0xFF;
		long b3 = buf[2]&0xFF;
		long b4 = buf[3]&0xFF;
		long b5 = buf[4]&0xFF;
		long b6 = buf[5]&0xFF;
		return (b1 << 40) | (b2 << 32) | (b3 << 24) | (b4 << 16) | (b5 << 8) | b6;
	}

	public long readLong() throws IOException {
		read(buf, 0, 8);
		long b1 = buf[0]&0xFF;
		long b2 = buf[1]&0xFF;
		long b3 = buf[2]&0xFF;
		long b4 = buf[3]&0xFF;
		long b5 = buf[4]&0xFF;
		long b6 = buf[5]&0xFF;
		long b7 = buf[6]&0xFF;
		long b8 = buf[7]&0xFF;
		return (b1 << 56) | (b2 << 48) | (b3 << 40) | (b4 << 32) | (b5 << 24) | (b6 << 16) | (b7 << 8) | b8;
	}
	
	public float readFloat() throws IOException {
		return Float.intBitsToFloat(readInt());
	}
	
	public double readDouble() throws IOException {
		return Double.longBitsToDouble(readLong());
	}
	
	public byte[] read(int len) throws IOException {
		byte[] bys = new byte[len];
		read(bys);
		return bys;
	}
	
	public void read(byte[] bys) throws IOException {
		read(bys, 0, bys.length);
	}
	
	public void read(byte[] bys, int off, int len) throws IOException {
		if (bys == null) throw new IllegalArgumentException("bys cannot be null");
		if (len < 0) throw new IndexOutOfBoundsException("len cannot be negative");
		int total = 0;
		while (total < len) {
			int result = in.read(bys, off + total, len - total);
			if (result == -1) {
				throw new EOFException("reached end of stream after reading "+total+" bytes; "+len+" bytes expected");
			}
			total += result;
		}
	}
	
	public void skip(long n) throws IOException {
		long totalSkipped = 0;
		while (totalSkipped < n) {
			long remaining = n-totalSkipped;
			long skipped = in.skip(remaining);
			if (skipped == 0) {
				int skip = (int) Math.min(remaining, buf.length);
				skipped = in.read(buf, 0, skip);
				if (skipped == -1) {
					throw new EOFException("reached end of stream after skipping "+totalSkipped+" bytes, "+n+" expected");
				}
			}
			totalSkipped += skipped;
		}
	}
	
}
