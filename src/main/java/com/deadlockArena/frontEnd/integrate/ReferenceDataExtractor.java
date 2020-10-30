package com.deadlockArena.frontEnd.integrate;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import lombok.Data;

final @Data
public class ReferenceDataExtractor {

	public HttpURLConnection setupConnection(final String urlString) throws IOException {
		final URL url = new URL(urlString);
		return (HttpURLConnection) url.openConnection();
	}

	public <T> List<T> establishConnection(final HttpURLConnection reqConn, final Type t)
			throws IOException {
		reqConn.connect();
		final JsonParser jp = new JsonParser();
		final JsonElement element = jp.parse(new InputStreamReader(reqConn.getInputStream()));
		final List<T> dtos = new Gson().fromJson(element, t);
		reqConn.disconnect();
		return dtos;
	}

	public <T> List<T> extractData(final String url, final Type t) throws IOException {
		final HttpURLConnection requestConnection = this.setupConnection(url);
		return this.establishConnection(requestConnection, t);
	}
}
