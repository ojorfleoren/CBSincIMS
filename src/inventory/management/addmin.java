package inventory.management;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author OJT-21
 */
public class addmin extends javax.swing.JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rst = null;
public addmin() {
    
        initComponents();
        conn = LogIn.connectDB();
        setExtendedState(addmin.MAXIMIZED_BOTH); 
        //Table Sizes
        //Display the total quantity into the dashboard
        displayTotalDataCount();
        displayReleaseCount();
        displayCheckingCount();
        displayRepairCount();
        displayReturnCount();
        displayDisposalCount();
        alignDataInfo(tblDataInfo);
        alignStock(tblStock);
        alignRelease(tblRelease);
        alignChecking(tblChecking);
        alignReturn(tblReturn);
        alignRepair(tblRepair);
        alignDisposal(tblDisposal);
    // Attach key listener to txtQty and the Employee number field
    txtQty.addKeyListener(createNumericKeyListener());
    }

private static final int[] dataAlignment = {SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER,
                                              SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER,
                                              SwingConstants.CENTER, SwingConstants.CENTER};
    private static final int[] dataWidths = {15, 40, 80, 80, 80, 50, 70, 15};
    
    private static final int[] stockAlignment = {SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER,
                                              SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER,
                                              SwingConstants.CENTER, SwingConstants.CENTER};
    private static final int[] minWidths = {15, 40, 120, 130, 220, 50, 60, 15};
    //
    private static final int[] alignment = {SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER,
                                              SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER,
                                              SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER};
    private static final int[] Widths = {95, 70, 80, 130, 100, 150, 90, 90, 15, 100};
    private static final int[] releaseAlign = {SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER,
                                              SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER,
                                              SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER};
    private static final int[] releaseWidth = {30, 30, 100, 80, 140, 100, 180, 60, 90, 15, 90};
    

public static void alignDataInfo(JTable tblDataInfo) {
        DefaultTableCellRenderer renderer;
        JTableHeader header = tblDataInfo.getTableHeader();
        TableCellRenderer headerRenderer = header.getDefaultRenderer();

        // Set alignment for header
        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        
        // Set font and bold for header
        Font headerFont = header.getFont();
        header.setFont(headerFont.deriveFont(Font.BOLD, 14));

        for (int i = 0; i < tblDataInfo.getColumnCount() && i < dataAlignment.length; i++) {
            int align = dataAlignment[i];
            int minWidth = dataWidths[i];
            renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(align);
            tblDataInfo.getColumnModel().getColumn(i).setCellRenderer(renderer);
            tblDataInfo.getColumnModel().getColumn(i).setMinWidth(minWidth); // Set minimum width for cell

            // Set minimum width for header
            tblDataInfo.getTableHeader().getColumnModel().getColumn(i).setMinWidth(minWidth);
        }
    }
    public static void alignStock(JTable tblStock) {
        DefaultTableCellRenderer renderer;
        JTableHeader header = tblStock.getTableHeader();
        TableCellRenderer headerRenderer = header.getDefaultRenderer();

        // Set alignment for header
        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        
        // Set font and bold for header
        Font headerFont = header.getFont();
        header.setFont(headerFont.deriveFont(Font.BOLD, 18));

        for (int i = 0; i < tblStock.getColumnCount() && i < stockAlignment.length; i++) {
            int align = stockAlignment[i];
            int minWidth = minWidths[i];
            renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(align);
            tblStock.getColumnModel().getColumn(i).setCellRenderer(renderer);
            tblStock.getColumnModel().getColumn(i).setMinWidth(minWidth); // Set minimum width for cell

            // Set minimum width for header
            tblStock.getTableHeader().getColumnModel().getColumn(i).setMinWidth(minWidth);
        }
    }
    public static void alignRelease(JTable tblRelease) {
        DefaultTableCellRenderer renderer;
        JTableHeader header = tblRelease.getTableHeader();
        TableCellRenderer headerRenderer = header.getDefaultRenderer();

        // Set alignment for header
        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        
        // Set font and bold for header
        Font headerFont = header.getFont();
        header.setFont(headerFont.deriveFont(Font.BOLD, 18)); // Set font size to 18

        for (int i = 0; i < tblRelease.getColumnCount() && i < releaseAlign.length; i++) {
            int align = releaseAlign[i];
            int width = releaseWidth[i];
            renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(align);
            tblRelease.getColumnModel().getColumn(i).setCellRenderer(renderer);
            tblRelease.getColumnModel().getColumn(i).setMinWidth(width); // Set minimum width for cell

            // Set minimum width for header
            tblRelease.getTableHeader().getColumnModel().getColumn(i).setMinWidth(width);
        }
    }
    public static void alignChecking(JTable tblChecking) {
        DefaultTableCellRenderer renderer;
        JTableHeader header = tblChecking.getTableHeader();
        TableCellRenderer headerRenderer = header.getDefaultRenderer();

        // Set alignment for header
        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        
        // Set font and bold for header
        Font headerFont = header.getFont();
        header.setFont(headerFont.deriveFont(Font.BOLD, 18)); // Set font size to 18

        for (int i = 0; i < tblChecking.getColumnCount() && i < alignment.length; i++) {
            int align = alignment[i];
            int width = Widths[i];
            renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(align);
            tblChecking.getColumnModel().getColumn(i).setCellRenderer(renderer);
            tblChecking.getColumnModel().getColumn(i).setMinWidth(width); // Set minimum width for cell

            // Set minimum width for header
            tblChecking.getTableHeader().getColumnModel().getColumn(i).setMinWidth(width);
        }
    }
    public static void alignReturn(JTable tblReturn) {
        DefaultTableCellRenderer renderer;
        JTableHeader header = tblReturn.getTableHeader();
        TableCellRenderer headerRenderer = header.getDefaultRenderer();

        // Set alignment for header
        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        
        // Set font and bold for header
        Font headerFont = header.getFont();
        header.setFont(headerFont.deriveFont(Font.BOLD, 18)); // Set font size to 18

        for (int i = 0; i < tblReturn.getColumnCount() && i < alignment.length; i++) {
            int align = alignment[i];
            int width = Widths[i];
            renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(align);
            tblReturn.getColumnModel().getColumn(i).setCellRenderer(renderer);
            tblReturn.getColumnModel().getColumn(i).setMinWidth(width); // Set minimum width for cell

            // Set minimum width for header
            tblReturn.getTableHeader().getColumnModel().getColumn(i).setMinWidth(width);
        }
    }
    public static void alignRepair(JTable tblRepair) {
        DefaultTableCellRenderer renderer;
        JTableHeader header = tblRepair.getTableHeader();
        TableCellRenderer headerRenderer = header.getDefaultRenderer();

        // Set alignment for header
        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        
        // Set font and bold for header
        Font headerFont = header.getFont();
        header.setFont(headerFont.deriveFont(Font.BOLD, 18)); // Set font size to 18

        for (int i = 0; i < tblRepair.getColumnCount() && i < alignment.length; i++) {
            int align = alignment[i];
            int width = Widths[i];
            renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(align);
            tblRepair.getColumnModel().getColumn(i).setCellRenderer(renderer);
            tblRepair.getColumnModel().getColumn(i).setMinWidth(width); // Set minimum width for cell

            // Set minimum width for header
            tblRepair.getTableHeader().getColumnModel().getColumn(i).setMinWidth(width);
        }
    }
    public static void alignDisposal(JTable tblDisposal) {
        DefaultTableCellRenderer renderer;
        JTableHeader header = tblDisposal.getTableHeader();
        TableCellRenderer headerRenderer = header.getDefaultRenderer();

        // Set alignment for header
        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        
        // Set font and bold for header
        Font headerFont = header.getFont();
        header.setFont(headerFont.deriveFont(Font.BOLD, 18)); // Set font size to 18

        for (int i = 0; i < tblDisposal.getColumnCount() && i < alignment.length; i++) {
            int align = alignment[i];
            int width = Widths[i];
            renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(align);
            tblDisposal.getColumnModel().getColumn(i).setCellRenderer(renderer);
            tblDisposal.getColumnModel().getColumn(i).setMinWidth(width); // Set minimum width for cell

            // Set minimum width for header
            tblDisposal.getTableHeader().getColumnModel().getColumn(i).setMinWidth(width);
        }
    }
    //End-------------------------------------------------------------------------------------------------------------End
