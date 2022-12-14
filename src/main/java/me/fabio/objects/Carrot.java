package me.fabio.objects;

import com.google.gson.annotations.SerializedName;
import me.fabio.CarrotStorage;

@SuppressWarnings("unused")
public record Carrot(@SerializedName("FailIndex") long failIndex,
                     @SerializedName("Guid") String guid,
                     @SerializedName("StorageZoneName") String storageZoneName,
                     @SerializedName("Path") String path,
                     @SerializedName("ObjectName") String objectName,
                     @SerializedName("Length") long length,
                     @SerializedName("LastChanged") String lastChanged,
                     @SerializedName("ServerId") String serverID,
                     @SerializedName("IsDirectory") boolean isDirectory,
                     @SerializedName("UserId") String userID,
                     @SerializedName("DateCreated") String dateCreated,
                     @SerializedName("StorageZoneId") String storageZoneID,
                     @SerializedName("Checksum") String checksum,
                     @SerializedName("ReplicatedZones") String replicatedZones) {

    @Override
    public String toString() {
        return CarrotStorage.gson.toJson(this);
    }
}
