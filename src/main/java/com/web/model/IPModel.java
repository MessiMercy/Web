package com.web.model;

/**
 * Created by Administrator on 2017/1/17.
 */
public class IPModel {
    private String host;
    private int port;
    private long createdTime;
    private long lastCheckedTime;
    private int errorTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IPModel ipModel = (IPModel) o;
        return port == ipModel.port && (host != null ? host.equals(ipModel.host) : ipModel.host == null);
    }

    @Override
    public int hashCode() {
        int result = host != null ? host.hashCode() : 0;
        result = 31 * result + port;
        return result;
    }

    @Override
    public String toString() {
        return "IPModel{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", createdTime=" + createdTime +
                ", lastCheckedTime=" + lastCheckedTime +
                '}';
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public long getLastCheckedTime() {
        return lastCheckedTime;
    }

    public void setLastCheckedTime(long lastCheckedTime) {
        this.lastCheckedTime = lastCheckedTime;
    }

    public int getErrorTime() {
        return errorTime;
    }

    public void setErrorTime(int errorTime) {
        this.errorTime = errorTime;
    }
}
