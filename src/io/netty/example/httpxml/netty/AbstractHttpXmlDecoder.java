package io.netty.example.httpxml.netty;

import java.io.StringReader;
import java.nio.charset.Charset;

import org.jibx.runtime.IBindingFactory;

import io.netty.handler.codec.MessageToMessageDecoder;

public abstract class AbstractHttpXmlDecoder<T> extends MessageToMessageDecoder<T> {
	private IBindingFactory factory;
	private StringReader reader;
	private Class<?> clazz;
	private boolean isPrint;
	private final static String CHARSET_NAME = "UTF-8";
	private final static Charset UTF_8 = Charset.forName(CHARSET_NAME);
	
}
