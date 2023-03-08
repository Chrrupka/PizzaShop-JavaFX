package org.example;
/**
 * Klasa opsujaca produkt
 */
public class Products {
    private String type;
    private String size;
    private int price;
    private int quantity;
    private int sumprice;
    private int totalsuma;

    /**
     * Konstruktor klasy Product z 6 parametrami
     * @param type - rodzaj pizzy
     * @param size - rozmiar pizzy
     * @param price - cena pizzy
     * @param quantity ilość
     * @param sumprice łączna kwota za dany rodzaj pizzy w zależności od sztuk
     * @param totalsuma łączna suma zamówień
     */
    public Products(String type, String size, int price, int quantity, int sumprice, int totalsuma) {
        this.type = type;
        this.size = size;
        this.price = price;
        this.quantity = quantity;
        this.sumprice = sumprice;
        this.totalsuma = totalsuma;
    }

    /**
     * Konstruktor bezargumentowy
     */
    public Products() {}

    /**
     * Uzyskaj totalną kwotę
     * @return totalsuma
     */
    public int getTotalsuma() {
        return totalsuma;
    }

    /**
     * Ustaw wartość totalsuma
     * @param totalsuma
     */
    public void setTotalsuma(int totalsuma) {
        this.totalsuma = totalsuma;
    }
    /**
     * Uzyskaj łączną kwotę
     * @return sumprice
     */
    public int getSumprice() {
        return sumprice;
    }

    /**
     * Ustaw łączną kwote
     * @param sumprice
     */
    public void setSumprice(int sumprice) {
        this.sumprice = sumprice;
    }


    /**
     * Uzyskaj typ
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Ustaw typ
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * Uzyskaj rozmiar
     * @return size
     */
    public String getSize() {
        return size;
    }

    /**
     * Ustaw rozmiar
     * @param size
     */
    public void setSize(String size) {
        this.size = size;
    }
    /**
     * Uzyskaj cene
     * @return price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Ustaw cene
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }
    /**
     * Uzyskaj ilość
     * @return quantity
     */

    public int getQuantity() {
        return quantity;
    }

    /**
     * Ustaw ilość
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}

