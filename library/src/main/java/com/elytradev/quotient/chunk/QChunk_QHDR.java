package com.elytradev.quotient.chunk;

import java.io.IOException;

import com.elytradev.quotient.MalformedSchematicException;
import com.elytradev.quotient.QChunk;
import com.elytradev.quotient.QuotientReader;
import com.elytradev.quotient.QuotientWriter;

/**
 * {@code Quotient HeaDeR} data. Contains basic information about the volume
 * contained in a Quotient file, such as the size and compression format. Must
 * come first and not be duplicated.
 */
public class QChunk_QHDR extends QChunk {

	public QChunk_QHDR() {
		super("QHDR");
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
