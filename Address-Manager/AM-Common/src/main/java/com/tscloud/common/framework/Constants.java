
package com.tscloud.common.framework;

import java.util.ArrayList;
import java.util.List;

/**
 * EnvConstants.java
 */
public final class Constants {

    public final class Env {

        private Env() {
        }

        public static final String BASE_HOME = "configs/";

        public static final String BASE_CONF = "base.conf";
    }

    public final class RestPathPrefix {
        public static final String CONSOLE = "console/";
        public static final String GATHER = "gather/";
        public static final String DATABASE = "database/";
        public static final String GISSERVER = "gisserver/";
        public static final String ETL = "etl/";
        public static final String SERVICES = "services/";
        public static final String SYSTEM = "system/";
        public static final String DATANODE = "datanode/";
        public static final String WEBCRAWLER = "webcrawler/";
        //ESB常量
        public static final String ESB = "esb/";
        //portal门户常量
        public static final String PORTAL = "portal/";
        //内网门户
        public static final String IN_PORTAL = "inportal/";
        //外网门户常量
        public static final String OUT_PORTAL = "outportal/";
        public static final String WORKFLOW = "workflow/";
        public static final String WORKFLOW_SERVER = "workflowServer/";
        public static final String FILEGATHER = "filegather/";
        public static final String MANAGER = "manager/";
        public static final String CA = "ca/";
        public static final String GISMANAGER = "gismanager/";
    }

    /**
     * 服务标识
     */
    public enum ServiceName {
        CONSOLE//,ETL,ESB,DATABASE,ISWAP;
    }


    /**
     * 引擎状态
     */
    public final class engine {
        public static final String ENGINE_TYPE_DISABLE = "0";
        public static final String ENGINE_TYPE_ENABLE = "1";
    }


    /**
     * 流程状态
     */
    public final class workflow {

        @Deprecated
        public static final String WORKFLOW_STOP_STATUS = "0";

        /*正在运行*/
        public static final String RUN_STATUS_RUNNING = "0";
        /*运行成功*/
        public static final String RUN_STATUS_SUCCEED = "1";
        /*运行失败*/
        public static final String RUN_STATUS_ERROR = "-1";

        /*部署状态*/
        public static final String DEPLOY_STATUS = "1";
        /*取消部署*/
        public static final String UN_DEPLOY_STATUS = "0";

        /*流程外部传入参数*/
        public static final String WORKFLOW_MSG = "msg";

        /**
         * 流程作业监听任务在指定的节点机上执行
         */
        public static final String WORKFLOW_LISTENINGCODE_IP = "workflow_listeningcode_ip";

        /**
         * 任务分配的key值
         */
        public static final String WORKFLOW_OPERATION_TASK_KEY = "workflow_operation_task_key";
        /**
         * 流程任务key
         */
        public static final String TOTAL_LIST_KEY = "total_list_key";

        /**
         * 数据包描述
         */
        public static final String DATA_PACKAGE_DESCRIBE = "data_package_describe";

        /**
         * 是否指定ip执行
         */
        public static final String IS_LOCAL_IP = "is_local_ip";

        /**
         * 其他参数
         */
        public static final String OTHER_KEY = "_other_key";


        /**
         * 增量字段名称
         */
        public static final String WF_INCRE_NAME = "_wf_increment_name";

        /**
         * 增量字段值
         */
        public static final String WF_INCRE_VAL = "_wf_increment_val";

        public static final String INCRE_VAL_BEGIN_DATE = "_increment_begin_date";
        public static final String INCRE_VAL_END_DATE = "_increment_end_date";


    }


    /**
     * 插件唯一标识符
     */
    public final class plugin {

        /*输入数据源*/
        public static final String DB_INSOURCE = "inDS_conn";

        /*输出数据源*/
        public static final String DB_OUTSOURCE = "outDS_conn";

        /*流程插件流转中用以存放请求参数的标识*/
        public static final String DB_PARAMS = "_DB_PARAMS";

