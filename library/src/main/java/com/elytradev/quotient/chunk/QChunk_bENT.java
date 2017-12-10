package com.elytradev.quotient.chunk;

import java.io.IOException;

import com.elytradev.quotient.MalformedSchematicException;
import com.elytradev.quotient.QChunk;
import com.elytradev.quotient.QuotientReader;
import com.elytradev.quotient.QuotientWriter;

/**
 * {@code Block ENTity} data. Contains NBT block entities.
 */
public class QChunk_bENT extends QChunk {

	public QChunk_bENT() {
		super("bENT");
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
