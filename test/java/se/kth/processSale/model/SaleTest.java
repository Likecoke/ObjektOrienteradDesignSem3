package se.kth.processSale.model;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.kth.processSale.integration.ItemDTO;
import se.kth.processSale.util.Node;

import static org.junit.Assert.assertEquals;

/** An object representing a sale   */
public class SaleTest {
    Sale newSale;
    //SaleDTO newSaleDTO;

    @Before
    public void  setUp(){
        newSale = new Sale();
        //newSaleDTO =  newSale.saleStatus();

    }

    @After
    public void tearDown(){
        newSale = null;
        //newSaleDTO = null;

    }

    @Test
    public void testNewSaleItemListEqualsNull(){
       SaleDTO newSaleDTO =  newSale.saleStatus();
       boolean exceptedResult = true;
       boolean result = newSaleDTO.getItemList() == null;

       assertEquals("New Sale itemList not equal to null", exceptedResult, result);



    }
    @Test
    public void testNewSaleItemRunningTotalEquals0() {
        SaleDTO newSaleDTO =  newSale.saleStatus();
        boolean result  = newSaleDTO.getRunningTotal() == 0;
        boolean expectedResult = true;
        assertEquals("New Sale runningTotal not equal to 0", expectedResult, result );

    }
    @Test
    public void ItemAddedToSale(){
        ItemDTO itemToBeAdded = new ItemDTO("abc",200, "mjölk");
        newSale.addItem(itemToBeAdded);

        Node<ItemDTO> saleList = newSale.saleStatus().getItemList();
        boolean result = saleList.item.getIdentifier().equals(itemToBeAdded.getIdentifier());
        boolean expectedResult = true;
        assertEquals("Item wasn't added to sale", result, expectedResult);

    }
    @Test
    public void saleStatusReturnsCorrectRunningTotal(){
        ItemDTO firstItem = new ItemDTO("abc",120, "mjölk");
        ItemDTO secondItem = new ItemDTO("abcd",240, "kakor");
        newSale.addItem(firstItem);
        newSale.addItem(secondItem);
        SaleDTO saleDTOAfterAddingItems = newSale.saleStatus();
        boolean result = saleDTOAfterAddingItems.getRunningTotal() == (firstItem.getPrice() + secondItem.getPrice());
        boolean expectedResult = true;
        assertEquals("RunningTotal doesn't match price of items added", result, expectedResult);

    }
    @Test
    public void saleStatusReturnsCorrectItemList(){
        ItemDTO firstItem = new ItemDTO("abc",120, "mjölk");
        ItemDTO secondItem = new ItemDTO("abcd",240, "kakor");
        newSale.addItem(firstItem);
        newSale.addItem(secondItem);
        SaleDTO saleDTOAfterAddingItems = newSale.saleStatus();
       Node<ItemDTO> itemsFromSale = saleDTOAfterAddingItems.getItemList();
       boolean result = itemsFromSale.item.equals(secondItem) && itemsFromSale.next.item.equals(firstItem);
       boolean expectedResult = true;
       assertEquals("The items weren't added to to the itemList correctly ", result, expectedResult);

    }







}