        /*流程id标示*/
        public static final String WORK_FLOW_ID = "_WORK_FLOW_ID";

        /*流程code*/
        public static final String WORK_FLOW_CODE_KEY = "WORK_FLOW_CODE";

        /*  用来标识插件和fourinone之间的数据*/
        public static final String WARE_HOUSE = "_WARE_HOUSE";

        /*  标识引擎列表  */
        public static final String ENGINE_LIST_TAG = "_ENGINE_LIST";

        /*  调用服务，反馈的数据结果集*/
        public static final String SERVICE_DATA_RESULT = "_DATA_RESULT";

        /*  流程运行时，用于关联数据源的key */
        public static final String DATA_SOURCE_KEY = "_DATA_SOURCE_KEY";

        public static final String ENGINE_KEY = "_ENGINE_KEY";

        /*-------------------------------流程运行调用插件统一参数设置---------------------------*/
        /*参数统一调用content key*/
        public static final String PLUG_CONTENT = "PLUG_CONTENT";

        /*参数统一调用的key  即插件类型*/
        public static final String PLUG_TYPE_KEY = "PLUG_TYPE_KEY";


        /**
         * -------------------------- db plugin start------------------------------------
         */
        /*DB_INSERT 插件*/
        public static final String PLUG_DB_INSERT = "db_insert ";


        /*DB_UPSert 插件*/
        public static final String PLUG_DB_UPSERT = "db_upsert";

        /*GIS数据同步 插件*/
        public static final String PLUG_GIS_DB_SYNC = "gis_db_sync";

        public static final String PLUG_EXCEL_DIR_IMPORT = "excel_import";


        /*DB_SELECT 插件*/
        public static final String PLUG_DB_QUERY = "db_query";

        /*DB_COUNT 插件*/
        public static final String PLUG_DB_COUNT = "db_count";

        /*日志插件*/
        public static final String PLUG_RESULT_LOG = "result_log";
        /**-------------------------- db plugin end----------------------------------------*/

        /**
         * -------------------------- file plugin start------------------------------------
         */
//        /*fileCount 插件*/
//        public static final String PLUG_FILE_COUNT = "filecount";
        /*参数统一调用的key  inFileType 文件总数查询类型*/
        public static final String PLUG_QUERY_IN_FILE_TYPE_KEY = "inFileType";
        /*参数统一调用的key  inFileType 文件总数查询类型*/
        public static final String PLUG_QUERY_OUT_FILE_TYPE_KEY = "outFileType";
        /*参数统一调用的value=1   (查询文件) inFileType 文件总数查询类型*/
        public static final String PLUG_QUERY_FILE_VALUE = "1";
        /*参数统一调用的value=2   (查询文件目录) inFileType 文件目录总数查询类型*/
        public static final String PLUG_QUERY_FILE_DIR_VALUE = "2";
        /**-------------------------- file plugin end----------------------------------------*/

        /**
         * -------------------------- localfile plugin start----------------------------------------
         */
        /* 本地文件 总数 */
        public static final String PLUG_LOCALFILE_COUNT = "localfile_count";
        /* 本地文件 -> bigfile */
        public static final String PLUG_LOCALFILE_TO_BIGFILE = "localfile_to_bigfile";
        /**-------------------------- localfile plugin end----------------------------------------*/

        /**
         * -------------------------- bigfile plugin start----------------------------------------
         */
        public static final String PLUG_BIGFILE_COUNT = "bigfile_count";
        public static final String PLUG_BIGFILE_TO_LOCALFILE = "bigfile_to_localfile";
        /**-------------------------- bigfile plugin end----------------------------------------*/

        /**
         * -------------------------- 数据生产 plugin start----------------------------------------
         */

