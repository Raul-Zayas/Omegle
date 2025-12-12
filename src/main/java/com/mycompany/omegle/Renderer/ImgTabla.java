package com.mycompany.omegle.Renderer;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ImgTabla extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        // Si el valor es un ImageIcon crea el JLabel con la imagen
        if (value instanceof ImageIcon) {
            JLabel label = new JLabel((ImageIcon) value);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setOpaque(true);
            if (isSelected) label.setBackground(table.getSelectionBackground());
            else label.setBackground(table.getBackground());
            return label;
        }

        // Fallback al comportamiento por defecto
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (isSelected) c.setBackground(table.getSelectionBackground());
        else c.setBackground(table.getBackground());
        return c;
    }
}
