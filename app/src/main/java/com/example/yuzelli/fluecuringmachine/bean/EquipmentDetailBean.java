package com.example.yuzelli.fluecuringmachine.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 51644 on 2017/6/9.
 */

public class EquipmentDetailBean implements Serializable {


    /**
     * systemdataId : 1496676071166
     * go : 1
     * period : 1.6
     * periodNum : 0
     * upwetTemperature : 24.1
     * updryTemperature : 24.1
     * downwetTemperature : 24.8
     * downdryTemperature : 24.9
     * wetTarget : 34.0
     * dryTarget : 36.0
     * wetAlarm : 1
     * wetAlarmWait : 1
     * wetAlarmDown : 11
     * dryAlarm : 1
     * dryAlarmWait : 1
     * dryAlarmDown : 1
     * powerAlarmUp : 250
     * powerAlarmDown : 200
     * powerAlarmWait : 1
     * totalTime : 0
     * systemStatus : 1111
     * voltage : 225V
     * times : 0
     * deviceTime : 19900717 12:25:21
     */

    private SystemDataBean systemData;
    /**
     * deviceId : 1496676071166
     * deviceName : 烤烟机
     * deviceSn : ffb3e899
     * deviceType : BBD-E
     * deviceGroup : xian
     * state : -1
     * systemdataId : 1496676071166
     * coredataId : 1496676071166
     * alarmId : 1496676071166
     * createTime : 2016-05-07 13:28:00.0
     */

    private DeviceBean device;
    /**
     * coredataId : 1496676071166
     * coreType : 0
     * creatTime : null
     * wetSet1 : 34
     * wetSet2 : 36
     * wetSet3 : 37
     * wetSet4 : 37
     * wetSet5 : 38
     * wetSet6 : 38
     * wetSet7 : 39
     * wetSet8 : 39
     * wetSet9 : 40
     * wetSet10 : 42
     * drySet1 : 36
     * drySet2 : 38
     * drySet3 : 40
     * drySet4 : 42
     * drySet5 : 46
     * drySet6 : 48
     * drySet7 : 50
     * drySet8 : 54
     * drySet9 : 60
     * drySet10 : 68
     * timeSet1 : 2
     * timeSet2 : 15
     * timeSet3 : 2
     * timeSet4 : 3
     * timeSet5 : 4
     * timeSet6 : 2
     * timeSet7 : 2
     * timeSet8 : 5
     * timeSet9 : 2
     * timeSet10 : 12
     * timeUp1 : 2
     * timeUp2 : 4
     * timeUp3 : 4
     * timeUp4 : 8
     * timeUp5 : 4
     * timeUp6 : 4
     * timeUp7 : 5
     * timeUp8 : 6
     * timeUp9 : 8
     */

    private CoreDataBean coreData;
    /**
     * systemData : {"systemdataId":1496676071166,"go":1,"period":"1.6","periodNum":"0","upwetTemperature":"24.1","updryTemperature":"24.1","downwetTemperature":"24.8","downdryTemperature":"24.9","wetTarget":"34.0","dryTarget":"36.0","wetAlarm":"1","wetAlarmWait":"1","wetAlarmDown":"11","dryAlarm":"1","dryAlarmWait":"1","dryAlarmDown":"1","powerAlarmUp":"250","powerAlarmDown":"200","powerAlarmWait":"1","totalTime":"0","systemStatus":"1111","voltage":"225V","times":0,"deviceTime":"19900717 12:25:21"}
     * device : {"deviceId":1496676071166,"deviceName":"烤烟机","deviceSn":"ffb3e899","deviceType":"BBD-E","deviceGroup":"xian","state":-1,"systemdataId":1496676071166,"coredataId":1496676071166,"alarmId":1496676071166,"createTime":"2016-05-07 13:28:00.0"}
     * coreData : {"coredataId":1496676071166,"coreType":0,"creatTime":null,"wetSet1":"34","wetSet2":"36","wetSet3":"37","wetSet4":"37","wetSet5":"38","wetSet6":"38","wetSet7":"39","wetSet8":"39","wetSet9":"40","wetSet10":"42","drySet1":"36","drySet2":"38","drySet3":"40","drySet4":"42","drySet5":"46","drySet6":"48","drySet7":"50","drySet8":"54","drySet9":"60","drySet10":"68","timeSet1":"2","timeSet2":"15","timeSet3":"2","timeSet4":"3","timeSet5":"4","timeSet6":"2","timeSet7":"2","timeSet8":"5","timeSet9":"2","timeSet10":"12","timeUp1":"2","timeUp2":"4","timeUp3":"4","timeUp4":"8","timeUp5":"4","timeUp6":"4","timeUp7":"5","timeUp8":"6","timeUp9":"8"}
     * alarms : ["1","2","3","4","5","6","7","8","9","10"]
     */

