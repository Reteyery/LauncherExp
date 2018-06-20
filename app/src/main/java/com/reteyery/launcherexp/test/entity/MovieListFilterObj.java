package com.reteyery.launcherexp.test.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 影视列表筛选条件
 */
public class MovieListFilterObj implements Serializable{

    private static final long serialVersionUID = 2346430610337037116L;

    /**
     * success : true
     * code : 1000
     * msg : success
     * data : {"area_item_list":[{"id":2,"item_name":"韩国","item_order":0,"mapping_values":"日韩","time_type_id":null},{"id":2,"item_name":"香港","item_order":3,"mapping_values":"中国香港","time_type_id":null},{"id":2,"item_name":"美国","item_order":3,"mapping_values":"美国","time_type_id":null},{"id":2,"item_name":"英国","item_order":3,"mapping_values":"英国","time_type_id":null},{"id":2,"item_name":"法国","item_order":3,"mapping_values":"法国","time_type_id":null},{"id":2,"item_name":"测试换行","item_order":3,"mapping_values":"Croatia","time_type_id":null},{"id":2,"item_name":"内地","item_order":3,"mapping_values":"中国内地","time_type_id":null}],"area_type_id":967,"area_type_name":"地区","order_item_list":[{"id":1,"item_name":"按最新","item_order":3,"mapping_values":"new","time_type_id":null},{"id":1,"item_name":"按最热","item_order":3,"mapping_values":"2","time_type_id":null}],"order_type_id":966,"order_type_name":"排序","program_type_id":54,"tag_item_list":[{"id":33,"item_name":"222","item_order":3,"mapping_values":"院线","time_type_id":null},{"id":33,"item_name":"9898","item_order":3,"mapping_values":"惊悚","time_type_id":null},{"id":33,"item_name":"444444","item_order":3,"mapping_values":"院线,喜剧","time_type_id":null}],"tag_type_id":968,"tag_type_name":"标签","year_item_list":[{"id":42,"item_name":"年份","item_order":3,"mapping_values":"1899","time_type_id":990},{"id":42,"item_name":"1","item_order":3,"mapping_values":"2017","time_type_id":972},{"id":42,"item_name":"2","item_order":3,"mapping_values":"2018","time_type_id":972},{"id":42,"item_name":"3333333333333333333333333333333333433333333333333333333333333333333333333333333333333333333","item_order":3,"mapping_values":"2013,2014","time_type_id":973},{"id":42,"item_name":"444444444444444444444444444444444444444444444444444444444","item_order":3,"mapping_values":"2017","time_type_id":990},{"id":42,"item_name":"4555555555555555555555","item_order":3,"mapping_values":"1999","time_type_id":990},{"id":42,"item_name":"6","item_order":3,"mapping_values":"2018","time_type_id":989},{"id":42,"item_name":"6777","item_order":3,"mapping_values":"2014,2015","time_type_id":973},{"id":42,"item_name":"8","item_order":3,"mapping_values":"2019","time_type_id":989},{"id":42,"item_name":"9","item_order":3,"mapping_values":"2019","time_type_id":972},{"id":42,"item_name":"11","item_order":3,"mapping_values":"2010","time_type_id":989}],"year_type_id":969,"year_type_name":"年份"}
     */

