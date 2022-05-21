////////////////////////////////////////////////////////////////////
// [Matteo] [Noro] [1229145]
// [Giovanni] [Cocco] [1223856]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

public class EItem {

    public enum item {Processor, Motherboard, Mouse, Keyboard};

    private item itemType;
    private String name;
    private double price;

    public EItem(item _itemType, String _name, double _price){
        this.itemType  = _itemType;
        this.name      = _name;
        if(_price >= 0.0D) {
            this.price = _price;
        }else {
            throw new IllegalArgumentException("Il prezzo deve essere >= 0");
        }
        
    }

    public item getItemType(){
        return itemType;
    }
    public String getItemName(){
        return name;
    }
    public double getItemPrice(){
        return price;
    }
    
}