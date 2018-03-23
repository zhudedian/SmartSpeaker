package com.lingan.edongspeechlibrary.bean;

import java.util.List;

/**
 * Created by Done on 2018/3/22.
 */

public class NetFM3Bean {
    /**
     * version : 2.9.4
     * applicationId : 151244661445834f
     * recordId : 5ab36ad03327930322000016
     * luabin : 0.5.2
     * result : {"semantics":{"request":{"slotcount":3,"domain":"电台","action":"电台","param":{"电台":"广东羊城交通台","keyword":"广东羊城交通台","keyword_pinyin":"guang dong yang cheng jiao tong tai"}}},"systime":100.26708984375,"input":"我想听广东羊城交通台","sds":{"state":"do","log":{"input":{"uacts":[{"slot_val":"广东羊城交通台","slot_name":"station","act_type":"inform","conf":1},{"slot_val":"广东羊城交通台","slot_name":"keyword","act_type":"inform","conf":1}],"nlu":{"input":"我想听广东羊城交通台","semantics":{"request":{"slotcount":3,"domain":"电台","action":"电台","param":{"电台":"广东羊城交通台","keyword":"广东羊城交通台","keyword_pinyin":"guang dong yang cheng jiao tong tai"}}},"res":"aihome.0.16.5_0.14.13","systime":100.26708984375}},"domain":"netfm","output":{"macts":[{"slot_val":{"program_name":"汽车玩家","radio_name":"广东羊城交通台"},"slot_name":"entity","act_type":"offer","conf":1},{"act_type":"do","conf":1}],"nlg":"准备播放汽车玩家广东羊城交通台"}},"version":"hnp-v1.2.2:[hnp:hnp-dev][res:20171211-17:26][src:20171211-17:26]","contextId":"4cd960612ef2bb002601c193","nlu":{"semantics":{"request":{"slotcount":3,"domain":"电台","action":"电台","param":{"电台":"广东羊城交通台","keyword":"广东羊城交通台","keyword_pinyin":"guang dong yang cheng jiao tong tai"}}}},"data":{"dbdata":[{"program_name":"汽车玩家","radio_name":"广东羊城交通台","support_bitrates":[24,64],"rate64_aac_url":"http://live.xmcdn.com/live/248/64.m3u8","id":248,"cover_url_large":"http://fdfs.xmcdn.com/group28/M07/31/31/wKgJXFkxQciQeAx-AAA2ZPc-qOo178_mobile_large.jpg","created_at":0,"radio_play_count":1469837,"radio_desc":"","rate24_ts_url":"http://live.xmcdn.com/live/248/24.m3u8?transcode=ts","rate24_aac_url":"http://live.xmcdn.com/live/248/24.m3u8","rate64_ts_url":"http://live.xmcdn.com/live/248/64.m3u8?transcode=ts","cover_url_small":"http://fdfs.xmcdn.com/group28/M07/31/31/wKgJXFkxQciQeAx-AAA2ZPc-qOo178_mobile_small.jpg","kind":"radio","updated_at":0,"schedule_id":171175}]},"domain":"netfm","output":"准备播放汽车玩家广东羊城交通台"},"res":"aihome.0.16.5_0.14.13"}
     * eof : 1
     */

    private String version;
    private String applicationId;
    private String recordId;
    private String luabin;
    private ResultBean result;
    private int eof;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getLuabin() {
        return luabin;
    }