    private boolean success;
    private int code;
    private String msg;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MovieListFilterObj{" +
                "success=" + success +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean implements Serializable{

        private static final long serialVersionUID = 2752154769326706300L;
        /**
         * area_item_list : [{"id":2,"item_name":"韩国","item_order":0,"mapping_values":"日韩","time_type_id":null},{"id":2,"item_name":"香港","item_order":3,"mapping_values":"中国香港","time_type_id":null},{"id":2,"item_name":"美国","item_order":3,"mapping_values":"美国","time_type_id":null},{"id":2,"item_name":"英国","item_order":3,"mapping_values":"英国","time_type_id":null},{"id":2,"item_name":"法国","item_order":3,"mapping_values":"法国","time_type_id":null},{"id":2,"item_name":"测试换行","item_order":3,"mapping_values":"Croatia","time_type_id":null},{"id":2,"item_name":"内地","item_order":3,"mapping_values":"中国内地","time_type_id":null}]
         * area_type_id : 967
         * area_type_name : 地区
         * order_item_list : [{"id":1,"item_name":"按最新","item_order":3,"mapping_values":"new","time_type_id":null},{"id":1,"item_name":"按最热","item_order":3,"mapping_values":"2","time_type_id":null}]
         * order_type_id : 966
         * order_type_name : 排序
         * program_type_id : 54
         * tag_item_list : [{"id":33,"item_name":"222","item_order":3,"mapping_values":"院线","time_type_id":null},{"id":33,"item_name":"9898","item_order":3,"mapping_values":"惊悚","time_type_id":null},{"id":33,"item_name":"444444","item_order":3,"mapping_values":"院线,喜剧","time_type_id":null}]
         * tag_type_id : 968
         * tag_type_name : 标签
         * year_item_list : [{"id":42,"item_name":"年份","item_order":3,"mapping_values":"1899","time_type_id":990},{"id":42,"item_name":"1","item_order":3,"mapping_values":"2017","time_type_id":972},{"id":42,"item_name":"2","item_order":3,"mapping_values":"2018","time_type_id":972},{"id":42,"item_name":"3333333333333333333333333333333333433333333333333333333333333333333333333333333333333333333","item_order":3,"mapping_values":"2013,2014","time_type_id":973},{"id":42,"item_name":"444444444444444444444444444444444444444444444444444444444","item_order":3,"mapping_values":"2017","time_type_id":990},{"id":42,"item_name":"4555555555555555555555","item_order":3,"mapping_values":"1999","time_type_id":990},{"id":42,"item_name":"6","item_order":3,"mapping_values":"2018","time_type_id":989},{"id":42,"item_name":"6777","item_order":3,"mapping_values":"2014,2015","time_type_id":973},{"id":42,"item_name":"8","item_order":3,"mapping_values":"2019","time_type_id":989},{"id":42,"item_name":"9","item_order":3,"mapping_values":"2019","time_type_id":972},{"id":42,"item_name":"11","item_order":3,"mapping_values":"2010","time_type_id":989}]
         * year_type_id : 969
         * year_type_name : 年份
         */

        private int area_type_id;
        private String area_type_name;
        private int order_type_id;
        private String order_type_name;
        private int program_type_id;
        private int tag_type_id;
        private String tag_type_name;
        private int year_type_id;
        private String year_type_name;
        private List<AreaItemListBean> area_item_list;
        private List<OrderItemListBean> order_item_list;
        private List<TagItemListBean> tag_item_list;
        private List<YearItemListBean> year_item_list;

        public int getArea_type_id() {
            return area_type_id;
        }

        public void setArea_type_id(int area_type_id) {
            this.area_type_id = area_type_id;
        }

        public String getArea_type_name() {
            return area_type_name;
        }

        public void setArea_type_name(String area_type_name) {
            this.area_type_name = area_type_name;
        }

        public int getOrder_type_id() {
            return order_type_id;
        }

        public void setOrder_type_id(int order_type_id) {
            this.order_type_id = order_type_id;
        }

        public String getOrder_type_name() {
            return order_type_name;
        }

        public void setOrder_type_name(String order_type_name) {
            this.order_type_name = order_type_name;
        }

        public int getProgram_type_id() {
            return program_type_id;
        }

        public void setProgram_type_id(int program_type_id) {
            this.program_type_id = program_type_id;
        }

        public int getTag_type_id() {
            return tag_type_id;
        }

        public void setTag_type_id(int tag_type_id) {
            this.tag_type_id = tag_type_id;
        }

        public String getTag_type_name() {
            return tag_type_name;
        }

        public void setTag_type_name(String tag_type_name) {
            this.tag_type_name = tag_type_name;
        }

        public int getYear_type_id() {
            return year_type_id;
        }

        public void setYear_type_id(int year_type_id) {
            this.year_type_id = year_type_id;
        }

        public String getYear_type_name() {
            return year_type_name;
        }

        public void setYear_type_name(String year_type_name) {
            this.year_type_name = year_type_name;
        }

        public List<AreaItemListBean> getArea_item_list() {
            return area_item_list;
        }

        public void setArea_item_list(List<AreaItemListBean> area_item_list) {
            this.area_item_list = area_item_list;
        }

        public List<OrderItemListBean> getOrder_item_list() {
            return order_item_list;
        }

        public void setOrder_item_list(List<OrderItemListBean> order_item_list) {
            this.order_item_list = order_item_list;
        }

        public List<TagItemListBean> getTag_item_list() {
            return tag_item_list;
        }

        public void setTag_item_list(List<TagItemListBean> tag_item_list) {
            this.tag_item_list = tag_item_list;
        }

        public List<YearItemListBean> getYear_item_list() {
            return year_item_list;
        }

        public void setYear_item_list(List<YearItemListBean> year_item_list) {
            this.year_item_list = year_item_list;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "area_type_id=" + area_type_id +
                    ", area_type_name='" + area_type_name + '\'' +
                    ", order_type_id=" + order_type_id +
                    ", order_type_name='" + order_type_name + '\'' +
                    ", program_type_id=" + program_type_id +
                    ", tag_type_id=" + tag_type_id +
                    ", tag_type_name='" + tag_type_name + '\'' +
                    ", year_type_id=" + year_type_id +
                    ", year_type_name='" + year_type_name + '\'' +
                    ", area_item_list=" + area_item_list +
                    ", order_item_list=" + order_item_list +
                    ", tag_item_list=" + tag_item_list +
                    ", year_item_list=" + year_item_list +
                    '}';
        }

        public static class AreaItemListBean implements Serializable{
            private static final long serialVersionUID = -5729811636145874961L;
            /**
             * id : 2
             * item_name : 韩国
             * item_order : 0
             * mapping_values : 日韩
             * time_type_id : null
             */

            private int id;
            private String item_name;
            private int item_order;
            private String mapping_values;
            private Object time_type_id;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getItem_name() {
                return item_name;
            }

            public void setItem_name(String item_name) {
                this.item_name = item_name;
            }

            public int getItem_order() {
                return item_order;
            }

            public void setItem_order(int item_order) {
                this.item_order = item_order;
            }

            public String getMapping_values() {
                return mapping_values;
            }

            public void setMapping_values(String mapping_values) {
                this.mapping_values = mapping_values;
            }

            public Object getTime_type_id() {
                return time_type_id;
            }

            public void setTime_type_id(Object time_type_id) {
                this.time_type_id = time_type_id;
            }

            @Override
            public String toString() {
                return "AreaItemListBean{" +
                        "id=" + id +
                        ", item_name='" + item_name + '\'' +
                        ", item_order=" + item_order +
                        ", mapping_values='" + mapping_values + '\'' +
                        ", time_type_id=" + time_type_id +
                        '}';
            }
        }

        public static class OrderItemListBean implements Serializable{
            private static final long serialVersionUID = 6498876059811097392L;
            /**
             * id : 1
             * item_name : 按最新
             * item_order : 3
             * mapping_values : new
             * time_type_id : null
             */

            private int id;
            private String item_name;
            private int item_order;
            private String mapping_values;
            private Object time_type_id;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getItem_name() {
                return item_name;
            }

            public void setItem_name(String item_name) {
                this.item_name = item_name;
            }

            public int getItem_order() {
                return item_order;
            }

            public void setItem_order(int item_order) {
                this.item_order = item_order;
            }

            public String getMapping_values() {
                return mapping_values;
            }

            public void setMapping_values(String mapping_values) {
                this.mapping_values = mapping_values;
            }

            public Object getTime_type_id() {
                return time_type_id;
            }

            public void setTime_type_id(Object time_type_id) {
                this.time_type_id = time_type_id;
            }
        }

        public static class TagItemListBean implements Serializable{
            private static final long serialVersionUID = 4497006570226136068L;
            /**
             * id : 33
             * item_name : 222
             * item_order : 3
             * mapping_values : 院线
             * time_type_id : null
             */

            private int id;
            private String item_name;
            private int item_order;
            private String mapping_values;
            private Object time_type_id;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getItem_name() {
                return item_name;
            }

            public void setItem_name(String item_name) {
                this.item_name = item_name;
            }

            public int getItem_order() {
                return item_order;
            }

            public void setItem_order(int item_order) {
                this.item_order = item_order;
            }

            public String getMapping_values() {
                return mapping_values;
            }

            public void setMapping_values(String mapping_values) {
                this.mapping_values = mapping_values;
            }

            public Object getTime_type_id() {
                return time_type_id;
            }

            public void setTime_type_id(Object time_type_id) {
                this.time_type_id = time_type_id;
            }

            @Override
            public String toString() {
                return "TagItemListBean{" +
                        "id=" + id +
                        ", item_name='" + item_name + '\'' +
                        ", item_order=" + item_order +
                        ", mapping_values='" + mapping_values + '\'' +
                        ", time_type_id=" + time_type_id +
                        '}';
            }
        }

        public static class YearItemListBean implements Serializable{
            private static final long serialVersionUID = 3312380327620956083L;
            /**
             * id : 42
             * item_name : 年份
             * item_order : 3
             * mapping_values : 1899
             * time_type_id : 990
             */

            private int id;
            private String item_name;
            private int item_order;
            private String mapping_values;
            private int time_type_id;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getItem_name() {
                return item_name;
            }

            public void setItem_name(String item_name) {
                this.item_name = item_name;
            }

            public int getItem_order() {
                return item_order;
            }

            public void setItem_order(int item_order) {
                this.item_order = item_order;
            }

            public String getMapping_values() {
                return mapping_values;
            }

            public void setMapping_values(String mapping_values) {
                this.mapping_values = mapping_values;
            }

            public int getTime_type_id() {
                return time_type_id;
            }

            public void setTime_type_id(int time_type_id) {
                this.time_type_id = time_type_id;
            }

            @Override
            public String toString() {
                return "YearItemListBean{" +
                        "id=" + id +
                        ", item_name='" + item_name + '\'' +
                        ", item_order=" + item_order +
                        ", mapping_values='" + mapping_values + '\'' +
                        ", time_type_id=" + time_type_id +
                        '}';
            }
        }
    }
}