        /* 生产数据导入 创建目录 */
        public static final String PLUGIN_DP_IMPORT_CREATE_DIR = "dp_import_create_dir";
        /* 生产数据导入 任务分配（远程工程目录） */
        public static final String PLUGIN_DP_IMPORT_WINREMOTE_TASK = "dp_import_remote_task";
        /* 生产数据导入 大文件系统*/
        public static final String PLUGIN_DP_IMPORT_TO_BIGFILE = "dp_import_bigfile";
        public static final String PLUGIN_DP_IMPORT_TO_CLOUDDB = "dp_import_clouddb";
        public static final String PLUGIN_DP_OBFUSCATE = "dp_obfuscate";
        public static final String PLUGIN_DP_DATA_RELEASE = "dp_data_release";
        public static final String PLUGIN_DP_DATA_COUNT_QUERY = "dp_data_count_query";
        public static final String PLUGIN_DP_DATA_DOWNLOAD = "dp_data_download";
        public static final String PLUGIN_DP_DATA_UPLOAD = "dp_data_upload";
        public static final String PLUGIN_DP_DATA_DEPLOY_TO_CLOUDDB = "dp_data_delpoy_info_to_clouddb";
        public static final String PLUGIN_DP_DATA_READ_LOCAL_DIR = "dp_data_read_local_dir";
        public static final String PLUGIN_DP_DATA_CREATE_DIR_TO_SMALLFILE = "dp_data_create_dir_to_smallfile";
        public static final String PLUGIN_DP_DATA_BLUR_TO_CLOUDDB = "dp_data_blur_info_to_clouddb";


        /* 拼接任务分配 */
        public static final String PLUG_DP_STITCHING_TASK = "data_image_stitching_task";
        /* 生产数据下发 */
        public static final String PLUG_DP_SEND = "data_produce_send";
        /* 图片拼接 */
        public static final String PLUG_DP_IMAGE_STITCHING = "dp_image_stitching";
        /* 图片拼接成果数据上传 */
        public static final String PLUG_DP_IMAGE_STITCHING_RESULT = "dp_image_stitching_result";

        public static final String PLUG_DP_ACTUAL_BLUR_TASK_ALLOCATE = "dp_actual_blur_task_allocate";
        /*实际模糊化任务下发插件*/
        public static final String PLUG_DP_ACTUAL_BLUR_DATA_SEND_TO_NODE = "dp_actual_blur_data_send_to_node";
        /*实际模糊化执行插件*/
        public static final String PLUG_DP_ACTUAL_BLUR = "dp_actual_blur";
        /*实际模糊化云数据库存储插件*/
        public static final String PLUG_DP_ACTUAL_BLUR_TO_CLOUDB = "dp_actual_blur_to_cloudb";


        /**-------------------------- 数据生产 plugin end----------------------------------------*/


        /**
         * -------------------------- hdfs plugin start------------------------------------
         */
         /*HDFS_READ 插件*/
        public static final String PLUG_HDFS = "hdfs";

        /*文件备份目录名称*/
        public static final String PLUG_FILE_BACKUP_DIR = "/backup";
        /**
         * -------------------------- hdfs plugin end----------------------------------------
         */


        // -------------------------- clouddb plugin start------------------------------------//
        public static final String PLUGIN_CLOUDDB_UPSERT = "clouddb_upsert";

        //-------------------------- clouddb plugin end----------------------------------------//

        /**
         * -------------------------- tachyon plugin start------------------------------------
         */
         /*TACHYON插件*/
        public static final String PLUG_TACHYON = "tachyon";

        /**
         * -------------------------- kafka plugin start------------------------------------
         */
        /*KAFKA_SEND 插件*/
        public static final String PLUG_KAFKA_SEND_VALUE = "KAFKA_SEND";

        /*KAFKA_RECEIVE 插件*/
        public static final String PLUG_KAFKA_RECEIVE_VALUE = "KAFKA_RECEIVE";

        /**
         * -------------------------- redis plugin end----------------------------------------
         */
        public static final String PLUGIN_REDIS_COUNT = "redis_count";

