/**
 * This class implements a dictionary using a hash table with separate chaining
 * @author Serena Hou 251015235
 */

import java.util.*;
public class Dictionary implements DictionaryADT{
    private int DICT_SIZE;
	private LinkedList<Record>[] hashTable;
	private int size = 0;
	
	public Dictionary (int dictSize) {
		DICT_SIZE = dictSize;
		hashTable = new LinkedList[DICT_SIZE];
	}
	
	private int hashFunction (String config) {
        int sum = config.charAt(0);
        for(int i = 0; i < config.length(); i++){ 
        	char c = config.charAt(i);
            sum = (sum * 31 + c) % DICT_SIZE;
        }
        sum = sum % DICT_SIZE;
        if(sum > DICT_SIZE - 1){
            return sum - DICT_SIZE;
        }
        return sum;
	}
	
	public int insert (Record pair) throws DictionaryException{
		if(hashTable[hashFunction(pair.getConfig())] != null) {
			Iterator<Record> iterator = hashTable[hashFunction(pair.getConfig())].iterator();
			while (iterator.hasNext()) {
				if (iterator.next().getConfig().equals(pair.getConfig())) {
					throw new DictionaryException();// pair.getConfig() is already in the dictionary
				}
			}
			hashTable[hashFunction(pair.getConfig())].add(pair);
            size++;
            return 1;
		}
		LinkedList<Record> list = new LinkedList<Record>();
        list.add(pair);
        hashTable[hashFunction(pair.getConfig())] = list;

        size++;
        return 0;
	}
	
	public void remove (String config) throws DictionaryException{
		if (hashTable[hashFunction(config)] != null) {
			Iterator<Record> iterator = hashTable[hashFunction(config)].iterator();
			while(iterator.hasNext()) {
				Record nextElement = iterator.next();
				if (nextElement.getConfig().equals(config)) {
					hashTable[hashFunction(config)].remove(nextElement);
					size--;
					return;
				}
			}
			throw new DictionaryException();
		}
		throw new DictionaryException();
	}
	
	public int get (String config) {
		if (hashTable[hashFunction(config)] != null) {
			Iterator<Record> iterator = hashTable[hashFunction(config)].iterator();
			Record header = iterator.next();
			while (!header.getConfig().equals(config) && iterator.hasNext()) {
				header = iterator.next();
			}
			if(header.getConfig().equals(config)) {
				return header.getScore();
			}
		}
		return -1;
	}
	
	public int numElements() {
		return size;
	}
}
