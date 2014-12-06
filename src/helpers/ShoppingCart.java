package helpers;

import java.io.Serializable;
import java.util.List;

// TODO implement all unimplemented methods of this class
public class ShoppingCart implements Serializable{

	private static final long serialVersionUID = 8012789291408030267L;

	private List<Item> items;
	
	public ShoppingCart(){
		// TODO initialize items as an ArrayList
	}
	
	public void addItem(Item item){
		// TODO add item to items list
	}
	
	public void removeItem(Item item){
		// TODO remove item from items list
	}
	
	public Item[] getItems(){
		// TODO convert items list to array of Item elements and return it
		return null;
	}
	
	public void clear(){
		// TODO clear all elements of items list
	}
}