        /**-------------------------- kafka plugin end----------------------------------------*/

        /**
         * -------------------------- localfile plugin start------------------------------------
         */
        /*localfile 插件*/
        public static final String PLUG_LOCAL_FILE = "localfile";
        /*文件备份目录名称*/
        public static final String PLUG_LOCAL_FILE_BACKUP_DIR = "/backup";
        /**-------------------------- localfile plugin end----------------------------------------*/

        /**
         * -------------------------- winfile plugin start------------------------------------
         */
        /*winfile 插件*/
        public static final String PLUG_WIN_FILE = "winfile";
        /*文件备份目录名称*/
        public static final String PLUG_WIN_FILE_BACKUP_DIR = "/backup";
        /**-------------------------- winfile plugin end----------------------------------------*/


        /**
         * -------------------------- pci plugin start------------------------------------
         */
        /*pci_autogcp   控制点采集  插件*/
        public static final String PLUG_PCI_AUTOGCP = "autogcp2";
        /*pci_autotie   连接点采集  插件*/
        public static final String PLUG_PCI_AUTOTIE = "autotie2";
        /*pci_fimport 导入   插件*/
        public static final String PLUG_PCI_FIMPORT = "fimport";
        /*pci_gcprefn  控制点过滤   插件*/
        public static final String PLUG_PCI_GCPREFN = "gcprefn";

        public static final String PLUG_PCI_GCPPRO = "gcppro";

        public static final String PLUG_PCI_GCPPRUNE = "gcpprune";

        public static final String PLUG_PCI_RFMODEL = "rfmodel";


        /*pci_oemodel    正射校正工程建立   插件*/
        public static final String PLUG_PCI_OEMODEL = "oemodel";
        /*pci_ortho   正射校正     插件*/
        public static final String PLUG_PCI_ORTHO = "ortho";
        /*pci_tprefn    连接点过滤   插件*/
        public static final String PLUG_PCI_TPREFN = "tprefn";

        public static final String PLUG_PCI_PYRAMID = "pyramid";

        public static final String PLUG_PCI_QBASMBLE = "qbasmble";

        public static final String PLUG_PCI_LINK = "link";

        public static final String PLUG_PCI_CRPROJ = "crproj";

        /* 生成工程概要*/
        public static final String PLUG_PCI_OEPROJ_SUM = "oeprojsum";

        /* 写入优化后的数学模型*/
        public static final String PLUG_PCI_CPMMSEG = "cpmmseg";


        /**
         * -------------------------- pci plugin end----------------------------------------
         */


    }

    /**
     * redis
     */
    public final class redis {

        /*IP*/
        public static final String IP = "ip";

        /*端口*/
        public static final String PORT = "port";
        /*key*/
        public static final String KEY = "key";
        /*value*/
        public static final String VALUE = "value";
        /*数据类型*/
        public static final String DATA_TYPE = "data_type";
        /*数据类型  字符型*/
        public static final String DATA_TYPE_STR = "str";
        /*数据类型  字节*/
        public static final String DATA_TYPE_BYTE = "byte";
    }

    /**
     * 共享远程目录
     */
    public final class remote {

        /*IP*/
        public static final String IP = "ip";

        /*端口*/
        public static final String PORT = "port";

        /*用户名*/
        public static final String USERNAME = "username";

        /*密码*/
        public static final String PASSWORD = "password";

        /*文件目录*/
        public static final String FILE_DIR = "file_dir";

        /*文件名称*/
        public static final String FILE_NAME = "file_name";

        /*文件格式   文件格式以逗号隔开*/
        public static final String FILE_FORMAT = "file_format";

    }


    /**
     * 本地文件
     */
    public final class localFile {

        /*文件目录*/
        public static final String lOCAL_FILE_DIR = "local_file_dir";

        /*文件名字*/
        public static final String lOCAL_FILE_NAME = "local_file_name";

