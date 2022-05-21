////////////////////////////////////////////////////////////////////
// [Matteo] [Noro] [1229145]
// [Giovanni] [Cocco] [1223856]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
    
    //Assert
    @Test
    public void testGetName(){
    //Arrange
        User user = new User("Matteo", "Noro", LocalDate.of(2000, 2, 9), "mn@gmail.com" );
    //Act
        String userName = user.getName();
    //Assert
        assertEquals("Matteo", userName);
    }
   

    @Test
    public void testGetSurnaame(){
    //Arrange
        User user = new User("Matteo", "Noro", LocalDate.of(2000, 2, 9), "mn@gmail.com");
    //Act
        String userSurname = user.getSurname();
    //Assert
        assertEquals("Noro", userSurname);
    }

    @Test
    public void testGetEmail(){
    //Arrange
        User user = new User("Matteo", "Noro", LocalDate.of(2000, 2, 9), "mn@gmail.com");
    //Act
        String userEmail = user.getEmail();
    //Assert
        assertEquals("mn@gmail.com", userEmail);
    }

    @Test
    public void testGetDateOfBirth() {
    //Arrange
        User user = new User("Matteo", "Noro", LocalDate.of(2000, 2, 9), "mn@gmail.com");
    //Act
        LocalDate userDate = user.getDateOfBirth();
    //Assert
        assertEquals(LocalDate.of(2000, 2, 9), userDate);
    }
    
    @Test
    public void testIllegalDateOfBirth() {
        try {
            new User("Matteo", "Noro", LocalDate.now().plusDays(10), "mn@gmail.com");
        }catch (IllegalArgumentException e) {
            assertEquals("La data di nascita deve essere precedente alla data odierna", e.getMessage());
        }
}
    
    @Test
    public void testNullDateOfBirth() {
        try {
            new User("Matteo", "Noro", null, "mn@gmail.com" );
        }catch(IllegalArgumentException e) {
            assertEquals("La data di nascita è null", e.getMessage());
        }
        
    }

}
