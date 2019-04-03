package com.chimpim.rfidmodule;

@SuppressWarnings({"unused", "WeakerAccess"})
public interface Reader {

    /**
     * @deprecated 不推荐使用
     */
    @Deprecated
    READER_ERR InitReader(String src, Reader_Type rtype);

    /**
     * 连接读写器
     *
     * @param src (网口或者串口)，读写器天线端口数
     */
    READER_ERR InitReader_Notype(String src, int rtype);

    /**
     * 关闭读写器
     */
    void CloseReader();

    READER_ERR GetTagData(int ant, char bank, int address, int blkcnt, byte[] data, byte[] accesspasswd, short timeout);

    READER_ERR WriteTagData(int ant, char bank, int address, byte[] data, int datalen, byte[] accesspasswd, short timeout);

    READER_ERR WriteTagEpcEx(int ant, byte[] Epc, int epclen, byte[] accesspwd, short timeout);

    READER_ERR TagInventory(int[] ants, int antcnt, short timeout, TAGINFO[] pTInfo, int[] tagcnt);

    READER_ERR TagInventory_Raw(int[] ants, int antcnt, short timeout, int[] tagcnt);

    READER_ERR TagInventory_BaseType(int[] ants, int antcnt, short timeout, char[] outbuf, int[] tagcnt);

    READER_ERR GetNextTag(TAGINFO TI);

    READER_ERR GetNextTag_BaseType(byte[] outbuf);

    READER_ERR LockTag(int ant, byte lockobjects, short locktypes, byte[] accesspasswd, short timeout);

    READER_ERR KillTag(int ant, byte[] killpasswd, short timeout);

    READER_ERR Lock180006BTag(int ant, int startblk, int blkcnt, short timeout);

    READER_ERR BlockPermaLock(int ant, int readlock, int startblk, int blkrange, byte[] mask, byte[] pwd, short timeout);

    READER_ERR BlockErase(int ant, int bank, int wordaddr, int wordcnt, byte[] pwd, short timeout);

    READER_ERR EraseDataOnReader();

    READER_ERR SaveDataOnReader(int address, byte[] data, int datalen);

    READER_ERR ReadDataOnReader(int address, byte[] data, int datalen);

    READER_ERR CustomCmd(int ant, CustomCmdType cmdtype, Object CustomPara, Object CustomRet);

    READER_ERR CustomCmd_BaseType(int ant, int cmdtype, byte[] CustomPara, byte[] CustomRet);

    READER_ERR SetGPO(int gpoid, int val);

    READER_ERR GetGPI(int gpoid, int[] val);

    READER_ERR PsamTransceiver(int soltid, int coslen, byte[] cos, int[] cosresplen, byte[] cosresp, byte[] errcode, short timeout);

    READER_ERR ParamGet(Mtr_Param key, Object val);

    READER_ERR ParamSet(Mtr_Param key, Object val);


    int MAXANTCNT = 16;
    int MAXIPSTRLEN = 50;
    int HOPTABLECNT = 100;
    int MAXEPCBYTESCNT = 62;
    int MAXEMBDATALEN = 128;
    int MAXINVPOTLSCNT = 6;

    /**
     * 模块类型
     */
    enum Module_Type {
        MODOULE_NONE(0),
        MODOULE_R902_M1S(1),
        MODOULE_R902_M2S(2),
        MODOULE_M5E(3),
        MODOULE_M5E_C(4),
        MODOULE_M6E(5),
        MODOULE_PR9000(6),
        MODOULE_M5E_PRC(7),
        MODOULE_M6E_PRC(8),
        MODOULE_M6E_MICRO(9),
        MODOULE_SLR1100(10),
        MODOULE_SLR1200(11),
        MODOULE_SLR1300(12),
        MODOULE_SLR3000(13),
        MODOULE_SLR5100(14),
        MODOULE_SLR5200(15),
        MODOULE_SLR3100(16),
        MODOULE_SLR3200(17);

        private int value;

