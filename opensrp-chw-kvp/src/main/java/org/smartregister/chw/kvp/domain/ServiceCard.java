package org.smartregister.chw.kvp.domain;

public class ServiceCard {
    private int background;

    private Integer actionItems;

    private String serviceName;

    private String serviceStatus;

    private Integer serviceIcon;

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public Integer getActionItems() {
        return actionItems;
    }

    public void setActionItems(int actionItems) {
        this.actionItems = actionItems;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public Integer getServiceIcon() {
        return serviceIcon;
    }

    public void setServiceIcon(Integer serviceIcon) {
        this.serviceIcon = serviceIcon;
    }

}
