package com.learn.websocket.billing.utility;

import com.learn.websocket.billing.entity.Order;
import com.learn.websocket.billing.entity.OrderItem;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Component
public class BillPdfGenerator {

    public static byte[] generateInvoice(
            Order order, List<OrderItem> orderItemList) {

        try {
            Document document = new Document(PageSize.A4);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, out);

            document.open();

            // Title
            Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
            document.add(new Paragraph("INVOICE", titleFont));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Customer: " + order.getUserName()));
            document.add(new Paragraph("Date: " + java.time.LocalDate.now()));
            document.add(new Paragraph(" "));

            // Table
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{4, 2, 2, 2});

            addHeader(table, "Item");
            addHeader(table, "Qty");
            addHeader(table, "Price");
            addHeader(table, "Total");

            double grandTotal = 0;

            for (OrderItem item : orderItemList) {
                table.addCell(item.getProductName());
                table.addCell(String.valueOf(item.getQuantity()));
                table.addCell(String.valueOf(item.getPrice()));
                table.addCell(String.valueOf(item.getQuantity()*item.getPrice()));
                grandTotal = order.getTotalAmount();
            }

            document.add(table);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(
                    "Grand Total: ₹" + grandTotal,
                    new Font(Font.HELVETICA, 14, Font.BOLD)
            ));

            document.close();

            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("PDF generation failed", e);
        }
    }

    private static void addHeader(PdfPTable table, String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }
}





















