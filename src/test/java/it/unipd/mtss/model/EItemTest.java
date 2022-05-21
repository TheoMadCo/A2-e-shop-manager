////////////////////////////////////////////////////////////////////
// [Matteo] [Noro] [1229145]
// [Giovanni] [Cocco] [1223856]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class EItemTest {
    
    @Test
    public void testGetItemType() {
    //Arrange
        EItem item = new EItem(EItem.item.Keyboard, "CorsairX42", 105);
    //Act
        EItem.item itemType = item.getItemType();
    //Assert
        assertEquals(EItem.item.Keyboard, itemType);
    }

    @Test
    public void testGetItemName() {
    //Arrange
        EItem item = new EItem(EItem.item.Keyboard, "CorsairX42", 105);
    //Act
        String itemName = item.getItemName();
    //Assert
        assertEquals("CorsairX42", itemName);
    }


    @Test
    public void testGetItemPrice() {
    //Arrange
        EItem item = new EItem(EItem.item.Keyboard, "CorsairX42", 105);
    //Act
        double itemPrice = item.getItemPrice();
    //Assert
        assertEquals(105, itemPrice, 0.0);
    }
    
    @Test
    public void testIllegalItemPrice() {
        try {
            new EItem(EItem.item.Keyboard, "CorsairX42", -1);
        } catch (IllegalArgumentException e) {
            assertEquals("Il prezzo deve essere >= 0", e.getMessage());
        }
    }

}
