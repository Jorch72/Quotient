package com.elytradev.quotient.chunk;

import java.io.IOException;

import com.elytradev.quotient.MalformedSchematicException;
import com.elytradev.quotient.QChunk;
import com.elytradev.quotient.QuotientReader;
import com.elytradev.quotient.QuotientWriter;

/*
 * {@code Quotient END}. Contains no data, signifies the end of a Quotient file.
 */
public class QChunk_QEND extends QChunk {

	public QChunk_QEND() {
		super("QEND");
	}

	@Override
	protected void writeInner(QuotientWriter out) throws IOException {}

	@Override
	protected void readInner(QuotientReader in, int length) throws IOException, MalformedSchematicException {}

}