        /*文件格式   文件格式以逗号隔开*/
        public static final String FILE_FORMAT = "file_format";


    }

    /**
     * hdfs
     */
    public final class hdfs {
        /*IP*/
        public static final String IP = "ip";

        /*端口*/
        public static final String PORT = "port";

        /*HDFS中文件目录*/
        public static final String HDFS_FILE_DIR = "hdfs_file_dir";

        /*文件名字*/
        public static final String HDFS_FILE_NAME = "hdfs_file_name";

        /*文件格式   文件格式以逗号隔开*/
        public static final String FILE_FORMAT = "file_format";


    }

    public final class rest {
        /*IP*/
        public static final String IP = "ip";

        /*端口*/
        public static final String PORT = "port";
    }


    /**
     * phoenix
     */
    public final class phoenix {
        /*url*/
        public static final String URL = "url";

        /*插件插入数据时的sql语句*/
        public static final String SQL = "sql";

        /*插件插入数据时的list对象*/
        public static final String UPSERT_LIST = "upsertList";


        public static final String TABLE_SCHEMA = "tableSchema";

        /*插件导入数据时的表名*/
        public static final String TABLE_NAME = "tableName";

        /*插件导入数据时的值*/
        public static final String CSV_DATA_BYTE = "csvDataByte";

        /*插件导出到HDFS时的文件系统*/
        public static final String FILE_SYSTEM = "fileSystem";

        /*插件导出到HDFS时的路径*/
        public static final String PATH = "path";


        /*用户名*/
        public static final String USERNAME = "username";

        /*密码*/
        public static final String PASSWORD = "password";

    }


    /**
     * Restful 对外的静态变量
     */
    public final class jsonView {

        public static final String STATUS_SUCCESS = "success";

        public static final String STATUS_FAIL = "fail";

        public static final String NONE_LOGIN = "none_login";

        /*  错误信息列表  */
        public static final String ERRMSG_PAGE = "分页信息为空";
        public static final String ERRMSG_ID = "主键ID为空";
        public static final String ERRMSG_OBJ = "对象为空";
    }


    /**
     * 数据源类型
     */
    public final class DataSource {

        /* 数据类型 0 结构型数据，1 文件*/
        public static final String DATA_TYPE = "_DATA_TYPE";

        /*  用于标识表名称变量 */
        public static final String TABLE_NAME_KEY = "_TABLE_NAME";
        /* 用区别表结构的前缀 */
        public static final String TABLE_COLUMN_PREFIX = "COLUMN_PREFIX_";

        public static final String DB_IP = "db_ip";
        public static final String DB_PORT = "db_port";
        public static final String DB_URL = "db_url";
        public static final String DB_TYPE = "db_type";
        public static final String DB_DRIVENAME = "db_drivename";
        public static final String DB_USERNAME = "db_username";
        public static final String DB_PASSWORD = "db_password";
        public static final String DB_NAME = "db_name";


        public static final String DS_TYPE_KAFKA = "KAFKA";
        public static final String DS_TYPE_ES = "elasticearch";
        public static final String DS_TYPE_CLOUD_HDFS = "HDFS";
        public static final String DS_TYPE_CLOUD_HBASE = "HBase";
        public static final String DS_TYPE_FTP = "FTP";
        public static final String DS_TYPE_CLOUDDB = "CloudDB";
        public static final String DS_TYPE_MONGODB = "MongoDB";
        public static final String DS_TYPE_ORACLE = "ORACLE";
        public static final String DS_TYPE_SQLSERVER = "SQLServer";
        public static final String DS_TYPE_DB2 = "DB2";
        public static final String DS_TYPE_MYSQL = "MySQL";
        public static final String DS_TYPE_WIN_RMT_DIR = "Win";
        public static final String DS_TYPE_LINUX_RMT_DIR = "Linux";
        public static final String DS_TYPE_LOCAL = "LOCAL";
        public static final String DS_TYPE_REDIS = "Redis";
        public static final String DS_TYPE_TACHYON = "tachyon";
        public static final String DS_TYPE_TFS = "TFS";
        public static final String DS_TYPE_MQ = "MQ";
        public static final String DS_TYPE_GREENPLUM = "GREENPLUM";
        public static final String DS_TYPE_IGNITE = "IGNITE";
        public static final String DS_TYPE_GLUSTERFS = "GLUSTERFS";

