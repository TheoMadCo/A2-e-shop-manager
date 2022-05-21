////////////////////////////////////////////////////////////////////
// [Matteo] [Noro] [1229145]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.io.Console;
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

    

        
}
    