    private List<String> alarms;

    public SystemDataBean getSystemData() {
        return systemData;
    }

    public void setSystemData(SystemDataBean systemData) {
        this.systemData = systemData;
    }

    public DeviceBean getDevice() {
        return device;
    }

    public void setDevice(DeviceBean device) {
        this.device = device;
    }

    public CoreDataBean getCoreData() {
        return coreData;
    }

    public void setCoreData(CoreDataBean coreData) {
        this.coreData = coreData;
    }

    public List<String> getAlarms() {
        return alarms;
    }

    public void setAlarms(List<String> alarms) {
        this.alarms = alarms;
    }

    public static class SystemDataBean implements Serializable{
        private long systemdataId;
        private int go;
        private String period;
        private String periodNum;
        private String upwetTemperature;
        private String updryTemperature;
        private String downwetTemperature;
        private String downdryTemperature;
        private String wetTarget;
        private String dryTarget;
        private String wetAlarm;
        private String wetAlarmWait;
        private String wetAlarmDown;
        private String dryAlarm;
        private String dryAlarmWait;
        private String dryAlarmDown;
        private String powerAlarmUp;
        private String powerAlarmDown;
        private String powerAlarmWait;
        private String totalTime;
        private String systemStatus;
        private String voltage;
        private int times;
        private String deviceTime;

        public long getSystemdataId() {
            return systemdataId;
        }

        public void setSystemdataId(long systemdataId) {
            this.systemdataId = systemdataId;
        }

        public int getGo() {
            return go;
        }

        public void setGo(int go) {
            this.go = go;
        }

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }

        public String getPeriodNum() {
            return periodNum;
        }

        public void setPeriodNum(String periodNum) {
            this.periodNum = periodNum;
        }

        public String getUpwetTemperature() {
            return upwetTemperature;
        }

        public void setUpwetTemperature(String upwetTemperature) {
            this.upwetTemperature = upwetTemperature;
        }

        public String getUpdryTemperature() {
            return updryTemperature;
        }

        public void setUpdryTemperature(String updryTemperature) {
            this.updryTemperature = updryTemperature;
        }

        public String getDownwetTemperature() {
            return downwetTemperature;
        }

        public void setDownwetTemperature(String downwetTemperature) {
            this.downwetTemperature = downwetTemperature;
        }

        public String getDowndryTemperature() {
            return downdryTemperature;
        }

        public void setDowndryTemperature(String downdryTemperature) {
            this.downdryTemperature = downdryTemperature;
        }

        public String getWetTarget() {
            return wetTarget;
        }

        public void setWetTarget(String wetTarget) {
            this.wetTarget = wetTarget;
        }

        public String getDryTarget() {
            return dryTarget;
        }

        public void setDryTarget(String dryTarget) {
            this.dryTarget = dryTarget;
        }

        public String getWetAlarm() {
            return wetAlarm;
        }

        public void setWetAlarm(String wetAlarm) {
            this.wetAlarm = wetAlarm;
        }

        public String getWetAlarmWait() {
            return wetAlarmWait;
        }

        public void setWetAlarmWait(String wetAlarmWait) {
            this.wetAlarmWait = wetAlarmWait;
        }

        public String getWetAlarmDown() {
            return wetAlarmDown;
        }

        public void setWetAlarmDown(String wetAlarmDown) {
            this.wetAlarmDown = wetAlarmDown;
        }

        public String getDryAlarm() {
            return dryAlarm;
        }

        public void setDryAlarm(String dryAlarm) {
            this.dryAlarm = dryAlarm;
        }

        public String getDryAlarmWait() {
            return dryAlarmWait;
        }

        public void setDryAlarmWait(String dryAlarmWait) {
            this.dryAlarmWait = dryAlarmWait;
        }

        public String getDryAlarmDown() {
            return dryAlarmDown;
        }

        public void setDryAlarmDown(String dryAlarmDown) {
            this.dryAlarmDown = dryAlarmDown;
        }

        public String getPowerAlarmUp() {
            return powerAlarmUp;
        }

        public void setPowerAlarmUp(String powerAlarmUp) {
            this.powerAlarmUp = powerAlarmUp;
        }