        public static final String DS_TYPE_KAFKA_INT = "1";
        public static final String DS_TYPE_ES_INT = "2";
        public static final String DS_TYPE_FTP_INT = "3";
        public static final String DS_TYPE_CLOUDDB_INT = "4";
        public static final String DS_TYPE_MONGODB_INT = "5";
        public static final String DS_TYPE_ORACLE_INT = "6";
        public static final String DS_TYPE_SQLSERVER_INT = "7";
        public static final String DS_TYPE_DB2_INT = "8";
        public static final String DS_TYPE_MYSQL_INT = "9";
        public static final String DS_TYPE_WIN_RMT_DIR_INT = "10";
        public static final String DS_TYPE_LINUX_RMT_DIR_INT = "11";
        public static final String DS_TYPE_LOCAL_INT = "12";
        public static final String DS_TYPE_REDIS_INT = "13";
        //结构化数据是HBASE
        public static final String DS_TYPE_CLOUD_HBASE_INT = "14";
        //非结构化数据是HDFS
        public static final String DS_TYPE_CLOUD_HDFS_INT = "15";
        //TFS文件系统
        public static final String DS_TYPE_TFS_INT = "16";
        //tachyon
        public static final String DS_TYPE_TACHYON_INT = "17";
        //MQ
        public static final String DS_TYPE_MQ_INT = "18";

        public static final String DS_TYPE_GREENPLUM_INT = "19";
        //IGNITE
        public static final String DS_TYPE_IGNITE_INT = "20";

        public static final String DS_TYPE_GLUSTERFS_INT = "21";


    }


    /**
     * 指标项 数据类型
     * 0：数据库
     * 1：文本
     */
    public final class ItemDataType {

        public static final String DS_TYPE_DB = "0";
        public static final String DS_TYPE_TEXT = "1";

    }

    /**
     * 指标项 文件类型
     * 0：目录
     * 1：文件
     */
    public final class ItemFileType {

        public static final String ITEM_DIR = "Folder";
        public static final String ITEM_FILE = "文件";

    }

    /**
     * 远程连接数据源的IP和端口
     */
    public final class ipAndPort {
        public static final String CONN_IP = "localhost";
        public static final String CONN_PORT = "5052";
    }


    public final class kafka {

        /*IP*/
        public static final String IP = "ip";

        /*端口*/
        public static final String PORT = "port";

        /*主题*/
        public static final String TOPIC = "topic";

        /*TYPE*/
        public static final String TYPE = "type";

        //字符类型
        public static final String STR_TYPE = "0";
        //文件类型
        public static final String FILE_TYPE = "1";
        //视频类型
        public static final String VIDEO_TYPE = "2";

        /*  连接类型为producer */
        public static final String KAFKA_CONN_PRODUCER = "0";
        /*  连接类型为Consumer */
        public static final String KAFKA_CONN_CONSUMCER = "1";

        public static final String KAFKA_GROUP_KEY = "_KAFKA_GROUP";

        public static final String KAFKA_TOPIC_KEY = "_KAFKA_TOPIC";

        /* temp data str key*/
        public static final String DATA_STR_TMP_KEY = "_DATA_STR";

        public static final String KAFKA_TMP_KEY = "_TMP_KEY";
    }

    /*分布式索引*/
    public final class elasticsearch {

        public static final int CONN_PORT = 9300;
        /*设置elasticsearch集群的名称*/
        public static final String CLUSTER_NAME = "elasticsearch";

