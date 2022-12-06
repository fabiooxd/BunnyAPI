package me.fabio.objects;

import com.google.gson.annotations.SerializedName;
import me.fabio.CarrotStorage;

@SuppressWarnings("unused")
public class Carrot {

    @SerializedName("FailIndex")
    private final long failIndex;
    @SerializedName("Guid")
    private final String guid;
    @SerializedName("StorageZoneName")
    private final String storageZoneName;
    @SerializedName("Path")
    private final String path;
    @SerializedName("ObjectName")
    private final String objectName;
    @SerializedName("Length")
    private final long length;
    @SerializedName("LastChanged")
    private final String lastChanged;
    @SerializedName("ServerId")
    private final String serverID;
    @SerializedName("IsDirectory")
    private final boolean isDirectory;
    @SerializedName("UserId")
    private final String userID;
    @SerializedName("DateCreated")
    private final String dateCreated;
    @SerializedName("StorageZoneId")
    private final String storageZoneID;
    @SerializedName("Checksum")
    private final String checksum;
    @SerializedName("ReplicatedZones")
    private final String replicatedZones;

    public Carrot(long failIndex,
                  String guid,
                  String storageZoneName,
                  String path,
                  String objectName,
                  long length,
                  String lastChanged,
                  String serverID,
                  boolean isDirectory,
                  String userID,
                  String dateCreated,
                  String storageZoneID,
                  String checksum,
                  String replicatedZones) {
        this.failIndex = failIndex;
        this.guid = guid;
        this.storageZoneName = storageZoneName;
        this.path = path;
        this.objectName = objectName;
        this.length = length;
        this.lastChanged = lastChanged;
        this.serverID = serverID;
        this.isDirectory = isDirectory;
        this.userID = userID;
        this.dateCreated = dateCreated;
        this.storageZoneID = storageZoneID;
        this.checksum = checksum;
        this.replicatedZones = replicatedZones;
    }

    public long getFailIndex() {
        return failIndex;
    }

    public String getGUID() {
        return guid;
    }

    public String getStorageZoneName() {
        return storageZoneName;
    }

    public String getPath() {
        return path;
    }

    public String getObjectName() {
        return objectName;
    }

    public long getLength() {
        return length;
    }

    public String getLastChanged() {
        return lastChanged;
    }

    public String getServerID() {
        return serverID;
    }

    public boolean getIsDirectory() {
        return isDirectory;
    }

    public String getUserID() {
        return userID;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getStorageZoneID() {
        return storageZoneID;
    }

    public String getChecksum() {
        return checksum;
    }

    public String getReplicatedZones() {
        return replicatedZones;
    }

    @Override
    public String toString() {
        return CarrotStorage.gson.toJson(this);
    }
}