        public String getPowerAlarmDown() {
            return powerAlarmDown;
        }

        public void setPowerAlarmDown(String powerAlarmDown) {
            this.powerAlarmDown = powerAlarmDown;
        }

        public String getPowerAlarmWait() {
            return powerAlarmWait;
        }

        public void setPowerAlarmWait(String powerAlarmWait) {
            this.powerAlarmWait = powerAlarmWait;
        }

        public String getTotalTime() {
            return totalTime;
        }

        public void setTotalTime(String totalTime) {
            this.totalTime = totalTime;
        }

        public String getSystemStatus() {
            return systemStatus;
        }

        public void setSystemStatus(String systemStatus) {
            this.systemStatus = systemStatus;
        }

        public String getVoltage() {
            return voltage;
        }

        public void setVoltage(String voltage) {
            this.voltage = voltage;
        }

        public int getTimes() {
            return times;
        }

        public void setTimes(int times) {
            this.times = times;
        }

        public String getDeviceTime() {
            return deviceTime;
        }

        public void setDeviceTime(String deviceTime) {
            this.deviceTime = deviceTime;
        }
    }

    public static class DeviceBean implements Serializable {
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

    public static class CoreDataBean implements Serializable {
        private long coredataId;
        private int coreType;
        private long creatTime;
        private String wetSet1;
        private String wetSet2;
        private String wetSet3;
        private String wetSet4;
        private String wetSet5;
        private String wetSet6;
        private String wetSet7;
        private String wetSet8;
        private String wetSet9;
        private String wetSet10;
        private String drySet1;
        private String drySet2;
        private String drySet3;
        private String drySet4;
        private String drySet5;
        private String drySet6;
        private String drySet7;
        private String drySet8;
        private String drySet9;
        private String drySet10;
        private String timeSet1;
        private String timeSet2;
        private String timeSet3;
        private String timeSet4;
        private String timeSet5;
        private String timeSet6;
        private String timeSet7;
        private String timeSet8;
        private String timeSet9;
        private String timeSet10;
        private String timeUp1;
        private String timeUp2;
        private String timeUp3;
        private String timeUp4;
        private String timeUp5;
        private String timeUp6;
        private String timeUp7;
        private String timeUp8;
        private String timeUp9;

        public long getCoredataId() {
            return coredataId;
        }

        public void setCoredataId(long coredataId) {
            this.coredataId = coredataId;
        }

        public int getCoreType() {
            return coreType;
        }

        public void setCoreType(int coreType) {
            this.coreType = coreType;
        }

        public long getCreatTime() {
            return creatTime;
        }

        public void setCreatTime(long creatTime) {
            this.creatTime = creatTime;
        }

        public String getWetSet1() {
            return wetSet1;
        }

        public void setWetSet1(String wetSet1) {
            this.wetSet1 = wetSet1;
        }

        public String getWetSet2() {
            return wetSet2;
        }

        public void setWetSet2(String wetSet2) {
            this.wetSet2 = wetSet2;
        }

        public String getWetSet3() {
            return wetSet3;
        }

        public void setWetSet3(String wetSet3) {
            this.wetSet3 = wetSet3;
        }

        public String getWetSet4() {
            return wetSet4;
        }

        public void setWetSet4(String wetSet4) {
            this.wetSet4 = wetSet4;
        }

        public String getWetSet5() {
            return wetSet5;
        }

        public void setWetSet5(String wetSet5) {
            this.wetSet5 = wetSet5;
        }

        public String getWetSet6() {
            return wetSet6;
        }

        public void setWetSet6(String wetSet6) {
            this.wetSet6 = wetSet6;
        }

        public String getWetSet7() {
            return wetSet7;
        }

        public void setWetSet7(String wetSet7) {
            this.wetSet7 = wetSet7;
        }

        public String getWetSet8() {
            return wetSet8;
        }

        public void setWetSet8(String wetSet8) {
            this.wetSet8 = wetSet8;
        }

        public String getWetSet9() {
            return wetSet9;
        }

        public void setWetSet9(String wetSet9) {
            this.wetSet9 = wetSet9;
        }

        public String getWetSet10() {
            return wetSet10;
        }

        public void setWetSet10(String wetSet10) {
            this.wetSet10 = wetSet10;
        }

        public String getDrySet1() {
            return drySet1;
        }

        public void setDrySet1(String drySet1) {
            this.drySet1 = drySet1;
        }

        public String getDrySet2() {
            return drySet2;
        }

        public void setDrySet2(String drySet2) {
            this.drySet2 = drySet2;
        }

        public String getDrySet3() {
            return drySet3;
        }

        public void setDrySet3(String drySet3) {
            this.drySet3 = drySet3;
        }

        public String getDrySet4() {
            return drySet4;
        }

        public void setDrySet4(String drySet4) {
            this.drySet4 = drySet4;
        }

        public String getDrySet5() {
            return drySet5;
        }

        public void setDrySet5(String drySet5) {
            this.drySet5 = drySet5;
        }

        public String getDrySet6() {
            return drySet6;
        }

        public void setDrySet6(String drySet6) {
            this.drySet6 = drySet6;
        }

        public String getDrySet7() {
            return drySet7;
        }

        public void setDrySet7(String drySet7) {
            this.drySet7 = drySet7;
        }

        public String getDrySet8() {
            return drySet8;
        }

        public void setDrySet8(String drySet8) {
            this.drySet8 = drySet8;
        }

        public String getDrySet9() {
            return drySet9;
        }

        public void setDrySet9(String drySet9) {
            this.drySet9 = drySet9;
        }

        public String getDrySet10() {
            return drySet10;
        }

        public void setDrySet10(String drySet10) {
            this.drySet10 = drySet10;
        }

        public String getTimeSet1() {
            return timeSet1;
        }

        public void setTimeSet1(String timeSet1) {
            this.timeSet1 = timeSet1;
        }

        public String getTimeSet2() {
            return timeSet2;
        }

        public void setTimeSet2(String timeSet2) {
            this.timeSet2 = timeSet2;
        }

        public String getTimeSet3() {
            return timeSet3;
        }

        public void setTimeSet3(String timeSet3) {
            this.timeSet3 = timeSet3;
        }

        public String getTimeSet4() {
            return timeSet4;
        }

        public void setTimeSet4(String timeSet4) {
            this.timeSet4 = timeSet4;
        }

        public String getTimeSet5() {
            return timeSet5;
        }

        public void setTimeSet5(String timeSet5) {
            this.timeSet5 = timeSet5;
        }

        public String getTimeSet6() {
            return timeSet6;
        }

        public void setTimeSet6(String timeSet6) {
            this.timeSet6 = timeSet6;
        }

        public String getTimeSet7() {
            return timeSet7;
        }

        public void setTimeSet7(String timeSet7) {
            this.timeSet7 = timeSet7;
        }

        public String getTimeSet8() {
            return timeSet8;
        }

        public void setTimeSet8(String timeSet8) {
            this.timeSet8 = timeSet8;
        }

        public String getTimeSet9() {
            return timeSet9;
        }

        public void setTimeSet9(String timeSet9) {
            this.timeSet9 = timeSet9;
        }

        public String getTimeSet10() {
            return timeSet10;
        }

        public void setTimeSet10(String timeSet10) {
            this.timeSet10 = timeSet10;
        }

        public String getTimeUp1() {
            return timeUp1;
        }

        public void setTimeUp1(String timeUp1) {
            this.timeUp1 = timeUp1;
        }

        public String getTimeUp2() {
            return timeUp2;
        }

        public void setTimeUp2(String timeUp2) {
            this.timeUp2 = timeUp2;
        }

        public String getTimeUp3() {
            return timeUp3;
        }

        public void setTimeUp3(String timeUp3) {
            this.timeUp3 = timeUp3;
        }

        public String getTimeUp4() {
            return timeUp4;
        }

        public void setTimeUp4(String timeUp4) {
            this.timeUp4 = timeUp4;
        }

        public String getTimeUp5() {
            return timeUp5;
        }

        public void setTimeUp5(String timeUp5) {
            this.timeUp5 = timeUp5;
        }

        public String getTimeUp6() {
            return timeUp6;
        }

        public void setTimeUp6(String timeUp6) {
            this.timeUp6 = timeUp6;
        }

        public String getTimeUp7() {
            return timeUp7;
        }

        public void setTimeUp7(String timeUp7) {
            this.timeUp7 = timeUp7;
        }

        public String getTimeUp8() {
            return timeUp8;
        }

        public void setTimeUp8(String timeUp8) {
            this.timeUp8 = timeUp8;
        }

        public String getTimeUp9() {
            return timeUp9;
        }

        public void setTimeUp9(String timeUp9) {
            this.timeUp9 = timeUp9;
        }
    }
}