        Module_Type(int value) {    //    必须是private的，否则编译错误
            this.value = value;
        }

        public static Module_Type valueOf(int value) {    //    手写的从int到enum的转换函数
            switch (value) {
                case 0:
                    return MODOULE_NONE;
                case 1:
                    return MODOULE_R902_M1S;
                case 2:
                    return MODOULE_R902_M2S;
                case 3:
                    return MODOULE_M5E;
                case 4:
                    return MODOULE_M5E_C;
                case 5:
                    return MODOULE_M6E;
                case 6:
                    return MODOULE_PR9000;
                case 7:
                    return MODOULE_M5E_PRC;
                case 8:
                    return MODOULE_M6E_PRC;
                case 9:
                    return MODOULE_M6E_MICRO;
                case 10:
                    return MODOULE_SLR1100;
                case 11:
                    return MODOULE_SLR1200;
                case 12:
                    return MODOULE_SLR1300;
                case 13:
                    return MODOULE_SLR3000;
                case 14:
                    return MODOULE_SLR5100;
                case 15:
                    return MODOULE_SLR5200;
                case 16:
                    return MODOULE_SLR3100;
                case 17:
                    return MODOULE_SLR3200;
                default:
                    return null;
            }
        }

        public int value() {
            return this.value;
        }

    }

    /**
     * 区域类型
     */
    enum Region_Conf {
        RG_NONE(0x0),
        RG_NA(0x01),
        RG_EU(0x02),
        RG_EU2(0X07),
        RG_EU3(0x08),
        RG_KR(0x03),
        RG_PRC(0x06),
        RG_PRC2(0x0A),
        RG_OPEN(0xFF);

        int p_v;

        Region_Conf(int v) {
            p_v = v;
        }

        public int value() {
            return this.p_v;
        }

        public static Region_Conf valueOf(int value) {    //    手写的从int到enum的转换函数
            switch (value) {
                case 0:
                    return RG_NONE;
                case 1:
                    return RG_NA;
                case 2:
                    return RG_EU;
                case 7:
                    return RG_EU2;
                case 8:
                    return RG_EU3;
                case 3:
                    return RG_KR;
                case 6:
                    return RG_PRC;
                case 0x0A:
                    return RG_PRC2;
                case 0xff:
                    return RG_OPEN;
            }
            return null;
        }
    }

    /**
     * 主板类型
     */
    enum MaindBoard_Type {
        MAINBOARD_NONE,
        MAINBOARD_ARM7,
        MAINBOARD_SERIAL,
        MAINBOARD_WIFI,
        MAINBOARD_ARM9,
        MAINBOARD_ARM9_WIFI,
    }

    /**
     * 标签协议
     */
    enum SL_TagProtocol {
        SL_TAG_PROTOCOL_NONE(0x00),
        SL_TAG_PROTOCOL_ISO180006B(0x03),
        SL_TAG_PROTOCOL_GEN2(0x05),
        SL_TAG_PROTOCOL_ISO180006B_UCODE(0x06),
        SL_TAG_PROTOCOL_IPX64(0x07),
        SL_TAG_PROTOCOL_IPX256(0x08);

        int p_v;

        SL_TagProtocol(int v) {
            p_v = v;
        }

        public int value() {
            return this.p_v;
        }

        public static SL_TagProtocol valueOf(int value) {    //    手写的从int到enum的转换函数
            switch (value) {
                case 0:
                    return SL_TAG_PROTOCOL_NONE;
                case 3:
                    return SL_TAG_PROTOCOL_ISO180006B;
                case 5:
                    return SL_TAG_PROTOCOL_GEN2;
                case 6:
                    return SL_TAG_PROTOCOL_ISO180006B_UCODE;
                case 7:
                    return SL_TAG_PROTOCOL_IPX64;
                case 8:
                    return SL_TAG_PROTOCOL_IPX256;
            }
            return null;
        }
    }


