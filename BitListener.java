public interface BitListener {
    /**
     * 
     * An Interface to easily recieve data
     * 
     * @param handler handler to recieve bits from
     * @param bits the string of bits recieved
     */
    void bitsReceived(BitHandler handler, String bits);
}