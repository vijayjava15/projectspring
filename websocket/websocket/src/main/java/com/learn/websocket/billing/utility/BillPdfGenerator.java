package com.learn.websocket.billing.utility;

import com.learn.websocket.billing.entity.Order;
import com.learn.websocket.billing.entity.OrderItem;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Component
public class BillPdfGenerator {

    public static byte[] generateBill(Order order, List<OrderItem> items) {

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Document document = new Document(PageSize.A4, 20, 20, 20, 20);
            PdfWriter.getInstance(document, out);
            document.open();

            Font headerFont = new Font(Font.HELVETICA, 14, Font.BOLD);
            Font normalFont = new Font(Font.HELVETICA, 10);
            Font boldFont = new Font(Font.HELVETICA, 10, Font.BOLD);

            /* ---------- SHOP HEADER ---------- */
            Paragraph shopName = new Paragraph("MY SHOP NAME", headerFont);
            shopName.setAlignment(Element.ALIGN_CENTER);
            document.add(shopName);

            Paragraph shopInfo = new Paragraph(
                    "Address Line\nGSTIN: 33XXXXXXX\nPhone: 9XXXXXXXXX",
                    normalFont
            );
            shopInfo.setAlignment(Element.ALIGN_CENTER);
            document.add(shopInfo);

            document.add(new Paragraph("------------------------------------------------"));

            /* ---------- BILL INFO ---------- */
            PdfPTable infoTable = new PdfPTable(2);
            infoTable.setWidthPercentage(100);
            infoTable.setWidths(new float[]{3, 3});

            infoTable.addCell(getCell("Bill No: " + order.getId(), PdfPCell.ALIGN_LEFT));
            infoTable.addCell(getCell("Date: " + java.time.LocalDateTime.now(), PdfPCell.ALIGN_RIGHT));

            infoTable.addCell(getCell("Customer: " + order.getUserName(), PdfPCell.ALIGN_LEFT));
            infoTable.addCell(getCell(" ", PdfPCell.ALIGN_RIGHT));

            document.add(infoTable);

            document.add(new Paragraph(" "));

            /* ---------- ITEMS TABLE ---------- */
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{4, 1.5f, 2, 2});

            addHeader(table, "Item");
            addHeader(table, "Qty");
            addHeader(table, "Rate");
            addHeader(table, "Amount");

            double subTotal = 0;

            for (OrderItem item : items) {
                double itemTotal = item.getQuantity() * item.getPrice();
                subTotal += itemTotal;

                table.addCell(new PdfPCell(new Phrase(item.getProductName(), normalFont)));
                table.addCell(rightCell(item.getQuantity()));
                table.addCell(rightCell(item.getPrice()));
                table.addCell(rightCell(itemTotal));
            }

            document.add(table);

            document.add(new Paragraph(" "));

            /* ---------- TOTALS ---------- */
            PdfPTable totalTable = new PdfPTable(2);
            totalTable.setWidthPercentage(100);
            totalTable.setWidths(new float[]{4, 2});

            totalTable.addCell(getCell("Subtotal", PdfPCell.ALIGN_RIGHT));
            totalTable.addCell(rightCell(subTotal));

            double gst = subTotal * 0.05; // example 5%
            totalTable.addCell(getCell("GST (5%)", PdfPCell.ALIGN_RIGHT));
            totalTable.addCell(rightCell(gst));

            double grandTotal = subTotal + gst;

            totalTable.addCell(getCell("TOTAL", PdfPCell.ALIGN_RIGHT, boldFont));
            totalTable.addCell(rightCell(grandTotal, boldFont));

            document.add(totalTable);

            document.add(new Paragraph(" "));
            Paragraph footer = new Paragraph("Thank you! Visit again 😊", boldFont);
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);

            document.close();
            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Bill PDF generation failed", e);
        }
    }

    /* ---------- Helper Methods ---------- */

    private static void addHeader(PdfPTable table, String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text, new Font(Font.HELVETICA, 10, Font.BOLD)));
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }

    private static PdfPCell getCell(String text, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text, new Font(Font.HELVETICA, 10)));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(alignment);
        return cell;
    }

    private static PdfPCell getCell(String text, int alignment, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(alignment);
        return cell;
    }

    private static PdfPCell rightCell(double value) {
        return rightCell(String.format("%.2f", value));
    }

    private static PdfPCell rightCell(double value, Font font) {
        return rightCell(String.format("%.2f", value), font);
    }

    private static PdfPCell rightCell(Object value) {
        return rightCell(String.valueOf(value));
    }

    private static PdfPCell rightCell(String value) {
        PdfPCell cell = new PdfPCell(new Phrase(value, new Font(Font.HELVETICA, 10)));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        return cell;
    }

    private static PdfPCell rightCell(String value, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(value, font));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        return cell;
    }
}





















