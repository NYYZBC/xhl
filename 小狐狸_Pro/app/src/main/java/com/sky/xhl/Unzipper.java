package com.sky.xhl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;



public class Unzipper {
    public static void unzip(File zipFile, File destDir) throws IOException {
        try (ZipFile zip = new ZipFile(zipFile)) {
            Enumeration<? extends ZipEntry> entries = zip.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                Path destPath = destDir.toPath().resolve(entry.getName());
                if (entry.isDirectory()) {
                    Files.createDirectories(destPath);
                } else {
                    createParentDirectories(destPath);
                    try (InputStream is = new BufferedInputStream(zip.getInputStream(entry));
                         OutputStream os = new BufferedOutputStream(Files.newOutputStream(destPath))) {
                        byte[] buffer = new byte[4096];
                        int len;
                        while ((len = is.read(buffer)) > 0) {
                            os.write(buffer, 0, len);
                        }
                    }
                }
            }
        }
    }

    private static void createParentDirectories(Path destPath) throws IOException {
        Path parent = destPath.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
    }
}