    /**
     * 锁对象
     */
    enum Lock_Obj {
        LOCK_OBJECT_KILL_PASSWORD(0x01), //锁定对象为销毁密码
        LOCK_OBJECT_ACCESS_PASSWD(0x02), //锁定对象为访问密码
        LOCK_OBJECT_BANK1(0x04), //锁定对象为bank1
        LOCK_OBJECT_BANK2(0x08),  //锁定对象为bank2
        LOCK_OBJECT_BANK3(0x10); //锁定对象为bank3

        int p_v;

        Lock_Obj(int v) {
            p_v = v;
        }

        public int value() {
            return this.p_v;
        }
    }


    /**
     * 锁类型
     */
    enum Lock_Type {
        KILL_PASSWORD_UNLOCK(0x0000),
        KILL_PASSWORD_LOCK(0x0200), //销毁密码密码锁定
        KILL_PASSWORD_PERM_LOCK(0x0300), //销毁密码永久锁定

        ACCESS_PASSWD_UNLOCK(0x00),
        ACCESS_PASSWD_LOCK(0x80), //访问密码密码锁定
        ACCESS_PASSWD_PERM_LOCK(0xC0), //访问密码永久锁定

        BANK1_UNLOCK(0x00),
        BANK1_LOCK(0x20), //bank1密码锁定
        BANK1_PERM_LOCK(0x30), //bank1永久锁定

        BANK2_UNLOCK(0x00),
        BANK2_LOCK(0x08), //bank2密码锁定
        BANK2_PERM_LOCK(0x0C),//bank2永久锁定

        BANK3_UNLOCK(0x00),
        BANK3_LOCK(0x02), //bank3密码锁定
        BANK3_PERM_LOCK(0x03); //bank3永久锁定

        int p_v;

        Lock_Type(int v) {
            p_v = v;
        }

        public int value() {
            return this.p_v;
        }
    }


    /**
     * 读写器类型
     */
    enum Reader_Type {
        MODULE_TWO_ANTS(0),
        MODULE_FOUR_ANTS(1),
        MODULE_THREE_ANTS(3),
        MODULE_ONE_ANT(4),
        PR9000(5),
        MODULE_ARM7_TWO_ANTS(6),
        MODULE_ARM7_FOUR_ANTS(7),
        M6E_ARM7_FOUR_ANTS(8),
        M56_ARM7_FOUR_ANTS(9),
        R902_M1S(10),
        R902_M2S(11),
        ARM7_16ANTS(12),
        SL_COMMN_READER(13);

        private int value;

        Reader_Type(int value) {    //    必须是private的，否则编译错误
            this.value = value;
        }

        public static Reader_Type valueOf(int value) {    //    手写的从int到enum的转换函数
            switch (value) {
                case 0:
                    return MODULE_TWO_ANTS;
                case 1:
                    return MODULE_FOUR_ANTS;
                case 2:
                    return MODULE_THREE_ANTS;
                case 3:
                    return MODULE_ONE_ANT;
                case 4:
                    return PR9000;
                case 5:
                    return MODULE_ARM7_TWO_ANTS;
                case 6:
                    return MODULE_ARM7_FOUR_ANTS;
                case 7:
                    return M6E_ARM7_FOUR_ANTS;
                case 8:
                    return M56_ARM7_FOUR_ANTS;
                case 9:
                    return R902_M1S;
                case 10:
                    return R902_M2S;
                case 11:
                    return ARM7_16ANTS;
                case 12:
                    return SL_COMMN_READER;
                default:
                    return null;
            }
        }

        public int value() {
            return this.value;
        }
    }


