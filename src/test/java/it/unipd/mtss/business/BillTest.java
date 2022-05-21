////////////////////////////////////////////////////////////////////
// [Matteo] [Noro] [1229145]
// [Giovanni] [Cocco] [1223856]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;

public class BillTest {
	
    @Test
    public void testItemSum() throws BillException {
    //Arrange
        BillImpl newBill = new BillImpl();
        User user = new User("Matteo", "Oron", LocalDate.of(2009, 9, 2), "mo@gmail.com");
        List<EItem> itemsOrdered = new ArrayList<EItem>(); 
        itemsOrdered.add(new EItem(EItem.item.Keyboard, "MXkeyboard", 105));
        itemsOrdered.add(new EItem(EItem.item.Mouse, "MXmaster", 60));
        itemsOrdered.add(new EItem(EItem.item.Mouse, "MXmaster", 60));
        itemsOrdered.add(new EItem(EItem.item.Motherboard, "amdx45", 268));
        itemsOrdered.add(new EItem(EItem.item.Processor, "corei7", 179));
    //Act
        double result = newBill.getOrderPrice(itemsOrdered, user, LocalTime.of(12, 30));
    //Assert
        assertEquals(672, result, 0.0);
    }
    
    @Test
    public void testNullItemsList() {
    //Arrange
        BillImpl newBill = new BillImpl();
        User user = new User("Matteo", "Oron", LocalDate.of(2009, 9, 2), "mo@gmail.com");
        List<EItem> itemsOrdered = new ArrayList<EItem>(); 
        itemsOrdered = null;
        try {
    //Act
            newBill.getOrderPrice(itemsOrdered, user, LocalTime.of(12, 30));
        }catch(BillException e) {
    //Assert
            assertEquals("La lista itemsOrdered è uguale a null", e.getMessage());
        }
    }

    @Test
    public void testNullItemInItemsList() {
    //Arrange 
        BillImpl newBill = new BillImpl();
    	User user = new User("Matteo", "Oron", LocalDate.of(2009, 9, 2), "mo@gmail.com");
    	List<EItem> itemsOrdered = new ArrayList<EItem>(); 
        itemsOrdered.add(new EItem(EItem.item.Keyboard, "MXkeyboard", 105));
        itemsOrdered.add(null);
        itemsOrdered.add(new EItem(EItem.item.Motherboard, "amdx45", 268));
        try {
    //Act
            newBill.getOrderPrice(itemsOrdered, user, LocalTime.of(12, 30));
        }catch(BillException e){
    //Assert
            assertEquals("La lista itemsOrdered contiene un item uguale a null", e.getMessage());
        }
    }
    
    @Test
    public void testNullUser() {
    //Arrange
        BillImpl newBill = new BillImpl();
        User user = null;
        List<EItem> itemsOrdered = new ArrayList<EItem>(); 
        itemsOrdered.add(new EItem(EItem.item.Keyboard, "MXkeyboard", 105));
        try {
    //Act
            newBill.getOrderPrice(itemsOrdered, user, LocalTime.of(12, 30));
        }catch(BillException e){
    //Assert
            assertEquals("utente è uguale a null", e.getMessage());
        }
    }

    @Test
    public void testMoreThan5Processori() throws BillException {
    //Arrange
        BillImpl newBill = new BillImpl();
        User user = new User("Matteo", "Oron", LocalDate.of(2000, 9, 2), "mo@gmail.com");
        List<EItem> itemsOrdered = new ArrayList<EItem>(); 
        
        itemsOrdered.add(new EItem(EItem.item.Keyboard, "myFirstMouse", 10));
        itemsOrdered.add(new EItem(EItem.item.Processor, "corePentino", 40));
        itemsOrdered.add(new EItem(EItem.item.Processor, "corePentino2", 80));
        itemsOrdered.add(new EItem(EItem.item.Processor, "corePentino2s", 90));
        itemsOrdered.add(new EItem(EItem.item.Processor, "coreiPentino3", 110));
        itemsOrdered.add(new EItem(EItem.item.Processor, "coreiPentinoLight", 50));
        itemsOrdered.add(new EItem(EItem.item.Processor, "corePentino6", 100));
    
    //Act
        double result = newBill.getOrderPrice(itemsOrdered, user, LocalTime.of(12, 30));
    //Assert 480 - 40/2 = 460
        assertEquals(460, result, 0.0);
    }