        public static final int MAX_QUERY_TERMS = 20;

    }

    /*zookeeper*/
    public final class ZooKeeper {

        /*连接zookeeper的Session超时时间*/
        public static final int SESSION_TIME_OUT = 30000;

        public static final String SLANT = "/";

        public static final String BACK_SLANT = "\\";

        public static final int CONN_PORT = 2181;

        public static final String ZK_STATE_CONNECTING = "CONNECTING";

        public static final String ZK_STATE_CLOSED = "CLOSED";

    }

    /*python*/
    public final class python {

        public static final String MID_FLAG = "$_#_$";

        /*脚本名称*/
        public static final String SCRIPTS_NAME = "scripts_name";

        /*执行函数*/
        public static final String FUNCTION = "function";

        /*参数*/
        public static final String PARAMETER = "parameter";


        public static final String PCI_PYTHON_FILE = "main.py";

    }

    /**
     * 数据库导出
     */
    public final class export {
        public static final String HBASE_TYPE = "0";

        public static final String MYSQL_TYPE = "1";

        public static final String ORACLE_TYPE = "2";

        public static final String DM_TYPE = "3";
    }

    /**
     * 规则引擎
     */
    public final class rule {
        /*规则类型*/
        public static final String QL_TYPE = "type";
        /*常用规则*/
        public static final String QL_COMMON_RULE = "common_rule";
        /*自定义规则*/
        public static final String QL_CUSTOM_RULE = "custom_rule";
        /*规则表达式*/
        public static final String QL_Express_RULE = "express";
        //固定参数
        public static final String VAR = "str";
        //参数列表
        public static final String COLUMN = "param_column";

    }

    /**
     * 任务相关
     */
    public final class task {
        /*任务已启动*/
        public static final String TASK_RUN_STATUS = "1";

        /*任务未启动*/
        public static final String TASK_STOP_STATUS = "0";
    }


    /**
     * 系统属性
     */
    public final class system {
        /*采集系统*/
        public static final String DATA_GATHER_SYS_NAME = "DATA_GATHER";

        /*数据库管理系统*/
        public static final String DATA_BASE_SYS_NAME = "DATA_BASE";

        public static final String DATA_ETL_SYS_NAME = "DATA_ETL";

        public static final String DATA_ESB_SYS_NAME = "DATA_ESB";


        /* 系统名称*/
        public static final String SYS_NAME_KEY = "SYS_NAME_KEY";
        /* 插件名称*/
        public static final String PLUGIN_NAME_KEY = "PLUGIN_NAME_KEY";
        /* 流程代码*/
        public static final String WORK_FLOW_CODE_KEY = "WORK_FLOW_CODE_KEY";
    }

    /**
     * 目录
     */
    public final class catalog {
        public static final String DIRECTORYTYPE_LAYER = "directoryType_layer";//图层目录
        public static final String DIRECTORYTYPE_APP = "directoryType_app";//应用目录
        public static final String DIRECTORYTYPE_SERVER = "directoryType_server";//服务目录
        public static final String DIRECTORYTYPE_DATA = "directoryType_data";//数据目录
        public static final String DIRECTORYTYPE_FILE = "directoryType_file";//文件目录
        public static final String DIRECTORYTYPE_PKG = "directoryType_pkg";//套餐目录
    }

    /**
     * 用户
     */
    public final class user {
        public static final String COOKIE_USER = "USER";
        public static final String COOKIE_TENANT = "TENANT";
        public static final String COOKIE_URL = "URL";
        public static final String COOKIE_USER_NAME = "USER_NAME";
        public static final String COOKIE_ORG_ID = "ORG_ID";
        /**
         * 系统用户
         */
        public static final String USER_TYPE = "0";
        /**
         * 租户下用户
         */
        public static final String TENAT_USER_TYPE = "1";
    }

    /**
     * 定义存储管理的数据类型
     */
    public final class dataSourceType {

