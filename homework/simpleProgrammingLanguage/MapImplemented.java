import java.util.ArrayList;

public class MapImplemented<K, V> implements MapInterface<K, V> {
    private static class MapEntry<K, V> {
      private K key;
      private V value;

      public MapEntry(K key, V value) {
          this.key = key;
          this.value = value;
      }

      public K getKey() { return key; }
      public V getValue() { return value; }
      public V setValue(V value) {
          V old = this.value;
          this.value = value;
          return old;
      }
    }

    private ArrayList<MapEntry<K, V>>[] table;
    private int capacity;
    private int size = 0;
    private double loadFactorThreshold = 0.75;
    
    public MapImplemented(int capacity) {
        this.capacity = capacity;
        table = (ArrayList<MapEntry<K, V>>[]) new ArrayList[capacity];
    }

    public MapImplemented() {
      this.capacity = 10;
      table = (ArrayList<MapEntry<K, V >>[]) new ArrayList[capacity];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public void put(K key, V value) {
        if (size >= capacity * loadFactorThreshold) {
            rehash();
        }
        int h = hash(key);
        if (table[h] == null) {
            table[h] = new ArrayList<>();
        }
        for (MapEntry<K, V> entry : table[h]) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }
        table[h].add(new MapEntry<>(key, value));
        size++;
    }

    private void rehash() {
        capacity *= 2;
        ArrayList<MapEntry<K, V>>[] newTable = (ArrayList<MapEntry<K, V>>[]) new ArrayList[capacity];
        for (ArrayList<MapEntry<K, V>> bucket : table) {
            if (bucket != null) {
                for (MapEntry<K, V> entry : bucket) {
                    int h = hash(entry.getKey());
                    if (newTable[h] == null) {
                        newTable[h] = new ArrayList<>();
                    }
                    newTable[h].add(entry);
                }
            }
        }
        table = newTable;
    }

    public V get(K key) {
        int h = hash(key);
        if (table[h] != null) {
            for (MapEntry<K, V> entry : table[h]) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }
}
