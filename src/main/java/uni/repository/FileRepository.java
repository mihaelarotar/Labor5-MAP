package uni.repository;

public interface FileRepository<E> extends ICrudRepository<E>{
    /**
     * reads data from file
     */
    void readFromFile();

    /**
     * writes data to file
     */
    void writeToFile();
}