    @Test
    public void testMoreThan10Mouse() throws BillException {
    //Arrange
        BillImpl newBill = new BillImpl();
    	User user = new User("Matteo", "Oron", LocalDate.of(2009, 9, 2), "mo@gmail.com");
    	List<EItem> itemsOrdered = new ArrayList<EItem>(); 
        itemsOrdered.add(new EItem(EItem.item.Mouse, "MXmaster1", 60));
        itemsOrdered.add(new EItem(EItem.item.Mouse, "MXmaster2", 78));
        itemsOrdered.add(new EItem(EItem.item.Mouse, "MXmaster3", 62));
        itemsOrdered.add(new EItem(EItem.item.Mouse, "MXmaster4", 59));
        itemsOrdered.add(new EItem(EItem.item.Mouse, "MXmaster1S", 45));
        itemsOrdered.add(new EItem(EItem.item.Mouse, "CorsairM1", 80));
        itemsOrdered.add(new EItem(EItem.item.Mouse, "CorsairM2", 90));
        itemsOrdered.add(new EItem(EItem.item.Mouse, "CorsairM3", 85));
        itemsOrdered.add(new EItem(EItem.item.Mouse, "CorsairM4", 87));
        itemsOrdered.add(new EItem(EItem.item.Mouse, "CorsairM4", 87));
        itemsOrdered.add(new EItem(EItem.item.Mouse, "Logi1", 60));
    //Act
        double result = newBill.getOrderPrice(itemsOrdered, user, LocalTime.of(12, 30));
    //Assert 793 - 45 = 748
        assertEquals(748, result, 0.0);
    }

    @Test
    public void testSameMouseAsKeyboard() throws BillException {
    //Arrange
        BillImpl newBill = new BillImpl();
    	User user = new User("Matteo", "Oron", LocalDate.of(2009, 9, 2), "mo@gmail.com");
    	List<EItem> itemsOrdered = new ArrayList<EItem>(); 
        itemsOrdered.add(new EItem(EItem.item.Keyboard, "MXkeyboard", 105));
        itemsOrdered.add(new EItem(EItem.item.Keyboard, "MXKeyboard2", 110));
        itemsOrdered.add(new EItem(EItem.item.Mouse, "MXmaster1", 60));
        itemsOrdered.add(new EItem(EItem.item.Mouse, "MXMaster2", 70));
        itemsOrdered.add(new EItem(EItem.item.Processor, "corei7", 179));
    //Act
        double result = newBill.getOrderPrice(itemsOrdered, user, LocalTime.of(12, 30));
    //Assert 105 + 110 + 60 + 70 + 179 - 60 = 464
        assertEquals(464, result, 0.0);
    }

    @Test
    public void testMoreThan1000EuroDiscount() throws BillException {
    //Arrange
        BillImpl newBill = new BillImpl();
    	User user = new User("Matteo", "Oron", LocalDate.of(2009, 9, 2), "mo@gmail.com");
        List<EItem> itemsOrdered = new ArrayList<EItem>();
        itemsOrdered.add(new EItem(EItem.item.Keyboard, "MXkeyboard", 105));
        itemsOrdered.add(new EItem(EItem.item.Motherboard, "AMDMS1", 250));
        itemsOrdered.add(new EItem(EItem.item.Processor, "XEON10", 1000));
    //Act
        double result = newBill.getOrderPrice(itemsOrdered, user, LocalTime.of(12, 30));
    //Assert 1355 - 135,5 = 1219.5
        assertEquals(1219.5, result, 0.0);

    }
        
}
    