    public void setLuabin(String luabin) {
        this.luabin = luabin;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getEof() {
        return eof;
    }

    public void setEof(int eof) {
        this.eof = eof;
    }

    public static class ResultBean {
        /**
         * semantics : {"request":{"slotcount":3,"domain":"电台","action":"电台","param":{"电台":"广东羊城交通台","keyword":"广东羊城交通台","keyword_pinyin":"guang dong yang cheng jiao tong tai"}}}
         * systime : 100.26708984375
         * input : 我想听广东羊城交通台
         * sds : {"state":"do","log":{"input":{"uacts":[{"slot_val":"广东羊城交通台","slot_name":"station","act_type":"inform","conf":1},{"slot_val":"广东羊城交通台","slot_name":"keyword","act_type":"inform","conf":1}],"nlu":{"input":"我想听广东羊城交通台","semantics":{"request":{"slotcount":3,"domain":"电台","action":"电台","param":{"电台":"广东羊城交通台","keyword":"广东羊城交通台","keyword_pinyin":"guang dong yang cheng jiao tong tai"}}},"res":"aihome.0.16.5_0.14.13","systime":100.26708984375}},"domain":"netfm","output":{"macts":[{"slot_val":{"program_name":"汽车玩家","radio_name":"广东羊城交通台"},"slot_name":"entity","act_type":"offer","conf":1},{"act_type":"do","conf":1}],"nlg":"准备播放汽车玩家广东羊城交通台"}},"version":"hnp-v1.2.2:[hnp:hnp-dev][res:20171211-17:26][src:20171211-17:26]","contextId":"4cd960612ef2bb002601c193","nlu":{"semantics":{"request":{"slotcount":3,"domain":"电台","action":"电台","param":{"电台":"广东羊城交通台","keyword":"广东羊城交通台","keyword_pinyin":"guang dong yang cheng jiao tong tai"}}}},"data":{"dbdata":[{"program_name":"汽车玩家","radio_name":"广东羊城交通台","support_bitrates":[24,64],"rate64_aac_url":"http://live.xmcdn.com/live/248/64.m3u8","id":248,"cover_url_large":"http://fdfs.xmcdn.com/group28/M07/31/31/wKgJXFkxQciQeAx-AAA2ZPc-qOo178_mobile_large.jpg","created_at":0,"radio_play_count":1469837,"radio_desc":"","rate24_ts_url":"http://live.xmcdn.com/live/248/24.m3u8?transcode=ts","rate24_aac_url":"http://live.xmcdn.com/live/248/24.m3u8","rate64_ts_url":"http://live.xmcdn.com/live/248/64.m3u8?transcode=ts","cover_url_small":"http://fdfs.xmcdn.com/group28/M07/31/31/wKgJXFkxQciQeAx-AAA2ZPc-qOo178_mobile_small.jpg","kind":"radio","updated_at":0,"schedule_id":171175}]},"domain":"netfm","output":"准备播放汽车玩家广东羊城交通台"}
         * res : aihome.0.16.5_0.14.13
         */

        private SemanticsBean semantics;
        private double systime;
        private String input;
        private SdsBean sds;
        private String res;

        public SemanticsBean getSemantics() {
            return semantics;
        }

        public void setSemantics(SemanticsBean semantics) {
            this.semantics = semantics;
        }

        public double getSystime() {
            return systime;
        }

        public void setSystime(double systime) {
            this.systime = systime;
        }

        public String getInput() {
            return input;
        }

        public void setInput(String input) {
            this.input = input;
        }

        public SdsBean getSds() {
            return sds;
        }

        public void setSds(SdsBean sds) {
            this.sds = sds;
        }

        public String getRes() {
            return res;
        }

        public void setRes(String res) {
            this.res = res;
        }

        public static class SemanticsBean {
            /**
             * request : {"slotcount":3,"domain":"电台","action":"电台","param":{"电台":"广东羊城交通台","keyword":"广东羊城交通台","keyword_pinyin":"guang dong yang cheng jiao tong tai"}}
             */

            private RequestBean request;

            public RequestBean getRequest() {
                return request;
            }

            public void setRequest(RequestBean request) {
                this.request = request;
            }

            public static class RequestBean {
                /**
                 * slotcount : 3
                 * domain : 电台
                 * action : 电台
                 * param : {"电台":"广东羊城交通台","keyword":"广东羊城交通台","keyword_pinyin":"guang dong yang cheng jiao tong tai"}
                 */

                private int slotcount;
                private String domain;
                private String action;
                private ParamBean param;

                public int getSlotcount() {
                    return slotcount;
                }

                public void setSlotcount(int slotcount) {
                    this.slotcount = slotcount;
                }

                public String getDomain() {
                    return domain;
                }

                public void setDomain(String domain) {
                    this.domain = domain;
                }

                public String getAction() {
                    return action;
                }

                public void setAction(String action) {
                    this.action = action;
                }

                public ParamBean getParam() {
                    return param;
                }

                public void setParam(ParamBean param) {
                    this.param = param;
                }

                public static class ParamBean {
                    /**
                     * 电台 : 广东羊城交通台
                     * keyword : 广东羊城交通台
                     * keyword_pinyin : guang dong yang cheng jiao tong tai
                     */

                    private String 电台;
                    private String keyword;
                    private String keyword_pinyin;

                    public String get电台() {
                        return 电台;
                    }

                    public void set电台(String 电台) {
                        this.电台 = 电台;
                    }

                    public String getKeyword() {
                        return keyword;
                    }

                    public void setKeyword(String keyword) {
                        this.keyword = keyword;
                    }

                    public String getKeyword_pinyin() {
                        return keyword_pinyin;
                    }

                    public void setKeyword_pinyin(String keyword_pinyin) {
                        this.keyword_pinyin = keyword_pinyin;
                    }
                }
            }
        }

        public static class SdsBean {
            /**
             * state : do
             * log : {"input":{"uacts":[{"slot_val":"广东羊城交通台","slot_name":"station","act_type":"inform","conf":1},{"slot_val":"广东羊城交通台","slot_name":"keyword","act_type":"inform","conf":1}],"nlu":{"input":"我想听广东羊城交通台","semantics":{"request":{"slotcount":3,"domain":"电台","action":"电台","param":{"电台":"广东羊城交通台","keyword":"广东羊城交通台","keyword_pinyin":"guang dong yang cheng jiao tong tai"}}},"res":"aihome.0.16.5_0.14.13","systime":100.26708984375}},"domain":"netfm","output":{"macts":[{"slot_val":{"program_name":"汽车玩家","radio_name":"广东羊城交通台"},"slot_name":"entity","act_type":"offer","conf":1},{"act_type":"do","conf":1}],"nlg":"准备播放汽车玩家广东羊城交通台"}}
             * version : hnp-v1.2.2:[hnp:hnp-dev][res:20171211-17:26][src:20171211-17:26]
             * contextId : 4cd960612ef2bb002601c193
             * nlu : {"semantics":{"request":{"slotcount":3,"domain":"电台","action":"电台","param":{"电台":"广东羊城交通台","keyword":"广东羊城交通台","keyword_pinyin":"guang dong yang cheng jiao tong tai"}}}}
             * data : {"dbdata":[{"program_name":"汽车玩家","radio_name":"广东羊城交通台","support_bitrates":[24,64],"rate64_aac_url":"http://live.xmcdn.com/live/248/64.m3u8","id":248,"cover_url_large":"http://fdfs.xmcdn.com/group28/M07/31/31/wKgJXFkxQciQeAx-AAA2ZPc-qOo178_mobile_large.jpg","created_at":0,"radio_play_count":1469837,"radio_desc":"","rate24_ts_url":"http://live.xmcdn.com/live/248/24.m3u8?transcode=ts","rate24_aac_url":"http://live.xmcdn.com/live/248/24.m3u8","rate64_ts_url":"http://live.xmcdn.com/live/248/64.m3u8?transcode=ts","cover_url_small":"http://fdfs.xmcdn.com/group28/M07/31/31/wKgJXFkxQciQeAx-AAA2ZPc-qOo178_mobile_small.jpg","kind":"radio","updated_at":0,"schedule_id":171175}]}
             * domain : netfm
             * output : 准备播放汽车玩家广东羊城交通台
             */

            private String state;
            private LogBean log;
            private String version;
            private String contextId;
            private NluBeanX nlu;
            private DataBean data;
            private String domain;
            private String output;

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public LogBean getLog() {
                return log;
            }

            public void setLog(LogBean log) {
                this.log = log;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public String getContextId() {
                return contextId;
            }

            public void setContextId(String contextId) {
                this.contextId = contextId;
            }

            public NluBeanX getNlu() {
                return nlu;
            }

            public void setNlu(NluBeanX nlu) {
                this.nlu = nlu;
            }

            public DataBean getData() {
                return data;
            }

            public void setData(DataBean data) {
                this.data = data;
            }

            public String getDomain() {
                return domain;
            }

            public void setDomain(String domain) {
                this.domain = domain;
            }

            public String getOutput() {
                return output;
            }

            public void setOutput(String output) {
                this.output = output;
            }

            public static class LogBean {
                /**
                 * input : {"uacts":[{"slot_val":"广东羊城交通台","slot_name":"station","act_type":"inform","conf":1},{"slot_val":"广东羊城交通台","slot_name":"keyword","act_type":"inform","conf":1}],"nlu":{"input":"我想听广东羊城交通台","semantics":{"request":{"slotcount":3,"domain":"电台","action":"电台","param":{"电台":"广东羊城交通台","keyword":"广东羊城交通台","keyword_pinyin":"guang dong yang cheng jiao tong tai"}}},"res":"aihome.0.16.5_0.14.13","systime":100.26708984375}}
                 * domain : netfm
                 * output : {"macts":[{"slot_val":{"program_name":"汽车玩家","radio_name":"广东羊城交通台"},"slot_name":"entity","act_type":"offer","conf":1},{"act_type":"do","conf":1}],"nlg":"准备播放汽车玩家广东羊城交通台"}
                 */

                private InputBean input;
                private String domain;
                private OutputBean output;

                public InputBean getInput() {
                    return input;
                }

                public void setInput(InputBean input) {
                    this.input = input;
                }

                public String getDomain() {
                    return domain;
                }

                public void setDomain(String domain) {
                    this.domain = domain;
                }

                public OutputBean getOutput() {
                    return output;
                }

                public void setOutput(OutputBean output) {
                    this.output = output;
                }

                public static class InputBean {
                    /**
                     * uacts : [{"slot_val":"广东羊城交通台","slot_name":"station","act_type":"inform","conf":1},{"slot_val":"广东羊城交通台","slot_name":"keyword","act_type":"inform","conf":1}]
                     * nlu : {"input":"我想听广东羊城交通台","semantics":{"request":{"slotcount":3,"domain":"电台","action":"电台","param":{"电台":"广东羊城交通台","keyword":"广东羊城交通台","keyword_pinyin":"guang dong yang cheng jiao tong tai"}}},"res":"aihome.0.16.5_0.14.13","systime":100.26708984375}
                     */

                    private NluBean nlu;
                    private List<UactsBean> uacts;

                    public NluBean getNlu() {
                        return nlu;
                    }

                    public void setNlu(NluBean nlu) {
                        this.nlu = nlu;
                    }

                    public List<UactsBean> getUacts() {
                        return uacts;
                    }

                    public void setUacts(List<UactsBean> uacts) {
                        this.uacts = uacts;
                    }

                    public static class NluBean {
                        /**
                         * input : 我想听广东羊城交通台
                         * semantics : {"request":{"slotcount":3,"domain":"电台","action":"电台","param":{"电台":"广东羊城交通台","keyword":"广东羊城交通台","keyword_pinyin":"guang dong yang cheng jiao tong tai"}}}
                         * res : aihome.0.16.5_0.14.13
                         * systime : 100.26708984375
                         */

                        private String input;
                        private SemanticsBeanX semantics;
                        private String res;
                        private double systime;

                        public String getInput() {
                            return input;
                        }

                        public void setInput(String input) {
                            this.input = input;
                        }

                        public SemanticsBeanX getSemantics() {
                            return semantics;
                        }

                        public void setSemantics(SemanticsBeanX semantics) {
                            this.semantics = semantics;
                        }

                        public String getRes() {
                            return res;
                        }

                        public void setRes(String res) {
                            this.res = res;
                        }

                        public double getSystime() {
                            return systime;
                        }

                        public void setSystime(double systime) {
                            this.systime = systime;
                        }

                        public static class SemanticsBeanX {
                            /**
                             * request : {"slotcount":3,"domain":"电台","action":"电台","param":{"电台":"广东羊城交通台","keyword":"广东羊城交通台","keyword_pinyin":"guang dong yang cheng jiao tong tai"}}
                             */

                            private RequestBeanX request;

                            public RequestBeanX getRequest() {
                                return request;
                            }

                            public void setRequest(RequestBeanX request) {
                                this.request = request;
                            }

                            public static class RequestBeanX {
                                /**
                                 * slotcount : 3
                                 * domain : 电台
                                 * action : 电台
                                 * param : {"电台":"广东羊城交通台","keyword":"广东羊城交通台","keyword_pinyin":"guang dong yang cheng jiao tong tai"}
                                 */

                                private int slotcount;
                                private String domain;
                                private String action;
                                private ParamBeanX param;

                                public int getSlotcount() {
                                    return slotcount;
                                }

                                public void setSlotcount(int slotcount) {
                                    this.slotcount = slotcount;
                                }

                                public String getDomain() {
                                    return domain;
                                }

                                public void setDomain(String domain) {
                                    this.domain = domain;
                                }

                                public String getAction() {
                                    return action;
                                }

                                public void setAction(String action) {
                                    this.action = action;
                                }

                                public ParamBeanX getParam() {
                                    return param;
                                }

                                public void setParam(ParamBeanX param) {
                                    this.param = param;
                                }

                                public static class ParamBeanX {
                                    /**
                                     * 电台 : 广东羊城交通台
                                     * keyword : 广东羊城交通台
                                     * keyword_pinyin : guang dong yang cheng jiao tong tai
                                     */

                                    private String 电台;
                                    private String keyword;
                                    private String keyword_pinyin;

                                    public String get电台() {
                                        return 电台;
                                    }

                                    public void set电台(String 电台) {
                                        this.电台 = 电台;
                                    }

                                    public String getKeyword() {
                                        return keyword;
                                    }

                                    public void setKeyword(String keyword) {
                                        this.keyword = keyword;
                                    }

                                    public String getKeyword_pinyin() {
                                        return keyword_pinyin;
                                    }

                                    public void setKeyword_pinyin(String keyword_pinyin) {
                                        this.keyword_pinyin = keyword_pinyin;
                                    }
                                }
                            }
                        }
                    }

                    public static class UactsBean {
                        /**
                         * slot_val : 广东羊城交通台
                         * slot_name : station
                         * act_type : inform
                         * conf : 1
                         */

                        private String slot_val;
                        private String slot_name;
                        private String act_type;
                        private int conf;

                        public String getSlot_val() {
                            return slot_val;
                        }

                        public void setSlot_val(String slot_val) {
                            this.slot_val = slot_val;
                        }

                        public String getSlot_name() {
                            return slot_name;
                        }

                        public void setSlot_name(String slot_name) {
                            this.slot_name = slot_name;
                        }

                        public String getAct_type() {
                            return act_type;
                        }

                        public void setAct_type(String act_type) {
                            this.act_type = act_type;
                        }

                        public int getConf() {
                            return conf;
                        }

                        public void setConf(int conf) {
                            this.conf = conf;
                        }
                    }
                }

                public static class OutputBean {
                    /**
                     * macts : [{"slot_val":{"program_name":"汽车玩家","radio_name":"广东羊城交通台"},"slot_name":"entity","act_type":"offer","conf":1},{"act_type":"do","conf":1}]
                     * nlg : 准备播放汽车玩家广东羊城交通台
                     */

                    private String nlg;
                    private List<MactsBean> macts;

                    public String getNlg() {
                        return nlg;
                    }

                    public void setNlg(String nlg) {
                        this.nlg = nlg;
                    }

                    public List<MactsBean> getMacts() {
                        return macts;
                    }

                    public void setMacts(List<MactsBean> macts) {
                        this.macts = macts;
                    }

                    public static class MactsBean {
                        /**
                         * slot_val : {"program_name":"汽车玩家","radio_name":"广东羊城交通台"}
                         * slot_name : entity
                         * act_type : offer
                         * conf : 1
                         */

                        private SlotValBean slot_val;
                        private String slot_name;
                        private String act_type;
                        private int conf;

                        public SlotValBean getSlot_val() {
                            return slot_val;
                        }

                        public void setSlot_val(SlotValBean slot_val) {
                            this.slot_val = slot_val;
                        }

                        public String getSlot_name() {
                            return slot_name;
                        }

                        public void setSlot_name(String slot_name) {
                            this.slot_name = slot_name;
                        }

                        public String getAct_type() {
                            return act_type;
                        }

                        public void setAct_type(String act_type) {
                            this.act_type = act_type;
                        }

                        public int getConf() {
                            return conf;
                        }

                        public void setConf(int conf) {
                            this.conf = conf;
                        }

                        public static class SlotValBean {
                            /**
                             * program_name : 汽车玩家
                             * radio_name : 广东羊城交通台
                             */

                            private String program_name;
                            private String radio_name;

                            public String getProgram_name() {
                                return program_name;
                            }

                            public void setProgram_name(String program_name) {
                                this.program_name = program_name;
                            }

                            public String getRadio_name() {
                                return radio_name;
                            }

                            public void setRadio_name(String radio_name) {
                                this.radio_name = radio_name;
                            }
                        }
                    }
                }
            }

            public static class NluBeanX {
                /**
                 * semantics : {"request":{"slotcount":3,"domain":"电台","action":"电台","param":{"电台":"广东羊城交通台","keyword":"广东羊城交通台","keyword_pinyin":"guang dong yang cheng jiao tong tai"}}}
                 */

                private SemanticsBeanXX semantics;

                public SemanticsBeanXX getSemantics() {
                    return semantics;
                }

                public void setSemantics(SemanticsBeanXX semantics) {
                    this.semantics = semantics;
                }

                public static class SemanticsBeanXX {
                    /**
                     * request : {"slotcount":3,"domain":"电台","action":"电台","param":{"电台":"广东羊城交通台","keyword":"广东羊城交通台","keyword_pinyin":"guang dong yang cheng jiao tong tai"}}
                     */

                    private RequestBeanXX request;

                    public RequestBeanXX getRequest() {
                        return request;
                    }

                    public void setRequest(RequestBeanXX request) {
                        this.request = request;
                    }

                    public static class RequestBeanXX {
                        /**
                         * slotcount : 3
                         * domain : 电台
                         * action : 电台
                         * param : {"电台":"广东羊城交通台","keyword":"广东羊城交通台","keyword_pinyin":"guang dong yang cheng jiao tong tai"}
                         */

                        private int slotcount;
                        private String domain;
                        private String action;
                        private ParamBeanXX param;

                        public int getSlotcount() {
                            return slotcount;
                        }

                        public void setSlotcount(int slotcount) {
                            this.slotcount = slotcount;
                        }

                        public String getDomain() {
                            return domain;
                        }

                        public void setDomain(String domain) {
                            this.domain = domain;
                        }

                        public String getAction() {
                            return action;
                        }

                        public void setAction(String action) {
                            this.action = action;
                        }

                        public ParamBeanXX getParam() {
                            return param;
                        }

                        public void setParam(ParamBeanXX param) {
                            this.param = param;
                        }

                        public static class ParamBeanXX {
                            /**
                             * 电台 : 广东羊城交通台
                             * keyword : 广东羊城交通台
                             * keyword_pinyin : guang dong yang cheng jiao tong tai
                             */

                            private String 电台;
                            private String keyword;
                            private String keyword_pinyin;

                            public String get电台() {
                                return 电台;
                            }

                            public void set电台(String 电台) {
                                this.电台 = 电台;
                            }

                            public String getKeyword() {
                                return keyword;
                            }

                            public void setKeyword(String keyword) {
                                this.keyword = keyword;
                            }

                            public String getKeyword_pinyin() {
                                return keyword_pinyin;
                            }

                            public void setKeyword_pinyin(String keyword_pinyin) {
                                this.keyword_pinyin = keyword_pinyin;
                            }
                        }
                    }
                }
            }

            public static class DataBean {
                private List<DbdataBean> dbdata;

                public List<DbdataBean> getDbdata() {
                    return dbdata;
                }

                public void setDbdata(List<DbdataBean> dbdata) {
                    this.dbdata = dbdata;
                }

                public static class DbdataBean {
                    /**
                     * program_name : 汽车玩家
                     * radio_name : 广东羊城交通台
                     * support_bitrates : [24,64]
                     * rate64_aac_url : http://live.xmcdn.com/live/248/64.m3u8
                     * id : 248
                     * cover_url_large : http://fdfs.xmcdn.com/group28/M07/31/31/wKgJXFkxQciQeAx-AAA2ZPc-qOo178_mobile_large.jpg
                     * created_at : 0
                     * radio_play_count : 1469837
                     * radio_desc :
                     * rate24_ts_url : http://live.xmcdn.com/live/248/24.m3u8?transcode=ts
                     * rate24_aac_url : http://live.xmcdn.com/live/248/24.m3u8
                     * rate64_ts_url : http://live.xmcdn.com/live/248/64.m3u8?transcode=ts
                     * cover_url_small : http://fdfs.xmcdn.com/group28/M07/31/31/wKgJXFkxQciQeAx-AAA2ZPc-qOo178_mobile_small.jpg
                     * kind : radio
                     * updated_at : 0
                     * schedule_id : 171175
                     */

                    private String program_name;
                    private String radio_name;
                    private String rate64_aac_url;
                    private int id;
                    private String cover_url_large;
                    private int created_at;
                    private int radio_play_count;
                    private String radio_desc;
                    private String rate24_ts_url;
                    private String rate24_aac_url;
                    private String rate64_ts_url;
                    private String cover_url_small;
                    private String kind;
                    private int updated_at;
                    private int schedule_id;
                    private List<Integer> support_bitrates;

                    public String getProgram_name() {
                        return program_name;
                    }

                    public void setProgram_name(String program_name) {
                        this.program_name = program_name;
                    }

                    public String getRadio_name() {
                        return radio_name;
                    }

                    public void setRadio_name(String radio_name) {
                        this.radio_name = radio_name;
                    }

                    public String getRate64_aac_url() {
                        return rate64_aac_url;
                    }

                    public void setRate64_aac_url(String rate64_aac_url) {
                        this.rate64_aac_url = rate64_aac_url;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getCover_url_large() {
                        return cover_url_large;
                    }

                    public void setCover_url_large(String cover_url_large) {
                        this.cover_url_large = cover_url_large;
                    }

                    public int getCreated_at() {
                        return created_at;
                    }

                    public void setCreated_at(int created_at) {
                        this.created_at = created_at;
                    }

                    public int getRadio_play_count() {
                        return radio_play_count;
                    }

                    public void setRadio_play_count(int radio_play_count) {
                        this.radio_play_count = radio_play_count;
                    }

                    public String getRadio_desc() {
                        return radio_desc;
                    }

                    public void setRadio_desc(String radio_desc) {
                        this.radio_desc = radio_desc;
                    }

                    public String getRate24_ts_url() {
                        return rate24_ts_url;
                    }

                    public void setRate24_ts_url(String rate24_ts_url) {
                        this.rate24_ts_url = rate24_ts_url;
                    }

                    public String getRate24_aac_url() {
                        return rate24_aac_url;
                    }

                    public void setRate24_aac_url(String rate24_aac_url) {
                        this.rate24_aac_url = rate24_aac_url;
                    }

                    public String getRate64_ts_url() {
                        return rate64_ts_url;
                    }

                    public void setRate64_ts_url(String rate64_ts_url) {
                        this.rate64_ts_url = rate64_ts_url;
                    }

                    public String getCover_url_small() {
                        return cover_url_small;
                    }

                    public void setCover_url_small(String cover_url_small) {
                        this.cover_url_small = cover_url_small;
                    }

                    public String getKind() {
                        return kind;
                    }

                    public void setKind(String kind) {
                        this.kind = kind;
                    }

                    public int getUpdated_at() {
                        return updated_at;
                    }

                    public void setUpdated_at(int updated_at) {
                        this.updated_at = updated_at;
                    }

                    public int getSchedule_id() {
                        return schedule_id;
                    }

                    public void setSchedule_id(int schedule_id) {
                        this.schedule_id = schedule_id;
                    }

                    public List<Integer> getSupport_bitrates() {
                        return support_bitrates;
                    }

                    public void setSupport_bitrates(List<Integer> support_bitrates) {
                        this.support_bitrates = support_bitrates;
                    }
                }
            }
        }
    }
}