        public static final String HBASE = "4";

        public static final String ORACEL = "6";

        public static final String SQL_SERVER = "7";

        public static final String DB2 = "8";

        public static final String MYSQL = "9";

    }

    /**
     * 定义索引日志状态
     */
    public final class indexTaskLog {
        //成功
        public static final String SUCCESS = "1";
        //失败
        public static final String FAILED = "0";
    }

    /**
     * 定义数据导出的类型
     */
    public final class exportType {

        public static final String INSERT = "1";

        public static final String CSV = "2";

        public static final String UPSERT = "3";

        public static final String EXCEL = "4";

    }

    /*定义分隔符*/
    public static final String SPLIT_COMMA = ",";
    public static final String SPLIT_HASH_KEY = "#";


    /**
     * tachyon
     */
    public final class tachyon {
        /*IP*/
        public static final String IP = "ip";

        /*端口*/
        public static final String PORT = "port";

        /*es url*/
        public static final String ES_HOST = "es_host";

        /*es 集群名称*/
        public static final String ES_CLOUSTER = "es_cluster";

        /*中文件目录*/
        public static final String TACHYON_FILE_DIR = "tachyon_file_dir";

        /*文件名字*/
        public static final String TACHYON_FILE_NAME = "tachyon_file_name";

        /*文件格式   文件格式以逗号隔开*/
        public static final String FILE_FORMAT = "file_format";

        //Elasticsearch information of FileSystem.
        public static final String ES_IP = "_ES_IP";
        public static final String ES_PORT = "_ES_PORT";
        public static final String ES_INDEX_NAME = "_ES_INDEX_NAME";
        public static final String ES_INDEX_TYPE = "_ES_INDEX_TYPE";
        public static final String ES_CLUSTER_NAME = "_ES_CLUSTER_NAME";
    }

    /**
     * 定义System资源类型，数据类型
     */
    public final class systemType {

        public static final String RES_TYPE_DATA = "resourceType_data";

        public static final String RES_TYPE_SERVICE = "resourceType_server";

        public static final String RES_TYPE_LAYER = "resourceType_layer";

        public static final String RES_TYPE_APP = "resourceType_app";

        public static final String RES_TYPE_FILE = "resourceType_file";

        public static final String RES_TYPE_MENU = "resourceType_menu";

        public static final String RES_TYPE_PKG = "resourceType_pkg";

    }


    public final class fileSystemConfig {

        public static final String TYPE_HDFS = DataSource.DS_TYPE_CLOUD_HDFS_INT;

        public static final String TYPE_TACHYON = DataSource.DS_TYPE_TACHYON_INT;

    }

    /**
     * 数据网格
     */
    public final class DataGrid {
        public static final String WORKFLOW_DATAGRID = "workflow_datagrid";
        public static final String LOG_DATAGRID = "log_datagird";
        public static final String MONITOR_LOG = "monitor_log";
        public static final String DB_DATAGRID = "db_datagrid";
        public static final String PROXY_DATAGRID = "proxy_datagrid";
    }


    /**
     * mq
     */
    public final class rabbitMQ {

        //数据采集 流程运行消息队列
        public static final String DG_WORKFLOW_RUN_Q = "dg_workflow_run_q";

        //数据采集 流程运行日志消息队列
        public static final String DG_WORKFLOW_LOG_Q = "dg_workflow_log_q";
        //流程分配的任务消息队列
        public static final String DG_WORKFLOW_OPERATION_TASK_Q = "dg_workflow_operation_task_q_fb";

//        public static final String EXCHANGE_NAME = "exchange_name";
//
//        public static final String ROUTE_KEY = "route_Key";


    }

    public static List<String> ImageTypes = new ArrayList<String>();

    static {
        ImageTypes.add("gif");
        ImageTypes.add("png");
        ImageTypes.add("jpg");
        ImageTypes.add("jpeg");
        ImageTypes.add("bmp");
    }

}
