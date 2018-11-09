package jh.zkj.com.yf.Bean;

import java.util.ArrayList;

/**
 * Created by wdefer
 * 2018/11/8
 * use 首页menu
 */
public class HomeMenuBean {


    /**
     * favoriteMenus : null
     * menus : {"id":"-1","text":"顶级节点","state":"open","checked":true,"attributes":null,"children":[{"id":"39e1d80d-2e4e-4821-82b3-5c2be0e4610f","text":"库存管理","state":"open","checked":false,"attributes":[{"path":"stockManage","prefixPower":"stockManage"}],"children":[{"id":"0ed31b73-4801-4482-a61a-858f8a57789a","text":"库存查询","state":"open","checked":false,"attributes":[{"path":"stockSelect","prefixPower":"stockSelect"}],"children":[],"parentId":"39e1d80d-2e4e-4821-82b3-5c2be0e4610f","parent":true}],"parentId":null,"parent":false},{"id":"dbd409e7-944d-4723-8ef2-76cec1d5a42d","text":"销售","state":"open","checked":false,"attributes":[{"path":"sale","prefixPower":"sale"}],"children":[{"id":"5c19f64c-ee2c-445f-8dec-676985661213","text":"零售下单","state":"open","checked":false,"attributes":[{"path":"soApp","prefixPower":"soApp"}],"children":[],"parentId":"dbd409e7-944d-4723-8ef2-76cec1d5a42d","parent":true},{"id":"7bd3ac3a-5bcb-45e2-979c-396e618e568c","text":"零售查询","state":"open","checked":false,"attributes":[{"path":"soSelect","prefixPower":"soSelect"}],"children":[],"parentId":"dbd409e7-944d-4723-8ef2-76cec1d5a42d","parent":true}],"parentId":null,"parent":false},{"id":"e04229c8-1e24-44a2-820e-9333bfd920da","text":"统计分析","state":"open","checked":false,"attributes":[{"path":" statisticalAnalysis","prefixPower":"statisticalAnalysis"}],"children":[{"id":"26fc0dff-3a60-4f6c-a336-41c650a9ab1b","text":"经营分析","state":"open","checked":false,"attributes":[{"path":"operationAnalysis","prefixPower":"operationAnalysis"}],"children":[],"parentId":"e04229c8-1e24-44a2-820e-9333bfd920da","parent":true}],"parentId":null,"parent":false}],"parentId":"","parent":false}
     */

    private Object favoriteMenus;
    private MenusBean menus;

    public Object getFavoriteMenus() {
        return favoriteMenus;
    }

    public void setFavoriteMenus(Object favoriteMenus) {
        this.favoriteMenus = favoriteMenus;
    }

    public MenusBean getMenus() {
        return menus;
    }

    public void setMenus(MenusBean menus) {
        this.menus = menus;
    }

    public static class MenusBean {
        /**
         * id : -1
         * text : 顶级节点
         * state : open
         * checked : true
         * attributes : null
         * children : [{"id":"39e1d80d-2e4e-4821-82b3-5c2be0e4610f","text":"库存管理","state":"open","checked":false,"attributes":[{"path":"stockManage","prefixPower":"stockManage"}],"children":[{"id":"0ed31b73-4801-4482-a61a-858f8a57789a","text":"库存查询","state":"open","checked":false,"attributes":[{"path":"stockSelect","prefixPower":"stockSelect"}],"children":[],"parentId":"39e1d80d-2e4e-4821-82b3-5c2be0e4610f","parent":true}],"parentId":null,"parent":false},{"id":"dbd409e7-944d-4723-8ef2-76cec1d5a42d","text":"销售","state":"open","checked":false,"attributes":[{"path":"sale","prefixPower":"sale"}],"children":[{"id":"5c19f64c-ee2c-445f-8dec-676985661213","text":"零售下单","state":"open","checked":false,"attributes":[{"path":"soApp","prefixPower":"soApp"}],"children":[],"parentId":"dbd409e7-944d-4723-8ef2-76cec1d5a42d","parent":true},{"id":"7bd3ac3a-5bcb-45e2-979c-396e618e568c","text":"零售查询","state":"open","checked":false,"attributes":[{"path":"soSelect","prefixPower":"soSelect"}],"children":[],"parentId":"dbd409e7-944d-4723-8ef2-76cec1d5a42d","parent":true}],"parentId":null,"parent":false},{"id":"e04229c8-1e24-44a2-820e-9333bfd920da","text":"统计分析","state":"open","checked":false,"attributes":[{"path":" statisticalAnalysis","prefixPower":"statisticalAnalysis"}],"children":[{"id":"26fc0dff-3a60-4f6c-a336-41c650a9ab1b","text":"经营分析","state":"open","checked":false,"attributes":[{"path":"operationAnalysis","prefixPower":"operationAnalysis"}],"children":[],"parentId":"e04229c8-1e24-44a2-820e-9333bfd920da","parent":true}],"parentId":null,"parent":false}]
         * parentId :
         * parent : false
         */

