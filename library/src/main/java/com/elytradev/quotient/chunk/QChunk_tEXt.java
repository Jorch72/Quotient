package com.elytradev.quotient.chunk;

import java.io.IOException;

import com.elytradev.quotient.MalformedSchematicException;
import com.elytradev.quotient.QChunk;
import com.elytradev.quotient.QuotientReader;
import com.elytradev.quotient.QuotientWriter;

/**
 * {@code TEXT} data. Contains arbitrary UTF-8 text accompanied by a "keyword".
 */
public class QChunk_tEXt extends QChunk {

	public QChunk_tEXt() {
		super("tEXt");
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
