package management.models.categories;

public class Storage {
    private String storageId;
    private float storageTempC;
    private float storageHumidity;
    private float currentWeight;
    private float maxWeight;
    private String storageDescribe;

    public Storage(String storageId, float storageTempC, float storageHumidity, float currentWeight, float maxWeight, String storageDescribe) {
        this.storageId = storageId;
        this.storageTempC = storageTempC;
        this.storageHumidity = storageHumidity;
        this.currentWeight = currentWeight;
        this.maxWeight = maxWeight;
        this.storageDescribe = storageDescribe;
    }

    public Storage(String storageId, float storageTempC, float storageHumidity, float currentWeight, float maxWeight) {
        this.storageId = storageId;
        this.storageTempC = storageTempC;
        this.storageHumidity = storageHumidity;
        this.currentWeight = currentWeight;
        this.maxWeight = maxWeight;
    }

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }

    public float getStorageTempC() {
        return storageTempC;
    }

    public void setStorageTempC(float storageTempC) {
        this.storageTempC = storageTempC;
    }

    public float getStorageHumidity() {
        return storageHumidity;
    }

    public void setStorageHumidity(float storageHumidity) {
        this.storageHumidity = storageHumidity;
    }

    public float getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(float currentWeight) {
        this.currentWeight = currentWeight;
    }

    public float getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(float maxWeight) {
        this.maxWeight = maxWeight;
    }

    public String getStorageDescribe() {
        return storageDescribe;
    }

    public void setStorageDescribe(String storageDescribe) {
        this.storageDescribe = storageDescribe;
    }
    
    
}
