package com.example.yuzelli.fluecuringmachine.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 51644 on 2017/6/8.
 */

public class EquipmentListBean implements Serializable {


    /**
     * count : 9
     * list : [{"deviceId":1,"deviceName":"1","deviceSn":"1001","deviceType":"1","deviceGroup":"1","state":-1,"systemdataId":1,"coredataId":1,"alarmId":1,"createTime":"2016-05-07 13:28:00.0"},{"deviceId":1496387748248,"deviceName":"dfsd","deviceSn":"ffb3e7fe","deviceType":"BBD-E","deviceGroup":"xian","state":-1,"systemdataId":1496387748248,"coredataId":1496387748248,"alarmId":1496387748248,"createTime":"2016-05-07 13:28:00.0"},{"deviceId":1496676071166,"deviceName":"烤烟机","deviceSn":"ffb3e899","deviceType":"BBD-E","deviceGroup":"xian","state":-1,"systemdataId":1496676071166,"coredataId":1496676071166,"alarmId":1496676071166,"createTime":"2016-05-07 13:28:00.0"},{"deviceId":1496676869588,"deviceName":"22","deviceSn":"ffb3e89a","deviceType":"BBD-E","deviceGroup":"xian","state":-1,"systemdataId":1496676869588,"coredataId":1496676869588,"alarmId":1496676869588,"createTime":"2016-05-07 13:28:00.0"},{"deviceId":1496824497671,"deviceName":"烤烟机","deviceSn":"ffb3e89c","deviceType":"BBD-E","deviceGroup":"xian","state":-1,"systemdataId":1496824497671,"coredataId":1496824497671,"alarmId":1496824497671,"createTime":"2016-05-07 13:28:00.0"},{"deviceId":1496826391443,"deviceName":"烤烟机","deviceSn":"ffb3e89d","deviceType":"BBD-E","deviceGroup":"xian","state":-1,"systemdataId":1496826391443,"coredataId":1496826391443,"alarmId":1496826391443,"createTime":"2016-05-07 13:28:00.0"},{"deviceId":1496886658505,"deviceName":"烤烟机","deviceSn":"ffb3e89f","deviceType":"BBD-E","deviceGroup":"xian","state":-1,"systemdataId":1496886658505,"coredataId":1496886658505,"alarmId":1496886658505,"createTime":"2016-05-07 13:28:00.0"},{"deviceId":1496887438204,"deviceName":"烤烟机","deviceSn":"ffb3e8a0","deviceType":"BBD-E","deviceGroup":"xian","state":-1,"systemdataId":1496887438204,"coredataId":1496887438204,"alarmId":1496887438204,"createTime":"2016-05-07 13:28:00.0"},{"deviceId":1496891693903,"deviceName":"烤烟机","deviceSn":"ffb3e8a4","deviceType":"BBD-E","deviceGroup":"xian","state":-1,"systemdataId":1496891693903,"coredataId":1496891693903,"alarmId":1496891693903,"createTime":"2016-05-07 13:28:00.0"}]
     */

    private int count;
    /**
     * deviceId : 1
     * deviceName : 1
     * deviceSn : 1001
     * deviceType : 1
     * deviceGroup : 1
     * state : -1
     * systemdataId : 1
     * coredataId : 1
     * alarmId : 1
     * createTime : 2016-05-07 13:28:00.0
     */

    private List<ListBean> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Serializable{
        private long deviceId;
        private String deviceName;
        private String deviceSn;
        private String deviceType;
        private String deviceGroup;
        private int state;
        private long systemdataId;
        private long coredataId;
        private long alarmId;
        private String createTime;

        public long getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(long deviceId) {
            this.deviceId = deviceId;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getDeviceSn() {
            return deviceSn;
        }

        public void setDeviceSn(String deviceSn) {
            this.deviceSn = deviceSn;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getDeviceGroup() {
            return deviceGroup;
        }

        public void setDeviceGroup(String deviceGroup) {
            this.deviceGroup = deviceGroup;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public long getSystemdataId() {
            return systemdataId;
        }

        public void setSystemdataId(long systemdataId) {
            this.systemdataId = systemdataId;
        }

        public long getCoredataId() {
            return coredataId;
        }

        public void setCoredataId(long coredataId) {
            this.coredataId = coredataId;
        }

        public long getAlarmId() {
            return alarmId;
        }

        public void setAlarmId(long alarmId) {
            this.alarmId = alarmId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
