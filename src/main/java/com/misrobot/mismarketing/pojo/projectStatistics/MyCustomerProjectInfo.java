package com.misrobot.mismarketing.pojo.projectStatistics;

/**
 * Created by GYQ on 2017/10/10.
 */
public class MyCustomerProjectInfo {
        private String total_budget_money;//客户总的预算额
        private String current_budget_money;//客户当年预算额
        private String budget_money_1;//第二年预算额
        private String budget_money_2;//第三年预算额
        private String current_actual_money;//当年实际销售额

        public String getTotal_budget_money() {
            return total_budget_money;
        }

        public void setTotal_budget_money(String total_budget_money) {
            this.total_budget_money = total_budget_money;
        }

        public String getCurrent_budget_money() {
            return current_budget_money;
        }

        public void setCurrent_budget_money(String current_budget_money) {
            this.current_budget_money = current_budget_money;
        }

        public String getBudget_money_1() {
            return budget_money_1;
        }

        public void setBudget_money_1(String budget_money_1) {
            this.budget_money_1 = budget_money_1;
        }

        public String getBudget_money_2() {
            return budget_money_2;
        }

        public void setBudget_money_2(String budget_money_2) {
            this.budget_money_2 = budget_money_2;
        }

        public String getCurrent_actual_money() {
            return current_actual_money;
        }

        public void setCurrent_actual_money(String current_actual_money) {
            this.current_actual_money = current_actual_money;
        }
}
