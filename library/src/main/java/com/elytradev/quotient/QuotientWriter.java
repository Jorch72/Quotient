package com.elytradev.quotient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import com.elytradev.quotient.util.CRC32OutputStream;


public class QuotientWriter {

	private OutputStream out;
	private List<OutputStream> stack = new ArrayList<>();
	
	public QuotientWriter(OutputStream out) {
		this.out = out;
	}
	
	public void push(OutputStream out) {
		stack.add(this.out);
		this.out = out;
	}
	
	public OutputStream pop() {
		OutputStream out = this.out;
		this.out = stack.remove(stack.size()-1);
		return out;
	}
	
	public void pushBuffer() {
		push(new ByteArrayOutputStream());
	}
	
	public byte[] popBuffer() {
		return ((ByteArrayOutputStream)pop()).toByteArray();
	}
	
	public void pushCRC() {
		push(new CRC32OutputStream(out));
	}
	
	public int popCRC() {
		return ((CRC32OutputStream)pop()).getCRC();
	}
	
	
	public void write(byte[] bys) throws IOException {
		write(bys, 0, bys.length);
	}
	
	public void write(byte[] bys, int ofs, int len) throws IOException {
		out.write(bys, ofs, len);
	}
	
	public void writeByte(int i) throws IOException {
		out.write((i >>> 0) & 0xFF);
	}

	public void writeShort(int i) throws IOException {
		out.write((i >>> 8) & 0xFF);
		out.write((i >>> 0) & 0xFF);
	}

	public void writeMedium(int i) throws IOException {
		out.write((i >>> 16) & 0xFF);
		out.write((i >>>  8) & 0xFF);
		out.write((i >>>  0) & 0xFF);
	}

	public void writeInt(int i) throws IOException {
		out.write((i >>> 24) & 0xFF);
		out.write((i >>> 16) & 0xFF);
		out.write((i >>>  8) & 0xFF);
		out.write((i >>>  0) & 0xFF);
	}

	public void writeInt(long i) throws IOException {
		out.write((int)((i >>> 24) & 0xFF));
		out.write((int)((i >>> 16) & 0xFF));
		out.write((int)((i >>>  8) & 0xFF));
		out.write((int)((i >>>  0) & 0xFF));
	}

	public void writeStretch(long i) throws IOException {
		out.write((int)((i >>> 40) & 0xFF));
		out.write((int)((i >>> 32) & 0xFF));
		out.write((int)((i >>> 24) & 0xFF));
		out.write((int)((i >>> 16) & 0xFF));
		out.write((int)((i >>>  8) & 0xFF));
		out.write((int)((i >>>  0) & 0xFF));
	}

	public void writeLong(long i) throws IOException {
		out.write((int)((i >>> 56) & 0xFF));
		out.write((int)((i >>> 48) & 0xFF));
		out.write((int)((i >>> 40) & 0xFF));
		out.write((int)((i >>> 32) & 0xFF));
		out.write((int)((i >>> 24) & 0xFF));
		out.write((int)((i >>> 16) & 0xFF));
		out.write((int)((i >>>  8) & 0xFF));
		out.write((int)((i >>>  0) & 0xFF));
	}
	
	
}
