    package com.ruoyi.web.controller.api.common;

    import java.util.HashMap;
    import java.util.Map;

    /**
     * 自行封装一个返回类 对请求进行统一的值返回
     * @param <T> 泛型
     */
    public class Result<T> {
        private Integer status;   //0 获取成功 1 获取失败

        private String msg; //错误/成功信息

        private T data; //数据

        private Map<Object,Object> map = new HashMap<Object,Object>(); //动态数据

        public static <T> Result<T> success(T object) {
            Result<T> r = new Result<>();
            r.data = object;
            r.status = 0;
            r.msg="查询成功";
            return r;
        }
        public static <T> Result<T> success(String msg,T object) {
            Result<T> r = new Result<>();
            r.data = object;
            r.status = 0;
            r.msg=msg;
            return r;
        }

        public static <T> Result<T> error(String msg) {
            Result<T> r = new Result<>();
            r.msg = msg;
            r.status = 1;
            return r;
        }

        public Result<T> add(String key, Object value) {
            this.map.put(key, value);
            return this;
        }

        public Result(Integer code, String msg, T data, Map<Object,Object> map) {
            this.status = code;
            this.msg = msg;
            this.data = data;
            this.map = map;
        }
        public Result() {
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Map<Object,Object> getMap() {
            return map;
        }

        public void setMap(Map<Object,Object> map) {
            this.map = map;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "code=" + status +
                    ", message='" + msg + '\'' +
                    ", data=" + data +
                    ", map=" + map +
                    '}';
        }
    }
