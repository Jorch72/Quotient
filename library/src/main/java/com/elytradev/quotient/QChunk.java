package com.elytradev.quotient;

import java.io.IOException;
import com.elytradev.quotient.util.ASCII;

/**
 * Represents a "chunk" of a Quotient file, inspired by PNG.
 */
public abstract class QChunk {

	protected final String name;
	
	protected QChunk(String name) {
		assertChunkName(name);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void write(QuotientWriter out) throws IOException, MalformedSchematicException {
		out.pushBuffer();
			out.pushCRC();
				writeInner(out);
			int crc = out.popCRC();
		byte[] data = out.popBuffer();
		
		out.writeInt(data.length);
		out.write(data);
		out.writeInt(crc);
	}
	
	public void read(QuotientReader in) throws IOException, MalformedSchematicException {
		int len = in.readInt();
		in.pushLimit(len);
			in.pushCRC();
				readInner(in, len);
			int crc = in.popCRC();
		in.skip(in.popLimit());
		int actualCrc = in.readInt();
		if (crc != actualCrc) {
			throw new MalformedSchematicException("CRCs didn't match! ("+crc+" != "+actualCrc+")");
		}
	}
	
	protected abstract void writeInner(QuotientWriter out) throws IOException, MalformedSchematicException;
	protected abstract void readInner(QuotientReader in, int length) throws IOException, MalformedSchematicException;
	
	public boolean isAncillary() {
		return isAncillary(name);
	}
	
	public boolean isPrivate() {
		return isPrivate(name);
	}
	
	public boolean isSafeToCopy() {
		return isSafeToCopy(name);
	}
	
	public static void assertChunkName(String name) {
		if (name.length() != 4) throw new IllegalArgumentException("not a valid chunk name (not 4 characters): "+name);
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if (c > 0x7F) throw new IllegalArgumentException("not a valid chunk name (contains non-ASCII characters): "+name);
		}
		if (ASCII.isLowerCase(name.charAt(2))) throw new IllegalArgumentException("not a valid chunk name (the third character must be uppercase): "+name);
	}
	
	public static boolean isAncillary(String name) {
		assertChunkName(name);
		return ASCII.isLowerCase(name.charAt(0));
	}
	
	public static boolean isPrivate(String name) {
		assertChunkName(name);
		return ASCII.isLowerCase(name.charAt(1));
	}
	
	public static boolean isSafeToCopy(String name) {
		assertChunkName(name);
		return ASCII.isLowerCase(name.charAt(3));
	}
	
}