        private String id;
        private String text;
        private String state;
        private boolean checked;
        private Object attributes;
        private String parentId;
        private boolean parent;
        private ArrayList<ChildrenBeanX> children;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        public Object getAttributes() {
            return attributes;
        }

        public void setAttributes(Object attributes) {
            this.attributes = attributes;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public boolean isParent() {
            return parent;
        }

        public void setParent(boolean parent) {
            this.parent = parent;
        }

        public ArrayList<ChildrenBeanX> getChildren() {
            return children;
        }

        public void setChildren(ArrayList<ChildrenBeanX> children) {
            this.children = children;
        }

        public static class ChildrenBeanX {
            /**
             * id : 39e1d80d-2e4e-4821-82b3-5c2be0e4610f
             * text : 库存管理
             * state : open
             * checked : false
             * attributes : [{"path":"stockManage","prefixPower":"stockManage"}]
             * children : [{"id":"0ed31b73-4801-4482-a61a-858f8a57789a","text":"库存查询","state":"open","checked":false,"attributes":[{"path":"stockSelect","prefixPower":"stockSelect"}],"children":[],"parentId":"39e1d80d-2e4e-4821-82b3-5c2be0e4610f","parent":true}]
             * parentId : null
             * parent : false
             */

            private String id;
            private String text;
            private String state;
            private boolean checked;
            private Object parentId;
            private boolean parent;
            private ArrayList<AttributesBean> attributes;
            private ArrayList<ChildrenBean> children;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public boolean isChecked() {
                return checked;
            }

            public void setChecked(boolean checked) {
                this.checked = checked;
            }

            public Object getParentId() {
                return parentId;
            }

            public void setParentId(Object parentId) {
                this.parentId = parentId;
            }

            public boolean isParent() {
                return parent;
            }

            public void setParent(boolean parent) {
                this.parent = parent;
            }

            public ArrayList<AttributesBean> getAttributes() {
                return attributes;
            }

            public void setAttributes(ArrayList<AttributesBean> attributes) {
                this.attributes = attributes;
            }

            public ArrayList<ChildrenBean> getChildren() {
                return children;
            }

            public void setChildren(ArrayList<ChildrenBean> children) {
                this.children = children;
            }

            public static class AttributesBean {
                /**
                 * path : stockManage
                 * prefixPower : stockManage
                 */

                private String path;
                private String prefixPower;

                public String getPath() {
                    return path;
                }

                public void setPath(String path) {
                    this.path = path;
                }

                public String getPrefixPower() {
                    return prefixPower;
                }

                public void setPrefixPower(String prefixPower) {
                    this.prefixPower = prefixPower;
                }
            }

            public static class ChildrenBean {
                /**
                 * id : 0ed31b73-4801-4482-a61a-858f8a57789a
                 * text : 库存查询
                 * state : open
                 * checked : false
                 * attributes : [{"path":"stockSelect","prefixPower":"stockSelect"}]
                 * children : []
                 * parentId : 39e1d80d-2e4e-4821-82b3-5c2be0e4610f
                 * parent : true
                 */

                private String id;
                private String text;
                private String state;
                private boolean checked;
                private String parentId;
                private boolean parent;
                private ArrayList<AttributesBeanX> attributes;
                private ArrayList<?> children;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public String getState() {
                    return state;
                }

                public void setState(String state) {
                    this.state = state;
                }

                public boolean isChecked() {
                    return checked;
                }

                public void setChecked(boolean checked) {
                    this.checked = checked;
                }

                public String getParentId() {
                    return parentId;
                }

                public void setParentId(String parentId) {
                    this.parentId = parentId;
                }

                public boolean isParent() {
                    return parent;
                }

                public void setParent(boolean parent) {
                    this.parent = parent;
                }

                public ArrayList<AttributesBeanX> getAttributes() {
                    return attributes;
                }

                public void setAttributes(ArrayList<AttributesBeanX> attributes) {
                    this.attributes = attributes;
                }

                public ArrayList<?> getChildren() {
                    return children;
                }

                public void setChildren(ArrayList<?> children) {
                    this.children = children;
                }

                public static class AttributesBeanX {
                    /**
                     * path : stockSelect
                     * prefixPower : stockSelect
                     */

                    private String path;
                    private String prefixPower;

                    public String getPath() {
                        return path;
                    }

                    public void setPath(String path) {
                        this.path = path;
                    }

                    public String getPrefixPower() {
                        return prefixPower;
                    }

                    public void setPrefixPower(String prefixPower) {
                        this.prefixPower = prefixPower;
                    }
                }
            }
        }
    }
}
