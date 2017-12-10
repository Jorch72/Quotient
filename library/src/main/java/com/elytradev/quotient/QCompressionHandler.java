package com.elytradev.quotient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public interface QCompressionHandler {

	InputStream wrap(InputStream in) throws IOException;
	OutputStream wrap(OutputStream out) throws IOException;
	
	public final class None implements QCompressionHandler {
		
		@Override
		public InputStream wrap(InputStream in) throws IOException {
			return in;
		}
		
		@Override
		public OutputStream wrap(OutputStream out) throws IOException {
			return out;
		}
		
	}
	
	public final class Deflate implements QCompressionHandler {
		
		@Override
		public InputStream wrap(InputStream in) throws IOException {
			return new InflaterInputStream(in);
		}
		
		@Override
		public OutputStream wrap(OutputStream out) throws IOException {
			return new DeflaterOutputStream(out);
		}
		
	}
	
	public class LateBinding implements QCompressionHandler {

		private static final String CANT_USE = "Can't use {} compression scheme (are you missing a library?)";
		
		private final String name;
		private final String className;
		private QCompressionHandler delegate;
		private Exception failureReason;
		
		public LateBinding(String name, String className) {
			this.name = name;
			this.className = className;
		}

		private void tryFillDelegate() throws IOException {
			if (delegate != null) return;
			if (failureReason != null) {
				throw new IOException(CANT_USE.replace("{}", name), failureReason);
			}
			try {
				delegate = Class.forName(className).asSubclass(QCompressionHandler.class).newInstance();
			} catch (Exception e) {
				failureReason = e;
				throw new IOException(CANT_USE.replace("{}", name), failureReason);
			}
		}
		
		@Override
		public InputStream wrap(InputStream in) throws IOException {
			tryFillDelegate();
			return delegate.wrap(in);
		}

		@Override
		public OutputStream wrap(OutputStream out) throws IOException {
			tryFillDelegate();
			return delegate.wrap(out);
		}

	}
	
}