    /**
     * 返回值类型
     */
    enum READER_ERR {
        MT_OK_ERR(0),
        MT_IO_ERR(1),
        MT_INTERNAL_DEV_ERR(2),
        MT_CMD_FAILED_ERR(3),
        MT_CMD_NO_TAG_ERR(4),
        MT_M5E_FATAL_ERR(5),
        MT_OP_NOT_SUPPORTED(6),
        MT_INVALID_PARA(7),
        MT_INVALID_READER_HANDLE(8),
        MT_HARDWARE_ALERT_ERR_BY_HIGN_RETURN_LOSS(9),
        MT_HARDWARE_ALERT_ERR_BY_TOO_MANY_RESET(10),
        MT_HARDWARE_ALERT_ERR_BY_NO_ANTENNAS(11),
        MT_HARDWARE_ALERT_ERR_BY_HIGH_TEMPERATURE(12),
        MT_HARDWARE_ALERT_ERR_BY_READER_DOWN(13),
        MT_HARDWARE_ALERT_ERR_BY_UNKNOWN_ERR(14),
        M6E_INIT_FAILED(15),
        MT_OP_EXECING(16),
        MT_UNKNOWN_READER_TYPE(17),
        MT_OP_INVALID(18),
        MT_HARDWARE_ALERT_BY_FAILED_RESET_MODLUE(19),
        MT_MAX_ERR_NUM(20),
        MT_MAX_INT_NUM(21);// = 0xfffffff2,

        private int value;

        READER_ERR(int value) {    //    必须是private的，否则编译错误
            this.value = value;
        }

        public static READER_ERR valueOf(int value) {    //    手写的从int到enum的转换函数
            switch (value) {
                case 0:
                    return MT_OK_ERR;
                case 1:
                    return MT_IO_ERR;
                case 2:
                    return MT_INTERNAL_DEV_ERR;
                case 3:
                    return MT_CMD_FAILED_ERR;
                case 4:
                    return MT_CMD_NO_TAG_ERR;
                case 5:
                    return MT_M5E_FATAL_ERR;
                case 6:
                    return MT_OP_NOT_SUPPORTED;
                case 7:
                    return MT_INVALID_PARA;
                case 8:
                    return MT_INVALID_READER_HANDLE;
                case 9:
                    return MT_HARDWARE_ALERT_ERR_BY_HIGN_RETURN_LOSS;
                case 10:
                    return MT_HARDWARE_ALERT_ERR_BY_TOO_MANY_RESET;
                case 11:
                    return MT_HARDWARE_ALERT_ERR_BY_NO_ANTENNAS;
                case 12:
                    return MT_HARDWARE_ALERT_ERR_BY_HIGH_TEMPERATURE;
                case 13:
                    return MT_HARDWARE_ALERT_ERR_BY_READER_DOWN;
                case 14:
                    return MT_HARDWARE_ALERT_ERR_BY_UNKNOWN_ERR;
                case 15:
                    return M6E_INIT_FAILED;
                case 16:
                    return MT_OP_EXECING;
                case 17:
                    return MT_UNKNOWN_READER_TYPE;
                case 18:
                    return MT_OP_INVALID;
                case 19:
                    return MT_HARDWARE_ALERT_BY_FAILED_RESET_MODLUE;
                case 20:
                    return MT_MAX_ERR_NUM;
                case 21:
                    return MT_MAX_INT_NUM;
                default:
                    return null;
            }
        }

        public int value() {
            return this.value;
        }
    }

    /**
     * 特殊指令
     */
    enum CustomCmdType {
        NXP_SetReadProtect(0),
        NXP_ResetReadProtect(1),
        NXP_ChangeEAS(2),
        NXP_EASAlarm(3),
        NXP_Calibrate(4),

        ALIEN_Higgs2_PartialLoadImage(5),
        ALIEN_Higgs2_FullLoadImage(6),

        ALIEN_Higgs3_FastLoadImage(7),
        ALIEN_Higgs3_LoadImage(8),
        ALIEN_Higgs3_BlockReadLock(9),
        ALIEN_Higgs3_BlockPermaLock(10),

        IMPINJ_M4_Qt(11);

        private int value;

        CustomCmdType(int value) {    //    必须是private的，否则编译错误
            this.value = value;
        }

