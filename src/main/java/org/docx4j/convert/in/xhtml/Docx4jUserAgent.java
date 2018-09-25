package org.docx4j.convert.in.xhtml;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.openhtmltopdf.extend.FSImage;
import com.openhtmltopdf.layout.SharedContext;
import com.openhtmltopdf.resource.ImageResource;
import com.openhtmltopdf.swing.NaiveUserAgent;
import com.openhtmltopdf.util.XRLog;
import com.openhtmltopdf.util.IOUtil;

public class Docx4jUserAgent extends NaiveUserAgent {

//    private static final int IMAGE_CACHE_CAPACITY = 32;
//
//    private SharedContext _sharedContext;
//
//    private final Docx4jDocxOutputDevice _outputDevice;
//
//    public Docx4jUserAgent(Docx4jDocxOutputDevice outputDevice) {
//		super(IMAGE_CACHE_CAPACITY);
//		_outputDevice = outputDevice;
//    }

    protected byte[] readStream(InputStream is) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream(is.available());
        byte[] buf = new byte[10240];
        int i;
        while ( (i = is.read(buf)) != -1) {
            out.write(buf, 0, i);
        }
        out.close();
        return out.toByteArray();
    }

    protected InputStream resolveAndOpenStream(String uri) {
        return IOUtil.openStreamAtUrl(uri);
    }
    
    public Docx4jFSImage getDocx4JImageResource(String uri) {
                
        InputStream is = resolveAndOpenStream(uri);
        if (is != null) {
            try {
                return new Docx4jFSImage(readStream(is));
            } catch (Exception e) {
                XRLog.exception("Can't read image file; unexpected problem for URI '" + uri + "'", e);
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
        return null;
    }

//    private void scaleToOutputResolution(Image image) {
//        float factor = _sharedContext.getDotsPerPixel();
//        image.scaleAbsolute(image.getPlainWidth() * factor, image.getPlainHeight() * factor);
//    }
//
//    public SharedContext getSharedContext() {
//        return _sharedContext;
//    }
//
//    public void setSharedContext(SharedContext sharedContext) {
//        _sharedContext = sharedContext;
//    }
}
