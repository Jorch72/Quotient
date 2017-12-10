package com.elytradev.quotient.chunk;

import java.io.IOException;

import com.elytradev.quotient.MalformedSchematicException;
import com.elytradev.quotient.QChunk;
import com.elytradev.quotient.QuotientReader;
import com.elytradev.quotient.QuotientWriter;

/**
 * {@code Block DATa} chunk. Contains data about the base blocks in a volume,
 * without tile entity data.
 */
public class QChunk_BDAT extends QChunk {
	private int[] blocks;
	
	public QChunk_BDAT() {
		super("BDAT");
	}

	@Override
	protected void writeInner(QuotientWriter out) throws IOException {
		
	}

	@Override
	protected void readInner(QuotientReader in, int length) throws IOException, MalformedSchematicException {
		// TODO Auto-generated method stub

	}

}
