package management.models.details;

import management.models.categories.Storage;
import management.models.categories.Fish;

public class StorageDetail {
    private Fish fishId;
    private Storage storageId;
    private float weight;

    public StorageDetail(Fish fishId, Storage storageId) {
        this.fishId = fishId;
        this.storageId = storageId;
    }

    public Fish getFishId() {
        return fishId;
    }

    public void setFishId(Fish fishId) {
        this.fishId = fishId;
    }

    public Storage getStorageId() {
        return storageId;
    }

    public void setStorageId(Storage storageId) {
        this.storageId = storageId;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