        public static CustomCmdType valueOf(int value) {    //    手写的从int到enum的转换函数
            switch (value) {
                case 0:
                    return NXP_SetReadProtect;
                case 1:
                    return NXP_ResetReadProtect;
                case 2:
                    return NXP_ChangeEAS;
                case 3:
                    return NXP_EASAlarm;
                case 4:
                    return NXP_Calibrate;
                case 5:
                    return ALIEN_Higgs2_PartialLoadImage;
                case 6:
                    return ALIEN_Higgs2_FullLoadImage;
                case 7:
                    return ALIEN_Higgs3_FastLoadImage;
                case 8:
                    return ALIEN_Higgs3_LoadImage;
                case 9:
                    return ALIEN_Higgs3_BlockReadLock;
                case 10:
                    return ALIEN_Higgs3_BlockPermaLock;
                case 11:
                    return IMPINJ_M4_Qt;
                default:
                    return null;
            }
        }

        public int value() {
            return this.value;
        }
    }


    /**
     * 读写器参数
     */
    enum Mtr_Param {
        MTR_PARAM_POTL_GEN2_SESSION(0),
        MTR_PARAM_POTL_GEN2_Q(1),
        MTR_PARAM_POTL_GEN2_TAGENCODING(2),
        MTR_PARAM_POTL_GEN2_MAXEPCLEN(3),
        MTR_PARAM_RF_ANTPOWER(4),
        MTR_PARAM_RF_MAXPOWER(5),
        MTR_PARAM_RF_MINPOWER(6),
        MTR_PARAM_TAG_FILTER(7),
        MTR_PARAM_TAG_EMBEDEDDATA(8),
        MTR_PARAM_TAG_INVPOTL(9),
        MTR_PARAM_READER_CONN_ANTS(10),
        MTR_PARAM_READER_AVAILABLE_ANTPORTS(11),
        MTR_PARAM_READER_IS_CHK_ANT(12),
        MTR_PARAM_READER_VERSION(13),
        MTR_PARAM_READER_IP(14),
        MTR_PARAM_FREQUENCY_REGION(15),
        MTR_PARAM_FREQUENCY_HOPTABLE(16),
        MTR_PARAM_POTL_GEN2_BLF(17),
        MTR_PARAM_POTL_GEN2_WRITEMODE(18),

        MTR_PARAM_POTL_GEN2_TARGET(19), //0:A; 1:B; 2:A->B; 3:B->A
        MTR_PARAM_TAGDATA_UNIQUEBYANT(20),
        MTR_PARAM_TAGDATA_UNIQUEBYEMDDATA(21),
        MTR_PARAM_TAGDATA_RECORDHIGHESTRSSI(22),
        MTR_PARAM_RF_TEMPERATURE(23),
        MTR_PARAM_RF_HOPTIME(24),
        MTR_PARAM_RF_LBT_ENABLE(25),
        MTR_PARAM_RF_SUPPORTEDREGIONS(26),
        MTR_PARAM_POTL_SUPPORTEDPROTOCOLS(27),
        MTR_PARAM_POTL_ISO180006B_BLF(28),
        MTR_PARAM_POTL_GEN2_TARI(29), //0:Tari of 25 microseconds;1:Tari of 12.5 microseconds;2:Tari of 6.25 microseconds
        MTR_PARAM_TRANS_TIMEOUT(30),
        MTR_PARAM_TAG_EMDSECUREREAD(31),
        MTR_PARAM_TRANSMIT_MODE(32),
        MTR_PARAM_POWERSAVE_MODE(33),
        MTR_PARAM_TAG_SEARCH_MODE(34),

        MTR_PARAM_POTL_ISO180006B_MODULATION_DEPTH(35),
        MTR_PARAM_POTL_ISO180006B_DELIMITER(36),

        MTR_PARAM_MAXINDEX(37);

        private int value;

        Mtr_Param(int value) {    //    必须是private的，否则编译错误
            this.value = value;
        }

