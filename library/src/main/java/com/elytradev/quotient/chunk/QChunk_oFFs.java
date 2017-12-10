package com.elytradev.quotient.chunk;

import java.io.IOException;

import com.elytradev.quotient.MalformedSchematicException;
import com.elytradev.quotient.QChunk;
import com.elytradev.quotient.QuotientReader;
import com.elytradev.quotient.QuotientWriter;

/**
 * {@code OFFSet} data. Contains the offset from the -X -Y -Z corner of the
 * volume that a player was at when the schematic was copied. Mainly useful for
 * in-game world editing.
 */
public class QChunk_oFFs extends QChunk {

	public QChunk_oFFs() {
		super("oFFs");
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
