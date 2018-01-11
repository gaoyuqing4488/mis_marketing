package com.misrobot.mismarketing.pojo.customerProject;

/**
 * 我的客户与项目
 * Created by GYQ on 2017/8/11.
 */
public class ImporantCustmProject {
    private Integer customerid;
    private String simply_name;
    private Integer projectid;
    private String project_name;

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public String getSimply_name() {
        return simply_name;
    }

    public void setSimply_name(String simply_name) {
        this.simply_name = simply_name;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

     public static class ImportantCustomers {
         private Integer customerid;
         private String simply_name;

         public Integer getCustomerid() {
             return customerid;
         }

         public void setCustomerid(Integer customerid) {
             this.customerid = customerid;
         }

         public String getSimply_name() {
             return simply_name;
         }

         public void setSimply_name(String simply_name) {
             this.simply_name = simply_name;
         }
         public static  ImportantCustomers assign(ImporantCustmProject imporantCustmProject){
             ImporantCustmProject.ImportantCustomers importantCustomers=new ImporantCustmProject.ImportantCustomers();
             importantCustomers.setCustomerid(imporantCustmProject.getCustomerid());
             importantCustomers.setSimply_name(imporantCustmProject.getSimply_name());
             return  importantCustomers;
         }
     }
}
