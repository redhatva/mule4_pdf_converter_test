package pdf2tiff;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.channels.FileChannel;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.PDFRenderer;

public class ConvertUtil {

	public static InputStream convert(InputStream thePDF) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream fis = new FileInputStream("convert.tiff");
		
		PDDocument pdd = PDDocument.load(thePDF);
		PDFRenderer pr = new PDFRenderer (pdd);

		BufferedImage bi = pr.renderImageWithDPI (0, 300);
		ImageWriter writer = ImageIO.getImageWritersBySuffix("tiff").next();
		ImageIO.write(bi, "tiff", baos);
		
		InputStream is = new ByteArrayInputStream(baos.toByteArray());
		return is;
	}
}