        public static Mtr_Param valueOf(int value) {    //    手写的从int到enum的转换函数
            switch (value) {
                case 0:
                    return MTR_PARAM_POTL_GEN2_SESSION;
                case 1:
                    return MTR_PARAM_POTL_GEN2_Q;
                case 2:
                    return MTR_PARAM_POTL_GEN2_TAGENCODING;
                case 3:
                    return MTR_PARAM_POTL_GEN2_MAXEPCLEN;
                case 4:
                    return MTR_PARAM_RF_ANTPOWER;
                case 5:
                    return MTR_PARAM_RF_MAXPOWER;
                case 6:
                    return MTR_PARAM_RF_MINPOWER;
                case 7:
                    return MTR_PARAM_TAG_FILTER;
                case 8:
                    return MTR_PARAM_TAG_EMBEDEDDATA;
                case 9:
                    return MTR_PARAM_TAG_INVPOTL;
                case 10:
                    return MTR_PARAM_READER_CONN_ANTS;
                case 11:
                    return MTR_PARAM_READER_AVAILABLE_ANTPORTS;
                case 12:
                    return MTR_PARAM_POTL_GEN2_TAGENCODING;
                case 13:
                    return MTR_PARAM_POTL_GEN2_MAXEPCLEN;
                case 14:
                    return MTR_PARAM_RF_ANTPOWER;
                case 15:
                    return MTR_PARAM_RF_MAXPOWER;
                case 16:
                    return MTR_PARAM_RF_MINPOWER;
                case 17:
                    return MTR_PARAM_TAG_FILTER;
                case 18:
                    return MTR_PARAM_TAG_EMBEDEDDATA;
                case 19:
                    return MTR_PARAM_TAG_INVPOTL;
                case 20:
                    return MTR_PARAM_READER_CONN_ANTS;
                case 21:
                    return MTR_PARAM_READER_AVAILABLE_ANTPORTS;
                case 22:
                    return MTR_PARAM_TAGDATA_RECORDHIGHESTRSSI;
                case 23:
                    return MTR_PARAM_POTL_GEN2_MAXEPCLEN;
                case 24:
                    return MTR_PARAM_RF_ANTPOWER;
                case 25:
                    return MTR_PARAM_RF_MAXPOWER;
                case 26:
                    return MTR_PARAM_RF_MINPOWER;
                case 27:
                    return MTR_PARAM_TAG_FILTER;
                case 28:
                    return MTR_PARAM_TAG_EMBEDEDDATA;
                case 29:
                    return MTR_PARAM_TAG_INVPOTL;
                case 30:
                    return MTR_PARAM_READER_CONN_ANTS;
                case 31:
                    return MTR_PARAM_POTL_GEN2_Q;
                case 32:
                    return MTR_PARAM_POTL_GEN2_TAGENCODING;
                case 33:
                    return MTR_PARAM_POTL_GEN2_MAXEPCLEN;
                case 34:
                    return MTR_PARAM_TAG_SEARCH_MODE;
                case 35:
                    return MTR_PARAM_POTL_ISO180006B_MODULATION_DEPTH;
                case 36:
                    return MTR_PARAM_POTL_ISO180006B_DELIMITER;
                default:
                    return null;
            }
        }

        public int value() {
            return this.value;
        }
    }


    class TAGINFO implements Cloneable {
        public byte AntennaID;
        public int Frequency;
        public int TimeStamp;
        public short EmbededDatalen;
        public byte[] EmbededData = new byte[MAXEMBDATALEN];
        public byte[] Res = new byte[2];
        public short Epclen;
        public byte[] PC = new byte[2];
        public byte[] CRC = new byte[2];
        public byte[] EpcId = new byte[MAXEPCBYTESCNT];
        public int Phase;
        public SL_TagProtocol protocol;
        public int ReadCnt;
        public int RSSI;

        public Object clone() {
            Object o;
            try {
                o = super.clone();//Object中的clone()识别出你要复制
                //的是哪一个对象。
            } catch (CloneNotSupportedException e) {
                return null;
            }
            return o;
        }

    }


