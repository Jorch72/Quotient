package com.elytradev.quotient.chunk;

import java.io.IOException;

import com.elytradev.quotient.MalformedSchematicException;
import com.elytradev.quotient.QChunk;
import com.elytradev.quotient.QuotientReader;
import com.elytradev.quotient.QuotientWriter;

/**
 * Represents an unknown chunk that is marked as safe to copy, so it may be
 * included in a re-written file.
 */
public class QChunkUnknownCopyable extends QChunk {

	private byte[] data;
	
	public QChunkUnknownCopyable(String name) {
		this(name, null);
	}
	
	public QChunkUnknownCopyable(String name, byte[] data) {
		super(name);
		this.data = data;
	}
	
	@Override
	protected void writeInner(QuotientWriter out) throws IOException {
		out.write(data);
	}

	@Override
	protected void readInner(QuotientReader in, int length) throws IOException, MalformedSchematicException {
		data = in.read(length);
	}

}
