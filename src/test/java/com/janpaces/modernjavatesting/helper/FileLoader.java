package com.janpaces.modernjavatesting.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.util.ResourceUtils;

public class FileLoader {
	public static String read(String filePath) throws IOException {
		File file = ResourceUtils.getFile(filePath);
		return new String(Files.readAllBytes(file.toPath()));
	}
}

