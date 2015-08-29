package com.beolnix.trpg.cmdargs;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.Manifest;

/**
 * Created by beolnix on 29/08/15.
 */
public class MetainfHelper {

    private final static String versionLabel = "Implementation-Version";

    public static String getVersion() {
        try {
            Manifest manifest = getManifest();
            String version = manifest.getMainAttributes().getValue(versionLabel);
            if (version != null) {
                return version;
            }
        } catch (IOException e) {
            //nop
        }

        return "UNDETERMINED";
    }

    private static Manifest getManifest() throws IOException {
        URLClassLoader cl = (URLClassLoader) MetainfHelper.class.getClassLoader();
        URL url = cl.findResource("META-INF/MANIFEST.MF");
        return new Manifest(url.openStream());
    }
}
