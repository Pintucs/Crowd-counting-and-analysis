package com.doorsecuritysys;



public class emergencyObject {

    private String emergencyName;
    private String emergencyIcon;


    private String id;

        public emergencyObject(String emergencyName, String emergencyIcon,String id){
        this.emergencyName=emergencyName;
        this.emergencyIcon=emergencyIcon;
        this.id=id;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmergencyName() {
        return emergencyName;
    }

    public void setEmergencyName(String emergencyName) {
        this.emergencyName = emergencyName;
    }



    public String getEmergencyIcon() {
        return emergencyIcon;
    }

    public void setEmergencyIcon(String emergencyIcon) {
        this.emergencyIcon = emergencyIcon;
    }

}
