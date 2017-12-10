package com.elytradev.quotient.chunk;

import java.io.IOException;

import com.elytradev.quotient.MalformedSchematicException;
import com.elytradev.quotient.QChunk;
import com.elytradev.quotient.QuotientReader;
import com.elytradev.quotient.QuotientWriter;

/**
 * {@code PaLeTtE} data. Contains the blockstate palette used by any BDAT
 * chunks.
 */
public class QChunk_PLTE extends QChunk {

	public QChunk_PLTE() {
		super("PLTE");
	}

	@Override
	protected void writeInner(QuotientWriter out) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void readInner(QuotientReader in, int length) throws IOException, MalformedSchematicException {
		// TODO Auto-generated method stub

	}

}
