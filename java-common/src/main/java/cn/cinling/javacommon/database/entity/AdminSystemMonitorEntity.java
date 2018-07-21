package cn.cinling.javacommon.database.entity;

public class AdminSystemMonitorEntity {
    public AdminSystemMonitorEntity() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemoryTotal() {
        return memoryTotal;
    }

    public void setMemoryTotal(Integer memoryTotal) {
        this.memoryTotal = memoryTotal;
    }

    public Integer getMemoryUse() {
        return memoryUse;
    }

    public void setMemoryUse(Integer memoryUse) {
        this.memoryUse = memoryUse;
    }

    public double getDiskTotal() {
        return diskTotal;
    }

    public void setDiskTotal(double diskTotal) {
        this.diskTotal = diskTotal;
    }

    public double getDiskUse() {
        return diskUse;
    }

    public void setDiskUse(double diskUse) {
        this.diskUse = diskUse;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    private Integer id;
    private Integer memoryTotal;
    private Integer memoryUse;
    private double diskTotal;
    private double diskUse;
    private Integer time;
}
