////////////////////////////////////////////////////////////////////
// [Matteo] [Noro] [1229145]
// [Giovanni] [Cocco] [1223856]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import it.unipd.mtss.model.User;

public class BillGiveawayTest {
    
    @Test
    public void testCorrectEligibility() {
    //Arrange    
        User user = new User("Matteo", "Oron", LocalDate.of(2010, 1, 1), "mo@gmail.com");
        BillGiveawayImpl giveaway = new BillGiveawayImpl();
    //Act
        LocalTime orderTime = LocalTime.of(18, 30);
        boolean result = giveaway.isOrderEligible(user, orderTime);
    //Assert
        assertEquals(true, result);
    }
    
    @Test
    public void testUserOver18() {
    //Arrange    
        User user = new User("Matteo", "Rono", LocalDate.of(1980, 1, 1), "mr@gmail.com");
        BillGiveawayImpl giveaway = new BillGiveawayImpl();
    //Act
        LocalTime orderTime = LocalTime.of(18, 30);
        boolean result = giveaway.isOrderEligible(user, orderTime);
    //Assert
        assertEquals(false, result);
    }
    
    @Test
    public void testWrongTime() {
    //Arrange    
        User user = new User("Matteo", "Rono", LocalDate.of(1980, 1, 1), "mr@gmail.com");
        BillGiveawayImpl giveaway = new BillGiveawayImpl();
    //Act
        LocalTime orderTime = LocalTime.of(11, 30);
        boolean result = giveaway.isOrderEligible(user, orderTime);
    //Assert
        assertEquals(false, result);
    }
    
    @Test
    public void testAlreadySelectedUser() {
    //Arrange    
        User user = new User("Matteo", "Rono", LocalDate.of(1980, 1, 1), "mr@gmail.com");
        BillGiveawayImpl giveaway = new BillGiveawayImpl();
    //Act
        giveaway.selectedUsers.add(user);
        LocalTime orderTime = LocalTime.of(18, 30);
        boolean result = giveaway.isOrderEligible(user, orderTime);
    //Assert
        assertEquals(false, result);
    }
    
    @Test
    public void testMoreThan10SelectedUsers() {
    //Arrange
        BillGiveawayImpl giveaway = new BillGiveawayImpl();
        //Permette creazione di persone diverse in data di
        for(int i=1; i<10; i++) { 
            User user = new User("Matteo", "Cognome", LocalDate.of(2011, 1, i), "mo@gmail.com");
            giveaway.selectedUsers.add(user);
        }
        User user = new User("Matteo", "Rono", LocalDate.of(1980, 2, 2), "mr@gmail.com");
        
    //Act
        LocalTime orderTime = LocalTime.of(18, 30);
        boolean result = giveaway.isOrderEligible(user, orderTime);
    //Assert
        assertEquals(false, result);

    }
    
    @Test
    public void testNullUser() {
    //Arrange    
        User user = null;
        BillGiveawayImpl giveaway = new BillGiveawayImpl();
    //Act
        LocalTime orderTime = LocalTime.of(18, 30);
    //Assert
        try {
            giveaway.giveAwayOrder(user, orderTime);
        }catch(IllegalArgumentException e) {
            assertEquals("user è null", e.getMessage());
        }
    }
    
    @Test
    public void testNullOrderTime() {
    //Arrange    
        User user = new User("Matteo", "Rono", LocalDate.of(1980, 2, 2), "mr@gmail.com");
        BillGiveawayImpl giveaway = new BillGiveawayImpl();
     //Act
        LocalTime orderTime = null;
     //Assert
         try {
             giveaway.giveAwayOrder(user, orderTime);
         }catch(IllegalArgumentException e) {
             assertEquals("orderTime è null", e.getMessage());
         }
    }
}
