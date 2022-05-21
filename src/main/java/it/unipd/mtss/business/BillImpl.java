////////////////////////////////////////////////////////////////////
// [Matteo] [Noro] [1229145]
// [Giovanni] [Cocco] [1223856]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import java.time.LocalTime;
import java.util.List;

import it.unipd.mtss.model.User;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.business.exception.BillException;

public class BillImpl implements Bill{


    public double getOrderPrice(List<EItem> itemsOrdered, User user, LocalTime orderTime) 
            throws BillException{
        double total = 0.0D;
        int countMouse = 0;
        int countProcessori = 0;
        double cheapestMouse = Double.MAX_VALUE;
        double cheapestProcessore = Double.MAX_VALUE;
        
        if(itemsOrdered == null) {
            throw new BillException("La lista itemsOrdered è uguale a null");
        }
        if(itemsOrdered.contains(null)) {
            throw new BillException("La lista itemsOrdered contiene un item uguale a null");
        }
        if(user == null) {
            throw new BillException("utente è uguale a null");
        }
        
        
        for(EItem item: itemsOrdered) {
            if(item.getItemType() == EItem.item.Mouse) {
                countMouse = countMouse + 1;
                if(item.getItemPrice() < cheapestMouse) {
                    cheapestMouse = item.getItemPrice();
                }
            }
            if (item.getItemType() == EItem.item.Processor) {
                countProcessori = countProcessori + 1;
                if(item.getItemPrice() < cheapestProcessore) {
                    cheapestProcessore = item.getItemPrice();
                }
            }

            total = total + item.getItemPrice();
        }

        if(countProcessori > 5){
            total = total - (cheapestProcessore / 2);
        }
        if(countMouse > 10) {
            total = total - (cheapestMouse);
        }
        
        return total;
    }
}