    class IMPINJM4QtPara {
        public byte[] AccessPwd;//[4]
        public int CmdType; //0 = read the QT control bits in cache;1 = write the QT control bits
        public int MemType; //0 = Tag uses Private Memory Map;1 = Tag uses Public Memory Map
        public int PersistType; //0 = write the QT Control to volatile memory;1 = write the QT Control to nonvolatile memory
        public int RangeType; //0 = Tag does not reduce range;1 = Tag reduces range if in or about to be in OPEN or SECURED state
        public short TimeOut;

        public IMPINJM4QtPara() {
            AccessPwd = new byte[4];
        }
    }


    class IMPINJM4QtResult {
        public int MemType;//0 = Tag uses Private Memory Map;1 = Tag uses Public Memory Map
        public int RangeType;//0 = Tag does not reduce range;1 = Tag reduces range if in or about to be in OPEN or SECURED state
    }


    class NXPChangeEASPara {
        public byte[] AccessPwd;//[4]
        public int isSet;
        public short TimeOut;

        public NXPChangeEASPara() {
            AccessPwd = new byte[4];
        }
    }


    class NXPEASAlarmPara {
        public byte DR;
        public byte MC;
        public byte TrExt;
        public short TimeOut;
    }


    class NXPEASAlarmResult {
        public byte[] EASdata;//[8];

        public NXPEASAlarmResult() {
            EASdata = new byte[8];
        }
    }


    class ALIENHiggs3BlockReadLockPara {
        public byte[] AccessPwd;//[4]
        public byte BlkBits;
        public short TimeOut;

        public ALIENHiggs3BlockReadLockPara() {
            AccessPwd = new byte[4];
        }
    }


    class AntPower {
        public int antid;
        public short readPower;
        public short writePower;
    }


    class AntPowerConf {
        public int antcnt;
        public AntPower[] Powers;//16

        public AntPowerConf() {
            Powers = new AntPower[16];
        }
    }


    class HardwareDetails {
        public Module_Type module;
        public MaindBoard_Type board;
        public Reader_Type logictype;
    }


    class EmbededSecureRead_ST {
        public int tagtype;
        public int pwdtype;
        public int ApIndexStartBitsInEpc;
        public int ApIndexBitsNumInEpc;
        public int bank;
        public int address;
        public int blkcnt;
        public int accesspwd;
    }


    class Reader_Ip {
        public byte[] ip;
        public byte[] mask;
        public byte[] gateway;
    }


    class Reader_Ver {
        public char[] ver;//[20];

        public Reader_Ver() {
            ver = new char[50];
        }
    }


    class TagFilter_ST {
        public int bank;
        public int startaddr;
        public int flen;
        public byte[] fdata;
        public int isInvert;

        public TagFilter_ST() {
            fdata = new byte[255];
        }
    }


    class EmbededData_ST {
        public int bank;
        public int startaddr;
        public int bytecnt;
        public byte[] accesspwd;
    }


    class AntLinkVSWR {
        public int andid;
        public float vswr;
    }


    class AntPortsVSWR {
        public int antcnt;
        public AntLinkVSWR[] AntVSWRS;//16[];

        public AntPortsVSWR() {
            AntVSWRS = new AntLinkVSWR[16];
        }
    }


    class ConnAnts_ST {
        public int antcnt;
        public int[] connectedants;//[MAXANTCNT];

        public ConnAnts_ST() {
            connectedants = new int[MAXANTCNT];
        }
    }


    class HoptableData_ST {
        public int[] htb;//100[HOPTABLECNT];
        public int lenhtb;

        public HoptableData_ST() {
            htb = new int[100];
        }
    }


    class Inv_Potl {
        public SL_TagProtocol potl;
        public int weight;
    }


    class Inv_Potls_ST {
        public int potlcnt;
        public Inv_Potl[] potls;//6[MAXINVPOTLSCNT];

        public Inv_Potls_ST() {
            potls = new Inv_Potl[6];
        }
    }


}
