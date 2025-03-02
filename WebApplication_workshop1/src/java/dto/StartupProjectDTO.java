/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author phucl
 */
public class StartupProjectDTO {
    private int id;
    private String name;
    private String description;
    private String status;
    private String estimatedLaunch;

    public StartupProjectDTO() {
    }

    public StartupProjectDTO(int id, String name, String description, String status, String estimatedLaunch) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.estimatedLaunch = estimatedLaunch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEstimatedLaunch() {
        return estimatedLaunch;
    }

    public void setEstimatedLaunch(String estimatedLaunch) {
        this.estimatedLaunch = estimatedLaunch;
    }

    @Override
    public String toString() {
        return "StartupProjectDTO{" + "id=" + id + ", name=" + name + ", description=" + description + ", status=" + status + ", estimatedLaunch=" + estimatedLaunch + '}';
    }
    
}
