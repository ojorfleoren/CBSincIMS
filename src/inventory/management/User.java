package inventory.management;

import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
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
public class User extends javax.swing.JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rst = null;

    public User() {
        initComponents();
        conn = LogIn.connectDB();
        setExtendedState(User.MAXIMIZED_BOTH);
        
        // Call the method and display the count of each area
        displayTotalDataCount();
        displayReleaseCount();
        displayCheckingCount();
        displayRepairCount();
        displayReturnCount();
        displayDisposalCount();
        alignStock(tblStock);
        alignRelease(tblRelease);
        alignChecking(tblChecking);
        alignReturn(tblReturn);
        alignRepair(tblRepair);
        alignDisposal(tblDisposal);
        //Alignment
 
        //End----------------------------------------------------------------End
    }
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
                                              SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER};
    private static final int[] releaseWidth = {60, 15, 80, 130, 100, 170, 60, 90, 15, 90};

    public static void alignStock(JTable tblStock) {
        DefaultTableCellRenderer renderer;
        JTableHeader header = tblStock.getTableHeader();
        TableCellRenderer headerRenderer = header.getDefaultRenderer();

        // Set alignment for header
        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        
        // Set font and bold for header
        java.awt.Font headerFont = header.getFont();
        header.setFont(headerFont.deriveFont(java.awt.Font.BOLD, 15));

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
        java.awt.Font headerFont = header.getFont();
        header.setFont(headerFont.deriveFont(java.awt.Font.BOLD, 15)); // Set font size to 18

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
        java.awt.Font headerFont = header.getFont();
        header.setFont(headerFont.deriveFont(java.awt.Font.BOLD, 15)); // Set font size to 18

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
        java.awt.Font headerFont = header.getFont();
        header.setFont(headerFont.deriveFont(java.awt.Font.BOLD, 15)); // Set font size to 18

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
        java.awt.Font headerFont = header.getFont();
        header.setFont(headerFont.deriveFont(java.awt.Font.BOLD, 15)); // Set font size to 18

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
        java.awt.Font headerFont = header.getFont();
        header.setFont(headerFont.deriveFont(java.awt.Font.BOLD, 15)); // Set font size to 18

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
//Functionalities Method    
//Display the quantity of each area into the dashboard    
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
//End------------------------------------------------------------------------End
//Display the information of each area in the table
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
        String sql = "SELECT rep.RepairID, rep.ItemID, s.SerialNo, s.ItemName, s.Model, s.Specification, s.Category, s.Brand, rep.Qty AS RepairedQty, rep.Date " +
                     "FROM Repair rep " +
                     "LEFT JOIN Stock s ON rep.ItemID = s.ItemID ";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {

            while (rst.next()) {
                int repairID = rst.getInt("RepairID");
                int itemID = rst.getInt("ItemID");
                String serialNo = rst.getString("SerialNo");
                String itemName = rst.getString("ItemName");
                String model = rst.getString("Model");
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
//End------------------------------------------------------------------------End

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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tabUser = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        lblTotalqty = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        lblChecking = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        lblRepair = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        lblReturn = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        lblDisposal = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        lblRelease = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        tabstocksUser = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStock = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        txtSearchItem = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        btnViewUser4 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblRelease = new javax.swing.JTable();
        jButton15 = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        txtSearchRelease = new javax.swing.JTextField();
        btnViewUser13 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblChecking = new javax.swing.JTable();
        jButton16 = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        txtSearchChecking = new javax.swing.JTextField();
        btnViewUser14 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblReturn = new javax.swing.JTable();
        jButton17 = new javax.swing.JButton();
        jLabel46 = new javax.swing.JLabel();
        btnViewUser15 = new javax.swing.JButton();
        txtSearchReturn = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblRepair = new javax.swing.JTable();
        jButton18 = new javax.swing.JButton();
        jLabel48 = new javax.swing.JLabel();
        txtSearchRepair = new javax.swing.JTextField();
        btnViewUser16 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblDisposal = new javax.swing.JTable();
        jButton19 = new javax.swing.JButton();
        jLabel49 = new javax.swing.JLabel();
        txtSearchDisposal = new javax.swing.JTextField();
        btnViewUser17 = new javax.swing.JButton();
        btnStock = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Rockwell", 1, 32)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/80x70 dashboard.png"))); // NOI18N
        jButton1.setText(" Dashboard");
        jButton1.setPreferredSize(new java.awt.Dimension(302, 77));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 300, 80));

        jButton2.setFont(new java.awt.Font("Rockwell", 1, 32)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/80x70 records.png"))); // NOI18N
        jButton2.setText(" Records");
        jButton2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 300, 80));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/logos_small_67RB7KTLSNXPWKW6NAG7-56a62ad1-removebg-preview (1) (3).png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        jPanel11.setBackground(new java.awt.Color(255, 232, 163));
        jPanel11.setPreferredSize(new java.awt.Dimension(231, 156));

        lblTotalqty.setFont(new java.awt.Font("Rockwell", 1, 50)); // NOI18N
        lblTotalqty.setText("0");

        jLabel28.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Total");

        jLabel29.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Stock Items");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(146, 146, 146))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(88, 88, 88))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(lblTotalqty, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(lblTotalqty, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel29)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(211, 109, 109));
        jPanel12.setPreferredSize(new java.awt.Dimension(241, 104));

        lblChecking.setFont(new java.awt.Font("Rockwell", 1, 50)); // NOI18N
        lblChecking.setText("0");

        jLabel31.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel31.setText("Checking Items");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Checking.png"))); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addComponent(lblChecking, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel31)
                .addGap(32, 32, 32))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(lblChecking))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel31)
                .addGap(28, 28, 28))
        );

        jPanel14.setBackground(new java.awt.Color(106, 177, 135));
        jPanel14.setPreferredSize(new java.awt.Dimension(241, 104));

        lblRepair.setFont(new java.awt.Font("Rockwell", 1, 50)); // NOI18N
        lblRepair.setText("0");

        jLabel33.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel33.setText("Repair Items");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Repair.png"))); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblRepair, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel33)
                .addGap(50, 50, 50))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblRepair)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel33)
                .addGap(36, 36, 36))
        );

        jPanel21.setBackground(new java.awt.Color(102, 102, 154));
        jPanel21.setPreferredSize(new java.awt.Dimension(241, 104));

        lblReturn.setFont(new java.awt.Font("Rockwell", 1, 50)); // NOI18N
        lblReturn.setText("0");

        jLabel37.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel37.setText("Return Items");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Return.png"))); // NOI18N

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel37)
                .addGap(59, 59, 59))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addComponent(lblReturn)
                        .addGap(13, 13, 13)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel37)
                .addGap(36, 36, 36))
        );

        jPanel20.setBackground(new java.awt.Color(190, 159, 191));
        jPanel20.setPreferredSize(new java.awt.Dimension(241, 104));

        lblDisposal.setFont(new java.awt.Font("Rockwell", 1, 50)); // NOI18N
        lblDisposal.setText("0");

        jLabel39.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel39.setText("Disposal Items");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Disposal.png"))); // NOI18N

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(lblDisposal, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel39)
                .addGap(42, 42, 42))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(lblDisposal))
                .addGap(31, 31, 31)
                .addComponent(jLabel39)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jLabel47.setFont(new java.awt.Font("Rockwell", 1, 55)); // NOI18N
        jLabel47.setText("DASHBOARD");

        jPanel28.setBackground(new java.awt.Color(251, 202, 126));

        lblRelease.setFont(new java.awt.Font("Rockwell", 1, 50)); // NOI18N
        lblRelease.setText("0");

        jLabel38.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel38.setText("Release Items");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Release.png"))); // NOI18N

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addComponent(lblRelease, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel38)
                .addGap(51, 51, 51))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(57, 57, 57))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                        .addComponent(lblRelease)
                        .addGap(41, 41, 41)))
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1506, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel47)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                            .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel47)
                .addGap(60, 60, 60)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                        .addComponent(jPanel28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(88, Short.MAX_VALUE))
        );

        tabUser.addTab("tab1", jPanel3);

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1508, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 109, Short.MAX_VALUE)
        );

        tabstocksUser.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        tblStock.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tblStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "SerialNo", "Name ", "Model", "Specification", "Category", "Brand", "Qty"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
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
        jScrollPane1.setViewportView(tblStock);
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

        jLabel25.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jLabel25.setText("Search:");

        txtSearchItem.setFont(new java.awt.Font("Rockwell", 0, 20)); // NOI18N
        txtSearchItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchItemActionPerformed(evt);
            }
        });

        jButton14.setBackground(new java.awt.Color(93, 190, 163));
        jButton14.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jButton14.setText("Print");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        btnViewUser4.setBackground(new java.awt.Color(93, 190, 163));
        btnViewUser4.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        btnViewUser4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Refresh .png"))); // NOI18N
        btnViewUser4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewUser4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1427, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel25)
                        .addGap(1, 1, 1)
                        .addComponent(txtSearchItem, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(btnViewUser4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnViewUser4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearchItem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))))
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 709, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabstocksUser.addTab("tab1", jPanel6);

        tblRelease.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tblRelease.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

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

        jButton15.setBackground(new java.awt.Color(93, 190, 163));
        jButton15.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jButton15.setText("Print");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jLabel44.setText("Search:");

        txtSearchRelease.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        txtSearchRelease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchReleaseActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jButton15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1086, Short.MAX_VALUE)
                .addComponent(jLabel44)
                .addGap(3, 3, 3)
                .addComponent(txtSearchRelease, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(btnViewUser13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 1425, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnViewUser13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel44, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSearchRelease, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(52, Short.MAX_VALUE)))
        );

        tabstocksUser.addTab("tab6", jPanel13);

        tblChecking.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tblChecking.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "CheckingID", "ItemID", "Serial", "ItemName", "Model", "Specifiation", "Category", "Brand", "Qty.", "CheckingDate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
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
        jScrollPane2.setViewportView(tblChecking);
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

        jButton16.setBackground(new java.awt.Color(93, 190, 163));
        jButton16.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jButton16.setText("Print");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jLabel45.setText("Search:");

        txtSearchChecking.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        txtSearchChecking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchCheckingActionPerformed(evt);
            }
        });

        btnViewUser14.setBackground(new java.awt.Color(93, 190, 163));
        btnViewUser14.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        btnViewUser14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Refresh .png"))); // NOI18N
        btnViewUser14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewUser14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jButton16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel45)
                        .addGap(3, 3, 3)
                        .addComponent(txtSearchChecking, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(btnViewUser14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1423, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnViewUser14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSearchChecking, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 709, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        tabstocksUser.addTab("tab2", jPanel7);

        tblReturn.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tblReturn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ReturnID", "ItemID", "Serial", "ItemName", "Model", "Specification", "Category", "Brand", "Qty.", "ReturnDate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
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
        jScrollPane3.setViewportView(tblReturn);
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

        jButton17.setBackground(new java.awt.Color(93, 190, 163));
        jButton17.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jButton17.setText("Print");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jLabel46.setText("Search:");

        btnViewUser15.setBackground(new java.awt.Color(93, 190, 163));
        btnViewUser15.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        btnViewUser15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Refresh .png"))); // NOI18N
        btnViewUser15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewUser15ActionPerformed(evt);
            }
        });

        txtSearchReturn.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        txtSearchReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchReturnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1429, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jButton17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel46)
                        .addGap(3, 3, 3)
                        .addComponent(txtSearchReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(btnViewUser15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnViewUser15, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel46, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSearchReturn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        tabstocksUser.addTab("tab3", jPanel8);

        tblRepair.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tblRepair.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "RepairID", "ItemID", "Serial", "ItemName", "Model", "Specification", "Category", "Brand", "Qty.", "RepairDate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
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
        jScrollPane4.setViewportView(tblRepair);
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

        jButton18.setBackground(new java.awt.Color(93, 190, 163));
        jButton18.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jButton18.setText("Print");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jLabel48.setText("Search:");

        txtSearchRepair.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        txtSearchRepair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchRepairActionPerformed(evt);
            }
        });

        btnViewUser16.setBackground(new java.awt.Color(93, 190, 163));
        btnViewUser16.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        btnViewUser16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Refresh .png"))); // NOI18N
        btnViewUser16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewUser16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1422, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jButton18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel48)
                        .addGap(3, 3, 3)
                        .addComponent(txtSearchRepair, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(btnViewUser16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnViewUser16, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSearchRepair, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        tabstocksUser.addTab("tab4", jPanel9);

        tblDisposal.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tblDisposal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "DisposalID", "ItemID  ", "Serial", "ItemName", "Model", "Specification", "Category", "Brand", "Qty.", "DisposalDate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
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
        jScrollPane5.setViewportView(tblDisposal);
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

        jButton19.setBackground(new java.awt.Color(93, 190, 163));
        jButton19.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jButton19.setText("Print");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jLabel49.setText("Search:");

        txtSearchDisposal.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        txtSearchDisposal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchDisposalActionPerformed(evt);
            }
        });

        btnViewUser17.setBackground(new java.awt.Color(93, 190, 163));
        btnViewUser17.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        btnViewUser17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Refresh .png"))); // NOI18N
        btnViewUser17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewUser17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jButton19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel49)
                        .addGap(3, 3, 3)
                        .addComponent(txtSearchDisposal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(btnViewUser17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1423, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnViewUser17, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSearchDisposal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabstocksUser.addTab("tab5", jPanel10);

        btnStock.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        btnStock.setText("Stock");
        btnStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStockActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jButton9.setText("Checking");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jButton10.setText("Return");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jButton11.setText("Repair");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jButton12.setText("Disposal");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setFont(new java.awt.Font("Rockwell", 1, 20)); // NOI18N
        jButton13.setText("Release");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabstocksUser, javax.swing.GroupLayout.PREFERRED_SIZE, 1449, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnStock)
                .addGap(2, 2, 2)
                .addComponent(jButton13)
                .addGap(2, 2, 2)
                .addComponent(jButton9)
                .addGap(2, 2, 2)
                .addComponent(jButton10)
                .addGap(2, 2, 2)
                .addComponent(jButton11)
                .addGap(2, 2, 2)
                .addComponent(jButton12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton12)
                            .addComponent(jButton11)))
                    .addComponent(btnStock))
                .addGap(1, 1, 1)
                .addComponent(tabstocksUser, javax.swing.GroupLayout.PREFERRED_SIZE, 832, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabUser.addTab("tab2", jPanel2);

        jPanel1.add(tabUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, -30, 1480, 970));

        jButton3.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jButton3.setText("LOG OUT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 840, 220, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1927, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1018, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        tabUser.setSelectedIndex(0);
        displayTotalDataCount();
        displayReleaseCount();
        displayCheckingCount();
        displayRepairCount();
        displayReturnCount();
        displayDisposalCount();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtSearchItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchItemActionPerformed
        // TODO add your handling code here:
        String searchText = txtSearchItem.getText().trim();

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
    }//GEN-LAST:event_txtSearchItemActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {

            JOptionPane.showMessageDialog(null, "Logout Successful");
             new LogIn().setVisible(true);
            dispose();
        } else {

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        tabUser.setSelectedIndex(1);
        displayStockItems();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockActionPerformed
        // TODO add your handling code here:
        tabstocksUser.setSelectedIndex(0);
        displayStockItems();
    }//GEN-LAST:event_btnStockActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        tabstocksUser.setSelectedIndex(2);
        displayCheckingData();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        tabstocksUser.setSelectedIndex(3);
        displayReturn();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        tabstocksUser.setSelectedIndex(4);
        displayRepair();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        tabstocksUser.setSelectedIndex(5);
        displayDisposal();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void btnViewUser4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewUser4ActionPerformed
        // TODO add your handling code here:
        txtSearchItem.setText("");
        displayStockItems();
    }//GEN-LAST:event_btnViewUser4ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        tabstocksUser.setSelectedIndex(1);
        displayReleaseData();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        printStock(tblStock);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        printRelease(tblRelease);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        printChecking(tblChecking);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        printReturn(tblReturn);
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        printRepair(tblRepair);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        printDisposal(tblDisposal);
    }//GEN-LAST:event_jButton19ActionPerformed

    private void txtSearchReleaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchReleaseActionPerformed
        // TODO add your handling code here:
        String searchText = txtSearchRelease.getText().trim();
        DefaultTableModel releaseModel = (DefaultTableModel) tblRelease.getModel();
        releaseModel.setRowCount(0); // Clear existing rows

        try {
            String sql = "SELECT rel.ReleaseID, rel.ItemID, rel.Location, s.SerialNo, s.ItemName, s.Model, s.Specification, s.Category, s.Brand, rel.Qty AS ReleasedQty, rel.Date " +
                         "FROM Release rel " +
                         "LEFT JOIN Stock s ON rel.ItemID = s.ItemID " +
                         "WHERE rel.Location LIKE? s.ItemName LIKE ? OR s.SerialNo LIKE ? OR s.Model LIKE ? OR s.Category LIKE ? OR s.Brand LIKE ?";
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

    private void btnViewUser13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewUser13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnViewUser13ActionPerformed

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

    private void btnViewUser14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewUser14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnViewUser14ActionPerformed

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

    private void btnViewUser15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewUser15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnViewUser15ActionPerformed

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

    private void btnViewUser16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewUser16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnViewUser16ActionPerformed

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

    private void btnViewUser17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewUser17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnViewUser17ActionPerformed

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
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new User().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStock;
    private javax.swing.JButton btnViewUser13;
    private javax.swing.JButton btnViewUser14;
    private javax.swing.JButton btnViewUser15;
    private javax.swing.JButton btnViewUser16;
    private javax.swing.JButton btnViewUser17;
    private javax.swing.JButton btnViewUser4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
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
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel lblChecking;
    private javax.swing.JLabel lblDisposal;
    private javax.swing.JLabel lblRelease;
    private javax.swing.JLabel lblRepair;
    private javax.swing.JLabel lblReturn;
    private javax.swing.JLabel lblTotalqty;
    private javax.swing.JTabbedPane tabUser;
    private javax.swing.JTabbedPane tabstocksUser;
    private javax.swing.JTable tblChecking;
    private javax.swing.JTable tblDisposal;
    private javax.swing.JTable tblRelease;
    private javax.swing.JTable tblRepair;
    private javax.swing.JTable tblReturn;
    private javax.swing.JTable tblStock;
    private javax.swing.JTextField txtSearchChecking;
    private javax.swing.JTextField txtSearchDisposal;
    private javax.swing.JTextField txtSearchItem;
    private javax.swing.JTextField txtSearchRelease;
    private javax.swing.JTextField txtSearchRepair;
    private javax.swing.JTextField txtSearchReturn;
    // End of variables declaration//GEN-END:variables
}
