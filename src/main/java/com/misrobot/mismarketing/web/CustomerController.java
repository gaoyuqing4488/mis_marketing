package com.misrobot.mismarketing.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.JsonObject;
import com.misrobot.mismarketing.constants.Constants;
import com.misrobot.mismarketing.constants.ErrorCode;
import com.misrobot.mismarketing.entity.CustomerDetailEntity;
import com.misrobot.mismarketing.entity.CustomerEntity;
import com.misrobot.mismarketing.pojo.mismarketing.dao.CustomerInfoResponse;
import com.misrobot.mismarketing.service.CustomerService;
import com.misrobot.mismarketing.util.ErrorMessageUtil;
import com.misrobot.mismarketing.util.JsonUtil;
import com.misrobot.mismarketing.util.TokenUtil;
import com.misrobot.mismarketing.base.RestCommand;
import com.misrobot.mismarketing.base.RestRequest;
import com.misrobot.mismarketing.base.RestResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by CHJ on 2017/7/28.
 */
@Controller
public @RequestMapping("/customer")
class CustomerController {

    private ObjectMapper mMapper = new ObjectMapper();

    @Autowired
    private CustomerService mCustomerService;

    @RequestMapping("/createnewcustomer")
    public @ResponseBody
    JsonNode createNewCustomer(@RequestBody JsonNode parameters, HttpSession session) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand restCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(restCmd);
            String cmd = command.getParameter("command");
            try {
                String full_name = command.getParameter("fullname");
                String project_number = command.getParameter("projectnumber");
                String budget = command.getParameter("budget");
                String budget_sale_1 = command.getParameter("budgetsale_1");
                String budget_sale_2 = command.getParameter("budgetsale_2");
                String budget_sale_3 = command.getParameter("budgetsale_3");
                String actual_sale = command.getParameter("actualsale");
                String customer_type = command.getParameter("customertype");
                //                String customer_rate = command.getParameter("customerrate");
                String customer_rate_describe = command.getParameter("customerratedescribe");
                String customer_num = command.getParameter("ishaveskillcenter");
                String is_have_skill_center = command.getParameter("ishaveskillcenter");
                String skill_center_area = command.getParameter("skillcenterarea");
                String skill_center_administer_num = command.getParameter("skillcenteradministernum");
                String customer_owner_id = command.getParameter("customerownerid");
                String customer_owner = command.getParameter("customerowner");
                String province = command.getParameter("province");
                String city = command.getParameter("city");
                String simply_name = command.getParameter("simplyname");
                String administrative_attribution = command.getParameter("administrativeattribution");
                String school_type = command.getParameter("schooltype");
                String hospital_rate = command.getParameter("hospitalrate");
                String is_attach_hospital = command.getParameter("isattachhospital");
                String is_attach_hospital_describe = command.getParameter("isattachhospitaldescribe");
                String is_teach_hospital = command.getParameter("isteachhospital");
                // Customer detail info.
                JsonNode customer_detail_info = command.getParameterAsJsonNode("customerdetailinfolist");
                if (StringUtils.isEmpty(cmd) || StringUtils.isEmpty(full_name)
                        || (!StringUtils.isNumeric(project_number))
                        || (!StringUtils.isNumeric(customer_type))
                        //                        || (!StringUtils.isNumeric(customer_rate))
                        || (!StringUtils.isNumeric(customer_num))
                        || (!StringUtils.isNumeric(is_have_skill_center))
                        || (!StringUtils.isNumeric(skill_center_area))
                        || (!StringUtils.isNumeric(skill_center_administer_num))
                        || (!StringUtils.isNumeric(customer_owner_id))
                        || (Integer.parseInt(customer_type) == 0 /* School */
                        && ((!StringUtils.isNumeric(administrative_attribution))
                        || (!StringUtils.isNumeric(school_type))))
                        || (Integer.parseInt(customer_type) == 1 /* Hospital */
                        && ((!StringUtils.isNumeric(hospital_rate))
                        || (!StringUtils.isNumeric(is_attach_hospital))
                        /*|| (!StringUtils.isNumeric(is_teach_hospital))*/))) {
                    restCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    restCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    return response.toJsonObject();
                }
                if (!TokenUtil.checkLoginInfo(Long.parseLong(customer_owner_id), session)) {
                    restCmd.setStatusCode(Integer.valueOf(ErrorCode.SESSION_INVALID));
                    restCmd.setStatusDescription("session is invalid!");
                    return response.toJsonObject();
                }
                CustomerEntity customerEntity = new CustomerEntity();
                customerEntity.setFullname(full_name);
                customerEntity.setProjectnum(Integer.parseInt(project_number));
                customerEntity.setBudget(new BigDecimal(budget));
                customerEntity.setBudgetsale_1(new BigDecimal(budget_sale_1));
                customerEntity.setBudgetsale_2(new BigDecimal(budget_sale_2));
                customerEntity.setBudgetsale_3(new BigDecimal(budget_sale_3));
                customerEntity.setActualsale(new BigDecimal(actual_sale));
                customerEntity.setCustomertype(Integer.parseInt(customer_type));
                //                customerEntity.setCustomerrate(Integer.parseInt(customer_rate));
                customerEntity.setCustomerratedescribe(customer_rate_describe);
                customerEntity.setCustomernum(Integer.parseInt(customer_num));
                customerEntity.setIshaveskillcenter(Integer.parseInt(is_have_skill_center));
                customerEntity.setSkillcenterarea(Integer.parseInt(skill_center_area));
                customerEntity.setSkillcenteradministernum(Integer.parseInt(skill_center_administer_num));
                customerEntity.setCustomerownerid(Integer.valueOf(customer_owner_id));
                customerEntity.setCustomerowner(customer_owner);
                customerEntity.setProvince(province);
                customerEntity.setCity(city);
                customerEntity.setSimplyname(simply_name);
                if (StringUtils.isNumeric(administrative_attribution))
                    customerEntity.setAdministrativeattribution(Integer.parseInt(administrative_attribution));
                if (StringUtils.isNumeric(school_type))
                    customerEntity.setSchooltype(Integer.parseInt(school_type));
                if (StringUtils.isNumeric(hospital_rate))
                    customerEntity.setHospitalrate(Integer.parseInt(hospital_rate));
                if (StringUtils.isNumeric(is_attach_hospital))
                    customerEntity.setIsattachhospital(Integer.parseInt(is_attach_hospital));
                customerEntity.setIsattachhospitaldescribe(is_attach_hospital_describe);
                if (StringUtils.isNumeric(is_teach_hospital))
                    customerEntity.setIsteachhospital(Integer.parseInt(is_teach_hospital));
                //insert date into database.
                int customerId = mCustomerService.insertNewCustomerInfo(customerEntity, customer_detail_info);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("customerid", customerId);
                restCmd.setParameter(jsonObject.toString());
                restCmd.setCommand(cmd);
                restCmd.setStatusCode(Integer.valueOf(Constants.SUCCESS));
                restCmd.setStatusDescription("create new customer success.");
            } catch (Exception e) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("customerid", -1);
                restCmd.setParameter(jsonObject.toString());
                restCmd.setCommand(cmd);
                restCmd.setStatusCode(Integer.valueOf(Constants.FAIL));
                restCmd.setStatusDescription("error occur when insert data into database.");
            }
        }
        return response.toJsonObject();
    }

    @RequestMapping("/querycustomers")
    public @ResponseBody
    JsonNode queryCustomers(@RequestBody JsonNode parameters) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {

            RestCommand restCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(restCmd);

            String cmd = command.getParameter("command");
            String customer_id = command.getParameter("customerid");
            String user_id = command.getParameter("userid");
            String page = command.getParameter("page");
            String pagesize = command.getParameter("pagesize");
            if (StringUtils.isEmpty(cmd) ||
                    !org.apache.commons.lang3.StringUtils.isNumeric(page) ||
                    !org.apache.commons.lang3.StringUtils.isNumeric(pagesize) ||
                    (Integer.parseInt(pagesize) <= 0) || (Integer.parseInt(page) <= 0)) {
                restCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                restCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                return response.toJsonObject();
            }
            int offset = (Integer.parseInt(page) - 1) * Integer.parseInt(pagesize);
            List<CustomerEntity> customerEntityList = new ArrayList<CustomerEntity>();
            List<CustomerDetailEntity> customerDetailEntityList = null;
            if (!StringUtils.isEmpty(customer_id)) { //find identify customer.
                CustomerEntity customerEntity = mCustomerService.findMainItemByCustomerID(Integer.parseInt(customer_id), offset, Integer.parseInt(pagesize));
                customerDetailEntityList = mCustomerService.findSubItemByCustomerID(Integer.parseInt(customer_id));
                if (customerEntity != null) {
                    customerEntityList.add(customerEntity);
                }
            } else {
                if ((!StringUtils.isEmpty(user_id)) && StringUtils.isNumeric(user_id)) {
                    //find identify user's all customers.
                    customerEntityList = mCustomerService.findMainItemsByUserID(Integer.valueOf(user_id), offset, Integer.parseInt(pagesize));
                    customerDetailEntityList = mCustomerService.findSubItemsByUserID(Integer.valueOf(user_id));
                } else {
                    //find all customers.
                    customerEntityList = mCustomerService.findAllMainItems(offset, Integer.parseInt(pagesize));
                    customerDetailEntityList = mCustomerService.findAllSubItems();
                }
            }
            //init response body.
            CustomerInfoResponse res = new CustomerInfoResponse();
            res.setCustomerlist(new ArrayList<CustomerInfoResponse.CustomerInfoUnit>());
            for (int i = 0; i < customerEntityList.size(); i++) {
                CustomerInfoResponse.CustomerInfoUnit unit = new CustomerInfoResponse.CustomerInfoUnit();
                CustomerEntity customerEntity = customerEntityList.get(i);
                unit.init(customerEntity, getCustomerDetailInfoList(customerEntity.getId(), customerDetailEntityList));
                res.getCustomerlist().add(unit);
            }
            restCmd.setParameter(JsonUtil.getJSONString(res));
            restCmd.setCommand(cmd);
            restCmd.setStatusCode(Integer.valueOf(Constants.SUCCESS));
            restCmd.setStatusDescription("query customers success.");
        }

        return response.toJsonObject();
    }

    @RequestMapping("/getcustomerscount")
    public @ResponseBody
    JsonNode getCustomersCount(@RequestBody JsonNode parameters) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {

            RestCommand restCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(restCmd);

            String cmd = command.getParameter("command");
            String customer_id = command.getParameter("customerid");
            String user_id = command.getParameter("userid");
            if (StringUtils.isEmpty(cmd)) {
                restCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                restCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                return response.toJsonObject();
            }

            int count = 0;
            if (!StringUtils.isEmpty(customer_id)) { //find identify customer.
                count = mCustomerService.getCustomerCountByPrimaryKey(Integer.parseInt(customer_id));
            } else {
                if ((!StringUtils.isEmpty(user_id)) && StringUtils.isNumeric(user_id)) {
                    //find identify user's all customers.
                    count = mCustomerService.getCustomerCountByUserID(Integer.valueOf(user_id));
                } else {
                    //find all customers.
                    count = mCustomerService.getAllCustomerCount();
                }
            }
            ObjectNode res = mMapper.createObjectNode();
            res.put("customerscount", count);
            restCmd.setParameter(res);
            restCmd.setCommand(cmd);
            restCmd.setStatusCode(Integer.valueOf(Constants.SUCCESS));
            restCmd.setStatusDescription("query customers success.");
        }

        return response.toJsonObject();
    }

    @RequestMapping("/updatecustomer")
    public @ResponseBody
    JsonNode updateCustomer(@RequestBody JsonNode parameters, HttpSession session) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand restCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(restCmd);
            String cmd = command.getParameter("command");
            String customer_id = command.getParameter("customerid");
            String full_name = command.getParameter("fullname");
            String project_number = command.getParameter("projectnumber");
            String budget = command.getParameter("budget");
            String budget_sale_1 = command.getParameter("budgetsale_1");
            String budget_sale_2 = command.getParameter("budgetsale_2");
            String budget_sale_3 = command.getParameter("budgetsale_3");
            String actual_sale = command.getParameter("actualsale");
            String customer_type = command.getParameter("customertype");
            //            String customer_rate = command.getParameter("customerrate");
            String customer_rate_describe = command.getParameter("customerratedescribe");
            String customer_num = command.getParameter("ishaveskillcenter");
            String is_have_skill_center = command.getParameter("ishaveskillcenter");
            String skill_center_area = command.getParameter("skillcenterarea");
            String skill_center_administer_num = command.getParameter("skillcenteradministernum");
            String customer_owner_id = command.getParameter("customerownerid");
            String customer_owner = command.getParameter("customerowner");
            String province = command.getParameter("province");
            String city = command.getParameter("city");
            String simply_name = command.getParameter("simplyname");
            String administrative_attribution = command.getParameter("administrativeattribution");
            String school_type = command.getParameter("schooltype");
            String hospital_rate = command.getParameter("hospitalrate");
            String is_attach_hospital = command.getParameter("isattachhospital");
            String is_attach_hospital_describe = command.getParameter("isattachhospitaldescribe");
            String is_teach_hospital = command.getParameter("isteachhospital");
            // Customer detail info.
            JsonNode customer_detail_info = command.getParameterAsJsonNode("customerdetailinfolist");
            if (StringUtils.isEmpty(cmd)
                    || (!StringUtils.isNumeric(customer_id))
                    || (!StringUtils.isNumeric(project_number))
                    || (!StringUtils.isNumeric(customer_type))
                    //                    || (!StringUtils.isNumeric(customer_rate))
                    || (!StringUtils.isNumeric(customer_num))
                    || (!StringUtils.isNumeric(is_have_skill_center))
                    || (!StringUtils.isNumeric(skill_center_area))
                    || (!StringUtils.isNumeric(skill_center_administer_num))
                    || (!StringUtils.isNumeric(customer_owner_id))
                    || (Integer.parseInt(customer_type) == 0 /* School */
                    && ((!StringUtils.isNumeric(administrative_attribution))
                    || (!StringUtils.isNumeric(school_type))))
                    || (Integer.parseInt(customer_type) == 1 /* Hospital */
                    && ((!StringUtils.isNumeric(hospital_rate))
                    || (!StringUtils.isNumeric(is_attach_hospital))
                    /*|| (!StringUtils.isNumeric(is_teach_hospital))*/))) {
                restCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                restCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                return response.toJsonObject();
            }
            if (!TokenUtil.checkLoginInfo(Long.parseLong(customer_owner_id), session)) {
                restCmd.setStatusCode(Integer.valueOf(ErrorCode.SESSION_INVALID));
                restCmd.setStatusDescription("session is invalid!");
                return response.toJsonObject();
            }
            CustomerEntity customerEntity = new CustomerEntity();
            customerEntity.setId(Integer.parseInt(customer_id));
            customerEntity.setFullname(full_name);
            customerEntity.setProjectnum(Integer.parseInt(project_number));
            customerEntity.setBudget(new BigDecimal(budget));
            customerEntity.setBudgetsale_1(new BigDecimal(budget_sale_1));
            customerEntity.setBudgetsale_2(new BigDecimal(budget_sale_2));
            customerEntity.setBudgetsale_3(new BigDecimal(budget_sale_3));
            customerEntity.setActualsale(new BigDecimal(actual_sale));
            customerEntity.setCustomertype(Integer.parseInt(customer_type));
            customerEntity.setCustomerratedescribe(customer_rate_describe);
            customerEntity.setCustomernum(Integer.parseInt(customer_num));
            customerEntity.setIshaveskillcenter(Integer.parseInt(is_have_skill_center));
            customerEntity.setSkillcenterarea(Integer.parseInt(skill_center_area));
            customerEntity.setSkillcenteradministernum(Integer.parseInt(skill_center_administer_num));
            customerEntity.setCustomerownerid(Integer.parseInt(customer_owner_id));
            customerEntity.setCustomerowner(customer_owner);
            customerEntity.setProvince(province);
            customerEntity.setCity(city);
            customerEntity.setSimplyname(simply_name);
            if (StringUtils.isNumeric(administrative_attribution))
                customerEntity.setAdministrativeattribution(Integer.parseInt(administrative_attribution));
            if (StringUtils.isNumeric(school_type))
                customerEntity.setSchooltype(Integer.parseInt(school_type));
            if (StringUtils.isNumeric(hospital_rate))
                customerEntity.setHospitalrate(Integer.parseInt(hospital_rate));
            if (StringUtils.isNumeric(is_attach_hospital))
                customerEntity.setIsattachhospital(Integer.parseInt(is_attach_hospital));
            customerEntity.setIsattachhospitaldescribe(is_attach_hospital_describe);
            if (StringUtils.isNumeric(is_teach_hospital))
                customerEntity.setIsteachhospital(Integer.parseInt(is_teach_hospital));
            try {
                mCustomerService.updateCustomerInfo(customerEntity, customer_detail_info);
                restCmd.setCommand(cmd);
                restCmd.setStatusCode(Integer.valueOf(Constants.SUCCESS));
                restCmd.setStatusDescription("update customers success.");
            } catch (Exception e) {
                restCmd.setCommand(cmd);
                restCmd.setStatusCode(Integer.valueOf(Constants.FAIL));
                restCmd.setStatusDescription("update customers fail.");
            }
        }

        return response.toJsonObject();
    }

    @RequestMapping("/deletecustomer")
    public @ResponseBody
    JsonNode deleteCustomer(@RequestBody JsonNode parameters, HttpSession session) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand restCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(restCmd);
            String cmd = command.getParameter("command");
            String customer_id = command.getParameter("customerid");
            String userid = command.getParameter("userid");
            if (StringUtils.isEmpty(cmd) ||
                    (!StringUtils.isNumeric(customer_id)) ||
                    (!StringUtils.isNumeric(userid))) {
                restCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                restCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                return response.toJsonObject();
            }

            if (!TokenUtil.checkLoginInfo(Long.parseLong(userid), session)) {
                restCmd.setStatusCode(Integer.valueOf(ErrorCode.SESSION_INVALID));
                restCmd.setStatusDescription("session is invalid!");
                return response.toJsonObject();
            }

            try {
                mCustomerService.deleteCustomerInfo(Integer.parseInt(customer_id));
                restCmd.setCommand(cmd);
                restCmd.setStatusCode(Integer.valueOf(Constants.SUCCESS));
                restCmd.setStatusDescription("delete customers success.");
            } catch (Exception e) {
                restCmd.setCommand(cmd);
                restCmd.setStatusCode(Integer.valueOf(Constants.FAIL));
                restCmd.setStatusDescription("delete customers fail.");
            }
        }

        return response.toJsonObject();
    }

    private List<CustomerDetailEntity> getCustomerDetailInfoList(int customerId, List<CustomerDetailEntity> customerDetailEntity) {
        List<CustomerDetailEntity> list = new ArrayList<CustomerDetailEntity>();
        for (CustomerDetailEntity detailEntity : customerDetailEntity) {
            if (detailEntity.getCustomerid() == customerId) {
                list.add(detailEntity);
            }
        }
        //
        return list;
    }
}