//Keyboard listener
private KeyListener createNumericKeyListener() {
    return new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE)) {
                e.consume(); // Ignore the event
            }
        }
    };
}
//Display Count in Dashboard
public void displayTotalDataCount() {
    try {
        // Execute a SQL query to sum the total quantity in the "Qty" column of the "Stock" table
        String sumSql = "SELECT SUM(Qty) AS total FROM Stock";
        try (PreparedStatement sumPst = conn.prepareStatement(sumSql);
             ResultSet rs = sumPst.executeQuery()) {

            if (rs.next()) {
                int totalQuantity = rs.getInt("total");

                // Update the label with the total quantity
                lblTotalqty.setText(Integer.toString(totalQuantity));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving total quantity.");
    }
}
//Display the total quantity of each area into the dashboard
public void displayReleaseCount() {
    try {
        String sumSql = "SELECT SUM(Qty) AS total FROM Release";
        try (PreparedStatement sumPst = conn.prepareStatement(sumSql);
             ResultSet rs = sumPst.executeQuery()) {

            if (rs.next()) {
                int totalQuantity = rs.getInt("total");

                // Update the label with the total quantity
                lblRelease.setText(Integer.toString(totalQuantity));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving total quantity.");
    }
}
public void displayCheckingCount() {
    try {
        String sumSql = "SELECT SUM(Qty) AS total FROM Checking";
        try (PreparedStatement sumPst = conn.prepareStatement(sumSql);
             ResultSet rs = sumPst.executeQuery()) {

            if (rs.next()) {
                int totalQuantity = rs.getInt("total");

                // Update the label with the total quantity
                lblChecking.setText(Integer.toString(totalQuantity));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving total quantity.");
    }
}
public void displayRepairCount() {
    try {
        // Execute a SQL query to sum the total quantity in the "Qty" column of the "Stock" table
        String sumSql = "SELECT SUM(Qty) AS total FROM Repair";
        try (PreparedStatement sumPst = conn.prepareStatement(sumSql);
             ResultSet rs = sumPst.executeQuery()) {

            if (rs.next()) {
                int totalQuantity = rs.getInt("total");

                // Update the label with the total quantity
                lblRepair.setText(Integer.toString(totalQuantity));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving total quantity.");
    }
}
public void displayReturnCount() {
    try {
        // Execute a SQL query to sum the total quantity in the "Qty" column of the "Stock" table
        String sumSql = "SELECT SUM(Qty) AS total FROM Return";
        try (PreparedStatement sumPst = conn.prepareStatement(sumSql);
             ResultSet rs = sumPst.executeQuery()) {

            if (rs.next()) {
                int totalQuantity = rs.getInt("total");

                // Update the label with the total quantity
                lblReturn.setText(Integer.toString(totalQuantity));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving total quantity.");
    }
}
public void displayDisposalCount() {
    try {
        // Execute a SQL query to sum the total quantity in the "Qty" column of the "Stock" table
        String sumSql = "SELECT SUM(Qty) AS total FROM Disposal";
        try (PreparedStatement sumPst = conn.prepareStatement(sumSql);
             ResultSet rs = sumPst.executeQuery()) {

            if (rs.next()) {
                int totalQuantity = rs.getInt("total");

                // Update the label with the total quantity
                lblDisposal.setText(Integer.toString(totalQuantity));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving total quantity.");
    }
}
//End--------------------------------------------------------------------------------End
//Search Functionality
public void searchItems(String searchText) {
    DefaultTableModel model = (DefaultTableModel) tblStock.getModel();
    model.setRowCount(0); // Clear existing rows

    try {
        String sql = "SELECT * FROM Stock WHERE ItemName LIKE ? OR SerialNo LIKE ? OR Model LIKE ? OR Category LIKE ? OR Brand LIKE ?";
        pst = conn.prepareStatement(sql);
        pst.setString(1, "%" + searchText + "%"); // Search itemName
        pst.setString(2, "%" + searchText + "%"); // Search Serial No
        pst.setString(3, "%" + searchText + "%"); // Search Category
        pst.setString(4, "%" + searchText + "%"); // Search Status
        pst.setString(5, "%" + searchText + "%");
        rst = pst.executeQuery();

        while (rst.next()) {
            int ItemID = rst.getInt("ItemID");
            String serialNumber = rst.getString("SerialNo");
            String itemName = rst.getString("ItemName");
            String modelValue = rst.getString("Model");
            String specification = rst.getString("Specification");
            String category = rst.getString("Category");
            String brand = rst.getString("Brand");
            int quantity = rst.getInt("Qty");

            // Map database column names to JTable column names
            model.addRow(new Object[]{ItemID, serialNumber, itemName, modelValue, specification, category, brand, quantity});
        }

    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving items from the database.");
    } finally {
        try {
            if (rst != null) {
                rst.close();
            }
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
//End--------------------------------------------------------------------------------End
//ADD item functionality
public void insertItem() {
    try {
        // Validate fields before proceeding
        if (!validateFields()) {
            return; // Stop execution if fields are not filled up
        }

        // Get the values from the text fields
        String serialNum = txtSerial.getText();
        String itemName = txtItemName.getText();
        String model = txtModel.getText();
        String specification = txtSpecification.getText();
        String category = cbSCategory.getSelectedItem().toString();
        String brand = txtBrand.getText();
        String qtyText = txtQty.getText();

        // Validate integer field (Quantity)
        int qty;
        try {
            qty = Integer.parseInt(qtyText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer value for Quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String message = "Are you sure you want to add this item?";
        int option = JOptionPane.showConfirmDialog(this, message, "Confirmation", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            String sql = "INSERT INTO Stock (SerialNo, ItemName, Model, Specification, Category, Brand, Qty) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?)";

            pst = conn.prepareStatement(sql);
            pst.setString(1, serialNum);
            pst.setString(2, itemName);
            pst.setString(3, model);
            pst.setString(4, specification);
            pst.setString(5, category);
            pst.setString(6, brand);
            pst.setInt(7, qty);

            pst.execute();
            JOptionPane.showMessageDialog(this, "Item added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            clear();
            displayDataItems();
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error adding item to the database.");
    } finally {
        try {
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
//End--------------------------------------------------------------------------------End
//Get info of column in database
private String getItemSerialNumber(int itemID) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:CELO_Database.db");
        String sql = "SELECT SerialNo FROM Stock WHERE ItemID = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, itemID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("SerialNo");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving item serial number.");
    }
    return null;
}
private String getItemName(int itemID) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:CELO_Database.db");
        String sql = "SELECT ItemName FROM Stock WHERE ItemID = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, itemID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("ItemName");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving item name.");
    }
    return null;
}
private String getItemModel(int itemID) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:CELO_Database.db");
        String sql = "SELECT Model FROM Stock WHERE ItemID = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, itemID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Model");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving item name.");
    }
    return null;
}
private String getItemSpecification(int itemID) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:CELO_Database.db");
        String sql = "SELECT Specification FROM Stock WHERE ItemID = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, itemID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Specification");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving item name.");
    }
    return null;
}
private String getCategory(int itemID) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:CELO_Database.db");
        String sql = "SELECT Category FROM Stock WHERE ItemID = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, itemID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Category");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving item category.");
    }
    return null;
}
private String getBrand(int itemID) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:CELO_Database.db");
        String sql = "SELECT Brand FROM Stock WHERE ItemID = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, itemID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Brand");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving item brand.");
    }
    return null;
}
//End-----------------------------------------------------------------------End
//Display the 6 areas from the database
public void displayReturn() {
    DefaultTableModel returnModel = (DefaultTableModel) tblReturn.getModel();
    returnModel.setRowCount(0); // Clear existing rows

    try {
        String sql = "SELECT r.ReturnID, r.ItemID, s.SerialNo, s.ItemName, s.Model, s.Specification,  s.Category, s.Brand, r.Qty AS ReturnedQty, r.Date " +
                     "FROM Return r " +
                     "LEFT JOIN Stock s ON r.ItemID = s.ItemID ";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int returnID = rst.getInt("ReturnID");
                int itemID = rst.getInt("ItemID");
                String serialNo = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String model = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int returnedQty = rst.getInt("ReturnedQty");
                Date date = rst.getDate("Date");

                // Add a row to the Return table with all the information
                returnModel.addRow(new Object[]{returnID, itemID, serialNo, itemName, model, specification, category, brand, returnedQty, date});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving Return data from the database.");
    }
}
public void displayRepair() {
    DefaultTableModel repairModel = (DefaultTableModel) tblRepair.getModel();
    repairModel.setRowCount(0); // Clear existing rows

    try {
        // Modify the SQL query for Repair area
        String sql = "SELECT rep.RepairID, rep.ItemID, s.SerialNo, s.Model, s.Specification, s.Category, s.Brand, rep.Qty AS RepairedQty, rep.Date " +
                     "FROM Repair rep " +
                     "LEFT JOIN Stock s ON rep.ItemID = s.ItemID ";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int repairID = rst.getInt("RepairID");
                int itemID = rst.getInt("ItemID");
                String serialNo = getItemSerialNumber(itemID);
                String itemName = getItemName(itemID); // Utilize getItemName method
                String model = getItemModel(itemID); // Utilize getItemModel method
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int repairedQty = rst.getInt("RepairedQty");
                Date date = rst.getDate("Date");

                // Add a row to the Repair table with all the information
                repairModel.addRow(new Object[]{repairID, itemID, serialNo, itemName, model, specification, category, brand, repairedQty, date});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving Repair data from the database.");
    }
}
public void displayDisposal() {
    DefaultTableModel disposalModel = (DefaultTableModel) tblDisposal.getModel();
    disposalModel.setRowCount(0); // Clear existing rows

    try {
        // Modify the SQL query for Disposal area
        String sql = "SELECT d.DisposalID, d.ItemID, s.SerialNo, s.ItemName, s.Model, s.Specification, s.Category, s.Brand, d.Qty AS DisposedQty, d.Date " +
                     "FROM Disposal d " +
                     "LEFT JOIN Stock s ON d.ItemID = s.ItemID ";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int disposalID = rst.getInt("DisposalID");
                int itemID = rst.getInt("ItemID");
                String serialNo = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String model = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int disposedQty = rst.getInt("DisposedQty");
                Date date = rst.getDate("Date");

                // Add a row to the Disposal table with all the information
                disposalModel.addRow(new Object[]{disposalID, itemID, serialNo, itemName, model, specification, category, brand, disposedQty, date});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving Disposal data from the database.");
    }
}
public void displayCheckingData() {
    DefaultTableModel checkingModel = (DefaultTableModel) tblChecking.getModel();
    checkingModel.setRowCount(0); // Clear existing rows
    try {
        String sql = "SELECT c.CheckingID, c.ItemID, s.SerialNo, s.ItemName, s.Model, s.Specification, s.Category, s.Brand, c.Qty AS TransferredQty, c.Date " +
                     "FROM Checking c " +
                     "LEFT JOIN Stock s ON c.ItemID = s.ItemID ";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int checkingID = rst.getInt("CheckingID");
                int itemID = rst.getInt("ItemID");
                String serialNo = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String model = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int transferredQty = rst.getInt("TransferredQty");
                Date date = rst.getDate("Date");

                // Add a row to the Checking table with all the information
                checkingModel.addRow(new Object[]{checkingID, itemID, serialNo, itemName, model, specification, category, brand, transferredQty, date});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving Checking data from the database.");
    }
}
public void displayReturnData() {
    DefaultTableModel model = (DefaultTableModel) tblReturn.getModel();
    model.setRowCount(0); // Clear existing rows

    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:CELO_Database.db");

        String sql = "SELECT r.ReturnID, r.ItemID, s.SerialNo, s.ItemName, s.Model, s.Specification, s.Category, s.Brand, c.Qty AS CheckedQty, r.Qty AS ReturnedQty, r.Date " +
                     "FROM Return r " +
                     "INNER JOIN Checking c ON r.ItemID = c.ItemID " +
                     "INNER JOIN Stock s ON r.ItemID = s.ItemID";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int returnID = rst.getInt("ReturnID");
                int itemID = rst.getInt("ItemID");
                String serialNumber = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String modelValue = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int checkedQty = rst.getInt("CheckedQty");
                int returnedQty = rst.getInt("ReturnedQty");
                Date returnDate = rst.getDate("Date");

                // Add a row to the table with all the information
                model.addRow(new Object[]{returnID, itemID, serialNumber, itemName, modelValue, specification,  category, brand, checkedQty, returnedQty, returnDate});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving return data from the database.");
    }
}
public void displayReleaseData() {
    DefaultTableModel releaseModel = (DefaultTableModel) tblRelease.getModel();
    releaseModel.setRowCount(0); // Clear existing rows
    
    try {
        String sql = "SELECT r.ReleaseID, r.ItemID, r.Location, s.SerialNo, s.ItemName, s.Model, s.Specification, s.Category, s.Brand, r.Qty AS ReleasedQty, r.Date " +
                     "FROM Release r " +
                     "LEFT JOIN Stock s ON r.ItemID = s.ItemID ";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int releaseID = rst.getInt("ReleaseID");
                int itemID = rst.getInt("ItemID");
                String location = rst.getString("Location");
                String serialNo = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String model = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int releasedQty = rst.getInt("ReleasedQty");
                Date date = rst.getDate("Date");

                // Add a row to the Release table with all the information
                releaseModel.addRow(new Object[]{releaseID, itemID, location, serialNo, itemName, model, specification, category, brand, releasedQty, date});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving Release data from the database.");
    }
}
public void displayCombinedData() {
    DefaultTableModel model = (DefaultTableModel) tblStock.getModel();
    model.setRowCount(0); // Clear existing rows

    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:CELO_Database.db");

        String sql = "SELECT s.ItemID, s.SerialNo, s.ItemName, s.Model, s.Category, s.Specification, s.Brand, s.Qty, " +
                     "c.Date AS CheckingDate " +
                     "FROM Stock s " +
                     "LEFT JOIN Checking c ON s.ItemID = c.ItemID ";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int itemID = rst.getInt("ItemID");
                String serialNumber = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String modelValue = rst.getString("Model");
                String category = rst.getString("Category");
                String specification = rst.getString("Specification");
                String brand = rst.getString("Brand");
                int quantity = rst.getInt("Qty");

                // Retrieve dates from different tables
                Date checkingDate = rst.getDate("CheckingDate");

                // Add a row to the table with all the information
                model.addRow(new Object[]{itemID, serialNumber, itemName, modelValue, category, specification, brand, quantity, checkingDate});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving combined data from the database.");
    }
}
public void displayChecking() {
    DefaultTableModel model = (DefaultTableModel) tblChecking.getModel();
    model.setRowCount(0); // Clear existing rows

    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:CELO_Database.db");

        String sql = "SELECT s.ItemID, s.SerialNo, s.ItemName, s.Model, s.Category, s.Specification, s.Brand, s.Qty, " +
                     "c.Date AS CheckingDate " +
                     "FROM Stock s " +
                     "LEFT JOIN Return c ON s.ItemID = c.ItemID ";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int itemID = rst.getInt("ItemID");
                String serialNumber = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String modelValue = rst.getString("Model");
                String category = rst.getString("Category");
                String specification = rst.getString("Specification");
                String brand = rst.getString("Brand");
                int quantity = rst.getInt("Qty");

                // Retrieve dates from different tables
                Date returnDate = rst.getDate("ReturnDate");

                // Add a row to the table with all the information
                model.addRow(new Object[]{itemID, serialNumber, itemName, modelValue, category, specification, brand, quantity, returnDate});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving combined data from the database.");
    }
}
//End--------------------------------------------------------------------------------End
//Validation method
private boolean validateFields() {
        if (txtSerial.getText().isEmpty() || txtItemName.getText().isEmpty() ||
            txtModel.getText().isEmpty() || txtSpecification.getText().isEmpty() ||
            txtBrand.getText().isEmpty() || txtQty.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Please fill in all the required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return false; // Validation failed
        }

        return true; // Validation passed
    }
//End--------------------------------------------------------------------------------End
//Clear funtionality
public void clear(){
        txtSerial.setText("");
        txtItemName.setText("");
        txtModel.setText("");
        txtSpecification.setText("");
        cbSCategory.setSelectedIndex(0);
        txtBrand.setText("");
        txtQty.setText("");
        txtSearchItem.setText("");
    } 
//End--------------------------------------------------------------------------------End
//Display data from database to ADD form table
public void displayDataItems() {
    DefaultTableModel model = (DefaultTableModel) tblDataInfo.getModel();
    model.setRowCount(0); // Clear existing rows

    try {
        String sql = "SELECT * FROM Stock";
        pst = conn.prepareStatement(sql);
        rst = pst.executeQuery();

        while (rst.next()) {
            int ItemID = rst.getInt("ItemID");
            String serialNumber = rst.getString("SerialNo");
            String itemName = rst.getString("ItemName");
            String modelValue = rst.getString("Model");
            String specification = rst.getString("Specification");
            String category = rst.getString("Category");
            String brand = rst.getString("Brand");
            int quantity = rst.getInt("Qty");

            // Map database column names to JTable column names
            model.addRow(new Object[]{ItemID, serialNumber, itemName, modelValue, specification, category, brand, quantity});
        }

    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving items from the database.");
    } finally {
        try {
            if (rst != null) {
                rst.close();
            }
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
//Display Stock Records
public void displayStockItems() {
    DefaultTableModel model = (DefaultTableModel) tblStock.getModel();
    model.setRowCount(0); // Clear existing rows

    try {
        String sql = "SELECT * FROM Stock";
        pst = conn.prepareStatement(sql);
        rst = pst.executeQuery();

        while (rst.next()) {
            int ItemID = rst.getInt("ItemID");
            String serialNumber = rst.getString("SerialNo");
            String itemName = rst.getString("ItemName");
            String modelValue = rst.getString("Model");
            String specification = rst.getString("Specification");
            String category = rst.getString("Category");
            String brand = rst.getString("Brand");
            int quantity = rst.getInt("Qty");

            // Map database column names to JTable column names
            model.addRow(new Object[]{ItemID, serialNumber, itemName, modelValue, specification, category, brand, quantity});
        }

    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error retrieving items from the database.");
    } finally {
        try {
            if (rst != null) {
                rst.close();
            }
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
public static void printStock(JTable tblStock) {
        // Get the table model
        TableModel model = tblStock.getModel();

        try {
            // Print the table
            boolean complete = tblStock.print();

            if (complete) {
                System.out.println("Print successful");
            } else {
                System.out.println("Print canceled");
            }
        } catch (PrinterException pe) {
            System.out.println("Print failed: " + pe.getMessage());
        }
    }
public static void printRelease(JTable tblRelease) {
        // Get the table model
        TableModel model = tblRelease.getModel();

        try {
            // Print the table
            boolean complete = tblRelease.print();

            if (complete) {
                System.out.println("Print successful");
            } else {
                System.out.println("Print canceled");
            }
        } catch (PrinterException pe) {
            System.out.println("Print failed: " + pe.getMessage());
        }
    }
public static void printChecking(JTable tblChecking) {
        // Get the table model
        TableModel model = tblChecking.getModel();

        try {
            // Print the table
            boolean complete = tblChecking.print();

            if (complete) {
                System.out.println("Print successful");
            } else {
                System.out.println("Print canceled");
            }
        } catch (PrinterException pe) {
            System.out.println("Print failed: " + pe.getMessage());
        }
    }
public static void printReturn(JTable tblReturn) {
        // Get the table model
        TableModel model = tblReturn.getModel();

        try {
            // Print the table
            boolean complete = tblReturn.print();

            if (complete) {
                System.out.println("Print successful");
            } else {
                System.out.println("Print canceled");
            }
        } catch (PrinterException pe) {
            System.out.println("Print failed: " + pe.getMessage());
        }
    }
public static void printRepair(JTable tblRepair) {
        // Get the table model
        TableModel model = tblRepair.getModel();

        try {
            // Print the table
            boolean complete = tblRepair.print();

            if (complete) {
                System.out.println("Print successful");
            } else {
                System.out.println("Print canceled");
            }
        } catch (PrinterException pe) {
            System.out.println("Print failed: " + pe.getMessage());
        }
    }
public static void printDisposal(JTable tblDisposal) {
        // Get the table model
        TableModel model = tblDisposal.getModel();

        try {
            // Print the table
            boolean complete = tblDisposal.print();

            if (complete) {
                System.out.println("Print successful");
            } else {
                System.out.println("Print canceled");
            }
        } catch (PrinterException pe) {
            System.out.println("Print failed: " + pe.getMessage());
        }
    }
//End--------------------------------------------------------------------------------End
// Define a method to fetch and display data from the accounts table
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnDashboard = new javax.swing.JButton();
        tabPane = new javax.swing.JTabbedPane();
        dashboard = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        lblChecking = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        lblRepair = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        lblDisposal = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        lblReturn = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        lblTotalqty = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        lblRelease = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtItemName = new javax.swing.JTextField();
        txtModel = new javax.swing.JTextField();
        txtQty = new javax.swing.JTextField();
        txtBrand = new javax.swing.JTextField();
        btnInsert = new javax.swing.JButton();
        txtClear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDataInfo = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        txtSpecification = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cbSCategory = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        txtSerial = new javax.swing.JTextField();
        txtSearchItem = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        tabSuperadmin = new javax.swing.JTabbedPane();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblStock = new javax.swing.JTable();
        txtSearchStock = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        btnViewUser4 = new javax.swing.JButton();
        btnPrint_stock = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblRelease = new javax.swing.JTable();
        btnPrint_release = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        btnViewUser9 = new javax.swing.JButton();
        txtSearchRelease = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblChecking = new javax.swing.JTable();
        btnPrint_checking = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        txtSearchChecking = new javax.swing.JTextField();
        btnViewUser10 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblReturn = new javax.swing.JTable();
        btnPrint_return = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        txtSearchReturn = new javax.swing.JTextField();
        btnViewUser11 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblRepair = new javax.swing.JTable();
        btnPrint_repair = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        txtSearchRepair = new javax.swing.JTextField();
        btnViewUser12 = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblDisposal = new javax.swing.JTable();
        btnPrint_disposal = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        txtSearchDisposal = new javax.swing.JTextField();
        btnViewUser13 = new javax.swing.JButton();
        btnStock = new javax.swing.JButton();
        btnChecking = new javax.swing.JButton();
        btnReturn = new javax.swing.JButton();
        btnRepair = new javax.swing.JButton();
        btnDisposal = new javax.swing.JButton();
        btnDisposal1 = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnRecords = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/logos_small_67RB7KTLSNXPWKW6NAG7-56a62ad1-removebg-preview (1) (3).png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 280, -1));

        btnDashboard.setFont(new java.awt.Font("Rockwell", 1, 32)); // NOI18N
        btnDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/80x70 dashboard.png"))); // NOI18N
        btnDashboard.setText("  Dashboard");
        btnDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDashboardActionPerformed(evt);
            }
        });
        jPanel1.add(btnDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 310, 80));

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1517, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 138, Short.MAX_VALUE)
        );

        jPanel12.setBackground(new java.awt.Color(211, 109, 109));
        jPanel12.setPreferredSize(new java.awt.Dimension(241, 104));

        lblChecking.setFont(new java.awt.Font("Rockwell", 1, 50)); // NOI18N
        lblChecking.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblChecking.setText("0");

        jLabel31.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel31.setText("Checking Items");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Checking.png"))); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblChecking, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(0, 120, Short.MAX_VALUE)
                        .addComponent(jLabel31)))
                .addGap(18, 18, 18))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblChecking)
                        .addGap(42, 42, 42)))
                .addComponent(jLabel31)
                .addGap(35, 35, 35))
        );

        jPanel14.setBackground(new java.awt.Color(106, 177, 135));
        jPanel14.setPreferredSize(new java.awt.Dimension(241, 104));

        lblRepair.setFont(new java.awt.Font("Rockwell", 1, 50)); // NOI18N
        lblRepair.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRepair.setText("0");

        jLabel33.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel33.setText("Repair Items");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Repair.png"))); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRepair, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(172, Short.MAX_VALUE)
                .addComponent(jLabel33)
                .addGap(26, 26, 26))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblRepair)
                .addGap(44, 44, 44)
                .addComponent(jLabel33)
                .addGap(34, 34, 34))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel20.setBackground(new java.awt.Color(190, 159, 191));
        jPanel20.setPreferredSize(new java.awt.Dimension(241, 104));

        lblDisposal.setFont(new java.awt.Font("Rockwell", 1, 50)); // NOI18N
        lblDisposal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDisposal.setText("0");

        jLabel39.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel39.setText("Disposal Items");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Disposal.png"))); // NOI18N

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDisposal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(0, 140, Short.MAX_VALUE)
                        .addComponent(jLabel39)))
                .addGap(16, 16, 16))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(lblDisposal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)))
                .addComponent(jLabel39)
                .addGap(37, 37, 37))
        );

        jPanel21.setBackground(new java.awt.Color(102, 102, 154));
        jPanel21.setPreferredSize(new java.awt.Dimension(241, 104));

        lblReturn.setFont(new java.awt.Font("Rockwell", 1, 50)); // NOI18N
        lblReturn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReturn.setText("0");

        jLabel37.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel37.setText("Return Items");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Return.png"))); // NOI18N

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(lblReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(166, Short.MAX_VALUE)
                .addComponent(jLabel37)
                .addGap(30, 30, 30))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(lblReturn)
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)))
                .addComponent(jLabel37)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel47.setFont(new java.awt.Font("Rockwell", 1, 55)); // NOI18N
        jLabel47.setText("DASHBOARD");

        jPanel19.setBackground(new java.awt.Color(255, 232, 163));
        jPanel19.setPreferredSize(new java.awt.Dimension(241, 104));

        lblTotalqty.setFont(new java.awt.Font("Rockwell", 1, 50)); // NOI18N
        lblTotalqty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalqty.setText("0");

        jLabel28.setFont(new java.awt.Font("Rockwell", 1, 37)); // NOI18N
        jLabel28.setText("Total");

        jLabel29.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel29.setText("Stock Items");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addGap(105, 105, 105))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(132, 132, 132))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addComponent(lblTotalqty, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTotalqty, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel29)
                .addGap(31, 31, 31))
        );

        jPanel28.setBackground(new java.awt.Color(251, 202, 126));
        jPanel28.setPreferredSize(new java.awt.Dimension(241, 104));

        lblRelease.setFont(new java.awt.Font("Rockwell", 1, 50)); // NOI18N
        lblRelease.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRelease.setText("0");

        jLabel38.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel38.setText("Release Items");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Release.png"))); // NOI18N

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRelease, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap(148, Short.MAX_VALUE)
                .addComponent(jLabel38)
                .addGap(29, 29, 29))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblRelease)
                        .addGap(34, 34, 34))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)))
                .addComponent(jLabel38)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout dashboardLayout = new javax.swing.GroupLayout(dashboard);
        dashboard.setLayout(dashboardLayout);
        dashboardLayout.setHorizontalGroup(
            dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardLayout.createSequentialGroup()
                .addGroup(dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dashboardLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                            .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(dashboardLayout.createSequentialGroup()
                        .addGap(544, 544, 544)
                        .addComponent(jLabel47)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dashboardLayout.setVerticalGroup(
            dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel47)
                .addGap(48, 48, 48)
                .addGroup(dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addGroup(dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62))
        );

        tabPane.addTab("tab1", dashboard);

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel5.setText("Item Name:");

        jLabel6.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel6.setText("Model:");

        jLabel7.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel7.setText("Category:");

        jLabel8.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel8.setText("Brand:");

        jLabel10.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel10.setText("Quantity:");

        txtItemName.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N

        txtModel.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        txtModel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtModelActionPerformed(evt);
            }
        });

        txtQty.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        txtQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQtyActionPerformed(evt);
            }
        });

        txtBrand.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        txtBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBrandActionPerformed(evt);
            }
        });

        btnInsert.setBackground(new java.awt.Color(93, 190, 163));
        btnInsert.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnInsert.setText("Insert");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        txtClear.setBackground(new java.awt.Color(93, 190, 163));
        txtClear.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        txtClear.setText("Clear");
        txtClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClearActionPerformed(evt);
            }
        });

        tblDataInfo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblDataInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "SerialNo", "ItemName", "Model", "Specification", "Category", "Brand", "Qty"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDataInfo.setRowHeight(21);
        tblDataInfo.setShowGrid(true);
        jScrollPane1.setViewportView(tblDataInfo);
        if (tblDataInfo.getColumnModel().getColumnCount() > 0) {
            tblDataInfo.getColumnModel().getColumn(0).setResizable(false);
            tblDataInfo.getColumnModel().getColumn(1).setResizable(false);
            tblDataInfo.getColumnModel().getColumn(2).setResizable(false);
            tblDataInfo.getColumnModel().getColumn(3).setResizable(false);
            tblDataInfo.getColumnModel().getColumn(4).setResizable(false);
            tblDataInfo.getColumnModel().getColumn(5).setResizable(false);
            tblDataInfo.getColumnModel().getColumn(6).setResizable(false);
            tblDataInfo.getColumnModel().getColumn(7).setResizable(false);
        }

        jLabel13.setFont(new java.awt.Font("Rockwell", 1, 55)); // NOI18N
        jLabel13.setText("ADD ITEM FORM");

        txtSpecification.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel15.setText("Specification:");

        cbSCategory.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        cbSCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hardware", "Software" }));

        jLabel18.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel18.setText("Serial No:");

        txtSerial.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        txtSerial.setPreferredSize(new java.awt.Dimension(63, 22));

        txtSearchItem.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        txtSearchItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchItemActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jLabel25.setText("Search:");

        jPanel8.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1516, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 121, Short.MAX_VALUE)
        );

        jButton1.setBackground(new java.awt.Color(93, 190, 163));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Refresh .png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel25)
                        .addGap(0, 0, 0)
                        .addComponent(txtSearchItem, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jButton1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel10))
                                .addGap(0, 0, 0)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSpecification)
                                    .addComponent(cbSCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtBrand)
                                    .addComponent(txtQty)
                                    .addComponent(txtSerial, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtItemName)
                                    .addComponent(txtModel)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtClear, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnInsert)))
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 935, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(69, 69, 69))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(481, 481, 481)
                        .addComponent(jLabel13)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearchItem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtClear, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSerial, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(13, 13, 13)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtModel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(txtSpecification, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(17, 17, 17)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbSCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(17, 17, 17)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txtBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(130, 130, 130))))
        );

        tabPane.addTab("tab2", jPanel3);

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));

        jLabel14.setFont(new java.awt.Font("Rockwell", 1, 48)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(247, 247, 217));
        jLabel14.setText("''Outsourcing inspired by Quality''");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 864, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1863, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(544, Short.MAX_VALUE))
        );

        tabSuperadmin.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        tblStock.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tblStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Serial No.", "ItemName", "Model", "Specification", "Category", "Brand", "Qty."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblStock.setRowHeight(24);
        tblStock.setShowGrid(true);
        jScrollPane2.setViewportView(tblStock);
        if (tblStock.getColumnModel().getColumnCount() > 0) {
            tblStock.getColumnModel().getColumn(0).setResizable(false);
            tblStock.getColumnModel().getColumn(1).setResizable(false);
            tblStock.getColumnModel().getColumn(2).setResizable(false);
            tblStock.getColumnModel().getColumn(3).setResizable(false);
            tblStock.getColumnModel().getColumn(4).setResizable(false);
            tblStock.getColumnModel().getColumn(5).setResizable(false);
            tblStock.getColumnModel().getColumn(6).setResizable(false);
            tblStock.getColumnModel().getColumn(7).setResizable(false);
        }

        txtSearchStock.setFont(new java.awt.Font("Rockwell", 0, 20)); // NOI18N
        txtSearchStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchStockActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jLabel27.setText("Search:");

        btnViewUser4.setBackground(new java.awt.Color(93, 190, 163));
        btnViewUser4.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        btnViewUser4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Refresh .png"))); // NOI18N
        btnViewUser4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewUser4ActionPerformed(evt);
            }
        });

        btnPrint_stock.setBackground(new java.awt.Color(93, 190, 163));
        btnPrint_stock.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnPrint_stock.setText("Print");
        btnPrint_stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrint_stockActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(btnPrint_stock)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(txtSearchStock, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnViewUser4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1443, Short.MAX_VALUE)))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(btnPrint_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearchStock, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnViewUser4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        tabSuperadmin.addTab("tab1", jPanel13);

        tblRelease.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tblRelease.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ReleaseID", "ItemID", "Location", "SerialNo", "ItemName", "Model", "Specification", "Category", "Brand", "Qty", "ReleaseDate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRelease.setRowHeight(24);
        tblRelease.setShowGrid(true);
        jScrollPane8.setViewportView(tblRelease);
        if (tblRelease.getColumnModel().getColumnCount() > 0) {
            tblRelease.getColumnModel().getColumn(0).setResizable(false);
            tblRelease.getColumnModel().getColumn(1).setResizable(false);
            tblRelease.getColumnModel().getColumn(2).setResizable(false);
            tblRelease.getColumnModel().getColumn(3).setResizable(false);
            tblRelease.getColumnModel().getColumn(4).setResizable(false);
            tblRelease.getColumnModel().getColumn(5).setResizable(false);
            tblRelease.getColumnModel().getColumn(6).setResizable(false);
            tblRelease.getColumnModel().getColumn(7).setResizable(false);
            tblRelease.getColumnModel().getColumn(8).setResizable(false);
            tblRelease.getColumnModel().getColumn(9).setResizable(false);
            tblRelease.getColumnModel().getColumn(10).setResizable(false);
        }

        btnPrint_release.setBackground(new java.awt.Color(93, 190, 163));
        btnPrint_release.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnPrint_release.setText("Print");
        btnPrint_release.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrint_releaseActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jLabel40.setText("Search:");

        btnViewUser9.setBackground(new java.awt.Color(93, 190, 163));
        btnViewUser9.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        btnViewUser9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Refresh .png"))); // NOI18N
        btnViewUser9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewUser9ActionPerformed(evt);
            }
        });

        txtSearchRelease.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        txtSearchRelease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchReleaseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPrint_release)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1104, Short.MAX_VALUE)
                .addComponent(jLabel40)
                .addGap(3, 3, 3)
                .addComponent(txtSearchRelease, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(btnViewUser9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 1443, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnViewUser9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSearchRelease, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnPrint_release, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(37, Short.MAX_VALUE)))
        );

        tabSuperadmin.addTab("tab6", jPanel2);

        tblChecking.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tblChecking.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "CheckingID", "ItemID", "Serial No.", "Name", "Model", "Specification", "Category", "Brand", "Qty.", "CheckDate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChecking.setRowHeight(24);
        tblChecking.setShowGrid(true);
        jScrollPane3.setViewportView(tblChecking);
        if (tblChecking.getColumnModel().getColumnCount() > 0) {
            tblChecking.getColumnModel().getColumn(0).setResizable(false);
            tblChecking.getColumnModel().getColumn(1).setResizable(false);
            tblChecking.getColumnModel().getColumn(2).setResizable(false);
            tblChecking.getColumnModel().getColumn(3).setResizable(false);
            tblChecking.getColumnModel().getColumn(4).setResizable(false);
            tblChecking.getColumnModel().getColumn(5).setResizable(false);
            tblChecking.getColumnModel().getColumn(6).setResizable(false);
            tblChecking.getColumnModel().getColumn(7).setResizable(false);
            tblChecking.getColumnModel().getColumn(8).setResizable(false);
            tblChecking.getColumnModel().getColumn(9).setResizable(false);
        }

        btnPrint_checking.setBackground(new java.awt.Color(93, 190, 163));
        btnPrint_checking.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnPrint_checking.setText("Print");
        btnPrint_checking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrint_checkingActionPerformed(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jLabel41.setText("Search:");

        txtSearchChecking.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        txtSearchChecking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchCheckingActionPerformed(evt);
            }
        });

        btnViewUser10.setBackground(new java.awt.Color(93, 190, 163));
        btnViewUser10.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        btnViewUser10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Refresh .png"))); // NOI18N
        btnViewUser10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewUser10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1443, Short.MAX_VALUE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(btnPrint_checking)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel41)
                        .addGap(3, 3, 3)
                        .addComponent(txtSearchChecking, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(btnViewUser10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPrint_checking, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnViewUser10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSearchChecking, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        tabSuperadmin.addTab("tab2", jPanel15);

        tblReturn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tblReturn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ReturnID", "ItemID", "SerialNo.", "Name", "Model", "Specification", "Category", "Brand", "Qty.", "ReturnDate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblReturn.setRowHeight(24);
        tblReturn.setShowGrid(true);
        jScrollPane4.setViewportView(tblReturn);
        if (tblReturn.getColumnModel().getColumnCount() > 0) {
            tblReturn.getColumnModel().getColumn(0).setResizable(false);
            tblReturn.getColumnModel().getColumn(1).setResizable(false);
            tblReturn.getColumnModel().getColumn(2).setResizable(false);
            tblReturn.getColumnModel().getColumn(3).setResizable(false);
            tblReturn.getColumnModel().getColumn(4).setResizable(false);
            tblReturn.getColumnModel().getColumn(5).setResizable(false);
            tblReturn.getColumnModel().getColumn(6).setResizable(false);
            tblReturn.getColumnModel().getColumn(7).setResizable(false);
            tblReturn.getColumnModel().getColumn(8).setResizable(false);
            tblReturn.getColumnModel().getColumn(9).setResizable(false);
        }

        btnPrint_return.setBackground(new java.awt.Color(93, 190, 163));
        btnPrint_return.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnPrint_return.setText("Print");
        btnPrint_return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrint_returnActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jLabel42.setText("Search:");

        txtSearchReturn.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        txtSearchReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchReturnActionPerformed(evt);
            }
        });

        btnViewUser11.setBackground(new java.awt.Color(93, 190, 163));
        btnViewUser11.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        btnViewUser11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Refresh .png"))); // NOI18N
        btnViewUser11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewUser11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1443, Short.MAX_VALUE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(btnPrint_return)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel42)
                        .addGap(3, 3, 3)
                        .addComponent(txtSearchReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(btnViewUser11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPrint_return, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnViewUser11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSearchReturn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        tabSuperadmin.addTab("tab3", jPanel16);

        tblRepair.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tblRepair.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "RepairID", "ItemID", "Serial No.", "Name", "Model", "Specification", "Category", "Brand", "Qty", "RepairDate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRepair.setRowHeight(24);
        tblRepair.setShowGrid(true);
        jScrollPane5.setViewportView(tblRepair);
        if (tblRepair.getColumnModel().getColumnCount() > 0) {
            tblRepair.getColumnModel().getColumn(0).setResizable(false);
            tblRepair.getColumnModel().getColumn(1).setResizable(false);
            tblRepair.getColumnModel().getColumn(2).setResizable(false);
            tblRepair.getColumnModel().getColumn(3).setResizable(false);
            tblRepair.getColumnModel().getColumn(4).setResizable(false);
            tblRepair.getColumnModel().getColumn(5).setResizable(false);
            tblRepair.getColumnModel().getColumn(6).setResizable(false);
            tblRepair.getColumnModel().getColumn(7).setResizable(false);
            tblRepair.getColumnModel().getColumn(8).setResizable(false);
            tblRepair.getColumnModel().getColumn(9).setResizable(false);
        }

        btnPrint_repair.setBackground(new java.awt.Color(93, 190, 163));
        btnPrint_repair.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnPrint_repair.setText("Print");
        btnPrint_repair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrint_repairActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jLabel43.setText("Search:");

        txtSearchRepair.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        txtSearchRepair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchRepairActionPerformed(evt);
            }
        });

        btnViewUser12.setBackground(new java.awt.Color(93, 190, 163));
        btnViewUser12.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        btnViewUser12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Refresh .png"))); // NOI18N
        btnViewUser12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewUser12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1443, Short.MAX_VALUE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(btnPrint_repair)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel43)
                        .addGap(3, 3, 3)
                        .addComponent(txtSearchRepair, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(btnViewUser12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPrint_repair, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnViewUser12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel43, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSearchRepair, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        tabSuperadmin.addTab("tab4", jPanel17);

        tblDisposal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tblDisposal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "DisposalID", "ItemID", "Serial No.", "Name", "Model", "Specification", "Category", "Brand", "Qty.", "DisposalDate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDisposal.setRowHeight(24);
        tblDisposal.setShowGrid(true);
        jScrollPane6.setViewportView(tblDisposal);
        if (tblDisposal.getColumnModel().getColumnCount() > 0) {
            tblDisposal.getColumnModel().getColumn(0).setResizable(false);
            tblDisposal.getColumnModel().getColumn(1).setResizable(false);
            tblDisposal.getColumnModel().getColumn(2).setResizable(false);
            tblDisposal.getColumnModel().getColumn(3).setResizable(false);
            tblDisposal.getColumnModel().getColumn(4).setResizable(false);
            tblDisposal.getColumnModel().getColumn(5).setResizable(false);
            tblDisposal.getColumnModel().getColumn(6).setResizable(false);
            tblDisposal.getColumnModel().getColumn(7).setResizable(false);
            tblDisposal.getColumnModel().getColumn(8).setResizable(false);
            tblDisposal.getColumnModel().getColumn(9).setResizable(false);
        }

        btnPrint_disposal.setBackground(new java.awt.Color(93, 190, 163));
        btnPrint_disposal.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnPrint_disposal.setText("Print");
        btnPrint_disposal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrint_disposalActionPerformed(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jLabel44.setText("Search:");

        txtSearchDisposal.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        txtSearchDisposal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchDisposalActionPerformed(evt);
            }
        });

        btnViewUser13.setBackground(new java.awt.Color(93, 190, 163));
        btnViewUser13.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        btnViewUser13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Refresh .png"))); // NOI18N
        btnViewUser13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewUser13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(btnPrint_disposal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel44)
                        .addGap(3, 3, 3)
                        .addComponent(txtSearchDisposal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(btnViewUser13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1443, Short.MAX_VALUE)))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPrint_disposal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnViewUser13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel44, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSearchDisposal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        tabSuperadmin.addTab("tab5", jPanel18);

        btnStock.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnStock.setText("Stock");
        btnStock.setPreferredSize(new java.awt.Dimension(98, 25));
        btnStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStockActionPerformed(evt);
            }
        });

        btnChecking.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnChecking.setText("Checking");
        btnChecking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckingActionPerformed(evt);
            }
        });

        btnReturn.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnReturn.setText("Return");
        btnReturn.setPreferredSize(new java.awt.Dimension(98, 25));
        btnReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnActionPerformed(evt);
            }
        });

        btnRepair.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnRepair.setText("Repair");
        btnRepair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRepairActionPerformed(evt);
            }
        });

        btnDisposal.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnDisposal.setText("Disposal");
        btnDisposal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisposalActionPerformed(evt);
            }
        });

        btnDisposal1.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnDisposal1.setText("Release");
        btnDisposal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisposal1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabSuperadmin, javax.swing.GroupLayout.PREFERRED_SIZE, 1449, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(btnStock, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(btnDisposal1)
                        .addGap(2, 2, 2)
                        .addComponent(btnChecking)
                        .addGap(2, 2, 2)
                        .addComponent(btnReturn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(btnRepair)
                        .addGap(2, 2, 2)
                        .addComponent(btnDisposal)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRepair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDisposal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReturn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnChecking, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDisposal1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(tabSuperadmin, javax.swing.GroupLayout.PREFERRED_SIZE, 832, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 185, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 826, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tabPane.addTab("tab4", jPanel4);

        jPanel1.add(tabPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(359, -40, 1490, 990));

        btnAdd.setFont(new java.awt.Font("Rockwell", 1, 32)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/80x70 item.png"))); // NOI18N
        btnAdd.setText("  Items");
        btnAdd.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel1.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 310, 80));

        btnRecords.setFont(new java.awt.Font("Rockwell", 1, 32)); // NOI18N
        btnRecords.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/80x70 records.png"))); // NOI18N
        btnRecords.setText("  Records");
        btnRecords.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRecords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecordsActionPerformed(evt);
            }
        });
        jPanel1.add(btnRecords, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, 310, 80));

        btnLogout.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        btnLogout.setText("LOG OUT");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 820, 220, 70));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
        // TODO add your handling code here:
        displayTotalDataCount();
        displayReleaseCount();
        displayCheckingCount();
        displayRepairCount();
        displayReturnCount();
        displayDisposalCount();
        tabPane.setSelectedIndex(0);
        displayTotalDataCount();
        
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
          tabPane.setSelectedIndex(1);
          displayDataItems();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRecordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecordsActionPerformed
        // TODO add your handling code here:
          tabPane.setSelectedIndex(2);
          displayStockItems();
    }//GEN-LAST:event_btnRecordsActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {

            JOptionPane.showMessageDialog(null, "Logout Successful");
             new LogIn().setVisible(true);
            dispose();
        } else {

        }
          
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void txtBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBrandActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBrandActionPerformed

    private void txtQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQtyActionPerformed

    private void txtModelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtModelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModelActionPerformed

    private void txtClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_txtClearActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        // TODO add your handling code here:
        insertItem();
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockActionPerformed
        // TODO add your handling code here:
        tabSuperadmin.setSelectedIndex(0);
        displayStockItems();
    }//GEN-LAST:event_btnStockActionPerformed

    private void btnCheckingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckingActionPerformed
        // TODO add your handling code here:
        tabSuperadmin.setSelectedIndex(2);
        displayCheckingData();
    }//GEN-LAST:event_btnCheckingActionPerformed

    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
        // TODO add your handling code here:
        tabSuperadmin.setSelectedIndex(3);
        displayReturnData();
        displayReturn();

    }//GEN-LAST:event_btnReturnActionPerformed

    private void btnRepairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRepairActionPerformed
        // TODO add your handling code here:
        tabSuperadmin.setSelectedIndex(4);
        displayRepair();

    }//GEN-LAST:event_btnRepairActionPerformed

    private void btnDisposalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisposalActionPerformed
        // TODO add your handling code here:
        tabSuperadmin.setSelectedIndex(5);
        displayDisposal();
        
    }//GEN-LAST:event_btnDisposalActionPerformed

    private void txtSearchStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchStockActionPerformed
        // TODO add your handling code here:
        String searchText = txtSearchStock.getText().trim();

        DefaultTableModel model = (DefaultTableModel) tblStock.getModel();
        model.setRowCount(0); // Clear existing rows

        try {
            String sql = "SELECT * FROM Stock WHERE SerialNo LIKE ? OR ItemName LIKE ? OR Model LIKE ? OR Category LIKE ? OR Brand LIKE ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + searchText + "%");
            pst.setString(2, "%" + searchText + "%");
            pst.setString(3, "%" + searchText + "%");
            pst.setString(4, "%" + searchText + "%");
            pst.setString(5, "%" + searchText + "%");
            rst = pst.executeQuery();

            while (rst.next()) {
                int ItemID = rst.getInt("ItemID");
                String serialNumber = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String modelValue = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int quantity = rst.getInt("Qty");

                // Map database column names to JTable column names
                model.addRow(new Object[]{ItemID, serialNumber, itemName, modelValue, specification, category, brand, quantity});
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Error searching items in the database.");
        } finally {
            try {
                if (rst != null) {
                    rst.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    
    }//GEN-LAST:event_txtSearchStockActionPerformed

    private void txtSearchItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchItemActionPerformed
        // TODO add your handling code here:
        String searchText = txtSearchItem.getText().trim();

        DefaultTableModel model = (DefaultTableModel) tblDataInfo.getModel();
        model.setRowCount(0); // Clear existing rows

        try {
            String sql = "SELECT * FROM Stock WHERE SerialNo LIKE ? OR ItemName LIKE ? OR Category LIKE ? OR Model LIKE? OR Brand LIKE ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + searchText + "%");
            pst.setString(2, "%" + searchText + "%");
            pst.setString(3, "%" + searchText + "%");
            pst.setString(4, "%" + searchText + "%");
            pst.setString(5, "%" + searchText + "%");
            rst = pst.executeQuery();

            while (rst.next()) {
                int ItemID = rst.getInt("ItemID");
                String serialNumber = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String modelValue = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int quantity = rst.getInt("Qty");

                // Map database column names to JTable column names
                model.addRow(new Object[]{ItemID, serialNumber, itemName, modelValue, specification, category, brand, quantity});
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Error searching items in the database.");
        } finally {
            try {
                if (rst != null) {
                    rst.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_txtSearchItemActionPerformed

    private void btnViewUser4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewUser4ActionPerformed
        // TODO add your handling code here:
        txtSearchStock.setText("");
        displayStockItems();
    }//GEN-LAST:event_btnViewUser4ActionPerformed

    private void btnDisposal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisposal1ActionPerformed
        // TODO add your handling code here:
        tabSuperadmin.setSelectedIndex(1);
        displayReleaseData();
    }//GEN-LAST:event_btnDisposal1ActionPerformed

    private void btnPrint_stockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrint_stockActionPerformed
        // TODO add your handling code here:
        printStock(tblStock);
    }//GEN-LAST:event_btnPrint_stockActionPerformed

    private void btnPrint_releaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrint_releaseActionPerformed
        // TODO add your handling code here:
        printRelease(tblRelease);
    }//GEN-LAST:event_btnPrint_releaseActionPerformed

    private void btnPrint_checkingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrint_checkingActionPerformed
        // TODO add your handling code here:
        printChecking(tblChecking);
    }//GEN-LAST:event_btnPrint_checkingActionPerformed

    private void btnPrint_returnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrint_returnActionPerformed
        // TODO add your handling code here:
        printReturn(tblReturn);
    }//GEN-LAST:event_btnPrint_returnActionPerformed

    private void btnPrint_repairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrint_repairActionPerformed
        // TODO add your handling code here:
        printRepair(tblRepair);
    }//GEN-LAST:event_btnPrint_repairActionPerformed

    private void btnPrint_disposalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrint_disposalActionPerformed
        // TODO add your handling code here:
        printDisposal(tblDisposal);
    }//GEN-LAST:event_btnPrint_disposalActionPerformed

    private void txtSearchReleaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchReleaseActionPerformed
        // TODO add your handling code here:
        String searchText = txtSearchRelease.getText().trim();
        DefaultTableModel releaseModel = (DefaultTableModel) tblRelease.getModel();
        releaseModel.setRowCount(0); // Clear existing rows

        try {
            String sql = "SELECT rel.ReleaseID, rel.ItemID, rel.Location, s.SerialNo, s.ItemName, s.Model, s.Specification, s.Category, s.Brand, rel.Qty AS ReleasedQty, rel.Date " +
                         "FROM Release rel " +
                         "LEFT JOIN Stock s ON rel.ItemID = s.ItemID " +
                         "WHERE rel.Location LIKE? OR s.ItemName LIKE ? OR s.SerialNo LIKE ? OR s.Model LIKE ? OR s.Category LIKE ? OR s.Brand LIKE ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + searchText + "%"); // Search Location
            pst.setString(2, "%" + searchText + "%"); // Search ItemName
            pst.setString(3, "%" + searchText + "%"); // Search SerialNo
            pst.setString(4, "%" + searchText + "%"); // Search Model
            pst.setString(5, "%" + searchText + "%"); // Search Category
            pst.setString(6, "%" + searchText + "%"); // Search Brand
            rst = pst.executeQuery();

            while (rst.next()) {
                int releaseID = rst.getInt("ReleaseID");
                int itemID = rst.getInt("ItemID");
                String location = rst.getString("Location");
                String serialNo = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String model = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int releasedQty = rst.getInt("ReleasedQty");
                Date date = rst.getDate("Date");

                // Add a row to the Release table with all the information
                releaseModel.addRow(new Object[]{releaseID, itemID, location, serialNo, itemName, model, specification, category, brand, releasedQty, date});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error retrieving items from the database.");
        } finally {
            try {
                if (rst != null) {
                    rst.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_txtSearchReleaseActionPerformed

    private void btnViewUser9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewUser9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnViewUser9ActionPerformed

    private void txtSearchCheckingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchCheckingActionPerformed
        // TODO add your handling code here:
        String searchText = txtSearchChecking.getText().trim();    
        DefaultTableModel checkingModel = (DefaultTableModel) tblChecking.getModel();
        checkingModel.setRowCount(0); // Clear existing rows

        try {
            String sql = "SELECT c.CheckingID, c.ItemID, s.SerialNo, s.ItemName, s.Model, s.Specification, s.Category, s.Brand, c.Qty AS CheckingQty, c.Date " +
                         "FROM Checking c " +
                         "LEFT JOIN Stock s ON c.ItemID = s.ItemID " +
                         "WHERE s.ItemName LIKE ? OR s.SerialNo LIKE ? OR s.Model LIKE ? OR s.Category LIKE ? OR s.Brand LIKE ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + searchText + "%"); // Search ItemName
            pst.setString(2, "%" + searchText + "%"); // Search SerialNo
            pst.setString(3, "%" + searchText + "%"); // Search Model
            pst.setString(4, "%" + searchText + "%"); // Search Category
            pst.setString(5, "%" + searchText + "%"); // Search Brand
            rst = pst.executeQuery();

            while (rst.next()) {
                int checkingID = rst.getInt("CheckingID");
                int itemID = rst.getInt("ItemID");
                String serialNo = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String model = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int checkingQty = rst.getInt("CheckingQty");
                Date date = rst.getDate("Date");

                // Add a row to the Release table with all the information
                checkingModel.addRow(new Object[]{checkingID, itemID, serialNo, itemName, model, specification, category, brand, checkingQty, date});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error retrieving items from the database.");
        } finally {
            try {
                if (rst != null) {
                    rst.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_txtSearchCheckingActionPerformed

    private void btnViewUser10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewUser10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnViewUser10ActionPerformed

    private void txtSearchReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchReturnActionPerformed
        // TODO add your handling code here:
        String searchText = txtSearchReturn.getText().trim();
        DefaultTableModel returnModel = (DefaultTableModel) tblReturn.getModel();
        returnModel.setRowCount(0); // Clear existing rows

        try {
            String sql = "SELECT r.ReturnID, r.ItemID, s.SerialNo, s.ItemName, s.Model, s.Specification, s.Category, s.Brand, r.Qty AS returnQty, r.Date " +
                         "FROM Return r " +
                         "LEFT JOIN Stock s ON r.ItemID = s.ItemID " +
                         "WHERE s.ItemName LIKE ? OR s.SerialNo LIKE ? OR s.Model LIKE ? OR s.Category LIKE ? OR s.Brand LIKE ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + searchText + "%"); // Search ItemName
            pst.setString(2, "%" + searchText + "%"); // Search SerialNo
            pst.setString(3, "%" + searchText + "%"); // Search Model
            pst.setString(4, "%" + searchText + "%"); // Search Category
            pst.setString(5, "%" + searchText + "%"); // Search Brand
            rst = pst.executeQuery();

            while (rst.next()) {
                int returnID = rst.getInt("ReturnID");
                int itemID = rst.getInt("ItemID");
                String serialNo = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String model = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int returnQty = rst.getInt("ReturnQty");
                Date date = rst.getDate("Date");

                // Add a row to the Release table with all the information
                returnModel.addRow(new Object[]{returnID, itemID, serialNo, itemName, model, specification, category, brand, returnQty, date});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error retrieving items from the database.");
        } finally {
            try {
                if (rst != null) {
                    rst.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_txtSearchReturnActionPerformed

    private void btnViewUser11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewUser11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnViewUser11ActionPerformed

    private void txtSearchRepairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchRepairActionPerformed
        // TODO add your handling code here:
        String searchText = txtSearchRepair.getText().trim();
        DefaultTableModel repairModel = (DefaultTableModel) tblRepair.getModel();
        repairModel.setRowCount(0); // Clear existing rows

        try {
            String sql = "SELECT rep.RepairID, rep.ItemID, s.SerialNo, s.ItemName, s.Model, s.Specification, s.Category, s.Brand, rep.Qty AS repairQty, rep.Date " +
                         "FROM Repair rep " +
                         "LEFT JOIN Stock s ON rep.ItemID = s.ItemID " +
                         "WHERE s.ItemName LIKE ? OR s.SerialNo LIKE ? OR s.Model LIKE ? OR s.Category LIKE ? OR s.Brand LIKE ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + searchText + "%"); // Search ItemName
            pst.setString(2, "%" + searchText + "%"); // Search SerialNo
            pst.setString(3, "%" + searchText + "%"); // Search Model
            pst.setString(4, "%" + searchText + "%"); // Search Category
            pst.setString(5, "%" + searchText + "%"); // Search Brand
            rst = pst.executeQuery();

            while (rst.next()) {
                int repairID = rst.getInt("RepairID");
                int itemID = rst.getInt("ItemID");
                String serialNo = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String model = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int repairQty = rst.getInt("RepairQty");
                Date date = rst.getDate("Date");

                // Add a row to the Release table with all the information
                repairModel.addRow(new Object[]{repairID, itemID, serialNo, itemName, model, specification, category, brand, repairQty, date});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error retrieving items from the database.");
        } finally {
            try {
                if (rst != null) {
                    rst.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_txtSearchRepairActionPerformed

    private void btnViewUser12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewUser12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnViewUser12ActionPerformed

    private void txtSearchDisposalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchDisposalActionPerformed
        // TODO add your handling code here:
        String searchText = txtSearchDisposal.getText().trim();
        DefaultTableModel disposalModel = (DefaultTableModel) tblDisposal.getModel();
        disposalModel.setRowCount(0); // Clear existing rows

        try {
            String sql = "SELECT d.DisposalID, d.ItemID, s.SerialNo, s.ItemName, s.Model, s.Specification, s.Category, s.Brand, d.Qty AS DisposalQty, d.Date " +
                         "FROM Disposal d " +
                         "LEFT JOIN Stock s ON d.ItemID = s.ItemID " +
                         "WHERE s.ItemName LIKE ? OR s.SerialNo LIKE ? OR s.Model LIKE ? OR s.Category LIKE ? OR s.Brand LIKE ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + searchText + "%"); // Search ItemName
            pst.setString(2, "%" + searchText + "%"); // Search SerialNo
            pst.setString(3, "%" + searchText + "%"); // Search Model
            pst.setString(4, "%" + searchText + "%"); // Search Category
            pst.setString(5, "%" + searchText + "%"); // Search Brand
            rst = pst.executeQuery();

            while (rst.next()) {
                int disposalID = rst.getInt("DisposalID");
                int itemID = rst.getInt("ItemID");
                String serialNo = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String model = rst.getString("Model");
                String specification = rst.getString("Specification");
                String category = rst.getString("Category");
                String brand = rst.getString("Brand");
                int disposalQty = rst.getInt("DisposalQty");
                Date date = rst.getDate("Date");

                // Add a row to the Release table with all the information
                disposalModel.addRow(new Object[]{disposalID, itemID, serialNo, itemName, model, specification, category, brand, disposalQty, date});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error retrieving items from the database.");
        } finally {
            try {
                if (rst != null) {
                    rst.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_txtSearchDisposalActionPerformed

    private void btnViewUser13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewUser13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnViewUser13ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(addmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new addmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnChecking;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnDisposal;
    private javax.swing.JButton btnDisposal1;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPrint_checking;
    private javax.swing.JButton btnPrint_disposal;
    private javax.swing.JButton btnPrint_release;
    private javax.swing.JButton btnPrint_repair;
    private javax.swing.JButton btnPrint_return;
    private javax.swing.JButton btnPrint_stock;
    private javax.swing.JButton btnRecords;
    private javax.swing.JButton btnRepair;
    private javax.swing.JButton btnReturn;
    private javax.swing.JButton btnStock;
    private javax.swing.JButton btnViewUser10;
    private javax.swing.JButton btnViewUser11;
    private javax.swing.JButton btnViewUser12;
    private javax.swing.JButton btnViewUser13;
    private javax.swing.JButton btnViewUser4;
    private javax.swing.JButton btnViewUser9;
    private javax.swing.JComboBox<String> cbSCategory;
    private javax.swing.JPanel dashboard;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lblChecking;
    private javax.swing.JLabel lblDisposal;
    private javax.swing.JLabel lblRelease;
    private javax.swing.JLabel lblRepair;
    private javax.swing.JLabel lblReturn;
    private javax.swing.JLabel lblTotalqty;
    private javax.swing.JTabbedPane tabPane;
    private javax.swing.JTabbedPane tabSuperadmin;
    private javax.swing.JTable tblChecking;
    private javax.swing.JTable tblDataInfo;
    private javax.swing.JTable tblDisposal;
    private javax.swing.JTable tblRelease;
    private javax.swing.JTable tblRepair;
    private javax.swing.JTable tblReturn;
    private javax.swing.JTable tblStock;
    private javax.swing.JTextField txtBrand;
    private javax.swing.JButton txtClear;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtModel;
    private javax.swing.JTextField txtQty;
    private javax.swing.JTextField txtSearchChecking;
    private javax.swing.JTextField txtSearchDisposal;
    private javax.swing.JTextField txtSearchItem;
    private javax.swing.JTextField txtSearchRelease;
    private javax.swing.JTextField txtSearchRepair;
    private javax.swing.JTextField txtSearchReturn;
    private javax.swing.JTextField txtSearchStock;
    private javax.swing.JTextField txtSerial;
    private javax.swing.JTextField txtSpecification;
    // End of variables declaration//GEN-END:variables
}
