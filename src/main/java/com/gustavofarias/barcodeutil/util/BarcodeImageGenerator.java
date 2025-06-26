package com.gustavofarias.barcodeutil.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.gustavofarias.barcodeutil.model.BarcodeType;
import org.krysalis.barcode4j.HumanReadablePlacement;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class BarcodeImageGenerator {

    /**
     * Generates a Base64 encoded PNG image of a barcode based on the given code and barcode type.
     * Supports various barcode formats, delegating generation to either ZXing or Barcode4J libraries.
     *
     * @param code the barcode data string to encode
     * @param type the type of barcode to generate
     * @return a Base64 encoded PNG image representing the barcode
     * @throws Exception if barcode generation or encoding fails
     */
    public static String generateBase64Image(String code, BarcodeType type) throws Exception {
        return switch (type) {
            case EAN13 -> generateWithZxing(code, BarcodeFormat.EAN_13);
            case EAN8 -> generateWithZxing(code, BarcodeFormat.EAN_8);
            case UPCA -> generateWithZxing(code, BarcodeFormat.UPC_A);
            case UPCE -> generateWithZxing(code, BarcodeFormat.UPC_E);
            case CODE128 -> generateWithZxing(code, BarcodeFormat.CODE_128);
            case GS1128, DUN14 -> generateWithBarcode4J(code);
            default -> throw new IllegalArgumentException("Unsupported barcode type: " + type);
        };
    }

    /**
     * Generates a barcode image using the ZXing library and encodes it as a Base64 PNG string.
     *
     * @param code   the barcode data string to encode
     * @param format the ZXing barcode format to use for generation
     * @return a Base64 encoded PNG image representing the barcode
     * @throws Exception if encoding or image generation fails
     */
    private static String generateWithZxing(String code, BarcodeFormat format) throws Exception {
        var bitMatrix = new MultiFormatWriter().encode(code, format, 300, 150);
        var image = MatrixToImageWriter.toBufferedImage(bitMatrix);

        return encodeImageToBase64(image);
    }

    /**
     * Generates a GS1-128 or DUN-14 barcode image using the Barcode4J library and encodes it as a Base64 PNG string.
     * Adds the FNC1 character required by the GS1-128 standard at the beginning of the code.
     *
     * @param code the barcode data string to encode
     * @return a Base64 encoded PNG image representing the barcode
     * @throws Exception if encoding or image generation fails
     */
    private static String generateWithBarcode4J(String code) throws Exception {
        var bean = new Code128Bean();
        bean.setMsgPosition(HumanReadablePlacement.HRP_BOTTOM);
        bean.setModuleWidth(UnitConv.in2mm(1.0f / 300));
        bean.setHeight(15);
        bean.doQuietZone(true);

        // GS1-128 requires the FNC1 character (\u00f1) at the start of the message
        var message = '\u00f1' + code;

        var baos = new ByteArrayOutputStream();
        var canvas = new BitmapCanvasProvider(
                baos, "image/png", 300, BufferedImage.TYPE_BYTE_BINARY, false, 0);

        bean.generateBarcode(canvas, message);
        canvas.finish();

        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    /**
     * Converts a BufferedImage to a Base64 encoded PNG string.
     *
     * @param image the BufferedImage to encode
     * @return a Base64 encoded PNG string
     * @throws Exception if image writing or encoding fails
     */
    private static String encodeImageToBase64(BufferedImage image) throws Exception {
        var baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);

        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }
}
